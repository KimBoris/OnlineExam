<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teacher Grade</title>
</head>
<body>
<h1>${eno}. ${ename}</h1>
<ul>
    <c:forEach items="gradeList" var="grade">
        <li>
            <a href="/teacher/detailgrade?e_no=${eno}&s_no=${grade.s_no}">${grade.s_name}</a>
            <span>${grade.totalScore}/10 점</span>
            <form action="/teacher/detailgrade" method="post">
                <input type="text" name="e_no" value="${eno}">
                <input type="text" name="s_no" value="${grade.s_no}">
                <input type="text" name="s_name" value="${grade.s_name}">
                <input type="text" name="score" value="${grade.totalScore}">
                <button>보기</button>
            </form>
        </li>
    </c:forEach>
</ul>
</body>
</html>
