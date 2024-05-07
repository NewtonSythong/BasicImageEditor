package cosc202.andie;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to draw an oval within a specified bounding rectangle on the image.
 * </p>
 * 
 * <p>
 * This class enables the drawing of ovals and circles. Users define the bounding
 * rectangle, which determines the shape's location and proportions. The shape can be either
 * filled with color or outlined, based on user preferences.
 * </p>
 * 
 * <p>
 * Suitable for creating visual markers or emphasizing elements within images.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * 
 * @author Tele Tamati
 */
public class DrawOval implements ImageOperation {
    private Rectangle bounds;
    private Color color;
    private boolean fill;

     /**
     * Constructor to create a DrawOval operation.
     * @param bounds Rectangle defining the bounding box of the oval.
     * @param color Color of the oval.
     * @param fill Whether the oval should be filled.
     */
    public DrawOval(Rectangle bounds, Color color, boolean fill){
        this.bounds = bounds;
        this.color = color;
        this.fill = fill;
    }

    /**
     * Applies the drawing operation to a given BufferedImage.
     * @param image The image to draw the oval on.
     * @return The modified image with the oval drawn.
     */
    public BufferedImage apply(BufferedImage image) {
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(color);
        if(fill){
            g2d.fillOval(bounds.x, bounds.y, bounds.width, bounds.height);
        } else {
            g2d.drawOval(bounds.x,bounds.y, bounds.width,bounds.height);
        }
        g2d.dispose();
        return image;
    }
}
