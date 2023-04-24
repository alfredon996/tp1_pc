public class Improvements implements Runnable {

    private int name;
    private Container container;

    public Improvements(Container container, int name) {
        this.name = name;
        this.container = container;
    }

    @Override
    public void run() {
        while (true) {
            if(this.container.getImprovedCount()>=100){
                break;
            }
            Img image = this.container.getRandomImage();
            if (image != null) {
                if (image.getUpgrades() != 3 && !image.getReview(name) && image.getLock().tryLock()) {
                    try {
                        Thread.sleep((long) (Math.random() + 10));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    image.setUpgrades(name);
                    if (image.getUpgrades() == 3) {
                        this.container.imageImproved();
                    }
                    image.getLock().unlock();
                }
            }
        }
    }
}
