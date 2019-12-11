/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.app;

import java.util.Scanner;
import pathfinder.bitmaphandler.BitmapHandler;
import pathfinder.domain.BreathFirstSearch;
import pathfinder.domain.Coordinates;
import pathfinder.domain.LeftWallFollower;
import pathfinder.domain.MazeAnalyzer;
import pathfinder.domain.Thremaux;

/**
 *
 * @author johyry
 */
public class App {

    public void run(Scanner lukija) {
        System.out.println("Tervetuloa labyrinttisoftaan! \n");

        while (true) {

            System.out.println("Valitse yksi seuraavista \n"
                    + "1. Algoritmien suoritusaikavertailu \n"
                    + "2. Algoritmien toimintavertailu \n"
                    + "3. Lopeta");

            String input1 = lukija.nextLine();

            if (input1.equals("3")) {
                break;
            }

            System.out.println("Valitse labyrintin tyyppi: \n"
                    + "1. Perfect (tasan yksi ratkaisu) \n"
                    + "2. Braided (mahdollisesti monta ratkaisua)");
            String input2 = lukija.nextLine();

            System.out.println("Valitse labyrintin koko: \n"
                    + "1. 5x5\n"
                    + "2. 101x101\n"
                    + "3. 501x501\n"
                    + "4. 1001x1001\n"
                    + "5. 2501x2501\n"
                    + "6. 5001x5001");

            String input3 = lukija.nextLine();

            if (input1.equals("1")) {
                if (input2.equals("1")) {
                    comparePerformanceOfAlgorithmsInPerfectMaze(input3);
                } else if (input2.equals("2")) {
                    comparePerformanceOfAlgorithmsInBraidedMaze(input3);
                }
            } else if (input1.equals("2")) {
                if (input2.equals("1")) {
                    compareAlgorithmsInPerfectMazeAndDrawPictures(input3);
                } else if (input2.equals("2")) {
                    compareAlgorithmsInBraidedMazeAndDrawPictures(input3);
                }
            }

        }

    }

    public void printMaze(int[][] maze) {
        for (int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze[0].length; x++) {
                System.out.print(maze[y][x]);
            }
            System.out.println(" " + y + " ");
        }
    }

    private void comparePerformanceOfAlgorithmsInPerfectMaze(String line2) {

        BitmapHandler bitmapHandler = new BitmapHandler("mazes/perfect/example" + line2 + ".bmp");

        int[][] maze = bitmapHandler.readFileAndReturnArray();

        MazeAnalyzer analyzer = new MazeAnalyzer(maze);
        Coordinates start = analyzer.findStartCoordinates();
        Coordinates goal = analyzer.findGoalCoordinates();

        long summa1 = 0;
        long summa2 = 0;
        long summa3 = 0;

        int lfwRounds = 0;
        int thmxRounds = 0;
        int bfsRounds = 0;

        int thmxShortest = 0;
        int bfsShortest = 0;

        int kierroksia = 100;

        for (int i = 0; i < kierroksia; i++) {

            LeftWallFollower lfw = new LeftWallFollower(maze, start, goal);
            long time1 = System.currentTimeMillis();
            if (lfw.init() == 1) {
                lfwRounds += lfw.getRoundsOfCalculations();
            } else {
                System.out.println("LFW didnt find route");
            }
            long time2 = System.currentTimeMillis();
            summa1 += time2 - time1;

            Thremaux thremaux = new Thremaux(maze, start, goal);
            long time3 = System.currentTimeMillis();
            if (thremaux.init() == 1) {
                thmxRounds += thremaux.getRoundsOfCalculations();
                thmxShortest += thremaux.getRouteLength();
            } else {
                System.out.println("Thremaux didnt find route");
            }
            long time4 = System.currentTimeMillis();
            summa2 += time4 - time3;

            BreathFirstSearch leveyshaku = new BreathFirstSearch(maze, start, goal);
            long time5 = System.currentTimeMillis();
            if (leveyshaku.init() == 1) {
                bfsRounds += leveyshaku.getRoundsOfCalculations();
                bfsShortest += leveyshaku.getRouteLength();
            } else {
                System.out.println("BFS didnt find route");
            }
            long time6 = System.currentTimeMillis();
            summa3 += time6 - time5;

        }

        System.out.println("LFW:");
        System.out.println("Aikakeskiarvo " + kierroksia + " hausta: " + summa1 / kierroksia + " ms");
        System.out.println("Laskukierroksia keskimäärin: " + lfwRounds / kierroksia);
        System.out.println("");
        System.out.println("Thremaux:");
        System.out.println("Aikakeskiarvo " + kierroksia + " hausta: " + summa2 / kierroksia + " ms");
        System.out.println("Laskukierroksia keskimäärin: " + thmxRounds / kierroksia);
        System.out.println("Lyhin reitti keskimäärin: " + thmxShortest / kierroksia);
        System.out.println("");
        System.out.println("BFS:");
        System.out.println("Aikakeskiarvo " + kierroksia + " hausta: " + summa3 / kierroksia + " ms");
        System.out.println("Laskukierroksia keskimäärin: " + bfsRounds / kierroksia);
        System.out.println("Lyhin reitti keskimäärin: " + bfsShortest / kierroksia);
        System.out.println("");

    }

    private void comparePerformanceOfAlgorithmsInBraidedMaze(String input3) {
        BitmapHandler bitmapHandler = new BitmapHandler("mazes/braided/example" + input3 + ".bmp");

        int[][] maze = bitmapHandler.readFileAndReturnArray();

        MazeAnalyzer analyzer = new MazeAnalyzer(maze);
        Coordinates start = analyzer.findStartCoordinates();
        Coordinates goal = analyzer.findGoalCoordinates();

        long summa1 = 0;
        long summa2 = 0;
        long summa3 = 0;

        int thmxRounds = 0;
        int bfsRounds = 0;

        int thmxShortest = 0;
        int bfsShortest = 0;

        int kierroksia = 10;

        for (int i = 0; i < kierroksia; i++) {

            Thremaux thremaux = new Thremaux(maze, start, goal);
            long time3 = System.currentTimeMillis();
            if (thremaux.init() == 1) {
                thmxRounds += thremaux.getRoundsOfCalculations();
                thmxShortest += thremaux.getRouteLength();
            } else {
                System.out.println("Thremaux didnt find route");
            }
            long time4 = System.currentTimeMillis();
            summa2 += time4 - time3;

            BreathFirstSearch leveyshaku = new BreathFirstSearch(maze, start, goal);
            long time5 = System.currentTimeMillis();
            if (leveyshaku.init() == 1) {
                bfsRounds += leveyshaku.getRoundsOfCalculations();
                bfsShortest += leveyshaku.getRouteLength();
            } else {
                System.out.println("BFS didnt find route");
            }
            long time6 = System.currentTimeMillis();
            summa3 += time6 - time5;

        }

        System.out.println("Thremaux:");
        System.out.println("Aikakeskiarvo " + kierroksia + " hausta: " + summa2 / kierroksia + " ms");
        System.out.println("Laskukierroksia keskimäärin: " + thmxRounds / kierroksia);
        System.out.println("Lyhin reitti keskimäärin: " + thmxShortest / kierroksia);
        System.out.println("");
        System.out.println("BFS:");
        System.out.println("Aikakeskiarvo " + kierroksia + " hausta: " + summa3 / kierroksia + " ms");
        System.out.println("Laskukierroksia keskimäärin: " + bfsRounds / kierroksia);
        System.out.println("Lyhin reitti keskimäärin: " + bfsShortest / kierroksia);
        System.out.println("");

    }

    private void compareAlgorithmsInPerfectMazeAndDrawPictures(String input3) {
        BitmapHandler bitmapHandler = new BitmapHandler("mazes/perfect/example" + input3 + ".bmp");

        int[][] maze = bitmapHandler.readFileAndReturnArray();

        MazeAnalyzer analyzer = new MazeAnalyzer(maze);
        Coordinates start = analyzer.findStartCoordinates();
        Coordinates goal = analyzer.findGoalCoordinates();

        int lfwRounds = 0;
        int thmxRounds = 0;
        int bfsRounds = 0;

        int thmxShortest = 0;
        int bfsShortest = 0;

        LeftWallFollower lfw = new LeftWallFollower(maze, start, goal);
        long time1 = System.currentTimeMillis();
        if (lfw.init() == 1) {
            lfwRounds = lfw.getRoundsOfCalculations();
        } else {
            System.out.println("LFW didnt find route");
        }
        long time2 = System.currentTimeMillis();
        bitmapHandler.generateJPGImageFromArray(lfw.getMarked(), "1");

        long summa1 = time2 - time1;

        Thremaux thremaux = new Thremaux(maze, start, goal);
        long time3 = System.currentTimeMillis();
        if (thremaux.init() == 1) {
            thmxRounds = thremaux.getRoundsOfCalculations();
            thmxShortest = thremaux.getRouteLength();
        } else {
            System.out.println("Thremaux didnt find route");
        }
        long time4 = System.currentTimeMillis();
        bitmapHandler.generateJPGImageFromArray(thremaux.getMarked(), "2");

        long summa2 = time4 - time3;

        BreathFirstSearch leveyshaku = new BreathFirstSearch(maze, start, goal);
        long time5 = System.currentTimeMillis();
        if (leveyshaku.init() == 1) {
            bfsRounds = leveyshaku.getRoundsOfCalculations();
            bfsShortest = leveyshaku.getRouteLength();
        } else {
            System.out.println("BFS didnt find route");
        }
        long time6 = System.currentTimeMillis();
        bitmapHandler.generateJPGImageFromArray(leveyshaku.getFoundRoute(), "3");
        long summa3 = time6 - time5;

        System.out.println("LFW:");
        System.out.println("Aika " + summa1 + " ms");
        System.out.println("Laskukierroksia: " + lfwRounds);
        System.out.println("");
        System.out.println("Thremaux:");
        System.out.println("Aikakeskiarvo " + summa2 + " ms");
        System.out.println("Laskukierroksia: " + thmxRounds);
        System.out.println("Lyhin reitti: " + thmxShortest);
        System.out.println("");
        System.out.println("BFS:");
        System.out.println("Aikakeskiarvo " + summa3 + " ms");
        System.out.println("Laskukierroksia keskimäärin: " + bfsRounds);
        System.out.println("Lyhin reitti keskimäärin: " + bfsShortest);
        System.out.println("");
    }

    private void compareAlgorithmsInBraidedMazeAndDrawPictures(String input3) {
        BitmapHandler bitmapHandler = new BitmapHandler("mazes/braided/example" + input3 + ".bmp");

        int[][] maze = bitmapHandler.readFileAndReturnArray();

        MazeAnalyzer analyzer = new MazeAnalyzer(maze);
        Coordinates start = analyzer.findStartCoordinates();
        Coordinates goal = analyzer.findGoalCoordinates();

        int thmxRounds = 0;
        int bfsRounds = 0;

        int thmxShortest = 0;
        int bfsShortest = 0;

        
        Thremaux thremaux = new Thremaux(maze, start, goal);
        long time3 = System.currentTimeMillis();
        if (thremaux.init() == 1) {
            thmxRounds = thremaux.getRoundsOfCalculations();
            thmxShortest = thremaux.getRouteLength();
        } else {
            System.out.println("Thremaux didnt find route");
        }
        long time4 = System.currentTimeMillis();
        bitmapHandler.generateJPGImageFromArray(thremaux.getMarked(), "2");

        long summa2 = time4 - time3;

        BreathFirstSearch leveyshaku = new BreathFirstSearch(maze, start, goal);
        long time5 = System.currentTimeMillis();
        if (leveyshaku.init() == 1) {
            bfsRounds = leveyshaku.getRoundsOfCalculations();
            bfsShortest = leveyshaku.getRouteLength();
        } else {
            System.out.println("BFS didnt find route");
        }
        long time6 = System.currentTimeMillis();
        bitmapHandler.generateJPGImageFromArray(leveyshaku.getFoundRoute(), "3");
        long summa3 = time6 - time5;

        
        System.out.println("Thremaux:");
        System.out.println("Aikakeskiarvo " + summa2 + " ms");
        System.out.println("Laskukierroksia: " + thmxRounds);
        System.out.println("Lyhin reitti: " + thmxShortest);
        System.out.println("");
        System.out.println("BFS:");
        System.out.println("Aikakeskiarvo " + summa3 + " ms");
        System.out.println("Laskukierroksia keskimäärin: " + bfsRounds);
        System.out.println("Lyhin reitti keskimäärin: " + bfsShortest);
        System.out.println("");
    }

}
