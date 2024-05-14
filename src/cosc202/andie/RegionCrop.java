package cosc202.andie;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.*;

/**
 * <p>
 * Implements an operation to extract a specific region from an image.
 * </p>
 * 
 * <p>
 * This operation isolates a user-defined region of an image, effectively removing the external areas. The process involves creating a smaller image from the defined rectangle, representing the area of interest.
 * The coordinates for the rectangle's top-left and bottom-right are used to define the cropping boundaries.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Tele Tamati
 */
public class RegionCrop implements ImageOperation, java.io.Serializable {

    /*
     * Holds the dimensions and position of the cropping area as a rectangle,
     * specifying the upper-left corner's x and y positions, and the rectangle's width and height.
     */
    private Rectangle cropArea;

    /**
     * Represents the scaling factor of the image when the crop is executed, ensuring the operation aligns with the user's view.
     */
    private double imageScale;

    /**
     * <p>
     * Initializes a new cropping operation for a specified image region.
     * </p>
     * 
     * <p>
     * The constructor prepares the operation by setting the region to be cropped and adjusting for any scale applied to the image display.
     * </p>
     * 
     * @param scale The display scale of the {@link ImagePanel} within which the {@link EditableImage} is shown.
     * @param rect defines the cropping boundaries.
     */
    public RegionCrop(double scale, Rectangle rect) {
        this.imageScale = scale;
        this.cropArea = rect;
    }

    /**
     * <p>
     * Executes the cropping operation on a provided image.
     * </p>
     * 
     * @param originalImage The image from which a region will be cropped.
     * @return A new BufferedImage object representing the cropped section of the original image.
     */
    public BufferedImage apply(BufferedImage originalImage) {
        // Calculate the actual coordinates and dimensions of the crop area, adjusted for the current image scale.
        int cropX = (int) (cropArea.x / imageScale);
        int cropY = (int) (cropArea.y / imageScale);
        int cropWidth = (int) (cropArea.width / imageScale);
        int cropHeight = (int) (cropArea.height / imageScale);
    
        // Ensure the crop area does not extend beyond the image bounds
        if (cropX < 0) {
            cropX = 0;
        }
        if (cropY < 0) {
            cropY = 0;
        }
    
        // Adjust width and height if they extend outside the image bounds
        if (cropX + cropWidth > originalImage.getWidth()) {
            cropWidth = originalImage.getWidth() - cropX;
        }
        if (cropY + cropHeight > originalImage.getHeight()) {
            cropHeight = originalImage.getHeight() - cropY;
        }
    
        // Now crop the image
        BufferedImage croppedImage = originalImage.getSubimage(cropX, cropY, cropWidth, cropHeight);
    
        // Create a copy of the cropped image to preserve the original properties
        BufferedImage reusult = new BufferedImage(croppedImage.getWidth(), croppedImage.getHeight(), originalImage.getType());
        Graphics graphics = reusult.createGraphics();
        graphics.drawImage(croppedImage, 0, 0, null);
        graphics.dispose();
    
        return reusult;
    }
    
}
