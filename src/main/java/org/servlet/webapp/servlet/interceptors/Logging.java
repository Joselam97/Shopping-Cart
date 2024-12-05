package org.servlet.webapp.servlet.interceptors;

import jakarta.interceptor.InterceptorBinding;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// The @InterceptorBinding annotation marks this annotation as one that is used to define interceptors
@InterceptorBinding
// This means the annotation will be available at runtime
@Retention(RetentionPolicy.RUNTIME)
// This allows the annotation to be applied to methods or types (classes or interfaces)
@Target({ElementType.METHOD, ElementType.TYPE})

public @interface Logging {
}
