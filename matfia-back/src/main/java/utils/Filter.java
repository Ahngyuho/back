package utils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "Filter", urlPatterns = {"/order/**", "/mypage/**"})
public class Filter implements jakarta.servlet.Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Cookie[] cookies = request.getCookies();
        boolean result = false;

        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("ATOKEN")) {
                    result = JwtUtil.validate(cookie.getValue());
                }
            }
        }

        if (result) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse)servletResponse).sendRedirect("/login");
        }
    }
}