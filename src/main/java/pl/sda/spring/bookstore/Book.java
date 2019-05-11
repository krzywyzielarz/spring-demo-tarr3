package pl.sda.spring.bookstore;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Book {
   @Id
   @GeneratedValue
   private long id;
   private String title;
}
