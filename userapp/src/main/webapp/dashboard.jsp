<%--
  Created by IntelliJ IDEA.
  User: Prateek
  Date: 19-09-2022
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>

<% String username = (String)request.getAttribute("username");
   String profileUpdateMessage = (String)request.getAttribute("profileUpdateMessage");
%>

<div>
    <form action="LogoutServlet" method="get">

        <input type="submit" value="LOGOUT">
    </form>

</div>

<h1>Welcome <%= username%></h1>

<div>
    <form action="UpdateProfileServlet" method="post">

        <label for="fullname">Full Name:</label>
        <input type="text" id="fullname" name="fullname" ><br>

        <label for="phonenumber">PhoneNumber:</label>
        <input type="text" id="phonenumber" name="phonenumber" ><br><br>

        <label for="email">Email:</label>
        <input type="text" id="email" name="email" ><br><br>

        <input type="submit" value="SAVE">

    </form>


    <%= profileUpdateMessage%>


</div>

</body>
</html>
