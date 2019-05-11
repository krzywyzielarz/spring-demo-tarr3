package pl.sda.spring.greeting;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Greeting {

   @Id
   @GeneratedValue
   private long id;
   private String msg;
   private String autor;

   public Greeting() {
   }

   public Greeting(String msg, String autor) {
      this.msg = msg;
      this.autor = autor;
   }
}