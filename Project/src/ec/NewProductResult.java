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

@WebServlet("/NewProductResult")
public class NewProductResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		try {
			// リクエストパラメータの入力項目を取得
			String name = request.getParameter("name");
			String detail = request.getParameter("detail");
			String photo = request.getParameter("photo");
			boolean eggAllergy = request.getParameter("eggAllergy") == null ? false : request.getParameter("eggAllergy").equals("egg");
			boolean wheatAllergy = request.getParameter("wheatAllergy") == null ? false : request.getParameter("wheatAllergy").equals("wheat");
			boolean milkAllergy = request.getParameter("milkAllergy") == null ? false : request.getParameter("milkAllergy").equals("milk");
			String allergyOther = request.getParameter("allergyOther")  == "" ? "なし" : request.getParameter("allergyOther");
			String priceStr = request.getParameter("price");
			String stockStr = request.getParameter("stock");

			// 同名の商品名が既に登録されているか(false:登録済み)
			ItemDAO itemDAO = new ItemDAO();
			boolean productNameCheck = itemDAO.newProduct(name);

			/** 登録できない場合 **/
			boolean newProductErr = false ;
			if ( name.equals("") || priceStr.equals("") || stockStr.equals("") ) {
				// リクエストスコープにエラーメッセージをセット
				request.setAttribute("newProductErrMsg", "入力されていない項目があります");
				newProductErr = true;

			} else if(productNameCheck == false) {
				// リクエストスコープにエラーメッセージをセット
				request.setAttribute("newProductErrMsg", "既に同じ商品名で登録されています");
				newProductErr = true;
			}

			if(newProductErr == true) {
				// リクエストスコープに入力内容をセット
				request.setAttribute("name", name);
				request.setAttribute("detail", detail);
				request.setAttribute("eggAllergy", eggAllergy);
				request.setAttribute("wheatAllergy", wheatAllergy);
				request.setAttribute("milkAllergy", milkAllergy);
				request.setAttribute("allergyOther", allergyOther);
				request.setAttribute("price", priceStr);
				request.setAttribute("stock", stockStr);

				// 商品マスタ新規登録jspにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newProduct.jsp");
				dispatcher.forward(request, response);
				return;
			}

			/** 登録できる場合 **/
			int price = Integer.parseInt(priceStr);
			int stock = Integer.parseInt(stockStr);

			//写真が登録されてない場合
			if(photo.equals("")) {
				photo = "null.png";
			}

			// ユーザ情報を登録
			ItemDAO itemDao = new ItemDAO();
			itemDao.newProduct(name, detail , photo, eggAllergy, wheatAllergy, milkAllergy, allergyOther, price, stock);

			//商品マスタ一覧ページにリダイレクト
			response.sendRedirect("ProductList");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
