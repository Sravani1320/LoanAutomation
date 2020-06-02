<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.voidmain.fm.dao.MyConnection" %>
    <%@ page import="java.io.*" %>
    <%@ page import="javax.servlet.*" %>
    <%@ page import="java.sql.*" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%

String loanid = request.getParameter("loanid");        

InputStream inputStream = null; // input stream of the upload file
 
// obtains the upload file part in this multipart request
Part filePart = request.getPart("file");
if (filePart != null) {
    // prints out some information for debugging
    System.out.println(filePart.getName());
    System.out.println(filePart.getSize());
    System.out.println(filePart.getContentType());
     
    // obtains input stream of the upload file
    inputStream = filePart.getInputStream();
}        

 
try {            
    
    if(loanid!=null){
    PreparedStatement ps=MyConnection.getConnection().prepareStatement("insert into document values(?,?)");
    ps.setInt(1, Integer.parseInt(loanid));            
     
    if (inputStream != null) {
        // fetches input stream of the upload file for the blob column
        ps.setBlob(2, inputStream);
    }
    else{
    	%>
    	<p> inputstream is null</p>
    	<%
    }

    // sends the statement to the database server
    int row = ps.executeUpdate();
    if (row > 0) {
    	response.sendRedirect("upload.jsp?status=success");
    }
    else {
    	response.sendRedirect("upload.jsp?status=failed");
    }
    }
    else{
    	%>
    	<p>Null value<%=loanid %></p>
    	<%
    }
} catch (SQLException ex) {
    ex.printStackTrace();
} 
%>

</body>
</html>