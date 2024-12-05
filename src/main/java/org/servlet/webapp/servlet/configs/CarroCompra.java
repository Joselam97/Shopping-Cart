package org.servlet.webapp.servlet.configs;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Named;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.*;

@SessionScoped  // Indicates that the bean is session-scoped, meaning its lifespan is tied to an HTTP session.
@Named // Makes the bean accessible by name in the context of dependency injection (CDI).
@Stereotype // This annotation marks the class as a stereotype, allowing it to aggregate multiple annotations for ease of use.
@Retention(RUNTIME) // This specifies that the annotation should be retained at runtime, allowing reflection and access to the annotation during runtime
@Target(ElementType.TYPE) // This defines the allowed target of the annotation, which in this case is the type (class or interface)
public @interface CarroCompra {
}

