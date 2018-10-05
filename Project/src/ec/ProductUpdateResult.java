package ec;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ItemDAO;

@WebServlet("/ProductUpdateResult")
public class ProductUpdateResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		try {
			// URLからGETパラメータとしてIDを受け取る
			String id_str = request.getParameter("id");
			int id =Integer.parseInt(id_str);

			// リクエストパラメータの入力項目を取得
			String name = request.getParameter("name");
			String detail = request.getParameter("detail");
			String photo = request.getParameter("photo");
			boolean eggAllergy = request.getParameter("eggAllergy") == null ? false : request.getParameter("eggAllergy").equals("egg");
			boolean wheatAllergy = request.getParameter("wheatAllergy") == null ? false : request.getParameter("wheatAllergy").equals("wheat");
			boolean milkAllergy = request.getParameter("milkAllergy") == null ? false : request.getParameter("milkAllergy").equals("milk");
			String allergyOther = request.getParameter("allergyOther");
			int price = Integer.parseInt(request.getParameter("price"));
			int stock = Integer.parseInt(request.getParameter("stock"));
			int sellAddNum = Integer.parseInt(request.getParameter("sellAddNum"));

			/** 登録できない場合 **/
			if (name.equals("") ) {
				// リクエストスコープにエラーメッセージをセット
				request.setAttribute("productUpdateErrMsg", "入力されていない項目があります");

				// 商品マスタ更新のjspにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("ProductUpdate");
				dispatcher.forward(request, response);
				return;
			}

			/** 登録できる場合 **/
			//idに紐づく商品情報を更新する
			ItemDAO itemDao = new ItemDAO();
			itemDao.productUpdate(id, name, detail, photo, eggAllergy, wheatAllergy, milkAllergy, allergyOther, price, stock, sellAddNum);

			// 商品マスタ一覧のサーブレットにリダイレクト
			response.sendRedirect("ProductList");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
