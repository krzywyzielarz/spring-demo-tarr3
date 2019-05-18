package pl.sda.spring.bookstore.common.dto;

import org.springframework.stereotype.Component;
import pl.sda.spring.bookstore.common.model.Author;

@Component
public class AuthorMapper {
   public Author mapToEntity(AddAuthorDto authorDto) {
      Author author = new Author();
      author.setName(authorDto.getName());
      author.setLastName(authorDto.getLastName());
      return author;
   }
}
