package pl.sda.spring.bookstore.common.model;

import lombok.Data;

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