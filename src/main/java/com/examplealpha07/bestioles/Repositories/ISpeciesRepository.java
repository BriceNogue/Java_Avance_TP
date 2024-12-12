package com.examplealpha07.bestioles.Repositories;

import com.examplealpha07.bestioles.Entities.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISpeciesRepository extends JpaRepository<Species, Integer> {
    public Species findByCommonName(String commonName);
    public List<Species> findByLatinNameContainingIgnoreCase(String keyWord);

    @Query(value = "SELECT * FROM Species ORDER BY common_name ASC;", nativeQuery = true)
    public List<Species> findByCommonNameASC();

    // ?1 indique le premier paramètre de la fonction;
    @Query(value = "SELECT * FROM Species s WHERE s.common_name LIKE ?1", nativeQuery = true)
    public List<Species> findByCommonNameLIKE(String commonName);
}
