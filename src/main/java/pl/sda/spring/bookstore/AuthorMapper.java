package pl.sda.spring.bookstore;

import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {
   public Author mapToEntity(AddAuthorDto authorDto) {
      Author author = new Author();
      author.setName(authorDto.getName());
      author.setLastName(authorDto.getLastName());
      return author;
   }
}
