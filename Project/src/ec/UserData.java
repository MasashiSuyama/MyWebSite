package ec;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BuyDataBeans;
import beans.UserDataBeans;
import dao.BuyDAO;
import dao.UserDAO;

/**
 * ユーザー詳細画面
 */
@WebServlet("/UserData")
public class UserData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		try {
			// URLからGETパラメータとしてIDを受け取る
			String id_str = request.getParameter("id");
			int id = Integer.parseInt(id_str);

			//idを引数にして、idに紐づくユーザ情報を出力する
			UserDAO userDao = new UserDAO();
			UserDataBeans udb = userDao.findUserInfo(id);

			//ログインユーザの購入履歴情報を取得する
			BuyDAO buyDao = new BuyDAO();
			ArrayList<BuyDataBeans> bdbList = null;
			try {
				bdbList = buyDao.findBuyDataAll(udb.getId());
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

			//ユーザ情報をリクエストスコープにセット
			request.setAttribute("userData", udb);
			request.setAttribute("bdbList", bdbList);

			// ユーザ詳細のjspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
