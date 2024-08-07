package org.example.onlineexam.exam;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.extern.log4j.Log4j2;
import org.example.onlineexam.common.StringUtil;
import org.example.onlineexam.teacher.dao.QuestionDAO;
import org.example.onlineexam.teacher.vo.QuestionVO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@WebServlet("/student/exam")
@Log4j2
public class StudentExamController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String examNum = req.getParameter("e_no");
        Integer e_no = StringUtil.getInt(examNum, 1);

        log.info("겟겟겟겟겟");
        try {
            List<QuestionVO> questionVOList = QuestionDAO.INSTANCE.get(e_no);
            req.setAttribute("questionVOList", questionVOList);
            req.setAttribute("e_no", e_no);


            String cookieValue = questionVOList.stream().map(vo -> vo.getQ_num() + ":0").collect(Collectors.joining("&"));

            Cookie answerCookie = new Cookie("answer", cookieValue);
            answerCookie.setPath("/");
            answerCookie.setMaxAge(60 * 60 * 24);

            resp.addCookie(answerCookie);

            req.getRequestDispatcher("/WEB-INF/exam/exam.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
