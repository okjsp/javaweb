package photogallery;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.print("post" + request.getParameter("id"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.print("get" + request.getParameter("id"));
        response.setHeader("Content-Type", "text/html; charset=utf8");
        PrintWriter out = response.getWriter();
        out.write("<h1>Hello World</h1>");
        out.close();
    }
}
