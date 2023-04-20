public class Moving implements Runnable {

    private int name;
    private Container container;
    private Container containerFinal;

    public Moving(Container container, int name) {
        this.name = name;
        this.container = container;
        this.containerFinal = new Container();
    }

    @Override
    public void run() {
        Img image = container.getRandomImage();
        if (image != null && image.getUpgrades() == 3 && image.isResized()) {

            try {
                Thread.sleep((long) (Math.random()) + 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            container.removeImage();
            containerFinal.addImage(image);
        }
    }

    public int getName() {
        return name;
    }

    public Container getContainer() {
        return container;
    }

    public Container getConteinerFinal() {
        return containerFinal;
    }

}
