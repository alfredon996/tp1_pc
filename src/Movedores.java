public class Movedores extends Proceso implements Runnable {
    private Contenedor ContenedorFinal;
    /*
        Al finalizar la ejecución es necesario verificar cuantas imágenes movió del
        contenedor inicial hacia el contenedor final, cada hilo del cuarto proceso.
    */
    private int movidasCount;

    public Movedores(Contenedor contenedor, Contenedor ContenedorFinal, int nombre) {
        super(contenedor, nombre);
        this.ContenedorFinal = ContenedorFinal;
        this.movidasCount = 0;
    }

    @Override
    public void run() {
        Imagen imagen;
        while (this.ContenedorInicial.getImagenesEliminadas() < 100) {
            imagen = this.ContenedorInicial.getImagen();
            if (imagen != null) {
                if (imagen.getImprovements() == 3 && imagen.getResize()) {
                    try {
                        Thread.sleep(45);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if (imagen.setCopy(true)) {
                            this.ContenedorFinal.agregarImagen(imagen);
                            this.ContenedorInicial.eliminarImagen(imagen);
                            this.ContenedorInicial.setImagenEliminada();
                            this.movidasCount++;
                        }
                    }
                }
            }
        }
        /*
          Al finalizar la ejecución es necesario verificar cuantas imágenes movió del
          contenedor inicial hacia el contenedor final, cada hilo del cuarto proceso.
         */
        System.out.println("Movedor " + this.nombre + " movio " + this.movidasCount + " imagenes.");
    }

    public Contenedor getContenedorFinal() {
        return ContenedorFinal;
    }
}
