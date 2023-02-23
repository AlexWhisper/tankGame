package org.alex.tankGame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

@SuppressWarnings({"all"})
public class MyPanel extends Panel implements KeyListener, Runnable {
    Hero hero = null;
    Vector<EnemyTank> enemyTanks = new Vector<EnemyTank>();
    Vector<Bomb> bombs = new Vector<>();
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    int enemySize = 3;
    //定义一个空图片
    Image offScreenImage = null;

    //这是我需要绘制的图片
    Image select = Toolkit.getDefaultToolkit().getImage("/bg.jpg");

    public MyPanel() {

        hero = new Hero(700, 350); //创建主角坦克
        hero.setSpeed(10);


        for (int i = 0; i < enemySize; i++) {//创建敌人坦克

            EnemyTank enemytank = new EnemyTank(100 * (i + 1), 0);
            enemytank.setDirect(2);
            enemyTanks.add(enemytank);
            new Thread(enemytank).start();

//            Bullet bullet = new Bullet(enemytank.getX() + 20, enemytank.getY() + 60, enemytank.getDirect());
//            enemytank.bullets.add(bullet);
//            new Thread(bullet).start();
        }

        //在构造器中初始化三张图片对象用来绘制爆炸的效果
        image1 = Toolkit.getDefaultToolkit().getImage("src/main/resources/boom2.gif");
//        image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
//        image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));

    }

    public void hitTank(Bullet b, Tank e) {

        switch (e.getDirect()) {
            case 0:
            case 2:
                if (b.getX() > e.getX() && b.getX() < e.getX() + 40 && b.getY() > e.getY() && b.getY() < e.getY() + 60) {
                    b.isLive = false;
                    e.isLive = false;

                    Bomb bomb = new Bomb(e.getX(), e.getY());
                    bombs.add(bomb);

                }
                break;
            case 1:
            case 3:
                if (b.getX() > e.getX() && b.getX() < e.getX() + 60 && b.getY() > e.getY() && b.getY() < e.getY() + 40) {
                    b.isLive = false;
                    e.isLive = false;

                    Bomb bomb = new Bomb(e.getX(), e.getY());
                    bombs.add(bomb);
                }
                break;

        }


    }

    public void hitHero() {
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            for (int j = 0; j < enemyTank.bullets.size(); j++) {
                Bullet bullet = enemyTank.bullets.get(j);
                if (hero.isLive && bullet.isLive) {
                    hitTank(bullet, hero);
                }

            }
        }


    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);


        g.fillRect(0, 0, 1000, 750);
        if (hero.isLive) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);
        }

        for (int i = 0; i < hero.bullets.size(); i++) {
            hero.bullet = hero.bullets.get(i);
            if (hero.bullet != null && hero.bullet.isLive == true) {
                System.out.println("子弹被绘制");
                g.fillOval(hero.bullet.getX(), hero.bullet.getY(), 10, 10);
            }
            if (hero.bullet.isLive == false) {
                hero.bullets.remove(hero.bullet);
            }
        }

        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (bomb.life > 0) {
//                g.fill3DRect(bomb.x,bomb.y,30,30,false);
                g.drawImage(image1, bomb.x, bomb.y, 100, 100, this);
            } else {
                bombs.remove(bomb);
            }
            bomb.leftDown();
        }


        for (int i = 0; i < enemyTanks.size(); i++) {

            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.isLive) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
                for (int j = 0; j < enemyTank.bullets.size(); j++) {
                    Bullet bullet = enemyTank.bullets.get(j);
                    if (bullet.isLive) {

                        //绘制子弹
                        g.fill3DRect(bullet.getX(), bullet.getY(), 10, 10, false);

                    } else {
                        //移除子弹
                        enemyTank.bullets.remove(bullet);
                    }
                }
            } else {
                enemyTanks.remove(enemyTank);
            }
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

        switch (direct) { //根据不同的direct绘制出不同方向的坦克
            case 0:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y);
                break;
            case 1:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            case 2:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
                break;
            case 3:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x, y + 20);
                break;

            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println((char) e.getKeyCode() + "键被按下");
        if (e.getKeyCode() == KeyEvent.VK_W) {

            hero.setDirect(0);
            hero.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(2);
            hero.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirect(3);
            hero.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(1);
            hero.moveRight();
        }

        if (e.getKeyCode() == KeyEvent.VK_J) {
            System.out.println("用户按下了J，开始射击");

            hero.shotEnemy();


        }

        repaint();
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }


    @Override
    public void run() {
        while (true) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            hitEnemy(); //测试是否集中了地方坦克

            hitHero(); //测试是否击中我的坦克

            this.repaint();
        }
    }

    private void hitEnemy() {
        for (int j = 0; j < hero.bullets.size(); j++) {
            hero.bullet = hero.bullets.get(j);

            if (hero.bullet != null && hero.bullet.isLive) {
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    hitTank(hero.bullet, enemyTank);
                }
            }
        }
    }

    // 双缓冲机制的翔实告终

    public void update(Graphics g) {
        if (offScreenImage == null) {
            // 截取窗体所在位置的图片 .创建一幅用于双缓冲的、可在屏幕外绘制的图像。
            offScreenImage = this.createImage(1200, 800);
        }
        // 获得截取图片的画布
        Graphics gOffScreen = offScreenImage.getGraphics();
        // 将截下的图片上的画布传给重绘函数，重绘函数只需要在截图的画布上绘制即可，不必在从底层绘制
        paint(gOffScreen);
        //将截下来的图片加载到窗体画布上去，才能达到每次重画的效果
        g.drawImage(offScreenImage, 0, 0, null);
    }

}
