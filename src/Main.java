/**
 * Las demoras del sistema en sus cuatro procesos deben configurarse de tal manera
 * de poder procesar 100 imágenes (desde la inserción en el primer contenedor hasta
 * que son movidas al contenedor final) en un periodo mínimo de 10 segundos y
 * máximo de 20 segundos.
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {

        int CREATOR_THREADS = 2;
        int IMPROVEMENT_THREADS = 3;
        int RESIZING_THREADS = 3;
        int MOVING_THREADS = 2;

        Container container = new Container();
        Container container_final = new Container();

        Thread[] creatorThreads = new Thread[CREATOR_THREADS];
        for (int i = 0; i < creatorThreads.length; i++) {
            creatorThreads[i] = new Thread(new Creator(container,i));
        }
        
        Thread[] improvementThreads = new Thread[IMPROVEMENT_THREADS];
        for (int i = 0; i < improvementThreads.length; i++) {
            improvementThreads[i] = new Thread(new Improvements(container,i));
        }

        Thread[] resizingThreads = new Thread[RESIZING_THREADS];
        for (int i = 0; i < resizingThreads.length; i++) {
            resizingThreads[i] = new Thread(new Resizing(container,i));
        }

        Thread[] movingThreads = new Thread[MOVING_THREADS];
        for (int i = 0; i < movingThreads.length; i++) {
            movingThreads[i] = new Thread(new Moving(container,container_final,i));
        }

        Thread LOG = new Thread(new Log(creatorThreads,improvementThreads,resizingThreads,movingThreads,container,container_final));

        for (Thread thread : creatorThreads) {
            thread.start();
        }
        for (Thread thread : improvementThreads) {
            thread.start();
        }
        for (Thread thread : resizingThreads) {
            thread.start();
        }
        for (Thread thread : movingThreads) {
            thread.start();
        }

        LOG.start();
        LOG.join();
    }
}