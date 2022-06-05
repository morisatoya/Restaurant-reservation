package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Reserve {

	private int rsvId;
	private int usrId;
	private String usrName;
	private int rsvYy;
	private int rsvMm;
	private int rsvDd;
	private int rsvHh;
	private int rsvMi;
	private int person;
	private int tableId;
	private String tableName;
	private int courseId;
	private String courseName;
	private String appDate;
	private int appYy;
	private int appMm;
	private int appDd;
	private int appHh;
	private int appMi;

	private static final String RSV_ID_KEY = "rsv_id";
	private static final String USR_ID_KEY = "usr_id";
	private static final String USR_NAME_KEY = "usr_name";
	private static final String RSV_DATE_KEY = "rsv_date";
	private static final String PERSON_KEY = "person";
	private static final String TABLE_ID_KEY = "table_id";
	private static final String TABLE_NAME_KEY = "table_name";
	private static final String COURSE_ID_KEY = "c_id";
	private static final String COURSE_NAME_KEY = "c_name";
	private static final String APP_DATE_KEY = "app_date";
	private static final String MAX_CAPACITY_KEY = "max_capacity";
	private static final long HOUR_TO_MILI_SEC = 3600 * 1000;
	private static final long MINUTE_TO_MILI_SEC = 60 * 1000;
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public Reserve() {
		super();
	}

	public int getRsvId() {
		return rsvId;
	}

	public int getUsrId() {
		return usrId;
	}

	public String getUsrName() {
		return usrName;
	}

	public int getRsvYy() {
		return rsvYy;
	}

	public int getRsvMm() {
		return rsvMm;
	}

	public int getRsvDd() {
		return rsvDd;
	}

	public int getRsvHh() {
		return rsvHh;
	}

	public int getRsvMi() {
		return rsvMi;
	}

	public int getPerson() {
		return person;
	}

	public int getTableId() {
		return tableId;
	}

	public String getTableName() {
		return tableName;
	}

	public int getCourseId() {
		return courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getAppDate() {
		return appDate;
	}

	public int getAppYy() {
		return appYy;
	}

	public int getAppMm() {
		return appMm;
	}

	public int getAppDd() {
		return appDd;
	}

	public int getAppHh() {
		return appHh;
	}

	public int getAppMi() {
		return appMi;
	}

	public String getRsvDateString() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, rsvYy);
		c.set(Calendar.MONTH, rsvMm - 1);
		c.set(Calendar.DATE, rsvDd);
		c.set(Calendar.HOUR_OF_DAY, rsvHh);
		c.set(Calendar.MINUTE, rsvMi);
		c.set(Calendar.SECOND, 0);
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		return sdf.format(c.getTime());
	}

	public void setRsvId(int rsvId) {
		this.rsvId = rsvId;
	}

	public void setUsrId(int usrId) {
		this.usrId = usrId;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	public void setRsvYy(int rsvYy) {
		this.rsvYy = rsvYy;
	}

	public void setRsvMm(int rsvMm) {
		this.rsvMm = rsvMm;
	}

	public void setRsvDd(int rsvDd) {
		this.rsvDd = rsvDd;
	}

	public void setRsvHh(int rsvHh) {
		this.rsvHh = rsvHh;
	}

	public void setRsvMi(int rsvMi) {
		this.rsvMi = rsvMi;
	}

	public void setPerson(int person) {
		this.person = person;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

	public void setAppYy(int appYy) {
		this.appYy = appYy;
	}

	public void setAppMm(int appMm) {
		this.appMm = appMm;
	}

	public void setAppDd(int appDd) {
		this.appDd = appDd;
	}

	public void setAppHh(int appHh) {
		this.appHh = appHh;
	}

	public void setAppMi(int appMi) {
		this.appMi = appMi;
	}

	public static ArrayList<Reserve> getReserveList(int usrId)
			throws IdealException {
		String sql = "select rsv_id,usr_id,usr_name,rsv_date,person,table_id,table_name,c_id,c_name,app_date "
				+ " from reserve join user using(usr_id) join table_loc using(table_id) join course using(c_id) where "
				+ " usr_id = ? order by rsv_date asc";

		boolean isError = false;
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Reserve> list = new ArrayList<Reserve>();

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, usrId);
			rs = pst.executeQuery();
			while (rs.next()) {
				Reserve r = new Reserve();
				r.setRsvId(rs.getInt(RSV_ID_KEY));
				r.setUsrId(rs.getInt(USR_ID_KEY));
				r.setUsrName(rs.getString(USR_NAME_KEY));
				String rstr = rs.getString(RSV_DATE_KEY);
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
				Calendar rcal = Calendar.getInstance();
				rcal.setTime(sdf.parse(rstr));

				r.setRsvYy(rcal.get(Calendar.YEAR));
				r.setRsvMm(rcal.get(Calendar.MONTH) + 1);
				r.setRsvDd(rcal.get(Calendar.DATE));
				r.setRsvHh(rcal.get(Calendar.HOUR_OF_DAY));
				r.setRsvMi(rcal.get(Calendar.MINUTE));

				r.setPerson(rs.getInt(PERSON_KEY));
				r.setTableId(rs.getInt(TABLE_ID_KEY));
				r.setTableName(rs.getString(TABLE_NAME_KEY));
				r.setCourseId(rs.getInt(COURSE_ID_KEY));
				r.setCourseName(rs.getString(COURSE_NAME_KEY));

				String astr = rs.getString(APP_DATE_KEY);
				Calendar acal = Calendar.getInstance();
				acal.setTime(sdf.parse(astr));
				r.setAppDate(rs.getDate(APP_DATE_KEY).toString());
				r.setAppYy(acal.get(Calendar.YEAR));
				r.setAppMm(acal.get(Calendar.MONTH));
				r.setAppDd(acal.get(Calendar.DATE));
				r.setAppHh(acal.get(Calendar.HOUR_OF_DAY));
				r.setAppMi(acal.get(Calendar.MINUTE));

				list.add(r);
			}
		} catch (SQLException e) {
			System.out.println(pst);
			e.printStackTrace();
			isError = true;
		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (ParseException e) {
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

	public static Reserve getReserve(int rsvId) throws IdealException {
		String sql = "select rsv_id,usr_id,usr_name,rsv_date,person,table_id,table_name,c_id,c_name,app_date "
				+ " from reserve join user using(usr_id) join table_loc using(table_id) join course using(c_id) where "
				+ " rsv_id = ?";

		boolean isError = false;
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Reserve r = new Reserve();

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			con = ds.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, rsvId);
			rs = pst.executeQuery();
			if (rs.next()) {
				r.setRsvId(rs.getInt(RSV_ID_KEY));
				r.setUsrId(rs.getInt(USR_ID_KEY));
				r.setUsrName(rs.getString(USR_NAME_KEY));
				Calendar rcal = Calendar.getInstance();
				rcal.setTime(sdf.parse(rs.getString(RSV_DATE_KEY)));

				r.setRsvYy(rcal.get(Calendar.YEAR));
				r.setRsvMm(rcal.get(Calendar.MONTH) + 1);
				r.setRsvDd(rcal.get(Calendar.DATE));
				r.setRsvHh(rcal.get(Calendar.HOUR_OF_DAY));
				r.setRsvMi(rcal.get(Calendar.MINUTE));

				r.setPerson(rs.getInt(PERSON_KEY));
				r.setTableId(rs.getInt(TABLE_ID_KEY));
				r.setTableName(rs.getString(TABLE_NAME_KEY));
				r.setCourseId(rs.getInt(COURSE_ID_KEY));
				r.setCourseName(rs.getString(COURSE_NAME_KEY));

				Calendar acal = Calendar.getInstance();
				acal.setTime(rs.getDate(APP_DATE_KEY));
				r.setAppDate(rs.getDate(APP_DATE_KEY).toString());
				r.setAppYy(acal.get(Calendar.YEAR));
				r.setAppMm(acal.get(Calendar.MONTH));
				r.setAppDd(acal.get(Calendar.DATE));
				r.setAppHh(acal.get(Calendar.HOUR_OF_DAY));
				r.setAppMi(acal.get(Calendar.MINUTE));

			}
		} catch (SQLException e) {
			System.out.println(pst);
			e.printStackTrace();
			isError = true;
		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (ParseException e) {
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

		return r;
	}

	public static void reserveCourseChk(int cId) throws IdealException {
		String sql = "select * from reserve  where c_id = ? and rsv_date > ?";

		boolean isError = false;
		boolean isNoElement = false;
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, cId);
			pst.setDate(2, new java.sql.Date(Calendar.getInstance().getTime()
					.getTime()));
			rs = pst.executeQuery();
			isNoElement = !rs.next();
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
		if (!isNoElement) {
			throw new IdealException(IdealException.ERR_NO_NOT_RESERV_DELETE);
		}
	}

	public static TableLoc insertChk(String dateStr, int personNum)
			throws IdealException {

		// String sql =
		// "select table_id,table_name,max_capacity,rsv_date from reserve join table_loc using (table_id) "
		// + "where rsv_date between ? and ? and max_capacity > ?";

		String sql = "select table_id,table_name,max_capacity from table_loc left join reserve using(table_id) "
				+ " where  max_capacity >= ?  and table_id not in( select table_id from reserve where rsv_date between ? and ?  )"
				+ " group by table_id ";

		boolean isError = false;
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		TableLoc table = null;
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(dateStr));

			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, personNum);
			pst.setString(
					2,
					sdf.format(cal.getTime().getTime() - HOUR_TO_MILI_SEC * 3
							- MINUTE_TO_MILI_SEC));
			pst.setString(
					3,
					sdf.format(cal.getTime().getTime() + HOUR_TO_MILI_SEC * 3
							- MINUTE_TO_MILI_SEC));
			rs = pst.executeQuery();
			if (rs.next()) {
				table = new TableLoc();
				table.setTableId(rs.getInt(TABLE_ID_KEY));
				table.setTableName(rs.getString(TABLE_NAME_KEY));
				table.setMaxCapacity(rs.getInt(MAX_CAPACITY_KEY));
			}
			System.out.println(pst);

		} catch (SQLException e) {
			System.out.println(pst);
			e.printStackTrace();
			isError = true;
		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (ParseException e) {
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
		return table;
	}

	public static TableLoc updateChk(int rsvID, String dateStr, int personNum)
			throws IdealException {

		String sql = "select table_id,table_name,max_capacity from table_loc left join reserve using(table_id) "
				+ " where  max_capacity >= ?  and table_id not in"
				+ "( select table_id from reserve where rsv_date between ? and ? and rsv_id != ? )"
				+ " group by table_id ";

		boolean isError = false;
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		TableLoc table = null;
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(dateStr));

			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			con.setAutoCommit(false);
			pst = con.prepareStatement(sql);
			pst.setInt(1, personNum);
			pst.setString(
					2,
					sdf.format(cal.getTime().getTime()
							- (HOUR_TO_MILI_SEC * 3 - MINUTE_TO_MILI_SEC)));
			pst.setString(
					3,
					sdf.format(cal.getTime().getTime() + HOUR_TO_MILI_SEC * 3
							- MINUTE_TO_MILI_SEC));
			pst.setInt(4, rsvID);
			rs = pst.executeQuery();
			if (rs.next()) {
				table = new TableLoc();
				table.setTableId(rs.getInt(TABLE_ID_KEY));
				table.setTableName(rs.getString(TABLE_NAME_KEY));
				table.setMaxCapacity(rs.getInt(MAX_CAPACITY_KEY));
			}

		} catch (SQLException e) {
			System.out.println(pst);
			e.printStackTrace();
			isError = true;
		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (ParseException e) {
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
		return table;
	}

	public static Reserve insert(Reserve reserve) throws IdealException {
		String sql = "insert into reserve(usr_id,rsv_date,person,table_id,c_id) "
				+ "values (?,?,?,?,?)";

		boolean isError = false;
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Savepoint sv = null;

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			con.setAutoCommit(false);
			sv = con.setSavepoint();
			pst = con.prepareStatement(sql);
			pst.setInt(1, reserve.getUsrId());
			pst.setString(2, reserve.getRsvDateString());
			pst.setInt(3, reserve.getPerson());
			pst.setInt(4, reserve.getTableId());
			pst.setInt(5, reserve.getCourseId());
			pst.executeUpdate();
			pst.close();
			pst = con.prepareStatement("select last_insert_id()");
			rs = pst.executeQuery();
			if (rs.next()) {
				reserve.setRsvId(rs.getInt(1));
			}
		} catch (SQLException e) {
			if (sv != null) {
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
		return reserve;
	}

	public static Reserve update(Reserve reserve) throws IdealException {
		String sql = "update reserve set usr_id=?,rsv_date=?,person=?,table_id=?,c_id=? "
				+ " where rsv_id = ?";
		boolean isError = false;
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		Reserve r = null;
		Savepoint sv = null;

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			con.setAutoCommit(false);
			sv = con.setSavepoint();
			pst = con.prepareStatement(sql);
			pst.setInt(1, reserve.getUsrId());
			pst.setString(2, reserve.getRsvDateString());
			pst.setInt(3, reserve.getPerson());
			pst.setInt(4, reserve.getTableId());
			pst.setInt(5, reserve.getCourseId());
			pst.setInt(6, reserve.getRsvId());
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(pst);
			if (sv != null) {
				try {
					if (con != null) {
						con.rollback(sv);
					}
				} catch (SQLException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
			}
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

		r = getReserve(reserve.getRsvId());
		return r;
	}

	public static void delete(Reserve reserve) throws IdealException {
		String sql = "delete from reserve where rsv_id = ?";

		boolean isError = false;
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
			pst.setInt(1, reserve.getRsvId());
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(pst);
			if (sv != null) {
				try {
					if (con != null) {
						con.rollback(sv);
					}
				} catch (SQLException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
			}
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
	}
	public static ArrayList<Reserve> getAllReserveList() throws IdealException{

		String sql = "select rsv_id,usr_id,usr_name,rsv_date,person,table_id,table_name,c_id,c_name,app_date "
				+ " from reserve join user using(usr_id) join table_loc using(table_id) join course using(c_id)";
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Reserve> AllList = new ArrayList<Reserve>();
		boolean isError = false;

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			while(rs.next()){
				Reserve r = new Reserve();
				r.setRsvId(rs.getInt(RSV_ID_KEY));
				r.setUsrId(rs.getInt(USR_ID_KEY));
				r.setUsrName(rs.getString(USR_NAME_KEY));
				String rstr = rs.getString(RSV_DATE_KEY);
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
				Calendar rcal = Calendar.getInstance();
				rcal.setTime(sdf.parse(rstr));

				r.setRsvYy(rcal.get(Calendar.YEAR));
				r.setRsvMm(rcal.get(Calendar.MONTH) + 1);
				r.setRsvDd(rcal.get(Calendar.DATE));
				r.setRsvHh(rcal.get(Calendar.HOUR_OF_DAY));
				r.setRsvMi(rcal.get(Calendar.MINUTE));

				r.setPerson(rs.getInt(PERSON_KEY));
				r.setTableId(rs.getInt(TABLE_ID_KEY));
				r.setTableName(rs.getString(TABLE_NAME_KEY));
				r.setCourseId(rs.getInt(COURSE_ID_KEY));
				r.setCourseName(rs.getString(COURSE_NAME_KEY));

				String astr = rs.getString(APP_DATE_KEY);
				Calendar acal = Calendar.getInstance();
				acal.setTime(sdf.parse(astr));
				r.setAppDate(rs.getDate(APP_DATE_KEY).toString());
				r.setAppYy(acal.get(Calendar.YEAR));
				r.setAppMm(acal.get(Calendar.MONTH));
				r.setAppDd(acal.get(Calendar.DATE));
				r.setAppHh(acal.get(Calendar.HOUR_OF_DAY));
				r.setAppMi(acal.get(Calendar.MINUTE));

				AllList.add(r);
			}
			System.out.println(AllList.size());
		} catch (SQLException e) {
			System.out.println(pst);
			e.printStackTrace();
			isError = true;
		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (ParseException e) {
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
		return AllList;
	}
}
