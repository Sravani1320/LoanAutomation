package com.voidmain.fm.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.voidmain.fm.dao.AppDAO;
import com.voidmain.fm.form.Plan;

@WebServlet("/AddPlanServlet")
public class AddPlanServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Plan plan=new Plan();
		
		plan.setBankName(request.getParameter("bankname"));
		plan.setLoanType(request.getParameter("loantype"));
		plan.setInterestRate(Float.parseFloat(request.getParameter("irate")));

		if(AppDAO.addPlan(plan)==1)
		{
			response.sendRedirect("viewbanks.jsp?status=success");
		}
		else
		{
			response.sendRedirect("viewbanks.jsp?status=User All Ready Exist");
		}
	}
}
