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

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import org.json.JSONObject;

@WebServlet(name = "RegistrationServletServlet", urlPatterns = {"/confirm", "/registerButtonPressed"})
public class RegistrationServlet extends HttpServlet {

    public void init() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //initialize all variables to the parameters received from the POST request sent from index.jsp
        String firstname = parameter(request, "firstname");
        String lastname = parameter(request, "lastname");
        String email = parameter(request, "email");
        String phone = parameter(request, "phone");
        String password = parameter(request, "password");
        String confirmPassword = parameter(request, "confirmPassword");
        String address1 = parameter(request, "address1");
        String address2 = parameter(request, "address2");
        String state = parameter(request, "state");
        String country = parameter(request, "country");
        String zipcode = parameter(request, "zipcode");
        String areacode = parameter(request, "area");

        StringBuilder error = new StringBuilder();
        if(!password.equals(confirmPassword)){
            error.append("Password does not match. Please try again.");
        }else{
            if(firstname == null || firstname.isEmpty()){
                error.append("First name must be provided. Please try again.");
            }else if(lastname == null || lastname.isEmpty()){
                error.append("Last name must be provided. Please try again.");
            }else if(email == null || email.isEmpty()) {
                error.append("Email must be provided. Please try again.");
            }else if(phone == null || phone.isEmpty()){
                error.append("Phone number must be provided. Please try again.");
            }else if(address1 == null || address1.isEmpty()){
                error.append("Address 1 must be provided. Please Try again.");
            }else if(state == null || state.isEmpty()){
                error.append("State must be provided. Please try again.");
            }else if(country == null || country.isEmpty()){
                error.append("Country must be provided. Please try again.");
            }else if(zipcode == null || zipcode.isEmpty()){
                error.append("Zip code must be provided. Please try again.");
            }else{
                //send api request to write to db
                JSONObject json = new JSONObject();
                json.put("firstname", firstname);
                json.put("lastname", lastname);
                json.put("email", email);
                json.put("password", password);
                json.put("phoneNumber", phone);
                json.put("address1", address1);
                json.put("address2", address2);
                json.put("state", state);
                json.put("country", country);
                json.put("zipCode", zipcode);
                json.put("areaCode", areacode);

                HttpPost method = null;
                try {
                    method = new HttpPost(new URI("http://localhost:8080/api/v1/person/create"));
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
                method.setHeader("Content-Type", "application/json");
                method.setEntity(new StringEntity(json.toString(), ContentType.APPLICATION_JSON));

                HttpClient client = new DefaultHttpClient();
                HttpResponse apiResponse = client.execute(method);
                InputStream in = apiResponse.getEntity().getContent();

                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = null;
                try {
                    root = mapper.readTree(in);
                } catch (JsonProcessingException ex) {
                    throw new RuntimeException(ex);
                }
                JsonNode setup = root.path("message");
                String message = String.valueOf(setup).replace("\"", "");
                //.replace("\"", "")
                if(message.isEmpty()){
                    error.append("Successfully added ").append(firstname).append(" ").append(lastname);
                }else{
                    error.append(message).append(". Please try again.");
                }
            }
        }
        request.setAttribute("error", error.toString());

        //switches to the confirmation page after written to DB
        RequestDispatcher changeView = request.getRequestDispatcher("view/pages/registrationConfirmation.jsp");
        changeView.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/register");
    }

    //helper method for parameters
    public String parameter(HttpServletRequest request, String value){
        return request.getParameter(value);
    }

}