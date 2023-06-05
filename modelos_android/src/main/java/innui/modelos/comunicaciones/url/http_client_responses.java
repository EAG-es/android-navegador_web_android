package innui.modelos.comunicaciones.url;

import android.innui.modelos_android.configuraciones.ResourceBundles;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;
import innui.bases;

import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 * @author profesor
 */
public class http_client_responses extends bases {
    public static String k_in_ruta = "assets/in/innui/modelos/comunicaciones/url/in";
    public static String k_protocolo_version = "protocolo_version";
    public static String k_estado_recibido = "estado_recibido";
    public static String k_estado_recibido_texto = "estado_recibido_texto";
    /**
     * Analiza un texto recibido de un servidor HTTP y carga los datos en y retorna el cuerpo del texto.
     * @param texto_recibido
     * @param datos_mapa
     * @param ok
     * @param extra_array
     * @return 
     * @throws Exception
     */
    public static String analizar_respuesta(String texto_recibido, Map<String, String> datos_mapa, oks ok, Object... extra_array) throws Exception {
        String retorno = null;
        int i = 0;
        String [] cabecera_cuerpo_array;
        String [] lineas_array;
        String [] partes_linea_array;
        String [] primera_linea;
        int pos;
        cabecera_cuerpo_array = texto_recibido.split("\n\\s*\n");
        ResourceBundle in = ResourceBundles.getBundle(k_in_ruta);
        if (cabecera_cuerpo_array.length <= 1) {
            ok.setTxt(tr.in(in, "NO SE HA PODIDO IDENTIFICAR LA CABECERA Y EL CUERPO DEL MENSAJE. "));
        } else {
            pos = texto_recibido.indexOf("\n", cabecera_cuerpo_array[0].length());
            pos = texto_recibido.indexOf("\n", pos + 1);
            retorno = texto_recibido.substring(pos + 1);
            lineas_array = cabecera_cuerpo_array[0].split("\n");
            for (String linea: lineas_array) {
                i = i + 1;
                if (i == 1) {
                    primera_linea = linea.split("\\s");
                    if (primera_linea.length == 3) {
                        datos_mapa.put(k_protocolo_version, primera_linea[0]);
                        datos_mapa.put(k_estado_recibido, primera_linea[1]);
                        datos_mapa.put(k_estado_recibido_texto, primera_linea[2]);
                    } else if (primera_linea.length == 2) {
                        datos_mapa.put(k_protocolo_version, primera_linea[0]);
                        datos_mapa.put(k_estado_recibido, primera_linea[1]);
                    } else {
                        ok.setTxt(java.text.MessageFormat.format(tr.in(in, "FORMATO INCORRECTO EN LA PRIMERA LINEA DE CABECERA: {0}"), new Object[] {i}));
                        retorno = null;
                        break;
                    }
                } else {
                    partes_linea_array = linea.split(":");
                    if (partes_linea_array.length == 2) {
                        datos_mapa.put(partes_linea_array[0], partes_linea_array[1]);
                    } else if (partes_linea_array.length > 2) {
                        datos_mapa.put(partes_linea_array[0], linea.substring(linea.indexOf(":") + 1));                        
                    } else {
                        ok.setTxt(java.text.MessageFormat.format(tr.in(in, "FORMATO INCORRECTO EN LA LINEA DE CABECERA: {0}"), new Object[] {i}));
                        retorno = null;
                        break;
                    }
                }
            }
        }
        return retorno;
    }
}
