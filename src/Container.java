import java.util.ArrayList;
import java.util.List;

public class Container {
    private final List<Img> images;
    /*
        InsertedCount: Imagenes agregadas al contenedor
        ImprovedCount: Imagenes mejoradas por los Improvements
        ResizedCount: Imagenes ajustadas
        MovedCount: Imagenes tomadas y borradas del contenedor y pasadas a otro
     */
    private int InsertedCount;
    private int ImprovedCount;
    private int ResizedCount;
    private int MovedCount;

    public Container() {
        this.images = new ArrayList<>();
        this.InsertedCount = 0;
        this.ImprovedCount = 0;
        this.ResizedCount = 0;
        this.MovedCount = 0;
    }

    public synchronized void addImage(Img image) {
        if (this.InsertedCount < 100){
            this.images.add(image);
            imageInserted();
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
        imageMoved();
    }   

    public void imageInserted() {
        this.InsertedCount++;
    }

    public synchronized void imageImproved() {
        this.ImprovedCount++;
    }

    public synchronized void imageResized() {
        this.ResizedCount++;
    }

    public void imageMoved() {
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
}