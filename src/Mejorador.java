public class Mejorador extends Proceso implements Runnable {
    private int mejoradasCount;
    private final boolean[] ImagenesRevisadas;

    public Mejorador(Contenedor ContenedorInicial, int nombre) {
        super(ContenedorInicial, nombre);
        this.mejoradasCount = 0;
        this.ImagenesRevisadas = new boolean[100];
    }

    @Override
    public void run() {
        Imagen imagen;
        while (this.mejoradasCount < 100) {
            imagen = this.ContenedorInicial.getImagen();
            if (imagen != null) {
                if (!this.ImagenesRevisadas[imagen.getName()]) {
                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        imagen.setImprovements();
                        this.ContenedorInicial.setImagenMejorada();
                        this.ImagenesRevisadas[imagen.getName()] = true;
                        this.mejoradasCount++;
                    }
                }
            }
        }
    }
}