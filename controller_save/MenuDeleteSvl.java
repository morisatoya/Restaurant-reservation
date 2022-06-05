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
import model.Menu;
import model.MenuType;
import model.Reserve;

/**
 * Servlet implementation class MenuDeleteSvl
 */
@WebServlet("/MenuDeleteSvl")
public class MenuDeleteSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuDeleteSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("ポスト接続します");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		HttpSession adminInfo = request.getSession();		//adminInfoの取得
		int typeId = 0;										//宣言
		int menuId = 0;
		String url = "";

		if(adminInfo == null){
			response.sendRedirect("home.jsp");
			return;
		}else{
			try{
				typeId = Integer.parseInt(request.getParameter("typeId"));
				menuId = Integer.parseInt(request.getParameter("menuId"));

				if(typeId == 100){							//コースを削除しようとしているのでcourseDeleteに処理を移行

					Reserve.reserveCourseChk(menuId);		//Idが存在するならエラーを投げてページ遷移

					ArrayList<ArrayList<Menu>> list = new ArrayList<ArrayList<Menu>>();
					ArrayList<MenuType> typeList = MenuType.getAllType();

					for(int i = 0; i < typeList.size(); i++){
						MenuType menutype = typeList.get(i);
						list.add(Menu.getMenu(menutype.getTypeId()));
					}
					request.setAttribute("typeMenuList", list);
					request.setAttribute("oneCourse", Course.getOneCourse(menuId));
					url = "courseDelete.jsp";

				}else{								//メニューを削除しようとしているのでmenuDeleteに処理を移行
					CourseCtl.CourseMenuChk(menuId);
					request.setAttribute("oneMenu", Menu.getOneMenu(menuId));
					url = "menuDelete.jsp";
				}
			}catch(ArrayIndexOutOfBoundsException e){
				e.printStackTrace();
			}catch(IdealException e){
				e.printStackTrace();
				request.setAttribute("msg", e.getMsg());
				url = "MenuMaintenanceSvl";
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		return;
	}

}
