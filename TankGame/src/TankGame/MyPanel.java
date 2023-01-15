package TankGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener,Runnable {
    //定义坦克
    MyTank myTank= null;

    Vector<EnemyTank> enemyTanks = new Vector<>();
    Vector<Bomb> bombs = new Vector<>();
    Vector<Node> nodes = new Vector<>();
    int enemyTankSize = 3;
    //定义爆炸图片
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;



    public MyPanel(String key) {
        //恢复记录数据
        nodes = Recorder.getRecord();

        //同步Record.enemyTanks和MyPanel.enemyTanks
        Recorder.setEnemyTanks(enemyTanks);

        //初始化我方坦克
        myTank = new MyTank(700,500);
        myTank.setSpeed(6);

        switch (key){
            case "1":
                for (int i = 0; i < enemyTankSize; i++) {
                    EnemyTank enemyTank = new EnemyTank((50 * (i + 1)), 0,2);
                    enemyTank.setEnemyTanks(enemyTanks);//同步EnemyTank.enemyTanks和MyPanel.enemyTanks
                    new Thread(enemyTank).start();
                    Shot enemyShot = new Shot(enemyTank.getX() + 20,enemyTank.getY() + 60,enemyTank.getDirect());
                    enemyTank.enemyShots.add(enemyShot);
                    new Thread(enemyShot).start();
                    enemyTanks.add(enemyTank);
                }
                Recorder.setHitEnemyTankNum(0);
                break;
            case "2":
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                    EnemyTank enemyTank = new EnemyTank(node.getX(),node.getY(),node.getDirect());
                    enemyTank.setEnemyTanks(enemyTanks);//同步EnemyTank.enemyTanks和MyPanel.enemyTanks
                    new Thread(enemyTank).start();
                    Shot enemyShot = new Shot(enemyTank.getX() + 20,enemyTank.getY() + 60,enemyTank.getDirect());
                    enemyTank.enemyShots.add(enemyShot);
                    new Thread(enemyShot).start();
                    enemyTanks.add(enemyTank);
                }
                break;
        }

        //初始化炸弹图片
        image1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("bomb_3.gif"));

        //播放音乐
        new PlayMusic("music.wav").start();
    }

    public void showInfo(Graphics g){
        g.setColor(Color.BLACK);
        Font font = new Font("宋体",Font.BOLD,25);
        g.setFont(font);
        g.drawString("您累计击毁敌方坦克",1020,30);
        drawTank(1020,60,g,0,1);
        g.setColor(Color.BLACK);
        g.drawString(Recorder.getHitEnemyTankNum() + "",1080,100);
    }

    public void showMyTank(Graphics g){
        if (myTank != null && myTank.isLife()) {
            drawTank(myTank.getX(), myTank.getY(), g, myTank.getDirect(), 0);
        }
    }

    public void showMyShot(Graphics g){
        for (int i = 0;i < myTank.myShots.size();i ++) {
            Shot shot = myTank.myShots.get(i);
            if (shot != null && shot.isLife()) {
                g.draw3DRect(shot.getX(),shot.getY(), 1, 1, false);
            }else {
                myTank.myShots.remove(shot);
            }
        }
    }

    public void showEnemyTankAndShot(Graphics g){
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.isLife()) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
                for (int j = 0; j < enemyTank.enemyShots.size(); j++) {
                    Shot enemyShot = enemyTank.enemyShots.get(j);
                    if (enemyShot.isLife()) {
                        g.draw3DRect(enemyShot.getX(), enemyShot.getY(), 1, 1, false);
                    } else {
                        enemyTank.enemyShots.remove(enemyShot);
                    }
                }
            }
        }
    }

    public void showBomb(Graphics g){
        if (bombs != null){
            for (int i = 0;i < bombs.size();i ++){
                //解决第一次爆炸不显示？？
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                Bomb bomb = bombs.get(i);

                if (bomb.getHealth() > 6){
                    g.drawImage(image1,bomb.getX(),bomb.getY(),60,60,this);
                }else if (bomb.getHealth() > 3){
                    g.drawImage(image2,bomb.getX(),bomb.getY(),60,60,this);
                }else {
                    g.drawImage(image3,bomb.getX(),bomb.getY(),60,60,this);
                }
                bomb.healthReduction();

                if (bomb.getHealth() == 0){
                    bombs.remove(bomb);
                }
            }
        }
    }

    //画出界面元素
    public void paint(Graphics g) {
        super.paint(g);
        //画出作战界面
        g.fillRect(0, 0, 1000, 750);
        //画出边栏内容
        showInfo(g);
        //画出自己的坦克
        showMyTank(g);
        //画出我方炮弹
        showMyShot(g);
        //画出敌方坦克和炮弹(为什么不能是增强for?)
        showEnemyTankAndShot(g);
        //画出爆炸效果
        showBomb(g);
    }

    public void drawTank(int x,int y,Graphics g,int direct,int type) {
        switch (type) {
            case 0:
                g.setColor(Color.cyan);//自己的坦克
                break;
            case 1:
                g.setColor(Color.yellow);//敌人的坦克
                break;
        }

        switch (direct) {
            case 0: //表示向上
                g.fill3DRect(x, y, 10, 60, false);//画出坦克左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//画出坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//画出坦克盖子
                g.fillOval(x + 10, y + 20, 20, 20);//画出圆形盖子
                g.drawLine(x + 20, y + 30, x + 20, y);//画出炮筒
                break;
            case 1: //表示向右
                g.fill3DRect(x - 10, y + 10, 60, 10, false);//画出坦克上边轮子
                g.fill3DRect(x - 10, y + 40, 60, 10, false);//画出坦克下边轮子
                g.fill3DRect(x, y + 20, 40, 20, false);//画出坦克盖子
                g.fillOval(x + 10, y + 20, 20, 20);//画出圆形盖子
                g.drawLine(x + 20, y + 30, x + 50, y + 30);//画出炮筒
                break;
            case 2: //表示向下
                g.fill3DRect(x, y, 10, 60, false);//画出坦克左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//画出坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//画出坦克盖子
                g.fillOval(x + 10, y + 20, 20, 20);//画出圆形盖子
                g.drawLine(x + 20, y + 30, x + 20, y + 60);//画出炮筒
                break;
            case 3: //表示向左
                g.fill3DRect(x - 10, y + 10, 60, 10, false);//画出坦克上边轮子
                g.fill3DRect(x - 10, y + 40, 60, 10, false);//画出坦克下边轮子
                g.fill3DRect(x, y + 20, 40, 20, false);//画出坦克盖子
                g.fillOval(x + 10, y + 20, 20, 20);//画出圆形盖子
                g.drawLine(x + 20, y + 30, x - 10, y + 30);//画出炮筒
                break;
            default:
                System.out.println("暂时没有处理");
        }
    }

    //判断是否击中敌人坦克
    public void hitTank(Shot shot,Tank tank) {
        switch (tank.getDirect()) {
            case 0:
            case 2:
                if (shot.getX() > tank.getX() && shot.getX() < tank.getX() + 40 &&
                        shot.getY() > tank. getY() && shot.getY() < tank. getY() + 60){
                    shot.setLife(false);
                    tank.setLife(false);
                    //击毁敌人坦克数加一
                    if (tank instanceof EnemyTank){
                        Recorder.addHitEnemyTankNum();
                    }
                    enemyTanks.remove(tank);

                    Bomb bomb = new Bomb(tank.getX(),tank.getY());
                    bombs.add(bomb);
                }
                break;
            case 1:
            case 3:
                if (shot.getX() > tank.getX() && shot.getX() < tank.getX() + 60 &&
                        shot.getY() > tank. getY() && shot.getY() < tank. getY() + 40){
                    shot.setLife(false);
                    tank.setLife(false);
                    //击毁敌人坦克数加一
                    if (tank instanceof EnemyTank){
                        Recorder.addHitEnemyTankNum();
                    }
                    enemyTanks.remove(tank);

                    Bomb bomb = new Bomb(tank.getX(),tank.getY());
                    bombs.add(bomb);
                }
                break;
        }
    }

    public void hitMyTank(){
        for (int i = 0;i < enemyTanks.size();i ++){
            EnemyTank enemyTank = enemyTanks.get(i);
            for (int j = 0;j < enemyTank.enemyShots.size();j ++){
                Shot enemyShot = enemyTank.enemyShots.get(j);
                if (enemyShot.isLife() && myTank.isLife()) {
                    hitTank(enemyShot, myTank);
                }
            }
        }
    }

    public void hitEnemyTank(){
        for (int i = 0;i <myTank.myShots.size();i ++) {
            Shot myShot = myTank.myShots.get(i);
            if (myShot != null && myShot.isLife()) {
                //不知道击中的哪个坦克，因此遍历enemyTanks
                for (int j = 0; j < enemyTanks.size(); j ++) {
                    EnemyTank enemyTank = enemyTanks.get(j);
                    hitTank(myTank.shot, enemyTank);
                }
            }
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //控制坦克移动
        if(e.getKeyCode() == KeyEvent.VK_W){
            myTank.setDirect(0);
            if (myTank.getY() >= 0) {
                myTank.moveUp();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            myTank.setDirect(1);
            if (myTank.getX() + 60 <= 1007) {
                myTank.moveRight();
            }
        }else if (e.getKeyCode() == KeyEvent.VK_S) {
            myTank.setDirect(2);
            if (myTank.getY() + 60 <= 747) {
                myTank.moveDown();
            }
        }else if (e.getKeyCode() == KeyEvent.VK_A) {
            myTank.setDirect(3);
            if (myTank.getX() >= 15) {
                myTank.moveLeft();
            }
        }
        //控制坦克发射炮弹
        if (e.getKeyCode() == KeyEvent.VK_J){
            myTank.attack();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    //界面重绘作为一个线程，实现不断运动
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //不断判断子弹是否存活
            hitMyTank();
            hitEnemyTank();

            this.repaint();
        }
    }
}
