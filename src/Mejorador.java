public class Mejorador extends Proceso implements Runnable {
    private int mejoradas;
    private final boolean[] ImagenesRevisadas;

    public Mejorador(Contenedor ContenedorInicial, int nombre) {
        super(ContenedorInicial, nombre);
        /*
            El proceso lleva la cuenta de imagenes que reviso
            para no volver a mejorar la misma.
         */
        this.mejoradas = 0;
        this.ImagenesRevisadas = new boolean[100];
    }

    @Override
    public void run() {
        Imagen imagen;
        while (this.mejoradas < 100) {
            imagen = this.ContenedorInicial.getImagen();
            if (imagen != null) {
                /*
                    Preguntamos si no reviso esta imagen anteriormente.
                 */
                if (!this.ImagenesRevisadas[imagen.getName()]) {
                    try {
                        Thread.sleep((long) ((Math.random() * 10) + 15) );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    /*
                        Mejoramos la imagen
                        Aumentamos la cuenta global del contenedor
                        Seteamos que fue revisada por este hilo
                        Aumentamos la cuenta interna del hilo
                     */
                    imagen.setImprovements();
                    this.ContenedorInicial.setImagenMejorada();
                    this.ImagenesRevisadas[imagen.getName()] = true;
                    this.mejoradas++;
                }
            }
        }
    }
}