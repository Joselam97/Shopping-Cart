package org.servlet.webapp.servlet.service;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;


public interface LoginService {
    // Defines a method that retrieves the username from the HttpServletRequest.
    // The method signature uses Optional<String> to indicate that the username may not be present.
    Optional<String> getUsername(HttpServletRequest request);

}
