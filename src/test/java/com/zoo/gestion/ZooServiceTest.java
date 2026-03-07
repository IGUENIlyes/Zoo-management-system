package com.zoo.gestion;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.zoo.gestion.data.ZooData;
import com.zoo.gestion.model.Animal;
import com.zoo.gestion.model.Mammifere;
import com.zoo.gestion.model.Oiseau;
import com.zoo.gestion.model.Reptile;
import com.zoo.gestion.service.ZooService;

class ZooServiceTest {

    private ZooService zooService;
    private List<Animal> animals;

    @BeforeEach
    void setUp() {
        ZooData zooData = new ZooData();
        animals = zooData.getAnimals();
        zooService = new ZooService(zooData);
    }

    // getAllAnimals

    @Test
    void getAllAnimals_returnsAllAnimals() {
        List<Animal> result = zooService.getAllAnimals();
        assertNotNull(result);
        assertEquals(12, result.size());
    }

    // getTotalDailyFood

    @Test
    void getTotalDailyFood_isPositive() {
        double total = zooService.getTotalDailyFood();
        assertTrue(total > 0);
    }

    @Test
    void getTotalDailyFood_matchesManualSum() {
        double expected = animals.stream().mapToDouble(Animal::getDailyFood).sum();
        assertEquals(expected, zooService.getTotalDailyFood(), 0.001);
    }


    // getAnimalsByType

    @Test
    void getAnimalsByType_Mammifere() {
        List<Animal> result = zooService.getAnimalsByType("Mammifere");
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(a -> a.getType().equalsIgnoreCase("Mammifere")));
    }

    @Test
    void getAnimalsByType_isCaseInsensitive() {
        assertEquals(
            zooService.getAnimalsByType("Poisson").size(),
            zooService.getAnimalsByType("poisson").size()
        );
    }

    @Test
    void getAnimalsByType_Reptile() {
        List<Animal> result = zooService.getAnimalsByType("Reptile");
        assertEquals(2, result.size());
    }

    @Test
    void getAnimalsByType_unknownType_returnsEmpty() {
        assertTrue(zooService.getAnimalsByType("Dragon").isEmpty());
    }


    // getAnimalsBySpecies

    @Test
    void getAnimalsBySpecies_Lion() {
        List<Animal> result = zooService.getAnimalsBySpecies("Lion");
        assertEquals(1, result.size());
        assertEquals("Raxor", result.get(0).getName());
    }

    @Test
    void getAnimalsBySpecies_isCaseInsensitive() {
        assertEquals(
            zooService.getAnimalsBySpecies("Cobra").size(),
            zooService.getAnimalsBySpecies("cobra").size()
        );
    }

    @Test
    void getAnimalsBySpecies_unknownSpecies_returnsEmpty() {
        assertTrue(zooService.getAnimalsBySpecies("Licorne").isEmpty());
    }


    // addAnimal

    @Test
    void addAnimal_increasesCount() {
        int before = zooService.getAllAnimals().size();
        Animal newAnimal = animals.get(0);
        zooService.addAnimal(newAnimal);
        assertEquals(before + 1, zooService.getAllAnimals().size());
    }


    // removeAnimalById

    @Test
    void removeAnimalById_removesExistingAnimal() {
        Animal target = zooService.getAllAnimals().get(0);
        boolean result = zooService.removeAnimalById(target.getId());
        assertTrue(result);
        assertNull(zooService.getAnimalById(target.getId()));
    }

    @Test
    void removeAnimalById_returnsFalse_whenIdNotFound() {
        assertFalse(zooService.removeAnimalById("nonexistent-id"));
    }


    // removeAnimalByName

    @Test
    void removeAnimalByName_removesRaxor() {
        boolean result = zooService.removeAnimalByName("Raxor");
        assertTrue(result);
        assertTrue(zooService.getAnimalsByName("Raxor").isEmpty());
    }

    @Test
    void removeAnimalByName_isCaseInsensitive() {
        boolean result = zooService.removeAnimalByName("raxor");
        assertTrue(result);
    }

    @Test
    void removeAnimalByName_returnsFalse_whenNotFound() {
        assertFalse(zooService.removeAnimalByName("Unknown"));
    }


    // getAnimalById

    @Test
    void getAnimalById_returnsCorrectAnimal() {
        Animal target = zooService.getAllAnimals().get(0);
        Animal found = zooService.getAnimalById(target.getId());
        assertNotNull(found);
        assertEquals(target.getId(), found.getId());
        assertEquals(target.getName(), found.getName());
    }

    @Test
    void getAnimalById_returnsNull_whenNotFound() {
        assertNull(zooService.getAnimalById("ghost-id"));
    }


    // updateAnimalById

    @Test
    void updateAnimalById_updatesCorrectly() {
        Animal target = zooService.getAllAnimals().get(0);
        boolean updated = zooService.updateAnimalById(
            target.getId(), "Zara", 7, 200.0, "Panther", "Mammifere", "Jungle"
        );
        assertTrue(updated);
        Animal after = zooService.getAnimalById(target.getId());
        assertEquals("Zara", after.getName());
        assertEquals(7, after.getAge());
        assertEquals(200.0, after.getPoids(), 0.001);
        assertEquals("Panther", after.getSpecies());
        assertEquals("Mammifere", after.getType());
        assertEquals("Jungle", after.getHabitat());
    }

    @Test
    void updateAnimalById_returnsFalse_whenIdNotFound() {
        assertFalse(zooService.updateAnimalById("ghost", "X", 1, 1.0, "X", "X", "X"));
    }


    // getAnimalsByName

    @Test
    void getAnimalsByName_returnsKelra() {
        List<Animal> result = zooService.getAnimalsByName("Kelra");
        assertEquals(1, result.size());
        assertEquals("Kelra", result.get(0).getName());
    }

    @Test
    void getAnimalsByName_isCaseInsensitive() {
        assertEquals(
            zooService.getAnimalsByName("Kelra").size(),
            zooService.getAnimalsByName("kelra").size()
        );
    }

    @Test
    void getAnimalsByName_returnsEmpty_whenNotFound() {
        assertTrue(zooService.getAnimalsByName("Nemo").isEmpty());
    }


    // getCountByCategory

    @Test
    void getCountByCategory_allCountsArePositive() {
        Map<String, Long> result = zooService.getCountByCategory();
        assertFalse(result.isEmpty());
        result.values().forEach(count -> assertTrue(count > 0));
    }

    @Test
    void getCountByCategory_totalMatchesAnimalCount() {
        Map<String, Long> result = zooService.getCountByCategory();
        long total = result.values().stream().mapToLong(Long::longValue).sum();
        assertEquals(zooService.getAllAnimals().size(), total);
    }


    // getAverageAgeBySpecies

    @Test
    void getAverageAgeBySpecies_lionAverageIsCorrect() {
        Map<String, Double> result = zooService.getAverageAgeBySpecies();
        assertEquals(6.0, result.get("Lion"), 0.001);
    }

    @Test
    void getAverageAgeBySpecies_allValuesArePositive() {
        zooService.getAverageAgeBySpecies()
            .values()
            .forEach(avg -> assertTrue(avg > 0));
    }

    @Test
    void getAverageAgeBySpecies_cobraAverageIsCorrect() {
        Map<String, Double> result = zooService.getAverageAgeBySpecies();
        assertEquals(4.0, result.get("Cobra"), 0.001);
    }


    // getAverageWeightBySpecies

    @Test
    void getAverageWeightBySpecies_lionWeightIsCorrect() {
        Map<String, Double> result = zooService.getAverageWeightBySpecies();
        assertEquals(210.4, result.get("Lion"), 0.001);
    }

    @Test
    void getAverageWeightBySpecies_allValuesArePositive() {
        zooService.getAverageWeightBySpecies()
            .values()
            .forEach(avg -> assertTrue(avg > 0));
    }

    @Test
    void getAverageWeightBySpecies_salamanderWeightIsCorrect() {
        Map<String, Double> result = zooService.getAverageWeightBySpecies();
        assertEquals(0.9, result.get("Salamander"), 0.001);
    }

    // Get un animal avec ces attributs subclass
    @Test
    void getAllAnimals_mammifereHasSubclassFields() {
        Animal raxor = zooService.getAnimalsByName("Raxor").get(0);
        assertInstanceOf(Mammifere.class, raxor);
        Mammifere m = (Mammifere) raxor;
        assertTrue(m.isCarnivore());
        assertTrue(m.isProduitLait());
    }

    @Test
    void getAllAnimals_oiseauHasSubclassFields() {
        Animal zyra = zooService.getAnimalsByName("Zyra").get(0);
        assertInstanceOf(Oiseau.class, zyra);
        Oiseau o = (Oiseau) zyra;
        assertTrue(o.isvole());
        assertEquals("crochu", o.getbeaktype());
        assertTrue(o.getenvergure() > 0);
    }

    @Test
    void getAllAnimals_reptileHasSubclassFields() {
        Animal seryth = zooService.getAnimalsByName("Seryth").get(0);
        assertInstanceOf(Reptile.class, seryth);
        Reptile r = (Reptile) seryth;
        assertTrue(r.getVenimeux());
        assertEquals("neurotoxine", r.getTypeVenin());
    }
}