package loadingbarra;

import java.applet.AudioClip;
import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import javax.sound.sampled.*;
import login.frmLogin;

public class FrmBienvenida extends JPanel {
    boolean pregresocompletado = false;
    public JFrame frame = new JFrame();
        private AudioClip sonido = java.applet.Applet.newAudioClip(getClass().getResource("/music/crash.wav"));
    public void setframe(JFrame _frame){
        this.frame = _frame;
    }

    private int progress = 0;
    private final Color backgroundColor = Color.decode("#18749c");  // Fondo
    private final Color loadingBarColor = Color.decode("#c82c2c");  // Barra
    private final Color detailColor = Color.decode("#b8842c");      // Detalles
    private final Image logo;

    private final Color[] textColors = {Color.WHITE, Color.YELLOW, Color.CYAN, Color.MAGENTA, Color.GREEN, Color.BLUE};
    private final String[] fonts = {"SansSerif", "Serif", "Monospaced", "Dialog", "DialogInput"};
    
    private int colorIndex = 0;  
    private int fontIndex = 0;  // Índice para el tipo de letra
    private Clip audioClip;  
    Timer timer = new Timer(80, e -> updateProgress()); 
    Timer textTimer = new Timer(300, e -> cycleTextStyle());

    public FrmBienvenida() {
        this.logo = new ImageIcon(getClass().getResource("/Logo.png")).getImage(); // Logo

        // Aquí cambias el tiempo de la barra
      
        
        timer.start();

        // Aquí cambias el tiempo de los colores y la fuente
        
        textTimer.start();
 
        // Reproducir música con ruta relativa
       sonido.play();
         
    }

    private void updateProgress() {
        progress += 1;  // Aumentar el progreso
        if (progress > 100) {
            progress = 100;  // Asegurarse de no superar el 100%
            pregresocompletado = true;
            stopMusic();     // Detener la música cuando el progreso llega a 100
            timer.stop();
            sonido.stop();
            this.mostrarlogin();
        }
        repaint();
       
    }
   

    private void cycleTextStyle() {
        // Cambiar el color del texto
        colorIndex = (colorIndex + 1) % textColors.length;

        // Cambiar la fuente del texto
        fontIndex = (fontIndex + 1) % fonts.length;

        repaint();
        
    }

    private void playMusic(String filepath) {
        try {
            // Usar InputStream para archivos dentro del JAR o recursos
            InputStream audioSrc = getClass().getResourceAsStream(filepath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioSrc);
            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);
            audioClip.start();  
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopMusic() {
        if (audioClip != null && audioClip.isRunning()) {
            audioClip.stop();  
        }
    }
    private void mostrarlogin(){
      frame.setVisible(false);
        this.setVisible(false);
        frmLogin frmlogin = new frmLogin();
        frmlogin.setLocationRelativeTo(null);
        frmlogin.setVisible(true);
    }
 

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Fondo
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());

        // LOGO CENTRO
        int logoWidth = 138;  // Ancho
        int logoHeight = 138;  // Alto
        int logoX = (getWidth() - logoWidth) / 2;
        int logoY = (getHeight() - logoHeight) / 2 - 20;
        g.drawImage(logo, logoX, logoY, logoWidth, logoHeight, this);

        // Texto título con colores y fuente que cambian
        g.setColor(textColors[colorIndex]);
        g.setFont(new Font(fonts[fontIndex], Font.BOLD, 24));  // Cambiar fuente dinámicamente
        g.drawString("Universidad Mariano Gálvez de Guatemala", getWidth() / 2 - 230, 50);
        g.drawString("Centro universitario zona 19", getWidth() / 2 - 160, 80);

        // Barra de carga - línea
        int barWidth = 300;
        int barHeight = 20;
        int barX = (getWidth() - barWidth) / 2;
        int barY = getHeight() - 100;
        g.setColor(detailColor);
        g.drawRect(barX, barY, barWidth, barHeight);

        // Barra de carga
        g.setColor(loadingBarColor);
        g.fillRect(barX + 1, barY + 1, (progress * (barWidth - 2)) / 100, barHeight - 2);

        // Cargando... - Texto
        g.setColor(detailColor);
        g.setFont(new Font("SansSerif", Font.PLAIN, 14));
        g.drawString("Cargando... " + progress + "%", barX + barWidth / 2 - 40, barY - 10);  
        
    }

    public void setLocationRelativeTo(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
