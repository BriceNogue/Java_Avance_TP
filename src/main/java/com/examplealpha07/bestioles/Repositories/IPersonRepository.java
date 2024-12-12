package com.examplealpha07.bestioles.Repositories;

import com.examplealpha07.bestioles.Entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPersonRepository extends JpaRepository<Person, Integer> {
    public List<Person> findByLastnameOrFirstname(String lastname, String firstname);
    public List<Person> findByAgeGreaterThanEqual(int age);
}
