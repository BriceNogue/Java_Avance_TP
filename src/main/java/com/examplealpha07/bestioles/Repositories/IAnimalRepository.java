package com.examplealpha07.bestioles.Repositories;

import com.examplealpha07.bestioles.Entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAnimalRepository extends JpaRepository<Animal, Integer> {
    public List<Animal> findBySpecies_CommonName(String species_CommonName);
    public List<Animal> findByColorIn(String[] colors);

    public Integer countBySex(String sex);

    @Query(value = "SELECT COUNT(*) FROM Animal a WHERE a.sex = :sex", nativeQuery = true)
    public Integer countBySexQuery(@Param("sex") String sex);

    @Query(value = "SELECT CASE WHEN COUNT(pa) > 0 THEN true ELSE false END " +
            "FROM person_animals pa WHERE pa.animals_id = :animals_id", nativeQuery = true)
    public boolean isBelongs(@Param("animals_id") Integer animals_id);
}
