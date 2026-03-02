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
}