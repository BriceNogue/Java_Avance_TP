package com.examplealpha07.bestioles.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Species")
public class Species {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "common_name", length = 50, nullable = false)
    private String commonName;

    @Column(name = "latin_name", length = 200, nullable = false)
    private String latinName;

    public Species() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }
}
