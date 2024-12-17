package com.examplealpha07.bestioles.Repositories;

import com.examplealpha07.bestioles.Entities.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
//import javax.transaction.Transactional;

import java.util.Random;

//NB: Attention a la nomenclature de l'interface custom et de la class qui l'implémente.
public class PersonRepositoryCustomImpl implements PersonRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void deletePersonsWithoutAnimals() {
        String jpql = "DELETE FROM Person p WHERE p.animals IS EMPTY";
        entityManager.createQuery(jpql).executeUpdate();
    }

    @Override
    @Transactional
    public void generateEntities(int nbr) {
        Random random = new Random();
        for (int i = 0; i < nbr; i++) {
            Person person = new Person();
            person.setAge(random.nextInt(80));
            person.setFirstname("Firstname_" + random.nextInt(10000));
            person.setLastname("Lastname_" + random.nextInt(10000));
            entityManager.persist(person);
        }
    }
}