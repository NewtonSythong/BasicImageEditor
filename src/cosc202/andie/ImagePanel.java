package cosc202.andie;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * <p>
 * UI display element for {@link EditableImage}s.
 * </p>
 * 
 * <p>
 * This class extends {@link JPanel} to allow for rendering of an image, as well as zooming
 * in and out. 
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills (modified by Tele Tamati)
 * @version 1.0
 */
public class ImagePanel extends JPanel {

    /**
     * The image to display in the ImagePanel.
     */
    private EditableImage image;

    /**
     * <p>
     * The zoom-level of the current view.
     * A scale of 1.0 represents actual size; 0.5 is zoomed out to half size; 1.5 is zoomed in to one-and-a-half size; and so forth.
     * </p>
     * 
     * <p>
     * Note that the scale is internally represented as a multiplier, but externally as a percentage.
     * </p>
     */
    private double scale;


    private Rectangle selectionRect = null;  // Rectangle to represent selected region


     /**
     * <p>
     * Create a new ImagePanel.
     * </p>
     * 
     * <p>
     * Newly created ImagePanels have a default zoom level of 100%
     * </p>
     */
    public ImagePanel() {
        image = new EditableImage();
        scale = 1.0;
        setupMouseHandlers();
    }

    /**
     * <p>
     * Get the currently displayed image
     * </p>
     *
     * @return the image currently displayed.
     */
    public EditableImage getImage() {
        return image;
    }

    /**
     * <p>
     * Get the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the original size, 50% is half-size, etc. 
     * </p>
     * @return The current zoom level as a percentage.
     */
    public double getZoom() {
        return 100 * scale;
    }

    /**
     * <p>
     * Set the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the original size, 50% is half-size, etc. 
     * The zoom level is restricted to the range [50, 200].
     * </p>
     * @param zoomPercent The new zoom level as a percentage.
     */
    public void setZoom(double zoomPercent) {
        if (zoomPercent < 50) {
            zoomPercent = 50;
        }
        if (zoomPercent > 200) {
            zoomPercent = 200;
        }
        scale = zoomPercent / 100;
    }

      /**
     * <p>
     * Gets the preferred size of this component for UI layout.
     * </p>
     * 
     * <p>
     * The preferred size is the size of the image (scaled by zoom level), or a default size if no image is present.
     * </p>
     * 
     * @return The preferred size of this component.
     */
    @Override
    public Dimension getPreferredSize() {
        if (image.hasImage()) {
            return new Dimension((int) Math.round(image.getCurrentImage().getWidth() * scale),
                                 (int) Math.round(image.getCurrentImage().getHeight() * scale));
        } else {
            return new Dimension(450, 450);
        }
    }

    /**
     * <p>
     * (Re)draw the component in the GUI.
     * </p>
     * 
     * @param g The Graphics component to draw the image on.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        if (image.hasImage()) {
            g2.scale(scale, scale);
            g2.drawImage(image.getCurrentImage(), null, 0, 0);
        }
        if (selectionRect != null) {
            g2.setColor(Color.RED); // Color for the selection rectangle
            g2.draw(selectionRect);
        }
        g2.dispose();
    }

     /**
     * Returns the current selection rectangle.
     * @return The current selection rectangle.
     */
    public Rectangle getRect() {
        return selectionRect;

    } 

    /**
     * Clears the current selection, removing the rectangle from the display.
     */
    public void clearSelection() {
        selectionRect = null;
        repaint();  // Repaint to remove the selection visually.
    }

    /**
     * Sets up mouse handlers for creating and modifying the selection rectangle.
     */
    private void setupMouseHandlers() {
        MouseAdapter mouseHandler = new MouseAdapter() {
            private Point startPoint;  // Starting point of the selection rectangle.

            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();  // Record the start point on mouse press.
                selectionRect = new Rectangle();  // Initialize the selection rectangle.
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int x = Math.min(startPoint.x, e.getX());  // Calculate top-left corner X.
                int y = Math.min(startPoint.y, e.getY());  // Calculate top-left corner Y.
                int width = Math.abs(e.getX() - startPoint.x);  // Calculate width of the rectangle.
                int height = Math.abs(e.getY() - startPoint.y);  // Calculate height of the rectangle.

                selectionRect.setBounds(x, y, width, height);  // Update the selection rectangle dimensions.
                repaint();  // Repaint to update the visual appearance of the rectangle.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (selectionRect.width == 0 || selectionRect.height == 0) {
                    selectionRect = null;  // Cancel the selection if rectangle is too small.
                }
                repaint();  // Repaint after releasing the mouse button.
            }
        };

        addMouseListener(mouseHandler);  // Add the mouse button handler.
        addMouseMotionListener(mouseHandler);  // Add the mouse motion handler.
    }
}