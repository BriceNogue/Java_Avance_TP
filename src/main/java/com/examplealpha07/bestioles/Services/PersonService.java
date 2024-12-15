package com.examplealpha07.bestioles.Services;

import com.examplealpha07.bestioles.Contracts.IPersonService;
import com.examplealpha07.bestioles.Entities.Person;
import com.examplealpha07.bestioles.Repositories.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements IPersonService {

    private final IPersonRepository personRepository;

    @Autowired
    public PersonService(IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> findByLastnameOrFirstname(String lastname, String firstname) {
        return personRepository.findByLastnameOrFirstname(lastname, firstname);
    }

    @Override
    public List<Person> findByAgeGreaterThanEqual(int age) {
        return personRepository.findByAgeGreaterThanEqual(age);
    }

    @Override
    public List<Person> findByAgeBetweenMIN_MAX(int ageMin, int ageMax) {
        return personRepository.findByAgeBetweenMIN_MAX(ageMin, ageMax);
    }

    @Override
    public List<Person> findByAnimals(int animal_id) {
        return personRepository.findByAnimals(animal_id);
    }

    @Override
    public Person getPersonById(int id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person createPerson(Person newPerson) {
        if (newPerson != null) {

            Person person = new Person();
            person.setFirstname(newPerson.getFirstname());
            person.setLastname(newPerson.getLastname());
            person.setAge(newPerson.getAge());
            person.setAnimals(newPerson.getAnimals());

            return personRepository.save(person);
        }else {
            return null;
        }
    }

    @Override
    public Person updatePerson(Person updatedPerson) {
        if (updatedPerson != null) {

            Person person = new Person();
            person.setId(updatedPerson.getId());
            person.setFirstname(updatedPerson.getFirstname());
            person.setLastname(updatedPerson.getLastname());
            person.setAge(updatedPerson.getAge());
            person.setAnimals(updatedPerson.getAnimals());

            return personRepository.save(person);
        }else {
            return null;
        }
    }

    @Override
    public boolean deletePerson(int id) {
        if (id > 0) {
            personRepository.deleteById(id);
            return !personRepository.existsById(id);
        }else {
            return false;
        }
    }
}
