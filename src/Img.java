import java.util.concurrent.locks.ReentrantLock;

public class Img{

    private final ReentrantLock lock;
    private int upgrades;       // Cantidad de mejoras. Maximo 3
    private boolean resize;     // Indica si ya fue ajustada o no
    private boolean[] reviews;  // Array utilizado para saber cual de los mejoradores reviso la imagen

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
