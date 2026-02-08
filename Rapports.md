# Rapport 1

Dans toute la codebase, au global chaque classe/fichier/etc avait trop de responsabilité. Le principe SRP était absolument baffoué.
Pour main.java, on a séparé tout dans des fonctions spécifiques. Pour le menu, on a séparé en trois classes : 
MenuService (la logique métier du menu), GameService (l'interaction menu / couche de stockage) et MenuFormatter (l'affichage du menu)
Enfin, GameCollections a été séparée en trois également : GameCollectionFormatter, GameCollectionLoader et GameCollectionSaver qui gèrent la logique de stockage et GameCollectionRepository.

---

# Rapport 2

Pour la première feature, si le titre a ajouter est repéré dans les jeux déjà existants, la fonction addGame est avortée De plus,les fonctions relevant de la logique ont été déplacées dans une classe séparée. 

Pour la seconde feature, une interface qui abstrait le mécanisme de choix aléatoire a été réalisée.

Pour la troisième feature, une gestion de la récupération de la date, ainsi que la vérification du jour de la semaine a été implémentée, avec l'ajout d'un classe DateGestion.

Au global : mise en place d'une DAO pour abstraire la gestion de la donnée, nous avons commencé à déplacer la responsabilité de la saisie utlisateur de MenuService vers le MenuFormatter. 

[Diagramme mermaid](diagramme-TP2.mmd)

# Rapport 3

Pour la deuxième feature, nous avo,s ajouté une classe RecommendByPlayerCountEntry, qui fait la gestion de la feature et est appelée par le Menu. Elle contient une méthode handle qui va faire cette gestion. Pour appliquer des tests avec des mocks, il a fallut ajouter une classe MenuInterface pour faire la gestion du MenuFormatter, ce qui a amené quelques changelents structurels pour les actions du menu. On fait également une gestion de l'affichage dans l'ordre alphabetique en ajoutant une méthode dans GameCollectionDao.

[Diagramme mermaid](diagramme-TP3.mmd)