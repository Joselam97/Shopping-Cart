package org.servlet.webapp.servlet.repositories;

import jakarta.inject.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Qualifier // Marks this annotation as a CDI (Contexts and Dependency Injection) qualifier
@Retention(RetentionPolicy.RUNTIME) // Specifies that the annotation is retained at runtime.
@Target({CONSTRUCTOR, METHOD, FIELD, PARAMETER, TYPE}) // Indicates where this annotation can be applied

// Custom annotation for marking JDBC-based repository implementations
public @interface RepositoryJdbc {
}
