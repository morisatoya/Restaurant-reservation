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
 * Servlet implementation class UserOperationSvl
 */
@WebServlet("/UserOperationSvl")
public class UserOperationSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final int INSERT = 11;
	public static final int UPDATE = 12;
	public static final int DELETE = 13;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserOperationSvl() {
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

		//処理に使うもの
		int mode = 0;		   //受け取ったmodeの値で処理を分岐させる。
		String url = "home.jsp";        //urlを入れるための文字列 homeで初期化
		User user = new User(); //ユーザーの情報を入れるオブジェクト。
		User re = null;			//Userのメソッドからの引数を入れるオブジェクト。

		//例外に使うもの
		String msg = "msg";//例外処理のために使う文字列

		if(request.getParameter("mode") != null){//modeの有無を調べる
			mode = Integer.parseInt(request.getParameter("mode"));
		}
		if(mode == 0 && session.getAttribute("userInfo") != null){
			RequestDispatcher dis = request.getRequestDispatcher("userIndex.jsp");
			dis.forward(request, response);
			return;
		}
		if(mode != 11 && session.getAttribute("userInfo") == null){//modeがINSERT以外且つセッション
			response.sendRedirect("home.jsp"); //がnullの場合homeに遷移。
			return;
		}
		try{//---userオブジェクトにリクエストオブジェクトから渡された値を入れる---
			user.setUsrName(request.getParameter("usrName"));
			user.setPassword(request.getParameter("password"));
			user.setAddress(request.getParameter("address"));
			user.setPhone(request.getParameter("phone"));
			user.setMail(request.getParameter("mail"));
		}catch(Exception e){//～ もしnullだったときのために ～

		}

		switch(mode){//--- modeの値によって処理を変える。--------------------------

		case INSERT://-----登録処理---------
			try{
				if(session.getAttribute("session") != null){

					RequestDispatcher dis = request.getRequestDispatcher("userIndex.jsp");
					dis.forward(request, response);
				}
			 re = User.insert(user);//insertメソッドから帰ってきたUserオブジェクトをreに入れる。

			 //reのUsrIdをつかってgetUserメソッドを使い帰ってきた値をセッションに入れる。
			session.setAttribute("userInfo",User.getUser(re.getUsrId()));
			url = "userInsertCompletion.jsp"; //画面遷移

			}catch(IdealException e){	//例外処理
				request.setAttribute(msg,e.getMsg());
				url = "userInsert.jsp";//顧客登録画面に遷移
			}
			break;//以下大体同じ処理

		case UPDATE://------更新処理---------
			try{
				//update
				int passmode = Integer.parseInt(request.getParameter("passmode"));
				//
				user.setUsrId(Integer.parseInt(request.getParameter("usrId")));
			re = User.update(user,passmode);
			session.setAttribute("userInfo", User.getUser(re.getUsrId()));
			url = "userIndex.jsp";

			}catch(IdealException e){	//例外処理
				request.setAttribute(msg, e.getMsg());
				url = "UserUpdateSvl";
			}
			break;
		case DELETE://-----削除処理----------
			try{
				user.setUsrId(Integer.parseInt(request.getParameter("usrId")));
				User.delete(user);
				session.invalidate();
				System.out.println("last");
				url = "home.jsp";

			}catch(IdealException e){//例外処理
				request.setAttribute(msg, e.getMsg());
				url = "UserDeleteSvl";
			}
			break;
		}
		//--------------------画面遷移------------------------
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);


	}

}
