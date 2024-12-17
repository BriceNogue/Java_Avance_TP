package com.examplealpha07.bestioles.Contracts;

import com.examplealpha07.bestioles.DTO.AnimalDTO;
import com.examplealpha07.bestioles.Entities.Animal;

import java.util.List;

public interface IAnimalService {
    public Animal getAnimalById(int id);
    public List<Animal> getAllAnimals();
    public Animal createAnimal(AnimalDTO animal);
    public Animal updateAnimal(AnimalDTO animal);
    public boolean deleteAnimal(int id);
}
