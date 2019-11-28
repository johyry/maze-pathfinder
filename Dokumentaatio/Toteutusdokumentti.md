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

Liiku labyrintissä
Merkkaa reitti aina kun liikut siellä (risteyksiä ei merkata)
Tarkista oletko maalissa
Älä koskaan mene reitille, joss on kaksi merkkiä
Kun saavut risteykseen, jossa muita reittejä kun tuloreitti ei ole merkitty, valitse 		satunnainen reitti
Muuten:
	- Jos reitillä josta saavuit on vain yksi merkki, palaa sinne
	- Jos kaksi, valitse reitti joss on vähiten merkkejä, jos yhtä paljon valitse 		   satunnaisesti

**LeftWallFollower**

Liiku labyrintissä pitäen vasen käsi seinässä.
Löytää reitin ulos vain täydellisissä labyrinteissä.


<h3> Saavutetut aika- ja tilavaativuudet </h3>

**Leveyshaku**

Täsmentyy myöhemmin. Aikavaativuus oletettavasti O(V+E), missä V on solmujen ja E kaarien määrä.

**Thremauxin algoritmi**

Täsmentyy myöhemmin. Aikavaativuus oletettavasti O(V+E), missä V on solmujen ja E kaarien määrä. Tämä siksi, koska Thremaux on käytännössä syvyyshaku.

**LeftWallFollower**

Täsmentyy myöhemmin. Huonoimmassa tapauksessa, eli kun reittiä ei ole ulos, käy kaikki mahdolliset solmut ja reunat läpi palaten takaisin lähtöön. Eli O(V+E), missä V on solmujen ja E kaarien määrä.

<h3> Suorituskyky- ja O-analyysivertailu </h3>

**Leveyshaku**



**Thremauxin algoritmi**



**LeftWallFollower**



<h3> Lähteet </h3>
