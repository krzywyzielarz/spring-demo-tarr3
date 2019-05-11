package pl.sda.spring.bookstore;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookstoreRestController {

   private Bookstore bookstore;

   public BookstoreRestController(Bookstore bookRepository) {
      this.bookstore = bookRepository;
   }

   @GetMapping
   public Iterable<Book> getAllBooks(@RequestParam(required = false) String title) {
      if(title != null)
         return bookstore.findByTitle(title);
      return bookstore.findAll();
   }

   @PostMapping
   public void addBook(@RequestBody Book book) {
      bookstore.save(book);
   }

}
