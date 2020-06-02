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
			
				<div class="slogan" font size="20">Loan Automation</font></div>
			</div>
			<div id="menubar">
				<ul id="menu">
					<!-- put class="current" in the li tag for the selected page - to highlight which page you're on -->

					<li><a class="current" href="index.jsp">Home</a></li>
					<li class="hvr-sweep-to-bottom"><a href="login.jsp">Login</a></li>
					<li class="hvr-sweep-to-bottom"><a href="addcustomers.jsp">Customer Registration</a></li>
					<li class="hvr-sweep-to-bottom"><a href="instructions.jsp">Instructions</a></li>
					
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
					 <h1>Please Follow the Instructions</h1>
				<%		
					}
				%>
				COMMON PROOFS APPLICABLE FOR EVERY LOAN</br>
	1.Proof of Identity (Any one): PAN/ Passport/ Driving License/ Voter ID card</br>
    2.Proof of Residence/ Address (Any one): Recent copy of Telephone Bill/ Electricity Bill/Water Bill/ Piped Gas Bill or copy of Passport/ Driving License/ Aadhar Card Life Insurance policy, bank statement</br>
				</br>LIST OF PAPERS/ DOCUMENTS APPLICABLE TO ALL APPLICANTS FOR HOME LOAN.</br>
    1.Employer Identity Card</br>
	2.Loan Application: Completed loan application form duly filled in affixed with 3 Passport size photographs</br>
	3.Property Papers</br> 
	4.Account Statement.</br>
	5.Income Proof for Salaried Applicant/ Co-applicant/ Guarantor.</br>
	6.Income Proof for Non-Salaried Applicant/ Co-applicant/ Guarantor.</br>
	</br>LIST OF PAPERS/ DOCUMENTS APPLICABLE TO ALL APPLICANTS FOR EDUCATION LOAN</br>
	1.Letter of admission</br>
	2.Duly filled and signed loan application form</br>
	2 recent passport size photographs</br>
	3.Statement of cost of study</br>
	4.Proof of identity and pr</br>
	5.IT returns or IT assessment order of previous two years of the co-borrower</br>
	6.Statement of assets and liabilities of parent/guardian</br>
	7.Proof of income of parent/guardian</br>
</br>LIST OF PAPERS/ DOCUMENTS APPLICABLE TO ALL APPLICANTS FOR CAR  LOAN</br>
	1.Proof of age</br>
	2.Application form</br>
	3.Passport size photograph</br>
	4.Income proof</br>
	5.Signature verification proof</br>
    6.Pro-forma Invoice or Rate List</br>
	</br>NOTE:  DOCUMENTS WILL BE COLLECTED MANUALLY BY THE EMPLOYEES OF THE PICKUP DEPARTMENT SO
MAKE SURE THAT YOU PROVIDE YOUR ADDRESS PROPERLY.

	

				
				
				
		</div>
	</div>
	</div>
	<div id="footer">
		
	</div>
</body>
</html>
