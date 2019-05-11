package pl.sda.spring.bookstore;

import org.springframework.data.repository.CrudRepository;

public interface Bookstore extends CrudRepository<Book, Long> {
   Iterable<Book> findByTitle(String title);
}
