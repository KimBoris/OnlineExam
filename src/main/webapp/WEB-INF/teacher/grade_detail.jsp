<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:set var="totalQuestions" value="${fn:length(detailList)}" />

    <h1>${s_name} 학생의 성적</h1>
    <h3>${totalScore}/${totalQuestions} 점</h3>
    <ul>
        <c:forEach items="${detailList}" var="detail">
            <li>
                <div>${detail.q_num}. ${detail.q_view}</div>
                <div><b>나의 답</b> ${detail.r_input}</div>
                <div><b>정답</b> ${detail.q_right}</div>
            </li>
        </c:forEach>
    </ul>
</body>
</html>
