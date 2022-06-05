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
import model.Menu;
import model.MenuType;

/**
 * Servlet implementation class ShowMenuSvl
 */
@WebServlet("/ShowMenuSvl")
public class ShowMenuSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowMenuSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		System.out.println("ポスト接続します");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		response.setContentType("text/html);chfarset=UTF-8");
		request.setCharacterEncoding("utf-8");
		RequestDispatcher rd = null;
		HttpSession userInfo = request.getSession();//セッション情報のuserInfoを取得
		int typeId = 100;
		String url = "";					//遷移先の初期
		//String msg = "msg";				//例外の処理を表示せさる
		if(request.getParameter("typeId") != null){
			typeId = Integer.parseInt(request.getParameter("typeId"));
		}
		try{
			//リクエストオブジェクトのコースリストにコース一覧をセットする
			//リクエストオブジェクトのメニューリストにメニュー一覧をセットする
			request.setAttribute("courseList", Course.getCourseList());
			request.setAttribute("menuList",Menu.getMenuList());
			request.setAttribute("mType", MenuType.getAllType());
			request.setAttribute("menu",Menu.getMenu(typeId));
			//遷移先にメニュー照会画面を指定
			url ="showMenu2.jsp";

		//独自例外が発生したとき
		}catch(IdealException e){
			request.setAttribute("msg", e.getMsg());
			if(userInfo != null){	//ログイン中なら
				response.sendRedirect("usrIndex.jsp");
				return;
			}else{
				response.sendRedirect("home.jsp");
				return;
			}
		}
		rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
