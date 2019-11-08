

<h1> Määrittelydokumentti </h1>

Projekti toteutetaan Javalla.

<h3> Käytetyt algoritmit ja tietorakenteet </h3>

Käytän työssäni valmista labyrintti-generaattoria [Daedalusta](https://www.astrolog.org/labyrnth/daedalus.htm). Generoin ohjelmalla 2D labyrinttejä, ja koodaan erilaisia algoritmeja nopeimman reitin ratkaisemiseen. Algoritmeiksi valitsen Leveyshaun, A* ja Trémauxin algoritmin.

<h3> Mikä on ratkaistava ongelma ja miksi kyseiset tietorakenteet </h3>

Ratkaistava ongelma on lyhin/nopein reitti labyrintissä. Leveyshaulla ja A* saa nopeasti ja varmasti haettua nopeimman reitin. Samalla voi vertailla niiden nopeuksia erikokoisissa labyrinteissä. Kolmas algoritmi on Tremauxin algoritmi. Tämän valitsin siksi, että algoritmin toimintatapa on mielenkiintoinen, ja sitä voisi käyttää ihminen ratkaistakseen labyrintin.

![Tremaux](../Kuvat/TremauxDescription.png)

<h3> Ohjelmalle syötettävät syötteet ja niiden käyttö </h3>

Ohjelma saa syötteenä tiedostoksi (bitmapiksi) generoidun labyrintin. Tämä muutetaan sitten ohjelmassa taulukoksi ja se sitten ratkaistaan. Mahdollisesti myös piirretään uusi bitmappi missä on ratkaistu reitti.

<h3> Tavoitteena olevat aikavaativuudet ja tilavaativuudet </h3>

Leveyshaun aikavaativuus on O(V+E), missä V on solmujen määrä, ja E reunojen määrä.

A* aikavaativuuden tavoite vielä epäselvä.

<h3> Lähteet: </h3>

[A* wikipedia] (https://en.wikipedia.org/wiki/A*_search_algorithm)

[Maze solving algorithms lyhyin polku](https://en.wikipedia.org/wiki/Maze_solving_algorithm#Shortest_path_algorithm)

[Leveyshaku wikipedia](https://en.wikipedia.org/wiki/Breadth-first_search)
