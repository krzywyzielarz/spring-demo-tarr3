package pl.sda.spring.greeting;

import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.Optional;


@RestController
@RequestMapping("/api/greetings")
public class GreetingRestController {

   private GreetingRepository greetingRepository;

   public GreetingRestController(GreetingRepository greetingRepository) {
      this.greetingRepository = greetingRepository;
      greetingRepository.saveAll(Arrays.asList(
              new Greeting("hi", "cygan"),
              new Greeting("hello", "tomek"),
              new Greeting("welcome", "adrian")));
   }

   @GetMapping("/{id}")
   public Optional<Greeting> getGreeting(@PathVariable long id){
      return greetingRepository.findById(id);
   }

   @DeleteMapping("/{id}")
   public void deleteGreeting(@PathVariable long id){
      greetingRepository.deleteById(id);
   }

   @PostMapping
   public void addGreeting(@RequestBody Greeting greeting) {
      greetingRepository.save(greeting);
   }

   @GetMapping
   public Iterable<Greeting> getAllGreetings(@RequestParam(required = false) String msg, @RequestParam(required = false) String autor) {
      if (autor != null)
         return greetingRepository.findByAutor(autor);

      if (msg != null)
         return greetingRepository.findByMsg(msg);
      return greetingRepository.findAll();
   }
}