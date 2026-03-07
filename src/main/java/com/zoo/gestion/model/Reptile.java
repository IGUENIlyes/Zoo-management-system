package com.zoo.gestion.model;

public class Reptile extends Animal {

    private boolean venimeux;
    private boolean mue;
    private double temperatureOptimale;
    private String typeVenin;

    public Reptile(String name, int age, double poids, String species, String type, String habitat,
                    boolean venimeux, boolean mue, double temperatureOptimale, String typeVenin) {
        super(name, age, poids, species, "Reptile", habitat);
        this.venimeux = venimeux;
        this.mue = mue;
        if (temperatureOptimale < 45.0){
            throw new IllegalArgumentException("Cette temperature est trop bas pour un reptile.");
        }
        this.temperatureOptimale = temperatureOptimale;
        if (venimeux == true){
            if (typeVenin == null || typeVenin.isEmpty()) {
                throw new IllegalArgumentException("Le type de Venin ne peut pas être vide si le reptile est venimeux.");
            }
        }
        this.typeVenin = typeVenin;
    }

    //Getters
    public double getTemperatureOptimale(){
        return this.temperatureOptimale;
    }

    public String getTypeVenin(){
        return this.typeVenin;
    }

    public boolean getMue(){
        return this.mue == true;
    }

    public boolean getVenimeux(){
        return this.venimeux == true;
    }

    //Setters
    public void setVenimeux(boolean venimeux) {
        this.venimeux = venimeux;
    }

    public void setMue(boolean mue) {
        this.mue = mue;
    }

    public void setTemperatureOptimale(double temperatureOptimale) {
        if (temperatureOptimale < 45.0) {
            throw new IllegalArgumentException("Cette température est trop basse pour un reptile.");
        }
        this.temperatureOptimale = temperatureOptimale;
    }

    public void setTypeVenin(String typeVenin) {
        if (this.venimeux) {
            if (typeVenin == null || typeVenin.isEmpty()) {
                throw new IllegalArgumentException("Le type de Venin ne peut pas être vide si le reptile est venimeux.");
            }
        }
        this.typeVenin = typeVenin;
    }

    //Methods
    public String seRechauffer() {
        return name + " se chauffe au soleil pour réguler sa température.";
    }

    public String utiliserVenin() {
        return venimeux ? name + " utilise son venin pour se défendre." : name + " n'est pas venimeux.";
    }

    public String move() {
        return name + " rampe lentement sur le sol.";
    }

    @Override
    public double getDailyFood() {
        double base = 0.03 * this.getPoids(); // 3% du poids
        double veninBonus = this.venimeux ? 0.02 : 0.0;
        double mueBonus = this.mue ? 0.01 : 0.0;
        return base + veninBonus + mueBonus;
    }
}