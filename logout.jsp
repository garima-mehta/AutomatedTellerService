<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.io.*"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% String file = "C:\\Users\\Administrator\\workspace\\AutomatedTellerService\\WebContent\\log.html";
PrintWriter pw = new PrintWriter(file);
pw.print(" ");
pw.print("Hello, Welcome to Verizon Chat box! Please Input your query!!!");
pw.close();

response.sendRedirect("login.html");
%>


</body>
</html>