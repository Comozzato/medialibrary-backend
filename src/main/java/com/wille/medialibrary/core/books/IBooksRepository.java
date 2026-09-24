package com.wille.medialibrary.core.books;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IBooksRepository extends JpaRepository <BooksModel, Long>{
    
}
