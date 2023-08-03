/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monster.island;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author Omar xv
 */
public class panelBatalla extends JPanel{
    private Image fondo;
    public panelBatalla(){
    }
    public void setImage(Image i){
        fondo=i;
    }
    @Override
    protected void paintComponent(Graphics g){
        g.drawImage(fondo, 0, 0, this);
    }
    
}
 