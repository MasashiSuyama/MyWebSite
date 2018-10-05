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

import beans.ItemDataBeans;
import dao.ItemDAO;

/**
 * 商品検索結果ページ
 */
@WebServlet("/ItemSearchResult")
public class ItemSearchResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//1ページに表示する商品数
	final static int PAGE_MAX_ITEM_COUNT = 9;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");

		try {
			//表示ページ番号 未指定の場合 1ページ目を表示
			int pageNum = Integer.parseInt(request.getParameter("page_num") == null ? "1" : request.getParameter("page_num"));

			HttpSession session = request.getSession();
			//リクエストパラメータの入力項目を取得
			String searchItemName = request.getParameter("search_itemName") == null ? "" : request.getParameter("search_itemName");
			String lowItemPrice = request.getParameter("low_itemPrice")  == null ? "" : request.getParameter("low_itemPrice");
			String highItemPrice = request.getParameter("high_itemPrice")  == null ? "" : request.getParameter("high_itemPrice");

			// 新たに検索されたキーワードをセッションに格納する
			session.setAttribute("searchItemName", searchItemName);
			session.setAttribute("lowItemPrice", lowItemPrice);
			session.setAttribute("highItemPrice", highItemPrice);

			// 商品リストを取得 ページ表示分のみ
			ItemDAO itemDAO = new ItemDAO();
			ArrayList<ItemDataBeans> searchResultItemsList =
					itemDAO.getItemBySearch(searchItemName, lowItemPrice, highItemPrice, pageNum, PAGE_MAX_ITEM_COUNT);
			// 検索に対しての総ページ数を取得
			double itemCount = itemDAO.getItemCount(searchItemName, lowItemPrice, highItemPrice);
			int pageMax = (int) Math.ceil(itemCount / PAGE_MAX_ITEM_COUNT);

			// 表示件数(○○～△△件目)
			int itemPageTop = PAGE_MAX_ITEM_COUNT * (pageNum - 1) + 1;
				if(itemCount == 0) {
					itemPageTop = 0;
				}
			request.setAttribute("itemPageTop", itemPageTop);
			int itemPageBottom = 0;
			if(pageNum == pageMax) {
				itemPageBottom = (int) Math.floor(itemCount);
			} else if (itemCount == 0)  {
					itemPageBottom = 0;
			}else {
				itemPageBottom = pageNum * PAGE_MAX_ITEM_COUNT;
			}
			request.setAttribute("itemPageBottom", itemPageBottom);
			// 検索に対しての総商品数
			request.setAttribute("itemCount", (int) Math.floor(itemCount));
			// 検索に対しての総ページ数
			request.setAttribute("pageMax", pageMax);
			// 検索に対しての表示ページ
			request.setAttribute("pageNum", pageNum);
			// リクエストスコープに商品一覧情報をセット
			request.setAttribute("itemList", searchResultItemsList);

			// 商品検索結果のjspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/itemSearchResult.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}
