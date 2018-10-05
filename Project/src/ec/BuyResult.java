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

			//カートを取得
			ArrayList<ItemDataBeans> cart = (ArrayList<ItemDataBeans>) session.getAttribute("cart");
			String arrivaldate_str =  (String) session.getAttribute("arrivalDate");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


			BuyDataBeans bdb = new BuyDataBeans();
			UserDataBeans udb= (UserDataBeans) session.getAttribute("userInfo");
			bdb.setUserId(udb.getId());
//			bdb.setArrivalDate((Date) session.getAttribute("arrivalDate"));

			bdb.setArrivalDate(sdf.parse(arrivaldate_str));
			bdb.setTotalPrice((int) session.getAttribute("totalMoney"));

			// 購入情報を登録
			int buyId = BuyDAO.insertBuy(bdb);

			// 購入詳細情報を購入情報IDに紐づけして登録
			for (ItemDataBeans cartInItem : cart) {
				BuyDetailDataBeans bddb = new BuyDetailDataBeans();
				bddb.setBuyId(buyId);
				bddb.setItemId(cartInItem.getId());
				BuyDetailDAO.insertBuyDetail(bddb, cartInItem.getBuyCount());
			}

			request.setAttribute("arrivalDate", arrivaldate_str);

			//カート内のセッションを削除する
			cart.clear();

			// 購入確認画面にフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/buyResult.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
