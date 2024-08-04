package com.example.librarymanagement.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.librarymanagement.service.BookService.*(..))")
    public void logBeforeMethod() {
        System.out.println("LoggingAspect: Before method execution");
    }

    @After("execution(* com.example.librarymanagement.service.BookService.*(..))")
    public void logAfterMethod() {
        System.out.println("LoggingAspect: After method execution");
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

        bookService.printInitialBookName();
        bookService.addBook("Spring in Action");
        bookService.listBooks();
    }
}
