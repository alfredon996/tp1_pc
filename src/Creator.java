public class Creator implements Runnable {

    private String name;
    private Container container;

    public Creator(Container container,String name){
        this.container = container;
        this.name = name;
    }

    @Override
    public void run() {

    }
}
