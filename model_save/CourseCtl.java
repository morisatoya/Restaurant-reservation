package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CourseCtl implements Serializable{
	private int c_Id;
	private int m_Id;

	public int getC_Id() {
		return c_Id;
	}
	public int getM_Id() {
		return m_Id;
	}
	public void setC_Id(int c_Id) {
		this.c_Id = c_Id;
	}
	public void setM_Id(int m_Id) {
		this.m_Id = m_Id;
	}

	public static void CourseMenuChk(int mId) throws IdealException{
		String sql = "select * from coursectl where m_id = ?";

		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean isError = false;
		boolean isNothing = false;

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, mId);
			rs = pst.executeQuery();
			isNothing = !rs.next();
		} catch (SQLException e) {
			System.out.println(pst);
			e.printStackTrace();
			isError = true;
		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (isError) {
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		}
		if(isNothing == false){
			throw new IdealException(IdealException.ERR_NO_NOT_MENU_DELETE);
		}

	}
}
