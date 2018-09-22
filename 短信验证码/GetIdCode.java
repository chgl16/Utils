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
 * @短信验证码测试
 */
public class GetIdCode {

    // 产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    // 产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "";           // TODO 改这里
    static final String accessKeySecret = ""; // TODO 改这里



    public static SendSmsResponse sendSms(String phone, String code) throws ClientException {

        // 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        // 必填:待发送手机号
        request.setPhoneNumbers(phone);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName("波波评测"); // TODO 改这里
        // 必填:模块CODE
        request.setTemplateCode("SMS_144943184");  // TODO 改这里
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的用户,您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\"" + code + "\"}");

        // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        // request.setSmsUpExtendCode("90997");

        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        // request.setOutId("yourOutId");

        // hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if(sendSmsResponse.getCode()!= null && sendSmsResponse.getCode().equals("OK")){
                System.out.println("短信发送成功！");
        }else {
                System.out.println("短信发送失败！");
                System.out.println("code:" + sendSmsResponse.getCode());
        }
        return sendSmsResponse;
    }

    //以下为测试代码，随机生成验证码
    private static int newcode;
    public static int getNewcode() {
        return newcode;
    }
    public static void setNewcode(){
//         newcode = (int)(Math.random()*9999)+100;   这句有漏洞
         newcode = (int) Math.round(Math.random() * (9999-1000) +1000);
     }
    
    /* 给参数phone发送随机验证码，返回验证码 */
    public static String getIdCode(String phone) throws Exception {
         setNewcode();
         String code = Integer.toString(getNewcode());
         System.out.println(code);
         SendSmsResponse sendSms =sendSms(phone,code);//填写你需要测试的手机号码
         
         System.out.println("短信接口返回的数据----------------");
         System.out.println("Code=" + sendSms.getCode());
         System.out.println("Message=" + sendSms.getMessage());
         System.out.println("RequestId=" + sendSms.getRequestId());
         System.out.println("BizId=" + sendSms.getBizId());
		
         
         // 发送成功返回验证码，发送失败返回null
         if(sendSms.getCode().equals("OK")) {
        	 return code;
         } else {
        	 return sendSms.getCode();
         }
        

    }
}