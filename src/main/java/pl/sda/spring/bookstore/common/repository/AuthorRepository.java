package pl.sda.spring.bookstore.common.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.spring.bookstore.common.model.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {


}
