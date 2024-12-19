package com.examplealpha07.bestioles.DTO.Mappers;

import com.examplealpha07.bestioles.DTO.PersonDto;
import com.examplealpha07.bestioles.Entities.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonDtoMapper {

    public PersonDtoMapper(){}

    public PersonDto toDto(Person person) {

        PersonDto dto = new PersonDto();
        dto.setId(person.getId());
        dto.setFirstname(person.getFirstname());
        dto.setLastname(person.getLastname());

        return dto;
    }
}
