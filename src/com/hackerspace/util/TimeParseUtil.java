package com.hackerspace.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;


/**
 * 时间转化工具类
 * @author tianx
 *
 */
public class TimeParseUtil {
	
	/**
	 * String 转化为 java.sql.Date
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static java.sql.Date StringtoDate(String date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		
		java.util.Date uDate = null;
		
		uDate =  format.parse(date);
			
		return new java.sql.Date(uDate.getTime());
		
	}
	
	public static String TimestamptoStr(Timestamp timestamp) {
		
		return timestamp.toString().substring(0, timestamp.toString().length()-2);
	}
	

}
