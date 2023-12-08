//package edu.example.demoproject.customValidator;
//
//
//import jakarta.validation.Constraint;
//import jakarta.validation.Payload;
//
//@Constraint(validatedBy = MultipartFileSizeValidator.class)
//public @interface MultipartFileSizeValid {
//    long maxSizeInMB() default 5120000;
//
//    String message() default "DEFAULT_ERROR_MESSAGE";
//
//    Class<?>[] groups() default {};
//
//    Class<? extends Payload>[] payload() default {};
//}