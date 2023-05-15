package com.nt.servlet;

//ErrorServlet.java (Dest servlet)


import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value="/errorurl",name = "error")
public class ErrorServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("ErrorServlet.doGet()");
	     //get PrintWrtier
		 PrintWriter pw=res.getWriter();
		 //set response content  type
		 res.setContentType("text/html");
		 //display  non-technical guiding message as the error message
		 pw.println("<h1 style='color:red;text-align:center'> Internal Problem --- Try Again  </h1>");
		 
		 //close the stream
		 pw.close();
		 
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("ErrorServlet.doPost()");
		doGet(req,res);
	}

}//class


