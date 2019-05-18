package pl.sda.spring.bookstore.common.dto;

import lombok.Data;
import pl.sda.spring.bookstore.common.model.Author;
import pl.sda.spring.bookstore.common.model.Book;

@Data
public class BookDto {
   private String title;
   private long id;
   private AuthorDto author;

   public static BookDto fromEntity(Book book) {
      BookDto bookDto = new BookDto();
      bookDto.setId(book.getId());
      bookDto.setTitle(book.getTitle());
      Author author = book.getAuthor();

      if(author != null) {
         AuthorDto authorDto = new AuthorDto();
         authorDto.setName(author.getName());
         authorDto.setLastName(author.getLastName());
         bookDto.setAuthor(authorDto);
      }

      return bookDto;
   }

   @Data
   public static class AuthorDto {
      private String name;
      private String lastName;
   }
}
