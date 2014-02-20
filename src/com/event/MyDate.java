package com.event;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MyDate {
	public int year;
	public int month;
	public int date;
	public int hour;
	public int minute;
	public int second;
	
	public MyDate(){
		
	}
	
	public MyDate(	int year,
					int month,
					int date,
					int hour,
					int minute,
					int second
			){
		this.year = year;
		this.month = month; 	// DatePicker start at 0 for Jan
		this.date = date;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	
	public MyDate(	MyDate date){
		this.year = date.year;
		this.month = date.month;	
		this.date = date.date;
		this.hour = date.hour;
		this.minute = date.minute;
		this.second = date.second;
	}
	
	@SuppressWarnings("deprecation")
	public MyDate(long time){
		Date parseDate = new Date(time);
				
		this.year = parseDate.getYear() + 1900;	 	//Date use 1900 start year, like 2014 = 114
		this.month = parseDate.getMonth();		// Date start month at 0, Jan is 0
		this.date = parseDate.getDate();
		this.hour = parseDate.getHours();
		this.minute = parseDate.getMinutes();
		this.second = parseDate.getSeconds();
		
		Log.d("MyDate("+time+")", this.toString());
		
	}
	
	/*
	 * return dd/MM/yyyy kk:mm:ss
	 */
	public String toString(){
		String stringDate = String.valueOf(date);
		String stringMonth = String.valueOf(month + 1);		//display time coresspond to user
		String stringHour = String.valueOf(hour);
		String stringMinute = String.valueOf(minute);
		String stringSecond = String.valueOf(second);
		
		if(date < 10){
			stringDate = "0" + stringDate;
		}
		if(month < 10){
			stringMonth = "0" + stringMonth;
		}
		if(hour < 10){
			stringHour = "0" + stringHour;
		}
		if(minute < 10){
			stringMinute = "0" + stringMinute;
		}
		if(second < 10){
			stringSecond = "0" + stringSecond;
		}
		
		String result = stringDate 		+ "/" +
						stringMonth		+ "/" +
						String.valueOf(year) 	+ " " +
						stringHour 		+ ":" +
						stringMinute 	+ ":" +
						stringSecond
						;
		return result;
	}
	
	/*
	 * getTime
	 * @return: time in millisecond(long)
	 */
	@SuppressLint("SimpleDateFormat")
	public long getTime(){
		long time = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date parseDate = null;
		try {
			parseDate = sdf.parse(this.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Log.d("before parse",this.toString());
		time = parseDate.getTime();
		Log.d("getTime", parseDate.toString());
		return time;
	}
			
	
}
