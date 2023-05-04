/**
 * El sistema debe contar con un LOG con fines estadísticos, el cual registre cada 500
 * milisegundos en un archivo:
 * - Cantidad de imágenes insertadas en el contenedor.
 * - Cantidad de imágenes mejoradas completamente.
 * - Cantidad de imágenes ajustadas.
 * - Cantidad de imágenes que han finalizado el último proceso.
 * - El estado de cada hilo del sistema.
 */

import java.util.Date;
public class Log implements Runnable{
    private final Thread[] creadoresThreads;
    private final Thread[] mejoradoresThreads;
    private final Thread[] ajustadoresThreads;
    private final Thread[] movedoresThreads;
    private final Contenedor ContenedorInicial;
    private final Contenedor ContenedorFinal;
    public Log(Thread[] creadoresThreads,Thread[] mejoradoresThreads,Thread[] ajustadoresThreads,Thread[] movedoresThreads,Contenedor ContenedorInicial,Contenedor ContenedorFinal){
        this.creadoresThreads = creadoresThreads;
        this.mejoradoresThreads = mejoradoresThreads;
        this.ajustadoresThreads = ajustadoresThreads;
        this.movedoresThreads = movedoresThreads;
        this.ContenedorInicial = ContenedorInicial;
        this.ContenedorFinal = ContenedorFinal;
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

            System.out.println("Cantidad de imágenes insertadas en el Contenedor Inicial: " + this.ContenedorInicial.getImagenesIngresadas());
            System.out.println("Cantidad de imágenes mejoradas completamente: " + (this.ContenedorInicial.getImagenesMejoradas()/3));
            System.out.println("Cantidad de imágenes ajustadas: " + this.ContenedorInicial.getImagenesAjustadas());
            System.out.println("Cantidad de imágenes que han finalizado el último proceso: " + this.ContenedorInicial.getImagenesEliminadas());
            System.out.println("Cantidad de imágenes insertadas en el Contenedor Final: " + this.ContenedorFinal.getImagenesIngresadas());

            for (Thread thread : creadoresThreads) {
                System.out.println("Creador " + thread.getName() + " esta " + thread.getState());
            }
            for (Thread thread : mejoradoresThreads) {
                System.out.println("Mejorador " + thread.getName() + " esta " + thread.getState());
            }
            for (Thread thread : ajustadoresThreads) {
                System.out.println("Ajustador " + thread.getName() + " esta " + thread.getState());
            }
            for (Thread thread : movedoresThreads) {
                System.out.println("Movedor " + thread.getName() + " esta " + thread.getState());
            }

            flag = false;
            for (Thread thread : creadoresThreads) {
                if (thread.isAlive()) {
                    flag = true;
                    break;
                }
            }
            for (Thread thread : mejoradoresThreads) {
                if (thread.isAlive()) {
                    flag = true;
                    break;
                }
            }
            for (Thread thread : ajustadoresThreads) {
                if (thread.isAlive()) {
                    flag = true;
                    break;
                }
            }
            for (Thread thread : movedoresThreads) {
                if (thread.isAlive()) {
                    flag = true;
                    break;
                }
            }
        }
        System.out.printf("Se finalizo el programa: %s\n", new Date());
    }
}