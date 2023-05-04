import java.util.concurrent.locks.ReentrantLock;

public class Imagen {
    /*
        Nombre de la imagen
        Improvements es un atributo de cada imagen que contabiliza cuantos hilos del proceso 2 la han tomado
        Resize indica si finalizo o no el proceso 3
        Copy funciona igual que el Resize, pero para el proceso 4
     */
    private int name;
    private int improvements;
    private boolean resize;
    private boolean copy;

    public Imagen() {
        this.name = -1;
        this.improvements = 0;
        this.resize = false;
        this.copy = false;
    }

    public void setName(int name) {
        this.name = name;
    }

    public synchronized void setImprovements() {
        this.improvements += 1;
    }

    public synchronized boolean setResize(boolean resize) {
        if (!this.resize) return this.resize = resize;
        return false;
    }

    public synchronized boolean setCopy(boolean copy) {
        if (!this.copy) return this.copy = copy;
        return false;
    }

    public int getName() {
        return name;
    }

    public int getImprovements() {
        return improvements;
    }

    public boolean getResize() {
        return resize;
    }

    public boolean getCopy() {
        return copy;
    }
}
