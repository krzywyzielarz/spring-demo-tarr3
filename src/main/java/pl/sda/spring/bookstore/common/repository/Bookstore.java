package pl.sda.spring.bookstore.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.spring.bookstore.common.model.Book;

import java.util.Collection;

public interface Bookstore extends JpaRepository<Book, Long> {
   Collection<Book> findByTitle(String title);
}
