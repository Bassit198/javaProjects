package bassit.webregistrationapi.services;

import bassit.webregistrationapi.model.Person;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log
@Service
public class PersonService {

    private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    private final PersonRepo personRepo;

    @Autowired
    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public void createPerson(Person person) {
        Person optionalPerson = personRepo.findPersonByEmail(person.getEmail());
        if(optionalPerson != null){
            throw new IllegalStateException("Person already exists. Please try again.");
        }else{
            String hashPassword = bcrypt.encode(person.getPassword());
            Person newPerson = new Person(
                    person.getFirstname(),
                    person.getLastname(),
                    person.getEmail(),
                    hashPassword,
                    person.getPhoneNumber(),
                    person.getAddress1(),
                    person.getAddress2(),
                    person.getState(),
                    person.getCountry(),
                    person.getZipCode(),
                    person.getAreaCode());
            log.info("Email and username not existing so person created with: " + person.getEmail());
            personRepo.save(newPerson);
        }
    }


    public Person getPersonObjectByEmail(String email) {
        Person person = personRepo.findPersonByEmail(email);
        if(person == null){
            throw new IllegalStateException("Person does not exists. Please try again.");
        }else{
            return person;
        }
    }

    public int getPersonObjectByEmailPassword(String email, String password) {
        Person person = personRepo.findPersonByEmail(email);
        if(person == null){
            throw new IllegalStateException("Incorrect credentials. Please try again.");
        }else{
            if(bcrypt.matches(password, person.getPassword())){
                return 200;
            }else{
                return 404;
            }

        }
    }
}
