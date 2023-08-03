/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monster.island;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Omar xv
 */
public class Personaje extends JLabel{
    private int fotograma;
    private int sentido;
    private int noPersonaje;
    private boolean flagMovimiento;
    private Timer tmrMovimiento;
    private JLabel talk;
    public Personaje(int noPersonaje)
    {
        super();
        sentido=3; //dar un numero a cadda direccion 
        flagMovimiento=false;
        setSize(40,40);
        setLocation(0,0);
        this.noPersonaje=noPersonaje;
        fotograma=1;
        setIcon(new ImageIcon(getClass().getResource("imagenes/personajes/"+noPersonaje+"/caminando/abajo/"+fotograma+".png")));
        talk = new JLabel(new ImageIcon(getClass().getResource("imagenes/interfaz/canDo.png")));
        tmrMovimiento=new Timer(100, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                flagMovimiento = true;
                switch(sentido)
                {
                    case 1:
                        if (getLocation().y>0)
                            setLocation(getLocation().x,getLocation().y-5);
                            talk.setLocation(getLocation().x,getLocation().y-5);
                        setIcon(new ImageIcon(getClass().getResource("imagenes/personajes/"+getNoPersonaje()+"/caminando/arriba/"+fotograma+".png")));
                        break;
                    case 2:
                        if (getLocation().x<2120)
                            setLocation(getLocation().x+5,getLocation().y);
                            talk.setLocation(getLocation().x+5,getLocation().y);
                        setIcon(new ImageIcon(getClass().getResource("imagenes/personajes/"+getNoPersonaje()+"/caminando/derecha/"+fotograma+".png")));
                        break;
                    case 3:
                        if (getLocation().y<2120)
                            setLocation(getLocation().x,getLocation().y+5);
                        talk.setLocation(getLocation().x,getLocation().y+5);
                        setIcon(new ImageIcon(getClass().getResource("imagenes/personajes/"+getNoPersonaje()+"/caminando/abajo/"+fotograma+".png")));
                        break;
                    case 4:
                        if (getLocation().x>0)
                            setLocation(getLocation().x-5,getLocation().y);
                        talk.setLocation(getLocation().x-5,getLocation().y);
                        setIcon(new ImageIcon(getClass().getResource("imagenes/personajes/"+getNoPersonaje()+"/caminando/izquierda/"+fotograma+".png")));
                        break;
                }
                fotograma++;
                if(fotograma==9)
                {
                    tmrMovimiento.stop();
                    flagMovimiento=false;
                }
            }
        }); 
        
    }
    public int getNoPersonaje()
    {
        return noPersonaje;
    }
    public void muevete(int sentido)
    {
        if(flagMovimiento == false){
            this.sentido=sentido;
            fotograma=1;
            tmrMovimiento.start();
        }
    }
    
    
}
