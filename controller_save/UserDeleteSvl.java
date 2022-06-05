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
 * Servlet implementation class UserDeleteSvl
 */
@WebServlet("/UserDeleteSvl")
public class UserDeleteSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteSvl() {
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

		String url = "home.jsp";//なんの処理も行われなかったらhomeに戻るように
		String msg = "msg";

		if(session.getAttribute("userInfo") == null){
			url = "userDelete.jsp";
		}else{
			try{//本命の処理------------------------------------------
				User userinfo = (User)session.getAttribute("userInfo");
				request.setAttribute("user", User.getUser(userinfo.getUsrId()));
				url = "userDelete.jsp";
				//----------------------------------------------------
			}catch(IdealException e){//例外
				request.setAttribute(msg, e.getMsg());
				url = "userIndex.jsp";
			}
		}

		//画面遷移
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request,response);
	}

}
