package com.zoo.gestion.model;

public class Oiseau extends Animal{
    private double envergure;
    private boolean vole;
    private String beaktype;
    
    public Oiseau(String name, int age, double poids, String species, String Type, String habitat, double envergure, boolean vole, String beaktype){
        super(name, age, poids, species,"Oiseau", habitat);

        if (envergure <=0) {
            throw new IllegalArgumentException("L'envergure doit être positive");

        }
        this.envergure = envergure;
        this.vole = vole;
        if (beaktype == null || beaktype.isEmpty()) {
            throw new IllegalArgumentException("Le type de bec ne peut pas être vide.");
        }
        this.beaktype = beaktype;

    }

    // Getters
    public double getenvergure() { return envergure; }
    public boolean isvole() { return vole; }
    public String getbeaktype() {
        return beaktype;
    }

    // Setters
    public void setenvergure(double envergure) {
        if (envergure <= 0) {
            throw new IllegalArgumentException("L'envergure doit être positive");
        }
        this.envergure = envergure;
    }
    public void setVole(boolean vole) {
        this.vole = vole;
    }
    public void setBeaktype(String beaktype) {
        if (beaktype == null || beaktype.isEmpty()) {
            throw new IllegalArgumentException("Le type de bec ne peut pas être vide.");
        }
        this.beaktype = beaktype;
    }
    
// Méthodes spécifiques aux oiseaux
    @Override
    public double getDailyFood() {
        return 0.2 * envergure; // Calcul de la nourriture quotidienne basé sur l'envergure
    }

    @Override
    public String move(){
        if(vole){
            return "je vole dans le ciel";
        }else{
            return "Je marche sur le sol";
        }
    }
}