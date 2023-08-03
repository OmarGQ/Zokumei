/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monster.island;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Usuario
 */
public class DragonBatalla extends JLabel{
    public Ataque rasguño, fuego, cura;
    private int HP, DEF, ATT, MP;
    public DragonBatalla(int niv){
        this.setSize(100, 100);
        this.setIcon(new ImageIcon(getClass().getResource("imagenes/personajes/batalla/dra.png")));
        rasguño = new Ataque("rasguño");
        fuego = new Ataque("fuego");
        cura = new Ataque("cura");
        HP = 800;
        DEF = 40;
        ATT = 100;
        MP = 500;
    }
    public void setHP(int hp){
        HP = hp;
    }
    public int getHP(){
        return HP;
    }
    public int getDEF(){
        return DEF;
    }
    public int getATT(){
        return ATT;
    }
    public int getMP(){
        return MP;
    }
    public int atacar(int i){
        switch(i){
            case 0:
                rasguño.Atacar();
                return (ATT+(rasguño.getDamage()));
            case 1:
                fuego.Atacar();
                MP = MP-50;
                return (ATT+(fuego.getDamage()));
            case 2:
                MP = MP-100;
                if((this.HP + 100) >= 800){
                    HP = 800;
                    return 0;
                }
                return cura.getDamage();
        }
        return -1;
    }
}
