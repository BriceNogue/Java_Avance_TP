package com.examplealpha07.bestioles.Controllers;

import com.examplealpha07.bestioles.Contracts.IAnimalService;
import com.examplealpha07.bestioles.DTO.AnimalDTO;
import com.examplealpha07.bestioles.Entities.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    private final IAnimalService animalService;

    @Autowired
    public AnimalController(IAnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/all")
    public List<Animal> getAll() {
        return animalService.getAllAnimals();
    }

    @GetMapping("/{id}")
    public Animal getAnimalById(@PathVariable("id") int id) {
        return animalService.getAnimalById(id);
    }

    @PostMapping("/create")
    public String createAnimal(@RequestBody AnimalDTO newAnimal) {
        Animal animal = animalService.createAnimal(newAnimal);

        String response = animal != null ? animal.getName() + " created successfully." : "Error creating new animal!";

        return response;
    }

    @PutMapping("/update/{id}")
    public String updateAnimal(@PathVariable("(id)") int id, @RequestBody AnimalDTO animalToUpdate) {
        if (id != animalToUpdate.getId() || id <= 0) {
            return "Error updating animal : incorrect id.";
        }

        Animal animal = animalService.updateAnimal(animalToUpdate);

        String response = animal != null ? animal.getName() + " updated successfully." : "Error updating animal!";

        return response;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAnimal(@PathVariable("id") int id) {
        String response = "";

        if (id <= 0){
            return "Invalid animal id!";
        }

        Animal animal = animalService.getAnimalById(id);

        if (animal == null){
            response = "Animal not found!";
        }else {
            response = animalService.deleteAnimal(id) ? "Animal deleted successfully." : "Error deleting animal!";
        }

        return response;
    }
}
