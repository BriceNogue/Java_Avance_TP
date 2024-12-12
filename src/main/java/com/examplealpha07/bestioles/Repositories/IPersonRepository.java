package com.examplealpha07.bestioles.Repositories;

import com.examplealpha07.bestioles.Entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonRepository extends JpaRepository<Person, Integer> {
}
