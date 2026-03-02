package main.java.com.zoo.gestion.model;

public class Invertebre extends Animal {

    // Attributs spécifiques aux invertébrés
    private boolean hasExoskeleton;  
    private int numberOfLegs;        
          
    // Constructeur
    public Invertebre(String name, int age, double poids, String species, String type, String habitat, boolean hasExoskeleton, int numberOfLegs) {
        super(name, age, poids, species, "Invertebre", habitat);
        this.hasExoskeleton = hasExoskeleton;
        if (numberOfLegs < 0) {
            throw new IllegalArgumentException("Le nombre de jambes ne peut pas être négatif.");
        }
        this.numberOfLegs = numberOfLegs;
        
    }

    // méthodes spécifiques aux invertébrés
    @Override
    public double getDailyFood() {

    double base = 0.05; // base pour tous les invertébrés

    double legsBonus = 0.01 * numberOfLegs;

    double exoBonus = hasExoskeleton ? 0.05 : 0.0;

    double habitatBonus = habitat.equalsIgnoreCase("aquatique") ? 0.1 : 0.0;

    return base + legsBonus + exoBonus + habitatBonus;
}

public String move() {
        if (numberOfLegs > 0) {
            return getName() + " se déplace en utilisant ses " + numberOfLegs + " jambes.";
        } else {
            return getName() + " se déplace en rampant ou en flottant.";
        }
    }

// Getters 
    public boolean hasExoskeleton() {
        return hasExoskeleton;
    }

    public int getNumberOfLegs() {
        return numberOfLegs;
    }

// Setters
    public void setHasExoskeleton(boolean hasExoskeleton) {
        this.hasExoskeleton = hasExoskeleton;
    }
    public void setNumberOfLegs(int numberOfLegs) {
        if (numberOfLegs < 0) {
            throw new IllegalArgumentException("Le nombre de jambes ne peut pas être négatif.");
        }
        this.numberOfLegs = numberOfLegs;
    }

    
}
