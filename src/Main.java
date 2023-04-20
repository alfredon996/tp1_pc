public class Main {
    public static void main(String[] args) {
        int CREATOR_THREADS = 2;
        int IMPROVEMENT_THREADS = 3;
        int RESIZING_THREADS = 3;
        int MOVING_THREADS = 2;

        Container container = new Container();

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
            movingThreads[i] = new Thread(new Moving(container,i));
        }

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

        boolean flag = true;
        // Print status of each thread
        while (flag) {

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // LOG
            System.out.println("Cantidad de imágenes insertadas en el contenedor: " + container.getInsertedCount());
            System.out.println("Cantidad de imágenes mejoradas completamente: " + container.getImprovedCount());
            System.out.println("Cantidad de imágenes ajustadas: " + container.getResizedCount());
            System.out.println("Cantidad de imágenes que han finalizado el último proceso: " + container.getMovedCount());

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
                if(thread.isAlive()){
                    flag = true;
                }
            }
            for (Thread thread : improvementThreads) {
                if(thread.isAlive()){
                    flag = true;
                }
            }
            for (Thread thread : resizingThreads) {
                if(thread.isAlive()){
                    flag = true;
                }
            }
            for (Thread thread : movingThreads) {
                if(thread.isAlive()){
                    flag = true;
                }
            }
        }
    }
}