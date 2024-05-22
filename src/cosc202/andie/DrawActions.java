package cosc202.andie;

import java.util.*;
import java.util.prefs.Preferences;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


/**
 * <p>
 * Actions provided by the Tools menu.
 * </p>
 *
 * <p>
 * This class represents a collection of tool actions.
 * It includes actions for cropping selected regions and other drawing functions.
 * </p>
 *
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 *
 * @author Tele Tamati
 * @version 1.0
 */
public class DrawActions{
    

    /**
     * A list of actions for the Tools menu.
     */
    protected ArrayList<Action> actions;
    private ResourceBundle bundle;

    /**
     * Storing color which will be used for drawing
     */
    public static Color drawColour = Color.BLACK;

    /**
     * Store the width value of hollow shapes or lines
     */
    public static int drawWidth = 5;

    /**
     * Constructor for the ToolsMenu class.
     * It initializes the list of actions and adds the selection tool action to it.
     */
    public DrawActions() {
        actions = new ArrayList<Action>();
        Preferences prefs = Preferences.userNodeForPackage(Andie.class);
        Locale.setDefault(new Locale(prefs.get("language","en"), prefs.get("country","NZ")));
        this.bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle");
        if (this.bundle == null){
            throw new RuntimeException ("Resource bundle not found");
        }
        actions = new ArrayList<Action>();
        actions.add(new RegionCropAction(bundle.getString("CropRegion"), null,"Crops selected region", null));
        actions.add(new SetWidth(bundle.getString("SetWidth"), null, "Set the width for Drawing", null));
        actions.add(new SetColour(bundle.getString("setColour"), null, "Set the colour for Drawing", null));
        actions.add(new DrawLineActions(bundle.getString("DrawLine"), null, "Draw a line brother", null));
        actions.add(new DrawRectActions(bundle.getString("DrawRect"), null,"Tool to draw a Rectangle", null));
        actions.add(new DrawOvalActions(bundle.getString("DrawOval"), null,"Tool to draw a Oval", null));
        actions.add(new DrawRectFilledActions(bundle.getString("DrawFilledRect"), null, "Draw a filled REctangle", null));
        actions.add(new DrawFilledOvalActions(bundle.getString("DrawFilledOval"), null, "Draw a filled Oval", null));
    }

    /**
     * This method creates a menu containing the list of tool actions.
     * It can be used to add these actions to a Tools menu.
     */
    public JMenu createMenu() {
        JMenu toolsMenu = new JMenu(bundle.getString("Draw"));

        for (Action action : actions) {
            toolsMenu.add(new JMenuItem(action));
        }

        return toolsMenu;
    }

    /**
     * Action to crop a selected region.
     * </p>
     * 
     * @see RegionCrop
     */
    public class RegionCropAction extends ImageAction {

        RegionCropAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
    }

        public void actionPerformed(ActionEvent e) {
            
            if (target.getImage().hasImage() == false) {
                // There is not an image crop, so display error message.
                JOptionPane.showMessageDialog(Andie.getFrame(), bundle.getString("NoImageSelected"));
                return;
            }
            // Check if there is a selected region.
            if (ImagePanel.selectionRect == null) {
                // Trying to crop when there is no region selected. Give the user an error.
                JOptionPane.showMessageDialog(Andie.getFrame(), bundle.getString("SelectRegionCrop"));
                return;
            }

            // There is an image open, and a selected region, so we try to crop it.
            target.getImage().apply(new RegionCrop(ImagePanel.scale, ImagePanel.selectionRect));
            ImagePanel.selectionRect = null;
            target.repaint();
            target.getParent().revalidate();
        }
    }
    
    /**
     * Action to draw a hollow Rectangle
     * </p>
     * 
     * @see DrawRect
     */
    public class DrawRectActions extends ImageAction{
        
        DrawRectActions(String name, ImageIcon imageIcon, String desc, Integer mnemonic) {
            super(name, imageIcon, desc, mnemonic);
        }
        public void actionPerformed(ActionEvent e){
            if(target.getImage().hasImage() == false){
                // There is not an image to draw on, so display error message.
                JOptionPane.showMessageDialog(Andie.getFrame(), bundle.getString("NoImageSelected"));
                return;
            }
            //Getting the Rectangle from ImagePanel (selection) 
            Rectangle recto = ImagePanel.getRect(); 
            if(recto == null){
                JOptionPane.showMessageDialog(Andie.getFrame(), bundle.getString("SelectRegionShape"));
                return;
            }
            boolean fill = false;
            DrawRect drawRect = new DrawRect(recto, drawColour, fill, drawWidth);
            target.getImage().apply(drawRect);
            target.repaint();
            
        }
        
        
    }

    /**
     * Action to draw filled Rectangle.
     * </p>
     * 
     * @see DrawRect
     */
    public class DrawRectFilledActions extends ImageAction{
        
        DrawRectFilledActions(String name, ImageIcon imageIcon, String desc, Integer mnemonic) {
            super(name, imageIcon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e){
            if(target.getImage().hasImage() == false){
                // There is not an image to draw on, so display error message.
                JOptionPane.showMessageDialog(Andie.getFrame(), bundle.getString("NoImageSelected"));
                return;
            }
            //Getting the Rectangle from ImagePanel (selection) 
            Rectangle recto = ImagePanel.getRect(); 
            if(recto == null){
                JOptionPane.showMessageDialog(Andie.getFrame(), bundle.getString("SelectRegionShape"));
                return;
            }
            boolean fill = true;
            DrawRect drawRect = new DrawRect(recto, drawColour, fill, drawWidth);
            target.getImage().apply(drawRect);
            target.repaint();
            }        
    }

    /**
     * Action to draw a Hollow Oval
     * </p>
     * 
     * @see DrawOval
     */
    public class DrawOvalActions extends ImageAction {
        public DrawOvalActions(String name, ImageIcon imageIcon, String desc, Integer mnemonic) {
            super(name, imageIcon, desc, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent e){
            Rectangle rect = ImagePanel.getRect();
            if (rect == null || rect.width == 0 || rect.height == 0) {
                JOptionPane.showMessageDialog(Andie.frame, bundle.getString("SelectRegionShape"));
                return;
            }
            boolean fill = false;
            DrawOval drawOval = new DrawOval(rect, drawColour, fill, drawWidth);
            target.getImage().apply(drawOval);
            target.repaint();
        }
    }


    /**
     * Action to draw a Filled Oval
     * </p>
     * 
     * @see DrawOval
     */
    public class DrawFilledOvalActions extends ImageAction {
        public DrawFilledOvalActions(String name, ImageIcon imageIcon, String desc, Integer mnemonic) {
            super(name, imageIcon, desc, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent e){
            Rectangle rect = ImagePanel.getRect();
            if (rect == null || rect.width == 0 || rect.height == 0) {
                JOptionPane.showMessageDialog(Andie.frame, bundle.getString("SelectRegionShape"));
                return;
            }
            boolean fill = true;
            DrawOval drawOval = new DrawOval(rect, drawColour, fill, drawWidth);
            target.getImage().apply(drawOval);
            target.repaint();
        }
    }

    /**
     * Action to draw a Line
     * </p>
     * 
     * @see DrawLine
     */
    public class DrawLineActions extends ImageAction {
        public DrawLineActions(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent e){
            Point startPoint = ImagePanel.getStartPoint();//new Point(ImagePanel.inX, ImagePanel.inY); // Method to get start point from ImagePanel
            Point endPoint = ImagePanel.getEndPoint(); // Method to get end point from ImagePanel
            if (startPoint == null || endPoint == null) {
                JOptionPane.showMessageDialog(Andie.getFrame(), bundle.getString("SelectLinePoints"));
                return;
            }            
            DrawLine drawLine = new DrawLine(startPoint, endPoint, drawColour, drawWidth);
            target.getImage().apply(drawLine);
            target.repaint();

        }

    }

    /**
     * Action to set the width for drawing hollow shapes;
     * It direclty alter the predefined Data Field drawWidth using 
     *  user friendly GUI being the JSlider
     * </p>
     * 
     */
    public class SetWidth extends ImageAction{
        public SetWidth(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent e){
            JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 20, drawWidth);
            slider.setMajorTickSpacing(5);
            slider.setMinorTickSpacing(1);
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);

            int result = JOptionPane.showConfirmDialog(Andie.frame, slider, bundle.getString("SelectLineWidth"),
                                                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                drawWidth = slider.getValue();  // Update the drawWidth used by all drawing actions
            }
        }
        
    }

    /**
     * Action to set the color for the drawings
     * and directly alters the Data Field drawColor;
     * currently using a Color Chooser to implement
     * </p>
     * 
     */
    public class SetColour extends ImageAction{
        public SetColour(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent e){
            Color initialColor = drawColour;  // Start with the current drawing color
            Color chosenColor = JColorChooser.showDialog(Andie.getFrame(), bundle.getString("SelectLineColor"), initialColor);
            if (chosenColor != null) {
                drawColour = chosenColor;  // Update the drawColour used by all drawing actions
            }
        }
    }
}