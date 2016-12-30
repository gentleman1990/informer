package utilities;

import gui.MyFrame;
import java.awt.*;

public class Common {

    public static void activatePopUp(){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new MyFrame();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
