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

@WebServlet("/ProductSearchResult")
public class ProductSearchResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//1ページに表示する商品マスタ数
	final static int PAGE_MAX_PRODUCT_COUNT = 10;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");

		try {
			//表示ページ番号 未指定の場合 1ページ目を表示
			int pageNum = Integer.parseInt(request.getParameter("page_num") == null ? "1" : request.getParameter("page_num"));

			HttpSession session = request.getSession();
			//リクエストパラメータの入力項目を取得
			String searchProductName = request.getParameter("search_productName") == null ? "" : request.getParameter("search_productName");
			String lowProductCost = request.getParameter("low_productCost")  == null ? "" : request.getParameter("low_productCost");
			String highProductCost = request.getParameter("high_productCost")  == null ? "" : request.getParameter("high_productCost");

			// 新たに検索されたキーワードをセッションに格納する
			session.setAttribute("searchProductName", searchProductName);
			session.setAttribute("lowProductCost", lowProductCost);
			session.setAttribute("highProductCost", highProductCost);

			// 商品マスタリストを取得 ページ表示分のみ
			ItemDAO itemDAO = new ItemDAO();
			ArrayList<ItemDataBeans> searchResultProductsList =
					itemDAO.getItemBySearch(searchProductName, lowProductCost, highProductCost, pageNum, PAGE_MAX_PRODUCT_COUNT);

			// 検索に対しての総ページ数を取得
			double productCount = itemDAO.getItemCount(searchProductName, lowProductCost, highProductCost);
			int pageMax = (int) Math.ceil(productCount / PAGE_MAX_PRODUCT_COUNT);

			// 表示件数(○○～△△件目)
			int productPageTop = PAGE_MAX_PRODUCT_COUNT * (pageNum - 1) + 1;
				if(productCount == 0) {
					productPageTop = 0;
				}
			request.setAttribute("productPageTop", productPageTop);
			int productPageBottom = 0;
			if(pageNum == pageMax) {
				productPageBottom = (int) Math.floor(productCount);
			} else if (productCount == 0)  {
					productPageBottom = 0;
			}else {
				productPageBottom = pageNum * PAGE_MAX_PRODUCT_COUNT;
			}
			request.setAttribute("productPageBottom", productPageBottom);
			// 検索に対しての総商品数
			request.setAttribute("productCount", (int) Math.floor(productCount));
			// 検索に対しての総ページ数
			request.setAttribute("pageMax", pageMax);
			// 検索に対しての表示ページ
			request.setAttribute("pageNum", pageNum);
			// リクエストスコープに商品マスタ一覧情報をセット
			request.setAttribute("productList", searchResultProductsList);

			// 商品マスタ検索結果のjspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productSearchResult.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
