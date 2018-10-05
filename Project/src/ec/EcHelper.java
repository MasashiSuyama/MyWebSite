package ec;

import java.text.SimpleDateFormat;
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

}
