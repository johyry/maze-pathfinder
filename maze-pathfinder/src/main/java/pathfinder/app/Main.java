/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.app;

import java.io.IOException;

/**
 *
 * @author johyry
 */
public class Main {

    public static void main(String[] args) {
        App application = new App();
        try {
            application.run();
        } catch (IOException exception) {
            System.out.println(exception);
        }
        
        //File file = new File("example1.bmp");
        
    }

}
