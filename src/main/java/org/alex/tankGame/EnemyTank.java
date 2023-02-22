package org.alex.tankGame;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {
    Bullet bullet = null;
    Vector<Bullet> bullets = new Vector<>();
    public boolean isLive = true;

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {

        while (true) {
            if (isLive && bullets.size() < 100) {
                switch (getDirect()) {
                    case 0:
                        bullet = new Bullet(getX() + 20, getY(), 0);
                        break;
                    case 1:
                        bullet = new Bullet(getX() + 60, getY() + 20, 1);
                        break;
                    case 2:
                        bullet = new Bullet(getX() + 20, getY() + 60, 2);
                        break;
                    case 3:
                        bullet = new Bullet(getX(), getY() + 20, 3);
                        break;
                }
                bullets.add(bullet);
                new Thread(bullet).start();

            }


            switch (getDirect()) {
                case 0: {
                    for (int i = 0; i < 30; i++) {
                        moveUp();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                break;

                case 1: {
                    for (int i = 0; i < 30; i++) {
                        moveRight();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }
                break;
                case 2: {
                    for (int i = 0; i < 30; i++) {
                        moveDown();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }
                break;
                case 3: {
                    for (int i = 0; i < 30; i++) {
                        moveLeft();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }

            }
            setDirect((int) (Math.random() * 4));
            if (!isLive) {
                System.out.println(Thread.currentThread().getName() + "线程退出");
                break;
            }
        }


    }
}
