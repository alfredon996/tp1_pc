/**
 * El cuarto proceso toma las imágenes ya mejoradas y ajustadas, les hace una copia en el
 * contenedor final, y luego las elimina del contenedor inicial. Dos hilos ejecutan este proceso,
 * y demoran un tiempo en ms en realizar su trabajo. Cada hilo toma de a una imágen por vez
 * de manera aleatoria.
 */
public class Moving implements Runnable {
    private int name;
    private Container container;
    private Container containerFinal;
    /*
        Al finalizar la ejecución es necesario verificar cuantas imágenes movió del
        contenedor inicial hacia el contenedor final, cada hilo del cuarto proceso.
    */
    private int movedCount;

    public Moving(Container container,Container containerFinal ,int name) {
        this.name = name;
        this.container = container;
        this.containerFinal = containerFinal;
        this.movedCount = 0;
    }

    @Override
    public void run() {
        while(true){
            if(this.containerFinal.sizeOf()>=100) break;
            Img image = container.getRandomImage();
            if (image != null) {
                /*
                Si esta ajustada, quiere decir que finalizo los procesos previos
                Por ende, ya la puedo mover al otro contenedor
                 */
                if(image.isResized()){
                    try {
                        Thread.sleep((long) ((Math.random() + 100)));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.container.removeImage(image);
                    this.containerFinal.addImage(image);
                    this.movedCount++;
                }
            }
        }
        /**
         * Al finalizar la ejecución es necesario verificar cuantas imágenes movió del
         * contenedor inicial hacia el contenedor final, cada hilo del cuarto proceso.
         */
        System.out.println("Moving " + this.name + " movio " + this.movedCount + " imagenes.");
    }
}
