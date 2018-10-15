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
	//1ページに表示する購入履歴数
	final static int PAGE_MAX_BUY_DETAIL_COUNT = 10;

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
				session.setAttribute("returnStrUrl", "UserData");
				// ログイン画面にリダイレクト
				response.sendRedirect("Login");
				return;
			}

			//idを引数にして、idに紐づくユーザ情報を出力する
			UserDAO userDao = new UserDAO();
			UserDataBeans udb = userDao.findUserInfo(id);

			//表示ページ番号 未指定の場合 1ページ目を表示
			int pageNum = Integer.parseInt(request.getParameter("page_num") == null ? "1" : request.getParameter("page_num"));

			//ログインユーザの購入履歴情報を取得する(ページ表示分のみ)
			BuyDAO buyDao = new BuyDAO();
			ArrayList<BuyDataBeans> bdbList = null;
			try {
				bdbList = buyDao.findBuyData(udb.getId(), pageNum, PAGE_MAX_BUY_DETAIL_COUNT);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

			// 総ページ数を取得
			Double buyAll = BuyDAO.getBuyAll(udb.getId());
			int pageMax = (int) Math.ceil(buyAll /  PAGE_MAX_BUY_DETAIL_COUNT);

			//総購入履歴数
			request.setAttribute("buyAll", (int) Math.floor(buyAll));
			// 総ページ数
			request.setAttribute("pageMax", pageMax);
			// 表示ページ
			request.setAttribute("pageNum", pageNum);

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
