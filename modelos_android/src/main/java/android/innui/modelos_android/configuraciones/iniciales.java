package android.innui.modelos_android.configuraciones;

import static android.innui.modelos_android.configuraciones.recursos_modificables.k_no_contexto_android;

import android.content.Context;

import innui.modelos.errores.Loggers;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;
import innui.bases;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 *
 * @author emilio
 */
public abstract class iniciales extends bases implements Serializable {
    public static String k_ruta_aplicacion = null;
    public static String k_ruta_relativa_recursos = "/assets/re"; //NOI18N
    public static String k_ruta_relativa_internacionalizacion = "/assets/in"; //NOI18N
    public static String k_extension_properties = ".properties";
    public static String k_ruta_relativa_properties = "/re/configuraciones" + k_extension_properties;  //NOI18N
    public static String k_in_ruta = "assets/in/android/innui/modelos_android/configuraciones/in";  //NOI18N
    public static String k_propiedad_duplicada = "propiedad_duplicada";
    public Properties properties = null;

    /**
     * Determina el nombre de la aplicación
     * @param mainclass
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public boolean _poner_aplicacion(Class<?> mainclass, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) {
                return false;
            }
            String ruta_properties;
            ruta_properties = mainclass.getPackage().getName();
            k_ruta_aplicacion = "/" + ruta_properties;
            return ok.es;
        } catch (Exception e) {
            throw e; // Ayuda para la depuración
        }
    }
    /**
     * Este método debería ser llamado desde iniciar. 
     * Realiza la configuración por defecto del logger, 
     * instala las carpetas de recursos (k_ruta_relativa_recursos) y de internacionalización (k_ruta_relativa_internacionalizacion) fuera del jar, 
     * lee el archivo de propiedades desde k_ruta_relativa_properties
     * @param mainclass Clase desde donde localizar el origen de las rutas relativas.
     * @param ok Comunicar resultados
     * @param extras_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public boolean _iniciar_desde_clase(Context context, Class<?> mainclass, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            ResourceBundle in;
            String ruta_properties;
            if (k_ruta_aplicacion == null) {
                ok.setTxt("_poner_aplicacion no ha sido llamado. ");
                return false;
            }
            in = ResourceBundles.getBundle(k_in_ruta);
            _logger = Loggers.getLogger(null);
            bases.k_logger_nombre.setLength(0);
            bases.k_logger_nombre.append(_logger.getName());
            ok.no_nul(_logger, tr.in(in, "Logger nulo. "));
            if (ok.es == false) { return false; }
            recursos_modificables.instalar_carpeta_fuera(context, mainclass
                    , k_ruta_relativa_recursos
                    , ok);
            if (ok.es == false) {
                if (ok.id.equals(k_no_contexto_android) == false) {
                    return false;
                }
            }
            recursos_modificables.instalar_carpeta_fuera(context, mainclass
                    , k_ruta_relativa_internacionalizacion
                    , ok);
            if (ok.es == false) {
                if (ok.id.equals(k_no_contexto_android) == false) {
                    return false;
                }
            }
            if (properties == null) {
                properties = new Properties();
            }
            // load a properties file
            ruta_properties = mainclass.getPackage().getName();
            File file = new File(k_ruta_relativa_recursos, ruta_properties + k_extension_properties);
            ruta_properties = file.getCanonicalPath();
            InputStream inputStream = Resources.getResourceAsStream(mainclass, ruta_properties);
            if (inputStream == null) {
                ruta_properties = k_ruta_relativa_properties;
                inputStream = Resources.getResourceAsStream(mainclass, ruta_properties);
            }
            if (inputStream != null) {
                Properties propertie = new Properties();
                propertie.load(inputStream);
                for (Entry<Object, Object> entry: propertie.entrySet()) {
                    if (properties.containsKey(entry.getKey())) {
                        ok.id = k_propiedad_duplicada;
                        ok.gravedad = oks.k_gravedad_baja;
                        ok.setTxt(tr.in(in, "Propiedad duplicada: ") + entry.getKey());
                        break;
                    }
                    properties.setProperty((String) entry.getKey(), (String) entry.getValue());
                }
                inputStream.close();
            }
            return ok.es;
        } catch (Exception e) {
            throw e; // Ayuda para la depuración
        }
    }
    /**
     * Este método debería ser llamado desde iniciar. 
     * Realiza la configuración por defecto del logger, 
     * instala las carpetas de recursos (k_ruta_relativa_recursos) y de internacionalización (k_ruta_relativa_internacionalizacion) fuera del jar, 
     * lee el archivo de propiedades desde k_ruta_relativa_properties
     * @param mainclass Clase desde donde localizar el origen de las rutas relativas.
     * @param ok Comunicar resultados
     * @param extras_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public boolean _terminar_desde_clase(Class<?> mainclass, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            ResourceBundle in;
            in = ResourceBundles.getBundle(k_in_ruta);
            // load a properties file
            String ruta_properties;
            ruta_properties = mainclass.getPackage().getName();
            File file = new File(k_ruta_relativa_recursos, ruta_properties + k_extension_properties);
            ruta_properties = file.getCanonicalPath();
            InputStream inputStream = Resources.getResourceAsStream(mainclass, ruta_properties);
            if (inputStream == null) {
                ruta_properties = k_ruta_relativa_properties;
                inputStream = Resources.getResourceAsStream(mainclass, ruta_properties);
            }
            if (inputStream != null) {
                boolean es_cambio = false;
                Properties propertie = new Properties();
                propertie.load(inputStream);
                for (Entry<Object, Object> entry: properties.entrySet()) {
                    if (propertie.containsKey(entry.getKey())) {
                        es_cambio = true;
                        propertie.put(entry.getKey(), entry.getValue());
                    }
                }
                inputStream.close();
                if (es_cambio) {
                    ruta_properties = rutas.crear_ruta_desde_clase(mainclass, ruta_properties, ok);
                    if (ok.es == false) { return false; }
                    ok.no_nul(ruta_properties);
                    if (ok.es == false) { return false; }
                    try (OutputStream outputStream = new FileOutputStream(ruta_properties)) {
                        propertie.store(outputStream, "");
                    }
                }
            }
            return ok.es;
        } catch (Exception e) {
            throw e; // Ayuda para la depuración
        }
    }
    /**
     * Método que se debería ejecutar después de iniciar.
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public abstract boolean run(oks ok, Object ... extra_array) throws Exception;
    /**
     * Método para iniciar los modelos_android (deben llamar a _iniciar_desde_clase).
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public abstract boolean iniciar(oks ok, Object ... extra_array) throws Exception;
    /**
     * Método para terminar los modelos_android (deben llamar a _terminar_desde_clase).
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public abstract boolean terminar(oks ok, Object ... extra_array) throws Exception;
}
