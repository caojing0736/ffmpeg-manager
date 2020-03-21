package com.sy.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.ObjectUtils;

public class DateUtil {

	private static SimpleDateFormat SDF_DATETIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat SDF_DATETIME_TWO = new SimpleDateFormat("yyyyMMddHHmmss");
	private static SimpleDateFormat SDF_DATA = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat SDF_Time = new SimpleDateFormat("HH:mm:ss");

	// 获得当前时间精确到时分秒
	static String getDate() {
		Date date = new Date();
		return SDF_DATETIME.format(date);
	}
	
	// 获得当前时间精确到时分秒
		static String getDate2() {
			Date date = new Date();
			return SDF_DATETIME_TWO.format(date);
		}

	static String getDate1() {
		Date date = new Date();
		return SDF_DATA.format(date);
	}

	// 传入年月日时分秒 获取date
	static Date getDateTimeByString(String stringDate) throws ParseException {
		return SDF_DATETIME.parse(stringDate);
	}

	// 传入年月日 获取date
	static Date getDateByString(String stringDate) throws ParseException {
		return SDF_DATA.parse(stringDate);
	}

	// 返回yyyy-MM-dd HH:MM:ss格式的时间
	static String forDateTime(Date date) {
		if (ObjectUtils.isEmpty(date)) {
			return null;
		}
		return SDF_DATETIME.format(date);
	}

	// 返回yyyy-MM-dd格式的现在日期
	static String getNowDate() {
		return SDF_DATA.format(new Date());
	}

	// 返回HH:mm:ss格式的现在时间
	static String getNowTime() {
		return SDF_Time.format(new Date());
	}

	// 结束时间将时间精确到某一天23点：59分
	static String getEndDate(String date1) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = simpleDateFormat.parse(date1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 获取默认选中的日期的年月日星期的值，并赋值
		Calendar calendar = Calendar.getInstance();// 日历对象
		calendar.setTime(date);// 设置当前日期
		String yearStr = calendar.get(Calendar.YEAR) + "";// 获取年份
		int month = calendar.get(Calendar.MONTH) + 1;// 获取月份
		int day = calendar.get(Calendar.DATE);// 获取日
		return yearStr + "-" + month + "-" + day + " " + "23:59:00";
	}

	// 获取某日期时间的00:00:00刚开始凌晨时分
	static String getBeginDate(String date1) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = simpleDateFormat.parse(date1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 获取默认选中的日期的年月日星期的值，并赋值
		Calendar calendar = Calendar.getInstance();// 日历对象
		calendar.setTime(date);// 设置当前日期
		String yearStr = calendar.get(Calendar.YEAR) + "";// 获取年份
		int month = calendar.get(Calendar.MONTH) + 1;// 获取月份
		int day = calendar.get(Calendar.DATE);// 获取日
		return yearStr + "-" + month + "-" + day + " " + "00:00:00";
	}
}
