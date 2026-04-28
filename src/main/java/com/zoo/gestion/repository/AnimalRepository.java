package com.zoo.gestion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zoo.gestion.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, String> {
    List<Animal> findByTypeIgnoreCase(String type);
    List<Animal> findBySpeciesIgnoreCase(String species);
    List<Animal> findByNameIgnoreCase(String name);
}
