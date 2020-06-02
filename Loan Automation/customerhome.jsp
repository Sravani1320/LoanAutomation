<%@page import="com.voidmain.fm.form.Plan"%>
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
					<li><a class="current" href="customerhome.jsp">Home</a></li>
					<li><a class="current" href="viewmessages.jsp">View Messages</a></li>
					<li><a class="current" href="sendmessage.jsp">Send Message</a></li>
					<li><a class="current" href="addloan.jsp">Add Loan</a></li>
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
				<h1>View Loan status</h1>
				<%
					}
				%>
			
				<%
					String username=(String)request.getSession().getAttribute("username");
				%>
				<table class="imagetable">
					<tr>
						<th>Loan Id</th>
						<th>Pickup</th>
						<th>Verification</th>
						<th>Legal</th>
						<th>Plan ID</th>						
						<!-- <th>Delete</th>-->

					</tr>
					<%
						List<Loan> loans = AppDAO.getUserLoans(username);

						Iterator<Loan> iterator = loans.iterator();

						while (iterator.hasNext()) {

							Loan loan = iterator.next();
							
							Plan plan=AppDAO.getPlanByID(Integer.parseInt(loan.getPlanId()));

							String status1 = "activated";
							String status2 = "waiting";
					%>
					<tr>
						<td><%=loan.getLid()%></td>
						<td><%=loan.getStatus1()%></td>
						<td><%=loan.getStatus2()%></td>
						<td><%=loan.getStatus3()%></td>
						<td><%=plan.getBankName()%>-<%=plan.getLoanType()%>-(<%=plan.getInterestRate()%>)</td>						
						<!-- <td><a href="customerhome.jsp?loanid=<%=loan.getLid()%>">delete</a></td>-->
					</tr>
					<%
						}
					%>
				</table>
				
				<%
					String loanid=request.getParameter("loanid");
				
					if(loanid!=null)
					{
						int result=AppDAO.deleteLoan(Integer.parseInt(loanid));
						
						if(result==1)
						{
							response.sendRedirect("customerhome.jsp?status=success");
						}
						else
						{
							response.sendRedirect("customerhome.jsp?status=failed");
						}
					}
				%>
			</div>
		</div>
		<div id="footer">
			
		</div>
	</div>
</body>
</html>


