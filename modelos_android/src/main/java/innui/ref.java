package innui;

/**
 *
 * @author emilio
 * @param <t_generico> Clase de la que crear el genérico de referencia.
 */
public class ref<t_generico> extends bases {
    public t_generico o;    
    /**
     * Constructor sin parámetros
     */
    public ref() {
        
    }
    /**
     * Constructor con asignación de objeto genérico
     * @param o Objeto que asignar al atributo o
     */
    public ref(t_generico o) {
        this.o = o;
    }

    public t_generico get() {
        return o;
    }

    public void set(t_generico o) {
        this.o = o;
    }
    
}
