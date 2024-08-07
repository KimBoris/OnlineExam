<%--
  Created by IntelliJ IDEA.
  User: a
  Date: 2024-08-06
  Time: 오후 6:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<style>
    .quizDiv {
        border: 1px solid black;
        margin: 10px;
        padding: 10px;
        height: 50vh;
    }

    .quizList {
        margin-left: 10vw;
        width: 50vw;
    }

    .navigator {
        position: fixed;
        top: 0;
        left: 0;
        width: 10vw;
        height: 100%;
        background-color: #f0f0f0;
        border-right: 1px solid black;
        pading: 10px;
        position: absolute;

    }

    .answerSheet {
        position: fixed;;
        top: 0;
        right: 0;
        width: 40vw;
        height: 100%;
        background-color: #f0f0f0;
        border-left: 1px solid black;
        padding: 10px;
        position: absolute;
    }

    .answerSheet iframe {
        height: 100vh;
    }
</style>
<ul class="quizList">
    <c:forEach items="${quizList}" var="quiz">
        <div class="quizDiv" id="quiz${quiz.qno}">
            <div>
                <h2>${quiz.qno}번 문제</h2>
                <p>${quiz.question}</p>
            </div>
            <div>
                <form method="post" action="/check/answer?count=${quizList.size()}" target="zero">

                    <input type="hidden" name="examno" value="${examNum}">
                    <input type="hidden" name="qno" value="${quiz.qno}">

                    <input type="radio" name="answer" value="1"> ${quiz.op1} <br>
                    <input type="radio" name="answer" value="1"> ${quiz.op1} <br>
                    <input type="radio" name="answer" value="1"> ${quiz.op1} <br>
                    <input type="radio" name="answer" value="1"> ${quiz.op1} <br>
                    <input type="radio" name="answer" value="1"> ${quiz.op1} <br>
                    <input type="submit" value="제출">
                </form>
            </div>
        </div>

        <script>
            function move(quizNum) {
                console.log(quizNum);

                const quizDiv = document.querySelector("#quiz" + quizNum);

                console.log(quizDiv);

                quizDiv.scrollIntoView({behavior: "smooth"});
            }
        </script>


        <div class="answerSheet">
            <iframe name="zero" src="/exam/answerSheet?count=${quizList.size()}"></iframe>
        </div>

        </div>
        <div>

        </div>
        </div>

    </c:forEach>

</ul>
</body>
</html>
