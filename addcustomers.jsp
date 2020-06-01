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
					 <h1>Register Here</h1>
				<%		
					}
				%>
				
			<form action="AddUsersServlet" name="ff" method="post"
				onsubmit="return check()">

				<div class="form_settings">
					
                  <p> <span>User Name </span><input type="text" placeholder="enter user name" name="username" onkeypress="return check_alpha(event)" autocomplete="off" required>
				  <p id="username_error" style="color:red;align:center"></p></p>
				
					<p>
						<span>Password</span><input type="password" placeholder="enter password" name="password" autocomplete="off" onkeypress="CheckPasswordStrength(this.value)" required>
				<p id="password_error" style="color:red;align:center"></p></p>
				
				
						<p> <span>Email</span><input type="text" id="txtEmail" placeholder="enter email" name="email" onchange='Javascript:checkEmail();' autocomplete="off" required>
				  <p id="email_error" style="color:red;align:center"></p>
				
					</p>
			
					<p>
						<span>Mobile Number </span><input type="text" placeholder="enter mobile number" name="mobile" onkeypress="return isNumberKey(event)" maxlength="10"  autocomplete="off" required>
			
			<p id="mobile_error" style="color:red;align:center"></p></p>
				
						

					<p>
						<span>Address</span><input class="contact" type="text" placeholder="enter address" name="address" required/>
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
	</div>
	
	<div id="footer">
		
	</div>	
	<script type="text/javascript">  
			function check_alpha(event) 
			{
				    var keyCode = (event.which) ? event.which : event.keyCode;
					if ((keyCode< 65 ||keyCode > 90) && (keyCode < 97 || keyCode > 123) &&keyCode != 32)
					{
						document.getElementById("username_error").innerHTML="Only alphabets are allowed";
						return false;
					}
					else
					{
						document.getElementById("username_error").innerHTML="";
						return true;
					}
			}
			function isNumberKey(evt)
			{
					var charCode = (evt.which) ? evt.which : evt.keyCode
					if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57))
					{
						document.getElementById("mobile_error").innerHTML="Please enter valid number";  
						return false;
					}
					else
					{
						document.getElementById("mobile_error").innerHTML="";
					    return true;
					}
					
			}
			function checkpwd(evt)
			{
				var keyCode = (event.which) ? event.which : event.keyCode
				 if((keyCode>47 && keyCode<58) || (keyCode>64 && keyCode<91) || (keyCode>96 && keyCode<123))
				 {
					document.getElementById("password_error").innerHTML="";  
				 }
				 else
				 {
					document.getElementById("password_error").innerHTML="Only Alphanumeric is allowed";
					return false;
				 }
				 return true;
			}
			function CheckPasswordStrength(password) {
        var password_strength = document.getElementById("password_error");
 
        //TextBox left blank.
        if (password.length == 0) {
            password_strength.innerHTML = "";
            return;
        }
 
        //Regular Expressions.
        var regex = new Array();
        regex.push("[A-Z]"); //Uppercase Alphabet.
        regex.push("[a-z]"); //Lowercase Alphabet.
        regex.push("[0-9]"); //Digit.
        regex.push("[$@$!%*#?&]"); //Special Character.
 
        var passed = 0;
 
        //Validate for each Regular Expression.
        for (var i = 0; i < regex.length; i++) {
            if (new RegExp(regex[i]).test(password)) {
                passed++;
            }
        }
 
        //Validate for length of Password.
        if (passed > 2 && password.length > 8) {
            passed++;
        }
 
        //Display status.
        var color = "";
        var strength = "";
        switch (passed) {
            case 0:
            case 1:
                strength = "Weak";
                color = "red";
                break;
            case 2:
                strength = "Good";
                color = "darkorange";
                break;
            case 3:
            case 4:
                strength = "Strong";
                color = "green";
                break;
            case 5:
                strength = "Very Strong";
                color = "darkgreen";
                break;
        }
		if(password.length<7)
		{
			password_strength.innerHTML="password must atleast contain 8 characters. strength:"+strength;
			password_strength.style.color = color;
		}
		else{
		  password_strength.innerHTML = strength;
        password_strength.style.color = color;
		}
    }
			function checkEmail() {

			    var email = document.getElementById('txtEmail');
			    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;

			    if (!filter.test(email.value)) {
			    alert('Please provide a valid email address');
			    email.focus;
			    return false;
			 }
			}
			</script>	
	</body>
</html>
