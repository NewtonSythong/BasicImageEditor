package cosc202.andie;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.awt.BasicStroke;

/**
 * <p>
 * ImageOperation to draw a line between two points on the image.
 * </p>
 * 
 * <p>
 * This class handles the drawing of lines. Users can specify
 * the start and end points of the line, by utilizing the selection rectangle
 * or from where the user starts pressing to where they release and this class will render the line
 * on the image using the specified color.
 * </p>
 * 
 * <p>
 * This is useful for adding annotations or highlighting parts of an image.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Tele Tamati
 */
public class DrawLine implements ImageOperation{


    private Color color;
    private int drawWidth;
    private Shape shape;

    /**
     * Constructor to create a DrawLine operation.
     * @param startPoint Starting point of the line.
     * @param endPoint Ending point of the line.
     * @param color Color of the line.
     */
    public DrawLine (Point startPoint, Point endPoint, Color color, int drawWidth){

        this.color = color;
        this.drawWidth = drawWidth;
        this.shape = new Line2D.Float(startPoint, endPoint);
    }


    /**
     * Applies the drawing operation to a given BufferedImage.
     * @param image The image to draw the line on.
     * @return The modified image with the line drawn.
     */
    public BufferedImage apply(BufferedImage image){
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(drawWidth));
        g2d.drawImage(image, 0, 0, null);
        g2d.draw(shape);
        g2d.dispose();
        return image;
    }
    
}
