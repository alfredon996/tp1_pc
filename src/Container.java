import java.util.ArrayList;
import java.util.List;

public class Container {
    private final List<Img> images;

    public Container() {
        images = new ArrayList<>();
    }

    public synchronized void addImage(Img image) {
        images.add(image);
    }

    public synchronized Img getRandomImage() {
        if (images.isEmpty()) {
            return null;
        }

        int index = (int) (Math.random() * images.size());
        return images.remove(index);
    }

    public synchronized boolean removeImage(Img image) {
        return images.remove(image);
    }

    public synchronized int size() {
        return images.size();
    }

    public int getInsertedCount() {
        return null;
    }

    public String getImprovedCount() {
        return null;
    }

    public String getResizedCount() {
        return null;
    }

    public String getMovedCount() {
        return null;
    }
}