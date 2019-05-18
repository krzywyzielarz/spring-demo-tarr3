package pl.sda.spring.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.spring.bookstore.model.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {


}
