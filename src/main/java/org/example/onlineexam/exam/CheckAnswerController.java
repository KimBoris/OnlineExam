package org.example.onlineexam.exam;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.extern.log4j.Log4j2;
import org.example.onlineexam.common.StringUtil;

import java.io.IOException;
import java.util.Arrays;

@WebServlet(value="/check/answer")
@Log4j2
public class CheckAnswerController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String e_no = req.getParameter("e_no");
        String q_noStr = req.getParameter("q_no");
        String q_numStr = req.getParameter("q_num");
        String answer = req.getParameter("answer");

        Cookie answerCookie = Arrays.stream(req.getCookies())
                .filter(cookie -> cookie.getName().equals("answer"))
                .findFirst().orElse(null);


        String[] answers = answerCookie.getValue().split("&");

        int q_num = StringUtil.getInt(q_numStr,-1);
        int q_no = StringUtil.getInt(q_noStr,-1);

        answers[q_num-1] = q_no + ":" + answer;

        String cookieValue = String.join("&",answers);

        Cookie answerCookies2 = new Cookie("answer", cookieValue);
        answerCookies2.setPath("/");
        answerCookies2.setMaxAge(60*60*24);

        resp.addCookie(answerCookies2);

        resp.sendRedirect("/student/answerSheet");
    }
}
