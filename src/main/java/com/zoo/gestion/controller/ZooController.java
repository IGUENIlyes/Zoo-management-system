package com.zoo.gestion.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoo.gestion.dto.AnimalRequest;
import com.zoo.gestion.dto.ApiResponse;
import com.zoo.gestion.map.AnimalMapper;
import com.zoo.gestion.model.Animal;
import com.zoo.gestion.service.ZooService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/animals")
public class ZooController {

    private ZooService zooService;
    private AnimalMapper animalMapper;

    public ZooController(ZooService zooService) {
        this.zooService = zooService;
       
    }

    // GET tous les animaux
    @GetMapping
    public ApiResponse<List<Animal>> getAllAnimals() {
        List<Animal> animals = zooService.getAllAnimals();
        return ApiResponse.success("Tous les animaux récupérés", animals);
    }

    // GET un animal par ID
    @GetMapping("/{id}")
    public ApiResponse<Animal> getAnimalById(@PathVariable String id) {
        Animal animal = zooService.getAnimalById(id);
        if (animal != null) {
            return ApiResponse.success("Animal trouvé", animal);
        }
        return ApiResponse.error("Animal non trouvé avec l'ID: " + id);
    }

    // GET animaux par type (catégorie)
    @GetMapping("/type/{type}")
    public ApiResponse<List<Animal>> getAnimalsByType(@PathVariable String type) {
        List<Animal> animals = zooService.getAnimalsByType(type);
        if (animals.isEmpty()) {
            return ApiResponse.error("Aucun animal trouvé pour le type: " + type);
        }
        return ApiResponse.success("Animaux du type '" + type + "' récupérés", animals);
    }

    // GET animaux par espèce
    @GetMapping("/species/{species}")
    public ApiResponse<List<Animal>> getAnimalsBySpecies(@PathVariable String species) {
        List<Animal> animals = zooService.getAnimalsBySpecies(species);
        if (animals.isEmpty()) {
            return ApiResponse.error("Aucun animal trouvé pour l'espèce: " + species);
        }
        return ApiResponse.success("Animaux de l'espèce '" + species + "' récupérés", animals);
    }

    // GET nourriture totale quotidienne
    @GetMapping("/daily-food")
    public ApiResponse<Double> getTotalDailyFood() {
        double total = zooService.getTotalDailyFood();
        return ApiResponse.success("Nourriture quotidienne totale calculée", total);
    }

    // POST - Créer un nouvel animal
    @PostMapping
    public ApiResponse<Animal> addAnimal(@Valid @RequestBody AnimalRequest request) {
        Animal animal = animalMapper.toAnimal(request);
        zooService.addAnimal(animal);
        return ApiResponse.success("Animal créé avec succès", animal);
    }

    // PUT - Modifier un animal
    @PutMapping("/{id}")
    public ApiResponse<String> updateAnimal(@PathVariable String id, @Valid @RequestBody AnimalRequest request) {
        boolean updated = zooService.updateAnimalById(
            id,
            request.getName(),
            request.getAge(),
            request.getPoids(),
            request.getSpecies(),
            request.getType(),
            request.getHabitat()
        );
        
        if (updated) {
            return ApiResponse.success("Animal modifié avec succès");
        }
        return ApiResponse.error("Animal non trouvé avec l'ID: " + id);
    }

    // DELETE un animal par ID
    @DeleteMapping("/{id}")
    public ApiResponse<String> removeAnimalById(@PathVariable String id) {
        boolean removed = zooService.removeAnimalById(id);
        if (removed) {
            return ApiResponse.success("Animal supprimé avec succès");
        }
        return ApiResponse.error("Animal non trouvé avec l'ID: " + id);
    }

    // DELETE un animal par nom
    @DeleteMapping("/name/{name}")
    public ApiResponse<String> removeAnimalByName(@PathVariable String name) {
        boolean removed = zooService.removeAnimalByName(name);
        if (removed) {
            return ApiResponse.success("Animal supprimé avec succès");
        }
        return ApiResponse.error("Aucun animal trouvé avec le nom: " + name);
    }
}
