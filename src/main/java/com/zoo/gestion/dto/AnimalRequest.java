package com.zoo.gestion.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AnimalRequest {

    @NotBlank(message = "Le type de l'animal est obligatoire.")
    private String type;

    @NotBlank(message = "Le nom de l'animal est obligatoire.")
    private String name;

    @Min(value = 0, message = "L'âge doit être positif.")
    private int age;

    @DecimalMin(value = "0.0", inclusive = false, message = "Le poids doit être supérieur à 0.")
    private double poids;

    @NotBlank(message = "L'espèce ne peut pas être vide.")
    private String species;

    @NotBlank(message = "L'habitat ne peut pas être vide.")
    private String habitat;

    // amphibien
    private Boolean vivresurterre;
    private Boolean vivredansleau;

    @DecimalMin(value = "0.0", inclusive = false, message = "La longueur doit être positive.")
    private Double longueur;

    // invertebre
    private Boolean hasExoskeleton;

    @Min(value = 0, message = "Le nombre de pattes ne peut pas être négatif.")
    private Integer numberOfLegs;

    // mammifere
    private Boolean produitLait;
    private Boolean isCarnivore;

    // oiseau
    @DecimalMin(value = "0.0", inclusive = false, message = "L'envergure doit être positive.")
    private Double envergure;

    private Boolean vole;

    @Size(min = 1, message = "Le type de bec ne peut pas être vide.")
    private String beaktype;

    // poisson
    @Pattern(
        regexp = "(?i)eau douce|eau sal[eé]e",
        message = "Le type d'eau doit être 'eau douce' ou 'eau salée'."
    )
    private String waterType;

    private Boolean isPredator;

    // reptile
    private Boolean venimeux;
    private Boolean mue;

    @DecimalMin(value = "45.0", message = "La température optimale doit être d'au moins 45°C pour un reptile.")
    private Double temperatureOptimale;

    private String typeVenin; // obligatoire si venimeux est vrai

    public AnimalRequest() {}

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public double getPoids() { return poids; }
    public void setPoids(double poids) { this.poids = poids; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public String getHabitat() { return habitat; }
    public void setHabitat(String habitat) { this.habitat = habitat; }

    public Boolean getVivresurterre() { return vivresurterre; }
    public void setVivresurterre(Boolean vivresurterre) { this.vivresurterre = vivresurterre; }

    public Boolean getVivredansleau() { return vivredansleau; }
    public void setVivredansleau(Boolean vivredansleau) { this.vivredansleau = vivredansleau; }

    public Double getLongueur() { return longueur; }
    public void setLongueur(Double longueur) { this.longueur = longueur; }

    public Boolean getHasExoskeleton() { return hasExoskeleton; }
    public void setHasExoskeleton(Boolean hasExoskeleton) { this.hasExoskeleton = hasExoskeleton; }

    public Integer getNumberOfLegs() { return numberOfLegs; }
    public void setNumberOfLegs(Integer numberOfLegs) { this.numberOfLegs = numberOfLegs; }

    public Boolean getProduitLait() { return produitLait; }
    public void setProduitLait(Boolean produitLait) { this.produitLait = produitLait; }

    public Boolean getIsCarnivore() { return isCarnivore; }
    public void setIsCarnivore(Boolean isCarnivore) { this.isCarnivore = isCarnivore; }

    public Double getEnvergure() { return envergure; }
    public void setEnvergure(Double envergure) { this.envergure = envergure; }

    public Boolean getVole() { return vole; }
    public void setVole(Boolean vole) { this.vole = vole; }

    public String getBeaktype() { return beaktype; }
    public void setBeaktype(String beaktype) { this.beaktype = beaktype; }

    public String getWaterType() { return waterType; }
    public void setWaterType(String waterType) { this.waterType = waterType; }

    public Boolean getIsPredator() { return isPredator; }
    public void setIsPredator(Boolean isPredator) { this.isPredator = isPredator; }

    public Boolean getVenimeux() { return venimeux; }
    public void setVenimeux(Boolean venimeux) { this.venimeux = venimeux; }

    public Boolean getMue() { return mue; }
    public void setMue(Boolean mue) { this.mue = mue; }

    public Double getTemperatureOptimale() { return temperatureOptimale; }
    public void setTemperatureOptimale(Double temperatureOptimale) { this.temperatureOptimale = temperatureOptimale; }

    public String getTypeVenin() { return typeVenin; }
    public void setTypeVenin(String typeVenin) { this.typeVenin = typeVenin; }
}
