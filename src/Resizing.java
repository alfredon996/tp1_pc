/**
 * El tercer proceso debe ajustar las imágenes al tamaño final solicitado. Este proceso tiene
 * que ejecutarse después que la imagen ya fue mejorada. Tres hilos son los encargados de
 * ejecutar este proceso, tomando cada uno una imagen aleatoria del contenedor por vez para
 * ajustarla, y demorando un tiempo aleatorio en ms. En el tiempo que demora en ajustar la
 * imágen no debe bloquear otros hilos que quieran tomar otras imágenes para ajustar. Una
 * imágen solo puede ser ajustada una sola vez.
 */
public class Resizing implements Runnable {

    private int name;
    private Container container;
    /*
        Al finalizar la ejecución es necesario verificar cuantas imágenes ajustó cada hilo del
        tercer proceso.
    */
    private int adjustedCount;

    public Resizing(Container container, int name) {
        this.name = name;
        this.container = container;
        /*
            Llevo la cuenta de cuantas imagenes mejora el hilo
         */
        this.adjustedCount = 0;
    }

    @Override
    public void run() {
        while (true) {
            if(this.container.getI()>=100) break;
            Img image = this.container.getRandomImage();
            if (image != null) {
                /*
                    Pregunto si la imagen fue actualizada por el proceso 2 y si no esta ajustada
                 */
                if (image.isUpgrade() && !image.isResized()) {
                    try {
                        Thread.sleep((long) ((Math.random() + 100)));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    /*
                        El metodo setResized pregunta si el hilo pudo ajustar la imagen.
                        Al haber un metodo sincronizado, puede que otro hilo tomo
                        la misma imagen y entro al bloque primero. Por ende, tenemos en cuenta
                        esa situacion previa antes de aumentar la cuenta.
                     */
                    if(image.setResized()){
                        this.adjustedCount++;
                        this.container.imageResized();
                    }
                    else{
                        System.out.println("Hubo un error al intentar procesar la imagen " + image.getName());
                    }
                }
            }
        }
        /**
         * Al finalizar la ejecución es necesario verificar cuantas imágenes ajustó cada hilo del
         * tercer proceso.
         */
        System.out.println("Resizing " + this.name + " ajusto " + this.adjustedCount + " imagenes.");
    }
}
