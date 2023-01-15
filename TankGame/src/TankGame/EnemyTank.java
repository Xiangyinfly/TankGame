package TankGame;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {
    Vector<Shot> enemyShots = new Vector<>();
    Vector<EnemyTank> enemyTanks = new Vector<>();

    //防止坦克重叠方法，但是转弯时会穿模
    public boolean isTouchEnemyTank(){
        switch (getDirect()){
            case 0:
                for (int i = 0;i < enemyTanks.size();i ++){
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this){
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2){
                            if (getX() >= enemyTank.getX()
                                    && getX() <= enemyTank.getX() + 40
                                    && getY() <= enemyTank.getY() + 60
                                    && getY() >= enemyTank.getY()) {
                                return true;
                            }
                            if (getX() + 40 >= enemyTank.getX()
                                    && getX() + 40 <= enemyTank.getX() + 40
                                    && getY() <= enemyTank.getY() + 60
                                    && getY() >= enemyTank.getY()) {
                                return true;
                            }
                        }
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3){
                            if (getX() >= enemyTank.getX()
                                    && getX() <= enemyTank.getX() + 60
                                    && getY() <= enemyTank.getY() + 40
                                    && getY() >= enemyTank.getY()) {
                                return true;
                            }
                            if (getX() + 40 >= enemyTank.getX()
                                    && getX() + 40 <= enemyTank.getX() + 60
                                    && getY() <= enemyTank.getY() + 40
                                    && getY() >= enemyTank.getY()) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1:
                for (int i = 0;i < enemyTanks.size();i ++){
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this){
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2){
                            if (getY() >= enemyTank.getY()
                                    && getY() <= enemyTank.getY() + 60
                                    && getX() + 60 >= enemyTank.getX()
                                    && getX() + 60 <= enemyTank.getX() + 40) {
                                return true;
                            }
                            if (getY() + 40 >= enemyTank.getY()
                                    && getY() + 40 <= enemyTank.getY() + 60
                                    && getX() + 60 >= enemyTank.getX()
                                    && getX() + 60 <= enemyTank.getX() + 40) {
                                return true;
                            }
                        }
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3){
                            if (getY() >= enemyTank.getY()
                                    && getY() <= enemyTank.getY() + 40
                                    && getX() + 60 >= enemyTank.getX()
                                    && getX() + 60 <= enemyTank.getX() + 60) {
                                return true;
                            }
                            if (getY() + 40 >= enemyTank.getY()
                                    && getY() + 40 <= enemyTank.getY() + 40
                                    && getX() + 60 >= enemyTank.getX()
                                    && getX() + 60 <= enemyTank.getX() + 60) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0;i < enemyTanks.size();i ++){
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this){
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2){
                            if (getX() >= enemyTank.getX()
                                    && getX() <= enemyTank.getX() + 40
                                    && getY() + 60 <= enemyTank.getY() + 60
                                    && getY() + 60 >= enemyTank.getY()) {
                                return true;
                            }
                            if (getX() + 40 >= enemyTank.getX()
                                    && getX() + 40 <= enemyTank.getX() + 40
                                    && getY() + 60 <= enemyTank.getY() + 60
                                    && getY() + 60 >= enemyTank.getY()) {
                                return true;
                            }
                        }
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3){
                            if (getX() >= enemyTank.getX()
                                    && getX() <= enemyTank.getX() + 60
                                    && getY() + 60 <= enemyTank.getY() + 40
                                    && getY() + 60 >= enemyTank.getY()) {
                                return true;
                            }
                            if (getX() + 40 >= enemyTank.getX()
                                    && getX() + 40 <= enemyTank.getX() + 60
                                    && getY() + 60 <= enemyTank.getY() + 40
                                    && getY() + 60 >= enemyTank.getY()) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3:
                for (int i = 0;i < enemyTanks.size();i ++){
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this){
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2){
                            if (getY() >= enemyTank.getY()
                                    && getY() <= enemyTank.getY() + 60
                                    && getX() >= enemyTank.getX()
                                    && getX() <= enemyTank.getX() + 40) {
                                return true;
                            }
                            if (getY() + 40 >= enemyTank.getY()
                                    && getY() + 40 <= enemyTank.getY() + 60
                                    && getX() >= enemyTank.getX()
                                    && getX() <= enemyTank.getX() + 40) {
                                return true;
                            }
                        }
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3){
                            if (getY() >= enemyTank.getY()
                                    && getY() <= enemyTank.getY() + 40
                                    && getX() >= enemyTank.getX()
                                    && getX() <= enemyTank.getX() + 60) {
                                return true;
                            }
                            if (getY() + 40 >= enemyTank.getY()
                                    && getY() + 40 <= enemyTank.getY() + 40
                                    && getX() >= enemyTank.getX()
                                    && getX() <= enemyTank.getX() + 60) {
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }
    public EnemyTank(int x, int y,int direct) {
        super(x, y);
        super.setDirect(direct);
    }

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    @Override
    public void run() {
        while (isLife()){
            if (isLife() && enemyShots.size() < 3){//控制敌人未销毁炮弹的个数
                Shot shot = null;
                switch (getDirect()){
                    case 0:
                        shot = new Shot(getX() + 20,getY(),0);
                        break;
                    case 1:
                        shot = new Shot(getX() + 50,getY() + 30,1);
                        break;
                    case 2:
                        shot = new Shot(getX() + 20,getY() + 60,2);
                        break;
                    case 3:
                        shot = new Shot(getX() - 10,getY() + 30,3);
                        break;
                }
                enemyShots.add(shot);
                new Thread(shot).start();
            }
            //控制敌人坦克随机移动
            switch (getDirect()){
                case 0:
                    for (int i = 1; i <= (int) (Math.random() * 500) + 30; i++) {
                        moveUp();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        //此处不将判断条件写在父类，是因为如果写在父类，if条件不成立也会进行for循环
                        if (getY() < 0){
                            break;
                        }

                        if (isTouchEnemyTank()){
                            break;
                        }
                    }
                    break;
                case 1:
                    for (int i = 1; i <= (int) (Math.random() * 500) + 30; i++) {
                        moveRight();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        if (getX() + 60 > 1007){
                            break;
                        }

                        if (isTouchEnemyTank()){
                            break;
                        }
                    }
                    break;
                case 2:
                    for (int i = 1; i <= (int) (Math.random() * 500) + 30; i++) {
                        moveDown();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        if (getY() + 60 > 747){
                            break;
                        }

                        if (isTouchEnemyTank()){
                            break;
                        }
                    }
                    break;
                case 3:
                    for (int i = 1; i <= (int) (Math.random() * 500) + 30; i++) {
                        moveLeft();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        if (getX() < 15){
                            break;
                        }

                        if (isTouchEnemyTank()){
                            break;
                        }
                    }
                    break;
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //随机改变方向
            setDirect((int)(Math.random() * 4));
        }
    }

}
