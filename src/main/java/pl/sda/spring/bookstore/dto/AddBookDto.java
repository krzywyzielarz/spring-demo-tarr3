package pl.sda.spring.bookstore.dto;

import lombok.Data;

@Data
public class AddBookDto {
   private String title;
   private Long authorId;
}
