package controller;

import java.io.IOException;
import java.time.LocalDateTime;

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
import model.User;

/**
 * Servlet implementation class ReserveInsertSvl
 */
@WebServlet("/ReserveInsertSvl")
public class ReserveInsertSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;



    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveInsertSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();

		if(session.getAttribute("userInfo") == null){
			response.sendRedirect("home.jsp");
			return;
		}

		User user = (User)session.getAttribute("userInfo");

		if(request.getAttribute("reserve") == null){
			// Reserveオブジェクトの生成
			Reserve reserve = new Reserve();

			// 日時設定
			LocalDateTime dt = LocalDateTime.now();
			reserve.setUsrId(user.getUsrId());
			reserve.getRsvDateString();
			reserve.setRsvYy(dt.getYear());
			reserve.setRsvMm(dt.getMonthValue());
			reserve.setRsvDd(dt.getDayOfMonth());
			reserve.setRsvHh(dt.getHour());
			reserve.setRsvMi(dt.getMinute());

			// 人数=1 テーブルID=1 コースID=1
			reserve.setPerson(1);
			reserve.setTableId(1);
			reserve.setCourseId(1);

			// reserveにセット
			request.setAttribute("reserve",reserve);
		}

		// courseListにオーダー可能なコースの一覧をセットして遷移する。
		try{
			request.setAttribute("courseList", Course.getCourseList());
			rd = request.getRequestDispatcher("reserveInsert.jsp");
		}catch(IdealException e){
			request.setAttribute("msg",e.getMsg());
			rd = request.getRequestDispatcher("ReserveListSvl");
		}

		rd.forward(request,response);
	}
}
