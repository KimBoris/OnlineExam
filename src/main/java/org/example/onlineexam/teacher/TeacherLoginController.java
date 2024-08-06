package org.example.onlineexam.teacher;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.extern.log4j.Log4j2;

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
        String tid = req.getParameter("tid");
        String tpw = req.getParameter("tpw");
        log.info("------------------------");
        log.info("tid = " + tid);
        log.info("tpw = " + tpw);
                            log.info("여기니?1");

        HttpSession session = req.getSession();
        // Cookie = 이름 아이디
        // 세션 = tno
        try {
            Optional<TeacherVO> result = TeacherDAO.INSTANCE.get(tid, tpw);
                            log.info("여기니?2");


            log.info("RESULT = " + result);

            result.ifPresentOrElse(
                    TeacherVO ->
                    {
                        Cookie loginCookie = new Cookie("tid", tid);
                        loginCookie.setPath("/");
                        loginCookie.setMaxAge(60 * 60 * 24);

                        resp.addCookie(loginCookie);
                        try {
                            log.info("쿠키줘봐" + req.getCookies());
                            resp.sendRedirect("/teacher/mypage");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    ,
                    () -> {
                        try {
                            log.info("여기니?");
                            resp.sendRedirect("/teacher/login");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (Exception e) {
            throw new RuntimeException();
        }


   /*     HttpSession session = req.getSession();


        try
        {
            Optional<TeacherVO> result = TeacherDAO.INSTANCE.get(tid, tpw);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
*/
    }
}
