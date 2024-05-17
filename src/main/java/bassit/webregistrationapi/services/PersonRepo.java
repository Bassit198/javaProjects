package bassit.webregistrationapi.services;

import bassit.webregistrationapi.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {

    //Optional<Person> findPersonByEmail(String email);

    Person findPersonByEmail(String email);

    Person findPersonByEmailAndPassword(String email, String password);
}
