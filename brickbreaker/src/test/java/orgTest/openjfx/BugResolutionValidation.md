## Validation de Résolution des Bugs

## Incidents:

**1. No Background (#11) :**
Le background dans le zone de jeu n'apparait pas.
Reglé dans merge request !11.

 **2. Brick out of the playground (#19) :**
Il y avait trop de bricks placés dans le niveau et donc ils sortaient du grille d'affichage. Cela a été reglé dans merge request !23.

**3. Ball movement issue (#43) :**
Quand on quince la balle entre la raquette et la mur elle se bloque et peut plus bouger.
Cela a été reglé dans merge request !44 et apparait plus.

**4. Window transition (#45)**
Lorsqu'on change la fenetre avec le bouton play, la fenêtre se redimensionne mal. Cela a été reglé dans merge request !32

**5. Ball racket collision bug (#47) :**
Le racket se permet de se dessiner sur la balle et cause des problemes de collision. Reglé dans merge request !44.

**6. Double collision (#50) :**
Si la raquete est en hauteur 400, et qu'on lance la partie en allant à droite, la balle casse 2 briques
Cela a été reglé en ajoutant des instruction "break" qui manquait (merge request !44).

**7. Problème lecture de niveau avec des briques de durabilité (#82) :**
Lorsqu'on place une brique de durabilité seul on obtient une erreure.
Reglé avec commit e0337e00.

**8. Erreur d'affichage (#94) :**
Le texte qui s'affiche au milieu de l'ecran (par exemple pour indiquant le nombres de vies restants) ne s'effaceait pas complétement.
Reglé dans merge request !78.

**9. Apply bonus (#105) :**
Les bonus dans les briques étés appliqués sans verifier qu'ils n'étaient pas nulles.
Reglé dans merge request !90.

**10. Problème d'affichage sur la pages des settings (#106) :** 
Il y avait trop de settings et donc il manquait un scroll pane.
Reglé avec merge request !96.

**11.  Bug respawn de balle (#107) :**
Lorsque la balle respawn son accélération est remise à 0 mais on veut pouvoir garder l'accélération de la balle précédente.
Reglé avec merge request !79.

**12. Bug sur l'accélération de la balle (#108) :**
La vitesse de la balle oscille or qu'on veut qu'elle croit. Le source du bug était qu'on prenait pas compte de la sagne de la vittesse avant d'ajouter l'accélération.
Reglé avec merge request !95.

**13. Erreur lié au Ball (#119) :**
Correction du constructeur de `Ball(Ball ball)` et correction de Threads avec un parcours d'une liste de Ball qui sont modifiées pendant le parcours.
Reglé avec merge request !107.

**14. Bug multi coop (#126) :**
Une seule raquette apparait sur l'écran en mode multijoueur, or il devrait y en avoir deux, les deux controllables.
Reglé dans merge request !125.

**15. Bug multi coop 2 (#127) :**
Le niveau 1 et tous les niveaux n'ayant pas de scores enregistrés ne chargent pas en mode multijoueur. 
Reglé dans merge request !125.

**16. Bug play edit (#128) :**
deux raquettes dont une seule jouable apparaissent en play edit quand on a lancé coop avant.
Reglé dansmerge request !125.

**17. Bug dans le mode rush (#129) :**
rush bloqué sur le niveau 1 : quand le niveau est fini au lieu de passer au niveau 2 on rejoue le niveau 1.
Reglé dans merge request !129.

**18. Bug Mode Rush ne change pas de niveau (#131)**
Reglé dans merge request !128.

**19. Bug bomus display (#132) :**
La position de la zone de jeu ne doit plus chevaucher le jeu. La liste des bonus doit être effacé en fin de niveau en mode rush. Les malus de switch les touches doit être révoqué en changent de niveau de rush.
Reglé dans merge request !130.

