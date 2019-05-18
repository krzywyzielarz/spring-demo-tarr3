package pl.sda.spring.bookstore;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface Bookstore extends JpaRepository<Book, Long> {
   Collection<Book> findByTitle(String title);
}
