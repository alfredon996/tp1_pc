public class Creador extends Proceso implements Runnable {
    public Creador(Contenedor ContenedorInicial, int nombre) {
        super(ContenedorInicial, nombre);
    }

    @Override
    public void run() {
        while (this.ContenedorInicial.getImagenesIngresadas() < 100) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Imagen i = new Imagen();
            this.ContenedorInicial.agregarImagen(i);
        }
    }
}

