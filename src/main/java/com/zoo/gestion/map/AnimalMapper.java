package com.zoo.gestion.map;

import com.zoo.gestion.dto.AnimalRequest;
import com.zoo.gestion.model.Amphibien;
import com.zoo.gestion.model.Animal;
import com.zoo.gestion.model.Invertebre;
import com.zoo.gestion.model.Mammifere;
import com.zoo.gestion.model.Oiseau;
import com.zoo.gestion.model.Poisson;
import com.zoo.gestion.model.Reptile;
import org.springframework.stereotype.Component;

@Component
public class AnimalMapper {

    public Animal toAnimal(AnimalRequest dto) {
        if (dto.getType() == null || dto.getType().isBlank()) {
            throw new IllegalArgumentException("Le type de l'animal est obligatoire.");
        }

        switch (dto.getType().toLowerCase()) {

            case "mammifere":
                validateFields(dto.getType(), dto.getProduitLait(), "produitLait");
                validateFields(dto.getType(), dto.getIsCarnivore(), "isCarnivore");
                return new Mammifere(
                    dto.getName(), dto.getAge(), dto.getPoids(),
                    dto.getSpecies(), dto.getType(), dto.getHabitat(),
                    dto.getProduitLait(), dto.getIsCarnivore()
                );

            case "oiseau":
                validateFields(dto.getType(), dto.getEnvergure(), "envergure");
                validateFields(dto.getType(), dto.getVole(), "vole");
                validateFields(dto.getType(), dto.getBeaktype(), "beaktype");
                return new Oiseau(
                    dto.getName(), dto.getAge(), dto.getPoids(),
                    dto.getSpecies(), dto.getType(), dto.getHabitat(),
                    dto.getEnvergure(), dto.getVole(), dto.getBeaktype()
                );

            case "poisson":
                validateFields(dto.getType(), dto.getWaterType(), "waterType");
                validateFields(dto.getType(), dto.getLongueur(), "longueur");
                validateFields(dto.getType(), dto.getIsPredator(), "isPredator");
                return new Poisson(
                    dto.getName(), dto.getAge(), dto.getPoids(),
                    dto.getSpecies(), dto.getType(), dto.getHabitat(),
                    dto.getWaterType(), dto.getLongueur(), dto.getIsPredator()
                );

            case "reptile":
                validateFields(dto.getType(), dto.getVenimeux(), "venimeux");
                validateFields(dto.getType(), dto.getMue(), "mue");
                validateFields(dto.getType(), dto.getTemperatureOptimale(), "temperatureOptimale");
                if (Boolean.TRUE.equals(dto.getVenimeux())) {
                    validateFields(dto.getType(), dto.getTypeVenin(), "typeVenin");
                }
                return new Reptile(
                    dto.getName(), dto.getAge(), dto.getPoids(),
                    dto.getSpecies(), dto.getType(), dto.getHabitat(),
                    dto.getVenimeux(), dto.getMue(), dto.getTemperatureOptimale(),
                    dto.getTypeVenin()
                );

            case "amphibien":
                validateFields(dto.getType(), dto.getVivresurterre(), "vivresurterre");
                validateFields(dto.getType(), dto.getVivredansleau(), "vivredansleau");
                validateFields(dto.getType(), dto.getLongueur(), "longueur");
                return new Amphibien(
                    dto.getName(), dto.getAge(), dto.getPoids(),
                    dto.getSpecies(), dto.getType(), dto.getHabitat(),
                    dto.getVivresurterre(), dto.getVivredansleau(), dto.getLongueur()
                );

            case "invertebre":
                validateFields(dto.getType(), dto.getHasExoskeleton(), "hasExoskeleton");
                validateFields(dto.getType(), dto.getNumberOfLegs(), "numberOfLegs");
                return new Invertebre(
                    dto.getName(), dto.getAge(), dto.getPoids(),
                    dto.getSpecies(), dto.getType(), dto.getHabitat(),
                    dto.getHasExoskeleton(), dto.getNumberOfLegs()
                );

            default:
                throw new IllegalArgumentException("Type inconnu : " + dto.getType());
        }
    }

    // vérifie qu champ obligatoire est pas nul
    private static void validateFields(String type, Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException("Champ obligatoire manquant pour " + type + " : " + fieldName);
        }
        if (value instanceof String && ((String) value).isBlank()) {
            throw new IllegalArgumentException("Champ obligatoire vide pour " + type + " : " + fieldName);
        }
    }
}