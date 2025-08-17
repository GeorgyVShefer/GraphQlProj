package org.example.graphqlproj.controller;

import org.example.graphqlproj.model.Author;
import org.example.graphqlproj.model.Book;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    private final List<Book> books = List
            .of(new Book("book1", "book1", 120, "author1"),
                    new Book("book2", "book2", 110, "author2"));

    private final List<Author> authors = new ArrayList();

    @MutationMapping
    public Author creatAuthor(@Argument String firstName, @Argument String lastName) {

        if(firstName != null){
            Author author = new Author("author " + firstName, firstName, lastName);
            authors.add(author);
            return author;
        }

       return null;
    }

    @QueryMapping
    public Book bookById(@Argument String id) {

        return books.stream().filter(book -> book.id().equals(id)).findFirst().orElse(null);
    }
}
