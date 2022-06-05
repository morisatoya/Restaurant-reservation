package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Course;
import model.CourseCtl;
import model.IdealException;

/**
 * Servlet implementation class CourseOperationSvl
 */
@WebServlet("/CourseOperationSvl")
public class CourseOperationSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final int INSERT = 21;
	public static final int UPDATE = 22;
	public static final int DELETE = 23;
	public static final int[] COURSE_MENU_TYPE_ID = { 200, 210, 220, 300, 320,
			400 };

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CourseOperationSvl() {
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
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		String url = "home.jsp";

		// パラメータの入れ物
		int mode = 100;
		int typeId = 100;

		// 登録、変更、削除に使う配列やオブジェクト--
		Course course = new Course();
		ArrayList<CourseCtl> list = new ArrayList<CourseCtl>();

		String[] fullcourse = { "appetizerId", "soupId", "pastaId", "meatId",
				"fishId", "dessertId" };

		// 値やセッションのNULLチェック---------------------------------------
		if (session.getAttribute("adminInfo") == null) {
			response.sendRedirect("home.jsp");
		}
		if (request.getParameter("mode") != null) {
			mode = Integer.parseInt(request.getParameter("mode"));
		}
		if (request.getParameter("typeId") != null) {
			//System.out.println(request.getParameter("typeId"));
			// typeId = Integer.parseInt("typeId");
			typeId = 100;
		}

		try {
			switch (mode) {
			case INSERT:// ---------------------------------------INSERT
				// 1,パラメータをセット
				course.setCourseName(request.getParameter("courseName"));
				course.setDetail(request.getParameter("detail"));
				course.setOrderFlg(Integer.parseInt(request
						.getParameter("orderFlg")));
				course.setPrice(Integer.parseInt(request.getParameter("price")));
				course.setTypeId(typeId);
				// INSERTに必要な値は5つ。
				for (int i = 0; i < fullcourse.length; i++) {// 2,CourseCtlのリストにm_idをセット
					CourseCtl courseCtl = new CourseCtl();
					int menuId = Integer.parseInt(request
							.getParameter(fullcourse[i]));
					if (menuId != 0) {
						courseCtl.setM_Id(menuId);
						list.add(courseCtl);
					}
				}// insertだから、courseIdは必要ない。
				Course.updateCourse(course, mode, list);// 3,値を送り処理してもらう。

				// 遷移用の処理
				request.setAttribute("typeId", typeId);
				url = "MenuMaintenanceSvl";
				break;

			case UPDATE:// ---------------------------------------UPDATE
				// 1,パラメータをセット
				int courseId = Integer.parseInt(request.getParameter("courseId"));
				course.setCourseId(Integer.parseInt(request.getParameter("courseId")));
				course.setCourseName(request.getParameter("courseName"));
				course.setDetail(request.getParameter("detail"));
				course.setOrderFlg(Integer.parseInt(request.getParameter("orderFlg")));
				course.setPrice(Integer.parseInt(request.getParameter("price")));
				course.setTypeId(typeId);
				// UPDATEなので値が6つ必要
				for (int i = 0; i < fullcourse.length; i++) {// 2,CourseCtlのリストに値をセット
					CourseCtl courseCtl = new CourseCtl();
					int menuId = Integer.parseInt(request
							.getParameter(fullcourse[i]));
					if (menuId != 0) {
						courseCtl.setM_Id(menuId);
						courseCtl.setC_Id(course.getCourseId());
						list.add(courseCtl);
					}
				}
				Course.updateCourse(course, mode, list);// 3,値を送り処理してもらう。
				request.setAttribute("typeId", typeId);
				url = "MenuMaintenanceSvl";
				break;

			case DELETE:// ----------------------------------------DELETE
				courseId = Integer.parseInt(request.getParameter("courseId"));
				course.setCourseId(courseId);
				//System.out.println("________");
				Course.updateCourse(course, mode, list);
				url = "MenuMaintenanceSvl";
				break;
			}

		} catch (IdealException e) {// ---------------------------------例外
			request.setAttribute("msg", e.getMsg());
			url = "MenuMaintenanceSvl";
		}

		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}

}
