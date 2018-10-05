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
 * 商品マスタ一覧ページ
 */
@WebServlet("/ProductList")
public class ProductList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//1ページに表示する商品数
	final static int PAGE_MAX_PRODUCT_COUNT = 10;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			//表示ページ番号 未指定の場合 1ページ目を表示
			int pageNum = Integer.parseInt(request.getParameter("page_num") == null ? "1" : request.getParameter("page_num"));

			//セッションに検索ワードが入っていたら破棄する
			String searchProductName = (String)session.getAttribute("searchProductName");
			if(searchProductName != null) {
				session.removeAttribute("searchProductName");
			}
			String lowProductCost = (String)session.getAttribute("lowProductCost");
			if(lowProductCost != null) {
				session.removeAttribute("lowProductCost");
			}
			String highProductCost = (String)session.getAttribute("highProductCost");
			if(highProductCost != null) {
				session.removeAttribute("highProductcost");
			}

			// 商品リストを取得 ページ表示分のみ
			ItemDAO itemDao = new ItemDAO();
			ArrayList<ItemDataBeans> searchResultProductList = itemDao.getProducts(pageNum, PAGE_MAX_PRODUCT_COUNT);

			// 総ページ数を取得
			Double productAll = itemDao.getProductAll();
			int pageMax = (int) Math.ceil(productAll / PAGE_MAX_PRODUCT_COUNT);

			//表示件数(○○～△△件目)
			request.setAttribute("productPageTop", PAGE_MAX_PRODUCT_COUNT * (pageNum - 1) + 1);
			int productPageBottom = 0;
			if(pageNum == pageMax) {
				productPageBottom = (int) Math.floor(productAll);
			} else {
				productPageBottom = pageNum * PAGE_MAX_PRODUCT_COUNT;
			}
			request.setAttribute("productPageBottom", productPageBottom);
			//総商品数
			request.setAttribute("productAll", (int) Math.floor(productAll));
			// 総ページ数
			request.setAttribute("pageMax", pageMax);
			// 表示ページ
			request.setAttribute("pageNum", pageNum);
			// リクエストスコープに商品マスタ一覧情報をセット
			request.setAttribute("productList", searchResultProductList);

			// 商品マスタ一覧のjspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productList.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
