package com.voidmain.fm.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.voidmain.fm.dao.AppDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username=request.getParameter("username").trim();

		if(AppDAO.isValidUser(username,request.getParameter("password").trim())!=0)
		{
			request.getSession().setAttribute("username",username);
			
			if(AppDAO.getUserType(username)==1)
			{
				response.sendRedirect("bankhome.jsp");
			}
			else
			{
				if(AppDAO.getUserType(username)==2)
				{
					response.sendRedirect("employeehome.jsp");
				}
				else
				{
					if(AppDAO.getUserType(username)==3)
					{
						response.sendRedirect("customerhome.jsp");
					}
				}
			}
		}
		else
		{
			response.sendRedirect("login.jsp?msgg=Invalid Username and Password");
		}
	}
}
