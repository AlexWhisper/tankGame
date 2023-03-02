package org.alex.tankGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankGame extends JFrame {
    private MyPanel mp=null;

    public static void main(String[] args) {
        new TankGame();
    }

    public TankGame() {
        int flag=JOptionPane.showConfirmDialog(null,"是否开始新游戏？","开始游戏",JOptionPane.YES_NO_OPTION);
        System.out.println(flag);
        mp=new MyPanel(flag);
        Thread thread = new Thread(mp);
        thread.start();
        this.setSize(1300,950);
        this.add(mp);
        this.addKeyListener(mp);

        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.exit(0);
            }
        });

    }
}
