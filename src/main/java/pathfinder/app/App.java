/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.app;

import java.io.IOException;
import pathfinder.bitmapreader.BitmapReader;
import pathfinder.domain.BreathFirstSearch;
import pathfinder.domain.Coordinates;
import pathfinder.domain.LeftWallFollower;
import pathfinder.domain.MazeAnalyzer;
import pathfinder.domain.Thremaux;
import pathfinder.datastructures.Queue;

/**
 *
 * @author johyry
 */
public class App {

    public void run() throws IOException {
        System.out.println("Tervetuloa labyrinttisoftaan! \n");

        // initializes bitmapreader and calls the method readfileandarray to get array representing the bitmap
        // in the array 1 = path, 0 = wall
        // Kun haluat suorittaa haun, valitse kyseisistä valmiiksi tuotetuista labyrinteistä joku ja kommentoi muut pois.
        // Oikealla on koko ja labyrintin tyyppi. Kaikki ovat tällä hetkellä täydellisiä tyypiltään.
//        BitmapReader bitmapreader = new BitmapReader("mazes/example1.bmp"); // koko 5x5 TÄYDELLINEN
        BitmapReader bitmapreader = new BitmapReader("mazes/example2.bmp"); // koko 101x101 TÄYDELLINEN
//        BitmapReader bitmapreader = new BitmapReader("mazes/example3.bmp"); // koko 1001x1001 TÄYDELLINEN
//         BitmapReader bitmapreader = new BitmapReader("mazes/example4.bmp"); // 501x501 TÄYDELLINEN
//        BitmapReader bitmapreader = new BitmapReader("mazes/example5.bmp"); // Koko 2501X2501 TÄYDELLINEN
//        BitmapReader bitmapreader = new BitmapReader("mazes/example6.bmp"); // Koko 5001x5001 TÄYDELLINEN

        int[][] maze = bitmapreader.readFileAndReturnArray();

        MazeAnalyzer analyzer = new MazeAnalyzer(maze);
        Coordinates start = analyzer.findStartCoordinates();
        Coordinates goal = analyzer.findGoalCoordinates();

        long summa1 = 0;
        long summa2 = 0;
        long summa3 = 0;

        int kierroksia = 10;

        for (int i = 0; i < kierroksia; i++) {
            
            LeftWallFollower lfw = new LeftWallFollower(maze, start, goal);
            long time1 = System.currentTimeMillis();
            if (lfw.init() == 1) {
                System.out.println("LFW found route");
                System.out.println("Rounds of calculations done: " + lfw.getRoundsOfCalculations());
            } else {
                System.out.println("LFW didnt find route");
            }
            long time2 = System.currentTimeMillis();
            summa1 += time2 - time1;

            System.out.println("");

            Thremaux thremaux = new Thremaux(maze, start, goal);
            long time3 = System.currentTimeMillis();
            if (thremaux.init() == 1) {
                System.out.println("Thremaux found route");
                System.out.println("Rounds of calculations done: " + thremaux.getRoundsOfCalculations());
                System.out.println("Length of route: " + thremaux.getRouteLength());
            } else {
                System.out.println("Thremaux didnt find route");
            }
            long time4 = System.currentTimeMillis();
            summa2 += time4 - time3;

            System.out.println("");

            BreathFirstSearch leveyshaku = new BreathFirstSearch(maze, start, goal);
            long time5 = System.currentTimeMillis();
            if (leveyshaku.init() == 1) {
                System.out.println("BFS found route");
                System.out.println("Rounds of calculations done: " + leveyshaku.getRoundsOfCalculations());
                System.out.println("Length of route: " + leveyshaku.getRouteLength());
            } else {
                System.out.println("BFS didnt find route");
            }
            long time6 = System.currentTimeMillis();
            summa3 += time6 - time5;

            System.out.println("");
        }

        System.out.println("LFW keskiarvo " + kierroksia + " hausta: " + summa1 / kierroksia + " ms");
        System.out.println("Thremauxin keskiarvo " + kierroksia + " hausta: " + summa2 / kierroksia + " ms");
        System.out.println("BFS keskiarvo " + kierroksia + " hausta: " + summa3 / kierroksia + " ms");

    }

    public void printMaze(int[][] maze) {
        for (int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze[0].length; x++) {
                System.out.print(maze[y][x]);
            }
            System.out.println(" " + y + " ");
        }
    }

}
