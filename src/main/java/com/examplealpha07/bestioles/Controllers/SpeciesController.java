package com.examplealpha07.bestioles.Controllers;

import com.examplealpha07.bestioles.Entities.Species;
import com.examplealpha07.bestioles.Contracts.ISpeciesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/species")
public class SpeciesController {

    private final ISpeciesService speciesService;

    @Autowired
    public SpeciesController(ISpeciesService speciesService) {
        this.speciesService = speciesService;
    }

    @GetMapping("/all")
    public List<Species> getAllSpecies() {
        return speciesService.getAllSpecies();
    }

    @GetMapping("/{id}")
    public Species getSpeciesById(@PathVariable("id") int id) {
        return speciesService.getSpeciesById(id);
    }

    @PostMapping("/create")
    public String createSpecies(@RequestBody Species newSpecies) {

        Species species = speciesService.createSpecies(newSpecies);

        if (species != null) {
            return species.getCommonName() + " Species created successfully.";
        }else {
            return "Error creating species";
        }
    }

    @PutMapping("/update/{id}")
    public String updateSpecies(@PathVariable("id") int id, @RequestBody @Valid Species speciesToUpdate) {

        if (id != speciesToUpdate.getId() || id <= 0) {
            return "Error creating species : incorrect id!";
        }

        String response = "";
        Species updatedSpecies = speciesService.updateSpecies(speciesToUpdate);

        response = updatedSpecies != null ? updatedSpecies.getCommonName() + " species updated successfully." : "Error updating species";
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSpecies(@PathVariable("id") int id) {

        if (id <= 0){
            return "Invalid species id.";
        }

        Species species = speciesService.getSpeciesById(id);
        String response = "";

        if (species != null) {
            response = speciesService.deleteSpecies(id) ? "Species deleted successfully." : "Error deleting species!";
        }else {
            response = "Species not found!";
        }

        return response;
    }
}
