# Projet-Compilation-2021-2022
Ce projet a été réalisé pour le cours de compilation de L3 Informatique 2021-2022 de L'Université de Lorraine.

Compilateur "Zoot" génère les instructions MIPS à partir d'un code source écrit en Zoot

Ainsi, le résultat de l'exécution sera un fichier d'instructions .asm, qui peut être exécuté dans l'environnement [MARS]([https://www.google.com](http://courses.missouristate.edu/kenvollmar/mars/))

# Paramètres du projet
Le projet a été testé dans IntelliJ IDEA avec jdk 16.0.1.

Pour lancer la création des deux analyseurs et la compilation de l’ensemble des classes, exécutez le fichier build.xml (clic droit sur le fichier et sélection de “Add as Ant Build File”). Dans la fenêtre ant ouverte, sélectionnez le mot “zoot” pour faire apparaître la flèche verte sur laquelle cliquer.

Pour lancer l'exécution du projet, ajouter une nouvelle "run configuration"; Main class - zoot.Zoot; Program arguments - chemin vers le fichier .zoot
