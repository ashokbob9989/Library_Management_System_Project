package com.project.lms.LibraryManagementSystem.frontend;

import com.project.lms.LibraryManagementSystem.repository.SignupRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import com.project.lms.LibraryManagementSystem.model.Signup;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@WebServlet("/signupServlet")
public class SignupServlet extends HttpServlet {

    private SignupRepository signupRepository;

    @Override
    public void init() throws ServletException {
        super.init();
        WebApplicationContext context = (WebApplicationContext) getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        signupRepository = context.getBean(SignupRepository.class);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        InputStream inputStream = getClass().getResourceAsStream("/static/signUp.html");
        if (inputStream == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "signUp.html not found");
            return;
        }
        try (OutputStream outputStream = resp.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (BufferedReader reader = req.getReader()) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }

            JSONObject jsonObject = new JSONObject(jsonBuilder.toString());
            String username = jsonObject.getString("username");
            String email = jsonObject.getString("email");
            String password = jsonObject.getString("password");

            Signup existingSignupByUsername = signupRepository.findByUsername(username);
            Signup existingSignupByEmail = signupRepository.findByEmail(email);

            resp.setContentType("application/json");

            // Check if username is already registered
            if (existingSignupByUsername != null) {
                resp.getWriter().write("{\"message\": \"Username already registered. Please log in.\"}");
            }
            // Check if email is already registered
            else if (existingSignupByEmail != null) {
                resp.getWriter().write("{\"message\": \"Email already registered. Please log in.\"}");
            }
            else {
                // Check if password is already taken
                List<Signup> allSignups = signupRepository.findAll(); // Make sure you have this method
                boolean passwordTaken = allSignups.stream().anyMatch(signup -> signup.getPassword().equals(password));

                // Check if the username is already taken
                boolean usernameTaken = allSignups.stream().anyMatch(signup -> signup.getUsername().equals(username));

                if (usernameTaken) {
                    resp.getWriter().write("{\"message\": \"Username already taken. Please choose a different username.\"}");
                }
                else if (passwordTaken) {
                    resp.getWriter().write("{\"message\": \"Password already taken. Please choose a different password.\"}");
                }
                else {
                    // Create a new signup
                    Signup signup = new Signup(username, email, password);
                    signupRepository.save(signup);
                    resp.getWriter().write("{\"message\": \"Signup successful\"}");
                }
            }
        }
    }

}