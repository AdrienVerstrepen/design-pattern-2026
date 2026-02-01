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

```mermaid
classDiagram
    namespace MainPackage {
        class Main {
            -static instance: Main
            +main(args: String[]) void
            +init(args: String[]) void
            +allocateMemory(args: String[]) void
            +launchApplication() void
        }
    }

    namespace Formatters {
        class MenuFormatter {
            +displayMainMenu() void
        }
        class GameCollectionFormatter {
            +viewAllGames() void
        }
    }

    namespace Services {
        class DateGestion {
            +isWeekEnd(): boolean
            +getDay()
            +getMonth()
        }

        class MenuLogic {
            +verificationIsNumber(input: String)
            +verificationIsString(input: String)
            +isValidString(input: String)
            +isValidNumber(input: String)
            +duplicateVerification(input: String)
        }

        class MenuService {
            +getRandom(n: int)
            +addGame()
            +viewAllGames()
            +init()
            +handleMenu(dao: GameCollectionDao)
            +summaryWeekEnd()
        }

        class RandomStrategy {
            <<interface>>
            +getNRandomGames(n: int, dao: GameCollectionDao) List~BoardGame~
        }

        class RandomNElementStrategy {
            +getNRandomGames(n: int, dao: GameCollectionDao) List~BoardGame~
        }
        class LastNElementStrategy {
            +getNRandomGames(n: int, dao: GameCollectionDao) List~BoardGame~
        }
        class FirstNElementStrategy {
            +getNRandomGames(n: int, dao: GameCollectionDao) List~BoardGame~
        }
    }

    namespace Repositories {
        class GameCollectionDao {
            <<interface>>
            +save(game: BoardGame) boolean
            +findAll() List~BoardGame~
            +delete(title: String)
        }

        class GameCollectionDaoCSV {
            -fileName: String
            +save(game: BoardGame) boolean
            +findAll() List~BoardGame~
            +delete(title: String)
        }

        class GameCollectionDaoJSON {
            -fileName: String
            +save(game: BoardGame) boolean
            +findAll() List~BoardGame~
            +delete(title: String)
        }

        class GameCollectionDaoRAM {
            -games: List~BoardGame~
            +save(game: BoardGame) boolean
            +findAll() List~BoardGame~
            +delete(title: String)
        }
    }

    namespace Models {
        class BoardGame {
            <<record>>
            +title: String
            +minPlayers: int
            +maxPlayers: int
            +category: String
        }
    }

    %% Relations
    Main --> MenuService : <<démarre>>
    MenuService --> MenuFormatter : <<utilise>>
    MenuService --> GameCollectionFormatter : <<utilise>>
    MenuService --> DateGestion : <<gère>>
    MenuService --> MenuLogic : <<vérifie>>
    MenuService --> GameCollectionDao : <<utilise>>
    MenuService --> RandomStrategy : <<utilise>>

    RandomNElementStrategy ..|> RandomStrategy : <<implémente>>
    LastNElementStrategy ..|> RandomStrategy : <<implémente>>
    FirstNElementStrategy ..|> RandomStrategy : <<implémente>>

    GameCollectionDaoCSV ..|> GameCollectionDao : <<implémente>>
    GameCollectionDaoJSON ..|> GameCollectionDao : <<implémente>>
    GameCollectionDaoRAM ..|> GameCollectionDao : <<implémente>>

    GameCollectionDaoCSV --> BoardGame : <<utilise>>
    GameCollectionDaoJSON --> BoardGame : <<utilise>>
    GameCollectionDaoRAM --> BoardGame : <<utilise>>
    RandomStrategy --> BoardGame : <<utilise>>
    ```