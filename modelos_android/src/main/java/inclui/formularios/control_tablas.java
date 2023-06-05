package inclui.formularios;

import innui.formularios.formularios;
import android.innui.modelos_android.configuraciones.ResourceBundles;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

/**
 *
 * @author emilio
 */
public class control_tablas extends control_entradas {
    public static String k_in_ruta = "assets/in/inclui/formularios/in";
    public static String k_control_tablas_opciones_mapa_lista = "control_tablas_opciones_mapas_lista";
    public static String k_control_tablas_letras_por_linea_num = "control_tablas_letras_por_linea_num";
    public static String k_separador_columna = "|";
    public static String k_indicador_mas_datos = "+";
    public LinkedList<LinkedHashMap<String, Object>> control_filas_lista;
    public Integer _letras_por_linea = 0;
    
    public control_tablas() {
        _control_tipo = k_entradas_tipo_numero;
    }
    /**
     * Inicia una selección.
     * @param tipo_entrada Se ignora este parámetro
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    @Override
    public boolean iniciar(String tipo_entrada, oks ok, Object ... extras_array) throws Exception {
        _control_tipo = k_entradas_tipo_numero;
        return ok.es;
    }
    /**
     * Prepara el formato que aplicar al texto de una columna.
     * @param formato
     * @param letras_columna_lista Devuelve el ancho de las columnas
     * @param ok
     * @param extras_array
     * @return El tamaño determinado de ancho de columna
     * @throws Exception 
     */
    public boolean _obtener_formato_para_formar_texto(StringBuffer formato, LinkedList<Integer> letras_columna_lista, oks ok, Object ... extras_array) throws Exception {
        String formato_tex = "";
        LinkedList<Integer> letras_columna_suma_lista = new LinkedList<>();
        try {
            if (ok.es == false) { return false; }
            Integer i = 0;
            Integer max_tam = 0;
            Integer suma_tam = 0;
            Integer tam = 0;
            String texto;
            Object valor_objeto;
            Double doble = 0.0;
            boolean es_exceso_tam;
            doble = Math.log10(control_filas_lista.size()) + 1;
            for (Map<String, Object> control_columnas_mapa: control_filas_lista) {
                i = 0;
                for (Entry<String, Object> entry: control_columnas_mapa.entrySet()) {
                    if (i < letras_columna_lista.size()) {
                        max_tam = letras_columna_lista.get(i);
                        suma_tam = letras_columna_suma_lista.get(i);
                    } else {
                        max_tam = 0;
                        suma_tam = 0;
                    }
                    valor_objeto = entry.getValue();
                    if (valor_objeto != null) {
                        texto = valor_objeto.toString();
                        if (suma_tam == 0) {
                            letras_columna_suma_lista.add(i, texto.length());
                        } else {
                            suma_tam = suma_tam + texto.length();
                            letras_columna_suma_lista.set(i, suma_tam);
                        }
                        if (max_tam == 0) {
                            letras_columna_lista.add(i, texto.length());
                        } else if (max_tam < texto.length()) {
                            letras_columna_lista.set(i, texto.length());
                        }
                    }
                    i = i + 1;
                }
            }
            i = 0;
            for (int columna_suma: letras_columna_suma_lista) {
                columna_suma = columna_suma / (control_filas_lista.size() - 1); // anchura media - la línea de cabecera
                max_tam = letras_columna_lista.get(i);
                if (columna_suma*2 < max_tam) {
                    max_tam = columna_suma*2; // max_tam no puede ser más grande que 2 veces la media de la anchura.
                }
                if (columna_suma > _letras_por_linea) {
                    max_tam = _letras_por_linea;
                }
                letras_columna_lista.set(i, max_tam);
                i = i + 1;
            }
            es_exceso_tam = false;
            while (true) {
                tam = k_separador_columna.length() + k_separador_columna.length()
                 + k_indicador_mas_datos.length();
                for (int columna_tam: letras_columna_lista) {
                    tam = tam + columna_tam + k_separador_columna.length();
                }
                if (tam <= _letras_por_linea) {
                    break;
                } else {
                    es_exceso_tam = true;
                    letras_columna_lista.remove(letras_columna_lista.size()-1);
                }
            }
            formato_tex = "(%" + doble.intValue() + "d)";
            i = 0;
            for (int columna_tam: letras_columna_lista) {
                formato_tex = formato_tex + k_separador_columna + "%-" + columna_tam + "s";
            }
            if (es_exceso_tam) {
                formato_tex = formato_tex + k_separador_columna + k_indicador_mas_datos;
            } else {
                formato_tex = formato_tex + k_separador_columna;
            }
        } catch (Exception e) {
            throw e;
        }
        formato.setLength(0);
        formato.append(formato_tex);
        return ok.es;
    }
    /**
     * Presenta la tabla
     * @param modo_operacion
     * @param objeto_a_presentar
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    @Override
    public boolean _presentar(String modo_operacion, Object objeto_a_presentar, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            if (modo_operacion.equals(formularios.k_fase_procesamiento)) {
                int columnas_num = 0;
                Object valor_objeto;
                String texto;
                String formato_tex;
                StringBuffer formato_stringBuffer = new StringBuffer();
                LinkedList<Integer> letras_columna_lista = new LinkedList<>();
                _obtener_formato_para_formar_texto(formato_stringBuffer, letras_columna_lista, ok);
                if (ok.es == false) { return false; }
                columnas_num = letras_columna_lista.size();
                formato_tex = formato_stringBuffer.toString();
                int posicion = 0;
                int i = 0;
                int i_columna = 0;
                Object [] object_datos_array = new Object[columnas_num + 1];
                for (Map<String, Object> control_columnas_mapa: control_filas_lista) {
                    i_columna = 0;
                    object_datos_array[0] = posicion;
                    i = 1;
                    for (Entry<String, Object> entry: control_columnas_mapa.entrySet()) {
                        if (i_columna >= columnas_num) {
                            break;
                        }
                        valor_objeto = entry.getValue();
                        if (valor_objeto != null) {
                            texto = valor_objeto.toString();
                            if (texto.length() > letras_columna_lista.get(i_columna)) {
                                texto = texto.trim();
                                if (texto.length() > letras_columna_lista.get(i_columna)) {
                                    texto = texto.substring(0, letras_columna_lista.get(i_columna)-1) + k_indicador_mas_datos;
                                }
                            }
                        } else {
                            texto = "";
                        }
                        object_datos_array[i] = texto;
                        i_columna = i_columna + 1;
                        i = i + 1;
                    }
                    texto = _formulario.formar_texto(null, formato_tex, ok, object_datos_array);
                    if (ok.es == false) { return false; }
                    _formulario.escribir_linea(texto, ok, extras_array);
                    posicion = posicion + 1;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Valida que el objeto obtenido en el control es válido
     * @param modo_operacion
     * @param objeto_a_validar
     * @param ok
     * @param extras_array
     * @return true si todo es correcto
     * @throws Exception 
     */
    @Override
    public boolean _validar(String modo_operacion, Object objeto_a_validar, oks ok, Object ... extras_array) throws Exception {
        return ok.es;
    }    
    /**
     * Activa la repetición del procesamiento del control.
     * @param ok Parámetro para la activación
     * @param extras_array
     * @return El nuevo valor que debe ponerse.
     * @throws Exception 
     */
    @Override
    public boolean poner_hacer_repetir_procesar(oks ok, Object ... extras_array) throws Exception {
        return false;
    }
    /**
     * Procesa la información capturada
     * @param modo_operacion
     * @param objeto_a_capturar
     * @param ok
     * @param extras_array
     * @return El objeto capturado, o null si hay error.
     * @throws Exception 
     */
    @Override
    public Object _capturar(String modo_operacion, Object objeto_a_capturar, oks ok, Object ... extras_array) throws Exception {
        return 0;
    }
    /**
     * Conecta un control con un formulario
     * @param formulario Formulario donde incorporar el control (por orden de inclusión)
     * @param clave Nombre identificador del formulario (en un grupo exclusivo el clave puede repetirse)
     * @param mensaje_de_captura Mensaje que presentar en la valor_de_captura
     * @param opciones_mapa Opciones que va a utilizar el control en algunas de sus fases 
     * (debe contener: k_opciones_mapa_control_selecciones, k_control_tablas_letras_por_linea_num)
     * @param ok
     * @param extras_array
     * @return true si no hay errores
     * @throws Exception 
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean poner_en_formulario(formularios formulario, String clave, Object valor, String mensaje_de_captura, Map<String, Object> opciones_mapa, oks ok, Object ... extras_array) throws Exception {
        ResourceBundle in;
        try {
            if (ok.es == false) { return false; }
            super.poner_en_formulario(formulario, clave, valor, mensaje_de_captura, opciones_mapa, ok, extras_array);
            if (ok.es == false) { return false; }
            if (opciones_mapa != null) {
                if (control_filas_lista == null) {
                    control_filas_lista = (LinkedList<LinkedHashMap<String, Object>>) opciones_mapa.get(k_control_tablas_opciones_mapa_lista);
                    if (control_filas_lista == null) {
                        in = ResourceBundles.getBundle(k_in_ruta);
                        ok.setTxt(tr.in(in, "Falta la entrada del mapa: ") + k_control_tablas_opciones_mapa_lista);
                    }
                }
                _letras_por_linea = (Integer) opciones_mapa.get(k_control_tablas_letras_por_linea_num);
                if (_letras_por_linea == null) {
                    in = ResourceBundles.getBundle(k_in_ruta);
                    ok.setTxt(ok.getTxt(), tr.in(in, "Falta la entrada del mapa: ") + k_control_tablas_letras_por_linea_num);
                }
            } else {
                in = ResourceBundles.getBundle(k_in_ruta);
                if (control_filas_lista == null) {
                    ok.setTxt(tr.in(in, "Falta la entrada del mapa: ") + k_control_tablas_opciones_mapa_lista);
                }
                ok.setTxt(ok.getTxt(), tr.in(in, "Falta la entrada del mapa: ") + k_control_tablas_letras_por_linea_num);
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Entrega una representación de tabla, vacía, válida para el control.
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public LinkedList<LinkedHashMap<String, Object>> crear_tabla_vacia(oks ok, Object ... extras_array) throws Exception {
        return new LinkedList<>();
    }
    /**
     * Carga el control con el contenido de un archivo de propiedades
     * @param filas_lista
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public boolean cargar_tabla(LinkedList<LinkedHashMap<String, Object>> filas_lista, oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return false; }
        try {
            control_filas_lista = filas_lista;
            if (opciones_mapa != null) {
                opciones_mapa.put(k_control_tablas_opciones_mapa_lista, control_filas_lista);
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Recupera una fila
     * @param fila_num
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public LinkedHashMap<String, Object> leer_fila(int fila_num, oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return null; }
        LinkedHashMap<String, Object> retorno_mapa = null;
        try {
            int i = 0;
            for (LinkedHashMap<String, Object> columnas_mapa : control_filas_lista) {
                if (i == fila_num) {
                    return columnas_mapa;
                }
                i = i + 1;
            }
        } catch (Exception e) {
            throw e;
        }
        return retorno_mapa;
    }
    
}
