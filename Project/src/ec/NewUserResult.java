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
import dao.UserDAO;

@WebServlet("/NewUserResult")
public class NewUserResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		try {
			// リクエストパラメータの入力項目を取得
			String loginId = request.getParameter("loginId");
			String password = request.getParameter("password");
			String passwordCheck = request.getParameter("passwordCheck");
			String userName = request.getParameter("name");
			String birthday = request.getParameter("birthdate");

			// loginIdが既に登録されているか(false:登録済み)
			UserDAO userDAO = new UserDAO();
			boolean loginIdCheck = userDAO.newUser(loginId);

			/** 登録できない場合 **/
			if (loginId.equals("") || password.equals("") || passwordCheck.equals("") || userName.equals("") || birthday.equals("")) {
				// リクエストスコープにエラーメッセージをセット
				request.setAttribute("newUserErrMsg", "入力されていない項目があります");

				// リクエストスコープに入力内容をセット
				request.setAttribute("loginId", loginId);
				request.setAttribute("userName", userName);
				request.setAttribute("birthday", birthday);

				// ユーザ新規登録jspにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newUser.jsp");
				dispatcher.forward(request, response);
				return;
			}
			if ((loginIdCheck == false) ) {
				// リクエストスコープにエラーメッセージをセット
				request.setAttribute("newUserErrMsg", "そのログインIDは使われています");

				// リクエストスコープに入力内容をセット
				request.setAttribute("loginId", loginId);
				request.setAttribute("userName", userName);
				request.setAttribute("birthday", birthday);

				// ユーザ新規登録jspにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newUser.jsp");
				dispatcher.forward(request, response);
				return;
			}
			if ( !(password.equals(passwordCheck)) ) {
				// リクエストスコープにエラーメッセージをセット
				request.setAttribute("newUserErrMsg", "パスワードが正しくありません。");

				// リクエストスコープに入力内容をセット
				request.setAttribute("loginId", loginId);
				request.setAttribute("userName", userName);
				request.setAttribute("birthday", birthday);

				// ユーザ新規登録jspにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newUser.jsp");
				dispatcher.forward(request, response);
				return;
			}

			/** 登録できる場合 **/
			// ユーザ情報を登録
			userDAO.newUser(loginId, userName, birthday, password);

			// セッションにユーザの情報をセット
			session.setAttribute("isLogin", true);
			UserDataBeans userDataBeans = userDAO.findByLoginInfo(loginId, password);
			session.setAttribute("userInfo", userDataBeans);

			//トップページにリダイレクト
			response.sendRedirect("Index");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}