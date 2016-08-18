<%-- <%@page import="java.sql.Date"%> --%>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@page import = "txttohtml.*" %>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% String msg = request.getParameter("text");
	Date date = new Date();
	System.out.println(msg);
	String file = "C:\\Users\\Administrator\\workspace\\AutomatedTellerService\\WebContent\\log.html";
	FileWriter filewriter = new FileWriter(file, true);
    //fwrite($fp, "<div class='msgln'>(".ddate("g:i A").") <b>".$_SESSION['name']."</b>: ".stripslashes(htmlspecialchars($text))."<br></div>");
    filewriter.write("<div class='msgln'>("+date.toString()+")<b>"+Stringtohtmlstring.stringToHTMLString(msg)+"<br></div>");
    //System.out.println("<div class='msgln'>("+date.toString()+")<b>"+Stringtohtmlstring.stringToHTMLString(msg)+"<br></div>");
    filewriter.close();
    
    String filePath= "C:\\Users\\Administrator\\workspace\\AutomatedTellerService\\WebContent\\log.html";
    BufferedReader reader = new BufferedReader(new FileReader(filePath));
    //BufferedReader br = new InputStreamReader(new FileInputStream(txtFilePath));
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