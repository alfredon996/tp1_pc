public abstract class Proceso {
    protected int nombre;
    protected Contenedor ContenedorInicial;
    public Proceso(Contenedor ContenedorInicial, int nombre) {
        this.nombre = nombre;
        this.ContenedorInicial = ContenedorInicial;
    }
    public int getNombre(){
        return nombre;
    }
    public Contenedor getContenedorInicial(){
        return ContenedorInicial;
    }
}
