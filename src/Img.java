public class Img {
    /**
     * Los objetos imágen tienen un atributo “improvements” el cual registra cuántos hilos
     * han tomado el archivo para mejorarlo (proceso 2).
     * Una imagen no puede pasar a ser ajustada sin antes haber sido mejorada por los 3
     * hilos del segundo proceso.
     */
    private int name;
    private int improvements;
    /*
        resize Indica si la imagen fue ajustada
     */
    private boolean resize;
    /*
        upgrade Indica si la imagen fue actualizada o no por los 3 hilos
     */
    private boolean upgrade;

    public Img() {
        this.improvements = 0;
        this.resize = false;
        this.upgrade = false;
    }
    public void setName(int name){
        this.name = name;
    }
    public int getName(){
        return name;
    }
    public int getImprovements() {
        return improvements;
    }
    public synchronized void setUpgrades() {
        this.improvements++;
        if(this.improvements == 3){
            this.upgrade = true;
        }
    }
    public synchronized boolean setResized() {
        if(this.resize){
            return false;
        }
        this.resize = true;
        return true;
    }
    public boolean isResized() {
        return resize;
    }
    public boolean isUpgrade() {
        return upgrade;
    }
}
