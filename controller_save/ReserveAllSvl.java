package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.IdealException;
import model.Reserve;


/**
 * Servlet implementation class ReserveAllSvl
 */
@WebServlet("/ReserveAllSvl")
public class ReserveAllSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveAllSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("reserveAll.jsp");
		//HttpSession session = request.getSession(true);

		try {

				ArrayList<Reserve> reserveListAll = Reserve.getAllReserveList();
				for(int i = 0; i<reserveListAll.size();i++){
					System.out.println(i);
				}
				request.setAttribute("reserveListAll", reserveListAll);

		} catch (IdealException e) {
			request.setAttribute("msg", e.getMsg());
			System.out.println(e.getMsg());
			rd = request.getRequestDispatcher("adminIndex.jsp");
		}
		rd.forward(request, response);
	}

}
