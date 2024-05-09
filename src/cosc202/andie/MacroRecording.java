// package cosc202.andie;

// import java.awt.event.ActionEvent;
// import java.util.Locale;
// import java.util.ResourceBundle;
// import java.util.Stack;
// import java.util.prefs.Preferences;

// import javax.swing.Action;
// import javax.swing.ImageIcon;
// import javax.swing.event.MenuKeyListener;
// import java.io.FileInputStream;
// import java.io.FileOutputStream;
// import java.io.ObjectOutputStream;

// public class MacroRecording {
//     protected static Stack<ImageOperation> operations = new Stack<ImageOperation>();
//     private String opsMacroName;
//     protected static boolean recording = false;
//     private ResourceBundle bundle;

//     public MacroRecording(){

//                 Preferences prefs = Preferences.userNodeForPackage(Andie.class);
//         Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
//         this.bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle");
//         if (this.bundle == null) {
//             throw new RuntimeException("Resource bundle not found!");
//         }

//     }


//     // MacroRecording(String name, ImageIcon icon, String desc, Integer mnemonic) {
//     // super(name, icon, desc, mnemonic);
//     // //TODO Auto-generated constructor stub
//     // }

//     public void startRecording() {
//         recording = true;
//     }

//     public void endRecording() throws Exception {
//         recording = false;
//         saveMacroOps();
//     }


//     public void saveMacroOps() throws Exception {
//         FileOutputStream fileOut = new FileOutputStream(opsMacroName);
//         ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
//         objOut.writeObject(operations);
//         objOut.close();

//     }

// }
