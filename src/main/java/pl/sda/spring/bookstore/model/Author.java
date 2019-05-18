package pl.sda.spring.bookstore.model;

import lombok.Data;
import pl.sda.spring.bookstore.model.Book;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class Author {
   @Id
   @GeneratedValue
   private long id;
   private String name;
   private String lastName;
   @OneToMany(mappedBy = "author")
   private Collection<Book> books;
}