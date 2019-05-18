package pl.sda.spring.bookstore;

import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorRestController {

   private AuthorMapper authorMapper;
   private AuthorService authorService;

   public AuthorRestController(AuthorMapper authorMapper, AuthorService authorService) {
      this.authorMapper = authorMapper;
      this.authorService = authorService;
   }

   @PostMapping("/api/authors")
   public long addAuthor(@RequestBody AddAuthorDto author) {
      Author savedAuthor = authorService.add(authorMapper.mapToEntity(author));
      return savedAuthor.getId();
   }

   @DeleteMapping("/api/authors/{id}")
   public void deleteAuthor(@PathVariable long id) {
      authorService.delete(id);
   }

}
