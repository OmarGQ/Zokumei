/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monster.island;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

/**
 *
 * @author Usuario
 */
public class CampoBatalla extends JFrame{
    private panelBatalla HUD, opciones1, opciones2,estatus;
    private Enemigo enemigo1, enemigo2, enemigo3;
    private Image hub, op, es;
    private JButton salir;
    private DragonBatalla dragon;
    private JLabel ataque, habilidad, ataEne1, ataEne2, ataEne3, habilidad1, habilidad2, fondo, salud, mana, info, inFactSalud, inFactMana;
    public int HP, dañoRecibido, dañoAplicado, turno;
    private int randInt, enemy1, enemy2, enemy3, noHabilidad;
    private Random rand;
    private boolean flagHabilidad, vivo1, vivo2, vivo3;
    public boolean success;
    private JLayeredPane capotas;
    private Timer turnos, crema;
    public final AudioClip battleSong;
    public CampoBatalla(int myHP, int ene1, int ene2, int ene3){
        rand = new Random();
        capotas = new JLayeredPane();
        this.add(capotas);
        inFactSalud = new JLabel("Salud");
        inFactMana = new JLabel("Mana");
        inFactSalud.setFont(new Font("Arial", Font.BOLD, 18));
        inFactMana.setFont(new Font("Arial", Font.BOLD, 18));
        inFactSalud.setForeground(Color.WHITE);
        inFactMana.setForeground(Color.WHITE);
        battleSong = java.applet.Applet.newAudioClip(getClass().getResource("audio/19.wav"));
        battleSong.loop();
        fondo = new JLabel(new ImageIcon(getClass().getResource("imagenes/personajes/batalla/fondo.png")));
        fondo.setSize(1280, 504);
        fondo.setLocation(0, 0);
        salud = new JLabel(""+myHP+"/800");
        salud.setFont(new Font("Arial", Font.BOLD, 18));
        salud.setForeground(Color.WHITE);
        mana = new JLabel("500/500");
        mana.setFont(new Font("Arial", Font.BOLD, 18));
        mana.setForeground(Color.WHITE);
        info = new JLabel("boi");
        info.setVisible(false);
        info.setLocation(390, 20);
        info.setSize(500, 70);
        info.setFont(new Font("Arial", Font.BOLD, 18));
        info.setForeground(Color.WHITE);
        if(enemy1 == 0 && enemy2 == 0 && enemy3 == 0){
            enemy1 = 1;
        }
        success = false;
        enemy1 = ene1;
        enemy2 = ene2;
        enemy3 = ene3;
        vivo1 = true;
        vivo2 = true;
        vivo3 = true;
        turno = 0; //0 = jugador 1 = enemigo1 2= enemigo2 3 = enemigo3
        HP = myHP;
        hub = new ImageIcon(getClass().getResource("imagenes/interfaz/HUD.png")).getImage();
        op = new ImageIcon(getClass().getResource("imagenes/interfaz/opciones.png")).getImage();
        es = new ImageIcon(getClass().getResource("imagenes/interfaz/estatus.png")).getImage();
        capotas.add(fondo, new Integer(0));
        flagHabilidad = false;
        ataque = new JLabel("Atacar");
        randInt = rand.nextInt(30);
        ataEne1 = null;
        ataEne2 = null;
        ataEne3 = null;
        habilidad = new JLabel("Habilidad");
        habilidad1 = new JLabel("Fuegote");
        habilidad2 = new JLabel("Cura");
        ataque.setFont(new Font("Arial", Font.BOLD, 18));
        ataque.setForeground(Color.WHITE);
        habilidad.setFont(new Font("Arial", Font.BOLD, 18));
        habilidad.setForeground(Color.WHITE);
        habilidad1.setFont(new Font("Arial", Font.BOLD, 18));
        habilidad1.setForeground(Color.WHITE);
        habilidad2.setFont(new Font("Arial", Font.BOLD, 18));
        habilidad2.setForeground(Color.WHITE);
        habilidad2.setVisible(false);
        dragon = new DragonBatalla(1);
        dragon.setLocation(500, 400);
        capotas.add(dragon, new Integer(2));
        capotas.add(dragon.rasguño, new Integer(3));
        capotas.add(dragon.fuego, new Integer(3));
        dragon.setHP(myHP);
        HUD = new panelBatalla();
        opciones1 = new panelBatalla();
        opciones2 = new panelBatalla();
        estatus =new panelBatalla();
        enemigo1 = null;
        enemigo2 = null;
        enemigo3 = null;
        salir = new JButton();
        capotas.setSize(1280, 720);
        capotas.setLocation(0, 0);
        salir.setLocation(0,0);
        salud.setSize(150, 48);
        inFactSalud.setSize(150, 48);
        inFactMana.setSize(150, 48);
        mana.setSize(150, 48);
        salir.setSize(100,100);
        HUD.setImage(hub);
        opciones1.setImage(op);
        opciones2.setImage(op);
        estatus.setImage(es);
        if(enemy1 == 1){
            enemigo1 = new Enemigo("conejo");
            enemigo1.setLocation(200, 200);
            capotas.add(enemigo1.rasguño, new Integer(3));
            capotas.add(enemigo1.mordida, new Integer(3));
            ataEne1 = new JLabel("Conejo");
            ataEne1.setFont(new Font("Arial", Font.BOLD, 18));
            ataEne1.setForeground(Color.WHITE);
            ataEne1.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent mk){
                    if(flagHabilidad == false){
                        dañoRecibido = 0;
                        dañoRecibido = myAtaque(0, enemigo1) - enemigo1.getDEF();
                        enemigo1.setHP(enemigo1.getHP()- dañoRecibido);
                        ataque.setVisible(false);
                        info.setVisible(true);
                        info.setText("Has usado rasguño por "+dañoRecibido+"");
                        if(enemigo1.getHP() <= 0){
                            vivo1 = false;
                        }
                        win(vivo1, vivo2, vivo3);
                    }
                    else{
                        if(noHabilidad == 1){
                            dañoRecibido = 0;
                            dañoRecibido = myAtaque(1, enemigo1) - enemigo1.getDEF();
                            enemigo1.setHP(enemigo1.getHP()- dañoRecibido);
                            flagHabilidad = false;
                            ataque.setVisible(false);
                            info.setVisible(true);
                            info.setText("Has usado fuegote por "+dañoRecibido+"");
                            mana.setSize(dragon.getMP()- (500/150), mana.getSize().height);
                            if(enemigo1.getHP() <= 0){
                                vivo1 = false;
                            }
                            win(vivo1, vivo2, vivo3);
                        }
                    }
                }
            });
            win(vivo1, vivo2, vivo3);
            opciones2.add(ataEne1);
            capotas.add(enemigo1, new Integer(2));
        }
        else if(enemy1 == 2){
            enemigo1 = new Enemigo("ojo");
            enemigo1.setLocation(200, 200);
            ataEne1 = new JLabel("Ojo");
            ataEne1.setFont(new Font("Arial", Font.BOLD, 18));
            ataEne1.setForeground(Color.WHITE);
            capotas.add(enemigo1.laser, new Integer(3));
            capotas.add(enemigo1.mirada, new Integer(3));
            ataEne1.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent mk){
                    if(flagHabilidad == false){
                        dañoRecibido = 0;
                        dañoRecibido = myAtaque(0, enemigo1) - enemigo1.getDEF();
                        enemigo1.setHP(enemigo1.getHP()- dañoRecibido);
                        ataque.setVisible(false);
                        info.setVisible(true);
                        info.setText("Has usado rasguño por "+dañoRecibido+"");
                        if(enemigo1.getHP() <= 0){
                                vivo1 = false;
                        }
                        win(vivo1, vivo2, vivo3);
                    }
                    else{
                        if(noHabilidad == 1){
                            dañoRecibido = 0;
                            dañoRecibido = myAtaque(1, enemigo1) - enemigo1.getDEF();
                            enemigo1.setHP(enemigo1.getHP()- dañoRecibido);
                            flagHabilidad = false;
                            ataque.setVisible(false);
                            info.setVisible(true);
                            info.setText("Has usado fuegote por "+dañoRecibido+"");
                            mana.setSize(dragon.getMP()- (500/150), mana.getSize().height);
                            if(enemigo1.getHP() <= 0){
                                vivo1 = false;
                            }
                            win(vivo1, vivo2, vivo3);
                        }
                    }
                }
            });
            opciones2.add(ataEne1);
            capotas.add(enemigo1, new Integer(2));
        }
        else if(enemy1 == 3){
            enemigo1 = new Enemigo("karateka");
            enemigo1.setLocation(200, 200);
            ataEne1 = new JLabel("Karateka");
            ataEne1.setFont(new Font("Arial", Font.BOLD, 18));
            ataEne1.setForeground(Color.WHITE);
            capotas.add(enemigo1.puñetazo, new Integer(3));
            ataEne1.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent mk){
                    if(flagHabilidad == false){
                        dañoRecibido = 0;
                        dañoRecibido = myAtaque(0, enemigo1) - enemigo1.getDEF();
                        dragon.rasguño.setLocation(dragon.rasguño.getLocation().x, dragon.rasguño.getLocation().y);
                        enemigo1.setHP(enemigo1.getHP()- dañoRecibido);
                        ataque.setVisible(false);
                        info.setVisible(true);
                        info.setText("Has usado rasguño por "+dañoRecibido+"");
                        if(enemigo1.getHP() <= 0){
                            vivo1 = false;
                        }
                        win(vivo1, vivo2, vivo3);
                    }
                    else{
                        if(noHabilidad == 1){
                            dañoRecibido = 0;
                            dañoRecibido = myAtaque(1, enemigo1) - enemigo1.getDEF();
                            enemigo1.setHP(enemigo1.getHP()- dañoRecibido);
                            flagHabilidad = false;
                            ataque.setVisible(false);
                            info.setVisible(true);
                            info.setText("Has usado fuegote por "+dañoRecibido+"");
                            mana.setText(""+dragon.getMP()+"/500");
                            if(enemigo1.getHP() <= 0){
                            vivo1 = false;
                            }
                            win(vivo1, vivo2, vivo3);
                        }
                    }
                }
            });
            opciones2.add(ataEne1);
            capotas.add(enemigo1, new Integer(2));
        }
        else if(enemy1 == 0){
            vivo1 = false;
        }
        if(enemy2 == 1){
            enemigo2 = new Enemigo("conejo");
            enemigo2.setLocation(400, 200);
            ataEne2 = new JLabel("Conejo");
            ataEne2.setFont(new Font("Arial", Font.BOLD, 18));
            capotas.add(enemigo2.rasguño, new Integer(3));
            capotas.add(enemigo2.mordida, new Integer(3));
            ataEne2.setForeground(Color.WHITE);
            info.setVisible(true);
            ataEne2.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent mk){
                    if(flagHabilidad == false){
                        dañoRecibido = 0;
                        dañoRecibido = myAtaque(0, enemigo2) - enemigo2.getDEF();
                        dragon.rasguño.setLocation(dragon.rasguño.getLocation().x, dragon.rasguño.getLocation().y);
                        enemigo2.setHP(enemigo2.getHP()- dañoRecibido);
                        info.setText("Has usado rasguño por "+dañoRecibido+"");
                        ataque.setVisible(false);
                        if(enemigo2.getHP() <= 0){
                            vivo2 = false;
                        }
                        win(vivo1, vivo2, vivo3);
                    }
                    else{
                        if(noHabilidad == 1){
                            dañoRecibido = 0;
                            dañoRecibido = myAtaque(1, enemigo2) - enemigo2.getDEF();
                            enemigo2.setHP(enemigo2.getHP()- dañoRecibido);
                            flagHabilidad = false;
                            ataque.setVisible(false);
                            info.setVisible(true);
                            info.setText("Has usado fuegote por "+dañoRecibido+"");
                            mana.setText(""+dragon.getMP()+"/500");
                            if(enemigo2.getHP() <= 0){
                                vivo2 = false;
                            }
                            win(vivo1, vivo2, vivo3);
                        }
                    }
                }
            });
            opciones2.add(ataEne2);
            capotas.add(enemigo2, new Integer(2));
        }
        else if(enemy2 == 2){
            enemigo2 = new Enemigo("ojo");
            enemigo2.setLocation(400, 200);
            ataEne2 = new JLabel("Ojo");
            ataEne2.setFont(new Font("Arial", Font.BOLD, 18));
            ataEne2.setForeground(Color.WHITE);
            capotas.add(enemigo2.laser, new Integer(3));
            capotas.add(enemigo2.mirada, new Integer(3));
            info.setVisible(true);
            ataEne2.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent mk){
                    if(flagHabilidad == false){
                        dañoRecibido = 0;
                        dañoRecibido =  myAtaque(0, enemigo2) - enemigo2.getDEF();
                        dragon.rasguño.setLocation(dragon.rasguño.getLocation().x, dragon.rasguño.getLocation().y);
                        enemigo2.setHP(enemigo2.getHP()- dañoRecibido);
                        info.setText("Has usado rasguño por "+dañoRecibido+"");
                        ataque.setVisible(false);
                        if(enemigo2.getHP() <= 0){
                            vivo2 = false;
                        }
                        win(vivo1, vivo2, vivo3);
                    }
                    else{
                        if(noHabilidad == 1){
                            dañoRecibido = 0;
                            dañoRecibido =  myAtaque(1, enemigo2) - enemigo2.getDEF();
                            enemigo2.setHP(enemigo2.getHP()- dañoRecibido);
                            flagHabilidad = false;
                            ataque.setVisible(false);
                            info.setVisible(true);
                            info.setText("Has usado fuegote por "+dañoRecibido+"");
                            mana.setText(""+dragon.getMP()+"/500");
                            if(enemigo2.getHP() <= 0){
                                vivo2 = false;
                            }
                            win(vivo1, vivo2, vivo3);
                        }
                    }
                }
            });
            opciones2.add(ataEne2);
            capotas.add(enemigo2, new Integer(2));
        }
        else if(enemy2 == 3){
            enemigo2 = new Enemigo("karateka");
            enemigo2.setLocation(400, 200);
            ataEne2 = new JLabel("Karateka");
            ataEne2.setFont(new Font("Arial", Font.BOLD, 18));
            ataEne2.setForeground(Color.WHITE);
            capotas.add(enemigo2.puñetazo, new Integer(3));
            ataEne2.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent mk){
                    if(flagHabilidad == false){
                        dañoRecibido =  myAtaque(0, enemigo2) - enemigo2.getDEF();
                        dragon.rasguño.setLocation(dragon.rasguño.getLocation().x, dragon.rasguño.getLocation().y);
                        enemigo2.setHP(enemigo2.getHP()- dañoRecibido);
                        ataque.setVisible(false);
                        info.setVisible(true);
                        info.setText("Has usado rasguño por "+dañoRecibido+"");
                        if(enemigo2.getHP() <= 0){
                            vivo2 = false;
                        }
                        win(vivo1, vivo2, vivo3);
                    }
                    else{
                        if(noHabilidad == 1){
                            dañoRecibido = 0;
                            dañoRecibido =  myAtaque(1, enemigo2) - enemigo2.getDEF();
                            enemigo2.setHP(enemigo2.getHP()- dañoRecibido);
                            flagHabilidad = false;
                            ataque.setVisible(false);
                            info.setVisible(true);
                            info.setText("Has usado fuegote por "+dañoRecibido+"");
                            mana.setText(""+dragon.getMP()+"/500");
                            if(enemigo2.getHP() <= 0){
                            vivo2 = false;
                            }
                            win(vivo1, vivo2, vivo3);
                        }
                    }
                }
            });
            opciones2.add(ataEne2);
            capotas.add(enemigo2, new Integer(2));
        }
        else if(enemy2 == 0){    
            vivo2 = false;
        }
        if(enemy3 == 1){
            enemigo3 = new Enemigo("conejo");
            enemigo3.setLocation(600, 200);
            ataEne3 = new JLabel("Conejo");
            ataEne3.setFont(new Font("Arial", Font.BOLD, 18));
            ataEne3.setForeground(Color.WHITE);
            capotas.add(enemigo3.rasguño, new Integer(3));
            capotas.add(enemigo3.mordida, new Integer(3));
            ataEne3.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent mk){
                    if(flagHabilidad == false){
                        dañoRecibido = 0;
                        dañoRecibido =  myAtaque(0, enemigo3) - enemigo3.getDEF();
                        dragon.rasguño.setLocation(dragon.rasguño.getLocation().x, dragon.rasguño.getLocation().y);
                        enemigo3.setHP(enemigo3.getHP()- dañoRecibido);
                        ataque.setVisible(false);
                        info.setVisible(true);
                        info.setText("Has usado rasguño por "+dañoRecibido+"");
                        if(enemigo3.getHP() <= 0){
                            vivo3 = false;
                        }
                        win(vivo1, vivo2, vivo3);
                    }
                    else{
                        if(noHabilidad == 1){
                            dañoRecibido = 0;
                            dañoRecibido =  myAtaque(1, enemigo3) - enemigo3.getDEF();
                            enemigo3.setHP(enemigo3.getHP()- dañoRecibido);
                            flagHabilidad = false;
                            ataque.setVisible(false);
                            info.setVisible(true);
                            info.setText("Has usado fuegote por "+dañoRecibido+"");
                            mana.setText(""+dragon.getMP()+"/500");
                            if(enemigo3.getHP() <= 0){
                            vivo3 = false;
                            }
                            win(vivo1, vivo2, vivo3);
                        }
                    }
                }
            });
            opciones2.add(ataEne3);
            capotas.add(enemigo3, new Integer(2));
        }
        else if(enemy3 == 2){
            enemigo3 = new Enemigo("ojo");
            enemigo3.setLocation(600, 200);
            ataEne3 = new JLabel("Ojo");
            ataEne3.setFont(new Font("Arial", Font.BOLD, 18));
            ataEne3.setForeground(Color.WHITE);
            capotas.add(enemigo3.laser, new Integer(3));
            capotas.add(enemigo3.mirada, new Integer(3));
            ataEne3.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent mk){
                    if(flagHabilidad == false){
                        dañoRecibido = 0;
                        dañoRecibido = enemigo3.getDEF() - myAtaque(0, enemigo3);
                        dragon.rasguño.setLocation(dragon.rasguño.getLocation().x, dragon.rasguño.getLocation().y);
                        enemigo3.setHP(enemigo3.getHP()- dañoRecibido);
                        ataque.setVisible(false);
                        info.setVisible(true);
                        info.setText("Has usado rasguño por "+dañoRecibido+"");
                        if(enemigo3.getHP() <= 0){
                            vivo3 = false;
                        }
                        win(vivo1, vivo2, vivo3);
                        }
                    else{
                        if(noHabilidad == 1){
                            dañoRecibido = 0;
                            dañoRecibido =  myAtaque(1, enemigo3) - enemigo3.getDEF();
                            enemigo3.setHP(enemigo3.getHP()- dañoRecibido);
                            flagHabilidad = false;
                            ataque.setVisible(false);
                            info.setVisible(true);
                            info.setText("Has usado fuegote por "+dañoRecibido+"");
                            mana.setText(""+dragon.getMP()+"/500");
                            
                        }
                        if(enemigo3.getHP() <= 0){
                            vivo3 = false;
                        }
                        win(vivo1, vivo2, vivo3);
                        }
                }
            });
            opciones2.add(ataEne3);
            capotas.add(enemigo3, new Integer(2));
        }
        else if(enemy3 == 3){
            enemigo3 = new Enemigo("karateka");
            enemigo3.setLocation(600, 200);
            ataEne3 = new JLabel("Karateka");
            ataEne3.setFont(new Font("Arial", Font.BOLD, 18));
            ataEne3.setForeground(Color.WHITE);
            capotas.add(enemigo3.puñetazo, new Integer(3));
            ataEne3.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent mk){
                    if(flagHabilidad == false){
                        dañoRecibido = 0;
                        dañoRecibido =  myAtaque(0, enemigo3) - enemigo3.getDEF();
                        dragon.rasguño.setLocation(dragon.rasguño.getLocation().x, dragon.rasguño.getLocation().y);
                        enemigo3.setHP(enemigo3.getHP()- dañoRecibido);
                        ataque.setVisible(false);
                        info.setVisible(true);
                        info.setText("Has usado rasguño por "+dañoRecibido+"");
                        if(enemigo3.getHP() <= 0){
                            vivo3 = false;
                        }
                        win(vivo1, vivo2, vivo3);
                    }
                    else{
                        if(noHabilidad == 1){
                            dañoRecibido = 0;
                            dañoRecibido =  myAtaque(1, enemigo3) - enemigo3.getDEF();
                            enemigo3.setHP(enemigo3.getHP()- dañoRecibido);
                            flagHabilidad = false;
                            ataque.setVisible(false);
                            info.setVisible(true);
                            info.setText("Has usado fuegote por "+dañoRecibido+"");
                            mana.setText(""+dragon.getMP()+"/500");
                            if(enemigo3.getHP() <= 0){
                                vivo3 = false;
                            }
                            win(vivo1, vivo2, vivo3);
                        }
                    }
                }
            });
            opciones2.add(ataEne3);
            capotas.add(enemigo3, new Integer(2));
        }
        else if(enemy3 == 0){
            vivo3 = false;
        }
        capotas.add(HUD, new Integer(4));
        capotas.add(opciones1, new Integer(4));
        capotas.add(opciones2, new Integer(4));
        capotas.add(estatus, new Integer(4));
        salir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                getMe().dispose();
            }
        });
        success = false;
        this.add(salir);
        this.setSize(1280, 720);
        this.setLayout(null);
        HUD.add(ataque);
        HUD.add(habilidad);
        HUD.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        HUD.setLocation(0, 504);
        HUD.setSize(174, 216);
        opciones1.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));
        opciones1.setLocation(176, 504);
        opciones1.setSize(1106,216);
        opciones1.add(habilidad1);
        opciones1.add(habilidad2);
        opciones2.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));
        opciones2.setLocation(176, 504);
        opciones2.setSize(1106,216);
        estatus.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        estatus.setLocation(945,396);
        estatus.setSize(335,107);
        estatus.add(inFactSalud);
        estatus.add(inFactMana);
        estatus.add(salud);
        estatus.add(mana);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        habilidad1.setVisible(false);
        ataque.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me){
                opciones1.setVisible(false);
                opciones2.setVisible(true);
            }
        });
        habilidad.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me){
                opciones1.setVisible(true);
                opciones2.setVisible(false);
                habilidad1.setVisible(true);
                habilidad2.setVisible(true);
            }
        });
        habilidad1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent mj){
                if(dragon.getMP() < 50){
                    info.setText("No tienes mana suficiente");
                }
                else{
                    flagHabilidad = true;
                    opciones2.setVisible(true);
                    opciones1.setVisible(false);
                    habilidad1.setVisible(false);
                    habilidad2.setVisible(false);
                    noHabilidad = 1; 
                }
            }
        });
        habilidad2.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent mg){
                if(dragon.getMP() < 50){
                    info.setText("No tienes mana suficiente");
                }
                else{
                    flagHabilidad = true;
                    opciones2.setVisible(true);
                    opciones1.setVisible(false);
                    habilidad1.setVisible(false);
                    habilidad2.setVisible(false);
                    info.setText("Has usado cura por 100");
                    dragon.setHP(dragon.getHP() - dragon.atacar(2));
                    mana.setText(""+dragon.getMP()+"/500");
                    myAtaque(-1, null);
                    ataque.setVisible(false);
                    noHabilidad = 2;
                    flagHabilidad = false;
                }
            }
        });
        ataque.addComponentListener(new ComponentAdapter(){
            @Override
            public void componentHidden(ComponentEvent ce){
                habilidad.setVisible(false);
            }
            @Override
            public void componentShown(ComponentEvent cc){
                habilidad.setVisible(true);
            }
        });
        turnos = new Timer(1200, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                switch(turno){
                    case 0:
                        ataque.setVisible(false);
                        habilidad.setVisible(false);
                        habilidad1.setVisible(false);
                        habilidad2.setVisible(false);
                        break;
                    case 1:     
                        ataque.setVisible(false);
                        habilidad.setVisible(false);
                        enemyTurn(enemy1, 1);
                        break;
                    case 2:
                        ataque.setVisible(false);
                        habilidad.setVisible(false);
                        enemyTurn(enemy2, 2);
                        break;
                    case 3:
                        ataque.setVisible(false);
                        habilidad.setVisible(false);
                        enemyTurn(enemy3, 3);
                        break;
                }
                turno++;
                if(turno == 4){
                    turno = 1;
                    ataque.setVisible(true);
                    habilidad.setVisible(true);
                    turnos.stop();
                }
            }
        });
        crema = new Timer(2000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                crema.stop();
                getMe().dispose();
            }
        });
        success = false;
        capotas.add(info, new Integer(3));
        info.setVisible(false);
    }
    private CampoBatalla getMe(){
        return this;
    }
    private void endHP(){
        HP = dragon.getHP();
    }
    private void enemyTurn(int enel, int turn){
        switch(turn){
            case 1:
                if(enel > 0){
                    enemigo1.setAtaqueLocation(dragon.getLocation().x, dragon.getLocation().y);
                    dañoAplicado = enemigo1.Ataca() - enemigo1.getDEF();
                    info.setVisible(true);
                    info.setText("El enemigo "+enemigo1.getNombre()+" ha utilizado "+enemigo1.getAttName()+"");
                    dragon.setHP(dragon.getHP() - dañoAplicado);
                    salud.setText(""+dragon.getHP()+"/800");
                    ataque.setVisible(true);
                    if(dragon.getHP() <= 0){
                        lose();
                    }
                }
                break; 
            case 2:
                if(enel > 0){
                    enemigo2.setAtaqueLocation(dragon.getLocation().x, dragon.getLocation().y);
                    dañoAplicado = enemigo2.Ataca() - enemigo2.getDEF();
                    info.setVisible(true);
                    info.setText("El enemigo "+enemigo2.getNombre()+" ha utilizado "+enemigo2.getAttName()+"");
                    dragon.setHP(dragon.getHP() - dañoAplicado);
                    salud.setText(""+dragon.getHP()+"/800");
                    ataque.setVisible(true);
                    if(dragon.getHP() <= 0){
                        lose();
                    }
                }
                break;
            case 3:
                if(enel > 0){
                    enemigo3.setAtaqueLocation(dragon.getLocation().x, dragon.getLocation().y);
                    dañoAplicado = enemigo3.Ataca() - enemigo3.getDEF();
                    info.setVisible(true);
                    info.setText("El enemigo "+enemigo3.getNombre()+" ha utilizado "+enemigo3.getAttName()+"");
                    dragon.setHP(dragon.getHP() - dañoAplicado);
                    salud.setText(""+dragon.getHP()+"/800");
                    salud.setVisible(true);
                    ataque.setVisible(true);
                    if(dragon.getHP() <= 0){
                        lose();
                    }
                }
                break;
        }
    }
    private void win(boolean alive1, boolean alive2, boolean alive3){
        if(enemy1 > 0){
            if(alive1 == false){
                enemy1 = 0;
                enemigo1.setIcon(new ImageIcon(getClass().getResource("imagenes/enemigos/ded.png")));
                opciones2.remove(ataEne1);
                ataEne1 = null;
            } 
        }
        else if(enemy2 > 0){
            if(alive2 == false){
                enemy2 = 0;
                enemigo2.setIcon(new ImageIcon(getClass().getResource("imagenes/enemigos/ded.png")));
                opciones2.remove(ataEne2);
                ataEne2 = null;
            }
        }
        else if(enemy3 > 0){
            if(alive3 == false){
                enemy3 = 0;
                enemigo3.setIcon(new ImageIcon(getClass().getResource("imagenes/enemigos/ded.png")));
                opciones2.remove(ataEne3);
                ataEne3 = null;
            }
        }
        if(alive1 == false && alive2 == false && alive3 == false){
            success = true;
            info.setText("HAS GANADO");
            battleSong.stop();
            crema.start();
        }
    }
    private void lose(){
        success = false;
        battleSong.stop();
        info.setText("HAS MUERTO");
        crema.start();
        this.dispose();
    }
    private int myAtaque(int ii, Enemigo ene){
        if(ii == -1 && ene == null){
            turnos.start();
            return 0;
        }
        if(ii == 0){
            dragon.rasguño.setLocation(ene.getLocation().x, ene.getLocation().y);
        }
        if(ii == 1){
            dragon.fuego.setLocation(ene.getLocation().x, ene.getLocation().y);
        }
        turnos.start();
        return dragon.atacar(ii);
    }
}
