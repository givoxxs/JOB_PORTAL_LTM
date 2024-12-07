package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.bean.Account;
import model.bo.LoginBO;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (username == null || password == null) {
			response.sendRedirect("login.jsp");
		} else {

			Account account = LoginBO.getInstance().checkAccount(username, password);
			if (account != null) {
				HttpSession session = request.getSession();
				session.setAttribute("account", account);
				response.sendRedirect("index.jsp");
			} else {
				response.sendRedirect("login.jsp");
			}
		}
	}

}
