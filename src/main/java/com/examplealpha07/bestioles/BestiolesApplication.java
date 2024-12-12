package com.examplealpha07.bestioles;

import com.examplealpha07.bestioles.Entities.Animal;
import com.examplealpha07.bestioles.Repositories.IAnimalRepository;
import com.examplealpha07.bestioles.Repositories.IPersonRepository;
import com.examplealpha07.bestioles.Repositories.ISpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BestiolesApplication implements CommandLineRunner {

    @Autowired
    public IPersonRepository personRepository;

    @Autowired
    public IAnimalRepository animalRepository;

    @Autowired
    public ISpeciesRepository speciesRepository;

    public static void main(String[] args) {
        SpringApplication.run(BestiolesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        personRepository.findAll().forEach(person -> {
            System.out.println("Firstname : " + person.getFirstname());
        });

        animalRepository.findAll().forEach(animal -> {
            System.out.println("Animal : " + animal.getName());
        });

        speciesRepository.findAll().forEach(species -> {
            System.out.println("Species : " + species.getCommonName());
        });
    }
}
