package pl.sda.spring.bookstore.common.dto;

import lombok.Data;

@Data
public class AddBookDto {
   private String title;
   private Long authorId;
}
