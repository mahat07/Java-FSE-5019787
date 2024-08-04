package com.example.librarymanagement.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.librarymanagement")
public class AppConfig {
}

package com.example.librarymanagement.service;

import org.springframework.stereotype.Service;

@Service
public class LibraryService {
    public void addBook(String bookName) {
        System.out.println("Book added: " + bookName);
    }

    public void listBooks() {
        System.out.println("Listing all books...");
        // You can add code here to list all books
    }
}

package com.example.librarymanagement;

import com.example.librarymanagement.service.LibraryService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LibraryManagementApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(com.example.librarymanagement.config.AppConfig.class);
        LibraryService libraryService = context.getBean(LibraryService.class);

        libraryService.addBook("Spring in Action");
        libraryService.listBooks();
    }
}
