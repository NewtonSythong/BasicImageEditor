// package cosc202.andie;

// import java.awt.event.ActionEvent;
// import java.util.ArrayList;
// import java.util.Stack;
// import javax.swing.Action;
// import javax.swing.ImageIcon;
// import javax.swing.JMenu;
// import javax.swing.JMenuItem;
// import javax.swing.event.MenuKeyListener;
// import java.io.FileInputStream;
// import java.io.FileOutputStream;
// import java.io.ObjectOutputStream;
// import java.util.Locale;
// import java.util.ResourceBundle;
// import java.util.prefs.Preferences;
// import javax.imageio.ImageIO;
// import javax.swing.AbstractAction;
// import javax.swing.Action;
// import javax.swing.ImageIcon;
// import javax.swing.JFileChooser;
// import javax.swing.JMenu;
// import java.awt.event.ActionEvent;
// import java.awt.event.KeyEvent;
// import javax.swing.JMenuItem;
// import javax.swing.JOptionPane;

// public class MacroRecordingActions {

//     protected static Stack<ImageOperation> operations = new Stack<ImageOperation>();
//     private String opsMacroName;
//     protected static boolean recording = false;
//     protected ArrayList<Action> actions;
//     private ResourceBundle bundle;

//     public MacroRecordingActions() {

//         Preferences prefs = Preferences.userNodeForPackage(Andie.class);
//         Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
//         this.bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle");
//         if (this.bundle == null) {
//             throw new RuntimeException("Resource bundle not found!");
//         }
//         actions = new ArrayList<Action>();
//         actions.add(
//                 new MacroRecordAction(bundle.getString("Open"), null, "Open a file", Integer.valueOf(KeyEvent.VK_O)));

//     }

//     public JMenu createMenu() {
//         JMenu macroMenu = new JMenu(bundle.getString("Macros"));

//         for (Action action : actions) {
//             macroMenu.add(new JMenuItem(action));
//         }

//         return macroMenu;
//     }

// }

// public class MacroRecordAction extends ImageAction {

//     protected static Stack<ImageOperation> operations = new Stack<ImageOperation>();
//     boolean isRecording = false;

//     MacroRecordAction(String name, ImageIcon icon, String desc, Integer mnemonic) {

//         super(name, icon, desc, mnemonic);
//     }

//     public void actionPerformed(ActionEvent e) {
//         isRecording = true;

//     }
// }
