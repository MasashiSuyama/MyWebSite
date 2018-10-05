package ec;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ItemDataBeans;
import dao.ItemDAO;

/**
 * ホーム画面
 */
@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {

			//人気商品・おすすめ商品の情報を取得
			ArrayList<ItemDataBeans>PopularItemList = ItemDAO.getPopularItem(3);
			ArrayList<ItemDataBeans>RandItemList = ItemDAO.getRandItem(3);

			//リクエストスコープにセット
			request.setAttribute("popularItemList", PopularItemList);
			request.setAttribute("randItemList", RandItemList);

			//セッションにsearchWordが入っていたら破棄する
			String searchWord = (String)session.getAttribute("searchWord");
			if(searchWord != null) {
				session.removeAttribute("searchWord");
			}

			request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
