package org.example.onlineexam.teacher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.Cleanup;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.example.onlineexam.common.ExcelReader;
import org.example.onlineexam.teacher.dao.QuestionDAO;
import org.example.onlineexam.teacher.vo.QuestionVO;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet(value = "/teacher/register")
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB
        maxFileSize=1024*1024*50,      	// 50 MB
        maxRequestSize=1024*1024*100)   	// 100 MB
@Log4j2
@ToString
public class TeacherRegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Regist DO GET ----------------------------------");

        req.getRequestDispatcher("/WEB-INF/teacher/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Regist DO POST ----------------------------------");

        String e_name = req.getParameter("e_name");

        Part filePart = req.getPart("q_file");

        @Cleanup InputStream in = filePart.getInputStream();

        try {
            List<QuestionVO> quizVOList = ExcelReader.readInputStream(in);

            log.info(quizVOList);

            HttpSession session = req.getSession();
            int t_no = (int) session.getAttribute("t_no");
            log.info("t_NO :" + t_no);

            QuestionDAO.INSTANCE.insertExam(e_name, t_no ,quizVOList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("/teacher/home");

    }
}
