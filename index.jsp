<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0" Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Chat - Customer Module</title>
<link type="text/css" rel="stylesheet" href="style.css" />
</head>
<body>
<div id="wrapper">
    <div id="menu">
        <p class="welcome">Welcome, <b></b></p>
        <p class="logout"><a id="exit" href="#">Exit Chat</a></p>
        <div style="clear:both"></div>
    </div>
     
    <div id="chatbox">
    <%
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
    
    </div>
     
    <form name="message" action="">
        <input name="usermsg" type="text" id="usermsg" size="63" />
        <input name="submitmsg" type="submit"  id="submitmsg" value="Send"  />
    </form>
</div>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"></script>
<script type="text/javascript">
	
// jQuery Document
	$(document).ready(function(){
		//If user wants to end session
		$("#exit").click(function(){
			var exit = confirm("Are you sure you want to end the session?");
			if(exit==true){window.location = 'logout.jsp?logout=true';}		
		});
	});
	
	$("#submitmsg").click(function(){	
		var clientmsg = $("#usermsg").val();
		//$.post("post.jsp", {text: clientmsg});
		
		$.post("post.jsp", {text: clientmsg}, function(data, status){
			$("#chatbox").html(data);
    });
		
		$("#usermsg").attr("value", "");
		
		
		return false;
	});
	
	/* function timedRefresh(timeoutPeriod) {
		setTimeout("location.reload(true);",timeoutPeriod);
	}
	
	window.onload = timedRefresh(7000);
 */	
    //setTimeout(refresh, 1000);

	
	</script>
</body>
</html>