package org.servlet.webapp.servlet.repositories;

import jakarta.inject.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

// Defines this as a CDI qualifier for dependency injection.
@Qualifier
// Keeps the annotation available at runtime.
@Retention(RetentionPolicy.RUNTIME)
// Specifies valid targets for this annotation.
@Target({CONSTRUCTOR, METHOD, FIELD, PARAMETER, TYPE})

public @interface RepositoryJpa {
    // Custom annotation to mark JPA-based repository implementations.
}
