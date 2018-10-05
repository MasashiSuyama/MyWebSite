package ec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ユーザ新規登録画面
 */
@WebServlet("/NewUser")
public class NewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		// 今日の日付の取得
		String date = EcHelper.getDate();

		// セッションに今日の日付をセット
		session.setAttribute("today", date);
		// ユーザ新規登録jspにフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/newUser.jsp").forward(request, response);
	}

}
