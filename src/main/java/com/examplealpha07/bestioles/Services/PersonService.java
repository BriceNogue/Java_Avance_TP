package com.examplealpha07.bestioles.Services;

import com.examplealpha07.bestioles.Contracts.IPersonService;
import com.examplealpha07.bestioles.Entities.Person;
import com.examplealpha07.bestioles.Repositories.IPersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

//import javax.transaction.Transactional;
import java.util.List;

@Service
public class PersonService implements IPersonService {

    private final IPersonRepository IPersonRepository;

    @Autowired
    public PersonService(IPersonRepository IPersonRepository) {
        this.IPersonRepository = IPersonRepository;
    }

    @Override
    public List<Person> findByLastnameOrFirstname(String lastname, String firstname) {
        return IPersonRepository.findByLastnameOrFirstname(lastname, firstname);
    }

    @Override
    public List<Person> findByAgeGreaterThanEqual(int age) {
        return IPersonRepository.findByAgeGreaterThanEqual(age);
    }

    @Override
    public List<Person> findByAgeBetweenMIN_MAX(int ageMin, int ageMax) {
        return IPersonRepository.findByAgeBetweenMIN_MAX(ageMin, ageMax);
    }

    @Override
    public List<Person> findByAnimals(int animal_id) {
        return IPersonRepository.findByAnimals(animal_id);
    }

    @Override
    public Person getPersonById(int id) {
        return IPersonRepository.findById(id).orElse(null);
    }

    @Override
    public List<Person> getAllPersons() {
        return IPersonRepository.findAll();
    }

    @Override
    public Person createPerson(Person newPerson) {
        if (newPerson != null) {

            Person person = new Person();
            person.setFirstname(newPerson.getFirstname());
            person.setLastname(newPerson.getLastname());
            person.setAge(newPerson.getAge());
            person.setAnimals(newPerson.getAnimals());

            return IPersonRepository.save(person);
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

            return IPersonRepository.save(person);
        }else {
            return null;
        }
    }

    @Override
    public boolean deletePerson(int id) {
        if (id > 0) {
            IPersonRepository.deleteById(id);
            return !IPersonRepository.existsById(id);
        }else {
            return false;
        }
    }

    // Renvoie un résultat paginé
    @Override
    public Page<Person> findAllAndPageable(Pageable pageable) {
        return IPersonRepository.findAll(pageable);
    }

    @Transactional
    public void deleteOrphanPersons() {
        IPersonRepository.deletePersonsWithoutAnimals();
    }

    @Transactional
    public void generateRandomPersons(int nbr) {
        IPersonRepository.generateEntities(nbr);
    }
}
