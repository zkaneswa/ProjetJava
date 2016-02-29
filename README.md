Manuel d’installation<br /><br />

Bienvenue sur SpaceShips Racing !<br />
Nous allons vous indiquer dans cette partie comment installer et lancer le programme.<br /><br />

I)	Introduction<br />

SpaceShips Racing a été programmé en Java, sa portabilité est donc garantie puisque les programmes Java sont exécutés sur une machine virtuelle (JVM), disponible sur toutes les plateformes.<br /><br />

Pour pouvoir jouer, vous avez accès au programme dans un fichier à part intitulé ProjetJava.jar ou directement sur github qui contient la dernière version de notre programme.<br />
Cependant, si vous importez le projet présent sur github, vous devrez savoir comment compiler le programme Java. Le fichier .jar est un fichier exécutable, vous pouvez alors passez directement à l’étape *.<br /><br />

II)	Qu’est ce que la compilation ?<br />

La compilation consiste à traduire le code source d’un programme en langage machine / assembleur.<br /><br />

III)	Comment compiler le programme ?<br />

Plusieurs possibilités : <br />
•	Soit directement dans le terminal de votre ordinateur :<br />
Compiler chaque fichier .java avec la commande : javac MaClasse.java<br />

•	Mais le plus simple reste encore d’utiliser un environnement de développement intégré (IDE) ! Nous vous recommandons d’utiliser Eclipse qui est nettement plus simple à manipuler.<br />
Vous pouvez le télécharger directement via ce lien : http://www.eclipse.org/ide/<br />

*Dans les deux cas, il vous faudra télécharger le JDK en cliquant sur le lien suivant : http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html<br />
Cet outil vous permettra de compiler le code Java en bytecode destiné à la JVM. <br />
Vous pouvez donc grâce à JDK, exécuter votre fichier .jar et jouer !<br /><br />

IV)	Comment exécuter le programme ?<br />

•	Avec le terminal, taper la commande : java MaClasse<br />

•	Après installation d’Eclipse, vous devez importer le projet présent sur github avec l’URI suivante : https://github.com/zkaneswa/ProjetJava.git<br />
Ensuite, il vous suffit d’exécuter le programme en faisant « run » sur le projet.<br />


Vous pouvez lire le manuel d’utilisation ou profiter du jeu et jouer avec des amis dès maintenant !<br /><br /><br />




Manuel d’utilisation<br /><br />


I)	Introduction<br />

SpaceShips Racing est un jeu de course spatiale se passant dans un tunnel d’une planète inconnue.<br /><br />

II)	But du jeu<br />

Le but du jeu de SpaceShips Racing est d’avoir le meilleur score. Plus vous êtes à droite de l’écran plus vous aurez de points. A l’inverse, si vous restez sur le bord gauche de l’écran, vous ne gagnerez aucun point, le score étant calculé en fonction de la position horizontale de votre vaisseau.<br /><br />

III)	Commandes et jeu<br />

Le jeu est composé de trois parties distinctes : <br /><br />

1)	Le menu<br />

Le menu qui permet d’accéder aux instructions du jeu, de choisir le nombre de joueurs (jusqu’à 3) et bien sûr de lancer une partie. On sélectionne le nombre de joueurs ou les instructions grâce aux flèches de contrôle et on valide avec la touche Entrée.<br /><br />

2)	Le jeu<br />

La deuxième partie correspond au jeu à proprement dit,  on se déplace de haut en bas et de gauche à droite grâce aux flèches de contrôle pour le joueur 1, aux touches Z, S, Q, D pour le joueur 2 et enfin U, J, H, K pour le troisième joueur. <br />
Une couleur est également définie pour chaque joueur : orange pour le joueur 1, violet pour le joueur 2, vert pour le joueur 3. Chaque joueur dispose de 10 unités d’énergie au départ.<br />
Tous les joueurs commencent au même niveau d’abscisse mais sont tout de suite attirés vers le bas à cause de la gravité de la planète.
Lorsque le joueur se cogne au tunnel il perd 1 point d’énergie et devient invincible pendant 1 seconde, le sprite de son vaisseau devient alors rouge. Lorsque l’on se cogne à un vaisseau ennemi les deux vaisseaux entrés en collision rebondissent. <br />
Lorsque l’on entre dans une zone spéciale, le tunnel devient bleu et les touches de contrôle sont ainsi inversées (haut devient bas, gauche devient droite) à cause des perturbations électromagnétiques qui se produisent parfois sur cette planète plus on s’enfonce dans le tunnel. <br />
	Lorsqu’un des joueurs meurt les autres vaisseaux ayant encore de l’énergie peuvent continuer à jouer pour augmenter leur score tandis que le vaisseau mort voit son sprite disparaître et son score arrête d’augmenter. La partie est finie quand tous les vaisseaux sont morts et on passe ainsi à la troisième partie du jeu. <br /><br />

3)	Fin du jeu<br />

Cette troisième partie consiste en l’affichage d’une image de Game Over et d’un texte indiquant le gagnant, c’est-à-dire celui qui a terminé la partie avec le score le plus haut. On peut choisir de revenir au menu en appuyant sur Entrée pour refaire une ou plusieurs parties.<br />


Un conseil : Amusez-vous !
