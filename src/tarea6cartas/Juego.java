/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tarea6cartas;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.*;
import javax.swing.JInternalFrame;
import static tarea6cartas.Cartas.conteo;


public class Juego extends javax.swing.JFrame {
  
    boolean estado = true;
   int minutos;
   int segundos;
  int milisegundos;
  String mision = null;
   String s;
   private String nickname;
 private Timer mTimer;
 private AudioClip sonido;

Cartas cartas = new Cartas();

    public Juego() {
        initComponents();
        
        this.getContentPane().setBackground(new Color(250,254,157));
        setIconImage(new ImageIcon("images/logo1.png").getImage());
        this.setExtendedState(this.MAXIMIZED_BOTH);
         mTimer = new Timer(10,(ActionEvent e)->{
            IniciarCronometro();
        });
      
        cartas.crearaleatorios();
        cartas.crearcartas();
        
        cartashilo hilop= new cartashilo();
        hilop.start();
       musica();
    }
  
    public Juego(String nickname) {
        initComponents();
        this.getContentPane().setBackground(new Color(250,254,157));
        setIconImage(new ImageIcon("images/logo1.png").getImage());
        this.setExtendedState(this.MAXIMIZED_BOTH);
         mTimer = new Timer(10,(ActionEvent e)->{
            IniciarCronometro();
        });
        cartas.crearaleatorios();
        cartas.crearcartas();
        
        cartashilo hilop= new cartashilo();
        hilop.start();
        this.nickname = nickname; // Guardar el nickname
       musica();
    }
   
  
  private void IniciarCronometro(){   
   ActualizarTiempo();
   ActualizarLabel();  
}
  public void musica(){
      sonido = java.applet.Applet.newAudioClip(getClass().getResource("/tarea6cartas/senbeï senbeï - robot racem.wav"));
        sonido.play();
  }

 
  private void ActualizarTiempo(){
 
      milisegundos++;
      if (milisegundos == 100) {
          milisegundos = 0;
          segundos++;
      }
      if (segundos == 60) {
          segundos = 0;
          minutos++;
      }
      if (minutos == 5  && segundos == 0 && milisegundos == 1) {
          
          lblgame.setText("!Game Over¡");
          mTimer.stop();
         
          Punteo pun = new Punteo (nickname,"5:0:0");
          pun.setTiempo(lbltemporizador.getText());
          pun.getconclusion("Fracaso");
          pun.intetos();
          dispose();
          pun.setLocationRelativeTo(null);
          pun.setVisible(true);
               
        
      }
      if (conteo >= 32) {
       
          lblgame.setText("! Ganastes ¡");
          mTimer.stop();
          sonido.stop();
          Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
         Punteo pun = new Punteo (nickname,lbltemporizador.getText());
          pun.setTiempo(lbltemporizador.getText());
          pun.getconclusion("Fracaso");
          pun.intetos();
          dispose();
          pun.setLocationRelativeTo(null);
          pun.setVisible(true);
                ((Timer) e.getSource()).stop(); // Detener el timer
            }
        });
       timer.start();
      }
      
  }
  private void ActualizarLabel(){
      String m = Integer.toString(minutos);
      String se = Integer.toString(segundos);
      String mi = Integer.toString(milisegundos);
       s =  m +" : "+ se +" : "+ mi;
      lbltemporizador.setText(s);
  }
  

 

 class cartashilo extends Thread
    {
             cartashilo(){}
             
             @Override
             public void run(){
                 
                 try{
                     for(int a=0; a<64; a++){
            add(cartas.voltearcartas(a));
        }
                     sleep(4000);
                    
                  mTimer.start();    
                     for(int a=0; a<64; a++){
                 add(cartas.voltearcartasdevuelta(a));                 
                     }
                     repaint();
                 }catch(InterruptedException ex){
                     System.out.println(""+ex);
                 }
                 
             }
        }
 

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbltemporizador = new javax.swing.JLabel();
        lblgame = new javax.swing.JLabel();
        btnsalir = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Juego de Memoria");
        setUndecorated(true);
        setResizable(false);

        lbltemporizador.setFont(new java.awt.Font("Tahoma", 1, 68)); // NOI18N
        lbltemporizador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbltemporizador.setText("0:0:0");
        lbltemporizador.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                lbltemporizadorAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        lblgame.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblgame.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblgame.setEnabled(false);

        btnsalir.setBackground(new java.awt.Color(204, 0, 0));
        btnsalir.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        btnsalir.setForeground(new java.awt.Color(255, 255, 255));
        btnsalir.setText("Salir");
        btnsalir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tarea6cartas/UMGp.png"))); // NOI18N
        jLabel8.setText("jLabel8");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("<html>Recuerda que puedes usar el comando<p><p>                  ATL+TAB         <p><p> para minimizar el juego <p><p>      ¡PERO TEN CUIDADO!<p>                  pues el tiempo seguirá corriendo<html>");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1195, 1195, 1195)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnsalir)
                .addContainerGap(602, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblgame, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(178, 178, 178))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(117, 117, 117))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lbltemporizador, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(193, 193, 193))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnsalir)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                .addComponent(lbltemporizador)
                .addGap(39, 39, 39)
                .addComponent(lblgame, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(135, 135, 135))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lbltemporizadorAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_lbltemporizadorAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_lbltemporizadorAncestorAdded

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
    System.exit(0);
    }//GEN-LAST:event_btnsalirActionPerformed

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
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Juego().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnsalir;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblgame;
    private javax.swing.JLabel lbltemporizador;
    // End of variables declaration//GEN-END:variables
}
