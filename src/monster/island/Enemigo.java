package monster.island;

import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Enemigo  extends JLabel{
    private Timer animaciones;
    private int HP, ATT, DEF, fotograma, randInt;
    public Ataque laser, mirada, rasguño, puñetazo, mordida;
    private String myName;
    public String att_name1, att_name2;
    private Random rand;
    public Enemigo(String nombre)
    {
        fotograma = 1;
        myName = nombre;
        this.setIcon(new ImageIcon(getClass().getResource("imagenes/enemigos/"+myName+"/1.png")));
        this.setSize(100, 150);
        animaciones = new Timer(100, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                getMe().setIcon(new ImageIcon(getClass().getResource("imagenes/enemigos/"+getNombre()+"/"+fotograma+".png")));
                fotograma++;
                if(fotograma == 10){
                    fotograma = 1;
                    animaciones.stop();
                }
            }         
        });
        att_name1 = "";
        att_name2 = "";
        rand = new Random();
        laser = new Ataque("laser");
        mirada = new Ataque("mirada");
        rasguño = new Ataque("rasguño");
        puñetazo = new Ataque("puñetazo");
        mordida = new Ataque("mordida");
        randInt = 0; //Aqui hay un entero asignado al azar, que es responsable de elegir que ataque usara el enemigo
        if(myName == "ojo"){
            this.setHP(200);
            this.setATT(70);
            this.setDEF(30);
        }
        else if(myName == "karateka"){
            this.setHP(400);
            this.setATT(40);
            this.setDEF(42);
        }
        else if(myName == "conejo"){
            this.setHP(300);
            this.setATT(50);
            this.setDEF(20);
        }
    }
    private Enemigo getMe(){
        return this;
    }
    public void setNombre(String nom){ //metodo para nombrar al enemigo
        myName = nom;
    }
    public void setATT(int at){//metodo para darle una cantidad de ataque al enemigo
        this.ATT = at;
    }
    public void setDEF(int def){//metodo para darle una cantidad de defensa al enemigo
        this.DEF = def;
    }
    public void setHP(int hp){//metodo para darle una cantidad de vida al enemigo
        this.HP = hp;
    }
    public String getNombre(){//metodo para obtener el nombre del enemigo
        return this.myName;
    }
    public int getHP(){//metodo para obtener la vida del enemigo
        return this.HP;
    }
    public int getDEF(){//metodo para obtener la defensa del enemigo
        return this.DEF;
    }
    public int getATT(){//metodo para obtener el ataque del enemigo
        return this.ATT;
    }
    public int Ataca(){ //Metodo para iniciar la animacion de ataque y dar un valor de daño que aplicara el enemigo
        randInt = rand.nextInt(2);
        switch(randInt){//utiliza un entero generado al azar
            case 0:
                if(myName == "ojo"){
                    att_name1 = laser.getNomAtt(); 
                    animaciones.start();
                    laser.Atacar();
                    return (laser.getDamage()+this.ATT);
                }
                if(myName == "conejo"){
                    att_name1 = mordida.getNomAtt();
                    animaciones.start();
                    mordida.Atacar();
                    return (mordida.getDamage()+this.ATT);
                }
                if(myName == "karateka"){
                    att_name1 = puñetazo.getNomAtt();
                    animaciones.start();
                    puñetazo.Atacar();
                    return (puñetazo.getDamage()+this.ATT);
                }
            case 1:
                if(myName == "ojo"){
                    att_name2 = mirada.getNomAtt();
                    animaciones.start();
                    mirada.Atacar();
                    return (mirada.getDamage()+this.ATT);
                }
                if(myName == "conejo"){
                    att_name2 = rasguño.getNomAtt();
                    animaciones.start();
                    rasguño.Atacar();
                    return (rasguño.getDamage()+this.ATT);
                }
                if(myName == "karateka"){
                    att_name2 = puñetazo.getNomAtt();
                    animaciones.start();
                    puñetazo.Atacar();
                    return (puñetazo.getDamage()+this.ATT);
                }
            }
        return -1;//en caso de que falle por mal codigo
    }
    public int getFotograma(){//metodo para obtener el fotograma actual
        return this.fotograma;
    }
    public String getAttName(){
        if(randInt == 0){
            return att_name1;
        }
        else{
            return att_name2;
        }
    }
    public void setAtaqueLocation(int x, int y){
        if(myName == "ojo"){
            laser.setLocation(x, y);
            mirada.setLocation(x, y);
        }
        else if(myName == "conejo"){
            mordida.setLocation(x, y);
            rasguño.setLocation(x, y);
        }
        else if(myName == "karateka"){
            puñetazo.setLocation(x, y);
        }
    }
}
