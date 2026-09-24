package com.wille.medialibrary.validations;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EpubFileValidator
        implements ConstraintValidator<ValidEpub, MultipartFile> {

    @Override
    public boolean isValid(
            MultipartFile file,
            ConstraintValidatorContext context
    ) {

        if (file == null || file.isEmpty()) {
            return false;
        }

        String contentType = file.getContentType();

        return "application/epub+zip".equals(contentType);
    }
}