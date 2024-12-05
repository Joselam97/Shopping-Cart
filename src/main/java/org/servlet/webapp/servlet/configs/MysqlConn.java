package org.servlet.webapp.servlet.configs;

import jakarta.inject.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;


@Qualifier // This annotation makes the annotation a CDI qualifier. A qualifier is used to differentiate between beans of the same type
@Retention(RetentionPolicy.RUNTIME) // This means that the annotation will be available at runtime
@Target({METHOD, FIELD, PARAMETER, TYPE, CONSTRUCTOR}) // Specifies that this annotation can be applied to methods, fields, parameters, types, and constructors

// Defines a custom annotation called MysqlConn
public @interface MysqlConn {
}
