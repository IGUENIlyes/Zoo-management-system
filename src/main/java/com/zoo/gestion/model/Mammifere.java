package com.zoo.gestion.model;

public class Mammifere extends Animal {
    private boolean produitLait;
    private boolean isCarnivore;

    public Mammifere(String name, int age, double poids, String species, String type, String habitat, boolean produitLait, boolean isCarnivore) {
        super(name, age, poids, species, "Mammifere", habitat);
        this.produitLait = produitLait;
        this.isCarnivore = isCarnivore;
    }

    @Override
    public double getDailyFood() {

    double base = 0.03 * poids; // 3% du poids corporel

    double carnivoreBonus = isCarnivore ? 1.5 : 0.0;

    double laitBonus = produitLait ? 0.5 : 0.0;

    return base + carnivoreBonus + laitBonus;
    }

    public boolean isProduitLait() {
        return produitLait;
    }

    public void setProduitLait(boolean produitLait) {
        this.produitLait = produitLait;
    }

    public boolean isCarnivore() {
        return isCarnivore;
    }

    public void setCarnivore(boolean carnivore) {
        isCarnivore = carnivore;
    }

    @Override
    public String move() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }
}
