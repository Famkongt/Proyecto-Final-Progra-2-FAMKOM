/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarea6cartas;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 *
 * @author Diego
 */
public class Cartas {
    static int conteo =0, primeracarta=0;
    boolean value = false;
     int primerClick = -1;
     JLabel etiqueta;
    int aleatorios[]= new int[64];
    JLabel dcJlabel;
    boolean arribaoabajo=false;
    boolean choice[]=new boolean[64];
    List<JLabel> dcjlabellist = new ArrayList<JLabel>();
    boolean estado[]= new boolean[64];
    List<JLabel> dcjlabellistordenadas = new ArrayList<JLabel>();
    int tamaniox = 87, tamanioy = 95; 
    int [][] matrizcoordenadas={{10,10},{tamaniox+20,10},{tamaniox*2+30,10},{tamaniox*3+40,10},{tamaniox*4+50,10},{tamaniox*5+60,10},{tamaniox*6+70,10},{tamaniox*7+80,10},
                                {10,tamanioy+20},{tamaniox+20,tamanioy+20},{tamaniox*2+30,tamanioy+20},{tamaniox*3+40,tamanioy+20},{tamaniox*4+50,tamanioy+20},{tamaniox*5+60,tamanioy+20},{tamaniox*6+70,tamanioy+20},{tamaniox*7+80,tamanioy+20},
                                {10,tamanioy*2+30},{tamaniox+20,tamanioy*2+30},{tamaniox*2+30,tamanioy*2+30},{tamaniox*3+40,tamanioy*2+30},{tamaniox*4+50,tamanioy*2+30},{tamaniox*5+60,tamanioy*2+30},{tamaniox*6+70,tamanioy*2+30},{tamaniox*7+80,tamanioy*2+30},
                                {10,tamanioy*3+40},{tamaniox+20,tamanioy*3+40},{tamaniox*2+30,tamanioy*3+40},{tamaniox*3+40,tamanioy*3+40},{tamaniox*4+50,tamanioy*3+40},{tamaniox*5+60,tamanioy*3+40},{tamaniox*6+70,tamanioy*3+40},{tamaniox*7+80,tamanioy*3+40},
                                {10,tamanioy*4+50},{tamaniox+20,tamanioy*4+50},{tamaniox*2+30,tamanioy*4+50},{tamaniox*3+40,tamanioy*4+50},{tamaniox*4+50,tamanioy*4+50},{tamaniox*5+60,tamanioy*4+50},{tamaniox*6+70,tamanioy*4+50},{tamaniox*7+80,tamanioy*4+50},
                                {10,tamanioy*5+60},{tamaniox+20,tamanioy*5+60},{tamaniox*2+30,tamanioy*5+60},{tamaniox*3+40,tamanioy*5+60},{tamaniox*4+50,tamanioy*5+60},{tamaniox*5+60,tamanioy*5+60},{tamaniox*6+70,tamanioy*5+60},{tamaniox*7+80,tamanioy*5+60},
                                {10,tamanioy*6+70},{tamaniox+20,tamanioy*6+70},{tamaniox*2+30,tamanioy*6+70},{tamaniox*3+40,tamanioy*6+70},{tamaniox*4+50,tamanioy*6+70},{tamaniox*5+60,tamanioy*6+70},{tamaniox*6+70,tamanioy*6+70},{tamaniox*7+80,tamanioy*6+70},
                                {10,tamanioy*7+80},{tamaniox+20,tamanioy*7+80},{tamaniox*2+30,tamanioy*7+80},{tamaniox*3+40,tamanioy*7+80},{tamaniox*4+50,tamanioy*7+80},{tamaniox*5+60,tamanioy*7+80},{tamaniox*6+70,tamanioy*7+80},{tamaniox*7+80,tamanioy*7+80},
                                };
    public Cartas(){
        
    }
    public int valorconteo (){// metodo para acceder a la variable conteo
        return conteo;
    }
    
   
     public void comparacion (int a, int b){
        System.out.println(""+a+" "+b );
         // Obtener los iconos de las etiquetas
        if (a %2==0){
          if (b==(a-1)){
         System.out.println("pareja correcta");
         value=true;
          for(int p =0; p<=dcjlabellist.size()-1; p++){
             if(dcjlabellist.get(p).getText().equals(Integer.toString(a)) || dcjlabellist.get(p).getText().equals(Integer.toString(b))){
                 dcjlabellist.get(p).setEnabled(false);
             }
         }
         conteo++;
     } else { 
         System.out.println("pareja Incorrecta");
         value=false;
         try{
              for(int p =0; p<=dcjlabellist.size()-1; p++){
             if(dcjlabellist.get(p).getText().equals(Integer.toString(a))){
                
                 rsscalelabel.RSScaleLabel.setScaleLabel(dcjlabellist.get(p), "imagenes/"+ Integer.toString(a) +".png");
                primeracarta=p;
             }else if(dcjlabellist.get(p).getText().equals(Integer.toString(b))){
                  rsscalelabel.RSScaleLabel.setScaleLabel(dcjlabellist.get(p), "imagenes/"+ Integer.toString(b) +".png");
             }
              }
         }catch(Exception e){
             System.out.println(""+e);
         }
        
            
         
     }
        }else {
             if (b==(a+1)){
         System.out.println("pareja correcta");
         value=true;
         System.out.println(""+a+" "+b);
         
         for(int p =0; p<=dcjlabellist.size()-1; p++){
             if(dcjlabellist.get(p).getText().equals(Integer.toString(a)) || dcjlabellist.get(p).getText().equals(Integer.toString(b))){
                 dcjlabellist.get(p).setEnabled(false);
             }
         }
         
         
         conteo++;
     } else { 
         System.out.println("pareja Incorrecta");
         value=false;
         try{
              for(int p =0; p<=dcjlabellist.size()-1; p++){
             if(dcjlabellist.get(p).getText().equals(Integer.toString(a))){
              
                 rsscalelabel.RSScaleLabel.setScaleLabel(dcjlabellist.get(p), "imagenes/"+ Integer.toString(a) +".png");
              }else if(dcjlabellist.get(p).getText().equals(Integer.toString(b))){
                  rsscalelabel.RSScaleLabel.setScaleLabel(dcjlabellist.get(p), "imagenes/"+ Integer.toString(b) +".png");
             }
              }
         }catch(Exception e){
             System.out.println(""+e);
         }
          

     }   
        }  
    }
     
     
     

    
    public void crearcartas(){

                        
        for(int a=0; a<64; a++){
            int s = a;
        dcJlabel = new JLabel(""+aleatorios[a]);
        dcJlabel.setBounds(matrizcoordenadas[a][0],matrizcoordenadas[a][1], tamaniox, tamanioy);
        dcJlabel.setBorder(BorderFactory.createLineBorder(Color.black));
         rsscalelabel.RSScaleLabel.setScaleLabel(dcJlabel, "imagenes/"+Integer.toString(aleatorios[a])+".png");
         choice[a]=false;
         dcjlabellist.add(dcJlabel);
         dcjlabellist.get(s).addMouseListener(new MouseListener() {
        
            
            @Override
            public void mouseClicked(MouseEvent e) {
                
                if(choice[s]){
                    rsscalelabel.RSScaleLabel.setScaleLabel(dcjlabellist.get(s), "imagenes/oculta.png");
                    choice[s]=false;
                }else{
                    rsscalelabel.RSScaleLabel.setScaleLabel(dcjlabellist.get(s), "imagenes/"+ Integer.toString(aleatorios[s]) +".png");
                    choice[s]=true;
                }
                
                 if (primerClick == -1) {//*************************************
                    // almacena la primera carta
                    primerClick = aleatorios [s];
                      primeracarta =s;
                   
                } else {
                    comparacion(primerClick, aleatorios [s]);
                    
                          class prueba extends Thread
    {
             prueba(){}
             
             @Override
             public void run(){
                 
                     try {
                        
                           
                         sleep(1000);
                        
                         System.out.println(""+primeracarta);
                         voltearcartasdevuelta(primeracarta);
                          voltearcartasdevuelta(s);
                     } catch (InterruptedException ex) {
                         Logger.getLogger(Cartas.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 
             }
             }
                                       prueba hiloprueba= new prueba();
                    
                                       if(!value){
                                           hiloprueba.start();
                                       }
                                       
                   
            
                    
                    // Reiniciar para la siguiente selección
                    primerClick = -1;
                    
                      System.out.println("su punteo es de :" + conteo);
                       
                 
                  
                }//*************************************************************
                 
            }

            @Override
            public void mousePressed(MouseEvent e) {
               
            }

            @Override
            public void mouseReleased(MouseEvent e) {
               
            }

            @Override
            public void mouseEntered(MouseEvent e) {
               dcjlabellist.get(s).setCursor(new Cursor((Cursor.HAND_CURSOR)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
               
            }
        });  
        }
       
    }
    
    public JLabel habilitardeshabilitarcartas (boolean direccion, int indice){
        
            dcJlabel=dcjlabellist.get(indice);
            dcJlabel.setEnabled(direccion);
            return dcJlabel;
        
    }
    
    
   
    public void crearaleatorios(){
        int random;
        boolean dec=false;
            for(int o = 0; o<aleatorios.length; o++){
                random = (int)(Math.random()*64)+1;
                
                for(int u = 0; u<aleatorios.length; u++){
                if(aleatorios[u]==random){
                    dec=false;
                    
                    u=aleatorios.length+5;
                }else{
                    dec=true;
                    
                   
                }
                }
                
                if(dec==true){
                    aleatorios[o]=random;
                   
                }else{
                    o=o-1;
                }
                
            }
            
    }
    
    
    
    public JLabel voltearcartas(int indice){
        dcJlabel=dcjlabellist.get(indice);
        return dcJlabel;
    }
    
    public JLabel voltearcartasdevuelta(int indice){
        dcJlabel=dcjlabellist.get(indice);
        rsscalelabel.RSScaleLabel.setScaleLabel(dcJlabel, "imagenes/oculta.png");
        return dcJlabel;
    }
    
    public JLabel mostrarcartas(int indice){
        
    dcJlabel = dcjlabellist.get(indice);
    return dcJlabel;
}
    
    public JLabel ocultarcartas(int indice){
        dcJlabel=dcjlabellist.get(indice);
        
        rsscalelabel.RSScaleLabel.setScaleLabel(dcJlabel, "imagenes/oculta.png");
        return dcJlabel;
    }
    
}



    


