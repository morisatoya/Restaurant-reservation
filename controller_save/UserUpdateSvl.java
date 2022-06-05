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
 * Servlet implementation class UserUpdateSvl
 */
@WebServlet("/UserUpdateSvl")
public class UserUpdateSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateSvl() {
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
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		String url = "home.jsp";
		String msg = "msg";//リクエストオブジェクトｍｓｇの名前。



		if(session.getAttribute("userInfo") == null){
			response.sendRedirect("home.jsp");
		}else{
			try{
			User user = (User)session.getAttribute("userInfo");//セッションからユーザー情報を受け取る
			request.setAttribute("user",User.getUser(user.getUsrId()));
			url = "userUpdate.jsp";
			}catch(IdealException e){
				request.setAttribute(msg,e.getMsg());
				System.out.println(e.getMsg());
				System.out.println("userUpdateSvl");
				url = "userIndex.jsp";
			}

		}//elseの
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);

	}

}
