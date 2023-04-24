import java.util.Date;
public class Log implements Runnable{
    private final Thread[] creatorThreads;
    private final Thread[] improvementThreads;
    private final Thread[] resizingThreads;
    private final Thread[] movingThreads;
    private final Container container;
    private final Container containerFinal;
    public Log(Thread[] creatorThreads,Thread[] improvementThreads,Thread[] resizingThreads,Thread[] movingThreads,Container container,Container containerFinal){
        this.creatorThreads = creatorThreads;
        this.improvementThreads = improvementThreads;
        this.resizingThreads = resizingThreads;
        this.movingThreads = movingThreads;
        this.container = container;
        this.containerFinal = containerFinal;
    }
    @Override
    public void run(){
        boolean flag = true;
        while (flag) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Cantidad de imágenes insertadas en el contenedor: " + container.getInsertedCount());
            System.out.println("Cantidad de imágenes mejoradas completamente: " + container.getImprovedCount());
            System.out.println("Cantidad de imágenes ajustadas: " + container.getResizedCount());
            System.out.println("Cantidad de imágenes que han finalizado el último proceso: " + container.getMovedCount());
            System.out.println("Cantidad de imágenes insertadas en el contenedor final: " + containerFinal.getInsertedCount());

            for (Thread thread : creatorThreads) {
                System.out.println("Creator " + thread.getName() + " is " + thread.getState());
            }
            for (Thread thread : improvementThreads) {
                System.out.println("Improvements " + thread.getName() + " is " + thread.getState());
            }
            for (Thread thread : resizingThreads) {
                System.out.println("Resizing " + thread.getName() + " is " + thread.getState());
            }
            for (Thread thread : movingThreads) {
                System.out.println("Moving " + thread.getName() + " is " + thread.getState());
            }

            flag = false;
            for (Thread thread : creatorThreads) {
                if (thread.isAlive()) {
                    flag = true;
                    break;
                }
            }
            for (Thread thread : improvementThreads) {
                if (thread.isAlive()) {
                    flag = true;
                    break;
                }
            }
            for (Thread thread : resizingThreads) {
                if (thread.isAlive()) {
                    flag = true;
                    break;
                }
            }
            for (Thread thread : movingThreads) {
                if (thread.isAlive()) {
                    flag = true;
                    break;
                }
            }
        }
        System.out.printf("Se finalizo el programa: %s\n", new Date());
    }
}
