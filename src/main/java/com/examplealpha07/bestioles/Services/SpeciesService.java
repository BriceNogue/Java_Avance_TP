package com.examplealpha07.bestioles.Services;

import com.examplealpha07.bestioles.Contracts.ISpeciesService;
import com.examplealpha07.bestioles.Entities.Species;
import com.examplealpha07.bestioles.Repositories.ISpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeciesService implements ISpeciesService {

    private final ISpeciesRepository speciesRepository;

    @Autowired
    public SpeciesService(ISpeciesRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
    }

    public Species createSpecies(Species species) {
        try {
            if (species != null){

                Species newSpecies = new Species();
                newSpecies.setCommonName(species.getCommonName());
                newSpecies.setLatinName(species.getLatinName());

                return speciesRepository.save(newSpecies);
            }else {
                return null;
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Species getSpeciesByName(String name) {
        if (name != null) {
            return speciesRepository.findByCommonName(name);
        }else {
            return null;
        }
    }

    public Species getSpeciesById(int id) {
        return speciesRepository.existsById(id) ? speciesRepository.findById(id).get() : null;
    }

    public List<Species> getAllSpecies() {
        return speciesRepository.findAll();
    }

    public Species updateSpecies(Species species) {
        if (species != null) {
            if (speciesRepository.existsById(species.getId())) {
                Species updatedSpecies = new Species();
                updatedSpecies.setId(species.getId());
                updatedSpecies.setCommonName(species.getCommonName());
                updatedSpecies.setLatinName(species.getLatinName());
            }
            return speciesRepository.save(species);
        }else {
            return null;
        }
    }

    public boolean deleteSpecies(int id) {
        if (id > 0) {
            speciesRepository.deleteById(id);
            return !speciesRepository.existsById(id);
        }else {
            return false;
        }
    }
}
