package TankGame;

import java.io.*;
import java.util.Vector;

public class Recorder {
    private static int hitEnemyTankNum = 0;
    private static Vector<EnemyTank> enemyTanks = null;
    private static Vector<Node> nodes = new Vector<>();
    private static BufferedWriter bufferedWriter = null;
    private static BufferedReader bufferedReader = null;
    private static String recordFile = "data.txt";

    public static Vector<Node> getRecord(){
        try {
            bufferedReader = new BufferedReader(new FileReader(recordFile));
            hitEnemyTankNum = Integer.parseInt(bufferedReader.readLine());
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                String[] XYDirect = line.split(" ");
                Node node = new Node(Integer.parseInt(XYDirect[0]),
                        Integer.parseInt(XYDirect[1]),
                        Integer.parseInt(XYDirect[2]));
                nodes.add(node);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return nodes;
    }

    public static void saveRecord(){
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(recordFile));
            bufferedWriter.write(hitEnemyTankNum + "\r\n");

            for (int i = 0;i < enemyTanks.size();i ++){
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.isLife()){
                    String record = enemyTank.getX() +
                            " " + enemyTank.getY() +
                            " " + enemyTank.getDirect();
                    bufferedWriter.write(record + "\r\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bufferedWriter != null){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void addHitEnemyTankNum(){
        hitEnemyTankNum ++;
    }

    public static int getHitEnemyTankNum() {
        return hitEnemyTankNum;
    }

    public static void setHitEnemyTankNum(int hitEnemyTankNum) {
        Recorder.hitEnemyTankNum = hitEnemyTankNum;
    }

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }
}
