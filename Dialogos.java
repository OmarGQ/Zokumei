/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monster.island;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 *
 * @author Usuario
 */
public class Dialogos extends JPanel{
    private Image ventanaDialogo;
    private JLabel nombre;
    private JLabel texto;
    public Dialogos(){
        this.setVisible(false);
        this.setLocation(730, 578);
        this.setSize(410, 133);
        this.setLayout(null);
        nombre = new JLabel();
        texto = new JLabel();
        ventanaDialogo = new ImageIcon(getClass().getResource("imagenes/interfaz/dialogue.png")).getImage();
        nombre.setLocation(9, 2);
        nombre.setSize(113, 30);
        this.addKeyListener(new KeyAdapter(){
         @Override
            public void keyPressed(KeyEvent ke) {
                if(ke.getKeyCode()== KeyEvent.VK_SPACE){
                    getMe().setFocusable(false);
                    getMe().setVisible(false);
                }
            }    
        });
        nombre.setFont(new Font("Arial", Font.BOLD, 16));
        nombre.setForeground(Color.WHITE);
        texto.setLocation(9, 32);
        texto.setText("<html>Me gustan las naranjas un monton<br>LA VERDAD NO JAJA TE MENTI VASURA</html>"); //Aqui tampoco se cambia el texto, esto se puede borrar
        texto.setVerticalAlignment(SwingConstants.NORTH);
        texto.setSize(410, 92);
        texto.setFont(new Font("Arial", Font.BOLD, 16));
        texto.setForeground(Color.WHITE);
        this.add(nombre);
        this.add(texto);
    }
    protected void paintComponent(Graphics g){
        g.drawImage(ventanaDialogo, 0, 0, this);
    }
    public void habla(String nom, String text){
        this.setVisible(true);
        this.setFocusable(true);
        this.requestFocus();
        nombre.setText(nom);
        texto.setText(text);
    }
    private Dialogos getMe(){
        return this;
    }
}
