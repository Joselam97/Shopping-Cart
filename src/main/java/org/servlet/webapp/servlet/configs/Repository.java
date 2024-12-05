package org.servlet.webapp.servlet.configs;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Named;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@RequestScoped // The bean will be created per HTTP request and discarded at the end of the request
@Named // The bean will be named based on the class name
@Stereotype // This makes @Repository a stereotype (a combination of multiple annotations)
@Retention(RUNTIME) // The annotation is available at runtime for DI frameworks to proces
@Target(ElementType.TYPE) // This annotation can only be applied to classes

// The custom annotation declaration
public @interface Repository {
}

