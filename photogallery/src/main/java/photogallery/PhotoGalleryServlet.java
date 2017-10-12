package photogallery;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Date;

public class PhotoGalleryServlet extends HttpServlet {

    private final String basePath = "/tmp/upload/";;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PhotoGallery board = (PhotoGallery) getServletContext().getAttribute("board");
        if (board == null) {
            getServletContext().setAttribute("board", new PhotoGallery());
        }
        String idStr = request.getParameter("id");
        Long id = 0L;
        if (idStr != null) {
            id = Long.parseLong(idStr);
        }
        Article article = board.get(id);
        request.setAttribute("article", article);

        String requestURI = request.getRequestURI();
        if (requestURI.equals("/")) {
            board.add(article);
        } else if (requestURI.equals("/image.do")){
            String saveName = request.getQueryString();

            File f=new File(basePath + saveName);
            InputStream is = new FileInputStream(f);

            OutputStream oos = response.getOutputStream();

            byte[] buf = new byte[8192];
            int c = 0;
            while ((c = is.read(buf, 0, buf.length)) > 0) {
                oos.write(buf, 0, c);
                oos.flush();
            }

            oos.close();
            is.close();

            return;
        }

        request.getRequestDispatcher("add.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Part filePart = request.getPart("file");
        final String fileName = getFileName(filePart);
        String saveName = String.valueOf(new Date().getTime());
        Long fileSize = 0L;

        OutputStream out = null;
        InputStream filecontent = null;
        final PrintWriter printWriter = response.getWriter();

        try {
            out = new FileOutputStream(new File(basePath + File.separator
                    + saveName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                fileSize += read;
                out.write(bytes, 0, read);
            }
        } catch (FileNotFoundException fne) {
            printWriter.println("You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location.");
            printWriter.println("<br/> ERROR: " + fne.getMessage());
            return;
        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
        }


        PhotoGallery board = (PhotoGallery) getServletContext().getAttribute("board");
        if (board == null) {
            getServletContext().setAttribute("board", new PhotoGallery());
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
        article.setFilename(fileName);
        article.setSaveName(saveName);
        article.setFileSize(fileSize);

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
    private String getFileName(final Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}
