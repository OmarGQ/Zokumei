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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Omar xv
 */
public class Interfaz extends JPanel{
    private JButton pausa;
    private Image fondillo;
    public int thisHP;
    public boolean paused, isPausable;
    public JLabel salud;
    public Interfaz(){
        super();
        pausa = new JButton();
        thisHP = 800;
        salud = new JLabel(""+thisHP+"/800");
        salud.setFont(new Font("Arial", Font.BOLD, 18));
        salud.setForeground(Color.WHITE);
        salud.setLocation(20, 100);
        salud.setSize(200, 200);
        fondillo = new ImageIcon(getClass().getResource("imagenes/interfaz/fondillo.png")).getImage();
        this.setSize(430,720);
        this.setOpaque(false);
        this.setLocation(720,0);
        this.add(pausa);
        isPausable = true;
        paused = false;
        this.setLayout(null);
        pausa.setSize(40, 40);
        pausa.setLocation(380, 10);
        this.setVisible(true);
        this.add(salud);
        pausa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(isPausable == true){
                    paused = !paused;
                    getMe().setFocusable(paused);
                    if(paused == true){
                        getMe().requestFocus();
                    }
                }
            }
        });
    }
    private Interfaz getMe(){
        return this;
    }
    public void setPaused(boolean is){
        isPausable = is;
    }
    @Override
    protected void paintComponent(Graphics g){
        g.drawImage(fondillo, 0, 0, this);
    }
}
