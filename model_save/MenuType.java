package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MenuType {
	private int typeId;
	private String typeName;

	public MenuType() {
		super();
	}

	public int getTypeId() {
		return typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeId(int typeID) {
		this.typeId = typeID;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public static ArrayList<MenuType> getAllType() throws IdealException{
		ArrayList<MenuType> list = new ArrayList<>();

		String sql = "select * from menutype";
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean isError = false;

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				MenuType mt = new MenuType();
				mt.setTypeId(rs.getInt("t_id"));
				mt.setTypeName(rs.getString("t_name"));
				list.add(mt);
			}
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

		if(isError){
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		}

		return list;
	}
}
