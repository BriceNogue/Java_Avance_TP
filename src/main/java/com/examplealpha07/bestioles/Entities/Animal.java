package com.examplealpha07.bestioles.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "color", length = 50)
    private String Color;

    @Column(name = "name", length = 50)
    private String Name;

    @Column(name = "sex", length = 250)
    private String Sex;

    @ManyToOne
    private Species Species;

    public Animal() {}

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public com.examplealpha07.bestioles.Entities.Species getSpecies() {
        return Species;
    }

    public void setSpecies(com.examplealpha07.bestioles.Entities.Species species) {
        Species = species;
    }
}
