package ec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ItemDAO;

@WebServlet("/ProductDeleteResult")
public class ProductDeleteResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		try {
			// URLからGETパラメータとしてIDを受け取る
			String id_str = request.getParameter("id");
			int id = Integer.parseInt(id_str);

			//idを引数にして、idに紐づく商品情報を削除する
			ItemDAO itemDao = new ItemDAO();
			itemDao.deleteItem(id);

			// 商品マスタ一覧のサーブレットにリダイレクト
			response.sendRedirect("ProductList");

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
