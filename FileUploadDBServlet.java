package com.voidmain.fm.servlets;
import com.voidmain.fm.dao.MyConnection;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;



import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class FileUploadDBServlet
 */
@WebServlet("/FileUploadDBServlet")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class FileUploadDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
     
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // gets values of text fields
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
            
            
            PreparedStatement ps=MyConnection.getConnection().prepareStatement("insert into document values(?,?)");
            ps.setString(1, loanid);            
             
            if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
                ps.setBlob(2, inputStream);
            }
 
            // sends the statement to the database server
            int row = ps.executeUpdate();
            if (row > 0) {
            	response.sendRedirect("upload.jsp?status=success");
            }
            else {
            	response.sendRedirect("upload.jsp?status=failed");
            }
        } catch (SQLException ex) {            
            ex.printStackTrace();
        } 
    }
}