package ec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

			//idを引数にして、idに紐づくユーザ情報を削除する
			UserDAO userDao = new UserDAO();
			userDao.deleteUser(id);

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
