package io.bbc13.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Lin
 * HH��24Сʱ��
 * hh��12Сʱ��
**/ 
public class CountTimeGap {
	
	public static long getMinuteGap(String fromTime, String toTime) throws ParseException {  // ���ݵĲ������㵽������
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		// ע�⣺hh����12�����00
		Date fromM = simpleFormat.parse(fromTime.substring(0, 16));    // ��ȡ������
	    Date toM = simpleFormat.parse(toTime.substring(0, 16)); 

		long from = fromM.getTime();  
		long to = toM.getTime();  
		int gap = (int) ((to - from) / (1000 * 60));  
		System.out.println("util.CountTimeGap ����ʱ��֮��ķ��Ӳ�gapΪ��" + gap);
		return gap;
	}
	
}
