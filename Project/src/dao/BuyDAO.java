package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import base.DBManager;
import beans.BuyDataBeans;

public class BuyDAO {

	/**
     * ユーザーidが一致する全ての購入履歴情報を取得する
     */
	public ArrayList<BuyDataBeans> findBuyData(int userId, int pageNum, int pageMaxBuyDetailCount) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			int startBuyDetailNum = (pageNum - 1) * pageMaxBuyDetailCount;
			con = DBManager.getConnection();

			st = con.prepareStatement(
					"SELECT * FROM buy WHERE user_id = ? ORDER BY id DESC LIMIT ?,?");
			st.setInt(1, userId);
			st.setInt(2, startBuyDetailNum);
			st.setInt(3, pageMaxBuyDetailCount);

			ResultSet rs = st.executeQuery();

			ArrayList<BuyDataBeans> bdbList = new ArrayList<BuyDataBeans>();

			while (rs.next()) {
				BuyDataBeans bdb = new BuyDataBeans();
				bdb.setId(rs.getInt("id"));
				bdb.setUserId(rs.getInt("user_id"));
				bdb.setTotalPrice(rs.getInt("total_price"));
				bdb.setBuyDate(rs.getTimestamp("create_date"));
				bdb.setArrivalDate(rs.getDate("arrival_date"));
				bdbList.add(bdb);
			}
			return bdbList;
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
	 * ユーザidが一致する全購入履歴数を取得
	 */
	public static double getBuyAll(int userId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement("select count(*) as cnt from buy WHERE user_id = ?");
			st.setInt(1, userId);
			ResultSet rs = st.executeQuery();

			double buyAll = 0;
			while (rs.next()) {
				buyAll = Double.parseDouble(rs.getString("cnt"));
			}
			return buyAll;
		} catch (Exception e) {
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
	 * 購入IDによる購入情報検索
	 */
	public static BuyDataBeans getBuyDetailByBuyId(int buyId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement(
					"SELECT * FROM buy WHERE buy.id = ?");
			st.setInt(1, buyId);

			ResultSet rs = st.executeQuery();

			BuyDataBeans bdb = new BuyDataBeans();
			if (rs.next()) {
				bdb.setId(rs.getInt("id"));
				bdb.setUserId(rs.getInt("user_id"));
				bdb.setTotalPrice(rs.getInt("total_price"));
				bdb.setArrivalDate(rs.getDate("arrival_date"));
				bdb.setBuyDate(rs.getTimestamp("create_date"));

			}
			return bdb;
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
	 * 購入情報登録処理
	 */
	public static int insertBuy(BuyDataBeans bdb) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		int autoKeyId = -1;
		try {
			con = DBManager.getConnection();

			// INSERT文を準備
			st = con.prepareStatement("INSERT INTO buy(user_id,total_price,arrival_date,create_date) VALUES(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);

			// INSERTを実行
			st.setInt(1, bdb.getUserId());
			st.setInt(2, bdb.getTotalPrice());
			st.setDate(3, new java.sql.Date(bdb.getArrivalDate().getTime()));
			st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			st.executeUpdate();

			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				autoKeyId = rs.getInt(1);
			}
			return autoKeyId;
        } catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}
}
