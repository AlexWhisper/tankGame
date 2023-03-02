package org.alex.tankGame;

import java.util.Vector;

public class Hero extends Tank { //这个是主角坦克，继承了tank类
    Bullet bullet=null;
    Vector<Bullet> bullets=new Vector<>();


    public Hero(int x, int y,int direct) {
        super(x, y, direct);
    }

    public void shotEnemy(){
       switch(getDirect()){
           case 0:
               bullet=new Bullet(getX()+20,getY(),0);
               bullets.add(bullet);
               break;
           case 1:
               bullet=new Bullet(getX()+60,getY()+20,1);
               bullets.add(bullet);
               break;
           case 2:
               bullet=new Bullet(getX()+20,getY()+60,2);
               bullets.add(bullet);
               break;
           case 3:
               bullet=new Bullet(getX(),getY()+20,3);
               bullets.add(bullet);
               break;
       }

       new Thread(bullet).start();

    }


}
