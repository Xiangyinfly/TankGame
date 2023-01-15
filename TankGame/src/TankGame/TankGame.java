package TankGame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class TankGame extends JFrame {
    MyPanel myPanel = null;
    static Scanner scanner = new Scanner(System.in);
    static boolean loop = true;
    public static void main(String[] args) {
        TankGame tankGame = new TankGame();
    }

    public TankGame() {
        while (loop) {
            System.out.println("--------请选择游戏模式--------");
            System.out.println("1:新游戏" + "\n" + "2:继续上局");
            String key = scanner.next();
            if (key.equals("1") || key.equals("2")) {
                loop = false;

                myPanel = new MyPanel(key);

                Thread thread = new Thread(myPanel);
                thread.start();

                this.add(myPanel);
                this.setSize(1400, 750);
                this.addKeyListener(myPanel);
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setVisible(true);

                //监听关闭窗口行为,调用saveRecord方法
                this.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        Recorder.saveRecord();
                        System.exit(0);
                    }
                });
            } else {
                System.out.println("输入有误，请输入1或2");
            }
        }
    }
}
