package org.example.onlineexam.teacher.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.onlineexam.common.CookieUtil;

import java.io.IOException;

@WebFilter(urlPatterns= "/teacher/*")
@Log4j2
public class TeacherFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("doFilter----------------------");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 로그인 페이지의 경우 필터링에서 제외
        String requestURI = request.getRequestURI();
        if(requestURI.equals("/teacher/login")){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // teacher 라는 이름의 쿠키가 없으면 로그인 안한 것으로 판단
        if(CookieUtil.getCookie(request, "teacher") == null) {
            response.sendRedirect("/teacher/login");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }
}
