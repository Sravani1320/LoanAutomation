package com.voidmain.fm.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.voidmain.fm.dao.AppDAO;


@WebServlet("/UpdatePassword")
public class UpdatePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String username=(String)request.getSession().getAttribute("username");
		
		String oldpassword=request.getParameter("oldpassword");
		String newpassword=request.getParameter("newpassword");

		if(AppDAO.updatePassword(username, oldpassword,newpassword)==1)
		{
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
			request.getSession().invalidate();
			response.sendRedirect("login.jsp?status=please verify your login");
		}
	}
}
