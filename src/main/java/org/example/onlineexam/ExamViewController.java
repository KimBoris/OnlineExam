package org.example.onlineexam;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@WebServlet("/exam/view")
@Log4j2
public class ExamViewController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        int examNum = (int) session.getAttribute("examNum");

        List<QuizVO> quizVOList = IntStream.rangeClosed(1, examNum).mapToObj(i -> QuizVO.builder()
                .qno(i)
                .question("문제 " + i)
                .op1("1번" + i)
                .op2("2번" + i)
                .op3("3번" + i)
                .op4("4번" + i)
                .op5("5번" + i)
                .answer(i % 5 + 1)
                .build()).collect(Collectors.toList());

        req.setAttribute("quizList", quizVOList);

        String cookieValue = quizVOList.stream().map(vo -> vo.getQno()+":0").collect(Collectors.joining("&"));

        Cookie answerCookie = new Cookie("answer", cookieValue);
        answerCookie.setPath("/");
        answerCookie.setMaxAge(60*60*24);

        resp.addCookie(answerCookie);

        req.getRequestDispatcher("/WEB-INF/exam/view.jsp");
    }
}
