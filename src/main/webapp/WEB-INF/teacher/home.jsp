<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teacher Home</title>
</head>
<body>
<h1>강사님, 환영합니다.</h1>
<c:forEach items="${examList}" var="exam">
    <div>
        <div>${exam.e_no} ${exam.e_name}</div>
        <form action="/teacher/grade" method="post">
            <input type="text" name="e_no" value="${exam.e_no}">
        </form>
    </div>
</c:forEach>
</body>
</html>