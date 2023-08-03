/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monster.island;

import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Usuario
 */
public class Ataque extends JLabel{
    private int damage, fotograma;
    private String nom_att;
    private Timer tiempo;
    private final AudioClip sound;
    public Ataque(String nom){
        damage = 0;
        nom_att = nom;
        sound = java.applet.Applet.newAudioClip(getClass().getResource("audio/ataques/"+nom_att+".wav"));
        fotograma = 1;
        this.setSize(100, 100);
        this.setVisible(false);
        tiempo = new Timer(100,new ActionListener(){ //Este timer es responsable de la animacion de golpe del ataque
            @Override
            public void actionPerformed(ActionEvent e) {
                getMe().setVisible(true);
                setIcon(new ImageIcon(getClass().getResource("imagenes/ataques/"+nom_att+"/"+fotograma+".png")));//recibe  el nombre del ataque y el fotograma, busca carpeta con nombre de ataque e imagen con nombre de fotograma
                fotograma++; //pasa el fotograma
                if(fotograma == 12){ //si el fotograma es 12 (debatible) para el timer 
                    fotograma = 1;
                    getMe().setVisible(false);
                    tiempo.stop();
                }
            } 
        });
        if(nom_att == "fuego"){
            this.setDamage(70);
        }
        else if(nom_att == "laser"){
            this.setDamage(45);
        }
        else if(nom_att == "mirada"){
            this.setDamage(-40);
        }
        else if(nom_att == "rasguño"){
            this.setDamage(40);
        }
        else if(nom_att == "puñetazo"){
            this.setDamage(30);
        }
        else if(nom_att == "mordida"){
            this.setDamage(35);
        }
        else if(nom_att == "cura"){
            this.setDamage(-150);
        }
    }
    private Ataque getMe(){
        return this;
    }
    public void Atacar(){ //metodo que da inicio a la animacion
        tiempo.start();
        if(!(nom_att == "cura")){
            sound.play();
        }
        fotograma = 1;
    }
    public void setDamage(int dmg){ //metodo para darle un valor de daño al ataque
        this.damage = dmg;
    }
    public void setNomAtt(String nom){ //metodo para darle un nombre al ataque
        this.nom_att = nom;
    }
    public String getNomAtt(){ //metodo para obtener el nombre del ataque
        return this.nom_att;
    }
    public int getDamage(){//metodo para obtener el nombre del ataque
        return this.damage;
    }
}
