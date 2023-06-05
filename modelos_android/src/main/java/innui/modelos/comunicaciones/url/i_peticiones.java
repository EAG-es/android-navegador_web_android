/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package innui.modelos.comunicaciones.url;

import innui.modelos.errores.oks;

import java.util.Map;

/**
 *
 * @author emilio
 */
public interface i_peticiones {
    public Map <String, String> leer_cabeceras(oks ok, Object... extra_array) throws Exception;
    public Map <String, String> leer_datos_peticion(oks ok, Object... extra_array) throws Exception;
    public Map <String, Object> leer_datos_adicionales(oks ok, Object... extra_array) throws Exception;
}
