package com.examplealpha07.bestioles.Repositories;

import com.examplealpha07.bestioles.Entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAnimalRepository extends JpaRepository<Animal, Integer> {
    public List<Animal> findBySpecies_CommonName(String species_CommonName);
    public List<Animal> findByColorIn(String[] colors);
}
