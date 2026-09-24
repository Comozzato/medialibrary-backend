package com.wille.medialibrary.core.books.request;

import org.springframework.web.multipart.MultipartFile;

import com.wille.medialibrary.validations.ValidEpub;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data 
public class BooksUploadRequest {
    
    @NotNull (message="File is required")
    @ValidEpub()
    private MultipartFile file;

}   

