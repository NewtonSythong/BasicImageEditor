package cosc202.andie;

import java.util.*;
import java.util.prefs.Preferences;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;

import javax.swing.event.*;
import javax.swing.*;
import java.awt.Toolkit;
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
 * @author Steven Mills
 * @version 1.0
 */
public class DrawActions{

    /**
     * A list of actions for the Tools menu.
     */
    protected ArrayList<Action> actions;
    private ResourceBundle bundle;
    /** 
     * Stores whether or not the user is currently drawing or selecting a region. This
     * is used to correctly colour the select region and colour buttons in thhe toolbar. 
     */
    public static boolean drawing = false;
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
    }

    /**
     * This method creates a menu containing the list of tool actions.
     * It can be used to add these actions to a Tools menu.
     */
    public JMenu createMenu() {
        JMenu toolsMenu = new JMenu(bundle.getString("Tools"));

        for (Action action : actions) {
            toolsMenu.add(new JMenuItem(action));
        }

        return toolsMenu;
    }

    /**
     * <p>
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
                JOptionPane.showMessageDialog(null, "No image detected for cropping");
                return;
            }
            // Check if there is a selected region.
            if (ImagePanel.selectionRect == null) {
                // Trying to crop when there is no region selected. Give the user an error.
                JOptionPane.showMessageDialog(null, "Please select a region to crop");
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
}







