public class Img{

    private int upgrades;
    private boolean resize;
    private boolean[] reviews;

    public Img(){
        this.upgrades = 0;
        this.resize = false;
        this.reviews = new boolean[3];
    }

    public int getUpgrades() {
        return this.upgrades;
    }

    public void setUpgrades(int name) {
        this.upgrades += 1;
        reviews[name] = true;
    }

    public boolean isResized() {
        return this.resize;
    }

    public void setResized() {
        this.resize = true;
    }

    public boolean getReview(int name) {
        return reviews[name];
    }
}
