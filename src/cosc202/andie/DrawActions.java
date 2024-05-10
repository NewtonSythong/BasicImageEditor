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
    public static Color drawColour = Color.PINK;

    /**
     * Store the width value of hollow shapes or lines
     */
    public static int drawWidth = 1;

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
            throw new RuntimeException ("Resource bundle not found, kefe kefe kefe");
        }
        actions = new ArrayList<Action>();
        actions.add(new RegionCropAction(bundle.getString("CropRegion"), null,"Crops selected region", null));
        actions.add(new DrawRectActions(bundle.getString("DrawRect"), null,"Tool to draw a Rectangle", null));
        actions.add(new DrawOvalActions(bundle.getString("DrawOval"), null,"Tool to draw a Oval", null));
        actions.add(new DrawLineActions(bundle.getString("DrawLine"), null, "Draw a line brother", null));
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
                JOptionPane.showMessageDialog(null, bundle.getString("NoImageSelected"));
                return;
            }
            // Check if there is a selected region.
            if (ImagePanel.selectionRect == null) {
                // Trying to crop when there is no region selected. Give the user an error.
                JOptionPane.showMessageDialog(null, bundle.getString("SelectRegionCrop"));
                return;
            }
            // There is an image open, and a selected region, so we try to crop it.
            target.getImage().apply(new RegionCrop(ImagePanel.scale, ImagePanel.selectionRect));
            ImagePanel.selectionRect = null;
            target.repaint();
            target.getParent().revalidate();
            target.repaint();
            ImagePanel.inX = 0;
            ImagePanel.inY = 0;
            target.getParent().revalidate();
        }
    }

    public class DrawRectActions extends ImageAction{
        
        DrawRectActions(String name, ImageIcon imageIcon, String desc, Integer mnemonic) {
            super(name, imageIcon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e){
            if(target.getImage().hasImage() == false){
                // There is not an image to draw on, so display error message.
                JOptionPane.showMessageDialog(null, bundle.getString("NoImageSelected"));
                return;
            }

            Rectangle recto = ImagePanel.getRect(); //Getting the Rectangle from ImagePanel (selection) 

            if(recto == null){
                JOptionPane.showMessageDialog(null, bundle.getString("SelectRegionShape"));
                return;
            }

            Color color = drawColour;
            boolean fill = false;

            DrawRect drawRect = new DrawRect(recto, color, fill);
            target.getImage().apply(drawRect);
            target.repaint();
            
        }
        
        
    }

    public class DrawOvalActions extends ImageAction {
        public DrawOvalActions(String name, ImageIcon imageIcon, String desc, Integer mnemonic) {
            super(name, imageIcon, desc, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent e){
            Rectangle rect = ImagePanel.getRect();
            if (rect == null || rect.width == 0 || rect.height == 0) {
                JOptionPane.showMessageDialog(null, bundle.getString("SelectRegionShape"));
                return;
            }
            Color color = drawColour;
            boolean fill = false;
            DrawOval drawOval = new DrawOval(rect, color, fill);
            target.getImage().apply(drawOval);
            target.repaint();
        }
    }

    public class DrawLineActions extends ImageAction {
        public DrawLineActions(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent e){
            Point startPoint = ImagePanel.getStartPoint();//new Point(ImagePanel.inX, ImagePanel.inY); // Method to get start point from ImagePanel
            Point endPoint = ImagePanel.getEndPoint(); // Method to get end point from ImagePanel
            if (startPoint == null || endPoint == null) {
                JOptionPane.showMessageDialog(null, bundle.getString("SelectLinePoints"));
                return;
            }
    
            Color color = drawColour;
            
            DrawLine drawLine = new DrawLine(startPoint, endPoint, color);
            target.getImage().apply(drawLine);
            target.repaint();

        }

    }
}