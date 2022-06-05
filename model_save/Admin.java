package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Admin {
	private String admName;					// 氏名
	private String password;				// パスワード
	private String exp;						// 備考

	public Admin(){
		super();
	}

	// getterの定義
	public String getAdmName() {
		return admName;
	}

	public String getPassword() {
		return password;
	}

	public String getExp() {
		return exp;
	}

	// setterの定義
	public void setAdmName(String admName) {
		this.admName = admName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}
	public static Admin login(String admName, String password)throws IdealException{
		Admin adm = null;
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		String sql = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean err = false;

		try{
			ic = new InitialContext();
			ds = (DataSource)ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			sql = "SELECT * FROM admin WHERE adm_name= ? AND password = PASSWORD(?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, admName);
			pst.setString(2, password);

			rs = pst.executeQuery();
			if(rs.next()){
				adm = new Admin();
				adm.setAdmName(rs.getString("adm_name"));
				adm.setPassword(rs.getString("password"));
				adm.setExp(rs.getString("exp"));
			}else{
				err = true;
			}
		}catch(SQLException|NamingException e){
			System.out.println(pst);
			e.printStackTrace();
			err = true;
		}finally{
			try{
				if(rs != null)rs.close();
				if(pst != null)pst.close();
				if(con != null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(err){
			throw new IdealException(IdealException.ERR_NO_NOT_MEMBER_EXCEPTION);
		}
		return adm;
	}

}
