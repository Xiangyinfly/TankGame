package TankGame;

public class Bomb {
    private int x;
    private int y;
    private int health = 9;
    private boolean life = true;

    public void healthReduction(){
        if (health > 0){
            health --;
        }else {
            life = false;
        }
    }

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isLife() {
        return life;
    }

    public void setLife(boolean life) {
        this.life = life;
    }
}
