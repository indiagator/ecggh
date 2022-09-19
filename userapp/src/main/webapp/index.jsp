<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "ECGGH Welcomes You again on its portal! We are Upskilling" %>
</h1>
<br/>


<div>
    <form action="LoginServlet" method="post">

        <label for="username">USERNAME:</label>
        <input type="text" id="username" name="username" ><br>

        <label for="password">PASSWORD:</label>
        <input type="password" id="password" name="password" ><br><br>

        <input type="submit" value="LOGIN">

    </form>

</div>


</body>
</html>