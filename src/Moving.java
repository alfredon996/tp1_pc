public class Moving implements Runnable {

    private String name;
    private Container container;

    public Moving(Container container,String name){
        this.container = container;
        this.name = name;
    }

    @Override
    public void run() {

    }
}
