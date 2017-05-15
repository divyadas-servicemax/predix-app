package com.servicemax.predix.srm.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.servicemax.predix.srm.model.Data;

public class Utility {
	/**
	 * Converts object to json
	 * 
	 * @param object
	 * @return
	 */

	public static String convertObjectToString(Object object) {

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		String objectAsString = "";
		try {
			objectAsString = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return objectAsString;

	}
	
	public static void main(String[] args) {
		Data s = new Data();
		s.setMax(22);
		String a = "abc";
		Map<Integer, Object> aaa = new HashMap<>();
		
		aaa.put(1, a);
		
		s.setValues(aaa.get(1));
		System.out.println(convertObjectToString(s));
	}

	public static long getTimeInMs(String dateString) {
		long timeInMs = 0;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT);
		try {
			Date d = simpleDateFormat.parse(dateString);
			timeInMs = d.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timeInMs;
	}

	public static String getReadableDateForUI(long timeInMS) {
		// Saturday, November 5, 2016 at 9:01 AM UTC
		String dateStr = "";

		try {
			DateFormat dateFormat = new SimpleDateFormat(ApplicationConstants.UI_DATE_FORMAT);
			dateStr = dateFormat.format(new Date(timeInMS));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dateStr;
	}

	

}
