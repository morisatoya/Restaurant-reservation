package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import controller.CourseOperationSvl;

public class Course implements Serializable {
	private int courseId;
	private String courseName;
	private String detail;
	private int price;
	private int orderFlg;
	private int typeId;
	private String typeName;
	private int menuId;
	private String menuName;
	private final static String[] KEYS = { "c_id", "c_name", "detail",
			"orderFlg", "price", "t_id", "t_name" };
	private final static String[] MENU_KEYS = { "m_id", "m_name" };

	public final static int[] COURSE_MENU_TYPEID = { 200, 210, 220, 300, 310,
			400 };

	public int getCourseId() {
		return courseId;
	}

	public String getCourseName() {
		return courseName;
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

	public int getMenuId() {
		return menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
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

	public void setMenuId(int menuID) {
		this.menuId = menuID;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Override
	public String toString() {
		// TODO 自動生成されたメソッド・スタブ
		return courseName + menuName;
	}

	public static Course getCourse(int cId) throws IdealException {
		Course course = new Course();
		String sql = "select * from course join menutype using (t_id) where c_id = ?";

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
			pst.setInt(1, cId);
			rs = pst.executeQuery();

			if (rs.next()) {
				course.setCourseId(rs.getInt(KEYS[0]));
				course.setCourseName(rs.getString(KEYS[1]));
				course.setDetail(rs.getString(KEYS[2]));
				course.setOrderFlg(rs.getInt(KEYS[3]));
				course.setPrice(rs.getInt(KEYS[4]));
				course.setTypeId(rs.getInt(KEYS[5]));
				course.setTypeName(rs.getString(KEYS[6]));
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
		return course;
	}

	public static ArrayList<Course> getOneCourse(int cId) throws IdealException {
		String sql = "select * from course join coursectl using(c_id) join menu using (m_id) join menutype on menu.t_id=menutype.t_id "
				+ "where c_id = ? order by c_id asc , menu.m_id asc";

		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean isError = false;
		ArrayList<Course> list = new ArrayList<>();

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, cId);
			rs = pst.executeQuery();

			while (rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt(KEYS[0]));
				course.setCourseName(rs.getString(KEYS[1]));
				course.setDetail(rs.getString(KEYS[2]));
				course.setOrderFlg(rs.getInt(KEYS[3]));
				course.setPrice(rs.getInt(KEYS[4]));
				course.setTypeId(rs.getInt("menutype.t_id"));
				course.setTypeName(rs.getString("menutype.t_name"));
				course.setMenuId(rs.getInt(MENU_KEYS[0]));
				course.setMenuName(rs.getString(MENU_KEYS[1]));
				list.add(course);
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

	public static ArrayList<Course> getCourseList() throws IdealException {
		String sql = "select * from course join coursectl using(c_id) join menu using (m_id) "
				+ "join menutype on menu.t_id=menutype.t_id order by c_id asc,menu.t_id asc";

		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean isError = false;
		ArrayList<Course> list = new ArrayList<>();

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt(KEYS[0]));
				course.setCourseName(rs.getString(KEYS[1]));
				course.setDetail(rs.getString(KEYS[2]));
				course.setOrderFlg(rs.getInt(KEYS[3]));
				course.setPrice(rs.getInt(KEYS[4]));
				course.setTypeId(rs.getInt("menutype.t_id"));
				course.setTypeName(rs.getString("menutype.t_name"));
				course.setMenuId(rs.getInt(MENU_KEYS[0]));
				course.setMenuName(rs.getString(MENU_KEYS[1]));
				list.add(course);
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

	public static ArrayList<Course> getOneCourseList() throws IdealException {
		String sql = "select * from course join menutype using (t_id) where orderFlg = 1 order by c_id";

		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean isError = false;
		ArrayList<Course> list = new ArrayList<>();

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt(KEYS[0]));
				course.setCourseName(rs.getString(KEYS[1]));
				course.setDetail(rs.getString(KEYS[2]));
				course.setOrderFlg(rs.getInt(KEYS[3]));
				course.setPrice(rs.getInt(KEYS[4]));
				course.setTypeId(rs.getInt(KEYS[5]));
				course.setTypeName(rs.getString(KEYS[6]));
				list.add(course);
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

	public static ArrayList<Course> getTypeCourseList(int tId)
			throws IdealException {
		String sql = "select * from course join menutype using (t_id) where t_id = ? order by c_id";

		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean isError = false;
		ArrayList<Course> list = new ArrayList<>();

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, tId);
			rs = pst.executeQuery();

			while (rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt(KEYS[0]));
				course.setCourseName(rs.getString(KEYS[1]));
				course.setDetail(rs.getString(KEYS[2]));
				course.setOrderFlg(rs.getInt(KEYS[3]));
				course.setPrice(rs.getInt(KEYS[4]));
				course.setTypeId(rs.getInt(KEYS[5]));
				course.setTypeName(rs.getString(KEYS[6]));
				list.add(course);
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

	public static int updateCourse(Course course, int mode,
			ArrayList<CourseCtl> courseCtl) throws IdealException {
		if (mode == CourseOperationSvl.INSERT) {
			return insert(course, courseCtl);
		} else if (mode == CourseOperationSvl.UPDATE) {
			return update(course, courseCtl);
		} else if (mode == CourseOperationSvl.DELETE) {
			return delete(course, courseCtl);
		}
		return -1;
	}

	private static int insert(Course course, ArrayList<CourseCtl> courseCtl)
			throws IdealException {
		// TODO 自動生成されたメソッド・スタブ
		String courseSql = "insert into course(" + KEYS[1] + "," + KEYS[2]
				+ "," + KEYS[3] + "," + KEYS[4] + "," + KEYS[5]
				+ ") values(?,?,?,?,?) ";
		String ctlSql = "insert into coursectl values(?,?)";

		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		boolean isError = false;
		Savepoint sp = null;
		ResultSet rs = null;
		int num = 0;

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			con.setAutoCommit(false);
			sp = con.setSavepoint();
			pst = con.prepareStatement(courseSql);
			pst.setString(1, course.getCourseName());
			pst.setString(2, course.getDetail());
			pst.setInt(3, course.getOrderFlg());
			pst.setInt(4, course.getPrice());
			pst.setInt(5, course.getTypeId());
			num += pst.executeUpdate();
			pst.close();
			pst = con.prepareStatement("select last_insert_id()");
			rs = pst.executeQuery();
			int id = 0;
			if (rs.next()) {
				id = rs.getInt(1);
			}
			for (CourseCtl cc : courseCtl) {
				pst.close();
				pst = con.prepareStatement(ctlSql);
				pst.setInt(1, id);
				pst.setInt(2, cc.getM_Id());
				num += pst.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(pst);
			e.printStackTrace();
			if (sp != null) {
				try {
					if (con != null) {
						con.rollback(sp);
					}
				} catch (SQLException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
				num = 0;
			}
			isError = true;
		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			num = 0;
		} finally {
			try {
				if(rs != null){
					rs.close();
				}
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
		return num;
	}

	private static int update(Course course, ArrayList<CourseCtl> courseCtl)
			throws IdealException {
		// TODO 自動生成されたメソッド・スタブ
		String courseSql = "update course set " + KEYS[1] + "=?," + KEYS[2]
				+ "=?," + KEYS[3] + "=?," + KEYS[4] + "=?," + KEYS[5]
				+ "=? where " + KEYS[0] + "=?";
		String ctlSql1 = "delete from coursectl where c_id = ?";
		String ctlSql2 = "insert into coursectl values(?,?)";

		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		boolean isError = false;
		Savepoint sp = null;
		int num = 0;

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			con.setAutoCommit(false);
			sp = con.setSavepoint();
			pst = con.prepareStatement(courseSql);
			pst.setString(1, course.getCourseName());
			pst.setString(2, course.getDetail());
			pst.setInt(3, course.getOrderFlg());
			pst.setInt(4, course.getPrice());
			pst.setInt(5, course.getTypeId());
			pst.setInt(6, course.getCourseId());
			num += pst.executeUpdate();
			pst.close();
			pst = con.prepareStatement(ctlSql1);
			pst.setInt(1, course.getCourseId());
			num += pst.executeUpdate();
			for (CourseCtl cc : courseCtl) {
				pst.close();
				pst = con.prepareStatement(ctlSql2);
				pst.setInt(1, course.getCourseId());
				pst.setInt(2, cc.getM_Id());
				num += pst.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(pst);
			e.printStackTrace();
			if (sp != null) {
				try {
					if (con != null) {
						con.rollback(sp);
					}
				} catch (SQLException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
				num = 0;
			}
			isError = true;
		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			num = 0;
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
		return num;
	}

	private static int delete(Course course, ArrayList<CourseCtl> courseCtl)
			throws IdealException {
		// TODO 自動生成されたメソッド・スタブ
		String courseSql = "delete from course where c_id = ?";
		String ctlSql = "delete from coursectl where c_id = ?";
		String reserveSerach = "select rsv_id from reserve where c_id = ?";

		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		boolean isError = false;
		Savepoint sp = null;
		int num = 0;
		ResultSet rs = null;
		boolean isReserved = false;

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			con.setAutoCommit(false);
			sp = con.setSavepoint();

			pst = con.prepareStatement(reserveSerach);
			pst.setInt(1, course.getCourseId());
			rs = pst.executeQuery();
			if(rs.next()){
				isReserved = true;
				throw new SQLException();
			}
			rs.close();
			pst.close();

			pst = con.prepareStatement(ctlSql);
			pst.setInt(1, course.getCourseId());
			num += pst.executeUpdate();
			pst.close();
			con.commit();

			pst = con.prepareStatement("select * from coursectl where c_id = ?");
			pst.setInt(1, course.courseId);
			rs = pst.executeQuery();

			if(!rs.next()){
				pst.close();
				pst = con.prepareStatement(courseSql);
				pst.setInt(1, course.getCourseId());
				num += pst.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(pst);
			e.printStackTrace();
			if (sp != null) {
				try {
					if (con != null) {
						con.rollback(sp);
					}
				} catch (SQLException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
				num = 0;
			}
			isError = true;
		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			num = 0;
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
		if (isReserved) {
			throw new IdealException(IdealException.ERR_NO_NOT_RESERV_DELETE);
		}
		if (isError) {
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		}
		return num;
	}
}
