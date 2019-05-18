package pl.sda.spring.bookstore.common.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Book {
   @Id
   @GeneratedValue
   private long id;
   private String title;
   @ManyToOne
   private Author author;
}
