/**
 * El primer proceso se encarga de cargar las imágenes en un contenedor, en su estado
 * original. Existen dos hilos que ejecutan este proceso, demorando un tiempo aleatorio en
 * ms para obtener la imágen y depositarla en el contenedor. Ambos hilos dejan las imágenes
 * en el mismo contenedor.
 */
public class Creator implements Runnable {
    private int name;
    private Container container;

    public Creator(Container container, int name) {
        this.name = name;
        this.container = container;
    }

    @Override
    public void run() {
        while (true) {
            if(this.container.sizeOf()>=100) break;
            try {
                Thread.sleep((long) ((Math.random() * 19) + 16));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Img image = new Img();
            this.container.addImage(image);
        }
    }
}
