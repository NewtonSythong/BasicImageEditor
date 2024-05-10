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
 *  @author Newton Sythong 
 * 
 */
public class BlockAveraging implements ImageOperation {

    private int blockSize;

    /**
     * Constructor for BlockAveraging
     * 
     * @param blockSize The size of the block to average
     */
    BlockAveraging(int blockSize) {
        this.blockSize = blockSize;
    }

    /**
     * Constructor for BlockAveraging with default block size of 2
     */
    BlockAveraging() {
        this(2);
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

                for (int y = 0; y < height; y += blockSize) {
                    for (int x = 0; x < width; x += blockSize) {
                        int[] rgb = new int[3];
                        int[] count = new int[3];
                        Arrays.fill(rgb, 0);
                        Arrays.fill(count, 0);
                        for (int j = 0; j < blockSize && y + j < height; j++) {
                            for (int i = 0; i < blockSize && x + i < width; i++) {
                                int pixel = input.getRGB(x + i, y + j);
                                rgb[0] += (pixel >> 16) & 0xff;
                                rgb[1] += (pixel >> 8) & 0xff;
                                rgb[2] += pixel & 0xff;
                                count[0]++;
                            }
                        }
                        int avgRed = rgb[0] / count[0];
                        int avgGreen = rgb[1] / count[0];
                        int avgBlue = rgb[2] / count[0];
                        int avgColor = (avgRed << 16) | (avgGreen << 8) | avgBlue;

                        for (int j = 0; j < blockSize && y + j < height; j++) {
                            for (int i = 0; i < blockSize && x + i < width; i++) {
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