package photogallery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@Controller
public class PhotoGalleryController {

    @Autowired
    PhotoGalleryService board;

    private final String basePath = "/tmp/upload/";

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", board.getList());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(HttpServletRequest request,
                      @RequestParam(value = "id", defaultValue = "0", required = false) Long id) {
        String actionState = "Add";
        if (id > 0) {
            request.setAttribute("article", board.get(id));
            actionState = "Update";
        }
        request.setAttribute("action", actionState);
        return "add";
    }

    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public void image(HttpServletRequest request, HttpServletResponse response) {
        String saveName = request.getQueryString();
        if (saveName == null) {
            return;
        }

        File f = new File(basePath + saveName);
        InputStream is = null;
        try {
            is = new FileInputStream(f);
            OutputStream oos = response.getOutputStream();

            byte[] buf = new byte[8192];
            int c = 0;
            while ((c = is.read(buf, 0, buf.length)) > 0) {
                oos.write(buf, 0, c);
                oos.flush();
            }
            oos.close();
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addTo(Article article, @RequestParam("file") MultipartFile file) {
        saveFile(article, file);

        board.add(article);
        return "redirect:/index.action";
    }

    private void saveFile(Article article, @RequestParam("file") MultipartFile file) {
        String saveName = String.valueOf(new Date().getTime());

        OutputStream fos = null;

        try {
            fos = new FileOutputStream(basePath + File.separator + saveName);

            fos.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        article.setFilename(file.getOriginalFilename());
        article.setSaveName(saveName);
        article.setFileSize(file.getSize());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Article article, @RequestParam("file") MultipartFile file) {
        if (file != null) {
            saveFile(article, file);
        }

        board.update(article);
        return "redirect:/index.action";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id", defaultValue = "0", required = false) Long id) {

        board.delete(id);
        return "redirect:/index.action";
    }

}
