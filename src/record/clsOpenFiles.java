/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package record;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Usuario
 */
public class clsOpenFiles {

    private JTextArea jtxatexto;  // Declaramos el JTextField como un atributo de la claseprivate JFrame frame; // Añadimos el JFrame como atributo de la clase
    private JFrame frame;  // Añadimos el JFrame como atributo de la clase

    public clsOpenFiles(JTextArea jtxatexto, JFrame frame) {
        this.jtxatexto = jtxatexto;
        this.frame = frame;
    }

    public void abrirArchivoEspecifico(String rutaArchivo) {
        // Creamos un archivo con la ruta especificada
        File fichero = new File(rutaArchivo);

        // Verificamos si el archivo existe
        if (fichero.exists() && fichero.isFile()) {
            // Leemos el contenido del archivo y lo mostramos en el JTextArea
            try (FileReader fr = new FileReader(fichero)) {
                StringBuilder cadena = new StringBuilder();
                int valor = fr.read();

                // Leemos el archivo carácter por carácter
                while (valor != -1) {
                    cadena.append((char) valor);
                    valor = fr.read();
                }

                // Mostramos el contenido en el JTextArea
                jtxatexto.setText(cadena.toString());

                // Cambiamos el título del JFrame al nombre del archivo
                frame.setTitle(fichero.getName());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else {
            // Si el archivo no existe, mostramos un mensaje de error
            JOptionPane.showMessageDialog(null, "El archivo especificado no existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
