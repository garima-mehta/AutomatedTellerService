<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Driver" %>
<%@page import="oracle.jdbc.driver.OracleDriver"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 	String url ="jdbc:oracle:thin:@localhost:1521:xe";
	String userName = "system";
	String password1 = "tiger";
	Connection conn = null;
	ResultSet rs = null; 
	PreparedStatement ps = null;
	try {
	Driver d = new  OracleDriver(); 
	DriverManager.registerDriver(d);
	conn = DriverManager.getConnection(url,userName, password1);
	}
catch(Exception e) {
		
	}
	int usid = Integer.parseInt(request.getParameter("uname"));
	HttpSession session1 = request.getSession();
	 session1.setAttribute("username", usid);
	String password = request.getParameter("psw");
	System.out.println(password);
	String query = "select password from User_Details where User_Id = ?";
	ps = conn.prepareStatement(query);
	ps.setInt(1,usid);
	rs = ps.executeQuery();
	RequestDispatcher rd ;
	if(rs.next())
	{
		if(rs.getString("password").equals(password)) {
		/* rd= request.getRequestDispatcher("index.jsp");
		rd.forward(request,response); */
		response.sendRedirect("index.jsp");
		}
		else {
			rd= request.getRequestDispatcher("login.html");
			rd.forward(request,response);
		}
	}
	else {
		rd= request.getRequestDispatcher("login.html");
		rd.forward(request,response);
	}
	rs.close();
	conn.close();
	
	
	

%>

</body>
</html>
