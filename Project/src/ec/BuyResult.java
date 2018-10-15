package ec;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BuyDataBeans;
import beans.BuyDetailDataBeans;
import beans.ItemDataBeans;
import beans.UserDataBeans;
import dao.BuyDAO;
import dao.BuyDetailDAO;
import dao.ItemDAO;

/**
 * 購入完了画面
 */
@WebServlet("/BuyResult")
public class BuyResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		try {
			// ログインセッションがない場合、エラー画面にリダイレクトさせる
			Boolean login =(Boolean) session.getAttribute("isLogin");
			if(!(login == true) ) {
				// ログイン画面にリダイレクト
				response.sendRedirect("Error");
				return;
			}

			//カートを取得
			ArrayList<ItemDataBeans> cart = (ArrayList<ItemDataBeans>) session.getAttribute("cart");
			String arrivalDate_str =  (String) session.getAttribute("arrivalDate");
			String arrivalDate = EcHelper.getDate(arrivalDate_str);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Boolean cartItemLack = false; //true:商品不足がある
			for(int i = cart.size() - 1; i >= 0 ; i--) {
				ItemDataBeans idb = ItemDAO.findItemInfo(cart.get(i).getId());

				if(idb.getStock() <= 0) {
					cart.remove(i);
					cartItemLack = true;
				} else if(idb.getStock() < cart.get(i).getBuyCount()) {
					cart.get(i).setBuyCount(cart.get(i).getStock());
					cartItemLack = true;
				}
			}
			if(cartItemLack) {
				request.setAttribute("buyErrMessage", "在庫が不足しました。在庫不足分は現在購入可能個数で表示しています。");
				// カートのサーブレットにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("Cart");
				dispatcher.forward(request, response);
				return;
			};

			BuyDataBeans bdb = new BuyDataBeans();
			UserDataBeans udb= (UserDataBeans) session.getAttribute("userInfo");
			bdb.setUserId(udb.getId());
			bdb.setArrivalDate(sdf.parse(arrivalDate));
			bdb.setTotalPrice((int) session.getAttribute("totalMoney"));

			// 購入情報を登録
			int buyId = BuyDAO.insertBuy(bdb);

			// 購入詳細情報を購入情報IDに紐づけして登録、また商品IDから在庫・売上個数を変更する
			for (ItemDataBeans cartInItem : cart) {
				BuyDetailDataBeans bddb = new BuyDetailDataBeans();
				bddb.setBuyId(buyId);
				bddb.setItemId(cartInItem.getId());
				BuyDetailDAO.insertBuyDetail(bddb, cartInItem.getBuyCount());

				ItemDAO itemDAO = new ItemDAO();
				itemDAO.productCountUpdate(cartInItem.getId(), cartInItem.getBuyCount());
			}

			//カート内のセッションを削除する
			cart.clear();

			request.setAttribute("arrivalDate", EcHelper.getDateStr(arrivalDate));


			// 購入確認画面にフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/buyResult.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
