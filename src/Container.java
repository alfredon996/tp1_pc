import java.util.ArrayList;
import java.util.List;

public class Container {
    private final List<Img> images;
    private int InsertedCount;
    private int ImprovedCount;
    private int ResizedCount;
    private int MovedCount;

    public Container() {
        images = new ArrayList<>();
        InsertedCount = 0;
        ImprovedCount = 0;
        ResizedCount = 0;
        MovedCount = 0;
    }

    public synchronized void addImage(Img image) {
        images.add(image);
        imageInserted();
    }

    public synchronized Img getRandomImage() {
        if (images.isEmpty()) {
            return null;
        }

        imagenTaken();
        int index = (int) (Math.random() * images.size());
        return images.remove(index);
    }

    public synchronized boolean removeImage() {
        imageMoved();
    }

    public synchronized void imageInserted() {
        InsertedCount += 1;
    }

    public synchronized void imagenTaken() {
        InsertedCount -= 1;
    }

    public synchronized void imageImproved() {
        ImprovedCount += 1;
    }

    public synchronized void imageResized() {
        ResizedCount += 1;
    }

    public synchronized void imageMoved() {
        MovedCount += 1;
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
    
    public int size() {
        return images.size();
    }
}