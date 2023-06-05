/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package innui.utiles.strings;

import static innui.utiles.bigdecimals.BigDecimals.k_in_ruta;

import android.innui.modelos_android.configuraciones.ResourceBundles;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;
import innui.bases;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

/**
 *
 * @author emilio
 */
public class Strings extends bases {

    public static String hacer_nul_vacio(String texto, oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return null; }
            if (texto == null) {
                return "";
            } else {
                return texto;
            }
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * Pasa un texto a bigdecimal, si es posible.
     * @param texto Texto conteniendo el número
     * @param ok Comunicar resultados
     * @param extra_array Hasta 2 parámetros, según la división de BigDecimal que se desee hacer.
     * @return El número extraido, o null.
     * @throws Exception Opción de notificar errores de excepción
     */
    public static BigDecimal texto_a_bigdecimal(String texto, oks ok, Object ... extra_array) throws Exception {
        BigDecimal retorno = null;
        ResourceBundle in;
        in = ResourceBundles.getBundle(k_in_ruta);
        try {
            // No funciona con "," decimal. retorno = BigDecimal.valueOf(Double.valueOf(texto));
            double doble = DecimalFormat.getNumberInstance().parse(texto).doubleValue();
            retorno = BigDecimal.valueOf(doble);
        } catch (Exception e) {
            ok.setTxt(tr.in(in, "Formato incorrecto de número. "), e);
        }
        return retorno;
    }
    /**
     * Pasa un texto a doble, si es posible.
     * @param texto Texto conteniendo el número
     * @param ok Comunicar resultados
     * @param extra_array Hasta 2 parámetros, según la división de BigDecimal que se desee hacer.
     * @return El número extraido, o null.
     * @throws Exception Opción de notificar errores de excepción
     */
    public static Double texto_a_doble(String texto, oks ok, Object ... extra_array) throws Exception {
        Double retorno = null;
        ResourceBundle in;
        in = ResourceBundles.getBundle(k_in_ruta);
        try {
            // No funciona con "," decimal. retorno = BigDecimal.valueOf(Double.valueOf(texto));
            retorno = DecimalFormat.getNumberInstance().parse(texto).doubleValue();
        } catch (Exception e) {
            ok.setTxt(tr.in(in, "Formato incorrecto de número. "), e);
        }
        return retorno;
    }
    /**
     * Pasa un texto a BigInteger, si es posible.
     * @param texto Texto conteniendo el número
     * @param ok Comunicar resultados
     * @param extra_array Hasta 2 parámetros, según la división de BigDecimal que se desee hacer.
     * @return El número extraido, o null.
     * @throws Exception Opción de notificar errores de excepción
     */
    public static BigInteger texto_a_biginteger(String texto, oks ok, Object ... extra_array) throws Exception {
        BigInteger retorno = null;
        ResourceBundle in;
        in = ResourceBundles.getBundle(k_in_ruta);
        try {
            // No funciona con "," decimal. retorno = BigDecimal.valueOf(Double.valueOf(texto));
            Long largo = DecimalFormat.getNumberInstance().parse(texto).longValue();
            retorno = BigInteger.valueOf(largo);
        } catch (Exception e) {
            ok.setTxt(tr.in(in, "Formato incorrecto de número. "), e);
        }
        return retorno;
    }
    /**
     * Pasa un texto a BigInteger, si es posible.
     * @param texto Texto conteniendo el número
     * @param ok Comunicar resultados
     * @param extra_array Hasta 2 parámetros, según la división de BigDecimal que se desee hacer.
     * @return El número extraido, o null.
     * @throws Exception Opción de notificar errores de excepción
     */
    public static Long texto_a_largo(String texto, oks ok, Object ... extra_array) throws Exception {
        Long retorno = null;
        ResourceBundle in;
        in = ResourceBundles.getBundle(k_in_ruta);
        try {
            // No funciona con "," decimal. retorno = BigDecimal.valueOf(Double.valueOf(texto));
            retorno = DecimalFormat.getNumberInstance().parse(texto).longValue();
        } catch (Exception e) {
            ok.setTxt(tr.in(in, "Formato incorrecto de número. "), e);
        }
        return retorno;
    }
    
}
