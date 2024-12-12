package com.examplealpha07.bestioles.Repositories;

import com.examplealpha07.bestioles.Entities.Species;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISpeciesRepository extends JpaRepository<Species, Integer> {
    public Species findByCommonName(String commonName);
    public List<Species> findByLatinNameContainingIgnoreCase(String keyWord);
}
