package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import model.bo.LoginBO;

import javax.servlet.http.*;

@WebServlet("/Sign_up")
public class Sign_up extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		String name	= request.getParameter("fullname");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (username == null || password == null) {
			response.sendRedirect("sign_up.jsp");
		} else {
			LoginBO.getInstance().signUp(name, email, username, password, "candidate");
			response.sendRedirect("index.jsp");
		}
	}

}
