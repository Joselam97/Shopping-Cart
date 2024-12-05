package org.servlet.webapp.servlet.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Optional;


//@Alternative
public class LoginServiceCookieImpl implements LoginService {

    // This method retrieves the username from cookies in the HttpServletRequest.
    @Override
    public Optional<String> getUsername(HttpServletRequest req) {

        // Retrieve cookies from the request. If there are no cookies, create an empty array.
        Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];

        // Use Arrays.stream to filter cookies with the name "username" and retrieve its value.
        // If a "username" cookie is found, its value will be returned inside an Optional.
        return Arrays.stream(cookies)
                .filter(c -> "username".equals(c.getName())) // Filters cookies to find the one with the name "username".
                .map(Cookie::getValue) // Extracts the value of the cookie (the username).
                .findAny(); // Returns the first match wrapped in an Optional.
    }
}
