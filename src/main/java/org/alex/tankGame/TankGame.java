package org.alex.tankGame;

import javax.swing.*;
import java.awt.*;

public class TankGame extends JFrame {
    private MyPanel mp=null;

    public static void main(String[] args) {
        new TankGame();
    }

    public TankGame() {
        mp=new MyPanel();
        this.add(mp);
        this.addKeyListener(mp);
        this.setSize(1000,750);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


    }
}
