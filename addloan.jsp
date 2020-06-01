<!DOCTYPE HTML>
<%@page import="java.util.Iterator"%>
<%@page import="com.voidmain.fm.dao.AppDAO"%>
<%@page import="com.voidmain.fm.form.Plan"%>
<%@page import="java.util.List"%>
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
				<!-- insert the page content here -->
				
				<%
					String status=request.getParameter("status");
							
					if(status!=null)
					{
				%>
						<h1><%=status%></h1>
				<%		
					}
					else
					{
				%>
					 <h1>Add Loan</h1>
				<%		
					}
				%>
			
			<form action="AddLoanServlet" name="ff" method="post"
				onsubmit="return check()">

				<div class="form_settings">
					
					<p>
						<span>Select Loan:</span>
						<select name="planId">
							<%
								List<Plan> plans=AppDAO.getPlans();
							
								Iterator<Plan> it=plans.iterator();
								
								while(it.hasNext())
								{
									Plan plan=it.next();
							%>
									<option value="<%=plan.getPlanId()%>"><%=plan.getBankName()%>-<%=plan.getLoanType()%>-(<%=plan.getInterestRate()%>)</option>
							<%		
									
								}
							%>
						</select>
					</p>
					
					<p style="padding-top: 15px">
						<span>&nbsp;</span><input class="submit" type="submit"
							name="contact_submitted" value="submit"/>
					</p>
					
				</div>
			</form>

		</div>
	</div>
	</div>
	<div id="footer">
			</div>
</body>
</html>
