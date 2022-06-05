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

/**
 * Servlet implementation class MenuOperationSvl
 */
@WebServlet("/MenuOperationSvl")
public class MenuOperationSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final int INSERT = 11;
	public static final int UPDATE = 12;
	public static final int DELETE = 13;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuOperationSvl() {
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
		response.setContentType("text/html);chfarset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession adminInfo = request.getSession();		//セッション情報のadminを取得
		RequestDispatcher rd = null;
		String url = "";				//遷移先の初期値にホーム画面を指定
		//String msg = "";				//例外の処理を表示する
		Menu menu = new Menu();			//menuオブジェクトの生成
		int mode = Integer.parseInt(request.getParameter("mode"));		//リクエストパラメーターのmodeの受け取り

		//メニュー情報を受け取り例外発生時"０"を設定
		try{
			if(adminInfo == null){
				url = "home.jsp";
				return;
			//メニュー情報の受け取り
			}else{


					menu.setMenuName(request.getParameter("menuName"));
					menu.setDetail(request.getParameter("detail"));
					menu.setOrderFlg(Integer.parseInt(request.getParameter("orderFlg")));
					menu.setPrice(Integer.parseInt(request.getParameter("price")));
					int typeId = Integer.parseInt(request.getParameter("typeId"));
					menu.setTypeId(typeId);
					if(request.getParameter("menuId") != null){
					menu.setMenuId(Integer.parseInt(request.getParameter("menuId")));
					}
				//

					Menu.updateMenu(menu, mode);

				request.setAttribute("typeId", Menu.getMenu(typeId));
				url ="MenuMaintenanceSvl";
			}
		}catch(IdealException e){
			request.setAttribute("msg", e.getMsg());
			request.setAttribute("oneMenu", menu);
			try{
				if(mode == INSERT){
					url = "MenuInsertSvl";
				}else if(mode == UPDATE){
					url = "MenuUpdateSvl";
				}else if(mode == DELETE){
					url = "MenuDeleteSvl";				}
			}catch(Exception ee){
				ee.printStackTrace();
			}
		}
		rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		return;
	}

}
