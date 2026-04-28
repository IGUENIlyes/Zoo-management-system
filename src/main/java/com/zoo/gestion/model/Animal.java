package com.zoo.gestion.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

@Entity
@Table(name = "animals")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Mammifere.class,  name = "Mammifere"),
    @JsonSubTypes.Type(value = Oiseau.class,     name = "Oiseau"),
    @JsonSubTypes.Type(value = Poisson.class,    name = "Poisson"),
    @JsonSubTypes.Type(value = Reptile.class,    name = "Reptile"),
    @JsonSubTypes.Type(value = Amphibien.class,  name = "Amphibien"),
    @JsonSubTypes.Type(value = Invertebre.class, name = "Invertebre")
})

public abstract class Animal {
    @Id
    @Column(length = 36, nullable = false, updatable = false)
    protected String id;

    @Column(nullable = false)
    protected String name;

    @Column(nullable = false)
    protected int age;

    @Column(nullable = false)
    protected double poids;

    @Column(nullable = false)
    protected String species;

    @Column(nullable = false)
    protected String type;

    @Column(nullable = false)
    protected String habitat;

    @Transient
    protected double dailyFood;

    protected Animal() {
    }

    // Constructeur

    public Animal(String name, int age, double poids, String species, String type, String habitat) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        if(age < 0) {
            throw new IllegalArgumentException("L'âge doit être positif.");
        }       
        this.age = age;
        if (poids < 0.0){
            throw new IllegalArgumentException("le poids doit etre plus que 0.");
        }
        this.poids = poids;
        if (species == null || species.isEmpty()){
            throw new IllegalArgumentException("l'espece de l'animal ne peut pas être vide.");
        }
        this.species = species;
        this.type = type;
        if (habitat == null || habitat.isEmpty()) {
            throw new IllegalArgumentException("Le habitat ne peut pas être vide.");
        }
        this.habitat = habitat;
    }

    @PrePersist
    protected void onCreate() {
        if (id == null || id.isBlank()) {
            id = UUID.randomUUID().toString();
        }
    }

//    méthodes abstraites
    public abstract double getDailyFood();
    public abstract String move();


// Getters 
    public String getallInfos() {
        return species + " : " + name + " (" + age + " ans)";
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getSpecies() {
        return species;
    }
    public String getId() {
        return id;
    }
    public double getPoids() {
        return poids;
    }
    public String getType() {
        return type;
    }
    

    public String getHabitat(){
        return this.habitat;
    }

    // Setters

    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        if(age < 0) {
            throw new IllegalArgumentException("L'âge doit être positif.");
        }
        this.age = age;
        
    }
    public void setPoids(double poids) {
        if (poids < 0.0){
            throw new IllegalArgumentException("le poids doit etre plus que 0.");
        }
        this.poids = poids;
    }
    public void setSpecies(String species) {
        if (species == null || species.isEmpty()) {
            throw new IllegalArgumentException("La espèce ne peut pas être vide.");
        }
        this.species = species;
    }
    public void setHabitat(String habitat) {
        if (habitat == null || habitat.isEmpty()) {
            throw new IllegalArgumentException("Le habitat ne peut pas être vide.");
        }
        this.habitat = habitat;
    }

    public void setType(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Le type ne peut pas être vide.");
        }
        this.type = type;
    }
    
    

}
