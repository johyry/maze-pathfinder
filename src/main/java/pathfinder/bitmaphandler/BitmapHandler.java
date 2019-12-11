/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.bitmaphandler;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author johyry
 */
public class BitmapHandler {

    private String fileLocation;

    public BitmapHandler(String filelocation) {
        this.fileLocation = filelocation;
    }

    /**
     * Reads the specified file Black pixel = PATH -> 1 White pixel = WALL -> 0
     *
     * @return Returns int[][] representing bitmap
     */
    public int[][] readFileAndReturnArray() {
        try {
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
        } catch (IOException exception) {
            System.out.println(exception);
            return null;
        }
    }

    public void generateJPGImageFromArray(int[][] array, String number) {
        try {

            array = changeArrayToRGBValues(array);

            BufferedImage bufferedImage = new BufferedImage(array.length, array[0].length, BufferedImage.TYPE_INT_RGB);

            for (int y = 0; y < array.length; y++) {
                for (int x = 0; x < array[0].length; x++) {
                    bufferedImage.setRGB(x, y, array[y][x]);
                }
            }

            File outputfile = new File("solvedMaze" + number + ".jpg");
            ImageIO.write(bufferedImage, "jpg", outputfile);
        } catch (IOException exception) {
            System.out.println(exception);
        }

    }

    private int[][] changeArrayToRGBValues(int[][] array) {
        for (int y = 0; y < array.length; y++) {
            for (int x = 0; x < array[0].length; x++) {
                int value = array[y][x];
                if (value == 0) {
                    array[y][x] = Color.WHITE.getRGB();
                } else if (value == 1) {
                    array[y][x] = Color.BLACK.getRGB();
                } else if (value == 2) {
                    array[y][x] = Color.GRAY.getRGB();
                }
            }
        }

        return array;
    }
}
