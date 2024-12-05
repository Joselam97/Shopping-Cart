package org.servlet.webapp.servlet.configs;


import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Named;
import org.servlet.webapp.servlet.interceptors.Logging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Logging // annotation likely triggers a custom logging mechanism for this service class.
@Stereotype // This annotation defines a stereotype, which is a meta-annotation used to combine multiple behaviors in one.
@Named // Makes this class available for dependency injection with a default name.
@Target(ElementType.TYPE) // This annotation can only be applied to types
@Retention(RetentionPolicy.RUNTIME) // The annotation will be available at runtime for frameworks like CDI to process.

// Declares the custom annotation named @Service
public @interface Service {
}
