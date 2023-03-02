package org.alex.tankGame;

import java.io.*;
import java.util.Vector;

public class Recorder {
    private static int killEnemyNums=0;
    private static FileWriter fw=null;
    private static BufferedWriter bw=null;
    private static BufferedReader br=null;
    private static FileReader fr=null;
    private static String recordPath="src\\main\\java\\org\\alex\\tankGame\\record.txt";

    private static Vector<EnemyTank> enemyTanks=null;

    private static Vector<Node> nodes=new Vector<>();
    public static void keepRecord(){
        try {
            bw=new BufferedWriter(new FileWriter(recordPath));
            bw.write(killEnemyNums+"\r\n");
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.isLive){
                    String enemyTankInfo=enemyTank.getX()+" "+enemyTank.getY()+" "+enemyTank.getDirect()+" "+"\r\n";
                    bw.write(enemyTankInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (bw!=null)
                bw.close();
                if (fw!=null)
                fw.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    public static int getKillEnemyNums() {
        return killEnemyNums;
    }

    public static void setKillEnemyNums(int killEnemyNums) {
        Recorder.killEnemyNums = killEnemyNums;
    }

    public static void addKillEnemyNums(){
        killEnemyNums++;
    }


    public static Vector<Node> getNodes() {

        try {
            br=new BufferedReader(new FileReader(recordPath));
            String killEnemyNumsStr=br.readLine();
            killEnemyNums=Integer.parseInt(killEnemyNumsStr);
            String enemyTankInfo=null;
            Node node = null;
            while ((enemyTankInfo=br.readLine())!=null){
                String[] split = enemyTankInfo.split(" ");
                node=new Node(Integer.parseInt(split[0]),Integer.parseInt(split[1]),Integer.parseInt(split[2]));
                nodes.add(node);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally
        {
            try {
                if (br!=null)
                br.close();
                if (fr!=null)
                fr.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return nodes;
    }

    public static String getRecordFile() {
        return recordPath;
    }
}
