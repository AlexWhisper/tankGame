package org.alex.tankGame;

public class Bomb {
    int x,y,life=10;
    boolean isLive;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void leftDown(){
        if(life>0){
            life--;
        }else{
            isLive=false;
        }
    }
}
