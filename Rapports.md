# Rapport 1

Dans toute la codebase, au global chaque classe/fichier/etc avait trop de responsabilité. Le principe SRP était absolument baffoué.
Pour main.java, on a séparé tout dans des fonctions spécifiques. Pour le menu, on a séparé en trois classes : 
MenuService (la logique métier du menu), GameService (l'interaction menu / couche de stockage) et MenuFormatter (l'affichage du menu)
Enfin, GameCollections a été séparée en trois également : GameCollectionFormatter, GameCollectionLoader et GameCollectionSaver qui gèrent la logique de stockage et GameCollectionRepository.

---

# Rapport 2

