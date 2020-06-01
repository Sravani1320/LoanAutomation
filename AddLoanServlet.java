package com.voidmain.fm.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.voidmain.fm.dao.AppDAO;
import com.voidmain.fm.form.Loan;

@WebServlet("/AddLoanServlet")
public class AddLoanServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Loan loan=new Loan();

		loan.setPlanId(request.getParameter("planId"));
		loan.setDocument("");
		loan.setCustomername((String)request.getSession().getAttribute("username"));
		loan.setBankname("");
		loan.setStatus1("pending");
		loan.setStatus2("pending");
		loan.setStatus3("pending");

		if(AppDAO.addLoan(loan)==1)
		{
			response.sendRedirect("addloan.jsp?status=success");
		}
		else
		{
			response.sendRedirect("addloan.jsp?status=failed");
		}
	}
}
