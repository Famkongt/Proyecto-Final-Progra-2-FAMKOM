/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package login;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;
import rsscalelabel.RSScaleLabel;
import connectionDB.*;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Wiliam
 */
public class frmLogin extends javax.swing.JFrame {

    private boolean isPasswordVisible = false;
    private FileInputStream fis;
    private int longitudBytes;

    /**
     * Creates new form frmLogin
     */
    public frmLogin() {
        initComponents();
        connection();
        designframe();
        imagesdesign1();
        showtextpassdesign1();
        showdatetime();
        designlabels();
        hidedesign2();
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

    public void designlabels() {
        // Crear un borde inferior para simular un subrayado más grueso
        MatteBorder border1 = BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 204, 255)); // El número '2' es el grosor de la línea
        jlblsignin.setBorder(border1);
        MatteBorder border2 = BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(102, 102, 102)); // El número '2' es el grosor de la línea
        jlblnewpass.setBorder(border2);
    }

    public void showdatetime() {
        // Mostrar Fecha y Hora en los jlabels
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
        String date = dateFormat.format(new Date());
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a", new Locale("es", "ES"));
        String time = timeFormat.format(new Date()).toLowerCase(); // Convierte "PM" a "pm"
        jlbldatetime1.setText("Guatemala, " + date + ", " + time);
        jlbldatetime2.setText("Guatemala, " + date + ", " + time);
    }

    public void imagesdesign1() {
        RSScaleLabel.setScaleLabel(jlblpass, "./images/mostrar.png");
        RSScaleLabel.setScaleLabel(jlbldesign1, "./images/diseño.png");
        RSScaleLabel.setScaleLabel(jlbllogo, "./images/umg.png");
    }

    public void imagesdesign2() {
        RSScaleLabel.setScaleLabel(jlblpass1, "./images/mostrar.png");
        RSScaleLabel.setScaleLabel(jlblconfpass, "./images/mostrar.png");
        RSScaleLabel.setScaleLabel(jlbldesign2, "./images/diseño.png");
    }

    public void hidedesign2() {
        this.jlbldesign2.setVisible(false);
        this.jlblsignint.setVisible(false);
        this.jlblpass1.setVisible(false);
        this.jtxfpass1.setVisible(false);
        this.jLabel5.setVisible(false);
        this.jtxfuser1.setVisible(false);
        this.jLabel6.setVisible(false);
        this.jlblconfpass.setVisible(false);
        this.jtxfconfpass.setVisible(false);
        this.jLabel7.setVisible(false);
        this.jLabel8.setVisible(false);
        this.jtxfmail.setVisible(false);
        this.jLabel9.setVisible(false);
        this.jlblimage.setVisible(false);
        this.jbtnimage.setVisible(false);
        this.jbtnsignin.setVisible(false);
        this.jlbldatetime1.setVisible(false);
        this.jbtnreturn.setVisible(false);
    }

    public void hidedesign1() {
        this.jlbldesign1.setVisible(false);
        this.jlbllogint.setVisible(false);
        this.jtxfpass.setVisible(false);
        this.jLabel3.setVisible(false);
        this.jtxfuser.setVisible(false);
        this.jLabel4.setVisible(false);
        this.jlblsignin.setVisible(false);
        this.jlblnewpass.setVisible(false);
        this.jbtnlogin.setVisible(false);
        this.jlblpass.setVisible(false);
        this.jLabel10.setVisible(false);
        this.jtxfmail1.setVisible(false);
        this.jlbldatetime2.setVisible(false);
        this.jlbllogo.setVisible(false);
    }

    public void showdesign2() {
        this.jlbldesign2.setVisible(true);
        this.jlblsignint.setVisible(true);
        this.jlblpass1.setVisible(true);
        this.jtxfpass1.setVisible(true);
        this.jLabel5.setVisible(true);
        this.jtxfuser1.setVisible(true);
        this.jLabel6.setVisible(true);
        this.jlblconfpass.setVisible(true);
        this.jtxfconfpass.setVisible(true);
        this.jLabel7.setVisible(true);
        this.jLabel8.setVisible(true);
        this.jtxfmail.setVisible(true);
        this.jLabel9.setVisible(true);
        this.jlblimage.setVisible(true);
        this.jbtnimage.setVisible(true);
        this.jbtnsignin.setVisible(true);
        this.jlbldatetime1.setVisible(true);
        this.jbtnreturn.setVisible(true);
    }

    public void showdesign1() {
        this.jlbldesign1.setVisible(true);
        this.jlbllogint.setVisible(true);
        this.jtxfpass.setVisible(true);
        this.jLabel3.setVisible(true);
        this.jtxfuser.setVisible(true);
        this.jLabel4.setVisible(true);
        this.jlblsignin.setVisible(true);
        this.jlblnewpass.setVisible(true);
        this.jbtnlogin.setVisible(true);
        this.jlblpass.setVisible(true);
        this.jLabel10.setVisible(true);
        this.jtxfmail1.setVisible(true);
        this.jlbldatetime2.setVisible(true);
        this.jlbllogo.setVisible(true);
    }

    public void showtextpassdesign1() {
        jtxfpass.setText("Ingrese su Contraseña");
        jtxfpass.setEchoChar((char) 0);
        jtxfpass.setForeground(new Color(999999));
        jtxfmail1.setText("Ingrese su Email");
        jtxfmail1.setForeground(new Color(999999));
    }

    public void showtextpassdesign2() {
        jtxfuser1.setText("");
        jtxfuser1.requestFocusInWindow(); // Mover foco al campo de usuario
        jtxfpass1.setText("Ingrese su Contraseña");
        jtxfpass1.setEchoChar((char) 0);
        jtxfpass1.setForeground(new Color(999999));
        jtxfconfpass.setText("Confirme su Contraseña");
        jtxfconfpass.setEchoChar((char) 0);
        jtxfconfpass.setForeground(new Color(999999));
        jtxfmail.setText("Ingrese su Email");
        jtxfmail.setForeground(new Color(999999));
        // Restablecer el JLabel a su estado original con la palabra "AVATAR"
        jlblimage.setIcon(null);  // Elimina la imagen del JLabel
        jlblimage.setText("AVATAR");  // Vuelve a mostrar la palabra "AVATAR"
        // También restablece el FileInputStream a null para indicar que no hay imagen seleccionada
        fis = null;
        longitudBytes = 0;
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbldatetime1 = new javax.swing.JLabel();
        jlbldatetime2 = new javax.swing.JLabel();
        jlbldesign1 = new javax.swing.JLabel();
        jlbldesign2 = new javax.swing.JLabel();
        jlblsignint = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jlblpass = new javax.swing.JLabel();
        jtxfuser = new javax.swing.JTextField();
        jbtnlogin = new javax.swing.JButton();
        jlblsignin = new javax.swing.JLabel();
        jlblnewpass = new javax.swing.JLabel();
        jtxfpass = new javax.swing.JPasswordField();
        jlbllogint = new javax.swing.JLabel();
        jlblpass1 = new javax.swing.JLabel();
        jtxfpass1 = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jtxfuser1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jlblconfpass = new javax.swing.JLabel();
        jtxfconfpass = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jtxfmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jlblimage = new javax.swing.JLabel();
        jbtnimage = new javax.swing.JButton();
        jbtnsignin = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jtxfmail1 = new javax.swing.JTextField();
        jbtnreturn = new javax.swing.JButton();
        jlbllogo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menú");
        setMaximumSize(new java.awt.Dimension(800, 500));
        setMinimumSize(new java.awt.Dimension(800, 500));
        setName("Menú"); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 500));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlbldatetime1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jlbldatetime1.setForeground(new java.awt.Color(255, 255, 255));
        jlbldatetime1.setText("Fecha y Hora");
        getContentPane().add(jlbldatetime1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 7, 370, 20));

        jlbldatetime2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jlbldatetime2.setForeground(new java.awt.Color(255, 255, 255));
        jlbldatetime2.setText("Fecha y Hora");
        getContentPane().add(jlbldatetime2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, 370, 20));

        jlbldesign1.setText("Design1");
        jlbldesign1.setAlignmentY(0.0F);
        getContentPane().add(jlbldesign1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 470));

        jlbldesign2.setText("Design2");
        jlbldesign2.setAlignmentY(0.0F);
        getContentPane().add(jlbldesign2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 400, 470));

        jlblsignint.setFont(new java.awt.Font("Isle Headline PERSONAL USE", 1, 36)); // NOI18N
        jlblsignint.setForeground(new java.awt.Color(0, 0, 102));
        jlblsignint.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblsignint.setText("Sign In");
        getContentPane().add(jlblsignint, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 130, -1));

        jLabel3.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 153));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Contraseña:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 180, 110, -1));

        jLabel4.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 153));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Usuario:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, 80, -1));

        jlblpass.setText("pass");
        jlblpass.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jlblpass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlblpassMouseClicked(evt);
            }
        });
        getContentPane().add(jlblpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 220, 40, 30));

        jtxfuser.setBackground(new java.awt.Color(242, 255, 255));
        jtxfuser.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jtxfuser.setForeground(new java.awt.Color(0, 51, 153));
        jtxfuser.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxfuserFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxfuserFocusLost(evt);
            }
        });
        getContentPane().add(jtxfuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 140, 350, 30));

        jbtnlogin.setBackground(new java.awt.Color(0, 0, 204));
        jbtnlogin.setFont(new java.awt.Font("Gill Sans Ultra Bold", 1, 14)); // NOI18N
        jbtnlogin.setForeground(new java.awt.Color(255, 255, 255));
        jbtnlogin.setText("Ingresar");
        jbtnlogin.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jbtnlogin.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jbtnlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnloginActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnlogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 350, 130, 40));

        jlblsignin.setFont(new java.awt.Font("Rockwell", 2, 14)); // NOI18N
        jlblsignin.setForeground(new java.awt.Color(0, 204, 255));
        jlblsignin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblsignin.setText("Crea un Nuevo Usuario");
        jlblsignin.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jlblsignin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlblsigninMouseClicked(evt);
            }
        });
        getContentPane().add(jlblsignin, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 400, 150, 30));

        jlblnewpass.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jlblnewpass.setForeground(new java.awt.Color(102, 102, 102));
        jlblnewpass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblnewpass.setText("Olvide mi Contraseña");
        jlblnewpass.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jlblnewpass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlblnewpassMouseClicked(evt);
            }
        });
        getContentPane().add(jlblnewpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 440, 100, 20));

        jtxfpass.setBackground(new java.awt.Color(242, 255, 255));
        jtxfpass.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jtxfpass.setForeground(new java.awt.Color(0, 51, 153));
        jtxfpass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxfpassFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxfpassFocusLost(evt);
            }
        });
        getContentPane().add(jtxfpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 220, 310, 30));

        jlbllogint.setFont(new java.awt.Font("Isle Headline PERSONAL USE", 1, 48)); // NOI18N
        jlbllogint.setForeground(new java.awt.Color(0, 0, 102));
        jlbllogint.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbllogint.setText("Login");
        getContentPane().add(jlbllogint, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 170, 80));

        jlblpass1.setText("pass");
        jlblpass1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jlblpass1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlblpass1MouseClicked(evt);
            }
        });
        getContentPane().add(jlblpass1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 210, 40, 30));

        jtxfpass1.setBackground(new java.awt.Color(242, 255, 255));
        jtxfpass1.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jtxfpass1.setForeground(new java.awt.Color(0, 51, 153));
        jtxfpass1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxfpass1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxfpass1FocusLost(evt);
            }
        });
        getContentPane().add(jtxfpass1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 310, 30));

        jLabel5.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 153));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Contraseña:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 110, -1));

        jtxfuser1.setBackground(new java.awt.Color(242, 255, 255));
        jtxfuser1.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jtxfuser1.setForeground(new java.awt.Color(0, 51, 153));
        jtxfuser1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxfuser1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxfuser1FocusLost(evt);
            }
        });
        getContentPane().add(jtxfuser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 350, 30));

        jLabel6.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 153));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Avatar:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 80, -1));

        jlblconfpass.setText("pass");
        jlblconfpass.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jlblconfpass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlblconfpassMouseClicked(evt);
            }
        });
        getContentPane().add(jlblconfpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 290, 40, 30));

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
        getContentPane().add(jtxfconfpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 310, 30));

        jLabel7.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 153));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Confirmar Contraseña:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 220, -1));

        jLabel8.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 153));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Correo Electronico:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 180, -1));

        jtxfmail.setBackground(new java.awt.Color(242, 255, 255));
        jtxfmail.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jtxfmail.setForeground(new java.awt.Color(0, 51, 153));
        jtxfmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxfmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxfmailFocusLost(evt);
            }
        });
        getContentPane().add(jtxfmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 350, 30));

        jLabel9.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 153));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Usuario:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 80, -1));

        jlblimage.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlblimage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblimage.setText("AVATAR");
        jlblimage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)));
        getContentPane().add(jlblimage, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 100, 100));

        jbtnimage.setBackground(new java.awt.Color(0, 0, 204));
        jbtnimage.setFont(new java.awt.Font("Gill Sans Ultra Bold", 1, 10)); // NOI18N
        jbtnimage.setForeground(new java.awt.Color(255, 255, 255));
        jbtnimage.setText("Subir Imagen");
        jbtnimage.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jbtnimage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnimageActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnimage, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 100, 40));

        jbtnsignin.setBackground(new java.awt.Color(0, 0, 204));
        jbtnsignin.setFont(new java.awt.Font("Gill Sans Ultra Bold", 1, 14)); // NOI18N
        jbtnsignin.setForeground(new java.awt.Color(255, 255, 255));
        jbtnsignin.setText("Registrarse");
        jbtnsignin.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jbtnsignin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnsigninActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnsignin, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 410, 130, 40));

        jLabel10.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 153));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Correo Electronico:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 260, 180, -1));

        jtxfmail1.setBackground(new java.awt.Color(242, 255, 255));
        jtxfmail1.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jtxfmail1.setForeground(new java.awt.Color(0, 51, 153));
        jtxfmail1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxfmail1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxfmail1FocusLost(evt);
            }
        });
        getContentPane().add(jtxfmail1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 300, 350, 30));

        jbtnreturn.setBackground(new java.awt.Color(0, 102, 255));
        jbtnreturn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jbtnreturn.setForeground(new java.awt.Color(255, 255, 255));
        jbtnreturn.setText("REGRESAR");
        jbtnreturn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnreturnMouseClicked(evt);
            }
        });
        getContentPane().add(jbtnreturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 100, 20));

        jlbllogo.setText("Logo");
        getContentPane().add(jlbllogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, 80, 70));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxfuserFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxfuserFocusGained
        // TODO add your handling code here:
        if (jtxfuser.getText().equals("Ingrese su Usuario")) {
            jtxfuser.setText("");
            jtxfuser.setForeground(new Color(0, 51, 153));
        }
    }//GEN-LAST:event_jtxfuserFocusGained

    private void jtxfuserFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxfuserFocusLost
        // TODO add your handling code here:            
        if (jtxfuser.getText().isEmpty()) {
            jtxfuser.setText("Ingrese su Usuario");
            jtxfuser.setForeground(new Color(999999));
        }
    }//GEN-LAST:event_jtxfuserFocusLost

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

    private void jtxfpassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxfpassFocusGained
        // TODO add your handling code here:
        if (new String(jtxfpass.getPassword()).equals("Ingrese su Contraseña")) {
            jtxfpass.setText("");
            jtxfpass.setEchoChar('*'); // Asegurarse de que el campo vuelva a ocultar el texto
            jtxfpass.setForeground(new Color(0, 51, 153));
        }
    }//GEN-LAST:event_jtxfpassFocusGained

    private void jtxfpassFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxfpassFocusLost
        // TODO add your handling code here:
        if (new String(jtxfpass.getPassword()).isEmpty()) {
            jtxfpass.setText("Ingrese su Contraseña");
            jtxfpass.setEchoChar((char) 0); // Desactiva el ocultamiento de texto para mostrar el mensaje
            jtxfpass.setForeground(new Color(999999)); // Color gris claro
        }
    }//GEN-LAST:event_jtxfpassFocusLost

    private void jbtnloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnloginActionPerformed
        // TODO add your handling code here:
        // Verificar si los campos están vacíos o contienen el texto por defecto
        if (jtxfuser.getText().equals("Ingrese su Usuario") || jtxfuser.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese su usuario");
            jtxfuser.requestFocusInWindow(); // Mover foco al campo de usuario
        } else if (new String(jtxfpass.getPassword()).equals("Ingrese su Contraseña") || new String(jtxfpass.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese su contraseña");
            jtxfpass.requestFocusInWindow(); // Mover foco al campo de contraseña
        } else if (jtxfpass.getPassword().length < 8) {
            JOptionPane.showMessageDialog(null, "Longitud de Contraseña Invalida");
            jtxfpass1.requestFocusInWindow(); // Mover foco al campo de contraseña
        } else if (new String(jtxfpass.getPassword()).matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(null, "La contraseña no debe contener números");
            jtxfpass1.requestFocusInWindow(); // Mover foco al campo de contraseña
        } else if (jtxfmail1.getText().equals("Ingrese su Email") || jtxfmail1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese su Email");
            jtxfmail1.requestFocusInWindow(); // Mover foco al campo de usuario
        } else if (!isValidEmail(jtxfmail1.getText())) {
            JOptionPane.showMessageDialog(null, "Ingrese un correo electrónico válido");
            jtxfmail1.requestFocusInWindow(); // Mover foco al campo de email
        } else {
            // Obtener los valores ingresados por el usuario
            String username = jtxfuser.getText();
            String password = new String(jtxfpass.getPassword());
            String email = jtxfmail1.getText();
            
            // Encriptar la contraseña ingresada
            String encryptedPassword = encryptPassword(password);

            // Conectar a la base de datos
            try {
                connectionDB connection = new connectionDB();
                Connection cn = connection.Open();

                // Crear una consulta SQL para verificar si el usuario, la contraseña y el correo electrónico existen
                String query = "SELECT * FROM Dates WHERE nickname = ? AND password = ? AND email = ?";
                PreparedStatement pst = cn.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, encryptedPassword);
                pst.setString(3, email);

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    // Usuario, contraseña y correo electrónico coinciden
                    JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.");
                    String nickname = jtxfuser.getText().trim(); // Obtener el nombre de usuario

                    // Abrir el frmMenu y pasar el nickname                    
                    frmMenu menu = new frmMenu(nickname);
                    menu.setLocationRelativeTo(null);
                    menu.setVisible(true);

                    // Cerrar o esconder el frmLogin si es necesario
                    this.dispose();
                } else {
                    // Usuario, contraseña o correo electrónico incorrectos
                    JOptionPane.showMessageDialog(null, "Usuario, contraseña o correo electrónico incorrectos.");
                }

                cn.close();
            } catch (SQLException e) {
                System.out.println("Error al conectar a la base de datos: " + e);
                JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos.");
            }
        }
    }//GEN-LAST:event_jbtnloginActionPerformed

    private void jlblsigninMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblsigninMouseClicked
        // TODO add your handling code here:
        showdesign2();
        hidedesign1();
        imagesdesign2();
        showtextpassdesign2();
    }//GEN-LAST:event_jlblsigninMouseClicked

    private void jlblpass1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblpass1MouseClicked
        // TODO add your handling code here:
        // Alternar entre mostrar y ocultar la contraseña
        if (isPasswordVisible) {
            RSScaleLabel.setScaleLabel(jlblpass1, "./images/ocultar.png");
            jtxfpass1.setEchoChar('*'); // Ocultar el texto
        } else {
            RSScaleLabel.setScaleLabel(jlblpass1, "./images/mostrar.png");
            jtxfpass1.setEchoChar((char) 0); // Mostrar el texto
        }
        isPasswordVisible = !isPasswordVisible; // Cambiar el estado
    }//GEN-LAST:event_jlblpass1MouseClicked

    private void jtxfpass1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxfpass1FocusGained
        // TODO add your handling code here:
        if (new String(jtxfpass1.getPassword()).equals("Ingrese su Contraseña")) {
            jtxfpass1.setText("");
            jtxfpass1.setEchoChar('*'); // Asegurarse de que el campo vuelva a ocultar el texto
            jtxfpass1.setForeground(new Color(0, 51, 153));
        }
    }//GEN-LAST:event_jtxfpass1FocusGained

    private void jtxfpass1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxfpass1FocusLost
        // TODO add your handling code here:
        if (new String(jtxfpass1.getPassword()).isEmpty()) {
            jtxfpass1.setText("Ingrese su Contraseña");
            jtxfpass1.setEchoChar((char) 0); // Desactiva el ocultamiento de texto para mostrar el mensaje
            jtxfpass1.setForeground(new Color(999999)); // Color gris claro
        }
    }//GEN-LAST:event_jtxfpass1FocusLost

    private void jtxfuser1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxfuser1FocusGained
        // TODO add your handling code here:
        if (jtxfuser1.getText().equals("Ingrese su Usuario")) {
            jtxfuser1.setText("");
            jtxfuser1.setForeground(new Color(0, 51, 153));
        }
    }//GEN-LAST:event_jtxfuser1FocusGained

    private void jtxfuser1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxfuser1FocusLost
        // TODO add your handling code here:
        if (jtxfuser1.getText().isEmpty()) {
            jtxfuser1.setText("Ingrese su Usuario");
            jtxfuser1.setForeground(new Color(999999));
        }
    }//GEN-LAST:event_jtxfuser1FocusLost

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

    private void jtxfmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxfmailFocusGained
        // TODO add your handling code here:
        if (jtxfmail.getText().equals("Ingrese su Email")) {
            jtxfmail.setText("");
            jtxfmail.setForeground(new Color(0, 51, 153));
        }
    }//GEN-LAST:event_jtxfmailFocusGained

    private void jtxfmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxfmailFocusLost
        // TODO add your handling code here:
        if (jtxfmail.getText().isEmpty()) {
            jtxfmail.setText("Ingrese su Email");
            jtxfmail.setForeground(new Color(999999));
        }
    }//GEN-LAST:event_jtxfmailFocusLost

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
    
    private void jbtnsigninActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnsigninActionPerformed
        // TODO add your handling code here:        
        // Verificar si los campos están vacíos o contienen el texto por defecto
        if (jtxfuser1.getText().equals("Ingrese su Usuario") || jtxfuser1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese su usuario");
            jtxfuser1.requestFocusInWindow(); // Mover foco al campo de usuario
        } else if (new String(jtxfpass1.getPassword()).equals("Ingrese su Contraseña") || new String(jtxfpass1.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese su contraseña");
            jtxfpass1.requestFocusInWindow(); // Mover foco al campo de contraseña
        } else if (new String(jtxfconfpass.getPassword()).equals("Confirme su Contraseña") || new String(jtxfconfpass.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "Confirme su contraseña");
            jtxfconfpass.requestFocusInWindow(); // Mover foco al campo de contraseña
        } else if (jtxfmail.getText().equals("Ingrese su Email") || jtxfmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese su Email");
            jtxfmail.requestFocusInWindow(); // Mover foco al campo de usuario
        } else if (!isValidEmail(jtxfmail.getText())) {
            JOptionPane.showMessageDialog(null, "Ingrese un correo electrónico válido");
            jtxfmail.requestFocusInWindow(); // Mover foco al campo de email
        } else if (jlblimage.getText().equals("AVATAR")) {
            JOptionPane.showMessageDialog(null, "Ingrese su Avatar");
        } else if (!new String(jtxfconfpass.getPassword()).equals(new String(jtxfpass1.getPassword()))) {
            JOptionPane.showMessageDialog(null, "Contraseñas No Coinciden");
            jtxfconfpass.requestFocusInWindow(); // Mover foco al campo de contraseña
        } else if (jtxfpass1.getPassword().length < 8 || jtxfconfpass.getPassword().length < 8) {
            JOptionPane.showMessageDialog(null, "Longitud de Contraseña Invalida");
            jtxfpass1.requestFocusInWindow(); // Mover foco al campo de contraseña
        } else if (new String(jtxfpass1.getPassword()).matches(".*\\d.*") || new String(jtxfconfpass.getPassword()).matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(null, "La contraseña no debe contener números");
            jtxfpass1.requestFocusInWindow(); // Mover foco al campo de contraseña
        } else {

            // Obtener los valores ingresados por el usuario
            String username = jtxfuser1.getText();
            String password = new String(jtxfpass1.getPassword());
            String email = jtxfmail.getText();
            
            // Encriptar la contraseña antes de guardarla
            String encryptedPassword = encryptPassword(password);
        
            // Obtener la fecha y hora actual
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String registrationDate = now.format(formatter);

            // Verificar si se ha seleccionado una imagen de avatar
            if (fis == null) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una imagen antes de registrarse.");
            } else {
                try {
                    // Conectar a la base de datos
                    connectionDB connection = new connectionDB();
                    Connection cn = connection.Open();

                    // Crear una consulta SQL para insertar los datos
                    PreparedStatement pst = cn.prepareStatement("INSERT INTO Dates VALUES (?, ?, ?, ?, ?, ?)");

                    // Insertar los datos del usuario y la imagen
                    pst.setInt(1, 0);  // Asumiendo que el campo `id` es autoincremental
                    pst.setString(2, username);
                    pst.setString(3, encryptedPassword);
                    pst.setString(4, email);
                    pst.setBlob(5, fis, longitudBytes);  // Guardar la imagen
                    pst.setString(6, registrationDate);  // Insertar la fecha de registro

                    // Ejecutar la consulta
                    pst.executeUpdate();
                    cn.close();

                    JOptionPane.showMessageDialog(null, "Usuario Creado con Éxito");
  
                    // Enviar la contraseña encriptada por correo electrónico
                    String subject = "Registro Exitoso - Validación de Perfil";
                    String body = "Te damos la bienvenida " + username + ",\n\n"
                            + "Gracias por registrarte. Aquí está tu codigo de validación:\n"
                            + encryptedPassword + "\n\n"
                            + "Por favor, verifica tu perfil, en el juego.";
                    
                    //Enviar el correo
                    clsSendEmail.sendEmail(email, subject, body);

                    
                    // Abrir un diálogo con título personalizado para que el usuario ingrese el código de verificación
                    String inputCode = JOptionPane.showInputDialog(null,
                            "Se ha enviado un código de verificación a tu correo.\nPor favor, ingresa el código de verificación:",
                            "Verificación de Creación de Usuario",
                            JOptionPane.QUESTION_MESSAGE);

                    // Verificar si el código ingresado es correcto
                    if (inputCode != null && inputCode.equals(encryptedPassword)) {
                        JOptionPane.showMessageDialog(null, "Verificación exitosa. ¡Bienvenido al Juego!");

                        // Mostrar y ocultar componentes de la interfaz
                        showdesign1();
                        hidedesign2();
                        imagesdesign1();
                    } else {
                        JOptionPane.showMessageDialog(null, "El código ingresado es incorrecto. Por favor, inténtelo de nuevo.");
                    }

                } catch (SQLException e) {
                    System.out.println("Error al guardar datos: " + e);
                    JOptionPane.showMessageDialog(null, "Error al guardar datos en la base de datos");
                }
            }
        }
    }//GEN-LAST:event_jbtnsigninActionPerformed

    private void jlblnewpassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblnewpassMouseClicked
        // TODO add your handling code here:
        dispose();
        frmResetPass frmresetpass = new frmResetPass();
        frmresetpass.setLocationRelativeTo(null);
        frmresetpass.setVisible(true);
    }//GEN-LAST:event_jlblnewpassMouseClicked

    private void jtxfmail1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxfmail1FocusGained
        // TODO add your handling code here:
        if (jtxfmail1.getText().equals("Ingrese su Email")) {
            jtxfmail1.setText("");
            jtxfmail1.setForeground(new Color(0, 51, 153));
        }
    }//GEN-LAST:event_jtxfmail1FocusGained

    private void jtxfmail1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxfmail1FocusLost
        // TODO add your handling code here:
        if (jtxfmail1.getText().isEmpty()) {
            jtxfmail1.setText("Ingrese su Email");
            jtxfmail1.setForeground(new Color(999999));
        }
    }//GEN-LAST:event_jtxfmail1FocusLost

    private void jbtnreturnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnreturnMouseClicked
        // TODO add your handling code here:
        showdesign1();
        hidedesign2();
        imagesdesign1();
        showtextpassdesign1();
    }//GEN-LAST:event_jbtnreturnMouseClicked

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
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton jbtnimage;
    private javax.swing.JButton jbtnlogin;
    private javax.swing.JButton jbtnreturn;
    private javax.swing.JButton jbtnsignin;
    private javax.swing.JLabel jlblconfpass;
    private javax.swing.JLabel jlbldatetime1;
    private javax.swing.JLabel jlbldatetime2;
    private javax.swing.JLabel jlbldesign1;
    private javax.swing.JLabel jlbldesign2;
    private javax.swing.JLabel jlblimage;
    private javax.swing.JLabel jlbllogint;
    private javax.swing.JLabel jlbllogo;
    private javax.swing.JLabel jlblnewpass;
    private javax.swing.JLabel jlblpass;
    private javax.swing.JLabel jlblpass1;
    private javax.swing.JLabel jlblsignin;
    private javax.swing.JLabel jlblsignint;
    private javax.swing.JPasswordField jtxfconfpass;
    private javax.swing.JTextField jtxfmail;
    private javax.swing.JTextField jtxfmail1;
    private javax.swing.JPasswordField jtxfpass;
    private javax.swing.JPasswordField jtxfpass1;
    private javax.swing.JTextField jtxfuser;
    private javax.swing.JTextField jtxfuser1;
    // End of variables declaration//GEN-END:variables
}
