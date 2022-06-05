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
import model.User;

/**
 * Servlet implementation class ReserveListSort
 */
@WebServlet("/ReserveListSortSvl")
public class ReserveListSortSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final int SORT_BY_ID = 10;
	public static final int SORT_BY_COURSE_NAME = 11;
	public static final int SORT_BY_RSV_DATE = 12;
	public static final int SORT_BY_APP_DATE = 13;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveListSortSvl() {
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
		int sortMode = 0;
		int usrId = 0;
		if(request.getSession().getAttribute("userInfo") ==null){
			response.sendRedirect("home.jsp");
			return;
		}
		usrId = ((User)request.getSession().getAttribute("userInfo")).getUsrId();
		try{
			sortMode =Integer.parseInt(request.getParameter("sortMode"));
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		ArrayList<Reserve> list = new ArrayList<>();
		try {
			list = Reserve.getReserveList(usrId);
		} catch (IdealException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			request.setAttribute("msg", e.getMsg());
		}
		if(sortMode == SORT_BY_ID){
			list.sort((e1,e2)->(e1.getRsvId()-e2.getRsvId()));
		} else if(sortMode == SORT_BY_COURSE_NAME){
			list.sort((e1,e2)->(e1.getCourseName().compareTo(e2.getCourseName())));
		} else if(sortMode == SORT_BY_APP_DATE){
			list.sort((e1,e2)->{
				if(e1.getAppYy()-e2.getAppYy()!=0){
					return e1.getAppYy()-e2.getAppYy();
				}
				if(e1.getAppMm()-e2.getAppMm()!=0){
					return e1.getAppMm()-e2.getAppMm();
				}
				if(e1.getAppDd()-e2.getAppDd()!=0){
					return e1.getAppDd()-e2.getAppDd();
				}
				if(e1.getAppHh()-e2.getAppHh()!=0){
					return e1.getAppHh()-e2.getAppHh();
				}
				if(e1.getAppMi()-e2.getAppMi()!=0){
					return e1.getAppMi()-e2.getAppMi();
				}
				return 0;
			});
		}
		request.setAttribute("reserveList",list);
		RequestDispatcher rd = request.getRequestDispatcher("reserveListSort.jsp");
		rd.forward(request, response);
	}

}
