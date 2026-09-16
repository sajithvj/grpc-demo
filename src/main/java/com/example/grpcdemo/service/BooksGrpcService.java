package com.example.grpcdemo.service;

import com.example.grpcdemo.model.Books;
import com.example.grpcdemo.proto.Book;
import com.example.grpcdemo.proto.BookList;

import com.example.grpcdemo.proto.BookRequest;
import com.example.grpcdemo.proto.BookServiceGrpc;
import com.example.grpcdemo.proto.Empty;
import com.example.grpcdemo.repository.BooksRepository;
import io.grpc.stub.StreamObserver;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class BooksGrpcService extends BookServiceGrpc.BookServiceImplBase {
  @Autowired
  BooksRepository booksRepository;
  private final KafkaProducerService kafkaProducerService;


  public BooksGrpcService(KafkaProducerService kafkaProducerService) {
    this.kafkaProducerService = kafkaProducerService;

  }

  @Override
  public void getAllBooks(Empty request, StreamObserver<BookList> responseObserver) {
    List<Books> books = booksRepository.findAll();
    BookList bookResponse = BookList.newBuilder().addAllBooks(
        books.stream().map(entity -> Book.newBuilder()
            .setId(entity.id().toString()).setName(entity.name()).setAuthor(entity.author())
            .setPrice(entity.price().intValue()).build()).toList()).build();
    responseObserver.onNext(bookResponse);
    responseObserver.onCompleted();
  }
  @Override
  public void getBookById(BookRequest request,
      StreamObserver<Book> responseObserver) {
    Books book = booksRepository.findById(request.getId()).orElse(null);

    if (book != null) {
      Book bookResponse = Book.newBuilder()
          .setId(book.id().toString())
          .setName(book.name())
          .setAuthor(book.author())
          .setPrice(book.price().intValue())
          .build();
      kafkaProducerService.publishOrderCreated( bookResponse.getId(),bookResponse.toString());
      responseObserver.onNext(bookResponse);


    } else {
      responseObserver.onError(new Exception("Book not found"));
      //kafkaProducerService.sendMessage("Book not found: " + request.getId());
    }
    responseObserver.onCompleted();
  }

}
