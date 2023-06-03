package edu.example.demoproject.customValidator;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = MultipartFileSizeValidator.class)
public @interface MultipartFileSizeValid {
    long maxSizeInMB() default 512;

    String message() default "DEFAULT_ERROR_MESSAGE";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}