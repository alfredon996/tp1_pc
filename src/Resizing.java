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
            Img image = container.getRandomImage();
            if (image != null && image.getUpgrades() == 3 && !image.isResized()) {

                try {
                    Thread.sleep((long) (Math.random()) + 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                image.setResized();
                container.imageResized();
                container.addImage(image);
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
