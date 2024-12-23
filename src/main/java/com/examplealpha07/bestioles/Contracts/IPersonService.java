package com.examplealpha07.bestioles.Contracts;

import com.examplealpha07.bestioles.DTO.PersonDto;
import com.examplealpha07.bestioles.DTO.ResponseDTO;
import com.examplealpha07.bestioles.Entities.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPersonService {
    public List<Person> findByLastnameOrFirstname(String lastname, String firstname);
    public List<Person> findByAgeGreaterThanEqual(int age);
    public List<Person> findByAgeBetweenMIN_MAX(int ageMin, int ageMax);
    public List<Person> findByAnimals(int animal_id);

    public Person getPersonById(int id);
    public List<PersonDto> getAllPersons();
    public Person createPerson(Person person);
    public Person updatePerson(Person person);
    public ResponseDTO.GeneralResponse deletePerson(int id);

    public Page<PersonDto> findAllAndPageable(int pageNum, int pageSize);

//    public void deletePersonsWithoutAnimals();
//    public void generateEntities(int nbr);
}
