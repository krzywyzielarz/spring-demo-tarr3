package pl.sda.spring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BookstoreRestApiIntegrationTest {

   @Autowired
   private MockMvc mockMvc;
//   @Autowired
//   private Bookstore bookstore;

//   @BeforeEach
//   void cleanDb() {
//      bookstore.deleteAll();
//   }

   @DisplayName(
           "when GET on /api/books, then OK status is returned"
   )
   @Test
   void test() throws Exception {
      // when
      mockMvc.perform(get("/api/books"))

              // then
              .andExpect(status().isOk());
   }

   @DisplayName(
           "when POST on /api/books, then book is created"
   )
   @Test
   void test1() throws Exception {
      // when
      mockMvc.perform(
              post("/api/books")
              .content("{\"title\": \"Clean Code\"}")
              .contentType(MediaType.APPLICATION_JSON)
      )

              // then
              .andExpect(status().isOk());
      mockMvc.perform(get("/api/books"))
              .andExpect(jsonPath("$", hasSize(1)))
              .andExpect(jsonPath("$[0].title", is("Clean Code")));
   }

   @DisplayName(
           "given 3 books 'Clean Code', 'Effective Java' and 'Design Patterns', " +
           "when GET with title param set to 'Clean Code', " +
           "then only 'Clean Code' book is returned"
   )
   @Test
   void test2() throws Exception {
      // given
      addBook("Clean Code");
      addBook("Effective Java");
      addBook("Design Patterns");

      // when
      mockMvc.perform(
              get("/api/books")
              .param("title", "Clean Code")
      )

      // then
      .andExpect(status().isOk())
              .andExpect(jsonPath("$", hasSize(1)))
              .andExpect(jsonPath("$[0].title", is("Clean Code")));
   }


   @DisplayName(
           "Jeśli autor Jan brzechwa istnieje w naszej bazie, " +
           "Gdy dodajamy książkę napisaną przez Brzechwe," +
           "To Gdy pobierzemy książkę to widzimy jej autora"
   )
   @Test
   void test3() throws Exception {
      //given
      long authorId = addAuthor("Jan", "Brzechwa");
      String bookJson = String.format(
              "{" +
              "\"title\": \"Lokomotywa\"," +
              "\"authorId\": %s" +
              "}", authorId);

      // when
      mockMvc.perform(
              post("/api/books")
              .content(bookJson)
              .contentType(MediaType.APPLICATION_JSON)
      )

      // then
      .andExpect(status().isOk());
      mockMvc.perform(get("/api/books"))
              .andExpect(jsonPath("$", hasSize(1)))
              .andExpect(jsonPath("$[0].author.name", is("Jan")))
              .andExpect(jsonPath("$[0].author.lastName", is("Brzechwa")));
   }

   @DisplayName(
           "given 'Clean Code' and 'Domain-driven design' books, " +
           "when delete 'Domain-driven design' book, " +
           "then only 'Clean Code' is left in the db"
   )
   @Test
   void test4() throws Exception {
      // given
      addBook("Clean Code");
      long dddId = addBook("Domain-driven design");

      // when
      mockMvc.perform(delete("/api/books/{id}", dddId))

              // then
              .andExpect(status().isOk());
      mockMvc.perform(get("/api/books"))
              .andExpect(jsonPath("$", hasSize(1)))
              .andExpect(jsonPath("$[0].title", is("Clean Code")));
   }

   private long addAuthor(String name, String  lastName) throws Exception {
      String authorJson = String.format("{" +
              "\"name\": \"%s\"," +
              "\"lastName\": \"%s\"" +
              "}", name, lastName);
      String authorId = mockMvc.perform(
              post("/api/authors")
                      .content(authorJson)
                      .contentType(MediaType.APPLICATION_JSON)

      )
              .andExpect(status().isOk())
              .andReturn().getResponse().getContentAsString();
      return Long.parseLong(authorId);
   }


   private long addBook(String title) throws Exception {
      String bookId = mockMvc.perform(
              post("/api/books")
                      .content(String.format("{\"title\": \"%s\"}", title))
                      .contentType(MediaType.APPLICATION_JSON)
      )
              .andReturn().getResponse().getContentAsString();
      return Long.parseLong(bookId);
   }
}
