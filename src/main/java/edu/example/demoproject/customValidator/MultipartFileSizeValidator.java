//package edu.example.demoproject.customValidator;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//
//@Component
//public class MultipartFileSizeValidator implements ConstraintValidator<MultipartFileSizeValid, MultipartFile> {
//
//    private static final Integer MB=1024*1024;
//
//    private long maxSizeInMB;
//
//    @Override
//    public void initialize(MultipartFileSizeValid fileSize) {
//        this.maxSizeInMB = fileSize.maxSizeInMB();
//    }
//
//    @Override
//    public boolean isValid(MultipartFile multipartFile,
//                           ConstraintValidatorContext
//                                   constraintValidatorContext) {
//
//        if(multipartFile==null)
//            return true;
//
//        return multipartFile.getSize()<maxSizeInMB*MB;
//    }
//}