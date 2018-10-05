package ec;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		// ログイン時に保存したセッション内のユーザ情報を削除
		session.setAttribute("isLogin", false);
		session.removeAttribute("userInfo");

		// リクエストスコープにログアウトメッセージをセット
		request.setAttribute("logoutMsg", "ログアウトしました。");

		// トップページにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("Index");
		dispatcher.forward(request, response);
	}

}
