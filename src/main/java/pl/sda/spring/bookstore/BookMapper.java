package pl.sda.spring.bookstore;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class BookMapper {

   private AuthorRepository authorRepository;

   public BookMapper(AuthorRepository authorRepository) {
      this.authorRepository = authorRepository;
   }

   public Iterable<BookDto> mapToDto(Collection<Book> books) {
      return books
              .stream().map(BookDto::fromEntity)
              .collect(Collectors.toList());
   }

   public Book mapToEntity(AddBookDto addBookDto) {
      Book book = new Book();
      book.setTitle(addBookDto.getTitle());
      Long authorId = addBookDto.getAuthorId();
      if(authorId != null) {
         Author author = authorRepository.findById(authorId).orElseThrow(
                 () -> new IllegalArgumentException("Author doesn't exist")
         );
         book.setAuthor(author);
      }
      return book;
   }
}
