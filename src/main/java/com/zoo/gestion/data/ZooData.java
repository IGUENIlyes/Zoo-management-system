// src/main/java/com/zoo/gestion/data/ZooData.java
package com.zoo.gestion.data;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.zoo.gestion.model.Amphibien;
import com.zoo.gestion.model.Animal;
import com.zoo.gestion.model.Invertebre;
import com.zoo.gestion.model.Mammifere;
import com.zoo.gestion.model.Oiseau;
import com.zoo.gestion.model.Poisson;
import com.zoo.gestion.model.Reptile;

public class ZooData {

    public static Map<String, Animal> loadAnimals() {

        Map<String, Animal> animaux = new ConcurrentHashMap<>();

        // ===== Mammifères =====
        Mammifere m1 = new Mammifere(
                "Raxor", 6, 210.4,
                "Lion", "Mammifere",
                "Plaine rocheuse",
                true, true
        );

        Mammifere m2 = new Mammifere(
                "Velka", 3, 45.2,
                "Wolf", "Mammifere",
                "Forêt boréale",
                true, false
        );

        // ===== Oiseaux =====
        Oiseau o1 = new Oiseau(
                "Zyra", 4, 6.2,
                "Golden Eagle", "Oiseau",
                "Falaises",
                1.8, true, "crochu"
        );

        Oiseau o2 = new Oiseau(
                "Tavik", 2, 15.0,
                "Ostrich", "Oiseau",
                "Savane sèche",
                2.4, false, "plat"
        );

        // ===== Poissons =====
        Poisson p1 = new Poisson(
                "Ornik", 5, 12.6,
                "Gar", "Poisson",
                "Rivière profonde",
                "eau douce",
                1.2,
                true
        );

        Poisson p2 = new Poisson(
                "Kelra", 1, 0.8,
                "Herring", "Poisson",
                "Lagune",
                "eau salée",
                0.35,
                false
        );

        // ===== Reptiles =====
        Reptile r1 = new Reptile(
                "Dravos", 8, 85.0,
                "Komodo Dragon", "Reptile",
                "Désert chaud",
                false, true,
                47.0,
                null
        );

        Reptile r2 = new Reptile(
                "Seryth", 4, 9.3,
                "Cobra", "Reptile",
                "Jungle dense",
                true, true,
                49.5,
                "neurotoxine"
        );

        // ===== Amphibiens =====
        Amphibien a1 = new Amphibien(
                "Nerix", 3, 0.9,
                "Salamander", "Amphibien",
                "Zone humide",
                true, true,
                0.4
        );

        Amphibien a2 = new Amphibien(
                "Ulmar", 2, 0.6,
                "Frog", "Amphibien",
                "Marécage",
                true, true,
                0.25
        );

        // ===== Invertébrés =====
        Invertebre i1 = new Invertebre(
                "Xelth", 1, 0.3,
                "Spider", "Invertebre",
                "Forêt tropicale",
                true, 8
        );

        Invertebre i2 = new Invertebre(
                "Vorin", 2, 1.1,
                "Centipede", "Invertebre",
                "Sous-sol humide",
                true, 24
        );

        // Add all to map
        List<Animal> liste = List.of(
                m1, m2,
                o1, o2,
                p1, p2,
                r1, r2,
                a1, a2,
                i1, i2
        );

        for (Animal a : liste) {
            animaux.put(a.getId(), a);
        }

        return animaux;
    }
}