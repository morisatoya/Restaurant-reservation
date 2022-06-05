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
import model.Menu;
import model.MenuType;

/**
 * Servlet implementation class MenuInsertSvl
 */
@WebServlet("/MenuInsertSvl")
public class MenuInsertSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuInsertSvl() {
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
		request.setCharacterEncoding("utf-8");
		HttpSession session =  request.getSession();
		String url = null;


		if(session.getAttribute("adminInfo") == null){//セッション情報のnullテェック
			response.sendRedirect("home.jsp");
			return;
		}

		try{
			int typeId = Integer.parseInt(request.getParameter("typeId"));

			if(typeId == 100){//typeId == 100 コースの場合----------------------------

				//requestにtypeMenuListをセットする処理～～～～～～～～～～～～～
				//setするためのLIST
				ArrayList<ArrayList<Menu>> sendList = new ArrayList<ArrayList<Menu>>();

				//↓sendListにメニューリストを入れていく
				ArrayList<MenuType> typeList = MenuType.getAllType();
				for(int i = 0;i < typeList.size();i++){

					MenuType menutype = typeList.get(i);
					sendList.add(Menu.getMenu(menutype.getTypeId()));
				}
				request.setAttribute("typeMenuList", sendList);//～～～～～～～～
				url = "courseInsert.jsp";

			}else{//コースではない場合（t_idが100以外-----------------------------------
				request.setAttribute("mType",MenuType.getAllType());
				request.setAttribute("typeId",typeId);
				url = "menuInsert.jsp";
			}
		}catch(IdealException e){//例外処理
			request.setAttribute("msg", e.getMsg());
			url = "MenuMaintenanceSvl";
		}
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
		return;
	}

}
