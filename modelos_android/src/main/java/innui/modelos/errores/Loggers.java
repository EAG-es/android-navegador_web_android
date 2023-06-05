/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package innui.modelos.errores;

import static innui.modelos.errores.Strings_max_Handler.extraer;
import static java.lang.System.err;

import java.io.File;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
/**
 *
 * @author profesor
 */
public class Loggers extends SystemLogger_utils {
    public static String k_in_ruta = "assets/in/innui/modelos/errores/in";
    /**
     * Número máximo de registros que guardar
     */
    public static String k_registros_max_Integer = "registros_max_Integer";
    /**
     * Número máximo de registros que guardar, por defecto
     */
    public static Integer k_registros_max_Integer_por_defecto = 100;
    /**
     * Nivel mínimo: Ver java.util.logging.Level 
     */
    public static String k_min_level = "min_Level";
    /**
     * Nivel mínimo por defecto
     */
    public static java.util.logging.Level k_min_level_por_defecto = java.util.logging.Level.ALL;
    /**
     * Lista de manejadores del logger
     * Si no hay ninguno, se añaden: Strings_max_Handler(registros_max_Integer, min_level) y consoleHandler().
     */
    public static String k_handlers_List_Handler = "handlers_List_Handler";
    public static String k_handlers_no_string_max_handler = "handlers_no_string_max_handler";
    public static String k_handlers_no_ConsoleHandler = "handlers_no_ConsoleHandler";
    
    /**
     * Registra e inicia un logger con unas caracteristicas concretas, o por defecto
     * @param sufijo null, para el sufijo por defecto; un sufijo se usaría en lugar del sufijo por defecto
     * @param opciones_mapa null, para las opciones por defecto. Un mapa de opciones que aplicar.
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public static Loggers getLogger(String sufijo, Map<String, Object> opciones_mapa, oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return null; }
            Loggers logger = Loggers.getLogger(sufijo);
            reconfigurar(logger.logger, opciones_mapa, ok);
            if (ok.es == false) {
                logger = null;
            }
            return logger;
        } catch (Exception e) {
            throw e; // Ayuda para la depuración
        }
    }
    /**
     * reconfigura un logger con unas caracteristicas concretas, o por defecto
     * @param logger Logger que reconfigurar.
     * @param opciones_mapa null, para las opciones por defecto. Un mapa de opciones que aplicar.
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    @SuppressWarnings("unchecked")
    public static boolean reconfigurar(java.util.logging.Logger logger, Map<String, Object> opciones_mapa, oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            Integer registros_max_Integer = null;
            java.util.logging.Level min_level = null;
            List<Handler> handlers_list = null;
            Handler [] handler_array = null;
            Strings_max_Handler strings_max_Handler;
            ConsoleHandler consoleHandler;
            logger.setUseParentHandlers(false);
            if (opciones_mapa != null) {
                registros_max_Integer = (Integer) opciones_mapa.get(k_registros_max_Integer);
            }
            if (registros_max_Integer == null) {
                registros_max_Integer = k_registros_max_Integer_por_defecto;
                if (opciones_mapa != null) {
                    opciones_mapa.put(k_registros_max_Integer, registros_max_Integer);
                }
            }
            if (opciones_mapa != null) {
                min_level = (java.util.logging.Level) opciones_mapa.get(k_min_level);
            }
            if (min_level == null) {
                min_level = k_min_level_por_defecto;
                if (opciones_mapa != null) {
                    opciones_mapa.put(k_min_level, min_level);
                }
            }
            if (opciones_mapa != null) {
                handlers_list = (List<Handler>) opciones_mapa.get(k_handlers_List_Handler);
            }
            if (handlers_list == null) {
                handlers_list = new ArrayList<>();
                if (opciones_mapa == null || opciones_mapa.get(k_handlers_no_string_max_handler) == null) {
                    strings_max_Handler = new Strings_max_Handler(registros_max_Integer, min_level);
                    handlers_list.add(strings_max_Handler);
                }
                if (opciones_mapa == null || opciones_mapa.get(k_handlers_no_ConsoleHandler) == null) {
                    consoleHandler = new ConsoleHandler();
                    handlers_list.add(consoleHandler);
                }
                if (handlers_list.isEmpty() == false) {
                    handler_array = logger.getHandlers();
                    for (Handler handler: handler_array) {
                        logger.removeHandler(handler);
                    }
                    for (Handler handler: handlers_list) {
                        logger.addHandler(handler);
                    }
                    if (opciones_mapa != null) {
                        opciones_mapa.put(k_handlers_List_Handler, handlers_list);
                    }
                }
            }
            return ok.es;
        } catch (Exception e) {
            throw e; // Ayuda para la depuración
        }
    }
    /**
     * Obtiene el nombre por defecto del logger
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public static String obtener_nombre_por_defecto(oks ok, Object ... extra_array) throws Exception {
        if (ok.es == false) { return null; }
        String  retorno = null;
        ResourceBundle in;
        File file;
        URL url;
        try {
            ProtectionDomain protectionDomain = Loggers.class.getProtectionDomain();
            CodeSource codeSource = protectionDomain.getCodeSource();
            url = codeSource.getLocation();
            try {
                file = new File(url.toURI());
            } catch (Exception e) {                
                ok.es = false;
                return null;
            }
            retorno = file.getPath();
            if (retorno.toLowerCase().endsWith(".jar")) {
                retorno = file.getParent();
            }
            if (retorno.endsWith(File.separator) == false) {
                retorno = retorno + File.separator;
            }
            file = new File(retorno);
            retorno = file.getCanonicalPath();
            if (retorno.endsWith(File.separator) == false) {
                retorno = retorno + File.separator;
            }
        } catch (Exception e) {
            ok.setTxt(e);
            retorno = null;
        }
        return retorno;
    }
    /**
     * 
     * @param mensaje
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public boolean poner_error(String mensaje, oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            boolean ret = true;
            err.println(mensaje);
            return ret;
        } catch (Exception e) {
            throw e; // Ayuda para la depuración
        }
    }
    /**
     * Crea un nuevo looger si no hay uno ya creado con el sufijo indicado.
     * @param sufijo null para utilizar el nombre por defecto. O un sufijo que añadir al nombre del logger.
     * @param resourceBundleName Nombre con los recursos que utilizar.
     * @return null si hay error, un Logger si no lo hay.
     * @throws Exception
     */
    public static Loggers getLogger(String sufijo, String resourceBundleName) throws Exception {
        oks ok = new oks();
        java.util.logging.Logger logger = null;
        String nombre;
        Loggers loggers = null;
        try {
            nombre = Loggers.obtener_nombre_por_defecto(ok);
            if (nombre == null) {
                ok.iniciar();
                nombre = "";
            }
            if (sufijo == null || sufijo.trim().isEmpty()) {
                nombre = nombre + "error.log";
            } else {
                nombre = nombre + sufijo;
            }
            if (resourceBundleName == null) {
                logger = SystemLogger_utils.getLogger(nombre).getLogger();
            } else {
                logger = SystemLogger_utils.getLogger(nombre, resourceBundleName).getLogger();
            }
            if (logger.getUseParentHandlers()) {
                ok.es = reconfigurar(logger, null, ok);
            }
            if (ok.es) {
                loggers = new Loggers();
                loggers.logger = logger;
            } else {
                throw new RuntimeException(ok.txt);
            }
        } catch (Exception e) {
            throw e;
        }
        return loggers;
    }
    /**
     * Crea un nuevo looger si no hay uno ya creado con el sufijo indicado.
     * @param sufijo null para utilizar el nombre por defecto. O un sufijo que añadir al nombre del logger.
     * @return null si hay error, un Logger si no lo hay.
     */
    public static Loggers getLogger(String sufijo) throws Exception {
        Loggers loggers = Loggers.getLogger(sufijo, null);
        return loggers;
    }
    
    @Override
    public String toString() {
        String retorno = null;
        oks ok = new oks();
        try {
            Strings_max_Handler strings_max_Handler = null;
            strings_max_Handler = extraer(this, ok);
            if (strings_max_Handler != null) {
                retorno = strings_max_Handler.toString();
            }
        } catch (Exception e) {
            
        }
        return retorno;
    }
}
