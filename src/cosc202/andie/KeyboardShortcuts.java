package cosc202.andie;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Action;

/**
 * This class represents the keyboard shortcuts for an application.
 * It manages a list of actions that can be triggered by keyboard events.
 * 
 * @author Cam Clark
 */
public class KeyboardShortcuts {

    /**
     * The KeyAdapter object that handles keyboard events.
     */
    KeyAdapter keyAdapter1;

    /**
     * Constructs a new KeyboardShortcuts object.
     * It initializes the KeyAdapter and sets up the keyboard shortcuts.
     */
    public KeyboardShortcuts() {

        ArrayList<Action> fileActions = new FileActions().actions;
        ArrayList<Action> editActions = new EditActions().actions;
        ArrayList<Action> imageActions = new ImageActions().actions;
        keyAdapter1 = new KeyAdapter() {
            /**
             * Handles the keyPressed event.
             * It triggers the corresponding action based on the key pressed.
             * 
             * @param e the keyboard event.
             */
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                int modifier = e.getModifiersEx();
                System.out.println(modifier);

                boolean cntrlPressed = (modifier & KeyEvent.CTRL_DOWN_MASK) != 0;
                
                
                switch (key) {
                    // File actions shortcuts
                    case KeyEvent.VK_O:
                        fileActions.get(0).actionPerformed(null);
                        break;
                    case KeyEvent.VK_S:
                        fileActions.get(1).actionPerformed(null);
                        break;
                    case KeyEvent.VK_A:
                        fileActions.get(2).actionPerformed(null);
                        break;
                    case KeyEvent.VK_E:
                        fileActions.get(3).actionPerformed(null);
                        break;
                    case KeyEvent.VK_0:
                        fileActions.get(4).actionPerformed(null);
                        break;
                    // edit actions shortcuts
                    case KeyEvent.VK_Z:
                        editActions.get(0).actionPerformed(null);
                        break;
                    case KeyEvent.VK_Y:
                        editActions.get(1).actionPerformed(null);
                        break;
                    // image actions shortcuts
                    case KeyEvent.VK_C:
                        imageActions.get(0).actionPerformed(null);
                        break;
                    case KeyEvent.VK_Q:
                        imageActions.get(1).actionPerformed(null);
                        break;
                    case KeyEvent.VK_H:
                        imageActions.get(2).actionPerformed(null);
                        break;
                    case KeyEvent.VK_G:
                        imageActions.get(3).actionPerformed(null);
                        break;
                    case KeyEvent.VK_B:
                        imageActions.get(4).actionPerformed(null);
                        break;
                    case KeyEvent.VK_L:
                        imageActions.get(5).actionPerformed(null);
                        break;
                }
            }
            
        };
    }

    public KeyAdapter getKeyAdapter() {
        return keyAdapter1;
    }

}
