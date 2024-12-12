package com.examplealpha07.bestioles.Repositories;

import com.examplealpha07.bestioles.Entities.Species;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISpeciesRepository extends JpaRepository<Species, Integer> {
}
