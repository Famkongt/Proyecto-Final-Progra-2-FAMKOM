/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package login;

import connectionDB.*;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import rsscalelabel.RSScaleLabel;
import record.*;
import tarea6cartas.Juego;
import creditosyayuda.*;

/**
 *
 * @author Wiliam
 */

public class frmMenu extends javax.swing.JFrame {

    private String nickname;
    /**
     * Creates new form frmMenu
     */
    public frmMenu() {
        initComponents();        
    }
    
    public frmMenu(String nickname){
        initComponents();
        connection();
        designframe();
        imagesdesign();
        this.nickname = nickname; // Guardar el nickname
        showdates(); // Llamar al método para cargar los datos
    }
    
    public void connection() {
        connectionDB connection = new connectionDB();
        java.sql.Connection conn = connection.Open();

        if (conn == null) {
            System.out.println("Error de Conexión");
        } else {
            System.out.println("Conexión Exitosa");
        }
    }
    
    public void designframe(){
        getContentPane().setBackground(new Color(217, 250, 251));
        //setIconImage(new ImageIcon(getClass().getResource("/images/logo.png")).getImage());
        setIconImage(new ImageIcon("images/logo1.png").getImage());
    }
    
    public void imagesdesign() {
        RSScaleLabel.setScaleLabel(jlblsignoff, "./images/cerrarsesion.png");
        RSScaleLabel.setScaleLabel(jlblcredits, "./images/creditos.png");
        RSScaleLabel.setScaleLabel(jlblhelp, "./images/ayuda.png");
        RSScaleLabel.setScaleLabel(jlbllogo, "./images/umg.png");
    }
    
    // Método showdates para cargar la información del usuario
    public void showdates() {
        try {
            connectionDB connection = new connectionDB();
            Connection cn = connection.Open();
            PreparedStatement pst = cn.prepareStatement("SELECT * FROM Dates WHERE nickname = ?");
            pst.setString(1, this.nickname); // Usar el nickname proporcionado
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Mostrar el nickname
                jlblnickname.setText(rs.getString("nickname"));

                // Leer el avatar como Blob
                Blob blob = rs.getBlob("avatar");

                if (blob != null && blob.length() > 0) {
                    byte[] data = blob.getBytes(1, (int) blob.length());

                    // Convertir el binario a una imagen
                    BufferedImage img = ImageIO.read(new ByteArrayInputStream(data));

                    // Escalar y mostrar la imagen en el JLabel
                    ImageIcon icono = new ImageIcon(img);
                    Icon imagen = new ImageIcon(icono.getImage().getScaledInstance(jlblavatar.getWidth(), jlblavatar.getHeight(), Image.SCALE_DEFAULT));
                    jlblavatar.setIcon(imagen);
                } else {
                    // Si el avatar está vacío o es null, mostrar el texto "AVATAR"
                    jlblavatar.setText("AVATAR");
                    jlblavatar.setIcon(null); // Limpiar el icono
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "¡Error al cargar los datos del usuario!");
            System.out.println("Error al cargar avatar: " + e);
        } catch (IOException e) {
            Logger.getLogger(frmMenu.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlblhelp = new javax.swing.JLabel();
        jlblsignoff = new javax.swing.JLabel();
        jlblcredits = new javax.swing.JLabel();
        jlblavatar = new javax.swing.JLabel();
        jlblMenu = new javax.swing.JLabel();
        jlblnickname = new javax.swing.JLabel();
        jbtnplay = new javax.swing.JButton();
        jbtnsettings = new javax.swing.JButton();
        jbtnrecord = new javax.swing.JButton();
        jlbllogo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menú");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(500, 500));
        setMinimumSize(new java.awt.Dimension(500, 500));
        setResizable(false);

        jlblhelp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblhelp.setText("Ayuda");
        jlblhelp.setToolTipText("Ayuda");
        jlblhelp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlblhelpMouseClicked(evt);
            }
        });

        jlblsignoff.setText("Cerrar Sesión");
        jlblsignoff.setToolTipText("Cerrar Sesión");
        jlblsignoff.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jlblsignoff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlblsignoffMouseClicked(evt);
            }
        });

        jlblcredits.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblcredits.setText("Creditos");
        jlblcredits.setToolTipText("Créditos");
        jlblcredits.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlblcreditsMouseClicked(evt);
            }
        });

        jlblavatar.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jlblavatar.setForeground(new java.awt.Color(0, 0, 153));
        jlblavatar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblavatar.setText("Avatar");
        jlblavatar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 102)));

        jlblMenu.setFont(new java.awt.Font("Isle Headline PERSONAL USE", 1, 48)); // NOI18N
        jlblMenu.setForeground(new java.awt.Color(0, 0, 102));
        jlblMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblMenu.setText("Menú");

        jlblnickname.setFont(new java.awt.Font("Lucida Calligraphy", 1, 24)); // NOI18N
        jlblnickname.setForeground(new java.awt.Color(0, 102, 153));
        jlblnickname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblnickname.setText("Nickname");

        jbtnplay.setBackground(new java.awt.Color(0, 51, 102));
        jbtnplay.setFont(new java.awt.Font("Maiandra GD", 1, 24)); // NOI18N
        jbtnplay.setForeground(new java.awt.Color(255, 255, 255));
        jbtnplay.setText("JUGAR");
        jbtnplay.setToolTipText("JUGAR");
        jbtnplay.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 102, 204), null, null));
        jbtnplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnplayActionPerformed(evt);
            }
        });

        jbtnsettings.setBackground(new java.awt.Color(0, 51, 102));
        jbtnsettings.setFont(new java.awt.Font("Maiandra GD", 1, 24)); // NOI18N
        jbtnsettings.setForeground(new java.awt.Color(255, 255, 255));
        jbtnsettings.setText("AJUSTES");
        jbtnsettings.setToolTipText("AJUSTES");
        jbtnsettings.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 102, 204), null, null));
        jbtnsettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnsettingsActionPerformed(evt);
            }
        });

        jbtnrecord.setBackground(new java.awt.Color(0, 51, 102));
        jbtnrecord.setFont(new java.awt.Font("Maiandra GD", 1, 24)); // NOI18N
        jbtnrecord.setForeground(new java.awt.Color(255, 255, 255));
        jbtnrecord.setText("HISTORIAL");
        jbtnrecord.setToolTipText("HISTORIAL");
        jbtnrecord.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 102, 204), null, null));
        jbtnrecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnrecordActionPerformed(evt);
            }
        });

        jlbllogo.setText("Logo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlblavatar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jlblnickname, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jlbllogo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jlblMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jlblhelp, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jbtnrecord, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnsettings, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtnplay, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblsignoff, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblcredits, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblsignoff, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jlbllogo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jlblMenu)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jlblnickname, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jbtnplay, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbtnrecord, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbtnsettings, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 76, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlblavatar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblhelp, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblcredits, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jlblsignoffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblsignoffMouseClicked
        // TODO add your handling code here:
        this.dispose();
        frmLogin login = new frmLogin();
        login.setLocationRelativeTo(null);
        login.setVisible(true);
    }//GEN-LAST:event_jlblsignoffMouseClicked

    private void jbtnsettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnsettingsActionPerformed
        // TODO add your handling code here:
        String nickname = jlblnickname.getText().trim(); // Obtener el nombre de usuario

        // Abrir el frmMenu y pasar el nickname                    
        frmSettings settings = new frmSettings(nickname);
        settings.setLocationRelativeTo(null);
        settings.setVisible(true);

        // Cerrar o esconder el frmMenu
        this.dispose();
    }//GEN-LAST:event_jbtnsettingsActionPerformed

    private void jbtnrecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnrecordActionPerformed
        // TODO add your handling code here:
        String nickname = jlblnickname.getText().trim(); // Obtener el nombre de usuario
        
        frmRecord frmrecord = new frmRecord(nickname);
        frmrecord.setLocationRelativeTo(null);
        frmrecord.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_jbtnrecordActionPerformed

    private void jbtnplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnplayActionPerformed
        String nickname = jlblnickname.getText().trim(); // Obtener el nombre de usuario
        
        Juego game = new Juego(nickname);
        game.setLocationRelativeTo(null);
        game.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jbtnplayActionPerformed

    private void jlblhelpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblhelpMouseClicked
        // TODO add your handling code here:
        String nickname = jlblnickname.getText().trim(); // Obtener el nombre de usuario
        
        Help help = new Help(nickname);
        help.setLocationRelativeTo(null);
        help.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_jlblhelpMouseClicked

    private void jlblcreditsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblcreditsMouseClicked
        // TODO add your handling code here:
        String nickname = jlblnickname.getText().trim(); // Obtener el nombre de usuario
        
        Creditos creditos = new Creditos(nickname);
        creditos.setLocationRelativeTo(null);
        creditos.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_jlblcreditsMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (ClassNotFoundException ex) {
        java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new frmMenu().setVisible(true);
        }
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtnplay;
    private javax.swing.JButton jbtnrecord;
    private javax.swing.JButton jbtnsettings;
    private javax.swing.JLabel jlblMenu;
    private javax.swing.JLabel jlblavatar;
    private javax.swing.JLabel jlblcredits;
    private javax.swing.JLabel jlblhelp;
    private javax.swing.JLabel jlbllogo;
    private javax.swing.JLabel jlblnickname;
    private javax.swing.JLabel jlblsignoff;
    // End of variables declaration//GEN-END:variables
}
