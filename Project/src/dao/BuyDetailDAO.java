package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import base.DBManager;
import beans.BuyDetailDataBeans;
import beans.ItemDataBeans;

public class BuyDetailDAO {

	/**
     * 購入IDによる購入詳細情報検索
     */
	public static ArrayList<ItemDataBeans> getItemDataByBuyId(int buyId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement(
					"SELECT item.id, item.name, item.price, buy_detail.buy_count"
					+ " FROM buy_detail JOIN item ON buy_detail.item_id = item.id"
					+ " WHERE buy_detail.buy_id = ?");
			st.setInt(1, buyId);

			ResultSet rs = st.executeQuery();
			ArrayList<ItemDataBeans> buyDetailItemList = new ArrayList<ItemDataBeans>();

			while (rs.next()) {
				ItemDataBeans idb = new ItemDataBeans();
				idb.setId(rs.getInt("id"));
				idb.setName(rs.getString("name"));
				idb.setPrice(rs.getInt("price"));
				idb.setBuyCount(rs.getInt("buy_count"));
				buyDetailItemList.add(idb);
			}

			return buyDetailItemList;
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}


	/**
	 * 購入詳細登録処理
	 */
	public static void insertBuyDetail(BuyDetailDataBeans bddb, int buyCount) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			// INSERT文を準備
			st = con.prepareStatement("INSERT INTO buy_detail(buy_id,item_id,buy_count) VALUES(?,?,?)");

			// INSERTを実行
			st.setInt(1, bddb.getBuyId());
			st.setInt(2, bddb.getItemId());
			st.setInt(3, buyCount);
			st.executeUpdate();

		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

}
