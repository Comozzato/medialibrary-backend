package com.wille.medialibrary.core.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wille.medialibrary.core.books.actions.FileStorage;
import com.wille.medialibrary.core.books.request.BooksUploadRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/books") 
public class BooksControlle {

  @Autowired
	private FileStorage fileStorage;

  @PostMapping(value = "/" , consumes  = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity <?> upload(@Valid 
  BooksUploadRequest request
) {
    String fileName = fileStorage.storeFile(request.getFile());

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/storage/books/")
			.path(fileName).toUriString();

    
    return ResponseEntity.status(HttpStatus.ACCEPTED).body( fileDownloadUri);
  }
}
