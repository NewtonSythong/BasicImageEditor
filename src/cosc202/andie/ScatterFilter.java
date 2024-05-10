package cosc202.andie;

import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

/**
 * This class is used to apply a scatter filter to an image.
 * The filter scatters pixels of an image within certiain radius.
 * 
 * @author Jenny Kim
 * 
 */
public class ScatterFilter implements ImageOperation {

    private int radius;

    /**
     * Constructs a ScatterFilter with a radius of 1.
     */
    public ScatterFilter() {
        this(1);
    }

    /**
     * Constructs a ScatterFilter with a given radius.
     * 
     * @param radius the radius of the scatter filter
     */
    public ScatterFilter(int radius) {
        this.radius = radius;
    }
    /**
     * Applies the scatter filter to the input image.
     * @param input the image to which the filter is applied
     * @return the image with the scatter filter applied
     */
    public BufferedImage apply(BufferedImage input) {
        try {

            if (input != null) {
                Random ran = new Random();

                int width = input.getWidth();
                int height = input.getHeight();

                BufferedImage output = new BufferedImage(width, height, input.getType());

                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        int scatterX = x + ran.nextInt(2 * radius + 1) - radius;
                        int scatterY = y + ran.nextInt(2 * radius + 1) - radius;
                        scatterX = Math.max(0, Math.min(scatterX, width - 1));
                        scatterY = Math.max(0, Math.min(scatterY, height - 1));

                        output.setRGB(x, y, input.getRGB(scatterX, scatterY));
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
