# 🦁 Zoo Gestion System

Une application web de gestion de zoo développée avec Java Spring Boot. Elle permet de gérer une collection d'animaux à travers une interface simple et complète, accessible directement depuis le navigateur.

---

## 🐾 Fonctionnalités

### Vue d'ensemble
Dès l'ouverture de l'application, deux indicateurs sont affichés en haut de page :
- Le **nombre total d'animaux** dans le zoo
- La **quantité de nourriture quotidienne** nécessaire pour tous les animaux (en kg)

### Statistiques avancées
Un bouton permet de charger des statistiques détaillées :
- **Animaux par catégorie** — combien d'animaux par type
- **Âge moyen par espèce**
- **Poids moyen par espèce**

### Filtres et recherche
La liste des animaux peut être filtrée par :
- **Type** (Mammifère, Oiseau, Poisson, Reptile, Amphibien, Invertébré)
- **Espèce** (ex: Lion, Cobra...)
- **Nom** (ex: Raxor, Kelra...)

### Ajouter un animal
Un formulaire permet de créer un nouvel animal. En choisissant le type, des champs supplémentaires apparaissent automatiquement selon les attributs propres à chaque sous-classe (ex: envergure pour un Oiseau, type de venin pour un Reptile).

### Modifier un animal
En cliquant sur un animal dans la liste, le formulaire de modification se remplit automatiquement avec ses informations. La modification met à jour les champs communs : nom, âge, poids, espèce, type et habitat.

### Supprimer un animal
Un animal peu être supprimé de deux façons :
- En **saisissant son nom** dans le champ de suppression
- En cliquant directement sur le **bouton supprimer** depuis sa carte dans la liste

### Liste des animaux
Tous les animaux sont affichés sous forme de cartes avec leurs informations générales et leurs attributs spécifiques selon leur type.

---

## 🐘 Types d'animaux supportés

| Type | Caractéristiques spécifiques |
|---|---|
| 🦁 Mammifère | Produit du lait, carnivore |
| 🦅 Oiseau | Envergure, capacité à voler, type de bec |
| 🐟 Poisson | Type d'eau (douce / salée), longueur, prédateur |
| 🐍 Reptile | Venimeux, type de venin, mue, température optimale |
| 🐸 Amphibien | Vit sur terre, vit dans l'eau, longueur |
| 🕷️ Invertébré | Exosquelette, nombre de pattes |

---

## 🚀 Lancer l'application

### Prérequis
- Java 17 ou supérieur
- Maven

### Démarrage

```bash
git clone https://github.com/IGUENIlyes/Zoo-management-system.git
cd Zoo-management-system
mvn spring-boot:run
```

L'application est accessible à l'adresse : **http://localhost:8080**

---

## 🧪 Tests

```bash
mvn test
```

Un résumé s'affiche dans le terminal indiquant si tous les tests ont réussi ou non.

---

## 👤 Auteurs

**IGUENI Lyes** — [GitHub](https://github.com/IGUENIlyes)
**MUKURI MAKA Jonas** — [GitHub](https://github.com/Jonas-Mukuri-Maka)
**NOTUE-KAMTO Michel-Bryan** 
