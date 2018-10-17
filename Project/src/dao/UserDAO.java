package dao;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import base.DBManager;
import beans.UserDataBeans;

public class UserDAO {
	 /**
     * idが一致するユーザー情報を削除する
     */
    public void deleteUser(int id) throws SQLException {
        Connection con = null;
        try {
            // データベースへ接続
            con = DBManager.getConnection();

            // SELECT文を準備
            String sql = "DELETE FROM user WHERE id = ?";

             // SELECTを実行し、ユーザ情報を削除
            PreparedStatement pStmt = con.prepareStatement(sql);
            pStmt.setInt(1, id);
            pStmt.executeUpdate();


        } catch (Exception e) {
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
    }

	/**
     * ログインIDとパスワードに紐づくユーザ情報を返す
     */
    public UserDataBeans findByLoginInfo(String loginId, String password) throws SQLException {
        Connection con = null;
        try {
            // データベースへ接続
            con = DBManager.getConnection();

            // SELECT文を準備
            String sql = "SELECT * FROM user WHERE login_id = ? and login_password = ?";

             // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = con.prepareStatement(sql);
            pStmt.setString(1, loginId);
            pStmt.setString(2, convartpassword(password));
            ResultSet rs = pStmt.executeQuery();

             // 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
            if (!rs.next()) {
                return null;
            }

            int idData = rs.getInt("id");
            String loginIdData = rs.getString("login_id");
            String nameData = rs.getString("name");
            return new UserDataBeans(idData,loginIdData, nameData);

        } catch (Exception e) {
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
    }

    /**
     * idが一致するユーザー情報を全て返す
     */
    public UserDataBeans findUserInfo(int id) throws SQLException {
        Connection con = null;
        try {
            // データベースへ接続
            con = DBManager.getConnection();

            // SELECT文を準備
            String sql = "SELECT * FROM user WHERE id = ?";

             // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = con.prepareStatement(sql);
            pStmt.setInt(1, id);
            ResultSet rs = pStmt.executeQuery();

             // 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
            if (!rs.next()) {
                return null;
            }

            String loginIdData = rs.getString("login_id");
            String nameData = rs.getString("name");
            Date brithDateData = rs.getDate("birthday");
            String createDateData = rs.getString("create_date");
            String updateDateData = rs.getString("update_date");

            return new UserDataBeans(id,loginIdData, nameData, brithDateData, createDateData, updateDateData);

        } catch (Exception e) {
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
    }

    /**
	 * 管理者を除くユーザ総数を取得
	 */
	public static double getUserAll() throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement("select count(*) as cnt from User WHERE login_id not in ('admin')");
			ResultSet rs = st.executeQuery();
			double userAll = 0;
			while (rs.next()) {
				userAll = Double.parseDouble(rs.getString("cnt"));
			}
			return userAll;
		} catch (Exception e) {
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
	 * 検索からの管理者を除くユーザ総数を取得
	 *
	 */
	public static double getUserCount(String searchLoginId, String searchUserName, String startBirthdate, String endBirthdate) throws SQLException {
		Connection con = null;
		try {
			con = DBManager.getConnection();

			// SELECT文を準備
            String sql = "select count(*) as cnt from user where login_id not in ('admin')";

            if(!searchLoginId.equals("")) {
            	sql += " and login_id = '" + searchLoginId + "'";
            }
            if(!searchUserName.equals("")) {
            	sql += " and name like '%" + searchUserName + "%'";
            }
            if(!startBirthdate.equals("")) {
            	sql += " and birthday >= '" + startBirthdate + "'";
            }
            if(!endBirthdate.equals("")) {
            	sql += " and birthday <= '" + endBirthdate + "'";
            }

            // SELECTを実行し、結果を取得
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

			double userCount = 0;
			while (rs.next()) {
				userCount = Double.parseDouble(rs.getString("cnt"));
			}
			return userCount;
		} catch (Exception e) {
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
	 * ユーザ検索
	 */
	public ArrayList<UserDataBeans> getUserBySearch
		(String searchLoginId, String searchUserName, String startBirthdate, String endBirthdate,int pageNum, int pageMaxUserCount) throws SQLException {

		Connection con = null;
		try {
			int startUserNum = (pageNum - 1) * pageMaxUserCount;
			con = DBManager.getConnection();

			// SELECT文を準備
            String sql = "SELECT * FROM user where login_id not in ('admin')";

            if(!searchLoginId.equals("")) {
            	sql += " and login_id = '" + searchLoginId + "'";
            }
            if(!searchUserName.equals("")) {
            	sql += " and name like '%" + searchUserName + "%'";
            }
            if(!startBirthdate.equals("")) {
            	sql += " and birthday >= '" + startBirthdate + "'";
            }
            if(!endBirthdate.equals("")) {
            	sql += " and birthday <= '" + endBirthdate + "'";
            }

            sql += "ORDER BY id ASC LIMIT "+ startUserNum + "," + pageMaxUserCount;

            // SELECTを実行し、結果表を取得
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

			ArrayList<UserDataBeans> userList = new ArrayList<UserDataBeans>();

			while (rs.next()) {
				UserDataBeans udb = new UserDataBeans();
				udb.setId(rs.getInt("id"));
				udb.setName(rs.getString("name"));
				udb.setBirthDate(rs.getDate("birthday"));
				udb.setLoginId(rs.getString("login_id"));
				userList.add(udb);
			}
			return userList;
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

    /**
	 * ユーザーIDを取得
	 */
	public static int getUserId(String loginId, String password) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement("SELECT * FROM user WHERE login_id = ?");
			st.setString(1, loginId);

			ResultSet rs = st.executeQuery();

			int userId = 0;
			while (rs.next()) {
				userId = rs.getInt("id");
				break;
			}
			return userId;

		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
     * 管理者以外のユーザ情報をページ表示分、取得する
     */
	public ArrayList<UserDataBeans> getUsers(int pageNum, int pageMaxUserCount) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			int startUserNum = (pageNum - 1) * pageMaxUserCount;
			con = DBManager.getConnection();

				st = con.prepareStatement("SELECT * FROM user WHERE login_id not in ('admin') ORDER BY id ASC LIMIT ?,?");
				st.setInt(1, startUserNum);
				st.setInt(2, pageMaxUserCount);

			ResultSet rs = st.executeQuery();
			ArrayList<UserDataBeans> userList = new ArrayList<UserDataBeans>();

			while (rs.next()) {
				UserDataBeans udb = new UserDataBeans();
				udb.setId(rs.getInt("id"));
				udb.setName(rs.getString("name"));
				udb.setBirthDate(rs.getDate("birthday"));
				udb.setLoginId(rs.getString("login_id"));
				userList.add(udb);
			}
			return userList;

		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	 /**
     * ログインIDがすでに登録されているかをbooleanで返す (true:未登録 false:登録済み)
     */
    public boolean newUser(String loginId) throws SQLException {
    	 Connection con = null;
         try {
             // データベースへ接続
             con = DBManager.getConnection();

             // SELECT文を準備
             String sql = "SELECT * FROM user WHERE login_id = ?";

             // SELECTを実行し、結果表を取得
             PreparedStatement pStmt = con.prepareStatement(sql);
             pStmt.setString(1, loginId);
             ResultSet rs = pStmt.executeQuery();

              // レコードが０件の場合未登録
             if (!rs.next()) {
                 return true;
             }
             return false;

         } catch (Exception e) {
 			throw new SQLException(e);
 		} finally {
 			if (con != null) {
 				con.close();
 			}
 		}
    }

    /**
     * ユーザ情報を登録する
     */
    public void newUser(String loginId, String userName, String birthday, String password) throws SQLException {
    	Connection con = null;
        try {
            // データベースへ接続
            con = DBManager.getConnection();


            //INSERT文を準備
            String sql = "insert into user(login_id,name,birthday,login_password,create_date,update_date) values(?, ?, ?, ?, now(), now())";

            // INSERTを実行
            PreparedStatement pStmt = con.prepareStatement(sql);
            pStmt.setString(1, loginId);
            pStmt.setString(2, userName);
            pStmt.setString(3, birthday);
            pStmt.setString(4, convartpassword(password));
            pStmt.executeUpdate();

        } catch (Exception e) {
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
    }

    /**
     * ユーザ情報を更新する
     */
    public void UserUpdate(int id, String userName, String birthday, String password) throws SQLException {
    	Connection con = null;
        try {
            // データベースへ接続
            con = DBManager.getConnection();

            //INSERT文を準備
            String sql = "UPDATE user SET login_password = ?, name = ?, birthday = ?, update_date = now() WHERE id = ?";
            // INSERTを実行
            PreparedStatement pStmt = con.prepareStatement(sql);
            pStmt.setString(1, convartpassword(password));
            pStmt.setString(2, userName);
            pStmt.setString(3, birthday);
            pStmt.setInt(4,id);
            pStmt.executeUpdate();

        } catch (Exception e) {
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
    }

    private String convartpassword(String password){
    	//ハッシュ生成前にバイト配列に置き換える際のCharset
    	Charset charset = StandardCharsets.UTF_8;
    	//ハッシュアルゴリズム
    	String algorithm = "MD5";

    	//ハッシュ生成処理
    	byte[] bytes = null;
		try {
			bytes = MessageDigest.getInstance(algorithm).digest(password.getBytes(charset));
		} catch (NoSuchAlgorithmException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
    	String result = DatatypeConverter.printHexBinary(bytes);
    	//標準出力
    	return result;

    }
}
