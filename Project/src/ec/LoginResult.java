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

@WebServlet("/LoginResult")
public class LoginResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*文字化け対策*/
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		try {
			// リクエストパラメータの入力項目を取得
			String loginId = request.getParameter("loginId");
			String password = request.getParameter("password");

			// リクエストパラメータの入力項目を引数に渡して、Daoのメソッドを実行
			UserDAO userDao = new UserDAO();
			UserDataBeans userDataBeans = userDao.findByLoginInfo(loginId, password);

			/** テーブルに該当のデータが見つからなかった場合 **/
			if (userDataBeans == null) {
				// リクエストスコープにエラーメッセージをセット
				request.setAttribute("errMsg", "ログインIDまたはパスワードが異なります。");

				// ログインjspにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
				dispatcher.forward(request, response);
				return;
			}

			/** テーブルに該当のデータが見つかった場合 **/
			// セッションにユーザの情報をセット
			session.setAttribute("isLogin", true);
			session.setAttribute("userInfo", userDataBeans);
			//ログイン前のページを取得
			String returnStrUrl = (String) EcHelper.cutSessionAttribute(session, "returnStrUrl");
			//ログイン前ページにリダイレクト。指定がない場合Index
			response.sendRedirect(returnStrUrl!=null?returnStrUrl:"Index");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
