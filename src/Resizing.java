public class Resizing implements Runnable {

    private int name;
    private Container container;

    /*
        Al finalizar la ejecución es necesario verificar cuantas imágenes ajustó cada hilo del
        tercer proceso.
    */
    private int adjusted;

    public Resizing(Container container, int name) {
        this.name = name;
        this.container = container;
        this.adjusted = 0;
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
                        Thread.sleep((long) (Math.random() + 100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    image.setResized();
                    setAdjusted();
                    this.container.imageResized();
                    image.getLock().unlock();
                }
            }
        }
        System.out.println("Resizing " + this.name + " ajusto " + this.adjusted + " imagenes.");
    }

    public void setAdjusted(){
        adjusted++;
    }
}
