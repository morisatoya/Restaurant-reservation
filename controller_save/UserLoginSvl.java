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
import model.User;



/**
 * Servlet implementation class UserLoginSvl
 */
@WebServlet("/UserLoginSvl")
public class UserLoginSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginSvl() {
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

		String usrId = null;//intだと初期化する必要があったため、StringにusrIDをいれる。
		String password = null;
		String url = "home.jsp";
		String msg = "msg";


		//--------usrID と passwordをログイン画面(userLogin.jsp)から受け取る
		if(request.getParameter("usrId") != null){
			usrId = request.getParameter("usrId");

		}else{

		}
		if(request.getParameter("password") != null){
			password = request.getParameter("password");

		}//-----------------------------------------------------------------

		try{//jspで入力されたIDとパスでデータベースに問い合わせる。

			//存在していたらセッションオブジェクト(userInfo)にユーザー情報を入れる。
				session.setAttribute("userInfo", User.login(Integer.parseInt(usrId),password));
				url = "userIndex.jsp";     //顧客情報処理選択画面に遷移する。

		}catch(IdealException e){//例外処理
			request.setAttribute(msg, e.getMsg());//msgは"msg"という文字列。
			url = "userLogin.jsp";
		}finally{
			//画面遷移
			RequestDispatcher dis =	request.getRequestDispatcher(url);
			dis.forward(request, response);
		}
	}

}
