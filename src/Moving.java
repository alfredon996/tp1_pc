public class Moving implements Runnable {

    private int name;
    private Container container;
    private Container containerFinal;

    public Moving(Container container,Container containerFinal ,int name) {
        this.name = name;
        this.container = container;
        this.containerFinal = containerFinal;
    }

    @Override
    public void run() {
        while(true){
            if(this.container.getMovedCount()>=100){
                break;
            }
            Img image = container.getRandomImage();
            if (image != null) {
                if(image.getUpgrades() == 3 && image.isResized() && image.getLock().tryLock()){
                    try {
                        Thread.sleep((long) (Math.random() + 10));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.container.removeImage(image);
                    this.containerFinal.addImage(image);
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

    public Container getConteinerFinal() {
        return containerFinal;
    }

}
