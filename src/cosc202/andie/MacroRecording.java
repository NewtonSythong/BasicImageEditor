package cosc202.andie;

import java.awt.event.ActionEvent;
import java.util.Stack;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.event.MenuKeyListener;
import java.io.*;

public class MacroRecording /**extends ImageAction*/ {

    protected Stack<ImageOperation> operations = new Stack<ImageOperation>();
    private String opsMacroName;
    protected static  boolean recording = false;

    // MacroRecording(String name, ImageIcon icon, String desc, Integer mnemonic) {
    //     super(name, icon, desc, mnemonic);
    //     //TODO Auto-generated constructor stub
    // }



    public void startRecording(){
        recording =  true;
    }

    public void endRecording(){
        recording =  false;
        saveMacroOps();
    }

    // @Override
    // public void actionPerformed(ActionEvent arg0) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    // }
    public void saveMacroOps() throws Exception{

    }
    

}
