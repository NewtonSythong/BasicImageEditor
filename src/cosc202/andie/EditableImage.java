package cosc202.andie;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;

import javax.imageio.ImageIO;

/**
 * <p>
 * An image with a set of operations applied to it.
 * </p>
 * 
 * <p>
 * The EditableImage represents an image with a series of operations applied to
 * it.
 * It is fairly core to the ANDIE program, being the central data structure.
 * The operations are applied to a copy of the original image so that they can
 * be undone.
 * THis is what is meant by "A Non-Destructive Image Editor" - you can always
 * undo back to the original image.
 * </p>
 * 
 * <p>
 * Internally the EditableImage has two {@link BufferedImage}s - the original
 * image
 * and the result of applying the current set of operations to it.
 * The operations themselves are stored on a {@link Stack}, with a second
 * {@link Stack}
 * being used to allow undone operations to be redone.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
class EditableImage {

    /** The original image. This should never be altered by ANDIE. */
    private BufferedImage original;
    /**
     * The current image, the result of applying {@link ops} to {@link original}.
     */
    private BufferedImage current;
    /** The sequence of operations currently applied to the image. */
    private Stack<ImageOperation> ops;
    /** A memory of 'undone' operations to support 'redo'. */
    private Stack<ImageOperation> redoOps;
    /** The file where the original image is stored/ */
    private String imageFilename;
    /** The file where the operation sequence is stored. */
    private String opsFilename;
    /** Check if Image is edited */
    private static boolean ImageEdited;
    /** Stack of macro operations */
    protected Stack<ImageOperation> macros;
    /** Macro recording status */
    protected boolean isRecording = false;

    /**
     * <p>
     * Create a new EditableImage.
     * </p>
     * 
     * <p>
     * A new EditableImage has no image (it is a null reference), and an empty stack
     * of operations.
     * </p>
     */
    public EditableImage() {
        original = null;
        current = null;
        ops = new Stack<ImageOperation>();
        redoOps = new Stack<ImageOperation>();
        imageFilename = null;
        opsFilename = null;
        macros = new Stack<ImageOperation>();
    }

    public String getOpsFilename() {
        return opsFilename;
    }

    public String getImageFilename() {
        return imageFilename;
    }

    /**
     * <p>
     * Check if there is an image loaded.
     * </p>
     * 
     * @return True if there is an image, false otherwise.
     */
    public boolean hasImage() {
        return current != null;
    }

    /**
     * <p>
     * Make a 'deep' copy of a BufferedImage.
     * </p>
     * 
     * <p>
     * Object instances in Java are accessed via references, which means that
     * assignment does
     * not copy an object, it merely makes another reference to the original.
     * In order to make an independent copy, the {@code clone()} method is generally
     * used.
     * {@link BufferedImage} does not implement {@link Cloneable} interface, and so
     * the
     * {@code clone()} method is not accessible.
     * </p>
     * 
     * <p>
     * This method makes a cloned copy of a BufferedImage.
     * This requires knowledge of some details about the internals of the
     * BufferedImage,
     * but essentially comes down to making a new BufferedImage made up of copies of
     * the internal parts of the input.
     * </p>
     * 
     * <p>
     * This code is taken from StackOverflow:
     * <a href=
     * "https://stackoverflow.com/a/3514297">https://stackoverflow.com/a/3514297</a>
     * in response to
     * <a href=
     * "https://stackoverflow.com/questions/3514158/how-do-you-clone-a-bufferedimage">https://stackoverflow.com/questions/3514158/how-do-you-clone-a-bufferedimage</a>.
     * Code by Klark used under the CC BY-SA 2.5 license.
     * </p>
     * 
     * <p>
     * This method (only) is released under
     * <a href="https://creativecommons.org/licenses/by-sa/2.5/">CC BY-SA 2.5</a>
     * </p>
     * 
     * @param bi The BufferedImage to copy.
     * @return A deep copy of the input.
     */
    private static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    /**
     * <p>
     * Open an image from a file.
     * </p>
     * 
     * <p>
     * Opens an image from the specified file.
     * Also tries to open a set of operations from the file with <code>.ops</code>
     * added.
     * So if you open <code>some/path/to/image.png</code>, this method will also try
     * to
     * read the operations from <code>some/path/to/image.png.ops</code>.
     * </p>
     * 
     * @param filePath The file to open the image from.
     * @throws Exception If something goes wrong.
     */
    public void open(String filePath) throws Exception {
        imageFilename = filePath;
        opsFilename = imageFilename + ".ops";
        File imageFile = new File(imageFilename);
        original = ImageIO.read(imageFile);
        current = deepCopy(original);
        try {
            FileInputStream fileIn = new FileInputStream(this.opsFilename);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            // Silence the Java compiler warning about type casting.
            // Understanding the cause of the warning is way beyond
            // the scope of COSC202, but if you're interested, it has
            // to do with "type erasure" in Java: the compiler cannot
            // produce code that fails at this point in all cases in
            // which there is actually a type mismatch for one of the
            // elements within the Stack, i.e., a non-ImageOperation.
            @SuppressWarnings("unchecked")
            Stack<ImageOperation> opsFromFile = (Stack<ImageOperation>) objIn.readObject();
            ops = opsFromFile;
            redoOps.clear();
            objIn.close();
            fileIn.close();
        } catch (Exception ex) {
            // Could be no file or something else. Carry on for now.
            ops.clear();
            redoOps.clear();
        }
        this.refresh();
    }

    /**
     * <p>
     * Save an image to file.
     * </p>
     * 
     * <p>
     * Saves an image to the file it was opened from, or the most recent file saved
     * as.
     * Also saves a set of operations from the file with <code>.ops</code> added.
     * So if you save to <code>some/path/to/image.png</code>, this method will also
     * save
     * the current operations to <code>some/path/to/image.png.ops</code>.
     * </p>
     * 
     * @throws Exception If something goes wrong.
     */
    public void save() throws Exception {
        if (this.opsFilename == null) {
            this.opsFilename = this.imageFilename + ".ops";
        }
        // Write image file based on file extension
        String extension = imageFilename.substring(1 + imageFilename.lastIndexOf(".")).toLowerCase();
        ImageIO.write(original, extension, new File(imageFilename));
        // Write operations file
        FileOutputStream fileOut = new FileOutputStream(this.opsFilename);
        ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
        objOut.writeObject(this.ops);
        objOut.close();
        fileOut.close();
    }

    /**
     * <p>
     * Save an image to a specified file.
     * </p>
     * 
     * <p>
     * Saves an image to the file provided as a parameter.
     * Also saves a set of operations from the file with <code>.ops</code> added.
     * So if you save to <code>some/path/to/image.png</code>, this method will also
     * save
     * the current operations to <code>some/path/to/image.png.ops</code>.
     * </p>
     * 
     * @param imageFilename The file location to save the image to.
     * @throws Exception If something goes wrong.
     */
    public void saveAs(String imageFilename) throws Exception {
        this.imageFilename = imageFilename;
        this.opsFilename = imageFilename + ".ops";
        save();
    }

    /**
     * Creates a copy of the current image without the change history and exports it
     * If an extension is specified, it saves with that extension
     * otherwise saves with the same extension as the original image
     * 
     * @param fileName
     * @throws Exception
     */
    public void export(String fileName) throws Exception {

        BufferedImage exportImage = deepCopy(this.current);
        ops.clear();
        redoOps.clear();

        String extension = "";

        if (fileName.contains(".")) { // if contains a ".", it indicates that it is an extension
            extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            extension = imageFilename.substring(imageFilename.lastIndexOf(".") + 1);
            fileName += "." + extension;
        }
        ImageIO.write(exportImage, extension, new File(fileName));
    }

    /**
     * <p>
     * Apply an {@link ImageOperation} to this image.
     * </p>
     * 
     * @param op The operation to apply.
     */
    public void apply(ImageOperation op) {
        current = op.apply(current);
        ops.add(op);
        if (isRecording) {
            macros.add(op);
        }
        ImageEdited = true;
    }

    /**
     * <p>
     * Undo the last {@link ImageOperation} applied to the image.
     * </p>
     */
    public void undo() {
        if (isRecording) {
            macros.pop();
        }
        redoOps.push(ops.pop());
        refresh();
    }

    /**
     * <p>
     * Reapply the most recently {@link undo}ne {@link ImageOperation} to the image.
     * </p>
     */
    public void redo() {
        if (isRecording) {
            macros.push(redoOps.pop());
        }
        apply(redoOps.pop());
    }

    /**
     * <p>
     * Get the current image after the operations have been applied.
     * </p>
     * 
     * @return The result of applying all of the current operations to the
     *         {@link original} image.
     */
    public BufferedImage getCurrentImage() {
        return current;
    }

    /**
     * <p>
     * Reapply the current list of operations to the original.
     * </p>
     * 
     * <p>
     * While the latest version of the image is stored in {@link current}, this
     * method makes a fresh copy of the original and applies the operations to it in
     * sequence.
     * This is useful when undoing changes to the image, or in any other case where
     * {@link current}
     * cannot be easily incrementally updated.
     * </p>
     */
    private void refresh() {
        current = deepCopy(original);
        for (ImageOperation op : ops) {
            current = op.apply(current);
        }
    }

    public static boolean GetimageEdited() {
        return ImageEdited;
    }

    public void openMacroFromFile(String file) {
        opsFilename = file;
        try {
            FileInputStream fileIn = new FileInputStream(opsFilename);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            @SuppressWarnings("unchecked")
            Stack<ImageOperation> macroOps = (Stack<ImageOperation>) objIn.readObject();
            Stack<ImageOperation> addToOps =  new Stack<ImageOperation>();
            while(!macroOps.isEmpty()){
                addToOps.add(macroOps.pop());
            }
            while(!addToOps.isEmpty()){
                apply(addToOps.pop());
            }
            redoOps.clear();
            objIn.close();
            fileIn.close();
        } catch (Exception ex) {
            if (original == null) {
                System.out.println("broken");
            }
        }

    }

}
