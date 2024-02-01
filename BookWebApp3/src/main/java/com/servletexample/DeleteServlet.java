package com.servletexample;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/deleteurl")
public class DeleteServlet extends HttpServlet {
	private static final String query="delete from bookdata where id=?";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get PrintWriterss
		PrintWriter pw= res.getWriter();
		//set content type
		res.setContentType("text/html");
		//get the id of record
		int id=Integer.parseInt(req.getParameter("id"));
		
		//Load jdbc driver
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		try(Connection con=DriverManager.getConnection("jdbc:mysql:///book", "root", "root");
				PreparedStatement ps=con.prepareStatement(query);){
			ps.setInt(1, id);
			
			int count=ps.executeUpdate();
			if(count==1) {
				pw.println("<h2>Record is deleted sucessfully</h2>");
			}else {
				pw.print("<h2>Record is not deleted sucessfully</h2>");
			}
			
			
			
		}catch(SQLException se) {
			se.printStackTrace();
			pw.println("<h1>"+se.getMessage()+"</h1>");
		}
		pw.println("<a href='Home.html'>Home</a>");
		pw.println("<br>");
		pw.println("<a href='bookList'>Book List</a>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
		
	}
  


}
