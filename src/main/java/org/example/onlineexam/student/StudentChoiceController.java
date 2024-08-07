package org.example.onlineexam.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.onlineexam.common.StringUtil;
import org.example.onlineexam.student.dao.ExamDAO_kmj;
import org.example.onlineexam.teacher.vo.ExamVO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(value = "/student/choice")
@Log4j2
public class StudentChoiceController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            List<ExamVO> examList = ExamDAO_kmj.INSTANCE.getExam();

            req.setAttribute("examList", examList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }//end try

        req.getRequestDispatcher("/WEB-INF/student/choice.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("CHOICE DO POST ------------------------------------");

        String e_noStr = req.getParameter("e_no");
        Integer e_no = StringUtil.getInt(e_noStr,1);
        log.info("e_no :" + e_no);

        resp.sendRedirect("/student/exam?e_no=" + e_no);
    }
}
