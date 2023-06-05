package inclui.formularios;

import static innui.formularios.formularios.k_fase_procesamiento;

import innui.modelos.errores.oks;

/**
 *
 * @author emilio
 */
public class control_textareas extends control_entradas {
    public static String k_in_ruta = "assets/in/inclui/formularios/in";
    public static String k_control_textareas_codigo_fin = "./.";
    public static String k_control_textareas_codigo_igual = ".=.";
    
    public control_textareas() {
        _control_tipo = k_entradas_tipo_texto;
    }
    /**
     * Inicia una textarea.
     * @param tipo_entrada Se ignora este parámetro
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    @Override
    public boolean iniciar(String tipo_entrada, oks ok, Object ... extras_array) throws Exception {
        _control_tipo = k_entradas_tipo_texto;
        return ok.es;
    }
    /**
     * Presenta información antes de capturar información
     * @param modo_operacion
     * @param objeto_a_presentar
     * @param ok
     * @param extras_array
     * @return true si todo es correcto
     * @throws Exception 
     */
    @Override
    public boolean _presentar(String modo_operacion, Object objeto_a_presentar, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            if (modo_operacion.equals(k_fase_procesamiento)) {
                _formulario.escribir_linea(mensaje_de_captura, ok, extras_array);
                if (ok.es == false) { return false; }
                if (_utilizar_valor_por_defecto(objeto_a_presentar, ok, extras_array)) {
                    objeto_a_presentar = valor;
                }
                if (ok.es == false) { return false; }
                String texto;
                if (objeto_a_presentar == null) { 
                    texto = "";
                } else {
                    texto = objeto_a_presentar.toString();
                }
                _formulario.escribir(k_entradas_codigo_cancelar
                        + " " + k_entradas_codigo_borrar
                        + " " + k_control_textareas_codigo_fin
                        + " " + k_control_textareas_codigo_igual
                        + " [" + texto + "] > "
                        , ok);
            }            
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Procesa la información capturada
     * @param modo_operacion
     * @param objeto_a_capturar
     * @param ok
     * @param extras_array
     * @return El objeto captuado, o null si hay error.
     * @throws Exception 
     */
    @Override
    public Object _capturar(String modo_operacion, Object objeto_a_capturar, oks ok, Object ... extras_array) throws Exception {
        String texto = "";
        String linea = null;
        try {
            if (ok.es == false) { return null; }
            if (modo_operacion.equals(k_fase_procesamiento)) {
                while (true) {
                    linea = _clui_lectura.leer_linea(ok, extras_array);
                    if (linea.equals(k_control_textareas_codigo_fin)) {
                        break;
                    }
                    if (linea.equals(k_entradas_codigo_cancelar)) {
                        _formulario.cancelar(ok, extras_array);
                        return null;
                    }
                    if (linea.equals(k_entradas_codigo_borrar)) {
                        texto = k_entradas_codigo_borrar;
                        break;
                    }
                    if (texto.isEmpty()) {
                        if (linea.equals(k_control_textareas_codigo_igual)) {
                            break;
                        }
                        texto = linea;
                    } else {
                        texto = texto + "\n" + linea;
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return texto;
    }    

}
