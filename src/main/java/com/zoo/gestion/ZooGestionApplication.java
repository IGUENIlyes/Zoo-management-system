package com.zoo.gestion;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.zoo.gestion.data.ZooData;
import com.zoo.gestion.repository.AnimalRepository;

@SpringBootApplication
public class ZooGestionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZooGestionApplication.class, args);
    }

    @Bean
    CommandLineRunner seedAnimals(AnimalRepository animalRepository, ZooData zooData) {
        return args -> {
            if (animalRepository.count() == 0) {
                animalRepository.saveAll(zooData.getAnimals());
            }
        };
    }
}
