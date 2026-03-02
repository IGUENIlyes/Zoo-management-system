package main.java.com.zoo.gestion.model;

public class Poisson extends Animal {
    // Attributs spécifiques au poisson

    private String Watertype;   
    private double longueur;            
    private boolean isPredator; 

// Constructeur
    public Poisson(String name, int age, double poids, String species, String type, String habitat,
                   String waterType, double longueur,
                    boolean isPredator) {
        super(name, age, poids, species, "Poisson", habitat, "aquatique");

        if (!waterType.equalsIgnoreCase("eau douce") &&
            !waterType.equalsIgnoreCase("eau salée")) {
            throw new IllegalArgumentException("Le type d'eau doit être 'eau douce' ou 'eau salée'");
        }
        this.Watertype = waterType;
         if (longueur <= 0) {
            throw new IllegalArgumentException("La longueur doit être positive");
        }
        this.longueur = longueur;
        this.isPredator = isPredator;
    }

    // Getters 
    public String getWaterType() { return Watertype; }
    public double getLongueur() { return longueur; }
    public boolean isPredator() { return isPredator; }

// Setters
    public void setWaterType(String waterType) {
        if (!waterType.equalsIgnoreCase("eau douce") &&
            !waterType.equalsIgnoreCase("eau salée")) {
            throw new IllegalArgumentException("Le type d'eau doit être 'eau douce' ou 'eau salée'");
        }
        this.Watertype = waterType;
    }
    public void setLongueur(double longueur) {
        if (longueur <= 0) {
            throw new IllegalArgumentException("La longueur doit être positive");
        }
        this.longueur = longueur;
    }
    public void setPredator(boolean predator) {
        isPredator = predator;
    }

    // Méthodes spécifiques au poisson

   @Override
public double getDailyFood() {

    double basePoids = 0.02 * getPoids(); // 2% du poids corporel
    double baseLongueur = 0.1 * longueur;

    double waterBonus = Watertype.equalsIgnoreCase("eau salée") ? 0.2 : 0.0;
    double predatorBonus = isPredator ? 0.5 : 0.0;

    return basePoids + baseLongueur + waterBonus + predatorBonus;
}


    @Override
    public String move() {
        return "Je nage dans " + Watertype;
    }
    }