package bassit.webregistrationapi.controller;

import bassit.webregistrationapi.model.Person;
import bassit.webregistrationapi.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //CRUD
    //create person
    @PostMapping("/create")
    public void createPerson(@RequestBody Person person){
        personService.createPerson(person);
    }
    //get person
    @GetMapping("/getPerson/{email}")
    public Person getPersonObject(@PathVariable String email){
        return personService.getPersonObjectByEmail(email);
    }

    @GetMapping("/getPerson/{email}/{password}")
    public int getPersonObject(@PathVariable String email, @PathVariable String password){
        return personService.getPersonObjectByEmailPassword(email, password);
    }
    //update person
    //delete person
}
