package org.example.onlineexam.teacher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.onlineexam.teacher.dao.TeacherDAO;
import org.example.onlineexam.teacher.vo.ExamVO;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/teacher/home")
@Log4j2
public class TeacherHomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
        log.info("doGet Home Page");

        try {
            // 시험 리스트
            List<ExamVO> examList = TeacherDAO.INSTANCE.getExam();
            req.setAttribute("examList", examList);

            log.info("examList: " + examList);

            for(ExamVO examVO : examList) {
                log.info("examVO: " + examVO);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }//end catch

        req.getRequestDispatcher("/WEB-INF/teacher/home.jsp").forward(req, resp);

    }
}//end TeacherHomeController