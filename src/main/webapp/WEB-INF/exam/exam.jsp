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
<h1> e_no : ${e_no}</h1>
<ul class="navigator">
    <c:forEach items="${questionVOList}" var="question">
        <li>
            <a onclick="move(${question.q_no})"> ${question.q_no}번 </a>
        </li>
    </c:forEach>
</ul>

<ul class="quizList">
    <c:forEach items="${questionVOList}" var="question">
        <div class="quizDiv" id="quiz${question.q_no}">
            <div>
                <h2>${question.q_num}번 문제</h2>
                <p>${question.q_view}</p>
            </div>
            <div>
                <form method="post" action="/check/answer" target="exam_test">

                    <input type="hidden" name="e_no" value="${e_no}">
                    <input type="hidden" name="q_no" value="${question.q_no}">
                    <input type="radio" name="answer" value="1"> ${question.q_answer1} <br>
                    <input type="radio" name="answer" value="2"> ${question.q_answer2} <br>
                    <input type="radio" name="answer" value="3"> ${question.q_answer3} <br>
                    <input type="radio" name="answer" value="4"> ${question.q_answer4} <br>
                    <input type="radio" name="answer" value="5"> ${question.q_answer5} <br>
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
            <iframe name="exam_test" src="/exam/answerSheet"></iframe>
        </div>


        </div>
        <div>

        </div>
        </div>

    </c:forEach>

</ul>
</body>
</html>
