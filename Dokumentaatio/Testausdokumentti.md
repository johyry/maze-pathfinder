<h1>Testausdokumentti</h1>


<h3>Yksikkötestit</h3>

Testikattavuus löytyy täältä:

Codecov: [![codecov](https://codecov.io/gh/johyry/maze-pathfinder/branch/master/graph/badge.svg)](https://codecov.io/gh/johyry/maze-pathfinder)


<h3>Suorituskykytestit</h3>

Testit on suoritettu manuaalisesti mainista testaten.


| Labyrintin koko  | Labyrintin tyyppi | Leveyshaku (ms) | Thremaux (ms) | LeftWallFollower (ms) | Laskentakierroksia keskimäärin (Leveyshaku) | Laskentakierroksia keskimäärin (Thremaux) | Laskentakierroksia keskimäärin (LeftWallFollower) | Keskiarvo # kierroksesta |
| ------------- | ------------- | :-------------: | :-------------: | :-------------: | :-------------: | :-------------: | :-------------: | :-------------: | 
| 101x101 | Täydellinen | 0 | 0 | 0 | 4600 | 4985 | 6265 | 100 |
| 501x501 | Täydellinen | 6 | 7 | 2 | 85721 | 123983 | 111197 | 100 |
| 1001x1001 | Täydellinen | 23 | 27 | 17 | 257843 | 467237 | 888951 | 100 |
| 2501x2501 | Täydellinen | 308 | 156 | 58 | 2900208 | 3187324 | 3438121 | 100 |
| 5001x5001 | Täydellinen | 2268 | 805 | 536 | 9238541 | 13792721 | 21625401 | 10 |

| Labyrintin koko  | Labyrintin tyyppi | Leveyshaku (ms) | Thremaux (ms) | Lyhimmän reitin pituus (Leveyshaku) | Lyhimmän reitin pituus (Thremaux) | Laskentakierroksia keskimäärin (Leveyshaku) | Laskentakierroksia keskimäärin (Thremaux) | Keskiarvo # kierroksesta |
| ------------- | ------------- | :-------------: | :-------------: | :-------------: | :-------------: | :-------------: | :-------------: | :-------------: | 
| 101x101 | Braided | 1 | 0 | 268 | 948 | 4478 | 5762 | 100 | 
| 501x501 | Braided | 8 | 6 | 1290 | 11799 | 114783 | 132702 | 100 | 
| 1001x1001 | Braided | 42 | 26 | 2510 | 45080 | 449849 | 525827 | 100 | 
| 2501x2501 | Braided | 402 | 186 | 6512 | 222099 | 2804233 | 3448630 | 100 | 
| 5001x5001 | Braided | 3697 | 1328 | 11712 | 691084 | 11675719 | 13885985 | 10 | 
