package com.examplealpha07.bestioles.DTO.Mappers;

import com.examplealpha07.bestioles.DTO.AnimalDto;
import com.examplealpha07.bestioles.Entities.Animal;

public class AnimalDtoMapper {
    public AnimalDtoMapper(){}

    public AnimalDto toDto(Animal animal) {

        AnimalDto dto = new AnimalDto();
        dto.setId(animal.getId());
        dto.setName(animal.getName());
        dto.setSex(animal.getSex());
        dto.setColor(animal.getColor());
        dto.setSpeciesId(animal.getSpecies().getId());

        return dto;
    }
}
