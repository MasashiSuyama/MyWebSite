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


@WebServlet("/ItemAdd")
public class ItemAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		try {
			// URLからGETパラメータとしてID・購入個数を受け取る
			String id_str = request.getParameter("id");
			String buyCount_str = request.getParameter("buy_count");
			int id = Integer.parseInt(id_str);
			int buyCount = Integer.parseInt(buyCount_str);

			//対象のアイテム情報を取得
			ItemDAO itemDAO = new ItemDAO();
			ItemDataBeans item = itemDAO.findItemInfo(id);

			//カートを取得
			ArrayList<ItemDataBeans> cart = (ArrayList<ItemDataBeans>) session.getAttribute("cart");

			//セッションにカートがない場合カートを作成
			if (cart == null) {
				cart = new ArrayList<ItemDataBeans>();
			}

			//購入個数情報を追加し、カートに商品を追加。
			item.setBuyCount(buyCount);
			cart.add(item);

			//カート情報更新
			session.setAttribute("cart", cart);
			request.setAttribute("itemIntoCartMessage", "商品を追加しました");

			// 買い物かごにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("Cart");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
