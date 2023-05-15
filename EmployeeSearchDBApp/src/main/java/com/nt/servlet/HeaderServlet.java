package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/header")
public class HeaderServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	   //get PrintWriter object
		 PrintWriter pw=res.getWriter();
		 //set response content type
		 res.setContentType("text/html");
		// place header logic
		  pw.println("<marquee behaviour='slide'><h1 style='color:red;text-align:center'> www. N A R E S H IT .com </h1> </marquee> <br><br><br><hr>");
		  
		  //do not close the stream becoz its commits output to the response
		  //pw.close();
	}//doGet(-,-)
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}//doPost(-,-)

}//class

