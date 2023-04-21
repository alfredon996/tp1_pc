import java.util.ArrayList;
import java.util.List;

public class Container {
    private final List<Img> images;
    private int InsertedCount;  // Cantidad de imagenes agregadas al contenedor
    private int ImprovedCount;  // Cantidad de imagenes mejoradas en el contenedor
    private int ResizedCount;   // Cantidad de imagenes ajustadas en el contenedor
    private int MovedCount;     // Cantidad de imagenes movidas de contenedor

    public Container() {
        this.images = new ArrayList<>();
        this.InsertedCount = 0;
        this.ImprovedCount = 0;
        this.ResizedCount = 0;
        this.MovedCount = 0;
    }

    public synchronized void addImage(Img image) {
        this.images.add(image);
        imageInserted();
    }

    public synchronized Img getRandomImage() {
        if (images.isEmpty()) {
            return null;
        }
        return images.get((int) (Math.random() * this.images.size()));
    }

    public synchronized void imageInserted() {
        this.InsertedCount++;
    }

    public synchronized void imageImproved() {
        this.ImprovedCount++;
    }

    public synchronized void imageResized() {
        this.ResizedCount++;
    }

    public synchronized void imageMoved() {
        this.MovedCount++;
    }

    public int getInsertedCount() {
        return InsertedCount;
    }
    
    public int getImprovedCount() {
        return ImprovedCount;
    }

    public int getResizedCount() {
        return ResizedCount;
    }

    public int getMovedCount() {
        return MovedCount;
    }

    public synchronized void removeImage() {
    }
}