package innui.utiles.strings;

import innui.modelos.errores.oks;
import innui.bases;

import java.util.ResourceBundle;

/**
 *
 * @author emilio
 */
public class literales extends bases {
    /**
     * Encuentra el final de un texto literal, terminando en el caracter fin.
     * @param donde_buscar
     * @param fin
     * @param i
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */    
    public static Integer _encontrar_fin_de_texto_literal(String donde_buscar, char fin, int i, oks ok, Object ... extras_array) throws Exception {
        Integer retorno = -1; 
        try {
            if (ok.es == false) { return null; }
            int tam = donde_buscar.length();
            int escapes_num = 0;
            while (true) {
                i = i +1;
                if (i >= tam) {
                    break;
                }
                if (donde_buscar.charAt(i) == '\\') {
                    escapes_num = escapes_num + 1;
                } else {
                    if (donde_buscar.charAt(i) == fin) {
                        if (escapes_num %2 == 0) {
                            // número par de escapes consecutivos
                            retorno = i;
                            break;
                        }
                    }
                    escapes_num = 0;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return retorno;
    }
    /**
     * Busca un texto saltándo las cadenas entrecomillas dobles y simples
     * @param donde_buscar
     * @param ignorar_caso
     * @param que_buscar
     * @param inicio
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public static int buscar_sin_texto_literal(String donde_buscar, boolean ignorar_caso, String que_buscar, int inicio, oks ok, Object ... extras_array) throws Exception {
        int encontrado_pos = -1;
        ResourceBundle in = null;
        try {
            if (ok.es == false) { return -1; }
            int i = inicio;
            int tam = donde_buscar.length();
            tam = tam - que_buscar.length() + 1;
            while (true) {
                if (i >= tam) {
                    break;
                }
                if (donde_buscar.regionMatches(ignorar_caso, i, que_buscar, 0, que_buscar.length())) {
                    encontrado_pos = i;
                    break;
                } else if (donde_buscar.charAt(i) == '"') {
                    i = _encontrar_fin_de_texto_literal(donde_buscar, '"', i, ok);
                    if (i == -1 || ok.es == false) { break; }
                } else if (donde_buscar.charAt(i) == '\'') {
                    i = _encontrar_fin_de_texto_literal(donde_buscar, '\'', i, ok);
                    if (i == -1 || ok.es == false) { break; }
                }
                i = i +1;
            }
        } catch (Exception e) {
            throw e;
        }
        return encontrado_pos;
    }
    /**
     * Busca un texto a continuación de la posición de inicio
     * @param donde_buscar
     * @param ignorar_caso
     * @param que_buscar
     * @param inicio
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public static int buscar_seguido(String donde_buscar, boolean ignorar_caso, String que_buscar, int inicio, oks ok, Object ... extras_array) throws Exception {
        int encontrado_pos = -1;
        ResourceBundle in = null;
        try {
            if (ok.es == false) { return -1; }
            int i = inicio;
            int tam = donde_buscar.length();
            tam = tam - que_buscar.length() + 1;
            while (true) {
                if (i >= tam) {
                    break;
                }
                if (donde_buscar.regionMatches(ignorar_caso, i, que_buscar, 0, que_buscar.length())) {
                    encontrado_pos = i;
                    break;
                } else if (("" + donde_buscar.charAt(i)).trim().isEmpty() == false) {
                    return -1;
                }
                i = i + 1;
            }
        } catch (Exception e) {
            throw e;
        }
        return encontrado_pos;
    }
}
