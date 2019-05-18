package pl.sda.spring.bookstore.controller;

import org.springframework.web.bind.annotation.*;
import pl.sda.spring.bookstore.service.BookstoreService;
import pl.sda.spring.bookstore.dto.AddBookDto;
import pl.sda.spring.bookstore.dto.BookDto;
import pl.sda.spring.bookstore.dto.BookMapper;

@RestController
@RequestMapping("/api/books")
public class BookstoreRestController {

   private BookMapper bookMapper;
   private BookstoreService bookstoreService;

   public BookstoreRestController(BookMapper bookMapper, BookstoreService bookstoreService) {
      this.bookMapper = bookMapper;
      this.bookstoreService = bookstoreService;
   }

   @GetMapping
   public Iterable<BookDto> getAllBooks(@RequestParam(required = false) String title) {
      if (title != null)
         return bookMapper.mapToDto(bookstoreService.findByTitle(title));
      return bookMapper.mapToDto(bookstoreService.findAll());
   }

   @PostMapping
   public long addBook(@RequestBody AddBookDto addBookDto) {
      return bookstoreService.add(bookMapper.mapToEntity(addBookDto));
   }

   @DeleteMapping("/{bookId}")
   public void deleteBook(@PathVariable(name = "bookId") long id) {
      bookstoreService.delete(id);
   }
}
