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

import java.util.Optional;

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

        // Region TP4

        System.out.println("Species : TP4");
        Species species = speciesRepository.findByCommonName("Chien");
        System.out.println("Species : " + species.getCommonName());
        System.out.println("Species latin name :");
        speciesRepository.findByLatinNameContainingIgnoreCase("F").forEach(speciesX -> {
            System.out.println("Latin name : " + speciesX.getLatinName());
        });

        System.out.println("Persons : TP4");
        personRepository.findByLastnameOrFirstname("Nero", "Paul").forEach(person -> {
            System.out.println("Full name : " + person.getLastname() + " " + person.getFirstname());
        });

        System.out.println("Person age >= : ");
        personRepository.findByAgeGreaterThanEqual(23).forEach(person -> {
            System.out.println("Firstname : " + person.getFirstname() + "; Age : " + person.getAge());
        });

        System.out.println("Animals : TP4");
        animalRepository.findBySpecies_CommonName("Chat").forEach(animal -> {
            System.out.println("Name : " + animal.getName() + "; Species : " + animal.getSpecies().getCommonName());
        });

        System.out.println("Animals colors : ");
        String[] colors = {"Blanc", "Noir", "Brun"};
        animalRepository.findByColorIn(colors).forEach(animal -> {
            System.out.println("Name : " + animal.getName() + "; Color : " + animal.getColor());
        });

        // EndRegion TP4

        // Region TP5

        System.out.println("Species : TP5");
        System.out.println("Species Common Name ASC: ");
        speciesRepository.findByCommonNameASC().forEach(speciesx1 -> {
            System.out.println("Name : " + speciesx1.getCommonName());
        });

        System.out.println("Species Common Name LIKE: ");
        speciesRepository.findByCommonNameLIKE("Chat").forEach(speciesx2 -> {
            System.out.println("Name : " + speciesx2.getCommonName());
        });

        System.out.println("Person : TP5");
        System.out.println("Person Age [MIN , MAX]");
        personRepository.findByAgeBetweenMIN_MAX(22, 60).forEach(person -> {
            System.out.println("Firstname : " + person.getFirstname() + "; Age : " + person.getAge());
        });

        System.out.println("Person Animals :");
        Optional<Animal> animal = animalRepository.findById(1);
        personRepository.findByAnimals(animal.get().getId()).forEach(person -> {
            System.out.println("Firstname : " + person.getFirstname() + "; Animal : " + animal.get().getName());
        });

        System.out.println("Animals : TP5");
        System.out.println("Animals Count by Sex : ");
        String sex = "M";
        System.out.println("\t" + animalRepository.countBySexQuery(sex) + " animal(s) of the " + sex + " sex;");

        System.out.println("Animals belongs");
        boolean isBelongs = animalRepository.existsById(8);
        System.out.println("Belongs : " + isBelongs);
        // EnRegion TP5
    }
}
