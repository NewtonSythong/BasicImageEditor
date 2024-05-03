package cosc202.andie;

import java.awt.image.BufferedImage;
import java.util.Random;

public class ScatterFilter implements ImageOperation{

    private int radius;

    public ScatterFilter() {
        this(1);
    }

    public ScatterFilter(int radius) {
        this.radius = radius;    }

    public BufferedImage apply(BufferedImage input) {
        Random ran = new Random();

        int width = input.getWidth();
        int height = input.getHeight();

        BufferedImage output = new BufferedImage(width, height, input.getType());

        for (int y = 0; y < height;y++) {
            for (int x = 0; x < width; x++) {
                int scatterX = x + ran.nextInt(2*radius+1) - radius;
                int scatterY = y + ran.nextInt(2*radius+1) - radius;
                scatterX = Math.max(0, Math.min(scatterX, width - 1));
                scatterY = Math.max(0, Math.min(scatterY, height - 1));

                output.setRGB(x, y, input.getRGB(scatterX, scatterY));
            }
        }

        return output;

    }

    
}
