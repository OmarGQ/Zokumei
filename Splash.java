/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monster.island;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Omar xv
 */
public class Splash extends JFrame{
    private Menu padre;
    private int onNow;
    private Timer tmrTiempo;
    public Splash (Menu padre)
    {
        super ();
        this.padre=padre;
        onNow = 1;
        setSize(600,400);
        setLocation(100,100);
        setContentPane(new JLabel(new ImageIcon(getClass().getResource("imagenes/menu/0.jpg"))));
        tmrTiempo=new Timer(2000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                getPadre().setVisible(true);
                tmrTiempo.stop();
                getMe().dispose();
        }});
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        tmrTiempo.start();
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);
    }
    public Splash getMe()
    {
        return this;
    }
    public Menu getPadre()
    {
        return padre;
    }
}
