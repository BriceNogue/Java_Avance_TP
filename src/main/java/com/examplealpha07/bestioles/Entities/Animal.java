package com.examplealpha07.bestioles.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "color", length = 50)
    private String color;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "sex", length = 250)
    private String sex;

    @ManyToOne
    private Species species;

    public Animal() {}

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

    public com.examplealpha07.bestioles.Entities.Species getSpecies() {
        return species;
    }

    public void setSpecies(com.examplealpha07.bestioles.Entities.Species species) {
        this.species = species;
    }
}
