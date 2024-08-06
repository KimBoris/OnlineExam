<%--
  Created by IntelliJ IDEA.
  User: a
  Date: 2024-08-05
  Time: 오후 4:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1> 반갑습니다. </h1>
<form action="/teacher/login" method="post">
    <div>
       아이디 <input name="t_id" type="text">
       비밀번호 <input name="t_pw" type="password">
        <button>Login</button>
    </div>
</form>


<%--<%

    System.out.println("야이놈들아"+session.getAttribute("t_name"));

    %>--%>

</body>
</html>
