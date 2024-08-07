<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: a
  Date: 2024-08-06
  Time: 오후 5:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    Answer Sheet
</div>

<p>${answerMap}</p>

<ul>
    <c:forEach items="${answerMap}" var ="answer">

        <li>
            <a onclick="move"(${answer['key']})> ${answer['key']}</a>
            <input type="text" readonly value="${answer['value']}"> </input>
        </li>
    </c:forEach>
</ul>

<script>
    const move = (quizNum) => {
        parent.move(quizNum);
    }
</script>
</body>
</html>
