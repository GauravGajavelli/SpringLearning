package com.gauravgajavelli;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyFirstServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().print(
                "<html>\n" +
                        "<body>\n" +
                        "<h1>Hello World</h1>\n" +
                        "<p>This is my very first, embedded Tomcat, HTML Page!</p>\n" +
                        "</body>\n" +
                        "</html>");
    }
}
