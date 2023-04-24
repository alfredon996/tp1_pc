public class Moving implements Runnable {

    private int name;
    private Container container;
    private Container containerFinal;
    /*
        Al finalizar la ejecución es necesario verificar cuantas imágenes movió del
        contenedor inicial hacia el contenedor final, cada hilo del cuarto proceso.
    */
    private int moved;

    public Moving(Container container,Container containerFinal ,int name) {
        this.name = name;
        this.container = container;
        this.containerFinal = containerFinal;
        this.moved = 0;
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
                        Thread.sleep((long) (Math.random() + 100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.container.removeImage(image);
                    this.containerFinal.addImage(image);
                    setMoved();
                    image.getLock().unlock();
                }
            }
        }
        System.out.println("Moving " + this.name + " movio " + this.moved + " imagenes.");
    }

    public void setMoved(){
        this.moved++;
    }
}
