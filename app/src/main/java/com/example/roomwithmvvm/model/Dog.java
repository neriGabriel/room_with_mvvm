package com.example.roomwithmvvm.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@Entity(tableName = "tb_dog")
@JsonIgnoreProperties({"description", "history"})
public class Dog implements Serializable {
    @Ignore
    private Weight weight;
    @Ignore
    private Height height;

    @PrimaryKey()
    @ColumnInfo(name = "dog_id")
    @NonNull
    private Integer id;

    @ColumnInfo(name = "dog_name")
    private String name;

    @ColumnInfo(name = "dog_country_code")
    private String country_code;

    @ColumnInfo(name = "dog_bred_for")
    private String bred_for;

    @ColumnInfo(name = "dog_breed_group")
    private String breed_group;

    @ColumnInfo(name = "dog_life_span")
    private String life_span;

    @ColumnInfo(name = "dog_temperament")
    private String temperament;

    @ColumnInfo(name = "dog_origin")
    private String origin;

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public Height getHeight() {
        return height;
    }

    public void setHeight(Height height) {
        this.height = height;
    }

    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getBred_for() {
        return bred_for;
    }

    public void setBred_for(String bred_for) {
        this.bred_for = bred_for;
    }

    public String getBreed_group() {
        return breed_group;
    }

    public void setBreed_group(String breed_group) {
        this.breed_group = breed_group;
    }

    public String getLife_span() {
        return life_span;
    }

    public void setLife_span(String life_span) {
        this.life_span = life_span;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
