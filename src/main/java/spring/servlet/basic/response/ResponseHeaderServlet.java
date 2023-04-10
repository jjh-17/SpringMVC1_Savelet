package spring.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //status-line
        response.setStatus(HttpServletResponse.SC_OK); //응답 코드 설정

        //response-headers
        response.setHeader("Cache-control", "no-cache, no-store, must-revalidate"); //캐시 제거
        response.setHeader("Pragma", "no-cache"); //캐시 제거
        response.setHeader("custom-header", "hello"); //사용자가 임의로 헤더를 설정

        //편의 메서드 호출
        setContentType(response);
        setCookie(response);
        redirect(response);

        response.getWriter().write("responseHeaderServlet 호출");
    }

    //헤더 편의 메서드
    private void setContentType(HttpServletResponse response) {
        //Content-Type 설정
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
    }

    //쿠키 편의 메서드
    private void setCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("myCookie", "hello");

        cookie.setMaxAge(600); //캐시 유지시간 600초
        response.addCookie(cookie);
    }

    //redirect 편의 메서드
    private void redirect(HttpServletResponse response) throws IOException{
        //Status Code 302
        response.sendRedirect("/basic/hello-form.html");
    }
}
