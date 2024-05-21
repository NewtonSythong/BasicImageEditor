package cosc202.andie;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

/**
 * This class is used to apply block averaging to an image.
 * It averages the colour of a block of pixels and sets the colour of all the
 * pixels in the block to the average colour.
 * 
 * @author Newton Sythong
 * 
 */
public class BlockAveraging implements ImageOperation {

    private int blockHeight;
    private int blockWidth;

    /**
     * Constructor for BlockAveraging
     * 
     * @param blockSize The size of the block to average
     */
    BlockAveraging(int blockheight, int blockWidth) {
        this.blockHeight = blockheight;
        this.blockWidth = blockWidth;

    }

    /**
     * Constructor for BlockAveraging with default block size of 2
     */
    BlockAveraging() {
        this(2, 2);
    }

    /**
     * Applies block averaging to the input image
     * 
     * @param input The image to apply block averaging to
     * @return The image with block averaging applied
     */
    public BufferedImage apply(BufferedImage input) {
        try {

            if (input != null) {
                int width = input.getWidth();
                int height = input.getHeight();

                BufferedImage output = new BufferedImage(width, height, input.getType());

                for (int y = 0; y < height; y += blockHeight) {
                    for (int x = 0; x < width; x += blockWidth) {
                        int[] rgb = new int[3];
                        int count = 0;
                        Arrays.fill(rgb, 0);
                        for (int j = 0; j < blockHeight && y + j < height; j++) {
                            for (int i = 0; i < blockWidth && x + i < width; i++) {
                                int pixel = input.getRGB(x + i, y + j);
                                rgb[0] += (pixel >> 16) & 0xff; // red
                                rgb[1] += (pixel >> 8) & 0xff;  // green
                                rgb[2] += pixel & 0xff;         // blue
                                count++;
                            }
                        }
                        int avgRed = rgb[0] / count;
                        int avgGreen = rgb[1] / count;
                        int avgBlue = rgb[2] / count;
                
                        for (int j = 0; j < blockHeight && y + j < height; j++) {
                            for (int i = 0; i < blockWidth && x + i < width; i++) {
                                int alpha = (input.getRGB(x + i, y + j) >> 24) & 0xff; // original alpha
                                int avgColor = (alpha << 24) | (avgRed << 16) | (avgGreen << 8) | avgBlue;
                                output.setRGB(x + i, y + j, avgColor);
                            }
                        }
                    }
                }
                return output;
            } else {
                throw new NullPointerException();
            }

        } catch (NullPointerException e) {
            ResourceBundle bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle");
            JOptionPane.showMessageDialog(null, bundle.getString("NoImageSelected"));
            return null;
        }
    }
}