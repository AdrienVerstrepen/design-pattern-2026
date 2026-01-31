# Rapport 1

Dans toute la codebase, au global chaque classe/fichier/etc avait trop de responsabilité. Le principe SRP était absolument baffoué.
Pour main.java, on a séparé tout dans des fonctions spécifiques. Pour le menu, on a séparé en trois classes : 
MenuService (la logique métier du menu), GameService (l'interaction menu / couche de stockage) et MenuFormatter (l'affichage du menu)
Enfin, GameCollections a été séparée en trois également : GameCollectionFormatter, GameCollectionLoader et GameCollectionSaver qui gèrent la logique de stockage et GameCollectionRepository.

---

# Rapport 2

Pour la première mission, si le titre a ajouter est repéré dans les jeux déjà existants, la fonction addGame est avortée De plus,les fonctions relevant de la logique ont été déplacées dans une classe séparée. 

Pour la troisième mission, une gestion de la récupération de la date, ainsi que la vérification du jour de la semaine a été implémentée, avec l'ajout d'un classe DateGestion.