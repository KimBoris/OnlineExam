package org.example.onlineexam;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Arrays;

@WebServlet(value="/check/answer")
@Log4j2
public class CheckAnswerController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String examno = req.getParameter("examno");
        String qnoStr = req.getParameter("qno");
        String answer = req.getParameter("answer");

        HttpSession session = req.getSession();
        String uid = (String)session.getAttribute("uid");

        Cookie answerCookie = Arrays.stream(req.getCookies())
                .filter(cookie -> cookie.getName().equals("answer"))
                .findFirst().orElse(null);


        String[] answers = answerCookie.getValue().split("&");

        int qno = Integer.parseInt(qnoStr);

        answers[qno-1] = qno+":"+answer;

        String cookieValue = String.join("&",answers);

        Cookie answerCookies2 = new Cookie("answer", cookieValue);
        answerCookies2.setPath("/");
        answerCookies2.setMaxAge(60*60*24);

        resp.addCookie(answerCookies2);

        resp.sendRedirect("/exam/answerSheet?count="+req.getParameter("count"));
    }
}
