package com.example.librarymanagement.service;

import com.example.librarymanagement.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;

    // Setter method for bookRepository
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(String bookName) {
        System.out.println("Book added: " + bookName);
        bookRepository.save(bookName);
    }

    public void listBooks() {
        System.out.println("Listing all books...");
        bookRepository.findAll().forEach(System.out::println);
    }
}

package com.example.librarymanagement.repository;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private List<String> books = new ArrayList<>();

    public void save(String bookName) {
        books.add(bookName);
    }

    public List<String> findAll() {
        return books;
    }
}

package com.example.librarymanagement;

import com.example.librarymanagement.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = context.getBean(BookService.class);

        bookService.addBook("Spring in Action");
        bookService.listBooks();
    }
}
