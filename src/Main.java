public class Main {
    public static void main(String[] args) throws InterruptedException {

        int CREADORES_THREADS = 2;
        int MEJORADORES_THREADS = 3;
        int AJUSTADORES_THREADS = 3;
        int MOVEDORES_THREADS = 2;

        Contenedor ContenedorInicial = new Contenedor();
        Contenedor ContenedorFinal = new Contenedor();

        Thread[] creadoresThreads = new Thread[CREADORES_THREADS];
        for (int i = 0; i < creadoresThreads.length; i++) {
            creadoresThreads[i] = new Thread(new Creador(ContenedorInicial, i));
        }

        Thread[] mejoradoresThreads = new Thread[MEJORADORES_THREADS];
        for (int i = 0; i < mejoradoresThreads.length; i++) {
            mejoradoresThreads[i] = new Thread(new Mejorador(ContenedorInicial, i));
        }

        Thread[] ajustadoresThreads = new Thread[AJUSTADORES_THREADS];
        for (int i = 0; i < ajustadoresThreads.length; i++) {
            ajustadoresThreads[i] = new Thread(new Ajustadores(ContenedorInicial, i));
        }

        Thread[] movedoresThreads = new Thread[MOVEDORES_THREADS];
        for (int i = 0; i < movedoresThreads.length; i++) {
            movedoresThreads[i] = new Thread(new Movedores(ContenedorInicial, ContenedorFinal, i));
        }

        Thread log = new Thread(new Log(creadoresThreads, mejoradoresThreads, ajustadoresThreads, movedoresThreads, ContenedorInicial, ContenedorFinal));

        for (Thread thread : creadoresThreads) {
            thread.start();
        }
        for (Thread thread : mejoradoresThreads) {
            thread.start();
        }
        for (Thread thread : ajustadoresThreads) {
            thread.start();
        }
        for (Thread thread : movedoresThreads) {
            thread.start();
        }
        log.start();
        log.join();
    }
}