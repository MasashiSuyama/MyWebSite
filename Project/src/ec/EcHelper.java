package ec;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;

public class EcHelper {
	/**
	 * セッションから指定データを取得（削除も一緒に行う）
	 */
	public static Object cutSessionAttribute(HttpSession session, String str) {
		Object test = session.getAttribute(str);
		session.removeAttribute(str);

		return test;
	}

	/**
	 * 今日の日付をString型で返す(yyyy-MM-dd)
	 */
	public static String getDate() {
		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = f.format(date);

		return dateStr;
	}

	/**
	 * 今日の日付から得た値日数後をStirng型で返す(yyyy-MM-dd)
	 */
	public static String getDateLate(int date) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, date);

		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		String monthStr = "";
		if(month >= 10) {
			monthStr = String.valueOf(month);
		} else {
			monthStr = "0" + String.valueOf(month);
		}
		int day = cal.get(Calendar.DATE);
		String dayStr = "";
		if(day >= 10) {
			dayStr = String.valueOf(day);
		} else {
			dayStr = "0" + String.valueOf(day);
		}

		String dateLate = year +"-"+ monthStr +"-"+ dayStr;

		return dateLate;
	}

	/**
	 * 日付(String型:yyyy-MM-dd)表示を日付(String型:yyyy年MM月dd日)にして返す
	 */
	public static String getDateStr(String date) {
		String date1 = date.replaceFirst("-", "年");
		String date2 = date1.replaceFirst("-", "月");
		String dateStr = date2 +"日";
		return dateStr;
	}

	/**
	 * 日付(String型:yyyy年MM月dd日)表示を日付(String型:yyyy-MM-dd)にして返す
	 */
	public static String getDate(String dateStr) {
		String dateStr1 = dateStr.replaceFirst("年", "-");
		String dateStr2 = dateStr1.replaceFirst("月", "-");
		String date = dateStr2.replaceFirst("日", "");
		return date;
	}
}
