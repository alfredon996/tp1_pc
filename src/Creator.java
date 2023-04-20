public class Creator implements Runnable {

    private int name;
    private Container container;

    public Creator(Container container, int name) {
        this.name = name;
        this.container = container;
    }

    @Override
    public void run() {
        while(container.size()<10){
            try {
                Thread.sleep((long) (Math.random()) + 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    
            Img image = new Img();
            this.container.addImage(image);
        }
    }

    public int getName() {
        return name;
    }

    public Container getContainer() {
        return container;
    }
}
