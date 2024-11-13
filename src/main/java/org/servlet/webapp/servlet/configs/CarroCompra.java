package org.servlet.webapp.servlet.configs;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Named;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.*;

@SessionScoped
@Named
@Stereotype
@Retention(RUNTIME)
@Target(ElementType.TYPE)
public @interface CarroCompra {
}

