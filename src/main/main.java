
package main;

import javax.swing.JFrame;
import loadingbarra.FrmBienvenida;
import login.frmLogin;

public class main {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        FrmBienvenida splashScreen = new FrmBienvenida();
        frame.add(splashScreen);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);  // Centrar
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        splashScreen.setframe(frame);
    }
}
