package org.example.onlineexam.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.example.onlineexam.common.CookieUtil;
import org.example.onlineexam.common.StringUtil;
import org.example.onlineexam.student.dao.ResultDAO;
import org.example.onlineexam.teacher.dao.QuestionDAO;
import org.example.onlineexam.teacher.vo.QuestionVO;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/student/exam")
@Log4j2
@ToString
public class StudentExamController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String examNum = req.getParameter("e_no");
        Integer e_no = StringUtil.getInt(examNum, 5);

        log.info("GET");
        try {
            List<QuestionVO> questionVOList = QuestionDAO.INSTANCE.get(e_no);
            req.setAttribute("questionVOList", questionVOList);
            req.setAttribute("e_no", e_no);


            String cookieValue = questionVOList.stream().map(vo -> vo.getQ_no() + ":0").collect(Collectors.joining("&"));

            Cookie answerCookie = new Cookie("answer", cookieValue);
            answerCookie.setPath("/");
            answerCookie.setMaxAge(60 * 60 * 24);

            resp.addCookie(answerCookie);

            req.getRequestDispatcher("/WEB-INF/student/exam.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("DO POST");

        HttpSession session = req.getSession();
        Integer s_no = (Integer) session.getAttribute("s_no");
        String enoString = req.getParameter("e_no");
        Integer e_no = StringUtil.getInt(enoString, 1);

        Map<Integer,String> answerMap = CookieUtil.parseStr(req);

        for (Map.Entry<Integer, String> entry : answerMap.entrySet()) {
            String valueStr = entry.getValue();
            Integer r_input = StringUtil.getInt(valueStr, 1);

            Integer q_no = entry.getKey();

            try {
                ResultDAO.INSTANCE.insertResult(s_no,q_no,r_input);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

        resp.sendRedirect("/student/result?e_no="+e_no);
    }
}
