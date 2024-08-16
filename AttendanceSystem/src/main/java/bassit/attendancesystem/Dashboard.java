package bassit.attendancesystem;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "DashboardServlet", value = {"/Dashboard-servlet", "/dashboard"})
public class Dashboard extends HttpServlet {

    //doGet is executed when the page is called from a href
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/pages/Dashboard.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form data
        String roleID = request.getParameter("role");
        String username = request.getParameter("username");
        String passwordHash = request.getParameter("password");
        String email = request.getParameter("email");

        // JSON data to send in the POST request
        String jsonInputString = "{\"username\": \"" + username + "\", \"passwordhash\": \"" + passwordHash + "\", \"email\": \"" + email + "\"}";

        // External API URL
        String apiUrl = "http://localhost:8080/api/v1/user/create/" + roleID;

        // Send POST request to the API
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            // Send JSON input data
            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Get and handle the API response
            int status = con.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                // Success - Handle API response if necessary
                // Forward to success JSP page
                request.setAttribute("message", "Data submitted successfully!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/pages/Success.jsp");
                dispatcher.forward(request, response);
            } else {
                // Failure - Handle the error
                request.setAttribute("message", "Failed to submit data. Error code: " + status);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/pages/Error.jsp");
                dispatcher.forward(request, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("message", "An error occurred: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/pages/Error.jsp");
            dispatcher.forward(request, response);
        }
    }
}