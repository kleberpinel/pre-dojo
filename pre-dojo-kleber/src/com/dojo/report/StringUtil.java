package com.dojo.report;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy k:m:s");
	
	public static Date getDateLog(String line){
		Date startDate = null;
		try {
			startDate = sdf.parse(line.substring(0,19));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return startDate;
	}

	public static String getLogInfo(String line) {
		String substring = line.substring(22);
//		System.out.println("Something happended = [" +substring+ "]");
		return substring;
	}
}
