package simpleboard;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleBoardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SimpleBoard board = (SimpleBoard) getServletContext().getAttribute("board");
        if (board == null) {
            getServletContext().setAttribute("board", new SimpleBoard());
        }
        String idStr = request.getParameter("id");
        Long id = 0L;
        if (idStr != null) {
            id = Long.parseLong(idStr);
        }
        String writer = request.getParameter("writer");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        Article article = new Article();
        article.setId(id);
        article.setWriter(writer);
        article.setTitle(title);
        article.setContent(content);

        String requestURI = request.getRequestURI();

        if (requestURI.equals("/add.do")) {
            board.add(article);
        } else if (requestURI.equals("/update.do")) {
            board.update(article);
        } else if (requestURI.equals("/delete.do")) {
            board.delete(id);
        }
        response.sendRedirect("/");
    }
}
