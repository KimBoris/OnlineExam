package org.example.onlineexam.teacher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.onlineexam.common.StringUtil;
import org.example.onlineexam.teacher.dao.TeacherDAO;
import org.example.onlineexam.teacher.vo.DetailVO;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/teacher/gradedetail")
@Log4j2
public class TeacherGradeDetailController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String enoString = req.getParameter("e_no");
        Integer e_no = StringUtil.getInt(enoString, 1);
        String e_name = req.getParameter("e_name");

        String snoString = req.getParameter("s_no");
        Integer s_no = StringUtil.getInt(snoString, 1);

        String s_name = req.getParameter("s_name");

        try {
            List<DetailVO> detailList = TeacherDAO.INSTANCE.getDetail(e_no, s_no);

            // 학생 점수 계산
            int totalScore = 0;

            for (DetailVO detail : detailList) {
                if (detail.getR_input() == detail.getQ_right()) {
                    totalScore++;
                }
            }//end for

            req.setAttribute("e_no", e_no);
            req.setAttribute("e_name", e_name);

            req.setAttribute("detailList", detailList);
            req.setAttribute("s_name", s_name);
            req.setAttribute("totalScore", totalScore);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }//end catch

        req.getRequestDispatcher("/WEB-INF/teacher/grade_detail.jsp").forward(req, resp);

    }
}
