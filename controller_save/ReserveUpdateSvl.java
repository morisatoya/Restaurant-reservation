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

/**
 * Servlet implementation class ReserveUpdateSvl
 */
@WebServlet("/ReserveUpdateSvl")
public class ReserveUpdateSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReserveUpdateSvl() {
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
		this.doPost(request, response);
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
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		boolean adm = false;
		if(session.getAttribute("adminInfo") != null){
			request.setAttribute("usrId",Integer.parseInt(request.getParameter("usrId")));
			adm = true;
		}
		if (session.getAttribute("userInfo") == null && adm == false) {
			response.sendRedirect("home.jsp");
			return;
		}
		// User user = (User) session.getAttribute("userInfo");
		try {
			int rsvId = Integer.parseInt(request.getParameter("rsvId"));
			request.setAttribute("courseList", Course.getCourseList());
			request.setAttribute("reserve", Reserve.getReserve(rsvId));
			rd = request.getRequestDispatcher("reserveUpdate.jsp");
		} catch (NumberFormatException e) {
			request.setAttribute("msg", "予約IDが不正です");
			if(adm == true)rd = request.getRequestDispatcher("ReserveAllSvl");
			else rd = request.getRequestDispatcher("ReserveListSvl");
		} catch (IdealException e) {
			request.setAttribute("msg", e.getMsg());
			System.out.println(e.getMsg());
			if(adm == true)rd = request.getRequestDispatcher("ReserveAllSvl");
			else rd = request.getRequestDispatcher("ReserveListSvl");
		}
		rd.forward(request, response);
	}
}
