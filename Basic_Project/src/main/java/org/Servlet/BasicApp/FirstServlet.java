package org.Servlet.BasicApp;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.*;
public class FirstServlet extends GenericServlet
{

	@Override
	public void service(ServletRequest req, ServletResponse resp) 
			throws ServletException, IOException 
	{
		//Fetch Basic/App Data
		String name=req.getParameter("nm"); //Kishor
		String place=req.getParameter("pl"); //Delhi
		//Presentation Logic
		PrintWriter out=resp.getWriter();
		          out.println("<html><body bgcolor='orange'>" 
		           +"<h1>Student name is "+name+" form "+place+"</h1>"
		           + "</body></html>");
		          out.close();
	}

}
