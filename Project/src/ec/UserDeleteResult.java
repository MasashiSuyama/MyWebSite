package ec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserDataBeans;
import dao.UserDAO;

@WebServlet("/UserDeleteResult")
public class UserDeleteResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		try {
			// URLからGETパラメータとしてIDを受け取る
			String id_str = request.getParameter("id");
			int id = Integer.parseInt(id_str);

			// idの一致するユーザまたは管理者としてログインセッションがない場合、ログイン画面にリダイレクトさせる
			UserDataBeans loginUdb =(UserDataBeans)session.getAttribute("userInfo");
			if(loginUdb == null || !(loginUdb.getLoginId().equals("admin") || loginUdb.getId() == id) ) {
				// セッションにリターンページ情報を書き込む
				session.setAttribute("returnStrUrl", "UserDeleteResult");
				// ログイン画面にリダイレクト
				response.sendRedirect("Login");
				return;
			}

			//idを引数にして、idに紐づくユーザ情報を削除する
			UserDAO userDao = new UserDAO();
			userDao.deleteUser(id);

			// 管理者としてログイン	してた場合
			if(loginUdb.getLoginId().equals("admin")) {
				// ユーザ一覧のサーブレットにリダイレクト
				response.sendRedirect("UserList");
				return;
			}

			// ログイン時に保存したセッション内のユーザ情報を削除
			session.setAttribute("isLogin", false);
			session.removeAttribute("userInfo");

			// トップページのサーブレットにリダイレクト
			response.sendRedirect("Index");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
