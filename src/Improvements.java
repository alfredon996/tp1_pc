/**
 * El segundo proceso tiene por objetivo mejorar la iluminación de cada imágen. En este
 * caso tres hilos ejecutan este proceso, demorando un tiempo aleatorio en ms para mejorar
 * la iluminación de cada imágen. Cada hilo debe tomar una imágen aleatoria del contenedor
 * por vez, y no puede tomar más de una vez la misma imágen. En el tiempo que demora en
 * mejorar la imágen no debe bloquear otros hilos que quieran tomar otras imágenes para
 * mejorar. Cada imagen debe ser mejorada por todos los hilos (los 3 del presente proceso) para
 * que el siguiente proceso pueda tomarla.
 */
public class Improvements implements Runnable {
    private int name;
    private Container container;
    private int improvedCount;
    private boolean[] reviews;

    public Improvements(Container container, int name) {
        this.name = name;
        this.container = container;
        /*
            Llevo la cuenta de cuantas imagenes mejora el hilo.
         */
        this.improvedCount = 0;
        this.reviews = new boolean[100];
    }

    @Override
    public void run() {
        while (true) {
            if(this.improvedCount >= 100){
                break;
            }
            Img image = this.container.getRandomImage();
            if (image != null) {
                /*
                    Pregunta si la imagen no fue revisada por este Improvement.
                 */
                if (!this.reviews[image.getName()]) {
                    try {
                        Thread.sleep((long) (Math.random() + 100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    image.setUpgrades();
                    this.reviews[image.getName()] = true;
                    this.improvedCount++;
                }
            }
        }
    }
}
