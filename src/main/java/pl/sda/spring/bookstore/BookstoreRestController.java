package pl.sda.spring.bookstore;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookstoreRestController {

   private Bookstore bookstore;
   private BookMapper bookMapper;

   public BookstoreRestController(Bookstore bookstore, BookMapper bookMapper) {
      this.bookstore = bookstore;
      this.bookMapper = bookMapper;
   }

   @GetMapping
   public Iterable<BookDto> getAllBooks(@RequestParam(required = false) String title) {
      if (title != null)
         return bookMapper.mapToDto(bookstore.findByTitle(title));
      return bookMapper.mapToDto(bookstore.findAll());
   }

   @PostMapping
   public long addBook(@RequestBody AddBookDto addBookDto) {
      return bookstore.save(bookMapper.mapToEntity(addBookDto)).getId();
   }

   @DeleteMapping("/{bookId}")
   public void deleteBook(@PathVariable(name = "bookId") long id) {
      bookstore.deleteById(id);
   }

}
