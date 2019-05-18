package pl.sda.spring.bookstore;

import lombok.Data;

@Data
public class AddBookDto {
   private String title;
   private Long authorId;
}
