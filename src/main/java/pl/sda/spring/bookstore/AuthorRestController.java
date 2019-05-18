package pl.sda.spring.bookstore;

import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;

@RestController
public class AuthorRestController {

   private AuthorRepository authorRepository;
   private AuthorMapper authorMapper;

   public AuthorRestController(AuthorRepository authorRepository, AuthorMapper authorMapper) {
      this.authorRepository = authorRepository;
      this.authorMapper = authorMapper;
   }

   @PostMapping("/api/authors")
   public long addAuthor(@RequestBody AddAuthorDto author) {
      Author savedAuthor = authorRepository.save(authorMapper.mapToEntity(author));
      return savedAuthor.getId();
   }

   @DeleteMapping("/api/authors/{id}")
   @Transactional
   public void deleteAuthor(@PathVariable long id) {
      authorRepository.findById(id).ifPresent(author -> {
         Collection<Book> books = author.getBooks();
         books.forEach(book -> book.setAuthor(null));
         books.clear();
         authorRepository.delete(author);
      });
      // authorRepository.deleteById(id);
   }

}
