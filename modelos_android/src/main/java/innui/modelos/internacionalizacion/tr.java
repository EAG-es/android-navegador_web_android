/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package innui.modelos.internacionalizacion;

import innui.bases;

import java.util.ResourceBundle;

/**
 *
 * @author emilio
 */
public class tr extends bases {
    /**
     * Atributo para extender la funcionalidad reemplaando la que existe
     */
    public static tr base = null;
    /**
     * Localiza un texto traducido, o devuelve el propio texto buscado.
     * @param in Información don de localizar las traducciones
     * @param clave Clave de búsqueda
     * @return La cadena traducida, o la clave buscada.
     * @throws Exception Opción de notificar errores de excepción
     */
    public static String in(ResourceBundle in, String clave) throws Exception {
        try {
            String texto = null;
            if (base != null) {
                return base.in(in, clave);
            } else {
                try {
                    texto = in.getString(clave);
                } catch (Exception e) {
                    escribir(clave);
                    texto = clave;
                }
            }
            return texto;
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * Escribe un texto en la salida estandar de errores
     * @param texto
     * @throws Exception Opción de notificar errores de excepción
     */
    public static void escribir(String texto) throws Exception {
        try  {
            if (base != null) {
                base.escribir(texto);
            } else {
                System.err.println("tr: ¿" + texto + "?");
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
