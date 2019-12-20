/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.imagedisplayer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author johyry
 */
public class ImageDisplayer extends JFrame {

    public void displayImages(int amount) {
        if (amount == 3) {
            JFrame frame = new JFrame();
            ImageIcon icon = new ImageIcon("src/main/resources/solvedmazes/solvedMaze1.jpg");
            JLabel label = new JLabel(icon);
            frame.add(label);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }

        JFrame frame1 = new JFrame();
        ImageIcon icon1 = new ImageIcon("src/main/resources/solvedmazes/solvedMaze2.jpg");
        JLabel label1 = new JLabel(icon1);
        frame1.add(label1);
        frame1.pack();
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);

        JFrame frame2 = new JFrame();
        ImageIcon icon2 = new ImageIcon("src/main/resources/solvedmazes/solvedMaze3.jpg");
        JLabel label2 = new JLabel(icon2);
        frame2.add(label2);
        frame2.pack();
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setVisible(true);
    }
}
