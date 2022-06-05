package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.IdealException;
import model.Menu;
import model.MenuType;

/**
 * Servlet implementation class MenuMaintenanceSvl
 */
@WebServlet("/MenuMaintenanceSvl")
public class MenuMaintenanceSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuMaintenanceSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		int typeId = 100;
		String url = "home.jsp";


		try {//nullチェックした後、mTypeとmenuをリクエストオブジェクトにセット--------
			if(session.getAttribute("adminInfo") == null){
				response.sendRedirect("home.jsp");
				return;
			}
			//typeIdの設定
			if(request.getParameter("typeId") != null){//typeIdがnullだったら初期値である100のまま
				typeId = Integer.parseInt(request.getParameter("typeId"));
			}

			request.setAttribute("mType",MenuType.getAllType());
			request.setAttribute("menu",Menu.getMenu(typeId));

			url = "menuMaintenance.jsp";
		} catch (IdealException e) {//例外---------------------------------------------
			request.setAttribute("msg", e.getMsg());
			e.printStackTrace();
			url = "adminIndex.jsp";
		}
		//遷移
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request,response);
		return;
	}

}
