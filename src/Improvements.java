public class Improvements implements Runnable {

    private int name;
    private Container container;

    public Improvements(Container container,int name){
        this.name = name;
        this.container = container;
    }

    @Override
    public void run() {

        Img image = container.getRandomImage();
        if (image != null){
            if(image.getUpgrades() != 3 && !image.getReview(name)) {

                try{
                    Thread.sleep((long)(Math.random()) + 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
    
                image.setUpgrades(name);
                container.imageImproved();
                container.addImage(image);
            }
        }
    }

    public int getName() {
        return name;
    }

    public Container getContainer() {
        return container;
    }
}
