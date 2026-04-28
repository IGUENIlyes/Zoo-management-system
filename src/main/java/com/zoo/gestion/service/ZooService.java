package com.zoo.gestion.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map; 

import org.springframework.stereotype.Service;

import com.zoo.gestion.model.Animal; 
import com.zoo.gestion.repository.AnimalRepository;

@Service
public class ZooService {
    private final AnimalRepository animalRepository;

    public ZooService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    // afficher tous les animaux
    public void displayAllAnimals() {
        for (Animal animal : animalRepository.findAll()) {
            System.out.println(animal.getallInfos());
        }
    }

    // Calculer la quantité totale de nourriture nécessaire 
    public double getTotalDailyFood() {
        double total = 0;
        for (Animal animal : animalRepository.findAll()) {
            total += animal.getDailyFood();
        }
        return total;
    }

    // filtrer les animaux par type (categorie)
    public List<Animal> getAnimalsByType(String type) {
        return animalRepository.findByTypeIgnoreCase(type);
    }

    // filtrer les animaux par species
    public List<Animal> getAnimalsBySpecies(String species) {
        return animalRepository.findBySpeciesIgnoreCase(species);
    }

    // ajouter un nouvel animal
    public void addAnimal(Animal animal) {
        animalRepository.save(animal);
    }

    // supprimer un animal par son id
    public boolean removeAnimalById(String id) {
        if (!animalRepository.existsById(id)) {
            return false;
        }
        animalRepository.deleteById(id);
        return true;
    }
    // supprimer un animal par son nom
    public boolean removeAnimalByName(String name) {
        List<Animal> animals = animalRepository.findByNameIgnoreCase(name);
        if (animals.isEmpty()) {
            return false;
        }
        animalRepository.deleteAll(animals);
        return true;
    }

   // chercher un animal par son id
    public Animal getAnimalById(String id) {
        return animalRepository.findById(id).orElse(null);
    }

    // modifier un animal par son id
    public boolean updateAnimalById(String id, String name, int age, double poids, String species, String type, String habitat) {
        return animalRepository.findById(id).map(animal -> {
            animal.setName(name);
            animal.setAge(age);
            animal.setPoids(poids);
            animal.setSpecies(species);
            animal.setHabitat(habitat);
            animalRepository.save(animal);
            return true;
        }).orElse(false);
    }

    // chercher un animal par son nom
    public List<Animal> getAnimalsByName(String name) {
        return animalRepository.findByNameIgnoreCase(name);
    }
    // Consulter les détails d'un animal par son id
    public void displayAnimalById(String id) {
        Animal animal = getAnimalById(id);
        if (animal == null) {
            System.out.println("Animal non trouvé.");
            return;
        }
        System.out.println("ID      : " + animal.getId());
        System.out.println("Nom     : " + animal.getName());
        System.out.println("Espèce  : " + animal.getSpecies());
        System.out.println("Type    : " + animal.getType());
        System.out.println("Age     : " + animal.getAge() + " ans");
        System.out.println("Poids   : " + animal.getPoids() + " kg");
        System.out.println("Habitat : " + animal.getHabitat());
    }

    // Consulter les détails d'un animal par son nom
    public void displayAnimalByName(String name) {
        List<Animal> animals = animalRepository.findByNameIgnoreCase(name);
        if (animals.isEmpty()) {
            System.out.println("Animal non trouvé.");
            return;
        }
        Animal animal = animals.get(0);
        System.out.println("ID      : " + animal.getId());
        System.out.println("Nom     : " + animal.getName());
        System.out.println("Espèce  : " + animal.getSpecies());
        System.out.println("Type    : " + animal.getType());
        System.out.println("Age     : " + animal.getAge() + " ans");
        System.out.println("Poids   : " + animal.getPoids() + " kg");
        System.out.println("Habitat : " + animal.getHabitat());
    }
    // Statistiques : nombre d’animaux par catégorie
    public Map<String, Long> getCountByCategory() {

         Map<String, Long> stats = new HashMap<>();
            for (Animal a : animalRepository.findAll()) {
             String category = a.getType();
              if (!stats.containsKey(category)) {
                
                stats.put(category, 1L); 
            } 
            else { 
                stats.put(category, stats.get(category) + 1); 
            }
         } 
         return stats; 
        }

        // Age moyen par espèce (statistiques)
    public Map<String, Double> getAverageAgeBySpecies() {
        Map<String, Integer> totalAge = new HashMap<>();
        Map<String, Integer> count = new HashMap<>();

        for (Animal animal : animalRepository.findAll()) {
            String species = animal.getSpecies();
            if (!totalAge.containsKey(species)) {
                totalAge.put(species, 0);
                count.put(species, 0);
            }
            totalAge.put(species, totalAge.get(species) + animal.getAge());
            count.put(species, count.get(species) + 1);
        }

        Map<String, Double> averageAge = new HashMap<>();
        for (Animal animal : animalRepository.findAll()) {
            String species = animal.getSpecies();
            averageAge.put(species, (double) totalAge.get(species) / count.get(species));
        }
        return averageAge;
    }

    // Poids moyen par espèce
    public Map<String, Double> getAverageWeightBySpecies() {
        Map<String, Double> totalPoids = new HashMap<>();
        Map<String, Integer> count = new HashMap<>();

        for (Animal animal : animalRepository.findAll()) {
            String species = animal.getSpecies();
            if (!totalPoids.containsKey(species)) {
                totalPoids.put(species, 0.0);
                count.put(species, 0);
            }
            totalPoids.put(species, totalPoids.get(species) + animal.getPoids());
            count.put(species, count.get(species) + 1);
        }

        Map<String, Double> averagePoids = new HashMap<>();
        for (Animal animal : animalRepository.findAll()) {
            String species = animal.getSpecies();
            averagePoids.put(species, totalPoids.get(species) / count.get(species));
        }
        return averagePoids;
    }

    // Nourriture totale necessaire par espèce quotidiennement
    public Map<String, Double> getTotalFoodBySpecies() {
        Map<String, Double> totalFood = new HashMap<>();

        for (Animal animal : animalRepository.findAll()) {
            String species = animal.getSpecies();
            totalFood.put(species, totalFood.getOrDefault(species, 0.0) + animal.getDailyFood());
        }
        return totalFood;
    }
}