import java.lang.Math;
public class Movedores extends Proceso implements Runnable {
    private Contenedor ContenedorFinal;
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
                /*
                    Preguntamos si la imagen ya finalizo el proceso de
                    mejora de iluminacion y de ajuste de tamaño.
                 */
                if (imagen.getImprovements() == 3 && imagen.getResize()) {
                    try {
                        Thread.sleep((long) ((Math.random() * 30) + 30) );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    /*
                        Intentamos copiar la imagen.
                        De ser posible, eliminamos la imagen del primer contenedor,
                        la agregamos al contenedor final y aumentamos las cuentas
                        necesarias.
                     */
                    if (imagen.setCopy(true)) {
                        this.ContenedorFinal.agregarImagen(imagen);
                        this.ContenedorInicial.eliminarImagen(imagen);
                        this.ContenedorInicial.setImagenEliminada();
                        this.movidasCount++;
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
}
