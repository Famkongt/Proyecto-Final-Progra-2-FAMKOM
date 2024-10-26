/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package login;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import rsscalelabel.RSScaleLabel;
import connectionDB.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;

/**
 *
 * @author Wiliam
 */
public class frmSettings extends javax.swing.JFrame {
    
    private boolean isPasswordVisible = false;
    private FileInputStream fis;
    private int longitudBytes;
    private String nickname;
    private Blob currentAvatarBlob; // Campo para almacenar la imagen actual

    /**
     * Creates new form frmSettings
     */
    public frmSettings() {
        initComponents();
    }
    
    public frmSettings(String nickname) {
        initComponents();
        connection();
        designframe();
        showdatetime();
        imagesdesign();
        this.nickname = nickname; // Guardar el nickname    
        showdates();
        showtextpassdesign();
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
        setIconImage(new ImageIcon("images/logo.png").getImage());
    }
    
    public void showdatetime() {
        // Mostrar Fecha y Hora en los jlabels
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
        String date = dateFormat.format(new Date());
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a", new Locale("es", "ES"));
        String time = timeFormat.format(new Date()).toLowerCase(); // Convierte "PM" a "pm"
        jlbldatetime.setText("Guatemala, " + date + ", " + time);
    }
    
    public void imagesdesign() {
        RSScaleLabel.setScaleLabel(jlblpass, "./images/mostrar.png");
        RSScaleLabel.setScaleLabel(jlblconfpass, "./images/mostrar.png");
        RSScaleLabel.setScaleLabel(jlbldesign, "./images/diseño.png");
        RSScaleLabel.setScaleLabel(jlbleditpass, "./images/editar.png");
        RSScaleLabel.setScaleLabel(jlbleditmail, "./images/editar.png");
        RSScaleLabel.setScaleLabel(jlbleditimage, "./images/editar.png");        
    }
    
    public void showtextpassdesign() {
        jtxfconfpass.setText("Confirme su Contraseña");
        jtxfconfpass.setEchoChar((char) 0);
        jtxfconfpass.setForeground(new Color(999999));  
        jtxfpass.requestFocusInWindow(); // Mover foco al campo de contraseña
    }
    
    // Método para validar si el email tiene un formato válido
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
    
    // Método para encriptar la contraseña usando la clave murciélago
    private String encryptPassword(String password) {
        String clave = "murcielago";
        String reemplazo = "0123456789";
        StringBuilder encriptada = new StringBuilder();

        for (char c : password.toLowerCase().toCharArray()) {
            int index = clave.indexOf(c);
            if (index != -1) {
                encriptada.append(reemplazo.charAt(index));
            } else {
                encriptada.append(c);  // Si el carácter no está en la clave, dejarlo como está
            }
        }

        return encriptada.toString();
    }
    
    private String decryptPassword(String encryptedPassword) {
        // Clave y reemplazo utilizados para encriptar la contraseña
        String clave = "murcielago";
        String reemplazo = "0123456789";

        // Crear un mapeo inverso para la desencriptación
        StringBuilder decrypted = new StringBuilder();

        for (char c : encryptedPassword.toCharArray()) {
            int index = reemplazo.indexOf(c);
            if (index != -1) {
                decrypted.append(clave.charAt(index)); // Revertir la sustitución
            } else {
                decrypted.append(c); // Si el carácter no está en el reemplazo, dejarlo como está
            }
        }

        return decrypted.toString();
    }

    public void showdates() {
        try {
            connectionDB connection = new connectionDB();
            Connection cn = connection.Open();
            PreparedStatement pst = cn.prepareStatement("SELECT * FROM Dates WHERE nickname = ?");
            pst.setString(1, this.nickname); // Usar el nickname proporcionado
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Mostrar el nickname
                jlbluser.setText(rs.getString("nickname"));

                // Mostrar el correo
                jtxfmail.setText(rs.getString("email"));
                
                // Mostrar la contraseña desencriptada
                String encryptedPass = rs.getString("password");
                String decryptedPass = decryptPassword(encryptedPass); // Método para desencriptar la contraseña
                jtxfpass.setText(decryptedPass);

                // Leer el avatar como Blob
                currentAvatarBlob = rs.getBlob("avatar");
                if (currentAvatarBlob != null) {
                    byte[] data = currentAvatarBlob.getBytes(1, (int) currentAvatarBlob.length());
                    BufferedImage img = ImageIO.read(new ByteArrayInputStream(data));
                    ImageIcon icono = new ImageIcon(img);
                    Icon imagen = new ImageIcon(icono.getImage().getScaledInstance(jlblimage.getWidth(), jlblimage.getHeight(), Image.SCALE_DEFAULT));
                    jlblimage.setIcon(imagen);
                } else {
                    jlblimage.setText("AVATAR");
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "¡Error al cargar los datos del usuario!");
            System.out.println("Error al cargar avatar: " + e);
        } catch (IOException e) {
            Logger.getLogger(frmMenu.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbldatetime = new javax.swing.JLabel();
        jlbldesign = new javax.swing.JLabel();
        jlblsignint = new javax.swing.JLabel();
        jlblpass = new javax.swing.JLabel();
        jtxfpass = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jlblconfpass = new javax.swing.JLabel();
        jtxfconfpass = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jtxfmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jbtnimage = new javax.swing.JButton();
        jbtnupdate = new javax.swing.JButton();
        jlblimage = new javax.swing.JLabel();
        jlbluser = new javax.swing.JLabel();
        jlbleditmail = new javax.swing.JLabel();
        jlbleditimage = new javax.swing.JLabel();
        jlbleditpass = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menú");
        setMaximumSize(new java.awt.Dimension(800, 500));
        setResizable(false);

        jlbldatetime.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jlbldatetime.setForeground(new java.awt.Color(255, 255, 255));
        jlbldatetime.setText("Fecha y Hora");

        jlbldesign.setText("Design");
        jlbldesign.setAlignmentY(0.0F);

        jlblsignint.setFont(new java.awt.Font("Isle Headline PERSONAL USE", 1, 36)); // NOI18N
        jlblsignint.setForeground(new java.awt.Color(0, 0, 102));
        jlblsignint.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblsignint.setText("Ajustes");

        jlblpass.setText("pass");
        jlblpass.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlblpass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlblpassMouseClicked(evt);
            }
        });

        jtxfpass.setBackground(new java.awt.Color(242, 255, 255));
        jtxfpass.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jtxfpass.setForeground(new java.awt.Color(0, 51, 153));
        jtxfpass.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 153));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Contraseña:");

        jLabel6.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 153));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Avatar:");

        jlblconfpass.setText("pass");
        jlblconfpass.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlblconfpass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlblconfpassMouseClicked(evt);
            }
        });

        jtxfconfpass.setBackground(new java.awt.Color(242, 255, 255));
        jtxfconfpass.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jtxfconfpass.setForeground(new java.awt.Color(0, 51, 153));
        jtxfconfpass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxfconfpassFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxfconfpassFocusLost(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 153));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Confirmar Contraseña:");

        jLabel8.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 153));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Correo Electronico:");

        jtxfmail.setBackground(new java.awt.Color(242, 255, 255));
        jtxfmail.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jtxfmail.setForeground(new java.awt.Color(0, 51, 153));
        jtxfmail.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 153));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Usuario:");

        jbtnimage.setBackground(new java.awt.Color(0, 0, 204));
        jbtnimage.setFont(new java.awt.Font("Gill Sans Ultra Bold", 1, 10)); // NOI18N
        jbtnimage.setForeground(new java.awt.Color(255, 255, 255));
        jbtnimage.setText("Subir Imagen");
        jbtnimage.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jbtnimage.setEnabled(false);
        jbtnimage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnimageActionPerformed(evt);
            }
        });

        jbtnupdate.setBackground(new java.awt.Color(0, 0, 204));
        jbtnupdate.setFont(new java.awt.Font("Gill Sans Ultra Bold", 1, 14)); // NOI18N
        jbtnupdate.setForeground(new java.awt.Color(255, 255, 255));
        jbtnupdate.setText("Actualizar");
        jbtnupdate.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jbtnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnupdateActionPerformed(evt);
            }
        });

        jlblimage.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jlblimage.setForeground(new java.awt.Color(0, 0, 153));
        jlblimage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblimage.setText("AVATAR");
        jlblimage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 102)));

        jlbluser.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jlbluser.setForeground(new java.awt.Color(0, 51, 153));
        jlbluser.setText("nickname");

        jlbleditmail.setText("edit");
        jlbleditmail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlbleditmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbleditmailMouseClicked(evt);
            }
        });

        jlbleditimage.setText("edit");
        jlbleditimage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlbleditimage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbleditimageMouseClicked(evt);
            }
        });

        jlbleditpass.setText("edit");
        jlbleditpass.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlbleditpass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbleditpassMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jtxfpass, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(330, 330, 330)
                .addComponent(jlblconfpass, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbleditpass, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(330, 330, 330)
                .addComponent(jlblpass, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbleditmail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jtxfmail, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jtxfconfpass, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addComponent(jbtnimage, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(170, 170, 170)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jlblsignint))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbluser, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jlbleditimage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlblimage, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(jbtnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jlbldatetime, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jlbldesign, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jlbldatetime, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jLabel9))
            .addComponent(jlbldesign, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jlblsignint))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jlblimage, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(50, 50, 50)
                                        .addComponent(jbtnimage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jlbleditimage, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(175, 175, 175)
                                .addComponent(jlblconfpass, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addComponent(jlblpass, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jlbluser, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jlbleditpass, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(130, 130, 130)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel8)
                                                    .addComponent(jlbleditmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(50, 50, 50)
                                                .addComponent(jLabel7))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(jtxfpass, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(170, 170, 170)
                                                .addComponent(jtxfmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(90, 90, 90)
                                                .addComponent(jtxfconfpass, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(jLabel5))))))
                .addGap(18, 18, 18)
                .addComponent(jbtnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jlblpassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblpassMouseClicked
        // TODO add your handling code here:
        // Alternar entre mostrar y ocultar la contraseña
        if (isPasswordVisible) {
            RSScaleLabel.setScaleLabel(jlblpass, "./images/ocultar.png");
            jtxfpass.setEchoChar('*'); // Ocultar el texto
        } else {
            RSScaleLabel.setScaleLabel(jlblpass, "./images/mostrar.png");
            jtxfpass.setEchoChar((char) 0); // Mostrar el texto
        }
        isPasswordVisible = !isPasswordVisible; // Cambiar el estado
    }//GEN-LAST:event_jlblpassMouseClicked

    private void jlblconfpassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblconfpassMouseClicked
        // TODO add your handling code here:
        // Alternar entre mostrar y ocultar la contraseña
        if (isPasswordVisible) {
            RSScaleLabel.setScaleLabel(jlblconfpass, "./images/ocultar.png");
            jtxfconfpass.setEchoChar('*'); // Ocultar el texto
        } else {
            RSScaleLabel.setScaleLabel(jlblconfpass, "./images/mostrar.png");
            jtxfconfpass.setEchoChar((char) 0); // Mostrar el texto
        }
        isPasswordVisible = !isPasswordVisible; // Cambiar el estado
    }//GEN-LAST:event_jlblconfpassMouseClicked

    private void jbtnimageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnimageActionPerformed
        // TODO add your handling code here:
        JFileChooser se = new JFileChooser();
        se.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int estado = se.showOpenDialog(null);
        if (estado == JFileChooser.APPROVE_OPTION) {
            try {

                fis = new FileInputStream(se.getSelectedFile());
                this.longitudBytes = (int) se.getSelectedFile().length();
                Image icono = ImageIO.read(se.getSelectedFile()).getScaledInstance(jlblimage.getWidth(), jlblimage.getHeight(), Image.SCALE_DEFAULT);
                jlblimage.setIcon(new ImageIcon(icono));
                jlblimage.updateUI();
                jlblimage.setText("");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Error en el primer catch");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error en el segundo catch");
            }
        }
    }//GEN-LAST:event_jbtnimageActionPerformed

    private void jbtnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnupdateActionPerformed
        // TODO add your handling code here:
        // Verificar si los campos están vacíos o contienen el texto por defecto
        if (new String(jtxfpass.getPassword()).equals("Ingrese su Contraseña") || new String(jtxfpass.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese su contraseña");
            jtxfpass.requestFocusInWindow(); // Mover foco al campo de contraseña
        } else if (new String(jtxfconfpass.getPassword()).equals("Confirme su Contraseña") || new String(jtxfconfpass.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "Confirme su contraseña");
            jtxfconfpass.requestFocusInWindow(); // Mover foco al campo de contraseña
        } else if (jtxfmail.getText().equals("Ingrese su Email") || jtxfmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese su Email");
            jtxfmail.requestFocusInWindow(); // Mover foco al campo de usuario
        } else if (!isValidEmail(jtxfmail.getText())) {
            JOptionPane.showMessageDialog(null, "Ingrese un correo electrónico válido");
            jtxfmail.requestFocusInWindow(); // Mover foco al campo de email
        } else if (!new String(jtxfconfpass.getPassword()).equals(new String(jtxfpass.getPassword()))) {
            JOptionPane.showMessageDialog(null, "Contraseñas No Coinciden");
            jtxfconfpass.requestFocusInWindow(); // Mover foco al campo de contraseña
        } else if (jtxfpass.getPassword().length < 8 || jtxfconfpass.getPassword().length < 8) {
            JOptionPane.showMessageDialog(null, "Longitud de Contraseña Invalida");
            jtxfpass.requestFocusInWindow(); // Mover foco al campo de contraseña
        } else if (new String(jtxfpass.getPassword()).matches(".*\\d.*") || new String(jtxfconfpass.getPassword()).matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(null, "La contraseña no debe contener números");
            jtxfpass.requestFocusInWindow(); // Mover foco al campo de contraseña
        } else {

            // Obtener los valores ingresados por el usuario
            String username = jlbluser.getText(); // Asumimos que tienes el nombre de usuario
            String password = new String(jtxfpass.getPassword());
            String email = jtxfmail.getText();

            // Encriptar la contraseña antes de guardarla
            String encryptedPassword = encryptPassword(password);

            // Obtener la fecha y hora actual
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String registrationDate = now.format(formatter);

            // Verificar si se ha seleccionado una imagen de avatar
            InputStream updatedAvatarStream = fis; // Utiliza el InputStream ya configurado para la imagen
            int updatedAvatarLength = longitudBytes; // Utiliza la longitud ya configurada para la imagen

            try {
                // Conectar a la base de datos
                connectionDB connection = new connectionDB();
                Connection cn = connection.Open();

                // Crear una consulta SQL para actualizar los datos
                String sql = "UPDATE Dates SET password = ?, email = ?, avatar = ?, registration_date = ? WHERE nickname = ?";
                PreparedStatement pst = cn.prepareStatement(sql);

                // Insertar los datos actualizados
                pst.setString(1, encryptedPassword);
                pst.setString(2, email);
                if (updatedAvatarStream != null && updatedAvatarLength > 0) {
                    pst.setBlob(3, updatedAvatarStream, updatedAvatarLength); // Actualizar la imagen si se proporciona una nueva
                } else {
                    // Mantener la imagen actual si no se proporciona una nueva
                    pst.setBlob(3, currentAvatarBlob);
                }
                pst.setString(4, registrationDate); // Actualizar la fecha de registro
                pst.setString(5, username); // Usuario para identificar el registro a actualizar

                // Ejecutar la consulta
                pst.executeUpdate();
                cn.close();

                JOptionPane.showMessageDialog(null, "Datos Actualizados con Éxito");
                
                // Enviar la contraseña encriptada por correo electrónico
                String subject = "Actualización de Datos - Validación de Perfil";
                String body = "Tus datos " + username + ",\n\n"
                        + "Han sido actualizados de manera exitosa. Aquí está tu codigo de validación:\n"
                        + encryptedPassword + "\n\n"
                        + "Por favor, verifica tu perfil, en el juego.";

                clsSendEmail.sendEmail(email, subject, body);
                
                // Abrir un diálogo con título personalizado para que el usuario ingrese el código de verificación
                String inputCode = JOptionPane.showInputDialog(null,
                        "Se ha enviado un código de verificación a tu correo.\nPor favor, ingresa el código de verificación:",
                        "Verificación de Actualización de Datos",
                        JOptionPane.QUESTION_MESSAGE);

                // Verificar si el código ingresado es correcto
                if (inputCode != null && inputCode.equals(encryptedPassword)) {
                    JOptionPane.showMessageDialog(null, "Verificación exitosa. ¡Bienvenido al Juego!");

                    // Mostrar y ocultar componentes de la interfaz
                    frmMenu menu = new frmMenu(nickname);
                    menu.setLocationRelativeTo(null);
                    menu.setVisible(true);

                    this.dispose();
                
                } else {
                    JOptionPane.showMessageDialog(null, "El código ingresado es incorrecto. Por favor, inténtelo de nuevo.");
                }
              
            } catch (SQLException e) {
                System.out.println("Error al actualizar datos: " + e);
                JOptionPane.showMessageDialog(null, "Error al actualizar datos en la base de datos");
            }
        }
    }//GEN-LAST:event_jbtnupdateActionPerformed

    private void jlbleditmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbleditmailMouseClicked
        // TODO add your handling code here:
        this.jtxfmail.setEnabled(true);
    }//GEN-LAST:event_jlbleditmailMouseClicked

    private void jlbleditimageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbleditimageMouseClicked
        // TODO add your handling code here:
        this.jbtnimage.setEnabled(true);
    }//GEN-LAST:event_jlbleditimageMouseClicked

    private void jlbleditpassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbleditpassMouseClicked
        // TODO add your handling code here:
        this.jtxfpass.setEnabled(true);
    }//GEN-LAST:event_jlbleditpassMouseClicked

    private void jtxfconfpassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxfconfpassFocusGained
        // TODO add your handling code here:
        if (new String(jtxfconfpass.getPassword()).equals("Confirme su Contraseña")) {
            jtxfconfpass.setText("");
            jtxfconfpass.setEchoChar('*'); // Asegurarse de que el campo vuelva a ocultar el texto
            jtxfconfpass.setForeground(new Color(0, 51, 153));
        }
    }//GEN-LAST:event_jtxfconfpassFocusGained

    private void jtxfconfpassFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxfconfpassFocusLost
        // TODO add your handling code here:
        if (new String(jtxfconfpass.getPassword()).isEmpty()) {
            jtxfconfpass.setText("Confirme su Contraseña");
            jtxfconfpass.setEchoChar((char) 0); // Desactiva el ocultamiento de texto para mostrar el mensaje
            jtxfconfpass.setForeground(new Color(999999)); // Color gris claro
        }
    }//GEN-LAST:event_jtxfconfpassFocusLost

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
            java.util.logging.Logger.getLogger(frmSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmSettings().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton jbtnimage;
    private javax.swing.JButton jbtnupdate;
    private javax.swing.JLabel jlblconfpass;
    private javax.swing.JLabel jlbldatetime;
    private javax.swing.JLabel jlbldesign;
    private javax.swing.JLabel jlbleditimage;
    private javax.swing.JLabel jlbleditmail;
    private javax.swing.JLabel jlbleditpass;
    private javax.swing.JLabel jlblimage;
    private javax.swing.JLabel jlblpass;
    private javax.swing.JLabel jlblsignint;
    private javax.swing.JLabel jlbluser;
    private javax.swing.JPasswordField jtxfconfpass;
    private javax.swing.JTextField jtxfmail;
    private javax.swing.JPasswordField jtxfpass;
    // End of variables declaration//GEN-END:variables
}
