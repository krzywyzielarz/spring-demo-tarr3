package pl.sda.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import pl.sda.spring.bookstore.Bookstore;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
           "then only 'Effective Java' book is returned"
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

   private void addBook(String title) throws Exception {
      mockMvc.perform(
              post("/api/books")
                      .content(String.format("{\"title\": \"%s\"}", title))
                      .contentType(MediaType.APPLICATION_JSON)
      );
   }
}
