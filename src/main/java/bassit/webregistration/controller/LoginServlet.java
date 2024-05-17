package bassit.webregistration.controller;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

@WebServlet(name = "LoginServletServlet", value = {"/loginPage", "/loginButtonPressed", "/account"})
public class LoginServlet extends HttpServlet {
    private String message;
    private int success;

    public void init() {
        message = "Hello World!";
        success = 800%300;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //assign input
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //send request to API to verify user credentials
        //Creating a HttpClient object
        CloseableHttpClient client = HttpClients.createDefault();

        //Creating a HttpGet object
        HttpGet method = new HttpGet("http://localhost:8080/api/v1/person/getPerson/" + email + "/" + password);

        //Executing the Get request
        HttpResponse apiResponse = client.execute(method);
        InputStream in = apiResponse.getEntity().getContent();

        //map the json from the response
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = null;
        try {
            root = mapper.readTree(in);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }

        //if credentials ok, forward to account home else error page with retry button
        if(root.asInt() == success){
            RequestDispatcher changeView = request.getRequestDispatcher("view/pages/accountHomepage.jsp");
            changeView.forward(request, response);
        }else{
            StringBuilder error = new StringBuilder();
            error.append("Unable to login. Please try again.");
            request.setAttribute("error", error.toString());

            //switches to the confirmation page after written to DB
            RequestDispatcher changeView = request.getRequestDispatcher("view/pages/registrationConfirmation.jsp");
            changeView.forward(request, response);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/login");
    }

    public void destroy() {
    }

}