<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teacher Home</title>
</head>
<body>
<h1>${tname} 강사님, 환영합니다.</h1>
<c:forEach items="${examList}" var="exam">
    <div>
        <a href="/teacher/grade?e_no=${exam.e_no}&e_name=${exam.e_name}">${exam.e_no} ${exam.e_name}</a>
        <span>작성자: ${exam.t_name}</span>
        <div>hi</div>
    </div>
</c:forEach>
</body>
</html>