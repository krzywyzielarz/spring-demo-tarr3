package pl.sda.spring.bookstore.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sda.spring.bookstore.common.dto.BookDto;
import pl.sda.spring.bookstore.common.dto.BookMapper;
import pl.sda.spring.bookstore.common.service.BookstoreService;

@Controller
public class BookstoreViewController {

   private BookstoreService bookstoreService;
   private BookMapper bookMapper;

   public BookstoreViewController(BookstoreService bookstoreService, BookMapper bookMapper) {
      this.bookstoreService = bookstoreService;
      this.bookMapper = bookMapper;
   }

   @GetMapping("/mvc/bookstore/all")
   public String showAllBooks(Model model) {
      Iterable<BookDto> allBooks = bookMapper.mapToDto(bookstoreService.findAll());
      model.addAttribute("allBooks", allBooks);
      return "bookstore/all-books";
   }
}
