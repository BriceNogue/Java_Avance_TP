package com.examplealpha07.bestioles.Services;

import com.examplealpha07.bestioles.Contracts.IAnimalService;
import com.examplealpha07.bestioles.DTO.AnimalDTO;
import com.examplealpha07.bestioles.Entities.Animal;
import com.examplealpha07.bestioles.Entities.Species;
import com.examplealpha07.bestioles.Repositories.IAnimalRepository;
import com.examplealpha07.bestioles.Repositories.ISpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService implements IAnimalService {

    private final IAnimalRepository animalRepository;
    private final ISpeciesRepository speciesRepository;

    @Autowired
    public AnimalService(IAnimalRepository animalRepository, ISpeciesRepository speciesRepository) {
        this.animalRepository = animalRepository;
        this.speciesRepository = speciesRepository;
    }

    @Override
    public Animal getAnimalById(int id) {
        return animalRepository.findById(id).orElse(null);
    }

    @Override
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    @Override
    public Animal createAnimal(AnimalDTO newAnimal) {
        try {
            Species species = speciesRepository.findById(newAnimal.getSpeciesId())
                    .orElseThrow(() -> new RuntimeException("Species not found!"));

            Animal animal = new Animal();
            animal.setName(newAnimal.getName());
            animal.setColor(newAnimal.getColor());
            animal.setSex(newAnimal.getSex());
            animal.setSpecies(species);

            return animalRepository.save(animal);

        }catch(Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Animal updateAnimal(Animal animalToUpdate) {
        try {

            Animal animal = new Animal();
            animal.setId(animalToUpdate.getId());
            animal.setName(animalToUpdate.getName());
            animal.setColor(animalToUpdate.getColor());
            animal.setSex(animalToUpdate.getSex());
            animal.setSpecies(animalToUpdate.getSpecies());

            return animalRepository.save(animal);

        }catch(Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteAnimal(int id) {
        if (id > 0) {
            animalRepository.deleteById(id);
            return !animalRepository.existsById(id);
        }else {
            return false;
        }
    }
}
