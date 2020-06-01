<%@page import="com.voidmain.fm.form.Plan"%>
<%@page import="com.voidmain.fm.form.Employee"%>
<%@page import="java.util.Iterator"%>
<%@page
	import="com.sun.xml.internal.bind.v2.runtime.reflect.ListIterator"%>
<%@page import="com.voidmain.fm.dao.AppDAO"%>
<%@page import="com.voidmain.fm.form.Loan"%>
<%@page import="java.util.List"%>

<!DOCTYPE HTML>
<html>

<head>
<title></title>
<meta name="description" content="website description" />
<meta name="keywords" content="website keywords, website keywords" />
<meta http-equiv="content-type"
	content="text/html; charset=windows-1252" />
<link rel="stylesheet" type="text/css"
	href="http://fonts.googleapis.com/css?family=Tangerine&amp;v1" />
<link rel="stylesheet" type="text/css"
	href="http://fonts.googleapis.com/css?family=Yanone+Kaffeesatz" />
<link rel="stylesheet" type="text/css" href="style/style.css" />

</head>

<body>
	<div id="main">
		<div id="header">
			<div id="logo">
				<div class="slogan">Loan Automation</div>
			</div>
			<div id="menubar">
				<ul id="menu">
					<!-- put class="current" in the li tag for the selected page - to highlight which page you're on -->
					<li><a class="current" href="employeehome.jsp">Home</a></li>
					<li class="hvr-sweep-to-bottom"><a href="logout.jsp">Logout</a></li>
				</ul>
			</div>
		</div>
		<div id="site_content">
			<div id="content">

				<%
					String status3 = request.getParameter("status");

					if (status3 != null) {
				%>
				<h1><%=status3%></h1>
				<%
					} else {
				%>
				<h1>View Loans</h1>
				<%
					}
				%>

				<table class="imagetable">
					<tr>
						<th>Loan Id</th>
						<th>Customer Name</th>
						<th>Plan ID</th>
					    <th>Update Status</th>

					</tr>
					<%
						List<Loan> loans = AppDAO.getLoans();

						Iterator<Loan> iterator = loans.iterator();

						while (iterator.hasNext()) {

							Loan loan = iterator.next();
							
							Plan plan=AppDAO.getPlanByID(Integer.parseInt(loan.getPlanId()));

							String status1 = "activated";
							String status2 = "waiting";
					%>
					<tr>
						<td><%=loan.getLid()%></td>
						<td><%=loan.getCustomername()%></td>
						<td><%=plan.getBankName()%>-<%=plan.getLoanType()%>-(<%=plan.getInterestRate()%>)</td>
						<%
							Employee emp=AppDAO.getEmployeeByName((String)request.getSession().getAttribute("username"));
						
							String type=emp.getType();
							
							if(type.equals("pickup"))
							{
						
						%>
						
						<td>
							<form action="UpdateLoanServlet" method="post">
								<input type="hidden" name="prid" value="<%=loan.getLid()%>">
								<select name="status">
									<option value="null">--select--</option>
									<option value="approved">Approved</option>
									<option value="pending">Pending</option>
									<option value="canceled">Canceled</option>
								</select>
								<input type="submit" value="Update">
							</form>
						</td>
						<%
							}
							else
							{
								if(type.equals("legal"))
								{
							
							%>
												
												
							<td>
								<form action="UpdateLoanServlet" method="post">
									<input type="hidden" name="prid" value="<%=loan.getLid()%>">
									<select name="status">
										<option value="null">--select--</option>
										<option value="approved">Approved</option>
										<option value="pending">Pending</option>
										<option value="canceled">Canceled</option>
									</select>
									<input type="submit" value="Update">
								</form>
							</td>
							<%
								}
								else
								{
									if(type.equals("verification"))
									{
								
								%>
								
								<td>
									<form action="UpdateLoanServlet" method="post">
										<input type="hidden" name="prid" value="<%=loan.getLid()%>">
										<select name="status">
											<option value="null">--select--</option>
											<option value="approved">Approved</option>
											<option value="pending">Pending</option>
											<option value="canceled">Canceled</option>
										</select>
										<input type="submit" value="Update">
									</form>
								</td>
								<%
									}
								}
							}
						%>
					</tr>
					<%
						}
					%>
				</table>
			</div>
		</div>
		<div id="footer">
			
		</div>
	</div>
</body>
</html>


