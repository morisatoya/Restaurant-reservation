package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AdminUser;
import model.IdealException;

/**
 * Servlet implementation class UserLoginSvl
 */
@WebServlet("/AdminUserLoginSvl")
public class AdminUserLoginSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUserLoginSvl() {
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
		response.setContentType("text/html;charset=UTF-8");

		String usrName = null;
		//int usrId = 0 ;
		String usrPhone = null;
		String url ="admin-userLogin.jsp";
		String msg = "msg";


			//--------usrPhone と usrName をログイン画面(admin-userLogin.jsp)から受け取る
			if(request.getParameter("usrPhone") != null){
				usrPhone =request.getParameter("usrPhone");
			}
			if(request.getParameter("usrName") != null){
				usrName = request.getParameter("usrName");
			}
			//else if(request.getParameter("usrPhone") != null){
			//	usrPhone = request.getParameter("usrPhone");
			//}
			//-----------------------------------------------------------------

			//jspで入力されたIDとパスでデータベースに問い合わせる。
		try{
			//存在していたらセッションオブジェクト(userInfo)にユーザー情報を入れる。
			session.setAttribute("userInfo", AdminUser.login(usrPhone,usrName));
			url = "admin-userIndex.jsp";     //顧客情報処理選択画面に遷移する。

		}catch(IdealException e){//例外処理
			request.setAttribute(msg, e.getMsg());//msgは"msg"という文字列。
			url = "admin-userLogin.jsp";
		}finally{
			//画面遷移
			RequestDispatcher dis =	request.getRequestDispatcher(url);
			dis.forward(request, response);
		}
	}

}
