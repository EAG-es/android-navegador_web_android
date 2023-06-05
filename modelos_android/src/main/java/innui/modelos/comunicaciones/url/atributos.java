package innui.modelos.comunicaciones.url;

import android.innui.modelos_android.configuraciones.ResourceBundles;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;
import innui.bases;

import java.util.ResourceBundle;

/**
 * Clase para los atributos de las etiqueta HTML
 */
public class atributos extends bases {
    public static String k_in_ruta = "assets/in/innui/modelos/comunicaciones.url/in";
    
    /**
     * Dado un nombre de atributo, extrae su valor de una etiqueta.
     * @param atributo Atributo que buscar
     * @param etiqueta Etiqueta HTML donde buscar
     * @param ok
     * @param extra_array
     * @return el valor del atributo, o null si hay error
     * @throws Exception
     */
    public static String extraer_atributo(String atributo, String etiqueta, oks ok, Object... extra_array) throws Exception {
        String resultado = null;
        String etiqueta_origen;
        String atributo_buscado;
        int pos;
        etiqueta_origen = etiqueta.toLowerCase();
        etiqueta_origen = etiqueta_origen.replaceAll("\\s+=\\s+", "="); //NOI18N
        atributo_buscado = atributo.toLowerCase();
        ResourceBundle in = null;
        in = ResourceBundles.getBundle(k_in_ruta);
        pos = etiqueta_origen.indexOf(atributo_buscado);
        if (pos >= 0) {
            pos = pos + atributo_buscado.length();
            char delimitador = etiqueta_origen.charAt(pos);
            int pos_final = etiqueta_origen.indexOf(delimitador, pos + 1);
            if (pos_final >= 0) {
                resultado = etiqueta_origen.substring(pos + 1, pos_final);
            }
        } else {
            ok.setTxt(tr.in(in, "NO SE HA ENCONTRADO EL ATRIBUTO EN LA ETIQUETA. "));
            resultado = null;
        }
        return resultado;
    }
    
}
