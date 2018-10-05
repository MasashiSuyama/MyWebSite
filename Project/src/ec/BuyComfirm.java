package ec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ItemDataBeans;

/**
 * 購入確認ページ
 */
@WebServlet("/BuyComfirm")
public class BuyComfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		try {
			//カートを取得
			ArrayList<ItemDataBeans> cart = (ArrayList<ItemDataBeans>) session.getAttribute("cart");

			// URLからGETパラメータとしてID・購入個数・発送到着日時を受け取る
			String[] buyCount_str = request.getParameterValues("buy_count");
			String[] deleteItem_str = request.getParameterValues("delete_item");
			String arrivalDate = request.getParameter("arrival_date");

			//全アイテム削除チェックされていたらアイテムを全削除しカート画面に戻る
			if(deleteItem_str != null && buyCount_str.length == deleteItem_str.length) {
				cart.clear();
				request.setAttribute("cartActionMessage", "カートに商品がありません");

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
				dispatcher.forward(request, response);
			} else {
				//小計金額の配列
				ArrayList<Integer> subtotalMoney = new ArrayList<Integer>();
				int subtotalMoneyArrayNum = 0;
				//合計金額
				int totalMoney = 0;

				//購入個数を変更する。
				for(int i = 0 ; i < cart.size() ; i++) {
					int buyCount = Integer.parseInt(buyCount_str[i]);
					cart.get(i).setBuyCount(buyCount);
					subtotalMoney.add(cart.get(i).getPrice() * buyCount);
					subtotalMoneyArrayNum++;
				}

				//削除にチェックのついてるものをカートから外す
				if(deleteItem_str != null) {
					ArrayList<Integer> deleteItem = new ArrayList<Integer>() ;
					for(int i = 0 ; i < deleteItem_str.length; i++) {
						deleteItem.add(Integer.parseInt(deleteItem_str[i]));
					}
					Collections.reverse(deleteItem);
					for(Integer deleteItemNum :deleteItem) {
						cart.remove(new Integer(deleteItemNum).intValue());
						subtotalMoney.remove(new Integer(deleteItemNum).intValue());
					}
				}

				String deliveryPrice = "";
				//合計金額
				for(int i = 0 ; i < subtotalMoney.size() ; i++) {
					totalMoney += subtotalMoney.get(i);
				}
				if(totalMoney <= 5000) {
					totalMoney += 200;
					deliveryPrice = "200円";
				} else {
					deliveryPrice = "無料";
				}

				//カート情報更新
				session.setAttribute("cart", cart);
				session.setAttribute("arrivalDate", arrivalDate);
				session.setAttribute("subtotalMoney", subtotalMoney);
				session.setAttribute("deliveryPrice", deliveryPrice);
				session.setAttribute("totalMoney", totalMoney);

				// 購入確認画面にフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/buyConfirm.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
