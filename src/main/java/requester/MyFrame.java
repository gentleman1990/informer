package requester;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MyFrame extends JFrame {

    int height = 150;
    int width = 300;

    public MyFrame() throws InterruptedException {
        super( "Nowe zlecenie" );
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int)screenSize.getWidth();
        int screenHeight = (int)screenSize.getHeight();
        setSize(width, height);
        setLocation(screenWidth-width,screenHeight-height-((int)(screenHeight*0.05)));


        JLabel label = new JLabel("Nowe zlecenie dla początkujących !");
        label.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        this.add(label);
        getContentPane().add(label);
        setVisible(true);

        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        timer.start();

    }

}