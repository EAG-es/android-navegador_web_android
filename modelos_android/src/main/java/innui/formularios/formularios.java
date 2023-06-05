package innui.formularios;

import android.innui.modelos_android.configuraciones.ResourceBundles;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;
import innui.bases;

import java.io.Serializable;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

/**
 *
 * @author emilio
 */
public class formularios extends bases implements Serializable {
    public static String k_in_base = "assets/in/innui/formularios/in";
    public static String k_fase_captura = "fase_captura";
    public static String k_fase_procesamiento = "fase_procesamiento";
    public static String k_importar_no_encontrado = "importar_no_encontrado";
    
    public LinkedList<controles> _controles_lista = new LinkedList<>();
    public boolean _es_terminar = false;
    public boolean _es_repetir = false;
    public boolean _es_cancelar = false;
    /**
     * Pone un nuevo control a la lista de controles.
     * @param control
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public boolean _poner_control(controles control, oks ok, Object ... extras_array) throws Exception {
        if (_controles_lista.contains(control) == false) {
            _controles_lista.add(control);
        }
        return ok.es;
    }
    /**
     * Quita un control existente, de la lista de controles
     * @param control
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public boolean _quitar_control(controles control, oks ok, Object ... extras_array) throws Exception {
        if (_controles_lista.contains(control)) {
            _controles_lista.remove(control);
        }
        return ok.es;
    }
    /**
     * Inicia el proceso de valor_de_captura y procesamiento de la información
     * @param modo_operacion Es la fase de operación de los controles del _formulario
     * @param ok
     * @param extras_array
     * @return 
     * @throws Exception 
     */
    public boolean _iniciar_formulario(String modo_operacion, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            _es_terminar = false;
            _es_cancelar = false;
            _es_repetir = false;
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Realiza los procesos de la fase de captura.
     * _iniciar_formulario, procesar, terminar_formulario
     * Si estos procesos retornan ok.es == false, procesar termina.
     * @param ok
     * @param extras_array
     * @return 
     * @throws Exception 
     */
    public boolean capturar(oks ok, Object ... extras_array) throws Exception {
        return procesar(k_fase_captura, ok, extras_array);
    }
    /** 
     * Repite la valor_de_captura del _formulario
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public boolean repetir(oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            _es_repetir = true;
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /** 
     * Si hay que repetir la valor_de_captura del _formulario
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public boolean ser_repetir(oks ok, Object ... extras_array) throws Exception {
        return _es_repetir;
    }
    /** 
     * Si hay que terminar el _formulario
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public boolean ser_terminar(oks ok, Object ... extras_array) throws Exception {
        return _es_terminar;
    }
    /** 
     * Si hay que terminar el _formulario
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public boolean ser_cancelar(oks ok, Object ... extras_array) throws Exception {
        return _es_cancelar;
    }
    /** 
     * Evita que se continúe con el valor_de_captura del _formulario
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public boolean terminar(oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            _es_terminar = true;
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /** 
     * Cancela el _formulario
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public boolean cancelar(oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            _es_cancelar = true;
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Realiza los procesos de la fase de procesamiento.
     * _iniciar_formulario, procesar, terminar_formulario
     * Si estos procesos retornan ok.es == false, procesar termina.
     * @param ok
     * @param extras_array
     * @return El objeto procesado, o null si hay error o no hay valor_de_captura.
     * @throws Exception 
     */
    public boolean procesar(oks ok, Object ... extras_array) throws Exception {
        return procesar(k_fase_procesamiento, ok, extras_array);
    }
    /**
     * Realiza los procesos de la fase de procesamiento.
     * _iniciar_formulario, procesar, terminar_formulario
     * Si estos procesos retornan ok.es == false, procesar termina.
     * @param modo_operacion Es la fase de operación de los controles del _formulario
     * @param ok
     * @param extras_array
     * @return 
     * @throws Exception 
     */
    public boolean procesar(String modo_operacion, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            Object object;
            while (true) {
                ok.iniciar();
                _iniciar_formulario(modo_operacion, ok, extras_array);
                if (ok.es == false) { return false; }
                for (controles control: _controles_lista) {
                    if (_es_terminar || _es_cancelar) {
                        break;
                    }
                    object = control.procesar(modo_operacion, control.getValor(), ok, extras_array);
                    if (ok.es == false) { break; }
                    if (modo_operacion.equals(k_fase_captura)) {
                        control.valor_de_captura = object;
                    } else {
                        control.valor = object;
                    }
                    if (_es_repetir) {
                        break;
                    }
                }
                _terminar_formulario(modo_operacion, ok, extras_array);                
                break;
            }                       
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Termina los procesos que iniciar dejó abiertos
     * @param modo_operacion Es la fase de operación de los controles del _formulario
     * @param ok
     * @param extras_array
     * @return 
     * @throws Exception 
     */
    public boolean _terminar_formulario(String modo_operacion, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Exporta a un mapa enlazado los pares clave (puede repetirse), valor de cada control.
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public List<Entry<String, Object>> exportar_valores(oks ok, Object ... extras_array) throws Exception {
        List<Entry<String, Object>>clave_valor_lista = null;
        try {
            if (ok.es == false) { return null; }
            clave_valor_lista = new ArrayList<>();
            Entry<String, Object> entry;
            for(controles control: _controles_lista) {
                entry = new SimpleEntry<>(control.clave, control.getValor());
                clave_valor_lista.add(entry);
            }
        } catch (Exception e) {
            throw e;
        }
        return clave_valor_lista;
    }
    /**
     * Exporta a un mapa enlazado los pares clave (puede repetirse), valor de cada control.
     * @param clave Calve de uno, o más, controles.
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public List<Entry<String, Object>> exportar_valores(String clave, oks ok, Object ... extras_array) throws Exception {
        List<Entry<String, Object>>clave_valor_lista = null;
        try {
            if (ok.es == false) { return null; }
            clave_valor_lista = new ArrayList<>();
            Entry<String, Object> entry;
            for(controles control: _controles_lista) {
                if (control.clave.equals(clave)) {
                    entry = new SimpleEntry<>(control.clave, control.getValor());
                    clave_valor_lista.add(entry);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return clave_valor_lista;
    }
    /**
     * Exporta a un mapa enlazado los pares clave (puede repetirse), valor de cada control.
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public List<Entry<String, Object>> exportar_valores_de_conversion(oks ok, Object ... extras_array) throws Exception {
        List<Entry<String, Object>>clave_valor_lista = null;
        try {
            if (ok.es == false) { return null; }
            clave_valor_lista = new ArrayList<>();
            Entry<String, Object> entry;
            for(controles control: _controles_lista) {
                entry = new SimpleEntry<>(control.clave, control.valor_de_conversion);
                clave_valor_lista.add(entry);
            }
        } catch (Exception e) {
            throw e;
        }
        return clave_valor_lista;
    }
    /**
     * Exporta a un mapa enlazado los pares clave (puede repetirse), valor de cada control.
     * @param clave Calve de uno, o más, controles.
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public List<Entry<String, Object>> exportar_valores_de_conversion(String clave, oks ok, Object ... extras_array) throws Exception {
        List<Entry<String, Object>>clave_valor_lista = null;
        try {
            if (ok.es == false) { return null; }
            clave_valor_lista = new ArrayList<>();
            Entry<String, Object> entry;
            for(controles control: _controles_lista) {
                if (control.clave.equals(clave)) {
                    entry = new SimpleEntry<>(control.clave, control.valor_de_conversion);
                    clave_valor_lista.add(entry);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return clave_valor_lista;
    }
    /**
     * Importa una lista con los pares clave (puede repetirse), valor a su control, en el orden en el que están.
     * @param clave_valor_lista Lista de origen.
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public boolean importar_valores(List<Entry<String, Object>> clave_valor_lista, oks ok, Object ... extras_array) throws Exception {
        ResourceBundle in;
        try {
            if (ok.es == false) { return false; }
            Map<String, Integer> duplicados_mapa = new HashMap<>();
            Integer contador_num;
            String clave;
            oks ok_final = new oks();
            for(Entry<String, Object> entry: clave_valor_lista) {
                clave = entry.getKey();
                if (duplicados_mapa.containsKey(clave)) {
                    contador_num = duplicados_mapa.get(clave);
                    contador_num = contador_num + 1;
                    duplicados_mapa.put(clave, contador_num);
                } else {
                    contador_num = 1;
                    duplicados_mapa.put(clave, contador_num);
                }
                int i = 1;
                Boolean es_encontrado = false;
                for (controles control: _controles_lista) {
                    if (control.clave.equals(clave)) {
                        if (i == contador_num) {
                            es_encontrado  = true;
                            control.importar_captura(entry.getValue(), ok);
                            if (ok.es == false) { break; }
                        } else {
                            i = i + 1;
                        }
                    }
                }
                if (es_encontrado == false) {
                    in = ResourceBundles.getBundle(k_in_base);
                    ok_final.id = k_importar_no_encontrado;
                    ok_final.setTxt(ok_final.getTxt(), tr.in(in, "No se ha encontrado el control con la clave: ") + clave);
                }
            }
            if (ok_final.es == false) {
                ok_final.setTxt(ok_final.getTxt(), ok.getTxt());
                ok.iniciar(ok_final);                
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Importa una lista con los pares clave (puede repetirse), valor a su control, en el orden en el que están.
     * @param clave
     * @param clave_valor_lista Lista de origen.
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public boolean importar_valores(String clave, List<Entry<String, Object>> clave_valor_lista, oks ok, Object ... extras_array) throws Exception {
        ResourceBundle in;
        try {
            if (ok.es == false) { return false; }
            Map<String, Integer> duplicados_mapa = new HashMap<>();
            Integer contador_num;
            String clave_valor;
            for(Entry<String, Object> entry: clave_valor_lista) {
                if (clave.equals(entry.getKey())) {
                    clave_valor = entry.getKey();
                    if (duplicados_mapa.containsKey(clave_valor)) {
                        contador_num = duplicados_mapa.get(clave_valor);
                        contador_num = contador_num + 1;
                        duplicados_mapa.put(clave_valor, contador_num);
                    } else {
                        contador_num = 1;
                        duplicados_mapa.put(clave_valor, contador_num);
                    }
                    int i = 1;
                    Boolean es_encontrado = false;
                    for (controles control: _controles_lista) {
                        if (control.clave.equals(clave_valor)) {
                            if (i == contador_num) {
                                es_encontrado  = true;
                                control.importar_captura(entry.getValue(), ok);
                            } else {
                                i = i + 1;
                            }
                        }
                    }
                    if (es_encontrado == false) {
                        in = ResourceBundles.getBundle(k_in_base);
                        ok.id = k_importar_no_encontrado;
                        ok.setTxt(ok.getTxt(), tr.in(in, "No se ha encontrado el control con la clave: ") + clave_valor);
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
}
