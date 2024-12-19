package com.examplealpha07.bestioles.Controllers;

import com.examplealpha07.bestioles.Common.CustomExceptions.EntityNotFoundException;
import com.examplealpha07.bestioles.Contracts.IPersonService;
import com.examplealpha07.bestioles.DTO.PersonDto;
import com.examplealpha07.bestioles.DTO.ResponseDTO;
import com.examplealpha07.bestioles.Entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public List<PersonDto> getAllPersons() {
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
    public ResponseDTO.GeneralResponse deletePerson(@PathVariable("id") int id) {

        if (id <= 0){
            return new ResponseDTO.GeneralResponse(false, "Invalid person id!", null);
        }

        return personService.deletePerson(id);
    }

    @GetMapping("/all/page")
    public Page<PersonDto> getAllPersons(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        if(pageNum < 0 || pageSize < 0){
            return null;
        }
        return personService.findAllAndPageable(pageNum, pageSize);
    }
}
