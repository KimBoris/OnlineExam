package org.example.onlineexam.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.example.onlineexam.common.StringUtil;
import org.example.onlineexam.teacher.dao.TeacherDAO;
import org.example.onlineexam.teacher.vo.DetailVO;

import java.io.IOException;
import java.util.List;

@WebServlet (value = "/student/result")
@Log4j2
public class StudentResultController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String enoString = req.getParameter("e_no");
        Integer e_no = StringUtil.getInt(enoString, 1);

        HttpSession session = req.getSession();
        Integer s_no = (Integer) session.getAttribute("s_no");

        try {
            List<DetailVO> detailList = TeacherDAO.INSTANCE.getDetail(e_no, s_no);

            // 학생 점수 계산
            int totalScore = 0;

            for (DetailVO detail : detailList) {
                if (detail.getR_input() == detail.getQ_right()) {
                    totalScore++;
                }
            }//end for

            req.setAttribute("totalCount", detailList.size());
            req.setAttribute("totalScore", totalScore);
            req.setAttribute("detailList", detailList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        req.getRequestDispatcher("/WEB-INF/student/result.jsp").forward(req, resp);
    }
}
