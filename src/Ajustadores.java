public class Ajustadores extends Proceso implements Runnable {
    private int ajustadasCount;

    public Ajustadores(Contenedor ContenedorInicial, int nombre) {
        super(ContenedorInicial, nombre);
        this.ajustadasCount = 0;
    }

    @Override
    public void run() {
        Imagen imagen;
        while (this.ContenedorInicial.getImagenesAjustadas() < 100) {
            imagen = this.ContenedorInicial.getImagen();
            if (imagen != null) {
                /*
                    Pregunto si la imagen fue actualizada por todos
                    los hilos del proceso 2
                 */
                if (imagen.getImprovements() == 3) {
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    /*
                        Intentamos ajustar el tamaño de la imagen.
                        De ser posible, modificamos los valores
                        tanto globales como internos del hilo.
                     */
                    if (imagen.setResize(true)) {
                        this.ContenedorInicial.setImagenAjustada();
                        this.ajustadasCount++;
                    }
                }
            }
        }
        System.out.println("Ajustador " + this.nombre + " ajusto " + this.ajustadasCount + " imagenes.");
    }
}
