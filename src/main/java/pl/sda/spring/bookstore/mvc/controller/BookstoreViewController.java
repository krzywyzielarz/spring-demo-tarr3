package pl.sda.spring.bookstore.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.spring.bookstore.common.dto.AddBookDto;
import pl.sda.spring.bookstore.common.dto.BookDto;
import pl.sda.spring.bookstore.common.dto.BookMapper;
import pl.sda.spring.bookstore.common.service.BookstoreService;

import java.security.Principal;

@Controller
public class BookstoreViewController {

    private BookstoreService bookstoreService;
    private BookMapper bookMapper;

    public BookstoreViewController(BookstoreService bookstoreService, BookMapper bookMapper) {
        this.bookstoreService = bookstoreService;
        this.bookMapper = bookMapper;
    }

    @GetMapping("/mvc/bookstore/all")
    public String showAllBooks(Model model, Principal principal) {
        Iterable<BookDto> allBooks = bookMapper.mapToDto(bookstoreService.findAll());
        model.addAttribute("allBooks", allBooks);
        model.addAttribute("loginUser", principal.getName());
        return "bookstore/all-books";
    }

    @PostMapping("/mvc/bookstore/add")
    public String addBook(AddBookDto addBookDto) {
        bookstoreService.add(bookMapper.mapToEntity(addBookDto));
        return "redirect:/mvc/bookstore/all";
        // return "bookstore/all-books";
    }

}
