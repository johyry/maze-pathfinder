/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.bitmapreader;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author johyry
 */
public class BitmapReader {

    private String fileLocation;

    public BitmapReader(String filelocation) {
        this.fileLocation = filelocation;
    }
    
    /**
     * Reads the specified file
     * Black pixel = PATH -> 1
     * White pixel = WALL -> 0
     * @return Returns int[][] representing bitmap
     */

    public int[][] readFileAndReturnArray() throws IOException {
        File bmpFile = new File(fileLocation);
        BufferedImage image = ImageIO.read(bmpFile);

        int[][] array2D = new int[image.getHeight()][image.getWidth()];

        for (int yPixel = 0; yPixel < image.getHeight(); yPixel++) {
            for (int xPixel = 0; xPixel < image.getWidth(); xPixel++) {
                int color = image.getRGB(xPixel, yPixel);
                if (color == Color.BLACK.getRGB()) {
                    array2D[yPixel][xPixel] = 1;
                } else {
                    array2D[yPixel][xPixel] = 0;
                }
            }
        }

        return array2D;
    }
}
