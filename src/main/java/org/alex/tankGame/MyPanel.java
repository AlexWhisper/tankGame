package org.alex.tankGame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

@SuppressWarnings({"all"})
public class MyPanel extends Panel implements KeyListener {
    Hero hero = null;
    Vector<EnemyTank> enemyTanks=new Vector<EnemyTank>();
    int enemySize=3;

    public MyPanel() {
        hero = new Hero(700, 350);
        hero.setSpeed(10);
        for (int i = 0; i < enemySize; i++) {
            EnemyTank enemytank = new EnemyTank(100*(i+1),0);
            enemytank.setDirect(2);
            enemyTanks.add(enemytank);
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);
        drawTank(hero.getX(),hero.getY(),g,hero.getDirect(),0);
        for (int i = 0; i < enemySize; i++) {

            drawTank(enemyTanks.get(i).getX(), enemyTanks.get(i).getY(),g, enemyTanks.get(i).getDirect(),1);
        }

    }

    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        switch (type) { //根据不同类型设置不同颜色的画笔，画出不同类型坦克
            case 0:
                g.setColor(Color.YELLOW);
                break;
            case 1:
                g.setColor(Color.cyan);
                break;
            default:
                break;
        }

        switch (direct){ //根据不同的direct绘制出不同方向的坦克
            case 0:
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y);
                break;
            case 1:
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x,y+30,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x+60,y+20);
                break;
            case 2:
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y+60);
                break;
            case 3:
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x,y+30,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x,y+20);
                break;

            default:break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println((char)e.getKeyCode()+"键被按下");
        if (e.getKeyCode()==KeyEvent.VK_UP){
            hero.setDirect(0);
            hero.moveUp();
        } else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
            hero.setDirect(2);
            hero.moveDown();
        }else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
            hero.setDirect(3);
            hero.moveLeft();
        }else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
            hero.setDirect(1);
            hero.moveRight();
        }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
