package org.servlet.webapp.servlet.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

// Marks the class as an application-scoped bean, meaning it will be shared across the entire application.
@ApplicationScoped
public class LoginServiceSessionImpl implements LoginService{

    // This method retrieves the username from the HTTP session, if present
    @Override
    public Optional<String> getUsername(HttpServletRequest request) {
        // Retrieving the session associated with the request (creating a new one if it doesn't exist).
        HttpSession session = request.getSession();

        // Retrieving the "username" attribute from the session.
        String username = (String) session.getAttribute("username");

        // If the username exists in the session, return it wrapped in an Optional.
        if (username != null){
            return Optional.of(username);
        }
        // If the username is not found in the session, return an empty Optional.
        return Optional.empty();
    }
}
