<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>

<% String errMsg = (String) request.getAttribute("errorMsg");
    String signupMessage = (String) request.getAttribute("signupMessage");

%>


<h1><%= "ECGGH Welcomes You again on its portal! We are Upskilling" %>
</h1>
<br/>


<div>
    <h3>Login Form</h3>
    <form action="LoginServlet" method="post">

        <label for="username">USERNAME:</label>
        <input type="text" id="username" name="username" ><br>

        <label for="password">PASSWORD:</label>
        <input type="password" id="password" name="password" ><br><br>

        <input type="submit" value="LOGIN">

    </form>

    <%= errMsg%>

</div>

<div>
    <h3>Signup Form</h3>
    <form action="SignupServlet" method="post">

        <label for="username">USERNAME:</label>
        <input type="text" id="signupusername" name="username" ><br>

        <label for="password">PASSWORD:</label>
        <input type="password" id="signuppassword" name="password" ><br><br>

        <input type="submit" value="Signup">

    </form>

    <%= signupMessage%>

</div>


</body>
</html>