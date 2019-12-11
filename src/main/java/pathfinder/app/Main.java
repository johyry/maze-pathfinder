/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.app;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author johyry
 */
public class Main {

    public static void main(String[] args) {
        App application = new App();
        Scanner lukija = new Scanner(System.in);
        application.run(lukija);

    }

}
