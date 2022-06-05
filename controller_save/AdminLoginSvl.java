package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Admin;
import model.IdealException;

/**
 * Servlet implementation class AdminLoginSvl
 */
@WebServlet("/AdminLoginSvl")
public class AdminLoginSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginSvl() {
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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text.html;utf-8");
		response.setHeader("Pragma","no-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);


		String admName = request.getParameter("admName");			// 管理者名を取得。
		String pw = request.getParameter("password");				// パスワードを取得。

		try{
			// DBの管理者テーブルより該当レコードを取得する。
			Admin adm = Admin.login(admName,pw);

			// 該当レコードが存在した時
			if(adm != null){
				// ログイン成功として、取得した管理者情報をセッション(adminInfo)に設定する。
				HttpSession session = request.getSession(true);
				session.setAttribute("adminInfo",adm);

			// 該当レコードが存在しない時
			}else{
				// 独自例外処理でスローする。
				throw new IdealException(IdealException.ERR_NO_ADMIN_EXCEPTION);
			}
			// ログインに成功したら「管理者処理画面」に遷移。
			response.sendRedirect("adminIndex.jsp");

		// 独自例外発生時
		}catch(IdealException e){
			HttpSession session = request.getSession(true);
			session.setAttribute("msg", e.getMsg());
			response.sendRedirect("adminLogin.jsp");				// 「管理者ログイン画面」に遷移。
		}
	}

}
