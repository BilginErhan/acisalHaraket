/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication50;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author ERHAN
 */
class dikdortgen
{
    public double xvel,yvel,radyan;
    public int aci,x,y,xa,ya;
    public int ileri,geri;
    public int genislik, yukseklik;
    public dikdortgen()
    {

        x=50;y=50;aci=0;xvel=0;yvel=0;
        genislik=100;yukseklik=50;ileri=0;geri=0;
    }
    public void move()
    {
        xvel=  Math.cos(Math.toRadians(aci))*20;
        yvel=  Math.sin(Math.toRadians(aci))*20;
        xa = (int) xvel;
        ya = (int) yvel;
        System.out.println(xvel);
        if(ileri == 1)
        {
            if(x+xa>0 && x+xa<600 && y+ya>0 && y+ya<600)
            {
                x=x+xa;
                y=y+ya;
            }
        }
        if(geri == 1)
        {
            if(x-xa>0 && x-xa<600 && y-ya>0 && y-ya<600)
            {
                x=x-xa;
                y=y-ya;
            }
        }
    }
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_UP)
        {
            ileri = 1;        
        }
        if(key == KeyEvent.VK_DOWN)
        {
            geri=1;
        }
        if(key == KeyEvent.VK_LEFT)
        {
            aci+=-10;
        }
        if(key == KeyEvent.VK_RIGHT)
        {
            aci+=10;
        }
    }
    
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_UP)
        {
            ileri=0;
        }
        if(key == KeyEvent.VK_DOWN)
        {
            geri=0;
        }
        if(key == KeyEvent.VK_LEFT)
        {
            aci+=0;
        }
        if(key == KeyEvent.VK_RIGHT)
        {
            aci+=0;
        }
        
    }
}
class Board extends JPanel
{
    dikdortgen d1;
    
    public Board()
    {
        d1 = new dikdortgen();
        addKeyListener(new TAdapter());
        setFocusable(true);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        doDrawing(g);
    }
    private void doDrawing(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g.create();
        
        AffineTransform old = g2d.getTransform();
        g2d.rotate(Math.toRadians(d1.aci), d1.x+d1.genislik/2, d1.y+d1.yukseklik/2);
        
        g2d.fillRect(d1.x, d1.y, d1.genislik, d1.yukseklik);
        
        g2d.setTransform(old);  
    }
    private class TAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            d1.keyPressed(e);
            repaint();
            d1.move();
        }
        @Override
        public void keyReleased(KeyEvent e)
        {
            d1.keyReleased(e);
            repaint();
            d1.move();
        }
    }
    
}
public class JavaApplication50 extends JFrame {


    public JavaApplication50()
    {
        Board board = new Board();
        add(board);
        setSize(700,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        // TODO code application logic here7
        JavaApplication50 j = new JavaApplication50();
        j.setVisible(true);
    }
    
}
