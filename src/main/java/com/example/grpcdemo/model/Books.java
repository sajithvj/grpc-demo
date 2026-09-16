package com.example.grpcdemo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("books")
public record Books(
@Id Object id,
    String name,
    String author,
    Long price
) {

}
