/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monster.island;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Omar xv
 */
public class Ayuda extends JFrame{
    private JLabel hola, regreso;
    public Ayuda()
    {
        super();
        this.setSize(1280,720);
        this.setLocation(0,0);
        setContentPane(new JLabel(new ImageIcon(getClass().getResource("imagenes/menu/ayida.png"))));
        hola= new JLabel();
        regreso= new JLabel(new ImageIcon(getClass().getResource("imagenes/menu/return.png")));
        hola.setText("<html>CONTROLES<br><br>MAPA<br>up = arriva<br>down = abajo<br>left = izquierda<br>right = derecha<br>space = interactuar</html>");
        hola.setSize(1280, 720);
        hola.setLocation(10, 10);
        regreso.setSize(120,120);
        regreso.setLocation(1100,460);
        hola.setForeground(java.awt.Color.YELLOW);
        hola.setFont(new Font("Arial",Font.PLAIN,30));
        regreso.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent me) {
                getMe().dispose();
            }
        });
        this.setLocationRelativeTo(null);
        this.add(hola);
        this.add(regreso);
        setUndecorated(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true); 
   }
    private Ayuda getMe()
    {
        return this;
    }
}
