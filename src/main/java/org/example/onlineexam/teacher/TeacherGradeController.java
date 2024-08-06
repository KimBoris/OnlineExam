package org.example.onlineexam.teacher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.onlineexam.common.StringUtil;
import org.example.onlineexam.teacher.dao.TeacherDAO;
import org.example.onlineexam.teacher.vo.ExamVO;
import org.example.onlineexam.teacher.vo.GradeVO;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/teacher/grade")
@Log4j2
public class TeacherGradeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("doGet grade page");

        // 파라미터 받아오기 - 시험 번호
        String enoString = req.getParameter("e_no");
        Integer eno = StringUtil.getInt(enoString, 1);

        // 파라미터 받아오기 - 시험 이름
        String ename = req.getParameter("e_name");

        try {
            // 학생별 성정 리스트 받아오기
            List<GradeVO> gradeList = TeacherDAO.INSTANCE.getGrade(eno);
            req.setAttribute("gradeList", gradeList);
            req.setAttribute("eno", eno);
            req.setAttribute("ename", ename);

            log.info("examList: " + gradeList);

            for(GradeVO gradeVO : gradeList) {
                log.info("gradeVO: " + gradeVO);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }//end catch

        req.getRequestDispatcher("/WEB-INF/teacher/grade.jsp").forward(req, resp);

    }
}//end TeacherGradeController