package bassit.attendancesystem;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "StudentListServlet", value = {"/StudentList-servlet", "/studentList"})
public class StudentList extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World! from student list";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}