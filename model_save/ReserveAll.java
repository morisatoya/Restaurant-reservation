package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ReserveAll implements Serializable{
	public static final long serialVersionUID = 1L;

	private int allrsvId;
	//private int allusrId;
	private String allusrName;
	private int allrsvYy;
	private int allrsvMm;
	private int allrsvDd;
	private int allrsvHh;
	private int allrsvMi;
	private int allperson;
	private int alltableId;
	private String alltableName;
	private int allcourseId;
	private String allcourseName;
	private String allappDate;
	private int allappYy;
	private int allappMm;
	private int allappDd;
	private int allappHh;
	private int allappMi;

	private static final String ALL_RSV_ID_KEY = "rsv_id";
	//private static final String ALL_USR_ID_KEY = "usr_id";
	private static final String ALL_USR_NAME_KEY = "usr_name";
	private static final String ALL_RSV_DATE_KEY = "rsv_date";
	private static final String ALL_PERSON_KEY = "person";
	//private static final String ALL_TABLE_ID_KEY = "table_id";
	private static final String ALL_TABLE_NAME_KEY = "table_name";
	//private static final String ALL_COURSE_ID_KEY = "c_id";
	private static final String ALL_COURSE_NAME_KEY = "c_name";
	private static final String ALL_APP_DATE_KEY = "app_date";
	public static final String ALL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public ReserveAll(){
		super();
	}
	public int getAllrsvId() {
		return allrsvId;
	}

	//public int getAllusrId() {
	//	return allusrId;
	//}

	public String getAllusrName() {
		return allusrName;
	}

	public int getAllrsvYy() {
		return allrsvYy;
	}

	public int getAllrsvMm() {
		return allrsvMm;
	}

	public int getAllrsvDd() {
		return allrsvDd;
	}

	public int getAllrsvHh() {
		return allrsvHh;
	}

	public int getAllrsvMi() {
		return allrsvMi;
	}

	public int getAllperson() {
		return allperson;
	}

	public int getAlltableId() {
		return alltableId;
	}

	public String getAlltableName() {
		return alltableName;
	}

	public int getAllcourseId() {
		return allcourseId;
	}

	public String getAllcourseName() {
		return allcourseName;
	}

	public String getAllappDate() {
		return allappDate;
	}

	public int getAllappYy() {
		return allappYy;
	}

	public int getAllappMm() {
		return allappMm;
	}

	public int getAllappDd() {
		return allappDd;
	}

	public int getAllappHh() {
		return allappHh;
	}

	public int getAllappMi() {
		return allappMi;
	}

	public void setArsvId(int arsvId) {
		this.allrsvId = arsvId;
	}

	//public void setAusrId(int ausrId) {
	//	this.allusrId = ausrId;
	//}

	public void setAusrName(String ausrName) {
		this.allusrName = ausrName;
	}

	public void setArsvYy(int arsvYy) {
		this.allrsvYy = arsvYy;
	}

	public void setArsvMm(int arsvMm) {
		this.allrsvMm = arsvMm;
	}

	public void setArsvDd(int arsvDd) {
		this.allrsvDd = arsvDd;
	}

	public void setArsvHh(int arsvHh) {
		this.allrsvHh = arsvHh;
	}

	public void setArsvMi(int arsvMi) {
		this.allrsvMi = arsvMi;
	}

	public void setAperson(int aperson) {
		this.allperson = aperson;
	}

	public void setAtableId(int atableId) {
		this.alltableId = atableId;
	}

	public void setAtableName(String atableName) {
		this.alltableName = atableName;
	}

	public void setAcourseId(int acourseId) {
		this.allcourseId = acourseId;
	}

	public void setAcourseName(String acourseName) {
		this.allcourseName = acourseName;
	}

	public void setAlappDate(String alappDate) {
		this.allappDate = alappDate;
	}

	public void setAlappYy(int alappYy) {
		this.allappYy = alappYy;
	}

	public void setAlappMm(int alappMm) {
		this.allappMm = alappMm;
	}

	public void setAlappDd(int alappDd) {
		this.allappDd = alappDd;
	}

	public void setAlappHh(int alappHh) {
		this.allappHh = alappHh;
	}

	public void setAlappMi(int alappMi) {
		this.allappMi = alappMi;
	}

	public static ArrayList<ReserveAll> getAllReserveList() throws IdealException{

		String sql = "select rsv_id,usr_id,usr_name,rsv_date,person,table_id,table_name,c_id,c_name,app_date "
				+ " from reserve join user using(usr_id) join table_loc using(table_id) join course using(c_id)";
		InitialContext ic = null;
		DataSource ds = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<ReserveAll> AllList = new ArrayList<ReserveAll>();
		boolean isError = false;

		try {
			ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/mysql");
			con = ds.getConnection();
			pst = con.prepareStatement(sql);
			//pst.setInt(1, usrId);
			rs = pst.executeQuery();

			while (rs.next()) {
				ReserveAll ra = new ReserveAll();
// 予約番号と名前の取得
				ra.setArsvId(rs.getInt(ALL_RSV_ID_KEY));
				//r.setUsrId(rs.getInt(ALL_USR_ID_KEY));
				ra.setAusrName(rs.getString(ALL_USR_NAME_KEY));
// 予約日時の取得
				String rastr = rs.getString(ALL_RSV_DATE_KEY);
				SimpleDateFormat sdf = new SimpleDateFormat(ALL_DATE_FORMAT);
				Calendar racal = Calendar.getInstance();
				racal.setTime(sdf.parse(rastr));
				ra.setArsvYy(racal.get(Calendar.YEAR));
				ra.setArsvMm(racal.get(Calendar.MONTH) + 1);
				ra.setArsvDd(racal.get(Calendar.DATE));
				ra.setArsvHh(racal.get(Calendar.HOUR_OF_DAY));
				ra.setArsvMi(racal.get(Calendar.MINUTE));
// 人数 テーブル コース名の取得
				ra.setAperson(rs.getInt(ALL_PERSON_KEY));
				//ra.setAtableId(rs.getInt(ALL_TABLE_ID_KEY));
				ra.setAtableName(rs.getString(ALL_TABLE_NAME_KEY));
				//ra.setAcourseId(rs.getInt(ALL_COURSE_ID_KEY));
				ra.setAcourseName(rs.getString(ALL_COURSE_NAME_KEY));
// 登録日時の取得
				String astr = rs.getString(ALL_APP_DATE_KEY);
				Calendar acal = Calendar.getInstance();
				acal.setTime(sdf.parse(astr));
				ra.setAlappDate(rs.getDate(ALL_APP_DATE_KEY).toString());
				ra.setAlappYy(acal.get(Calendar.YEAR));
				ra.setAlappMm(acal.get(Calendar.MONTH));
				ra.setAlappDd(acal.get(Calendar.DATE));
				ra.setAlappHh(acal.get(Calendar.HOUR_OF_DAY));
				ra.setAlappMi(acal.get(Calendar.MINUTE));
// 全部 AllList にぶっこむ
				AllList.add(ra);
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
		if(isError){
			throw new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
		}
		return AllList;
	}
}
