package com.zoo.gestion.model;

public class Amphibien extends Animal{

    private boolean vivresurterre;
    private boolean vivredansleau;
    private double longueur;


    public Amphibien(String name, int age,double poids, String species, String type, String habitat, boolean vivresurterre, boolean vivredansleau, double longueur) {
        super(name, age, poids, species, "Amphibien", habitat);
        this.vivresurterre = vivresurterre;
        this.vivredansleau = vivredansleau;
        if (longueur <= 0) {
            throw new IllegalArgumentException("La longueur doit être positive");
        }
        this.longueur = longueur;
    }

    @Override
    public double getDailyFood() {

    double basePoids = 0.015 * getPoids(); // 1.5% du poids
    double baseLongueur = 0.05 * longueur;

    double terreBonus = vivresurterre ? 0.2 : 0.0;
    double eauBonus = vivredansleau ? 0.1 : 0.0;

    return basePoids + baseLongueur + terreBonus + eauBonus;
}

    @Override
    public String move() {
        if (vivresurterre && vivredansleau) {
            return getName() + " peut se déplacer à la fois sur terre et dans l'eau.";
        } else if (vivresurterre) {
            return getName() + " se déplace principalement sur terre.";
        } else if (vivredansleau) {
            return getName() + " se déplace principalement dans l'eau.";
        } else {
            return getName() + " a un mode de déplacement inconnu.";
        }
    }

    public boolean isVivresurterre() {
        return vivresurterre;
    }

    public boolean isVivredansleau() {
        return vivredansleau;
    }

    public double getLongueur() {
        return longueur;
    }
    public void setVivresurterre(boolean vivresurterre) {
        this.vivresurterre = vivresurterre;
    }
    public void setVivredansleau(boolean vivredansleau) {
        this.vivredansleau = vivredansleau;
    }
    public void setLongueur(double longueur) {
        if (longueur <= 0) {
            throw new IllegalArgumentException("La longueur doit être positive");
        }
        this.longueur = longueur;
    }






}