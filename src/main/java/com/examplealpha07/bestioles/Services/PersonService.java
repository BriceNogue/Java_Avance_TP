package com.examplealpha07.bestioles.Services;

import com.examplealpha07.bestioles.Common.CustomExceptions.EntityToCreateHasAnIdException;
import com.examplealpha07.bestioles.Common.CustomExceptions.EntityNotFoundException;
import com.examplealpha07.bestioles.Common.CustomExceptions.EntityToUpdateHasNoIdException;
import com.examplealpha07.bestioles.Contracts.IPersonService;
import com.examplealpha07.bestioles.DTO.Mappers.PersonDtoMapper;
import com.examplealpha07.bestioles.DTO.PersonDto;
import com.examplealpha07.bestioles.DTO.ResponseDTO;
import com.examplealpha07.bestioles.Entities.Person;
import com.examplealpha07.bestioles.Repositories.IPersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

//import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService implements IPersonService {

    private final IPersonRepository IPersonRepository;
    private final PersonDtoMapper personDtoMapper;

    @Autowired
    public PersonService(IPersonRepository IPersonRepository, PersonDtoMapper personDtoMapper) {
        this.IPersonRepository = IPersonRepository;
        this.personDtoMapper = personDtoMapper;
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
        Person person = IPersonRepository.findById(id).orElse(null);
        if (person == null) {
            throw new EntityNotFoundException();
        }
        return person;
    }

    @Override
    public List<PersonDto> getAllPersons() {
        List<Person> persons = IPersonRepository.findAll();
        return persons.stream().map(personDtoMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Person createPerson(Person newPerson) {
        try{
            if (newPerson.getId() == null || newPerson.getId() == 0) {

                Person person = new Person();
                person.setFirstname(newPerson.getFirstname());
                person.setLastname(newPerson.getLastname());
                person.setAge(newPerson.getAge());
                person.setAnimals(newPerson.getAnimals());

                return IPersonRepository.save(person);
            }else {
                throw new EntityToCreateHasAnIdException();
            }
        }catch (EntityToCreateHasAnIdException e){
            System.out.println("Error : "+ e.getMessage());
            throw new EntityToCreateHasAnIdException();
        }
    }

    @Override
    public Person updatePerson(Person updatedPerson) {
        try{
            if (updatedPerson.getId() != null && updatedPerson.getId() > 0) {

                Person person = new Person();
                person.setId(updatedPerson.getId());
                person.setFirstname(updatedPerson.getFirstname());
                person.setLastname(updatedPerson.getLastname());
                person.setAge(updatedPerson.getAge());
                person.setAnimals(updatedPerson.getAnimals());

                return IPersonRepository.save(person);
            }else {
                throw new EntityToUpdateHasNoIdException();
            }
        }
        catch (EntityToUpdateHasNoIdException e){
            System.out.println("Error : "+ e.getMessage());
            throw new EntityToUpdateHasNoIdException();
        }
    }

    @Override
    public ResponseDTO.GeneralResponse deletePerson(int id) {
        String responseMsg = "";
        boolean flag = false;

        try{
            if(!IPersonRepository.existsById(id)){
                throw new EntityNotFoundException();
            }

            Person person = this.getPersonById(id);
            if (person != null) {
                IPersonRepository.deleteById(id);
                responseMsg = !IPersonRepository.existsById(id) ? person.getFirstname() + " deleted successfully." :
                        "Error deleting " + person.getFirstname() + "!";
                flag = IPersonRepository.existsById(id);
            }else {
                responseMsg = "Person not found!";
                flag = false;
            }

            return new ResponseDTO.GeneralResponse<>(flag, responseMsg, null);

        }catch (EntityNotFoundException e) {
            //return new ResponseDTO.GeneralResponse<>(false, "Error deleting person!", null);
            throw new EntityNotFoundException();
        }
    }

    // Renvoie un résultat paginé
    @Override
    public Page<PersonDto> findAllAndPageable(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Person> persons = IPersonRepository.findAll(pageable);
        return persons.map((person) -> personDtoMapper.toDto(person));
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
