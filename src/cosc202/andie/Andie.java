package cosc202.andie;

import java.awt.BorderLayout;
import java.awt.Image;
import java.util.Locale;
import java.util.prefs.Preferences;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JToolBar; 

/**
 * <p>
 * Main class for A Non-Destructive Image Editor (ANDIE).
 * </p>
 * 
 * <p>
 * This class is the entry point for the program.
 * It creates a Graphical User Interface (GUI) that provides access to various
 * image editing and processing operations.
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
public class Andie {
    public static JFrame frame;

    /**
     * <p>
     * Launches the main GUI for the ANDIE program.
     * </p>
     * 
     * <p>
     * This method sets up an interface consisting of an active image (an
     * {@code ImagePanel})
     * and various menus which can be used to trigger operations to load, save,
     * edit, etc.
     * These operations are implemented {@link ImageOperation}s and triggered via
     * {@code ImageAction}s grouped by their general purpose into menus.
     * </p>
     * 
     * @see ImagePanel
     * @see ImageAction
     * @see ImageOperation
     * @see FileActions
     * @see EditActions
     * @see ImageActions
     * @see FilterActions
     * @see ColourActions
     * 
     * @throws Exception if something goes wrong.
     */

    public static void createAndShowGUI() throws Exception {

        if (frame == null) {
            frame = new JFrame("ANDIE");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } else {
            frame.getContentPane().removeAll();
        }

        Image image = ImageIO.read(Andie.class.getClassLoader().getResource("icon.png"));
        frame.setIconImage(image);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // The main content area is an ImagePanel
        ImagePanel imagePanel = new ImagePanel();
        ImageAction.setTarget(imagePanel);
        JScrollPane scrollPane = new JScrollPane(imagePanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Add in menus for various types of action the user may perform.
        JMenuBar menuBar = new JMenuBar();
        

        // File menus are pretty standard, so things that usually go in File menus go
        // here.
        FileActions fileActions = new FileActions();
        menuBar.add(fileActions.createMenu());

        // Likewise Edit menus are very common, so should be clear what might go here.
        EditActions editActions = new EditActions();
        menuBar.add(editActions.createMenu());

        // View actions control how the image is displayed, but do not alter its actual
        // content
        ViewActions viewActions = new ViewActions();
        menuBar.add(viewActions.createMenu());

        // Filters apply a per-pixel operation to the image, generally based on a local
        // window
        FilterActions filterActions = new FilterActions();
        menuBar.add(filterActions.createMenu());

        // Actions that affect the representation of colour in the image
        ColourActions colourActions = new ColourActions();
        menuBar.add(colourActions.createMenu());

        ImageActions imageActions = new ImageActions();
        menuBar.add(imageActions.createMenu());

        // Drawing menu for Drawing and Region Cropping
        DrawActions drawActions = new DrawActions();
        menuBar.add(drawActions.createMenu());

        // Macro menu
        MacroRecordingActions macroMenu = new MacroRecordingActions();
        menuBar.add(macroMenu.createMenu());

        // Language menu
        LanguageActions LanguageActions = new LanguageActions();
        menuBar.add(LanguageActions.createMenu());

        //Actions for changing light/dark mode
        ThemeActions themeActions = new ThemeActions();
        menuBar.add(themeActions.createMenu());
        
        // Toolbar
        JToolBar toolBar = new JToolBar();
        Toolbar.setToolBar(toolBar);


        frame.setJMenuBar(menuBar);
        frame.add(toolBar, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
        frame.requestFocusInWindow();

    }

    public static JFrame getFrame() { // Add this method
        return frame;
    }



    /**
     * <p>
     * Main entry point to the ANDIE program.
     * </p>
     * 
     * <p>
     * Creates and launches the main GUI in a separate thread.
     * As a result, this is essentially a wrapper around {@code createAndShowGUI()}.
     * </p>
     * 
     * @param args Command line arguments, not currently used
     * @throws Exception If something goes awry
     * @see #createAndShowGUI()
     */
    public static void main(String[] args) throws Exception {
        Locale current = Locale.getDefault();
        Preferences prefs = Preferences.userNodeForPackage(Andie.class);
        if (!current.equals(Locale.ENGLISH) || !current.equals(Locale.KOREAN) || !current.equals(Locale.ITALIAN)) {
            Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
        }
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();

                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
        });

    }

}
