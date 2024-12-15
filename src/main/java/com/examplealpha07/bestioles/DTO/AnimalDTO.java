package com.examplealpha07.bestioles.DTO;

import com.examplealpha07.bestioles.Entities.Species;

public class AnimalDTO {
    private Integer id;
    private String color;
    private String name;
    private String sex;
    private Integer speciesId;

    public AnimalDTO() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(int speciesId) {
        this.speciesId = speciesId;
    }
}
