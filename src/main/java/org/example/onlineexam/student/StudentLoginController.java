package org.example.onlineexam.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.example.onlineexam.HelloServlet;
import org.example.onlineexam.student.dao.StudentDAOsy;
import org.example.onlineexam.student.vo.StudentVO;

import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/student/login")
@Log4j2
public class StudentLoginController extends HelloServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doGet login page");

        req.getRequestDispatcher("/WEB-INF/student/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doPost login page");

        String s_id = req.getParameter("s_id");
        String s_pw = req.getParameter("s_pw");

        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(60*60);

        try {
            Optional<StudentVO> result = StudentDAOsy.INSTANCE.get(s_id, s_pw);
            result.ifPresentOrElse(
                    StudentVO -> {
                        StudentVO student = result.get();

                        session.setAttribute("s_no", student.getS_no());

                        Cookie loginCookie = new Cookie("student", s_id);
                        loginCookie.setPath("/");
                        loginCookie.setMaxAge(60 * 60 * 24);

                        resp.addCookie(loginCookie);

                        try {
                            resp.sendRedirect("/student/logintest");
                            log.info("student login success");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    () -> {
                        try {
                            resp.sendRedirect("/student/login");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
