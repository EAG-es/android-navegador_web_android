/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package innui.utiles.strings;

import innui.modelos.errores.oks;

import java.util.Date;
import java.util.Locale;

/**
 *
 * @author emilio
 */
public interface i_formatos {
    /**
     * Método de formato de texto que se puede sustituir para cambiar los formatos de presentación por defecto dado con su uso
     * @param id Identificador para facilitar el cambio de formato
     * @param formato Formato
     * @param ok Comunicar resultados
     * @param extras_array Parámetros para el formato
     * @return el texto formateado
     * @throws Exception Opción de notificar errores de excepción
     */
    public String formar_texto(String id, String formato, oks ok, Object... extras_array) throws Exception;
    /**
     * Método de formato de texto que se puede sustituir para cambiar los formatos de presentación por defecto dado con su uso
     * @param id Identificador para facilitar el cambio de formato
     * @param locale Información específica para la internacionalización.
     * @param formato Formato
     * @param ok Comunicar resultados
     * @param extras_array Parámetros para el formato
     * @return el texto formateado
     * @throws Exception Opción de notificar errores de excepción
     */
    public String formar_texto(String id, Locale locale, String formato, oks ok, Object... extras_array) throws Exception;
    /**
     * Método de formato de fecha y hora que se puede sustituir para cambiar los formatos de presentación por defecto dado con su uso
     * @param id Identificador para facilitar el cambio de formato
     * @param date fecha a la que dar formato
     * @param ok Comunicar resultados
     * @param extras_array Parámetros para el formato
     * @return el texto formateado
     * @throws Exception Opción de notificar errores de excepción
     */
    public String formar_fecha(String id, Date date, oks ok, Object... extras_array) throws Exception;
    /**
     * Método de formato de fecha y hora que se puede sustituir para cambiar los formatos de presentación por defecto dado con su uso
     * @param id Identificador para facilitar el cambio de formato
     * @param locale Información específica para la internacionalización.
     * @param date fecha a la que dar formato
     * @param ok Comunicar resultados
     * @param extras_array Parámetros para el formato
     * @return el texto formateado
     * @throws Exception Opción de notificar errores de excepción
     */
    public String formar_fecha(String id, Locale locale, Date date, oks ok, Object... extras_array) throws Exception;
    /**
     * Método de formato de numeros decimales que se puede sustituir para cambiar los formatos de presentación por defecto dado con su uso
     * @param id Identificador para facilitar el cambio de formato
     * @param numero Número doble
     * @param ok Comunicar resultados
     * @param extras_array Parámetros para el formato
     * @return el texto formateado
     * @throws Exception Opción de notificar errores de excepción
     */
    public String formar_numero(String id, double numero, oks ok, Object... extras_array) throws Exception;
    /**
     * Método de formato de numeros decimales que se puede sustituir para cambiar los formatos de presentación por defecto dado con su uso
     * @param id Identificador para facilitar el cambio de formato
     * @param locale Información específica para la internacionalización.
     * @param numero Número doble
     * @param ok Comunicar resultados
     * @param extras_array Parámetros para el formato
     * @return el texto formateado
     * @throws Exception Opción de notificar errores de excepción
     */
    public String formar_numero(String id, Locale locale, double numero, oks ok, Object... extras_array) throws Exception;

}
