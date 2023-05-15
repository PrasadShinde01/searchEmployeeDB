package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class EmployeeSearchServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=res.getWriter();
		// set response content type
		res.setContentType("text/html");
		
		// read form data
		int no=Integer.parseInt(req.getParameter("eno"));
		
		// get access to ServletContext object
		ServletContext sc=getServletContext();
		//read  context param values
		String driver=sc.getInitParameter("driver");
		String url=sc.getInitParameter("url");
		String user=sc.getInitParameter("dbuser");
		String pwd=sc.getInitParameter("dbpwd");
		
		// load jdbc driver class
		try {
			Class.forName(driver);
		
			//include header
			RequestDispatcher rd1=req.getRequestDispatcher("/header");
			rd1.include(req, res);
		
	
		try(
				Connection con=DriverManager.getConnection(url,user, pwd);
				PreparedStatement ps=con.prepareStatement("SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP WHERE EMPNO=?");
				){
			 //set values to query params
			   ps.setInt(1, no);
			   //execute the query
	   try(ResultSet rs=ps.executeQuery()){
			  //process the ResultSet object
			 if(rs.next()) {
				 pw.println("<table border='1' bgcolor='cyan' align='center'>");
				 pw.println("<tr><th>Empno </th><th>Ename </th><th>job </th><th>salary </th><th>deptno </th></tr>");
				 pw.println("<tr><td>"+rs.getInt(1)+" </td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getDouble(4) +"</td><td>"+rs.getInt(5) +"</td></tr>");
				 
				 pw.println("</table>");
			 }
			 else {
				 pw.println("<h1 style='color:red'>No records found </h1>");
				 
			 }
			//add home hyperlink
			 pw.println("<h1 style='text-align:center'><a href='input.html'>Home </a></h1>");
			 //get Access to ServletConfig object
			   ServletConfig cg=getServletConfig();
			 	 pw.println("<br> <b>dbuser init param value :: "+cg.getInitParameter("dbuser")+"</b>");
			
			 	//include header
					RequestDispatcher rd2=req.getRequestDispatcher("/footer.html");
					rd2.include(req, res);
			
	   }//try3
		}///try2
		}//try1
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("EmployeeSearchServlet.doGet()-- before rd.forward(-,-)");
			pw.println("<b> before rd.forward(-,-) </b>");
			/*RequestDispatcher rd=req.getRequestDispatcher("errorurl");
			rd.forward(req, res);*/
			/* ServletContext sc1=getServletContext();
			 RequestDispatcher rd=sc1.getRequestDispatcher("/errorurl");
			 rd.forward(req, res);*/
		
			ServletContext  sc1=getServletContext();
			RequestDispatcher  rd=sc1.getNamedDispatcher("error");
			rd.forward(req, res);
			System.out.println("EmployeeSearchServlet.doGet()-- after rd.forward(-,-)");
			pw.println("<b> after rd.forward(-,-) </b>");
		}
		
		
		
	}//method

    public  void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}//method

}//class
