<%--
  Created by IntelliJ IDEA.
  User: a
  Date: 2024-08-05
  Time: 오후 4:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<form action="/teacher/register" method="post" enctype="multipart/form-data">
    <label>Exam Name</label>
    <input name="e_name" type="text">
    <label>Excel</label>
    <input name="q_file" type="file">
    <button type="submit">SUBMIT</button>
</form>
<a href="/teacher/home">HOME</a>


</body>
</html>
