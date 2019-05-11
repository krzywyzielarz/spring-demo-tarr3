package pl.sda.spring.greeting;

import org.springframework.data.repository.CrudRepository;

public interface GreetingRepository extends CrudRepository<Greeting, Long> {
   Iterable<Greeting> findByMsg(String msg);
   Iterable<Greeting> findByAutor(String autor);
}
