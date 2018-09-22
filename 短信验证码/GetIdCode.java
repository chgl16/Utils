package io.bbc13.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * @Lin
 * @2018.9.15
 * @������֤�����
 */
public class GetIdCode {

    // ��Ʒ����:��ͨ�Ŷ���API��Ʒ,�����������滻
    static final String product = "Dysmsapi";
    // ��Ʒ����,�����������滻
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO �˴���Ҫ�滻�ɿ������Լ���AK(�ڰ����Ʒ��ʿ���̨Ѱ��)
    static final String accessKeyId = "";           // TODO ������
    static final String accessKeySecret = ""; // TODO ������



    public static SendSmsResponse sendSms(String phone, String code) throws ClientException {

        // ������������ʱʱ��
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        // ��ʼ��acsClient,�ݲ�֧��region��
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // ��װ�������-��������������̨-�ĵ���������
        SendSmsRequest request = new SendSmsRequest();
        // ����:�������ֻ���
        request.setPhoneNumbers(phone);
        // ����:����ǩ��-���ڶ��ſ���̨���ҵ�
        request.setSignName("��������"); // TODO ������
        // ����:ģ��CODE
        request.setTemplateCode("SMS_144943184");  // TODO ������
        // ��ѡ:ģ���еı����滻JSON��,��ģ������Ϊ"�װ����û�,������֤��Ϊ${code}"ʱ,�˴���ֵΪ
        request.setTemplateParam("{\"code\":\"" + code + "\"}");

        // ѡ��-���ж�����չ��(�����������û�����Դ��ֶ�)
        // request.setSmsUpExtendCode("90997");

        // ��ѡ:outIdΪ�ṩ��ҵ����չ�ֶ�,�����ڶ��Ż�ִ��Ϣ�н���ֵ���ظ�������
        // request.setOutId("yourOutId");

        // hint �˴����ܻ��׳��쳣��ע��catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if(sendSmsResponse.getCode()!= null && sendSmsResponse.getCode().equals("OK")){
                System.out.println("���ŷ��ͳɹ���");
        }else {
                System.out.println("���ŷ���ʧ�ܣ�");
                System.out.println("code:" + sendSmsResponse.getCode());
        }
        return sendSmsResponse;
    }

    //����Ϊ���Դ��룬���������֤��
    private static int newcode;
    public static int getNewcode() {
        return newcode;
    }
    public static void setNewcode(){
//         newcode = (int)(Math.random()*9999)+100;   �����©��
         newcode = (int) Math.round(Math.random() * (9999-1000) +1000);
     }
    
    /* ������phone���������֤�룬������֤�� */
    public static String getIdCode(String phone) throws Exception {
         setNewcode();
         String code = Integer.toString(getNewcode());
         System.out.println(code);
         SendSmsResponse sendSms =sendSms(phone,code);//��д����Ҫ���Ե��ֻ�����
         
         System.out.println("���Žӿڷ��ص�����----------------");
         System.out.println("Code=" + sendSms.getCode());
         System.out.println("Message=" + sendSms.getMessage());
         System.out.println("RequestId=" + sendSms.getRequestId());
         System.out.println("BizId=" + sendSms.getBizId());
		
         
         // ���ͳɹ�������֤�룬����ʧ�ܷ���null
         if(sendSms.getCode().equals("OK")) {
        	 return code;
         } else {
        	 return sendSms.getCode();
         }
        

    }
}