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
import pathfinder.domain.MazeAnalyzer;
import pathfinder.domain.Thremaux;

/**
 *
 * @author johyry
 */
public class App {

    public void run() throws IOException {
        System.out.println("Tervetuloa labyrinttisoftaan! \n");

        // initializes bitmapreader and calls the method readfileandarray to get array representing the bitmap
        // in the array 1 = path, 0 = wall
//        BitmapReader bitmapreader = new BitmapReader("example1.bmp"); // koko 5x5
//        BitmapReader bitmapreader = new BitmapReader("example2.bmp"); // koko 11x11
        BitmapReader bitmapreader = new BitmapReader("example3.bmp"); // koko 1001x1001
//         BitmapReader bitmapreader = new BitmapReader("example4.bmp");
//         BitmapReader bitmapreader = new BitmapReader("example5.bmp"); // Koko 17x17

        int[][] maze = bitmapreader.readFileAndReturnArray();

        MazeAnalyzer analyzer = new MazeAnalyzer(maze);
        Coordinates start = analyzer.findStartCoordinates();
        Coordinates goal = analyzer.findGoalCoordinates();

        System.out.println("Haetaan nopein reitti Thremauxilla: ");

        Thremaux thremaux = new Thremaux(maze, start, goal);
        thremaux.init();

        System.out.println("");
        System.out.println("Haetaan nopein reitti Leveyshaulla: ");

        BreathFirstSearch leveyshaku = new BreathFirstSearch(maze, start, goal);
//        //printMaze(maze);
        leveyshaku.init();
        //printMaze(maze);
//        System.out.println(leveyshaku.findStart());
//        System.out.println(leveyshaku.findGoal());

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
