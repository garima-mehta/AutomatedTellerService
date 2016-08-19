<%-- <%@page import="java.sql.Date"%> --%>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@page import = "txttohtml.*" %>
<%@page import = "txttohtml.*" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% String msg = request.getParameter("text");
	HttpSession session1 = request.getSession();
    String usid = session1.getAttribute("username").toString();
    int id_user = Integer.parseInt(usid);
	Parser p = new Parser();
    String result = p.parsing(msg,id_user);
	
    
	  
	Date date = new Date();
	System.out.println(msg);
	String file = "C:\\Users\\Administrator\\workspace\\AutomatedTellerService\\WebContent\\log.html";
	FileWriter filewriter = new FileWriter(file, true);
 
filewriter.write("<div class='msgln'>( ME:>"+date.toString()+")"+Stringtohtmlstring.stringToHTMLString(msg)+"<br></div>");
filewriter.write("<div class='msgln'>( SERVER:>"+date.toString()+")"+Stringtohtmlstring.stringToHTMLString(result)+"<br></div>");
    
    filewriter.close();
    
    String filePath= "C:\\Users\\Administrator\\workspace\\AutomatedTellerService\\WebContent\\log.html";
    BufferedReader reader = new BufferedReader(new FileReader(filePath));
   
    StringBuilder sb = new StringBuilder();
    String line;

    while((line = reader.readLine())!= null){
        sb.append(line+"\n");
    }
    out.println(sb.toString());

    reader.close();
%>
</body>
</html>