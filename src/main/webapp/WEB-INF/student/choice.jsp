<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/student/choice" method="post">
        <h3>시험선택</h3>
        <select name="e_no">
            <c:forEach items="${examList}" var="list">
                <option value="${list.e_no}">${list.e_name}</option>
            </c:forEach>
        </select>

        <button type="submit">시험보기</button>
    </form>
</body>
</html>
