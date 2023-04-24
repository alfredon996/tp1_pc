import java.util.concurrent.locks.ReentrantLock;

public class Img{
    private final ReentrantLock lock;
    private int upgrades;
    private boolean resize;
    private boolean[] reviews;
    public Img(){
        this.lock = new ReentrantLock();
        this.upgrades = 0;
        this.resize = false;
        this.reviews = new boolean[3];
    }

    public int getUpgrades() {
        return upgrades;
    }
    public void setUpgrades(int name) {
        this.upgrades += 1;
        this.reviews[name] = true;
    }
    public boolean isResized() {
        return resize;
    }
    public void setResized() {
        this.resize = true;
    }
    public boolean getReview(int name) {
        return reviews[name];
    }
    public ReentrantLock getLock() {
        return lock;
    }
}
