package org.servlet.webapp.servlet.configs;

import jakarta.inject.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;


@Qualifier // Marks this annotation as a qualifier for dependency injection
@Retention(RetentionPolicy.RUNTIME) // The annotation will be available at runtime
@Target({METHOD, FIELD, PARAMETER, TYPE, CONSTRUCTOR}) // The annotation can be applied to methods, fields, parameters, types, and constructors

// Declaration of the custom annotation
public @interface ProductoServicePrincipal {
}
