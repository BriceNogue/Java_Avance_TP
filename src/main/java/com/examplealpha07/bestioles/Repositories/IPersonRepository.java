package com.examplealpha07.bestioles.Repositories;

import com.examplealpha07.bestioles.Entities.Animal;
import com.examplealpha07.bestioles.Entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IPersonRepository extends JpaRepository<Person, Integer> {
    public List<Person> findByLastnameOrFirstname(String lastname, String firstname);
    public List<Person> findByAgeGreaterThanEqual(int age);

    @Query(value = "SELECT * FROM Person p WHERE p.age BETWEEN :ageMin AND :ageMax;", nativeQuery = true)
    public List<Person> findByAgeBetweenMIN_MAX(@Param("ageMin") Integer ageMin, @Param("ageMax") Integer ageMax);

    @Query(value = "SELECT * FROM Person p JOIN Person_animals pa ON p.id = pa.person_id " +
            "WHERE pa.animals_id = :animal_id;", nativeQuery = true)
    public List<Person> findByAnimals(@Param("animal_id") Integer animal_id);
}
