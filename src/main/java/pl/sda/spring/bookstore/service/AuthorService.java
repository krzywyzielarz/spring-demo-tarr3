package pl.sda.spring.bookstore.service;

import org.springframework.stereotype.Service;
import pl.sda.spring.bookstore.model.Author;
import pl.sda.spring.bookstore.model.Book;
import pl.sda.spring.bookstore.repository.AuthorRepository;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class AuthorService {

   private AuthorRepository authorRepository;

   public AuthorService(AuthorRepository authorRepository) {
      this.authorRepository = authorRepository;
   }

   public Author add(Author author) {
      return authorRepository.save(author);
   }

   @Transactional
   public void delete(long id) {
      authorRepository.findById(id).ifPresent(author -> {
         Collection<Book> books = author.getBooks();
         books.forEach(book -> book.setAuthor(null));
         books.clear();
         authorRepository.delete(author);
      });
   }
}
