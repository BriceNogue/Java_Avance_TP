package com.examplealpha07.bestioles.Repositories;

import com.examplealpha07.bestioles.Entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAnimalRepository extends JpaRepository<Animal, Integer> {
}
