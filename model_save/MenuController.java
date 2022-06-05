package model;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MenuController
 */
@WebServlet("/chap06/MenuController")
public class MenuController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public final static int TO_TOP = 0;
	public final static int TO_INSERT = 1;
	public final static int TO_UPDATE = 2;
	public final static int TO_DELETE = 3;
	public final static int INSERT = 11;
	public final static int UPDATE = 12;
	public final static int DELETE = 13;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MenuController() {
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
		int typeID;
		int menuID;
		int mode;
		Menu menu = new Menu();
		String url = null;

		if (request.getParameter("typeID") != null) {//
			typeID = Integer.parseInt(request.getParameter("typeID"));
			menu.setTypeId(typeID);
		} else {
			typeID = 100;
		}
		if (request.getParameter("mode") != null) {
			mode = Integer.parseInt(request.getParameter("mode"));
		} else {
			mode = TO_TOP;
		}

		//System.out.println(typeID);
		//System.out.println(mode);


		try {
			request.setAttribute("mType", MenuType.getAllType());

			switch (mode) {
			case TO_TOP:
				request.setAttribute("menu", Menu.getMenu(typeID));
				url = "menuMaintenance.jsp";
				break;
			case TO_INSERT:
				url = "insertMenu.jsp";
				break;
			case TO_UPDATE:
				menuID = Integer.parseInt(request.getParameter("menuID"));
				request.setAttribute("oneMenu", Menu.getOneMenu(menuID, typeID));
				url = "updateMenu.jsp";
				break;
			case TO_DELETE:
				menuID = Integer.parseInt(request.getParameter("menuID"));
				request.setAttribute("oneMenu", Menu.getOneMenu(menuID, typeID));
				url = "deleteMenu.jsp";
				break;
			case INSERT:

				menu.setMenuName(request.getParameter("menuName"));
				menu.setDetail(request.getParameter("detail"));
				menu.setOrderFlg(Integer.parseInt(request.getParameter("orderFlg")));
				menu.setPrice(Integer.parseInt(request.getParameter("price")));
				//menu.setTypeID(typeID);
				Menu.updateMenu(menu, mode);
				request.setAttribute("menu", Menu.getMenu(typeID));
				url = "menuMaintenance.jsp";
				break;
			case UPDATE:

				//セット↓
				menu.setMenuName(request.getParameter("menuName"));
				menu.setDetail(request.getParameter("detail"));
				menu.setOrderFlg(Integer.parseInt(request.getParameter("orderFlg")));
				menu.setPrice(Integer.parseInt(request.getParameter("price")));
				//menu.setTypeID(typeID);
				menuID = Integer.parseInt(request.getParameter("menuID"));
				menu.setMenuId(menuID);
				//セット↑
				Menu.updateMenu(menu, mode);
				request.setAttribute("menu", Menu.getMenu(typeID));
				url = "menuMaintenance.jsp";
				System.out.println(menu.getMenuId() + menu.getMenuName() + menu.getOrderFlg()
						+			menu.getPrice());
				break;
			case DELETE:

				menu.setMenuId(Integer.parseInt(request.getParameter("menuID")));
				//menu.setTypeID(typeID);
				Menu.updateMenu(menu, mode);

				request.setAttribute("menu", Menu.getMenu(typeID));
				url = "menuMaintenance.jsp";

				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			url = "menuMaintenance.jsp";
			System.out.println("MenuControllerエラー");
		}

		RequestDispatcher disp = request.getRequestDispatcher(url);
		disp.forward(request, response);
	}

}

