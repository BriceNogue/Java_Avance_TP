package com.examplealpha07.bestioles;

import com.examplealpha07.bestioles.Entities.Animal;
import com.examplealpha07.bestioles.Entities.Species;
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

        // TP4

        System.out.println("Species :");
        Species species = speciesRepository.findByCommonName("Chien");
        System.out.println("Species : " + species.getCommonName());
        System.out.println("Species latin name :");
        speciesRepository.findByLatinNameContainingIgnoreCase("F").forEach(speciesX -> {
            System.out.println("Latin name : " + speciesX.getLatinName());
        });

        System.out.println("Persons : ");
        personRepository.findByLastnameOrFirstname("Nero", "Paul").forEach(person -> {
            System.out.println("Full name : " + person.getLastname() + " " + person.getFirstname());
        });

        System.out.println("Person age >= : ");
        personRepository.findByAgeGreaterThanEqual(23).forEach(person -> {
            System.out.println("Firstname : " + person.getFirstname() + "; Age : " + person.getAge());
        });

        System.out.println("Animals : ");
        animalRepository.findBySpecies_CommonName("Chat").forEach(animal -> {
            System.out.println("Name : " + animal.getName() + "; Species : " + animal.getSpecies().getCommonName());
        });

        System.out.println("Animals colors : ");
        String[] colors = {"Blanc", "Noir", "Brun"};
        animalRepository.findByColorIn(colors).forEach(animal -> {
            System.out.println("Name : " + animal.getName() + "; Color : " + animal.getColor());
        });
    }
}
