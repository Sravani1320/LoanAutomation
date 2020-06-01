package com.voidmain.fm.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.voidmain.fm.dao.AppDAO;
import com.voidmain.fm.form.Bank;

@WebServlet("/AddBankServlet")
public class AddBankServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Bank userForm=new Bank();
		
		String userName=request.getParameter("username");
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String mobile=request.getParameter("mobile");
		String address=request.getParameter("address");
		
		userForm.setUserName(userName);
		userForm.setAddress(address);
		userForm.setEmail(email);
		userForm.setMobile(mobile);
		userForm.setPassword(password);
		userForm.setName(name);

		if(AppDAO.addBank(userForm)==2)
		{
			response.sendRedirect("addbank.jsp?status=success");
		}
		else
		{
			response.sendRedirect("addbank.jsp?status=User All Ready Exist");
		}
	}
}
