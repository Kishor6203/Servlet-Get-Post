package org.Servlet.PostApp;

import java.io.*;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class FirstServlet extends HttpServlet
{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException 
	{
		//Fetch UI/Form Data
		String sid=req.getParameter("i");
		int id=Integer.parseInt(sid);
		String name=req.getParameter("nm");
		String dept=req.getParameter("dp");
		String sperc=req.getParameter("pr");
		double perc=Double.parseDouble(sperc);
		
		//presentation Logic //Servlet Tech
		PrintWriter out=resp.getWriter();
		      out.println("<html><body bgcolor='orange'>"
		    		  +"<h1>Student name is "+name+" form "+dept+"</h1>"
		    		  +"</body></html>");
		      out.close();
		      
		      //Presistance Logic //jdbc Tech
		      
		      Connection con=null;
		      PreparedStatement pstmt=null;
		      try {
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=admin");
						pstmt=con.prepareStatement("insert into btm.student1 values(?,?,?,?)"); //DML
						//Set the data for Place Holder
						pstmt.setInt(1, id);
						pstmt.setString(2, name);
						pstmt.setString(3, dept);
						pstmt.setDouble(4, perc);
						//Excute
						pstmt.executeUpdate();
						System.out.println("Record Inserted");
						} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		      finally {
		    	  if(pstmt!=null)
		    	  {
		    		  try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
		    	  }
		    	  if(con!=null)
		    	  {
		    		  try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
		    	  }
		      }
	}

}
