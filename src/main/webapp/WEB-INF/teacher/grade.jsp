<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teacher Grade</title>
</head>
<body>
<h1>${e_no}. ${e_name}</h1>
<ul>
    <c:forEach items="${gradeList}" var="grade">
        <li>
            <span>${grade.s_name}</span>
            <form action="/teacher/gradedetail" method="get">
                <input type="hidden" name="e_no" value="${e_no}">
                <input type="hidden" name="e_name" value="${e_name}">
                <input type="hidden" name="s_no" value="${grade.s_no}">
                <input type="hidden" name="s_name" value="${grade.s_name}">
                <button>보기</button>
            </form>
        </li>
    </c:forEach>
</ul>
<a href="/teacher/home">HOME</a>
</body>
</html>
