package com.voidmain.fm.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.voidmain.fm.dao.AppDAO;
import com.voidmain.fm.form.Employee;

@WebServlet({"/UpdateLoanServlet"})

public class UpdateLoanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Employee emp=AppDAO.getEmployeeByName((String)request.getSession().getAttribute("username"));
		
		String type=emp.getType();
		
		if(type.equals("pickup"))
		{
			if(AppDAO.updateLoan1(Integer.parseInt(request.getParameter("prid")),request.getParameter("status"))==1)
			{
				response.sendRedirect("employeehome.jsp?status=success");
			}
			else
			{
				response.sendRedirect("employeehome.jsp?status=failed");
			}
		}
		else
		{
			if(type.equals("legal"))
			{
				if(AppDAO.updateLoan3(Integer.parseInt(request.getParameter("prid")),request.getParameter("status"))==1)
				{
					response.sendRedirect("employeehome.jsp?status=success");
				}
				else
				{
					response.sendRedirect("employeehome.jsp?status=failed");
				}
			}
			else
			{
				if(type.equals("verification"))
				{
					if(AppDAO.updateLoan2(Integer.parseInt(request.getParameter("prid")),request.getParameter("status"))==1)
					{
						response.sendRedirect("employeehome.jsp?status=success");
					}
					else
					{
						response.sendRedirect("employeehome.jsp?status=failed");
					}
				}
			}
		}
	}
}
