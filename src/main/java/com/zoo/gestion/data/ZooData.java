package com.zoo.gestion.data;

import java.util.ArrayList;
import java.util.List;

import com.zoo.gestion.model.Amphibien;
import com.zoo.gestion.model.Animal;
import com.zoo.gestion.model.Invertebre;
import com.zoo.gestion.model.Mammifere;
import com.zoo.gestion.model.Oiseau;
import com.zoo.gestion.model.Poisson;
import com.zoo.gestion.model.Reptile;

public class ZooData {

    private final List<Animal> animals;

    public ZooData() {
        animals = new ArrayList<>();

        animals.add(new Mammifere("Raxor", 6, 210.4, "Lion", "Mammifere", "Plaine rocheuse", true, true));
        animals.add(new Mammifere("Velka", 3, 45.2, "Wolf", "Mammifere", "Forêt boréale", true, false));

        animals.add(new Oiseau("Zyra", 4, 6.2, "Golden Eagle", "Oiseau", "Falaises", 1.8, true, "crochu"));
        animals.add(new Oiseau("Tavik", 2, 15.0, "Ostrich", "Oiseau", "Savane sèche", 2.4, false, "plat"));

        animals.add(new Poisson("Ornik", 5, 12.6, "Gar", "Poisson", "Rivière profonde", "eau douce", 1.2, true));
        animals.add(new Poisson("Kelra", 1, 0.8, "Herring", "Poisson", "Lagune", "eau salée", 0.35, false));

        animals.add(new Reptile("Dravos", 8, 85.0, "Komodo Dragon", "Reptile", "Désert chaud", false, true, 47.0, null));
        animals.add(new Reptile("Seryth", 4, 9.3, "Cobra", "Reptile", "Jungle dense", true, true, 49.5, "neurotoxine"));

        animals.add(new Amphibien("Nerix", 3, 0.9, "Salamander", "Amphibien", "Zone humide", true, true, 0.4));
        animals.add(new Amphibien("Ulmar", 2, 0.6, "Frog", "Amphibien", "Marécage", true, true, 0.25));

        animals.add(new Invertebre("Xelth", 1, 0.3, "Spider", "Invertebre", "Forêt tropicale", true, 8));
        animals.add(new Invertebre("Vorin", 2, 1.1, "Centipede", "Invertebre", "Sous-sol humide", true, 24));
    }

    public List<Animal> getAnimals() {
        return animals;
    }
}