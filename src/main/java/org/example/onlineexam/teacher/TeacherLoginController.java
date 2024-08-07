package org.example.onlineexam.teacher;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.extern.log4j.Log4j2;
import org.example.onlineexam.teacher.dao.TeacherDAO;
import org.example.onlineexam.teacher.vo.TeacherVO;

import java.io.IOException;
import java.util.Optional;


@Log4j2
@WebServlet(value = "/teacher/login")
public class TeacherLoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/teacher/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String t_id = req.getParameter("t_id");
        String t_pw = req.getParameter("t_pw");


        String cookieStr = /*t_name+"%"+*/t_id;


        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(60*60);


        //String aaa = (String) session.getAttribute("t_name");
        // Cookie = 이름 아이디
        // 세션 = tno

        try {
            Optional<TeacherVO> result = TeacherDAO.INSTANCE.get(t_id, t_pw);


            result.ifPresentOrElse(
                    TeacherVO ->
                    {
                        TeacherVO teacher = result.get();
                        session.setAttribute("t_no", teacher.getT_no());
                        session.setAttribute("t_name", teacher.getT_name());
                        session.setAttribute("t_no", teacher.getT_no());

                        /*session.getAttribute("t_name");
                        log.info("aaaaaaaaaa" + (String) session.getAttribute("t_name"));
*/
                        Cookie loginCookie = new Cookie("teacher", t_id);
                        loginCookie.setPath("/");
                        loginCookie.setMaxAge(60 * 60 * 24);

                        resp.addCookie(loginCookie);

                        try {
                            resp.sendRedirect("/teacher/home");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    ,
                    () -> {
                        try {
                            resp.sendRedirect("/teacher/login");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
