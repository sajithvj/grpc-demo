package com.example.grpcdemo.repository;


import com.example.grpcdemo.model.Books;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BooksRepository extends MongoRepository<Books,String> {
}
