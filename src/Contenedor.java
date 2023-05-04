import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Contenedor {
    private final List<Imagen> imagenes;
    /*
        Variables para contabilizar cuantas imagenes dentro del
        contenedor han sido mejoradas y ajustadas en tamaño.
     */
    private int ImagenesIngresadas;
    private int ImagenesMejoradas;
    private int ImagenesAjustadas;
    private int ImagenesEliminadas;

    public Contenedor() {
        this.imagenes = new ArrayList<>();
        this.ImagenesIngresadas = 0;
        this.ImagenesMejoradas = 0;
        this.ImagenesAjustadas = 0;
        this.ImagenesEliminadas = 0;
    }

    public synchronized void agregarImagen(Imagen imagen) {
        if (this.ImagenesIngresadas < 100) {
            /*
                El contenedor le asigna un nombre a la imagen al ser ingresada
             */
            if (imagen.getName() == -1) imagen.setName(this.imagenes.size());
            /*
                Si pudo ingresar la imagen correctamente,
                se aumenta la cuenta de imagenes en el contenedor
            */
            this.ImagenesIngresadas += 1;
            this.imagenes.add(imagen);
        }
    }

    int i = 0;
    Imagen imagen;
    public synchronized Imagen getImagen(){
        if (!this.imagenes.isEmpty()) {
            try {
                i = (int) (Math.random() * (this.ImagenesIngresadas));
                for(Imagen imagen:this.imagenes){
                    if(imagen.getName()==i){
                        return this.imagenes.get(i);
                    }
                }
            } catch (IndexOutOfBoundsException ignored) {
            }
        }
        return null;
    }

    public synchronized void eliminarImagen(Imagen imagen) {
        this.imagenes.remove(imagenes);
    }

    public List<Imagen> getList() {
        return imagenes;
    }

    /*
        Metodos estadisticos
     */
    public synchronized void setImagenMejorada() {
        this.ImagenesMejoradas += 1;
    }

    public synchronized void setImagenAjustada() {
        this.ImagenesAjustadas += 1;
    }

    public void setImagenEliminada() {
        this.ImagenesEliminadas += 1;
    }

    public int getImagenesIngresadas() {
        return ImagenesIngresadas;
    }

    public int getImagenesMejoradas() {
        return ImagenesMejoradas;
    }

    public int getImagenesAjustadas() {
        return ImagenesAjustadas;
    }

    public int getImagenesEliminadas() {
        return ImagenesEliminadas;
    }

}