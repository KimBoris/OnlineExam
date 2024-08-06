<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teacher Grade</title>
</head>
<body>
<h1>${eno}. ${ename}</h1>
<c:forEach items="gradeList" var="grade">
    <a href="/teacher/detailgrade?e_no=${eno}&s.no=${grade.s_no}">${grade.s_name}</a>
    <span>${grade.totalScore}점</span>
</c:forEach>
</body>
</html>
