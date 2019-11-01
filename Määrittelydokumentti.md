

<h1> Määrittelydokumentti </h1>

<h3> Käytetyt algoritmit ja tietorakenteet </h3>

Käytän työssäni valmista labyrintti-generaattoria (haettu netistä), ja ratkaisuun käytän A* hakua ja leveyshakua. A* ja Leveyshaku tulee toteutetuksi taulukoiden avulla.

<h3> Mikä on ratkaistava ongelma ja miksi kyseiset tietorakenteet </h3>

Ratkaistava ongelma on lyhin/nopein reitti labyrintissä. Käytän kyseisiä algoritmejä, koska labyrintin saa helposti muunnettua verkoksi, ja on mukava soveltaa aiemmilla kursseilla opittuja asioita käytännön työssä. A* toimii, jos tiedossa on maali. Jos tietoa ei ole maalin sijainnista, leveyshaku toimii.

<h3> Ohjelmalle syötettävät syötteet ja niiden käyttö </h3>

Ohjelma saa syötteenä labyrintin koon. Oletuksena on, että labyrintti on neliö, joten syöte on sivun pituus. Sitä käytetään sitten generoimaan labyrintti valmiilla algoritmilla.

<h3> Tavoitteena olevat aikavaativuudet ja tilavaativuudet </h3>

Leveyshaun aikavaativuus on O(V+E), missä V on solmujen määrä, ja E reunojen määrä.

A* aikavaativuuden tavoite vielä epäselvä.

<h3> Lähteet: </h3>

[A* wikipedia] (https://en.wikipedia.org/wiki/A*_search_algorithm)

[Maze solving algorithms lyhyin polku](https://en.wikipedia.org/wiki/Maze_solving_algorithm#Shortest_path_algorithm)

[Leveyshaku wikipedia](https://en.wikipedia.org/wiki/Breadth-first_search)
