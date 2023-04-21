public class Resizing implements Runnable {

    private int name;
    private Container container;

    public Resizing(Container container, int name) {
        this.name = name;
        this.container = container;
    }

    @Override
    public void run() {
        while (true) {
            if(this.container.getResizedCount()>=100){
                break;
            }
            Img image = this.container.getRandomImage();
            if (image != null) {
                if (image.getUpgrades() == 3 && !image.isResized() && image.getLock().tryLock()) {
                    try {
                        Thread.sleep((long) (Math.random() + 10));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    image.setResized();
                    this.container.imageResized();
                    image.getLock().unlock();
                }

            }
        }
    }

    public int getName() {
        return name;
    }

    public Container getContainer() {
        return container;
    }
}
