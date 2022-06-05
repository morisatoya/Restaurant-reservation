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
import model.IdealException;
import model.Menu;
import model.MenuType;
/**
 * Servlet implementation class MenuUpdateSvl
 */
@WebServlet("/MenuUpdateSvl")
public class MenuUpdateSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuUpdateSvl() {
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
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session =  request.getSession();
		String url = null;


		if(session.getAttribute("adminInfo") == null){
			response.sendRedirect("home.jsp");
		}

		try{
			int typeId = Integer.parseInt(request.getParameter("typeId"));
			int menuId = Integer.parseInt(request.getParameter("menuId"));
			if(typeId == 100){//typeId == 100 コースの場合----------------------------
				//menuIdからコース内のメニューを取得
				 //順番を整えてセット
				  request.setAttribute("oneCourse",Course.getOneCourse(menuId));

				//全メニューを取得。
				ArrayList<ArrayList<Menu>> list2 = new ArrayList<ArrayList<Menu>>();
				ArrayList<MenuType> typeList = MenuType.getAllType();

				for(int i = 0; i < typeList.size(); i++){
					MenuType menutype = typeList.get(i);
					list2.add(Menu.getMenu(menutype.getTypeId()));
				}
				request.setAttribute("typeMenuList", list2);

				url = "courseUpdate.jsp";

			}else{//メニューの場合（t_idが100以外-----------------------------------
				request.setAttribute("mType",MenuType.getAllType());
				request.setAttribute("typeId",typeId);
				request.setAttribute("oneMenu",Menu.getOneMenu(menuId));
				url = "menuUpdate.jsp";
			}
		}catch(IdealException e){//例外処理
			request.setAttribute("msg", e.getMsg());
			url = "MenuMaintenanceSvl";
		}
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}

}
