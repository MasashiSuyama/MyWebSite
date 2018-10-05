package ec;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserDataBeans;
import dao.UserDAO;

@WebServlet("/UserSearchResult")
public class UserSearchResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//1ページに表示するユーザ数
	final static int PAGE_MAX_USER_COUNT = 10;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		try {
			// 管理者としてログインセッションがない場合、ログイン画面にリダイレクトさせる
			UserDataBeans udb =(UserDataBeans)session.getAttribute("userInfo");
			if(udb == null || !( udb.getLoginId().equals("admin") )) {
				// セッションにリターンページ情報を書き込む
				session.setAttribute("returnStrUrl", "UserList");
				// ログイン画面にリダイレクト
				response.sendRedirect("Login");
				return;
			}

			//表示ページ番号 未指定の場合 1ページ目を表示
			int pageNum = Integer.parseInt(request.getParameter("page_num") == null ? "1" : request.getParameter("page_num"));

			//リクエストパラメータの入力項目を取得
			String searchLoginId = request.getParameter("search_loginId") == null ? "" : request.getParameter("search_loginId");
			String searchUserName = request.getParameter("search_userName")  == null ? "" : request.getParameter("search_userName");
			String startBirthdate = request.getParameter("start_Birthdate")  == null ? "" : request.getParameter("start_Birthdate");
			String endBirthdate = request.getParameter("end_Birthdate")  == null ? "" : request.getParameter("end_Birthdate");

			// 新たに検索されたキーワードをセッションに格納する
			session.setAttribute("searchLoginId", searchLoginId);
			session.setAttribute("searchUserName", searchUserName);
			session.setAttribute("startBirthdate", startBirthdate);
			session.setAttribute("endBirthdate", endBirthdate);

			// ユーザリストを取得 ページ表示分のみ
			UserDAO userDAO = new UserDAO();
			ArrayList<UserDataBeans> searchResultUsersList =
					userDAO.getUserBySearch(searchLoginId, searchUserName, startBirthdate, endBirthdate, pageNum, PAGE_MAX_USER_COUNT);

			// 検索に対しての総ページ数を取得
			double userCount = UserDAO.getUserCount(searchLoginId, searchUserName, startBirthdate, endBirthdate);
			int pageMax = (int) Math.ceil(userCount / PAGE_MAX_USER_COUNT);

			// 表示件数(○○～△△件目)
			int userPageTop = PAGE_MAX_USER_COUNT * (pageNum - 1) + 1;
				if(userCount == 0) {
					userPageTop = 0;
				}
			request.setAttribute("userPageTop", userPageTop);
			int userPageBottom = 0;
			if(pageNum == pageMax) {
				userPageBottom = (int) Math.floor(userCount);
			} else if (userCount == 0)  {
					userPageBottom = 0;
			}else {
				userPageBottom = pageNum * PAGE_MAX_USER_COUNT;
			}
			request.setAttribute("userPageBottom", userPageBottom);
			// 検索に対しての総ユーザー数(管理者除く)
			request.setAttribute("userCount", (int) Math.floor(userCount));
			// 検索に対しての総ページ数
			request.setAttribute("pageMax", pageMax);
			// 検索に対しての表示ページ
			request.setAttribute("pageNum", pageNum);
			// リクエストスコープにユーザ一覧情報をセット
			request.setAttribute("userList", searchResultUsersList);
			// ユーザ一覧のjspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userSearchResult.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
