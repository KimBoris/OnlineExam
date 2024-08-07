package org.example.onlineexam.exam;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.onlineexam.common.CookieUtil;

import java.io.IOException;
import java.util.Map;

@Log4j2
@WebServlet(value="/exam/answerSheet")
public class AnswerSheetController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Integer,String> answerMap = CookieUtil.parseStr(req);

        log.info("ANswerMap = "+ answerMap);
        req.setAttribute("answerMap", answerMap);

        req.getRequestDispatcher("/WEB-INF/exam/answerSheet.jsp").forward(req, resp);

    }
}
