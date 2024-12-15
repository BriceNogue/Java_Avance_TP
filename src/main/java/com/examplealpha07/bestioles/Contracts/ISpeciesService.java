package com.examplealpha07.bestioles.Contracts;

import com.examplealpha07.bestioles.Entities.Species;

import java.util.List;

public interface ISpeciesService {

    public Species createSpecies(Species species);
    public Species getSpeciesByName(String name);
    public Species getSpeciesById(int id);
    public List<Species> getAllSpecies();
    public Species updateSpecies(Species species);
    public boolean deleteSpecies(int id);
}
