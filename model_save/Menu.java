package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import controller.MenuOperationSvl;

public class Menu {
	private int menuId;
	private String menuName;
	private String detail;
	private int price;
	private int orderFlg;
	private int typeId;
	private String typeName;
	private final static String[] KEYS = { "m_id", "m_name", "detail",
			"orderFlg", "price", "t_id", "t_name" };

	private final static String M_NAME_KEY = "m_Name";
	private final static String M_ID_KEY = "m_id";
	private final static String C_NAME_KEY = "c_name";
	private final static String C_ID_KEY = "c_id";

	public Menu() {
		super();
	}

	public int getMenuId() {
		return menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public String getDetail() {
		return detail;
	}

	public int getPrice() {
		return price;
	}

	public int getOrderFlg() {
		return orderFlg;
	}

	public int getTypeId() {
		return typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setMenuId(int menuID) {
		this.menuId = menuID;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setOrderFlg(int orderFlg) {
		this.orderFlg = orderFlg;
	}

	public void setTypeId(int typeID) {
		this.typeId = typeID;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		// TODO 自動生成されたメソッド・スタブ
		return menuName;
	}

	public static Menu getOneMenu(int menuID, int typeID) throws IdealException {
		String sql = null;
		Menu menu = new Menu();
		if (typeID == 100) {
			sql = "select * from course join menutype using (t_id) where c_id = ?";
			KEYS[0] = C_ID_KEY;
			KEYS[1] = C_NAME_KEY;
		} else {
			sql = "select * from menu join menutype using (t_id) where m_id = ?";
			KEYS[0] = M_ID_KEY;
			KEYS[1] = M_NAME_KEY;
		}

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
			pst.setInt(1, menuID);
			rs = pst.executeQuery();
			menu = new Menu();

			if (rs.next()) {
				menu.setMenuId(rs.getInt(KEYS[0]));
				menu.setMenuName(rs.getString(KEYS[1]));
				menu.setDetail(rs.getString(KEYS[2]));
				menu.setOrderFlg(rs.getInt(KEYS[3]));
				menu.setPrice(rs.getInt(KEYS[4]));
				menu.setTypeId(rs.getInt(KEYS[5]));
				menu.setTypeName(rs.getString(KEYS[6]));
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

		if (isError) {
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		}
		return menu;
	}

	public static Menu getOneMenu(int menuID) throws IdealException {
		return getOneMenu(menuID, 0);
	}

	public static ArrayList<Menu> getMenu(int typeID) throws IdealException {
		String sql = "select * from menu join menutype using (t_id) where t_id = ? order by m_id asc";
		if (typeID == 100) {
			KEYS[0] = C_ID_KEY;
			KEYS[1] = C_NAME_KEY;
			sql = "select * from course join menutype using (t_id) where t_id = ? order by c_id asc";
		} else {
			KEYS[0] = M_ID_KEY;
			KEYS[1] = M_NAME_KEY;
		}

		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Menu> list = new ArrayList<Menu>();
		boolean isError = false;

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, typeID);
			rs = pst.executeQuery();
			while (rs.next()) {
				Menu menu = new Menu();
				menu.setMenuId(rs.getInt(KEYS[0]));
				menu.setMenuName(rs.getString(KEYS[1]));
				menu.setDetail(rs.getString(KEYS[2]));
				menu.setOrderFlg(rs.getInt(KEYS[3]));
				menu.setPrice(rs.getInt(KEYS[4]));
				menu.setTypeId(rs.getInt(KEYS[5]));
				menu.setTypeName(rs.getString(KEYS[6]));
				list.add(menu);
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

		if (isError) {
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		}

		return list;
	}

	public static ArrayList<Menu> getMenuList() throws IdealException {
		String[] sqls = {
				"select * from course join menutype using (t_id) where orderFlg = 1 order by t_id asc , c_id asc",
				"select * from menu join menutype using (t_id) where orderFlg = 1 order by t_id , m_id asc" };
		KEYS[0] = C_ID_KEY;
		KEYS[1] = C_NAME_KEY;


		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Menu> list = new ArrayList<Menu>();
		boolean isError = false;

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			for (String sql : sqls) {
				pst = con.prepareStatement(sql);
				rs = pst.executeQuery();
				while (rs.next()) {
					Menu menu = new Menu();
					menu.setMenuId(rs.getInt(KEYS[0]));
					menu.setMenuName(rs.getString(KEYS[1]));
					menu.setDetail(rs.getString(KEYS[2]));
					menu.setOrderFlg(rs.getInt(KEYS[3]));
					menu.setPrice(rs.getInt(KEYS[4]));
					menu.setTypeId(rs.getInt(KEYS[5]));
					menu.setTypeName(rs.getString(KEYS[6]));
					list.add(menu);
				}
				KEYS[0] = M_ID_KEY;
				KEYS[1] = M_NAME_KEY;

				rs.close();
				pst.close();
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

		if (isError) {
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		}

		return list;
	}

	public static int updateMenu(Menu m, int mode) throws IdealException {
		int number = 0;
		String sql = null;
		String table = "menu";

		if (m.getTypeId() == 100) {
			KEYS[0] = C_ID_KEY;
			KEYS[1] = C_NAME_KEY;
			table = "course";
		} else {
			KEYS[0] = M_ID_KEY;
			KEYS[1] = M_NAME_KEY;
		}
		String[] values = null; // sql文にセットする値を入れる変数
		boolean[] isString = null; // i番目のvaluesがStringがどうかを審議値で保持***falseの場合は数字と判断
		boolean isError = false;

		if (mode == MenuOperationSvl.INSERT) {
			sql = "insert into " + table + " values (?,?,?,?,?,?)";
			values = new String[] { String.valueOf(m.getMenuId()),
					m.getMenuName(), m.getDetail(),
					String.valueOf(m.getOrderFlg()),
					String.valueOf(m.getPrice()), String.valueOf(m.getTypeId()) };
			isString = new boolean[] { false, true, true, false, false, false };
		} else if (mode == MenuOperationSvl.UPDATE) {
			sql = "update " + table + " set " + KEYS[1] + "= ?, " + KEYS[2]
					+ " = ?, " + KEYS[3] + "= ?, " + KEYS[4] + " = ? , "
					+ KEYS[5] + " = ?  where " + KEYS[0] + "=?";
			values = new String[] { m.getMenuName(), m.getDetail(),
					String.valueOf(m.getOrderFlg()),
					String.valueOf(m.getPrice()),
					String.valueOf(m.getTypeId()),
					String.valueOf(m.getMenuId()) };
			isString = new boolean[] { true, true, false, false, false, true };
		} else if (mode == MenuOperationSvl.DELETE) {
			sql = "delete from " + table + " where " + KEYS[0] + " = ?";
			values = new String[] { String.valueOf(m.getMenuId()) };
			isString = new boolean[] { false };
			if (m.typeId == 100) {
				deleteCtl(m, mode);
			}
		}

		if (sql == null) {
			// modeが見つからなければ-1を返す。
			return -1;
		}

		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		Savepoint sv = null;

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			con.setAutoCommit(false);
			sv = con.setSavepoint();
			pst = con.prepareStatement(sql);

			// System.out.println(m.getTypeID());
			for (int i = 0; i < values.length; i++) {
				if (isString[i]) {
					pst.setString(i + 1, values[i]);
				} else {
					pst.setInt(i + 1, Integer.parseInt(values[i]));
				}
			}
			number = pst.executeUpdate();
		} catch (SQLException e) {
			if(con != null){
				try {
					con.rollback(sv);
				} catch (SQLException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
			}
			System.out.println(pst);
			e.printStackTrace();
			number = -1;
			isError = true;
		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			number = -1;
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
				if (con != null) {
					con.commit();
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (isError) {
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		}
		return number;
	}

	@Deprecated
	private static void deleteCtl(Menu m, int mode) throws IdealException {
		// TODO 自動生成されたメソッド・スタブ
		if (m.typeId != 100 && mode != MenuController.DELETE) {
			throw new RuntimeException("コースの削除ではありません");
		}

		String sql = "delete from coursectl where c_id = ?";
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		Savepoint sv =null;
		boolean isError = false;

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			con = ds.getConnection();
			con.setAutoCommit(false);
			sv = con.setSavepoint();
			pst = con.prepareStatement(sql);
			// System.out.println(m.getTypeID());
			pst.setInt(1, m.menuId);
			pst.executeUpdate();
		} catch (SQLException e) {
			if(con != null){
				try {
					con.rollback(sv);
				} catch (SQLException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
			}
			System.out.println(pst);
			e.printStackTrace();
			isError = true;
		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			try {
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

	}
}
