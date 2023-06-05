/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package innui;

import innui.modelos.errores.oks;

/**
 *
 * @author emilio
 */
public interface i_escrituras {
    /**
     * Escribe texto en la salida estándar, si no se sustituye.
     * @param texto Texto que escribir
     * @param ok Comunicar resultados
     * @param extras_array Parámetros para el formato
     * @return true si tiene éxito.
     * @throws Exception Opción de notificar errores de excepción
     */
    public boolean escribir(String texto, oks ok, Object... extras_array) throws Exception;
    /**
     * Escribe una línea de texto en la salida estándar, si no se sustituye.
     * @param texto Texto que escribir
     * @param ok Comunicar resultados
     * @param extras_array Parámetros para el formato
     * @return true si tiene éxito.
     * @throws Exception Opción de notificar errores de excepción
     */
    public boolean escribir_linea(String texto, oks ok, Object... extras_array) throws Exception;
    /**
     * Escribe una línea de texto en la salida de error, si no se sustituye.
     * @param texto Texto que escribir
     * @param ok Comunicar resultados
     * @param extras_array Parámetros para el formato
     * @return true si tiene éxito.
     */
    public boolean escribir_error(String texto, oks ok, Object... extras_array);
    /**
     * Escribe una línea de texto en la salida de error, si no se sustituye.
     * @param texto Testo que escribir
     * @param ok Comunicar resultados
     * @param extras_array Parámetros para el formato
     * @return true si tiene éxito.
     */
    public boolean escribir_linea_error(String texto, oks ok, Object... extras_array);
    /**
     * Escribe una línea de texto en la salida de log, si no se sustituye.
     * @param texto Texto que escribir
     * @param nivel Nivel de mensaje de log
     * @param ok Comunicar resultados
     * @param extras_array Parámetros para el formato
     * @return true si tiene éxito.
     */
    public boolean escribir_log(String texto, int nivel, oks ok, Object... extras_array);
    /**
     * Escribe una línea de texto INFO en la salida de log, si no se sustituye.
     * @param texto Texto que escribir
     * @param ok Comunicar resultados
     * @param extras_array Parámetros para el formato
     * @return true si tiene éxito.
     */
    public boolean escribir_log(String texto, oks ok, Object... extras_array);
    /**
     * Limita los mensaje de log que son registrados. OFF los quita todos, ALL los pone todos, etc...
     * @param limite Nivel de mensaje de log (puede ser null, entonces por defecto es quitar log: OFF)
     * @param ok Comunicar resultados
     * @param extras_array Parámetros para el formato
     * @return true si tiene éxito.
     */
    public boolean limitar_log(int limite, oks ok, Object... extras_array);
    /**
     * Responde true si el nivel es posible que se registre, segun el nivel límite establecido
     * @param limite Nivel de mensaje de log (puede ser null, entonces por defecto es quitar log: OFF)
     * @param ok Comunicar resultados
     * @param extras_array Parámetros para el formato
     * @return true el log se puede hacer para ese limite.
     */
    public boolean ser_posible_log(int limite, oks ok, Object... extras_array);
    
}
