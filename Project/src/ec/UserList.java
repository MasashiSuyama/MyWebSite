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

/**
 * ユーザ一覧(管理者ページ)
 */
@WebServlet("/UserList")
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//1ページに表示するユーザ数
	final static int PAGE_MAX_USER_COUNT = 10;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		try {
			// 管理者としてログインセッションがない場合、ログイン画面にリダイレクトさせる
			UserDataBeans udb =(UserDataBeans)session.getAttribute("userInfo");
			if(udb == null || !(udb.getLoginId().equals("admin")) ) {
				// セッションにリターンページ情報を書き込む
				session.setAttribute("returnStrUrl", "UserList");
				// ログイン画面にリダイレクト
				response.sendRedirect("Login");
				return;
			}

			//表示ページ番号 未指定の場合 1ページ目を表示
			int pageNum = Integer.parseInt(request.getParameter("page_num") == null ? "1" : request.getParameter("page_num"));

			//セッションに検索ワードが入っていたら破棄する
			String searchLoginId = (String)session.getAttribute("searchLoginId");
			if(searchLoginId != null) {
				session.removeAttribute("searchLoginId");
			}
			String searchUserName = (String)session.getAttribute("searchUserName");
			if(searchUserName != null) {
				session.removeAttribute("searchUserName");
			}
			String startBirthdate = (String)session.getAttribute("startBirthdate");
			if(startBirthdate != null) {
				session.removeAttribute("startBirthdate");
			}
			String endBirthdate = (String)session.getAttribute("endBirthdate");
			if(endBirthdate != null) {
				session.removeAttribute("endBirthdate");
			}

			// ユーザーリストを取得 ページ表示分のみ
			UserDAO userDAO = new UserDAO();
			ArrayList<UserDataBeans> searchResultUserList = userDAO.getUsers(pageNum, PAGE_MAX_USER_COUNT);

			// 総ページ数を取得
			Double userAll = UserDAO.getUserAll();
			int pageMax = (int) Math.ceil(userAll / PAGE_MAX_USER_COUNT);

			//表示件数(○○～△△件目)
			request.setAttribute("userPageTop", PAGE_MAX_USER_COUNT * (pageNum - 1) + 1);
			int userPageBottom = 0;
			if(pageNum == pageMax) {
				userPageBottom = (int) Math.floor(userAll);
			} else {
				userPageBottom = pageNum * PAGE_MAX_USER_COUNT;
			}
			request.setAttribute("userPageBottom", userPageBottom);
			//総ユーザー数(管理者除く)
			request.setAttribute("userAll", (int) Math.floor(userAll));
			// 総ページ数
			request.setAttribute("pageMax", pageMax);
			// 表示ページ
			request.setAttribute("pageNum", pageNum);
			// リクエストスコープにユーザ一覧情報をセット
			request.setAttribute("userList", searchResultUserList);

			// ユーザ一覧のjspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
