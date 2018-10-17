package ec;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserDataBeans;

/**
 * 管理者トップページ
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		// 管理者としてログインセッションがない場合、ログイン画面にリダイレクトさせる
		UserDataBeans udb =(UserDataBeans)session.getAttribute("userInfo");
		if(udb == null || !(udb.getLoginId().equals("admin")) ) {
			// ログイン画面にリダイレクト
			response.sendRedirect("Login");
			return;
		}

		// 管理者トップページにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/administrationTop.jsp");
		dispatcher.forward(request,response);
	}

}
