package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Course;
import model.IdealException;
import model.Reserve;
import model.TableLoc;

/**
 * Servlet implementation class ReserveOperationSvl
 */
@WebServlet("/ReserveOperationSvl")
public class ReserveOperationSvl extends HttpServlet {
	public static final int INSERT = 11;
	public static final int UPDATE = 12;
	public static final int DELETE = 13;

	private static final long serialVersionUID = 1L;

	public static boolean errorTest = false;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReserveOperationSvl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String url = "home.jsp";
		HttpSession session = request.getSession();
		Reserve reserve = new Reserve();
		try {
			reserve.setRsvId(Integer.parseInt(request.getParameter("rsvId")));
		} catch (NumberFormatException e) {
			reserve.setRsvId(0);
		}
		try {
			reserve.setRsvYy(Integer.parseInt(request.getParameter("rsvYy")));
		} catch (NumberFormatException e) {
			reserve.setRsvYy(0);
		}
		try {
			reserve.setRsvMm(Integer.parseInt(request.getParameter("rsvMm")));
		} catch (NumberFormatException e) {
			reserve.setRsvMm(0);
		}
		try {
			reserve.setRsvDd(Integer.parseInt(request.getParameter("rsvDd")));
		} catch (NumberFormatException e) {
			reserve.setRsvDd(0);
		}
		try {
			reserve.setRsvHh(Integer.parseInt(request.getParameter("rsvHh")));
		} catch (NumberFormatException e) {
			reserve.setRsvHh(0);
		}
		try {
			reserve.setRsvMi(Integer.parseInt(request.getParameter("rsvMi")));
		} catch (NumberFormatException e) {
			reserve.setRsvMi(0);
		}
		try {
			reserve.setUsrId(Integer.parseInt(request.getParameter("usrId")));
		} catch (NumberFormatException e) {
			reserve.setUsrId(0);
		}
		try {
			reserve.setPerson(Integer.parseInt(request.getParameter("person")));
		} catch (NumberFormatException e) {
			reserve.setPerson(0);
		}
		try {
			reserve.setCourseId(Integer.parseInt(request
					.getParameter("courseId")));
		} catch (NumberFormatException e) {
			reserve.setCourseId(0);
		}
		try {
			reserve.setTableId(Integer.parseInt(request.getParameter("tableId")));
		} catch (NumberFormatException e) {
			reserve.setTableId(0);
		}

		int mode = Integer.parseInt(request.getParameter("mode"));
		if (session.getAttribute("userInfo") == null && session.getAttribute("adminInfo") == null ) {
			url = "home.jsp";
		} else if (mode == INSERT) {
			try {
				if (errorTest) {
					throw new IdealException(
							IdealException.ERR_NO_ADMIN_EXCEPTION);
				}
				// 更新処理
				TableLoc table = Reserve.insertChk(reserve.getRsvDateString(),
						reserve.getPerson());
				// 登録可能なテーブルを探す
				if (table != null) {
					// ある場合はテーブル情報追加
					reserve.setTableId(table.getTableId());
					reserve.setTableName(table.getTableName());
					request.setAttribute("reserve", Reserve.insert(reserve));
					reserve.setCourseName(Course.getCourse(reserve.getCourseId()).getCourseName());
					url = "reserveCompletion.jsp";
				} else {
					// ない場合は例外スロー
					throw new IdealException(IdealException.ERR_NO_NOT_VACANCY);
				}
			} catch (IdealException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				request.setAttribute("msg", e.getMsg());
				url = "ReserveInsertSvl";
			}
		} else if (mode == UPDATE) {
			try {
				if (errorTest) {
					throw new IdealException(
							IdealException.ERR_NO_ADMIN_EXCEPTION);
				}
				TableLoc table = Reserve.updateChk(reserve.getRsvId(),
						reserve.getRsvDateString(), reserve.getPerson());
				if (table != null) {
					reserve.setTableId(table.getTableId());
					reserve.setTableName(table.getTableName());
					request.setAttribute("reserve", Reserve.update(reserve));
					if(session.getAttribute("adminInfo") != null)url = "ReserveAllSvl";
					else url = "ReserveListSvl";
				} else {
					throw new IdealException(IdealException.ERR_NO_NOT_VACANCY);
				}
			} catch (IdealException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				request.setAttribute("msg", e.getMsg());
				if(session.getAttribute("adminInfo") != null)url = "ReserveAllSvl";
				else url = "ReserveListSvl";
			}
		} else if (mode == DELETE) {
			try {
				if (errorTest) {
					throw new IdealException(
							IdealException.ERR_NO_ADMIN_EXCEPTION);
				}
				Reserve.delete(reserve);
				if(session.getAttribute("adminInfo") != null)url = "ReserveAllSvl";
				else url = "ReserveListSvl";
			} catch (IdealException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				request.setAttribute("msg", e.getMsg());
				url = "ReserveDeleteSvl";
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
