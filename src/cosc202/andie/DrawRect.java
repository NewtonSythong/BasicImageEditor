package cosc202.andie;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to draw a rectangle on the image.
 * </p> 
 * 
 * <p>
 * This class draws a rectangle on the image. 
 * It may either draw a filled rectangle or draw an outlined rectangle.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Tele Tamati
 */
public class DrawRect implements ImageOperation{

    private Rectangle rect;
    private Color color;
    private boolean fill;

    /**
     * Constructor to create a DrawRect operation.
     * @param rect Rectangle defining the area to draw.
     * @param color Color of the rectangle.
     * @param fill Whether the rectangle should be filled.
     * 
    */
    public DrawRect(Rectangle rect, Color color, boolean fill){
    this.rect = rect;
    this.color = color;
    this.fill = fill;  
    }


    /**
     * Applies the drawing operation to a given BufferedImage.
     * @param image The image to draw the rectangle on.
     * @return The modified image with the rectangle drawn.
    */
    public BufferedImage apply(BufferedImage input){
        Graphics2D g2d = input.createGraphics();
        g2d.setColor(color);
        if (fill) {
            g2d.fill(rect);
        } else { 
            g2d.draw(rect);
        }
        g2d.dispose();
        return input;
    }    
}
   

