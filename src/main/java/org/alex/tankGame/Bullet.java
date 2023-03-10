package org.alex.tankGame;

public class Bullet implements Runnable{
    private int x;
    private int y;
    private int direct;
    private int speed=10;
    boolean isLive=true;

    public Bullet(int x, int y,int direct) {
        this.x = x;
        this.y = y;
        this.direct=direct;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(70);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            switch (direct){

                case 0:
                    y-=speed;
                    break;
                case 1:
                    x+=speed;
                    break;
                case 2:
                    y+=speed;
                    break;
                case 3:
                    x-=speed;
                    break;
            }
//            System.out.println(x+" "+y);

            if (!(x>0&&x<1000&&y>0&&y<750&&isLive)){
                isLive=false;
                break;
            }

        }

    }
}
