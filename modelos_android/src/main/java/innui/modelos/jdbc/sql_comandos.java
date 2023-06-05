package innui.modelos.jdbc;

import android.innui.modelos_android.configuraciones.ResourceBundles;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;
import innui.modelos.tipos_valores;
import innui.utiles.strings.literales;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 * @author emilio
 */
public class sql_comandos {
    public static String k_in_ruta = "assets/in/innui/modelos/jdbc/in";
    public static String k_sql_comandos_insert = "insert ";
    public static String k_sql_comandos_select = "select ";
    public static String k_sql_comandos_delete = "delete ";
    public static String k_sql_comandos_marcador_columnas_lista = "<columnas>";
    public static String k_sql_comandos_marcador_columnas_lista_error = "no <columnas>";
    public static String k_sql_comandos_marcador_para_valor = "?";
    public static String k_sql_comandos_id_error_iniciar = "sql_comandos_iniciar";
    public static String k_sql_comandos_formato_date = "%1$tF %1$tT";
    public static String k_sql_comandos_formato_time = "%1$tT:%1$tL";
    public String clase_driver_jdbc;
    public String clase_driver_jdbc_uri_conexion;
    public String creacion_sql;
    public String lectura_sql;
    public String actualizacion_sql;
    public String borrado_sql;
    public String _lectura_sql;
    public LinkedList<String> lectura_columnas_lista;
    public LinkedList<String> clave_nueva_columnas_lista;
    public LinkedList<LinkedHashMap<String, tipos_valores>> lecturas_lista;
    public LinkedHashMap<String, tipos_valores> clave_nueva_mapa;
    public Map<String, Object> peticiones_mapa;
    
    public boolean iniciar(String driver, String uri, oks ok, Object... extra_array) throws Exception {
        clase_driver_jdbc = driver;
        clase_driver_jdbc_uri_conexion = uri;
        return ok.es;
    }
    /**
     * Crea la lista de columnas que se van a leer.
     * @param ok
     * @param extra_array Nombres de las columnas que poner en la lista (pueden contener la palabra clave alias, o ser una funcion (se toma el último nombre (el espacio en blanco es el separador)))
     * @return
     * @throws Exception 
     */
    public boolean leer_crear_lectura_columnas_lista(oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return false; }
        try {
            if (lectura_columnas_lista == null) {
                lectura_columnas_lista = new LinkedList<>();
            } else {
                lectura_columnas_lista.clear();
            }
            String [] nombres_array;
            for (Object object: extra_array) {
                if (object != null && object.toString().trim().isEmpty() == false) {
                    nombres_array = object.toString().trim().split("\\s+");
                    lectura_columnas_lista.add(nombres_array[nombres_array.length-1]);
                }
            }
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return ok.es;
    }
    /**
     * Crea la lista de columnas que se van a leer.
     * @param columnas_tex Texto con los nombres de las columnas separados por: ,
     * @param ok
     * @param extra_array Nombres de las columnas que poner en la lista (pueden contener la palabra clave alias, o ser una funcion (se toma el último nombre (el espacio en blanco es el separador)))
     * @return
     * @throws Exception 
     */
    public boolean leer_crear_lectura_columnas_lista(String columnas_tex, oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return false; }
        try {
            String [] columnas_array;
            columnas_array = columnas_tex.split(",");
            Object [] objects_array = new Object[columnas_array.length];
            int i = 0;
            for (String columna: columnas_array) {
                columna = columna.trim();
                if (columna.trim().isEmpty() == false) {
                    objects_array[i] = columna;
                    i = i + 1;
                }
            }
            leer_crear_lectura_columnas_lista(ok, objects_array); 
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return ok.es;
    }
    /**
     * Crea la lista de columnas de una clave nueva automática creada.
     * @param ok
     * @param extra_array Nombres de las columnas que poner en la lista (un nombre por columna)
     * @return
     * @throws Exception 
     */
    public boolean crear_clave_nueva_columnas_lista(oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return false; }
        try {
            if (clave_nueva_columnas_lista == null) {
                clave_nueva_columnas_lista = new LinkedList<>();
            } else {
                clave_nueva_columnas_lista.clear();
            }
            for (Object object: extra_array) {
                clave_nueva_columnas_lista.add(object.toString());
            }
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return ok.es;
    }
    /**
     * Crea o vacia lecturas_lista
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public boolean leer_crear_lecturas_lista(oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return false; }
        try {
            if (lecturas_lista == null) {
                lecturas_lista = new LinkedList<>();
            } else {
                lecturas_lista.clear();
            }
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return ok.es;
    }
    /**
     * Crea un mapa para una fila resultante de una lectura
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public LinkedHashMap<String, tipos_valores> crear_columnas_mapa(oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return null; }
        LinkedHashMap<String, tipos_valores> columnas_mapa = null;
        try {
            columnas_mapa = new LinkedHashMap<>();
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return columnas_mapa;
    }
    /**
     * Valida y completa las instrucciones CLAB, antes de pasarle los datos en k_sql_comandos_marcador_para_valor
     * @param comando
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public boolean leer_iniciar(String comando, oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return false; }
        ResourceBundle in;
        try {
            String texto;
            String columnas_tex;
            in = ResourceBundles.getBundle(k_in_ruta);
            lectura_sql = comando;
            texto = lectura_sql;
            if (texto != null) {
                texto = texto.toLowerCase().trim();
                if (texto.contains(k_sql_comandos_select) == false) {
                    ok.setTxt(ok.getTxt(), tr.in(in, "Se esperaba una orden: ") + k_sql_comandos_select);
                }
                if (texto.contains(" " + k_sql_comandos_marcador_columnas_lista + " ") == false) {
                    ok.setId(k_sql_comandos_marcador_columnas_lista_error);
                    ok.setTxt(ok.getTxt(), tr.in(in, "Falta el marcador para la lista de columnas: ") + k_sql_comandos_marcador_columnas_lista);
                    _lectura_sql = texto;
                } else {
                    if (lectura_columnas_lista == null) {
                        ok.setTxt(tr.in(in, "Error, no se han definido las columnas que leer. "));
                    } else {
                        columnas_tex = "";
                        for (String columna: lectura_columnas_lista) {
                            if (columnas_tex.trim().isEmpty()) {
                                columnas_tex = columnas_tex + columna;
                            } else {
                                columnas_tex = columnas_tex + ", " + columna;
                            }
                        }
                        _lectura_sql = texto.replace(k_sql_comandos_marcador_columnas_lista, columnas_tex);
                    }
                }
            }
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return ok.es;
    }
    /**
     * Valida y completa las instrucciones CLAB, antes de pasarle los datos en k_sql_comandos_marcador_para_valor
     * @param comando
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public boolean crear_iniciar(String comando, oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return false; }
        ResourceBundle in;
        try {
            String texto;
            String columnas_tex;
            in = ResourceBundles.getBundle(k_in_ruta);
            creacion_sql = comando;
            texto = creacion_sql;
            if (texto != null) {
                texto = texto.toLowerCase().trim();
                if (texto.contains(k_sql_comandos_insert) == false) {
                    ok.setTxt(ok.getTxt(), tr.in(in, "Se esperaba una orden: ") + k_sql_comandos_insert);
                }
            }
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return ok.es;
    }
    /**
     * Valida y completa las instrucciones CLAB, antes de pasarle los datos en k_sql_comandos_marcador_para_valor
     * @param comando
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public boolean actualizar_iniciar(String comando, oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return false; }
        ResourceBundle in;
        try {
            in = ResourceBundles.getBundle(k_in_ruta);
            actualizacion_sql = comando;
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return ok.es;
    }
    /**
     * Valida y completa las instrucciones CLAB, antes de pasarle los datos en k_sql_comandos_marcador_para_valor
     * @param comando
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public boolean borrar_iniciar(String comando, oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return false; }
        ResourceBundle in;
        try {
            String texto;
            in = ResourceBundles.getBundle(k_in_ruta);
            borrado_sql = comando;
            texto = borrado_sql;
            if (texto != null) {
                texto = texto.toLowerCase().trim();
                if (texto.contains(k_sql_comandos_delete) == false) {
                    ok.setTxt(ok.getTxt(), tr.in(in, "Se esperaba una orden: ") + k_sql_comandos_delete);
                }
            }
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return ok.es;
    }

    public String getLeer_sql() {
        return _lectura_sql;
    }
    /**
     * Completa las marcas k_sql_comandos_marcador_para_valor de una instrucción SQL, con los valores correspondientes
     * @param texto Texto donde poner el valor
     * @param valor Valor que poner (en función de su clase)
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public String _poner_valor_en_marcas(String texto, Object valor, oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return null; }
        ResourceBundle in;
        try {
            int inicio_busqueda_pos;
            in = ResourceBundles.getBundle(k_in_ruta);
            if (ok.es == false) { return null; }
            inicio_busqueda_pos = 0;
            int i = 1;
            int tam = texto.length();
            char letra;
            while (true) {
                if (i > 1) {
                    break;
                }
                inicio_busqueda_pos = literales.buscar_sin_texto_literal(texto, true
                , k_sql_comandos_marcador_para_valor, inicio_busqueda_pos, ok, extra_array);
                if (ok.es == false) { break; }
                if (inicio_busqueda_pos < 0) {
                    ok.setTxt(tr.in(in,"No se ha encontrado la posición: " + k_sql_comandos_marcador_para_valor));
                    break;
                } else {
                    if (inicio_busqueda_pos + 1 < tam) {
                        letra = texto.charAt(inicio_busqueda_pos + 1);
                        if (Character.isAlphabetic(letra)
                         || Character.isDigit(letra)) {
                            i = i -1;
                        }
                    } 
                    if (inicio_busqueda_pos - 1 >= 0) {
                        letra = texto.charAt(inicio_busqueda_pos - 1);
                        if (Character.isAlphabetic(letra)
                         || Character.isDigit(letra)) {
                            i = i -1;
                        }
                    }
                }
                inicio_busqueda_pos = inicio_busqueda_pos + 1;
                i = i + 1;
            }
            if (ok.es == false) { return null; }
            texto = texto.substring(0, inicio_busqueda_pos - 1)
            + _pasar_a_texto_sql_segun_tipo(valor, ok)
            + texto.substring(inicio_busqueda_pos - 1 + k_sql_comandos_marcador_para_valor.length());
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return texto;
    }
    /**
     * Completa las marcas k_sql_comandos_marcador_para_valor de una instrucción SQL, con los valores correspondientes
     * @param texto Texto donde poner el valor
     * @param valores_lista Valores que poner (en función de su clase)
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public String _poner_valor_en_marcas(String texto, List<Object> valores_lista, oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return null; }
        try {
            if (ok.es == false) { return null; }
            for (Object valor: valores_lista) {
                texto = _poner_valor_en_marcas(texto, valor, ok, extra_array);
                if (ok.es == false) { break; }
            }
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return texto;
    }
    /** 
     * Convierte a texto el valor pasado, en función de su clase
     * @param valor
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public String _pasar_a_texto_sql_segun_tipo(Object valor, oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return null; }
        String texto = null;
        try {
            if (valor == null) {
                texto = "null";
            } else if (valor instanceof String) {
                texto = "'" + valor + "'";
            } else if (valor instanceof Date) {
                texto = "'" + String.format(k_sql_comandos_formato_date, (Date) valor) + "'";
            } else if (valor instanceof Time) {
                texto = "'" + String.format(k_sql_comandos_formato_time, (Time) valor) + "'";
            } else if (valor instanceof Integer) {
                texto = String.valueOf((Integer) valor);
            } else if (valor instanceof Long) {
                texto = String.valueOf((Long) valor);
            } else if (valor instanceof Double) {
                texto = String.valueOf((Double) valor);
            } else if (valor instanceof Float) {
                texto = String.valueOf((Float) valor);
            } else if (valor instanceof BigDecimal) {
                texto = ((BigDecimal) valor).toPlainString();
            } else if (valor instanceof BigInteger) {
                texto = ((BigInteger) valor).toString();
            } else {
                texto = valor.toString();
            }
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return texto;        
    }
    /**
     * Completa las marcas k_sql_comandos_marcador_para_valor de una instrucción SQL, con los valores correspondientes
     * @param valor Valor que poner (en función de su clase)
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public boolean crear_poner_valor(Object valor, oks ok, Object... extra_array) throws Exception {
        String texto;
        texto = _poner_valor_en_marcas(creacion_sql, valor, ok, extra_array);
        if (ok.es) {
            creacion_sql = texto;
        }
        return ok.es;
    }
    /**
     * Completa las marcas k_sql_comandos_marcador_para_valor de una instrucción SQL, con los valores correspondientes
     * @param valores_lista Valor que poner (en función de su clase)
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public boolean crear_poner_valor(List<Object> valores_lista, oks ok, Object... extra_array) throws Exception {
        String texto;
        texto = _poner_valor_en_marcas(creacion_sql, valores_lista, ok, extra_array);
        if (ok.es) {
            creacion_sql = texto;
        }
        return ok.es;
    }
    /**
     * Completa las marcas k_sql_comandos_marcador_para_valor de una instrucción SQL, con los valores correspondientes
     * @param valor Valor que poner (en función de su clase)
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public boolean leer_poner_valor(Object valor, oks ok, Object... extra_array) throws Exception {
        String texto;
        texto = _poner_valor_en_marcas(_lectura_sql, valor, ok, extra_array);
        if (ok.es) {
            _lectura_sql = texto;
        }
        return ok.es;
    }
    /**
     * Completa las marcas k_sql_comandos_marcador_para_valor de una instrucción SQL, con los valores correspondientes
     * @param valores_lista Valor que poner (en función de su clase)
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public boolean leer_poner_valor(List<Object> valores_lista, oks ok, Object... extra_array) throws Exception {
        String texto;
        texto = _poner_valor_en_marcas(_lectura_sql, valores_lista, ok, extra_array);
        if (ok.es) {
            _lectura_sql = texto;
        }
        return ok.es;
    }
    /**
     * Completa las marcas k_sql_comandos_marcador_para_valor de una instrucción SQL, con los valores correspondientes
     * @param valor Valor que poner (en función de su clase)
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public boolean actualizar_poner_valor(Object valor, oks ok, Object... extra_array) throws Exception {
        String texto;
        texto = _poner_valor_en_marcas(actualizacion_sql, valor, ok, extra_array);
        if (ok.es) {
            actualizacion_sql = texto;
        }
        return ok.es;
    }
    /**
     * Completa las marcas k_sql_comandos_marcador_para_valor de una instrucción SQL, con los valores correspondientes
     * @param valores_lista Valor que poner (en función de su clase)
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public boolean actualizar_poner_valor(List<Object> valores_lista, oks ok, Object... extra_array) throws Exception {
        String texto;
        texto = _poner_valor_en_marcas(actualizacion_sql, valores_lista, ok, extra_array);
        if (ok.es) {
            actualizacion_sql = texto;
        }
        return ok.es;
    }
    /**
     * Completa las marcas k_sql_comandos_marcador_para_valor de una instrucción SQL, con los valores correspondientes
     * @param valor Valor que poner (en función de su clase)
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public boolean borrar_poner_valor(Object valor, oks ok, Object... extra_array) throws Exception {
        String texto;
        texto = _poner_valor_en_marcas(borrado_sql, valor, ok, extra_array);
        if (ok.es) {
            borrado_sql = texto;
        }
        return ok.es;
    }
    /**
     * Completa las marcas k_sql_comandos_marcador_para_valor de una instrucción SQL, con los valores correspondientes
     * @param valores_lista Valor que poner (en función de su clase)
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public boolean borrar_poner_valor(List<Object> valores_lista, oks ok, Object... extra_array) throws Exception {
        String texto;
        texto = _poner_valor_en_marcas(borrado_sql, valores_lista, ok, extra_array);
        if (ok.es) {
            borrado_sql = texto;
        }
        return ok.es;
    }
    /**
     * Devuelve la lista leida, con datos conteniendo pares: tipo-valor
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public LinkedList<LinkedHashMap<String, tipos_valores>> leer_lecturas_lista_con_tipo_valor(oks ok, Object... extra_array) throws Exception {
        return lecturas_lista;
    }
    /**
     * Devuelve la lista leida, con datos de tipo Object
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public LinkedList<LinkedHashMap<String, Object>> leer_lecturas_lista(oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return null; }
        LinkedList<LinkedHashMap<String, Object>> retorno_lecturas_lista;
        try {
            retorno_lecturas_lista = new LinkedList<>();
            for (LinkedHashMap<String, tipos_valores> columnas_lista : lecturas_lista) {
                LinkedHashMap<String, Object> mapa = new LinkedHashMap<>();
                for (Map.Entry<String, tipos_valores> entry : columnas_lista.entrySet()) {
                    mapa.put(entry.getKey(), entry.getValue().valor);
                }
                retorno_lecturas_lista.add(mapa);
            }
        } catch (Exception e) {
            throw e;
        }
        return retorno_lecturas_lista;
    }
}
