package bassit.filetransfertest;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

@WebServlet(name = "FileServlet", value = "/FileServlet")
@MultipartConfig
public class FileServlet extends HttpServlet {
    private String message;
    private final int ARBITARY_SIZE = 1048;

    public void init() {
        message = "Hello World!";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("File received. This is from post servlet");
        String description = request.getParameter("description"); // Retrieves <input type="text" name="description">
        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        InputStream fileContent = filePart.getInputStream();

        File targetFile = new File("C:\\Users\\Bassit\\IdeaProjectsUltimate\\FileTransferTest\\src\\main\\resources\\new.csv");

        FileUtils.copyInputStreamToFile(fileContent, targetFile);


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public void destroy() {
    }

    private static String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

}