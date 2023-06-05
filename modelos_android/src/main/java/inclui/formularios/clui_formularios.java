package inclui.formularios;

import inclui.utiles.clui_lecturas;
import innui.formularios.controles;
import android.innui.modelos_android.configuraciones.ResourceBundles;
import innui.modelos.errores.oks;
import innui.formularios.formularios;
import innui.modelos.internacionalizacion.tr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

/**
 *
 * @author emilio 
 */
public class clui_formularios extends formularios {
    public static String k_in_base = "assets/in/inclui/formularios/in";    
    public static String k_error_nombres_repetidos = "error_nombres_repetidos";
    public clui_lecturas _clui_lectura = new clui_lecturas();
    
    @Override
    public boolean _poner_control(controles control, oks ok, Object ... extras_array) throws Exception {
        super._poner_control(control, ok, extras_array);
        if (ok.es) {
            if (control.opciones_mapa == null) {
                control.opciones_mapa = new HashMap<>();
            }
            control.opciones_mapa.put(control_entradas.k_entradas_clui_lectura, _clui_lectura);
        }
        return ok.es;
    }    
    /**
     * Inicia el proceso de capruta y procesamiento de la información
     * @param modo_operacion Es la fase de operación de los controles del _formulario
     * @param ok
     * @param extras_array
     * @return 
     * @throws Exception 
     */
    @Override
    public boolean _iniciar_formulario(String modo_operacion, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            if (modo_operacion.equals(k_fase_procesamiento)) {
                super._iniciar_formulario(modo_operacion, ok, extras_array);
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    /**
     * Realiza los procesos posteriores a la valor_de_captura de los datos
     * @param modo_operacion Es la fase de operación de los controles del _formulario
     * @param ok
     * @param extras_array
     * @return 
     * @throws Exception 
     */
    @Override
    public boolean procesar(String modo_operacion, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            if (modo_operacion.equals(k_fase_procesamiento)) {
                while (true) {
                    ok.iniciar();
                    super.procesar(modo_operacion, ok, extras_array);
                    if (ok.es) {
                        if (_es_cancelar == false) {
                            revisar_nombres_repetidos(ok, extras_array);
                            if (ok.id.equals(k_error_nombres_repetidos)) {
                                oks ok_1 = new oks();
                                escribir_linea_error(ok.getTxt(), ok_1);
                                if (ok_1.es == false) {
                                    ok.setTxt(ok.getTxt(), ok_1.getTxt());
                                    break;
                                }
                                _es_repetir = true;
                            }
                        }
                        if (_es_repetir) {
                            continue;
                        }
                    }
                    break;
                }
            } else {
                super.procesar(modo_operacion, ok, extras_array);
            }
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }    
    
    public boolean revisar_nombres_repetidos(oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            Map<String, controles> nombres_mapa = new HashMap<>();
            Map<String, List<controles>> lista_controles_mapa = new HashMap<>();
            control_entradas entrada_primera;
            for (controles control: _controles_lista) {
                if (nombres_mapa.containsKey(control.clave) == false) {
                    nombres_mapa.put(control.clave, control);
                } else {
                    if (lista_controles_mapa.containsKey(control.clave)) {
                        lista_controles_mapa.get(control.clave).add(control);
                    } else {
                        lista_controles_mapa.put(control.clave, new ArrayList<>());
                        lista_controles_mapa.get(control.clave).add(nombres_mapa.get(control.clave));                            
                        lista_controles_mapa.get(control.clave).add(control);
                    }
                    if (control instanceof control_entradas) {
                        controles control_primero = lista_controles_mapa.get(control.clave).get(0);
                        if (control_primero instanceof control_entradas) {
                            entrada_primera = (control_entradas) control_primero;
                            if (entrada_primera._control_tipo.equals(((control_entradas) control)._control_tipo) == false) {
                                ok.setTxt("Grupo de controles con mismo nombre y distinto tipo de entrada: " + control.clave);
                                break;
                            }
                        } else {
                            ok.setTxt("Grupo de controles con mismo nombre y distinto tipo de entrada: " + control.clave);
                            break;
                        }
                    }
                }
            }
            if (ok.es == false) { return false; }
            ok.iniciar();
            for (Entry<String, List<controles>> entry: lista_controles_mapa.entrySet()) {
                int i = 0;
                for (controles control: entry.getValue()) {
                    if (control instanceof control_entradas) {
                        control_entradas entrada = (control_entradas) control;
                        if (entrada._control_tipo.equals(control_entradas.k_entradas_tipo_radio)) {
                            if (entrada.valor != null 
                             && entrada.valor.toString().startsWith(control_entradas.k_entradas_codigo_cancelar) == false) {
                                i = i + 1;
                            }
                        } else if (control.valor != null) {
                            i = i + 1;
                        }
                    } else if (control.valor != null) {
                        i = i + 1;
                    }
                    if (i > 1) {
                        ok.id = k_error_nombres_repetidos;
                        ok.setTxt("Grupo de controles con mismo nombre y más de un valor: " + entry.getKey() + " \n", ok.getTxt());
                        break;
                    }
                }
                if (i == 0) {
                    ok.setTxt("Grupo de controles con mismo nombre y ningún valor: " + entry.getKey() + " \n", ok.getTxt());
                }
            }                
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
    
    @Override
    public boolean capturar(oks ok, Object ... extras_array) throws Exception {
        ResourceBundle in;
        in = ResourceBundles.getBundle(k_in_base);
        ok.setTxt(tr.in(in, "No existe la fase de captura en este formulario. "));
        return ok.es;
    }
}
