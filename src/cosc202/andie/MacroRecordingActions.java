package cosc202.andie;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.event.MenuKeyListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MacroRecordingActions {

    protected ArrayList<Action> actions;
    ResourceBundle bundle;

    public MacroRecordingActions() {

        Preferences prefs = Preferences.userNodeForPackage(Andie.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
        this.bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle");
        if (this.bundle == null) {
            throw new RuntimeException("Resource bundle not found!");
        }
        actions = new ArrayList<Action>();
        actions.add(
                new MacroRecordAction(bundle.getString("Record"), null, "Start a macro recording",
                        Integer.valueOf(KeyEvent.VK_Z)));
        actions.add(
                new MacroStopAction(bundle.getString("Stop"), null, "stop a macro recording",
                        Integer.valueOf(KeyEvent.VK_U)));
        actions.add(
                new MacroOpenAction(bundle.getString("Open"), null, "Open a macro recording onto an image",
                        Integer.valueOf(KeyEvent.VK_P)));
    }

    public JMenu createMenu() {
        JMenu macroMenu = new JMenu(bundle.getString("Macros"));

        for (Action action : actions) {
            macroMenu.add(new JMenuItem(action));
        }

        return macroMenu;
    }

    public class MacroRecordAction extends ImageAction {

        MacroRecordAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            try {
                target.getImage().isRecording = true;
            } catch (Exception ex) {
                if (!target.getImage().hasImage()) {
                    JOptionPane.showMessageDialog(null, bundle.getString("NoImageSelected"),
                            "No Image Open",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    public class MacroStopAction extends ImageAction {

        MacroStopAction(String name, ImageIcon icon, String desc, Integer mnemonic) {

            super(name, icon, desc, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
            target.getImage().isRecording = false;
            JFileChooser fileChooser = new JFileChooser();
            int decision = fileChooser.showSaveDialog(target);
            if (decision == JFileChooser.APPROVE_OPTION) {
                try {
                    // String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    String macroName = target.getImage().getImageFilename() + "macro.ops";
                    FileOutputStream fileOut = new FileOutputStream(macroName);
                    System.out.println(1);
                    ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
                    objOut.writeObject(target.getImage().macros);
                    objOut.close();
                    fileOut.close();
                } catch (Exception e) {
                    System.out.println("its broken");
                }
            }

        }

    }

    public class MacroOpenAction extends ImageAction {
        MacroOpenAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            if(target.getImage() == null){
                System.out.println("failure");
            }
            
            JFileChooser fileChooser = new JFileChooser ();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("ops", "ops");
            fileChooser.setFileFilter(filter);
            int result = fileChooser.showOpenDialog(null);
            if(result == JFileChooser.APPROVE_OPTION){
                try{
                    String opsFileToAdd = fileChooser.getSelectedFile().getCanonicalPath();
                    System.out.println(opsFileToAdd);
                    target.getImage().openMacroFromFile(opsFileToAdd);
                    System.out.println(23421);
                 } catch(Exception ex){
                  JOptionPane.showMessageDialog(null, "failure");
                }
            

        }

    
}
    }
}