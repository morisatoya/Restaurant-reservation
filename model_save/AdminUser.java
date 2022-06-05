package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AdminUser implements Serializable{


	public static final long serialVersionUID = 1L;

	private int usrId;
	private String usrName;
	private String password;
	private String address;
	private String usrPhone;
	private String mail;
	private String exp;

	public AdminUser(){
		super();
	}
	public int getUsrId(){
		return usrId;
	}
	public String getUsrName(){
		return usrName;
	}
	public String getPassword(){
		return password;
	}
	public String getAddress(){
		return address;
	}
	public String getUsrPhone(){
		return usrPhone;
	}
	public String getMail(){
		return mail;
	}
	public String getExp(){
		return exp;
	}

	public void setUsrId(int usrId){
		this.usrId = usrId;
	}
	public void setUsrName(String usrName){
		this.usrName = usrName;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public void setUsrPhone(String usrPhone){
		this.usrPhone = usrPhone;
	}
	public void setMail(String mail){
		this.mail = mail;
	}
	public void setExp(String exp){
		this.exp = exp;
	}

		public static AdminUser login(String usrPhone, String usrName) throws IdealException{
			String sql = null;
			InitialContext ic = null;
			DataSource ds = null;
			Connection con = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			boolean err = false;
			AdminUser log = new AdminUser();

			try {
				ic = new InitialContext();
				ds = (DataSource) ic.lookup("java:comp/env/mysql");
				con = ds.getConnection();
				sql ="SELECT * FROM user WHERE usr_name AND phone";
				pst = con.prepareStatement(sql);
				pst.setString(2, usrPhone);
				pst.setString(1, usrName);
				rs = pst.executeQuery();

				if(rs.next()) {
					log.setUsrId(rs.getInt("usr_id"));
					log.setUsrName(rs.getString("usr_name"));
					log.setPassword(rs.getString("password"));
					log.setAddress(rs.getString("address"));
					log.setUsrPhone(rs.getString("phone"));
					log.setMail(rs.getString("mail"));
					log.setExp(rs.getString("exp"));
				}else{
					err = true;
				}
			}catch (SQLException|NamingException e) {
				err = true;

			}finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (pst != null) {
						pst.close();
					}
					if (con != null) {
						con.close();
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if(err){
				System.out.println(pst.toString());
				throw new IdealException(IdealException.ERR_NO_NOT_MEMBER_EXCEPTION);
			}
			return log;
		}
	public static AdminUser getUser(int usrId) throws IdealException{{
		String sql = null;
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean err = false;
		AdminUser usr = new AdminUser();

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			sql ="SELECT * FROM user WHERE usr_id = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, usrId);
			rs = pst.executeQuery();

			while(rs.next()) {
				usr.setUsrId(rs.getInt("usr_id"));
				usr.setUsrName(rs.getString("usr_name"));
				usr.setPassword(rs.getString("password"));
				usr.setAddress(rs.getString("address"));
				usr.setUsrPhone(rs.getString("phone"));
				usr.setMail(rs.getString("mail"));
				usr.setExp(rs.getString("exp"));
			}
		}catch (SQLException | NamingException e) {
			e.printStackTrace();
			err = true;
		}finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
				if (con != null) {
					con.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(err){
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		}
		return usr;
		}
	}

			public static AdminUser insert(AdminUser user) throws IdealException{//----------------------
			String sql = null;
			InitialContext ic = null;
			DataSource ds = null;
			Connection con = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			int usrId = -1;
			boolean err = false;
			AdminUser insert = new AdminUser();

			try {
				ic = new InitialContext();
				ds = (DataSource) ic.lookup("java:comp/env/mysql");
				con = ds.getConnection();
				con.setAutoCommit(false);
				sql = "INSERT INTO user(usr_id ,usr_name ,password , address , phone , mail , exp )"
						+" VALUES( DEFAULT , ? , PASSWORD(?) , ? , ? , ? , ? )";
				pst = con.prepareStatement(sql);

				pst.setString(1, user.getUsrName());
				pst.setString(2, user.getPassword());
				pst.setString(3, user.getAddress());
				pst.setString(4, user.getUsrPhone());
				pst.setString(5, user.getMail());
				pst.setString(6, user.getExp());
				pst.executeUpdate();
				pst.close(); //insertが終わったのでｐｓｔをクローズ

				pst = con.prepareStatement("SELECT LAST_INSERT_ID()");
				rs = pst.executeQuery();//insertしたデータのＩＤを取り出す。

				if(rs.next()){
					usrId = rs.getInt("LAST_INSERT_ID()");//usrIdにsql文の実行結果を受け渡す。

					rs.close();//役目が終わったのでいったんクローズ
					pst.close();//同上

					sql ="SELECT * FROM user WHERE usr_id = ?";//idからすべての情報を受け取る
					pst = con.prepareStatement(sql);
					pst.setInt(1,usrId);
					rs = pst.executeQuery();//実行結果をrsに入れる。

					while(rs.next()){
						insert.setUsrId(rs.getInt("usr_id"));
						insert.setUsrName(rs.getString("usr_name"));
						insert.setAddress(rs.getString("address"));
						insert.setUsrPhone("phone");
						insert.setMail("mail");
						insert.setExp("exp");
						//insert.setPassword("password");
						con.commit();
					}
				}else{
					err = true;
					//たぶん個々の値がUserOperatioSvlにわたるわけではないがとりあえずいれとく。
					throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
				}


			}catch (SQLException | NamingException |IdealException e) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
			}finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (pst != null) {
						pst.close();
					}
					if (con != null) {
						con.commit();
						con.close();
					}
					if(pst != null){
						pst.close();
					}
				}catch (Exception e) {
					e.printStackTrace();;
				}
			}
			if(err){
				throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			}
			return insert;
		}//-----------------------------------------------------------------------
	public static AdminUser update(AdminUser user,int passmode) throws IdealException{
		String sql = null;
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		AdminUser up = new AdminUser();
		boolean err = false;

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			con.setAutoCommit(false);
			//passwordとUsrIdで選択

			sql ="UPDATE user SET usr_name = ?, password = PASSWORD(?),address = ?,"
			+ " phone = ?, mail = ?, exp = ? WHERE usr_id = ? ";
			if(passmode == 1){
			sql ="UPDATE user SET usr_name = ?, password = ? ,address = ?,"
			+ " phone = ?, mail = ?, exp = ? WHERE usr_id = ? ";
			}

			pst = con.prepareStatement(sql);

			pst.setString(1, user.getUsrName());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getAddress());
			pst.setString(4, user.getUsrPhone());
			pst.setString(5, user.getMail());
			pst.setString(6, user.getExp());
			pst.setInt(7, user.getUsrId());

			pst.executeUpdate();
			pst.close();

			sql = "SELECT * FROM user WHERE usr_id = ? ";
			pst = con.prepareStatement(sql);
			pst.setInt(1,user.getUsrId());
			rs = pst.executeQuery();

			if(rs.next()){
				up.setUsrId(rs.getInt("usr_id"));
				up.setUsrName(rs.getString("usr_name"));
				up.setAddress("address");
				up.setUsrPhone("phone");
				up.setMail("mail");
				up.setExp("exp");
				up.setPassword("password");
				con.commit();
			}else{
				err = true;
				throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			}


		}catch (SQLException | NamingException | IdealException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
		}finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
				if (con != null) {
					con.commit();
					con.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(err){
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		}
		return up;
	}
	public static void delete(AdminUser user) throws IdealException{
		String[] sql = {"DELETE FROM reserve WHERE usr_id = ?;","DELETE FROM user WHERE usr_id = ? "};
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		boolean err = false;

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			con.setAutoCommit(false);

			for(int i = 0; i < sql.length; i++){
				pst = con.prepareStatement(sql[i]);
				pst.setInt(1, user.getUsrId());
				pst.executeUpdate();
				pst.close();
			}





		}catch (SQLException | NamingException e) {
			try {
				err  = true;
				System.out.println("エラー");
				con.rollback();
			} catch (SQLException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
		}finally {
			try {
				if (pst != null) {
					pst.close();
				}
				if (con != null) {
					con.commit();
					con.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			if(err){
				throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			}
		}

	}
}
