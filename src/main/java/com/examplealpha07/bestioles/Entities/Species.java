package com.examplealpha07.bestioles.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Species")
public class Species {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "common_name", length = 50, nullable = false)
    private String CommonName;

    @Column(name = "latin_name", length = 200, nullable = false)
    private String LatinName;

    public Species() {}

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getCommonName() {
        return CommonName;
    }

    public void setCommonName(String commonName) {
        CommonName = commonName;
    }

    public String getLatinName() {
        return LatinName;
    }

    public void setLatinName(String latinName) {
        LatinName = latinName;
    }
}
