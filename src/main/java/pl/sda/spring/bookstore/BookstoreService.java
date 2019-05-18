package pl.sda.spring.bookstore;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BookstoreService {

   private Bookstore bookstore;

   public BookstoreService(Bookstore bookstore) {
      this.bookstore = bookstore;
   }

   public Collection<Book> findByTitle(String title) {
      return bookstore.findByTitle(title);
   }

   public Collection<Book> findAll() {
      return bookstore.findAll();
   }

   public long add(Book book) {
      return bookstore.save(book).getId();
   }

   public void delete(long id) {
      bookstore.deleteById(id);
   }
}
