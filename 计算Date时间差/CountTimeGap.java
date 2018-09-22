package io.bbc13.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Lin
 * HH是24小时制
 * hh是12小时制
**/ 
public class CountTimeGap {
	
	public static long getMinuteGap(String fromTime, String toTime) throws ParseException {  // 传递的参数计算到了秒钟
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		// 注意：hh遇到12点会变成00
		Date fromM = simpleFormat.parse(fromTime.substring(0, 16));    // 截取到分钟
	    Date toM = simpleFormat.parse(toTime.substring(0, 16)); 

		long from = fromM.getTime();  
		long to = toM.getTime();  
		int gap = (int) ((to - from) / (1000 * 60));  
		System.out.println("util.CountTimeGap 两个时间之间的分钟差gap为：" + gap);
		return gap;
	}
	
}
