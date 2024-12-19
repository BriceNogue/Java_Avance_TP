package com.examplealpha07.bestioles.Contracts;

import com.examplealpha07.bestioles.DTO.AnimalDto;
import com.examplealpha07.bestioles.Entities.Animal;

import java.util.List;

public interface IAnimalService {
    public Animal getAnimalById(int id);
    public List<Animal> getAllAnimals();
    public Animal createAnimal(AnimalDto animal);
    public Animal updateAnimal(AnimalDto animal);
    public boolean deleteAnimal(int id);
}
