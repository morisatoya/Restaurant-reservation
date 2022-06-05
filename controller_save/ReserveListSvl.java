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

import model.IdealException;
import model.Reserve;
import model.User;

/**
 * Servlet implementation class ReserveListSvl
 */
@WebServlet("/ReserveListSvl")
public class ReserveListSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReserveListSvl() {
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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;utf-8");

		RequestDispatcher rd = request.getRequestDispatcher("reserveList.jsp");

		// userがnull時
		try {
			HttpSession session = request.getSession(true);
			User user = null;
			int usrId;
			if (session.getAttribute("userInfo") == null) {
				response.sendRedirect("home.jsp");
				return;
			} else {
				user = (User) session.getAttribute("userInfo");
				usrId = user.getUsrId();
				ArrayList<Reserve> reserveList = Reserve.getReserveList(usrId);
				request.setAttribute("reserveList", reserveList);
			}
		} catch (IdealException e) {
			request.setAttribute("msg", e.getMsg());
			System.out.println(e.getMsg());
			rd = request.getRequestDispatcher("userIndex.jsp");
		}
		rd.forward(request, response);
	}
}
