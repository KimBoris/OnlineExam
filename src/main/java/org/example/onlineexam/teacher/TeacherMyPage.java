package org.example.onlineexam.teacher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
@Log4j2
@WebServlet(value="/teacher/mypage")
public class TeacherMyPage extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       // req.getRequestDispatcher("/WEB-INF/teacher/logintest.jsp").forward(req, resp);

        HttpSession session = req.getSession(false);

        if(session == null || session.getAttribute("t_name") == null) {
            resp.sendRedirect("/teacher/mypage");
            return;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/teacher/logintest.jsp").forward(req, resp);
    }
}
