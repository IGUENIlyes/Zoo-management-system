package main.java.com.zoo.gestion.service;

import com.zoo.gestion.data.ZooData; 
import com.zoo.gestion.model.Animal; 
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List; 
import java.util.Map; 

@Service
public class ZooService {
    private ZooData zooData;

    public ZooService(ZooData zooData) {
        this.zooData = zooData;
    }

    public List<Animal> getAllAnimals() {
        return zooData.getAnimals();
    }

    // afficher tous les animaux
    public void displayAllAnimals() {
        for (Animal animal : zooData.getAnimals()) {
            System.out.println(animal.getallInfos());
        }
    }

    // Calculer la quantité totale de nourriture nécessaire 
    public double getTotalDailyFood() {
        double total = 0;
for (Animal animal : zooData.getAnimals()) {
    total += animal.getDailyFood();
}
return total;
    }

    // filtrer les animaux par type (categorie)
    public List<Animal> getAnimalsByType(String type) {
        List<Animal> result = new ArrayList<>();
        for (Animal animal : zooData.getAnimals()) {
            if (animal.getType().equalsIgnoreCase(type)) {
                result.add(animal);
            }
        }
        return result;
    }

    // filtrer les animaux par species
    public List<Animal> getAnimalsBySpecies(String species) {
        List<Animal> result = new ArrayList<>();
        for (Animal animal : zooData.getAnimals()) {
            if (animal.getSpecies().equalsIgnoreCase(species)) {
                result.add(animal);
            }
        }
        return result;
    }

    // ajouter un nouvel animal
    public void addAnimal(Animal animal) {
        zooData.getAnimals().add(animal);
    }

    // supprimer un animal par son id
    public boolean removeAnimalById(String id) {
        return zooData.getAnimals().removeIf(animal -> animal.getId().equals(id));
    }
    // supprimer un animal par son nom
    public boolean removeAnimalByName(String name) {
        return zooData.getAnimals().removeIf(animal -> animal.getName().equalsIgnoreCase(name));
    }

   // chercher un animal par son id
    public Animal getAnimalById(String id) {
        for (Animal animal : zooData.getAnimals()) {
            if (animal.getId().equals(id)) {
                return animal;
            }
        }
        return null; 
    }

    // modifier un animal par son id
    public boolean updateAnimalById(String id, String name, int age, double poids, String species, String type, String habitat) {
        for (Animal animal : zooData.getAnimals()) {
            if (animal.getId().equals(id)) {
                animal.setName(name);
                animal.setAge(age);
                animal.setPoids(poids);
                animal.setSpecies(species);
                animal.setType(type);
                animal.setHabitat(habitat);
                return true;
            }
        }
        return false;
    }

    // chercher un animal par son nom
    public List<Animal> getAnimalsByName(String name) {
        List<Animal> result = new ArrayList<>();
        for (Animal animal : zooData.getAnimals()) {
            if (animal.getName().equalsIgnoreCase(name)) {
                result.add(animal);
            }
        }
        return result;
    }
    // Consulter les détails d'un animal par son id
    public void displayAnimalById(String id) {
        for (Animal animal : zooData.getAnimals()) {
            if (animal.getId().equals(id)) {
                System.out.println("ID      : " + animal.getId());
                System.out.println("Nom     : " + animal.getName());
                System.out.println("Espèce  : " + animal.getSpecies());
                System.out.println("Type    : " + animal.getType());
                System.out.println("Age     : " + animal.getAge() + " ans");
                System.out.println("Poids   : " + animal.getPoids() + " kg");
                System.out.println("Habitat : " + animal.getHabitat());
                return;
            }
        }
        System.out.println("Animal non trouvé.");
    }

    // Consulter les détails d'un animal par son nom
    public void displayAnimalByName(String name) {
        for (Animal animal : zooData.getAnimals()) {
            if (animal.getName().equalsIgnoreCase(name)) {
                System.out.println("ID      : " + animal.getId());
                System.out.println("Nom     : " + animal.getName());
                System.out.println("Espèce  : " + animal.getSpecies());
                System.out.println("Type    : " + animal.getType());
                System.out.println("Age     : " + animal.getAge() + " ans");
                System.out.println("Poids   : " + animal.getPoids() + " kg");
                System.out.println("Habitat : " + animal.getHabitat());
                return;
            }
        }
        System.out.println("Animal non trouvé.");
    }
    // Statistiques : nombre d’animaux par catégorie
    public Map<String, Long> getCountByCategory() {

         Map<String, Long> stats = new HashMap<>();
          for (Animal a : zooData.getAnimals()) {
             String category = a.getCategory();
              if (!stats.containsKey(category)) {
                
                stats.put(category, 1L); 
            } 
            else { 
                stats.put(category, stats.get(category) + 1); 
            }
         } 
         return stats; 
        }

        // Age moyen par espèce
    public Map<String, Double> getAverageAgeBySpecies() {
        Map<String, Integer> totalAge = new HashMap<>();
        Map<String, Integer> count = new HashMap<>();

        for (Animal animal : zooData.getAnimals()) {
            String species = animal.getSpecies();
            if (!totalAge.containsKey(species)) {
                totalAge.put(species, 0);
                count.put(species, 0);
            }
            totalAge.put(species, totalAge.get(species) + animal.getAge());
            count.put(species, count.get(species) + 1);
        }

        Map<String, Double> averageAge = new HashMap<>();
        for (Animal animal : zooData.getAnimals()) {
            String species = animal.getSpecies();
            averageAge.put(species, (double) totalAge.get(species) / count.get(species));
        }
        return averageAge;
    }

    // Poids moyen par espèce
    public Map<String, Double> getAverageWeightBySpecies() {
        Map<String, Double> totalPoids = new HashMap<>();
        Map<String, Integer> count = new HashMap<>();

        for (Animal animal : zooData.getAnimals()) {
            String species = animal.getSpecies();
            if (!totalPoids.containsKey(species)) {
                totalPoids.put(species, 0.0);
                count.put(species, 0);
            }
            totalPoids.put(species, totalPoids.get(species) + animal.getPoids());
            count.put(species, count.get(species) + 1);
        }

        Map<String, Double> averagePoids = new HashMap<>();
        for (Animal animal : zooData.getAnimals()) {
            String species = animal.getSpecies();
            averagePoids.put(species, totalPoids.get(species) / count.get(species));
        }
        return averagePoids;
    }
}