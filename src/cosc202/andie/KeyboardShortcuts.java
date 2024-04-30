package cosc202.andie;

import java.awt.*;
import javax.swing.Action;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KeyboardShortcuts {

    KeyAdapter keyAdapter1;

    public KeyboardShortcuts() {

        ArrayList<Action> fileActions = new FileActions().actions;
        ArrayList<Action> editActions = new EditActions().actions;
        ArrayList<Action> imageActions = new ImageActions().actions;
        keyAdapter1 = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
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
                    case KeyEvent.VK_C : 
                    imageActions.get(0).actionPerformed(null);
                    break;
                    case KeyEvent.VK_Q : 
                    imageActions.get(1).actionPerformed(null);
                    break;
                    case KeyEvent.VK_H : 
                    imageActions.get(2).actionPerformed(null);
                    break;
                    case KeyEvent.VK_G : 
                    imageActions.get(3).actionPerformed(null);
                    break;
                    case KeyEvent.VK_B : 
                    imageActions.get(4).actionPerformed(null);
                    break;
                    case KeyEvent.VK_L : 
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
