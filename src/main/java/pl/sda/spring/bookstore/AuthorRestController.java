package pl.sda.spring.bookstore;

import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.util.Collection;

@RestController

public class AuthorRestController {

   private AuthorRepository authorRepository;

   public AuthorRestController(AuthorRepository authorRepository) {
      this.authorRepository = authorRepository;
   }

   @PostMapping("/api/authors")
   public long addAuthor(@RequestBody Author author) {
      Author savedAuthor = authorRepository.save(author);
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
