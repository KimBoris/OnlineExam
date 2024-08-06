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
        Integer eno = StringUtil.getInt(enoString, 1);

        String snoString = req.getParameter("s_no");
        Integer sno = StringUtil.getInt(snoString, 1);

        String sname = req.getParameter("s_name");

        String scoreString = req.getParameter("score");
        Integer score = StringUtil.getInt(scoreString, 1);

        try {
            List<DetailVO> detailList = TeacherDAO.INSTANCE.getDetail(eno, sno);
            req.setAttribute("detailList", detailList);
            req.setAttribute("sname", sname);
            req.setAttribute("score", score);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
