package com.examplealpha07.bestioles.Controllers;

import com.examplealpha07.bestioles.Contracts.IPersonService;
import com.examplealpha07.bestioles.Entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final IPersonService personService;

    @Autowired
    public PersonController(IPersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/all")
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable("id") int id) {
        return personService.getPersonById(id);
    }

    @PostMapping("/create")
    public String createPerson(@RequestBody Person newPerson) {

        Person person = personService.createPerson(newPerson);
        String response = person != null ? person.getFirstname() + " created successfully." : "Error creating person.";

        return response;
    }

    @PutMapping("/update/{id}")
    public String updatePerson(@PathVariable("id") int id, @RequestBody Person personToUpdate) {

        if (id != personToUpdate.getId() || id <= 0) {
            return "Error updating person : incorrect id.";
        }

        Person person = personService.updatePerson(personToUpdate);

        String response = person != null ? person.getFirstname() + " updated successfully." : "Error updating " + personToUpdate.getFirstname();

        return response;
    }

    @DeleteMapping("/delete/{id}")
    public String deletePerson(@PathVariable("id") int id) {

        String response = "";

        if (id <= 0){
            return "Invalid person id!";
        }

        Person person = personService.getPersonById(id);

        if (person != null) {
            response = personService.deletePerson(id) ? person.getFirstname() + " deleted successfully." :
                    "Error deleting " + person.getFirstname() + "!";
        }else {
            response = "Person not found!";
        }

        return response;
    }
}
