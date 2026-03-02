package main.java.com.zoo.gestion.model;

import java.util.UUID;

public abstract class Animal {
    protected String id;
    protected String name;
    protected int age;
    protected double poids;
    protected String species;
    protected String type;
    protected String habitat;
    protected double dailyFood;

    // Constructeur

    public Animal(String name, int age, double poids, String species, String type, String habitat) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        if(age < 0) {
            throw new IllegalArgumentException("L'âge doit être positif.");
        }       
        this.age = age;
        if (poids < 0.0){
            throw new IllegalAccessException("le poids doit etre plus que 0.");
        }
        this.poids = poids;
        if (species == null || species.isEmpty()){
            throw new IllegalAccessException("l'espece de l'animal ne peut pas être vide.");
        }
        this.species = species;
        this.type = type;
        if (habitat == null || habitat.isEmpty()) {
            throw new IllegalArgumentException("Le habitat ne peut pas être vide.");
        }
        this.habitat = habitat;
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
            throw new IllegalAccessException("le poids doit etre plus que 0.");
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
