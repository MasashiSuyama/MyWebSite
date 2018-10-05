package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import base.DBManager;
import beans.ItemDataBeans;

public class ItemDAO {
	/**
     * idが一致する商品情報を削除する
     */
    public void deleteItem(int id) throws SQLException {
        Connection con = null;
        try {
            // データベースへ接続
            con = DBManager.getConnection();

            // SELECT文を準備
            String sql = "DELETE FROM item WHERE id = ?";

             // SELECTを実行し、ユーザ情報を削除
            PreparedStatement pStmt = con.prepareStatement(sql);
            pStmt.setInt(1, id);
            pStmt.executeUpdate();

        } catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
    }

	/**
	 * 商品検索
	 */
	public ArrayList<ItemDataBeans> getItemBySearch
		(String searchItemName, String lowItemCost, String highItemCost, int pageNum, int pageMaxItemCount) throws SQLException {

		Connection con = null;
		try {
			int startItemNum = (pageNum - 1) * pageMaxItemCount;
			con = DBManager.getConnection();

			// SELECT文を準備
            String sql = "SELECT * FROM item where name like '%" + searchItemName + "%'";
            if(!lowItemCost.equals("")) {
            	sql += " and price >= " + lowItemCost;
            }
            if(!highItemCost.equals("")) {
            	sql += " and price <= " + highItemCost;
            }

            sql += " ORDER BY id ASC LIMIT "+ startItemNum + "," + pageMaxItemCount;

            // SELECTを実行し、結果表を取得
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

			ArrayList<ItemDataBeans> itemList = new ArrayList<ItemDataBeans>();

			while (rs.next()) {
				ItemDataBeans idb = new ItemDataBeans();
				idb.setId(rs.getInt("id"));
				idb.setName(rs.getString("name"));
				idb.setPrice(rs.getInt("price"));
				idb.setStock(rs.getInt("stock"));
				idb.setFileName(rs.getString("file_name"));
				itemList.add(idb);
			}
			return itemList;
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
	 * 検索から商品総数を取得
	 *
	 */
	public static double getItemCount(String searchItemName, String lowItemCost, String highItemCost) throws SQLException {
		Connection con = null;
		try {
			con = DBManager.getConnection();

			// SELECT文を準備
            String sql = "select count(*) as cnt from item where name like '%" + searchItemName + "%'";
            if(!lowItemCost.equals("")) {
            	sql += " and price >= '" + lowItemCost + "'";
            }
            if(!highItemCost.equals("")) {
            	sql += " and price <= '" + highItemCost + "'";
            }

            // SELECTを実行し、結果表を取得
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

			double itemCount = 0;
			while (rs.next()) {
				itemCount = Double.parseDouble(rs.getString("cnt"));
			}
			return itemCount;
		} catch (Exception e) {
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
	 * 販売個数順で引数指定分のItemDataBeansを取得
	 */
	public static ArrayList<ItemDataBeans> getPopularItem(int limit) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement("SELECT * FROM item ORDER BY buy_sum DESC,id DESC LIMIT ? ");
			st.setInt(1, limit);

			ResultSet rs = st.executeQuery();

			ArrayList<ItemDataBeans> itemList = new ArrayList<ItemDataBeans>();

			while (rs.next()) {
				ItemDataBeans item = new ItemDataBeans();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getInt("price"));
				item.setFileName(rs.getString("file_name"));
				itemList.add(item);
			}
			return itemList;
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	 /**
	 * 商品マスタ総数を取得
	 */
	public static double getProductAll() throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement("select count(*) as cnt from item");
			ResultSet rs = st.executeQuery();
			double productAll = 0;
			while (rs.next()) {
				productAll = Double.parseDouble(rs.getString("cnt"));
			}
			return productAll;
		} catch (Exception e) {
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
     * 商品マスタ情報をページ表示分、取得する
     */
	public ArrayList<ItemDataBeans> getProducts(int pageNum, int pageMaxUserCount) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			int startUserNum = (pageNum - 1) * pageMaxUserCount;
			con = DBManager.getConnection();

				st = con.prepareStatement("SELECT * FROM item ORDER BY id ASC LIMIT ?,?");
				st.setInt(1, startUserNum);
				st.setInt(2, pageMaxUserCount);

			ResultSet rs = st.executeQuery();
			ArrayList<ItemDataBeans> productList = new ArrayList<ItemDataBeans>();

			while (rs.next()) {
				ItemDataBeans idb = new ItemDataBeans();
				idb.setId(rs.getInt("id"));
				idb.setName(rs.getString("name"));
				idb.setPrice(rs.getInt("price"));
				idb.setStock(rs.getInt("stock"));
				productList.add(idb);
			}
			return productList;
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
	 * ランダムで引数指定分のItemDataBeansを取得
	 */
	public static ArrayList<ItemDataBeans> getRandItem(int limit) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement("SELECT * FROM item ORDER BY RAND() LIMIT ? ");
			st.setInt(1, limit);

			ResultSet rs = st.executeQuery();

			ArrayList<ItemDataBeans> itemList = new ArrayList<ItemDataBeans>();

			while (rs.next()) {
				ItemDataBeans item = new ItemDataBeans();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getInt("price"));
				item.setFileName(rs.getString("file_name"));
				itemList.add(item);
			}
			return itemList;
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
     * idが一致する商品情報を全て返す
     */
    public ItemDataBeans findItemInfo(int id) throws SQLException{
        Connection con = null;
        try {
            // データベースへ接続
            con = DBManager.getConnection();

            // SELECT文を準備
            String sql = "SELECT * FROM item WHERE id = ?";

             // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = con.prepareStatement(sql);
            pStmt.setInt(1, id);
            ResultSet rs = pStmt.executeQuery();

             // 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
            if (!rs.next()) {
                return null;
            }

            String nameData = rs.getString("name");
            String detailData = rs.getString("detail");
            Boolean eggAllergyData = rs.getBoolean("allergy_egg");
            Boolean wheatAllergyData = rs.getBoolean("allergy_wheat");
            Boolean milkAllergyData = rs.getBoolean("allergy_milk");
            String allergyOtherData = rs.getString("allergy_other");
            int priceData = rs.getInt("price");
            String fileNameData = rs.getString("file_name");
            int stockData = rs.getInt("stock");
            int buySumData = rs.getInt("buy_sum");
            return new ItemDataBeans(id, nameData, detailData, eggAllergyData, wheatAllergyData, milkAllergyData,
            		allergyOtherData, priceData, fileNameData, stockData, buySumData);

        } catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
    }

    /**
     * 商品情報を登録する
     */
    public void newProduct(String name, String detail, String photo,
    		boolean eggAllergy, boolean wheatAllergy, boolean milkAllergy, String allergyOther, int price, int stock) throws SQLException {
    	Connection con = null;
        try {
            // データベースへ接続
            con = DBManager.getConnection();

            //INSERT文を準備
            String sql = "insert into item(name, detail, file_name, allergy_egg, allergy_wheat, allergy_milk, allergy_other, price, stock, buy_sum) "
            		+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, 0)";

            // INSERTを実行
            PreparedStatement pStmt = con.prepareStatement(sql);
            pStmt.setString(1, name);
            pStmt.setString(2, detail);
            pStmt.setString(3, photo);
            pStmt.setBoolean(4, eggAllergy);
            pStmt.setBoolean(5, wheatAllergy);
            pStmt.setBoolean(6, milkAllergy);
            pStmt.setString(7, allergyOther);
            pStmt.setInt(8, price);
            pStmt.setInt(9, stock);

            pStmt.executeUpdate();

        } catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
    }

    /**
     * 商品情報を更新する
     */
    public void productUpdate(int id, String name, String detail, String photo,boolean eggAllergy,
    		boolean wheatAllergy, boolean milkAllergy, String allergyOther, int price, int stock, int sellAddNum) throws SQLException {

    	Connection con = null;
        try {
            // データベースへ接続
            con = DBManager.getConnection();

            //INSERT文を準備
            String sql = "UPDATE item SET name = ?, detail = ?, file_name = ?, allergy_egg = ?, allergy_wheat = ?"
            		+ ", allergy_milk = ?, allergy_other = ?, price = ?, stock = ? WHERE id = ?";
            // INSERTを実行
            PreparedStatement pStmt = con.prepareStatement(sql);
            pStmt.setString(1, name);
            pStmt.setString(2, detail);
            pStmt.setString(3, photo);
            pStmt.setBoolean(4, eggAllergy);
            pStmt.setBoolean(5, wheatAllergy);
            pStmt.setBoolean(6, milkAllergy);
            pStmt.setString(7, allergyOther);
            pStmt.setInt(8, price);
            pStmt.setInt(9, (stock + sellAddNum) );
            pStmt.setInt(10, id);
            pStmt.executeUpdate();

        } catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
        }
    }
}
