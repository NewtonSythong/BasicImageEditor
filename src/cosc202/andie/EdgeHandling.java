package cosc202.andie;

import java.awt.image.*;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Image;

/**
 * <p>
 * Edge handling for Gaussian, Mean and Sharpen filters
 * to prevent the edges from getting cut off when the filter is applied
 * </p>
 * 
 * <p>
 * Edges are handled by creating a slightly larger image, replacing the middle
 * part of the larger image with the original image and then applying the
 * filter.
 * The larger image is then cropped back to original size
 * </p>
 *
 *
 * @author Jenny Kim
 * @version 1.0
 */
public class EdgeHandling {

    BufferedImage input;

    public EdgeHandling() {
    }

    /**
     * Method to add padding to the edges of the original image and make it larger
     * 
     * @param src    the original image
     * @param radius the amount of pixels to add to the width and height of the
     *               image
     * @return the padded image
     */
    public BufferedImage addPadding(BufferedImage src, int radius) {
        int newWidth = src.getWidth() + 2 * radius;
        int newHeight = src.getHeight() + 2 * radius;

        // BufferedImage paddedImage = new BufferedImage(newWidth, newHeight, src.getType());
        Image image = src.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);

        BufferedImage paddedImage = new BufferedImage(newWidth, newHeight, src.getType());

        Graphics2D g = paddedImage.createGraphics();
        // g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        g.drawImage(image, 0, 0, null);
        g.dispose();

        return paddedImage;
    }

    /**
     * Crops the edges of the image by the radius
     * 
     * @param src    the padded image
     * @param radius the amount to cut off the edges
     * @return the image with no padding
     */
    public BufferedImage cropImage(BufferedImage src, int radius) {
        int newWidth = src.getWidth() - 2 * radius;
        int newHeight = src.getHeight() - 2 * radius;

        BufferedImage croppedImage = src.getSubimage(radius, radius, newWidth, newHeight);

        return croppedImage;
    }

    // crop out the original image width * height from the enlarged image
    /**
     * Crops the middle part of the image out so we are left with only
     * the padding pixels
     * 
     * @param src            the padded image
     * @param widthOriginal  the width of the gap to be left in the middle
     * @param heightOriginal the height of the gap to be left in the middle
     * @return an image with only edges and a transparent centre
     */
    public BufferedImage cropMiddle(BufferedImage src, int widthOriginal, int heightOriginal) {
        int startX = (src.getWidth() - widthOriginal) / 2;
        int startY = (src.getHeight() - heightOriginal) / 2;

        BufferedImage edgeImage = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());

        Graphics2D g = edgeImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        g.drawImage(src, 0, 0, null);
        g.clearRect(startX, startY, widthOriginal, heightOriginal);
        g.dispose();

        return edgeImage;
    }

    /**
     * Add an image to the transparent part of a larger image
     * 
     * @param edgeImage the image with only edges left
     * @param original  the image to replace the transparent middle
     * @return an image of original size with padding on the edges
     */
    public BufferedImage addOriginalImage(BufferedImage edgeImage, BufferedImage original) {
        int startX = (edgeImage.getWidth() - original.getWidth()) / 2;
        int startY = (edgeImage.getHeight() - original.getHeight()) / 2;

        Graphics2D g = edgeImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        g.drawImage(original, startX, startY, null);
        g.dispose();

        return edgeImage;
    }
}
