package org.servlet.webapp.servlet.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.servlet.webapp.servlet.service.LoginService;

import java.io.IOException;
import java.util.Optional;

// URL mapping for the logout functionality
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    // Injecting LoginService to manage authentication and session information
    @Inject
    private LoginService auth;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Checking if the user is logged in by retrieving the username from the session
        Optional<String> username = auth.getUsername(req);

        // If the username is present (meaning the user is logged in)
        if (username.isPresent()){
            // Invalidate the current session to log the user out
            HttpSession session = req.getSession();
            session.invalidate(); // Destroys the session, removing the logged-in user information

        }
        // Redirect the user to the login page after logging out
        resp.sendRedirect(req.getContextPath() + "/login.html");
    }
}
