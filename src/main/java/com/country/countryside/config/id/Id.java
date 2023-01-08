package com.country.countryside.config.id;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD })
@Retention(RUNTIME)
public @interface Id {

    Class<?> strategy() default ObjectIdGenerators.UUIDGenerator.class;

}
