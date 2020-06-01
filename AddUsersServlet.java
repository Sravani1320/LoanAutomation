package com.voidmain.fm.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.voidmain.fm.dao.AppDAO;
import com.voidmain.fm.form.Customer;

@WebServlet("/AddUsersServlet")
public class AddUsersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Customer userForm=new Customer();
		
		String userName=request.getParameter("username");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String mobile=request.getParameter("mobile");
		String address=request.getParameter("address");
		
		userForm.setUserName(userName);
		userForm.setAddress(address);
		userForm.setEmail(email);
		userForm.setMobile(mobile);
		userForm.setPassword(password);

		if(AppDAO.addCustomer(userForm)==2)
		{
			response.sendRedirect("addcustomers.jsp?status=success");
		}
		else
		{
			response.sendRedirect("addcustomers.jsp?status=User All Ready Exist");
		}
	}
}
