/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.innui.modelos_android.configuraciones;

import static android.innui.modelos_android.configuraciones.iniciales.k_extension_properties;
import static android.innui.modelos_android.configuraciones.iniciales.k_ruta_relativa_internacionalizacion;
import static android.innui.modelos_android.configuraciones.iniciales.k_ruta_relativa_recursos;
import static android.innui.modelos_android.configuraciones.rutas.crear_ruta_desde_clase;

import innui.bases;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author emilio
 */
public class Resources extends bases {
    public static String k_in_ruta = "assets/in/android/innui/modelos_android/configuraciones/in";
    /**
     * Obtiene un recurso
     * @param ruta_relativa ruta del recurso
     * @return la URL del recurso
     * @throws Exception Opción de notificar errores de excepción
     */
    public static URL getResource(String ruta_relativa) throws Exception {
        oks ok = new oks();
        return getResource(Resources.class, ruta_relativa, ok);
    }
    /**
     * Obtiene un recurso
     * @param clase clase desde la que referir la ruta
     * @param ruta_relativa ruta del recurso
     * @return la URL del recurso
     * @throws Exception Opción de notificar errores de excepción
     */
    public static URL getResource(Class<?> clase, String ruta_relativa) throws Exception {
        oks ok = new oks();
        return getResource(clase, ruta_relativa, ok);
    }
    /**
     * Obtiene un recurso
     * @param ruta_relativa ruta del recurso
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return la URL del recurso
     * @throws Exception Opción de notificar errores de excepción
     */
    public static URL getResource(String ruta_relativa, oks ok, Object ... extra_array) throws Exception {
        if (ok.es == false) { return null; }
        return getResource(Resources.class, ruta_relativa, ok);
    }
    /**
     * Obtiene un recurso
     * @param clase clase desde la que referir la ruta
     * @param ruta_relativa ruta del recurso
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return la URL del recurso
     * @throws Exception Opción de notificar errores de excepción
     */
    public static URL getResource(Class<?> clase, String ruta_relativa, oks ok, Object ... extra_array) throws Exception {
        if (ok.es == false) { return null; }
        File file = null;
        ResourceBundle in;
        URL url = null;
        String ruta;
        try {
            ruta = crear_ruta_desde_clase(clase, ruta_relativa, ok);
            ok.es = (ruta != null);
            if (ok.es) {
                file = new File(ruta);
                ok.es = file.exists();
            }
            if (ok.es) {
                url = file.toURI().toURL();
            } else {
                url = clase.getResource(ruta_relativa);
                ok.es = (url != null);
                if (ok.es) {
                    _instalar_fuera(ruta_relativa, ok, extra_array);
                    if (ok.es == false) { return null; }
                }
            }
        } catch (Exception e) {
            in = ResourceBundles.getBundle(k_in_ruta);
            ok.setTxt(tr.in(in, "NO SE HA ENCONTRADO LA URL DEL RECURSO SOLICITADO. ")
                , ok.txt);
            ok.setTxt(ok.getTxt(), e);
            url = null;
        }
        return url;
    }
    /**
     * Obtiene un recurso como un stream
     * @param ruta_relativa ruta del recurso
     * @return el InpupStream del recurso
     * @throws Exception Opción de notificar errores de excepción
     */
    public static InputStream getResourceAsStream(String ruta_relativa) throws Exception {
        oks ok = new oks();
        return getResourceAsStream(Resources.class, ruta_relativa, ok);
    }
    /**
     * Obtiene un recurso como un stream
     * @param clase clase desde la que referir la ruta
     * @param ruta_relativa ruta del recurso
     * @return el InpupStream del recurso
     * @throws Exception Opción de notificar errores de excepción
     */
    public static InputStream getResourceAsStream(Class<?> clase, String ruta_relativa) throws Exception {
        oks ok = new oks();
        return getResourceAsStream(clase, ruta_relativa, ok);
    }
    /**
     * Obtiene un recurso como un stream
     * @param ruta_relativa ruta del recurso
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return el InpupStream del recurso
     * @throws Exception Opción de notificar errores de excepción
     */
    public static InputStream getResourceAsStream(String ruta_relativa, oks ok, Object ... extra_array) throws Exception {
        return getResourceAsStream(Resources.class, ruta_relativa, ok);
    }
    /**
     * Obtiene un recurso como un stream
     * @param clase clase desde la que referir la ruta
     * @param ruta_relativa ruta del recurso
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return el InpupStream del recurso
     * @throws Exception Opción de notificar errores de excepción
     */
    public static InputStream getResourceAsStream(Class<?> clase, String ruta_relativa, oks ok, Object ... extra_array) throws Exception {
        if (ok.es == false) { return null; }
        File file = null;
        ResourceBundle in;
        String ruta;
        InputStream inputstream = null;
        try {
            ruta = crear_ruta_desde_clase(clase, ruta_relativa, ok);
            ok.es = (ruta != null);
            if (ok.es) {
                file = new File(ruta);
                ok.es = file.exists();
            }
            if (ok.es) {
                URL url = file.toURI().toURL();
                inputstream = url.openStream();
            } else {
                inputstream = clase.getResourceAsStream(ruta_relativa);
                ok.es = (inputstream != null);
                if (ok.es) {
                    _instalar_fuera(ruta_relativa, ok, extra_array);
                    if (ok.es == false) { return null; }
                }
            }
        } catch (Exception e) {
            ok.txt = e.getMessage();
            if (ok.txt == null) {
                ok.txt = "";
            }
            in = ResourceBundles.getBundle(k_in_ruta);
            ok.setTxt(tr.in(in, "NO SE HA PODIDO CONSTRUIR UNA CORRIENTE DE ENTRADA DESDE EL RECURSO SOLICITADO. ")
                , ok.txt);
            ok.setTxt(ok.getTxt(), e);
            inputstream = null;
        }
        return inputstream;
    }
    /**
     * Instala el archivo en la ruta de estándar de destino.
     * @param ruta_base
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception
     */
    public static boolean _instalar_fuera(String ruta_base, oks ok, Object ... extra_array) throws Exception {
        if (ok.es == false) { return false; }
        try {
            File file = new File(ruta_base);
            ruta_base = file.getAbsolutePath();
            file = new File(k_ruta_relativa_recursos);
            String ruta_relativa_recursos = file.getAbsolutePath();
            file = new File(k_ruta_relativa_internacionalizacion);
            String ruta_relativa_internacionalizacion = file.getAbsolutePath();
            if (ruta_base.startsWith(ruta_relativa_recursos)
                    || ruta_base.startsWith(ruta_relativa_internacionalizacion)) {
                if (ruta_base.endsWith(k_extension_properties) == false) {
                    ruta_base = ruta_base + k_extension_properties;
                }
                recursos_modificables.instalar_fuera(ResourceBundles.class
                        , ruta_base, ok);
            }
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return ok.es;
    }
}
