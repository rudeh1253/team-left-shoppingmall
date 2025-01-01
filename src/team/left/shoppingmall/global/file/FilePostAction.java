package team.left.shoppingmall.global.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.CommandHandler;
import team.left.shoppingmall.global.CommonConstants;

public class FilePostAction implements CommandHandler {
    private static String imagesDirectory = null;
    
    @Override
    public void init(ServletConfig servletConfig) {
        ServletContext servletContext = servletConfig.getServletContext();
        File filePath = new File(servletContext.getRealPath("/resources/images"));
        if (!filePath.exists()) {
            System.out.println("not exists");
            filePath.mkdirs();
        }
        String pathInString = filePath.getAbsolutePath();
        imagesDirectory = pathInString.endsWith(File.separator) ? pathInString : pathInString + File.separator;
        System.out.println("imageDirectory=" + imagesDirectory);
    }

    @Override
    public String handleCommand(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String filename = request.getParameter("filename");
        System.out.println("filename=" + filename);
        if (!isValidFileName(filename)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        filename = filename.startsWith("/") ? filename.substring("/".length()) : filename;
        byte[] buffer = new byte[CommonConstants.BUFFER_SIZE];
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(imagesDirectory + filename)));
                BufferedInputStream bis = new BufferedInputStream(request.getInputStream())) {
            int readCount;
            while ((readCount = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, readCount);
            }
        }
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        return null;
    }
    
    private boolean isValidFileName(String filename) {
        if (filename == null) {
            return false;
        }
        
        Pattern regex = Pattern.compile("^/?[\\w\\-. ]+\\.(jpg|png|jpeg|webp|gif|bmp)$");
        return regex.matcher(filename).matches();
    }
}
