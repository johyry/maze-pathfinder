<h1> Toteutusdokumentti </h1>

<h3> Ohjelman yleisrakenne </h3>

Käytän työssäni valmista labyrintti-generaattoria [Daedalusta](https://www.astrolog.org/labyrnth/daedalus.htm). Generoin ohjelmalla 2D labyrinttejä ja ratkaisen niitä, eli selvitän onko niissä reittiä ulos, ja mikä on nopein reitti.

Käytetyt algoritmit ja pseudokoodit:

**Leveyshaku**

(Löytää aina nopeimman reitin)

	jono.enqueue(alku)
	vierailtu[alku] = true
	etaisyys[alku] = 0
	while not jono.empty()
		solmu = jono.dequeue()
		for naapuri in verkko[solmu]
			if vierailtu[naapuri]
				continue
			jono.enqueue(naapuri)
			vierailtu[naapuri] = true
			etaisyys[naapuri] = etaisyys[solmu]+1

**Thremauxin algoritmi**

(Löytää nopeimman reitin vain täydellisissä labyrinteissä)

- Liiku labyrintissä
- Merkkaa reitti aina kun liikut siellä (risteyksiä ei merkata)
- Tarkista oletko maalissa
- Älä koskaan mene reitille, joss on kaksi merkkiä
- Kun saavut risteykseen, jossa muita reittejä kun tuloreitti ei ole merkitty, valitse satunnainen reitti
- Muuten:
	- Jos reitillä josta saavuit on vain yksi merkki, palaa sinne
	- Jos kaksi, valitse reitti joss on vähiten merkkejä, jos yhtä paljon valitse 		   satunnaisesti

**LeftWallFollower**

- Liiku labyrintissä pitäen vasen käsi seinässä.
- Löytää reitin ulos vain täydellisissä labyrinteissä.


<h3> Saavutetut aika- ja tilavaativuudet </h3>

**Leveyshaku**

Aikavaativuus O(V+E), missä V on solmujen ja E kaarien määrä.
Tilavaativuus on O(V), missä V on solmujen määrä.

**Thremauxin algoritmi**

Aikavaativuus O(V+E), missä V on solmujen ja E kaarien määrä.
Tilavaativuus on O(1), koska vain yksi solmu on muistissa kerrallaan. Algoritmi ylläpitää lisäksi taulukkoa käydyistä solmuista, mutta tämä ei lisää tilavaativuutta.

**LeftWallFollower**

Aikavaativuus huonoimmassa tapauksessa, eli kun reittiä ei ole ulos, käy kaikki mahdolliset solmut ja reunat läpi palaten takaisin lähtöön. Eli O(V+E), missä V on solmujen ja E kaarien määrä.
Tilavaativuus on O(1), koska vain yksi solmu on muistissa kerrallaan.

<h3> Suorituskyky- ja O-analyysivertailu </h3>

ks taulukot [testausdokumentista](Testausdokumentti.md)

**Leveyshaku**

Leveyshaku käy läpi vähiten solmuja kaikissa hauissa, mutta koska pitää jonossa muistissa niin paljon solmuja, se on kaikista hitain.

**Thremauxin algoritmi**

Thremaux on nopeudeltaan keskimmäisin kahteen muuhun verrattuna. Se käy lisäksi vähemmän solmuja läpi kuin leftwallfollower, mutta enemmän kuin leveyshaku.

**LeftWallFollower**

LWF on kaikista nopein täydellisissä labyrinteissa. Toimintaperjaate on niin yksinkertainen, että vaikka se käy läpi eniten solmuja, se on nopein.

<h3> Lähteet </h3>

[Daedaluksen algoritmisivu] (https://www.astrolog.org/labyrnth/algrithm.htm)

[Thremaux wikipedia] (https://en.wikipedia.org/wiki/Maze_solving_algorithm#Tr%C3%A9maux's_algorithm)

[Left wall follower] (https://en.wikipedia.org/wiki/Maze_solving_algorithm#Wall_follower)

[Leveyshaku wikipedia](https://en.wikipedia.org/wiki/Breadth-first_search)
