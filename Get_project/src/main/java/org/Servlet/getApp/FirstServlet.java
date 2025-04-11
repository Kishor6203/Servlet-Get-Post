package org.Servlet.getApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// Fetch UI/ Form Data
		String sid = req.getParameter("i"); // "1"
		int id = Integer.parseInt(sid); //1
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=admin");
			pstmt = con.prepareStatement("select * from btm.student where id = ?");
			
			// Persistence logic
			pstmt.setInt(1, id);
			
			// Execute the sql query
			rs = pstmt.executeQuery();
			
			// Presentation logic
			PrintWriter out = resp.getWriter();
			if(rs.next())
			{
				String nm = rs.getString("Name"); // Abc
//				String dp = rs.getString("Dept"); // EACE
				double pr = rs.getDouble("Perc"); // 79.80
				
				out.println("<html> <body bgcolor='silver'>" 
						+ "<h1> Student name is " + nm + "</h1>" 
						+ "<h1> Percentage is " + pr
					    + "</body> </html");
			}
			else 
			{
				out.println("<html> <body bgcolor='red'>"
						+ "<h1> No Data Found" + "</h1>"
						+ "</body> </html>");
			}
			out.close();
			
	} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			if(rs != null)
			{
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null)
			{
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null)
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

