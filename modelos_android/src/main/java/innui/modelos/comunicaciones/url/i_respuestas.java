package innui.modelos.comunicaciones.url;

import innui.modelos.errores.oks;

import java.util.Map;

/**
 *
 * @author emilio
 */
public interface i_respuestas {
    /**
     * 
     * @param datos_mapa
     * @param ok
     * @param extra_array
     * @return true si todo es correcto, false si hay error.
     * @throws Exception
     */
    public boolean completar_cabeceras(Map <String, String> datos_mapa, oks ok, Object... extra_array) throws Exception;
    /**
     * 
     * @param status
     * @param content_type
     * @param ok
     * @param extra_array
     * @return true si todo es correcto, false si hay error.
     * @throws Exception
     */
    public boolean iniciar_respuesta(int status, String content_type, oks ok, Object... extra_array) throws Exception;
    /**
     * 
     * @param mensaje
     * @param inicio
     * @param tam
     * @param ok
     * @param extra_array
     * @return true si todo es correcto, false si hay error.
     * @throws Exception
     */
    public boolean escribir_respuesta(byte [] mensaje, int inicio, int tam, oks ok, Object... extra_array) throws Exception;
    /**
     * 
     * @param es_cerrar
     * @param ok
     * @param extra_array
     * @return true si todo es correcto, false si hay error.
     * @throws Exception
     */
    public boolean terminar_respuesta(boolean es_cerrar, oks ok, Object... extra_array) throws Exception;
}
