package pathfinder.datastructures;

import pathfinder.domain.Coordinates;

/**
 *
 * @author johyry
 */
public class Queue {

    private int size;
    private int beginningIndex;
    private int endingIndex;
    private Coordinates[] queue;
    private int tableSize;

    public Queue() {
        tableSize = 1000000;
        queue = new Coordinates[tableSize];
        beginningIndex = 0;
        endingIndex = 0;
        size = 0;
    }
    
    public Queue(int startSize) {
        tableSize = startSize;
        queue = new Coordinates[tableSize];
        beginningIndex = 0;
        endingIndex = 0;
        size = 0;
    }

    /**
     * Lisää yhden koordinaattiolion jonoon. - Jos indeksi joka merkkaa
     * viimeistä alkiota osoittaa taulukon loppuun, - ja aloitusindeksi on alle
     * puolenvälin -> Initialisoi uusi taulukko 2x koolla ja kopioi vanhat arvot
     * sinne - ja aloitusindeksi on yli puolenvälin -> Siirrä vanhat arvot
     * alkamaan nollasta alkaen
     *
     * @param xy lisättävä koordinaatti
     * @return true, jos onnistui, false jos ei
     */
    public boolean add(Coordinates xy) {

        if (endingIndex != tableSize) {
            queue[endingIndex] = xy;
            endingIndex++;
            size++;
            return true;
        } else {
            if (beginningIndex < tableSize / 2) {
                moveValuesToStartFromIndex0(tableSize * 2);
            } else {
                moveValuesToStartFromIndex0(tableSize);
            }
            add(xy);
        }

        return false;
    }

    /**
     * Palauttaa jonon pään
     *
     * @return Coordinates olio
     */
    public Coordinates poll() {
        if (size == 0) {
            return null;
        }

        Coordinates toReturn = queue[beginningIndex];
        queue[beginningIndex] = null;
        beginningIndex++;
        size--;
        return toReturn;
    }

    /**
     * Luo uuden taulukon koolla newSize ja
     * kopioi vanhan taulukon arvot alkamaan taas indeksistä 0 alkaen
     * 
     * 
     * @param newSize uuden taulukon koko
     * @return true, jos onnistui, false jos ei
     */
    public boolean moveValuesToStartFromIndex0(int newSize) {
        Coordinates[] newQue = new Coordinates[newSize];

        for (int i = 0; i < size; i++) {
            newQue[i] = queue[beginningIndex];
            beginningIndex++;
        }

        tableSize = newSize;
        beginningIndex = 0;
        endingIndex = size;
        queue = newQue;
        return true;
        
    }

    /**
     * Onko jono tyhjä vai ei
     * @return true, jos on, false jos ei
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Palauttaa senhetkisen taulukon koon
     * @return int taulukon koko missä jonoa simuloidaan
     */
    public int getTableSize() {
        return tableSize;
    }

    /**
     * Queue size
     * @return int queue size
     */
    public int getSize() {
        return size;
    }

    
    
    

}
