<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teacher Home</title>
</head>
<body>
<h1>강사님, 환영합니다.</h1>
    <div>
<c:forEach items="${examList}" var="exam">
        <div>
            <span>제목 :</span>
            <span>${exam.e_name}</span>
        </div>
        <form action="/teacher/grade" method="post">
            <input type="hidden" name="e_no" value="${exam.e_no}">
            <input type="hidden" name="e_name" value="${exam.e_name}">
            <input type="submit" value="SUBMIT">
            <button type="submit">SUBMIT</button>
        </form>
</c:forEach>
        <a href="/teacher/register">시험 등록하기</a>
    </div>
</body>
</html>