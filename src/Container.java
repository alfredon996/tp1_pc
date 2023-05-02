import java.util.ArrayList;
import java.util.List;

public class Container {
    private final List<Img> images;
    private int i;
    public Container() {
        this.images = new ArrayList<>();
        this.i = 0;
    }

    public synchronized void addImage(Img image) {
        if (this.images.size() < 100){
            /*
                El contenedor le asigna un nombre a la imagen al ser ingresada
             */
            image.setName(this.images.size());
            this.images.add(image);
        }
    }

    public synchronized Img getRandomImage() {
        if (this.images.isEmpty()) {
            return null;
        }        
        return images.get((int) (Math.random() * this.images.size()));
    }

    public synchronized void removeImage(Img image) {
        this.images.remove(image);
    }
    public int sizeOf(){
        return this.images.size();
    }

    public void imageResized(){
        this.i++;
    }
    public int getI(){
        return this.i;
    }
}