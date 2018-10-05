package ec;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserDataBeans;
import dao.UserDAO;

@WebServlet("/UserUpdateResult")
public class UserUpdateResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		try {
			// URLからGETパラメータとしてIDを受け取る
			String id_str = request.getParameter("id");
			int id = Integer.parseInt(id_str);


			// idの一致するユーザまたは管理者としてログインセッションがない場合、ログイン画面にリダイレクトさせる
			UserDataBeans loginUdb =(UserDataBeans)session.getAttribute("userInfo");
			if(loginUdb == null || !(loginUdb.getLoginId().equals("admin") || loginUdb.getId() == id) ) {
				// セッションにリターンページ情報を書き込む
				session.setAttribute("returnStrUrl", "UserUpdateResult");
				// ログイン画面にリダイレクト
				response.sendRedirect("Login");
				return;
			}

			// リクエストパラメータの入力項目を取得
			String userName = request.getParameter("userName");
			String birthdate = request.getParameter("birthdate");
			String password = request.getParameter("password");
			String passwordCheck = request.getParameter("passwordCheck");

			/** 登録できない場合 **/
			if (password.equals("") || passwordCheck.equals("") || userName.equals("") || birthdate.equals("")) {
				// リクエストスコープにエラーメッセージをセット
				request.setAttribute("UserUpdateErrMsg", "入力されていない項目があります");

				//idを引数にして、idに紐づくユーザ情報を出力する
				UserDAO userDao = new UserDAO();
				UserDataBeans udb = userDao.findUserInfo(id);

				//入力されているユーザ情報を保持する
				udb.setName(userName);
				Date date = Date.valueOf(birthdate);
				udb.setBirthDate(date);

				//ユーザ情報をリクエストスコープにセット
				request.setAttribute("userUpdate", udb);

				// ユーザ新規登録jspにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
				dispatcher.forward(request, response);
				return;
			}
			if ( !(password.equals(passwordCheck)) ) {
				// リクエストスコープにエラーメッセージをセット
				request.setAttribute("UserUpdateErrMsg", "パスワードが正しくありません。");

				//idを引数にして、idに紐づくユーザ情報を出力する
				UserDAO userDao = new UserDAO();
				UserDataBeans udb = userDao.findUserInfo(id);

				//入力されているユーザ情報を保持する
				udb.setName(userName);
				Date date = Date.valueOf(birthdate);
				udb.setBirthDate(date);

				//ユーザ情報をリクエストスコープにセット

				// ユーザ新規登録jspにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
				dispatcher.forward(request, response);
				return;
			}
			/** 登録できる場合 **/
			//idに紐づくユーザ情報を更新する
			UserDAO userDao = new UserDAO();
			userDao.UserUpdate(id, userName, birthdate, password);

			// 管理者としてログイン	してた場合
			if(loginUdb.getLoginId().equals("admin")) {
				// ユーザ一覧のサーブレットにリダイレクト
				response.sendRedirect("UserList");
				return;
			}

			//idを引数にして、idに紐づくユーザ情報を出力する
			UserDataBeans udb = userDao.findUserInfo(id);

			//ユーザ情報をリクエストスコープにセット
			request.setAttribute("userData", udb);

			//ユーザ情報ページへフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user.jsp");
			dispatcher.forward(request, response);

		}  catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
