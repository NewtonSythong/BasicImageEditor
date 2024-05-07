// package cosc202.andie;

// import java.awt.event.ActionEvent;
// import java.util.Stack;
// import javax.swing.Action;
// import javax.swing.ImageIcon;
// import javax.swing.event.MenuKeyListener;
// import java.io.FileInputStream;
// import java.io.FileOutputStream;
// import java.io.ObjectOutputStream;

// public class MacroRecording /** extends ImageAction */
// {

//     protected static Stack<ImageOperation> operations = new Stack<ImageOperation>();
//     private String opsMacroName;
//     protected static boolean recording = false;

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

//     // @Override
//     // public void actionPerformed(ActionEvent arg0) {
//     // // TODO Auto-generated method stub
//     // throw new UnsupportedOperationException("Unimplemented method
//     // 'actionPerformed'");
//     // }
//     public void saveMacroOps() throws Exception {
//         ObjectOutputStream objOut = new ObjectOutputStream(opsMacroName);
//         objOut.writeObject(operations);
//         objOut.close();

//     }

// }
