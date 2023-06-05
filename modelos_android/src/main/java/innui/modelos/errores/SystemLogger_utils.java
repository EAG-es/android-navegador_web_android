/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package innui.modelos.errores;

import java.util.ResourceBundle;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emilio
 */
public class SystemLogger_utils {

    public Logger logger;
    /**
     * Obtiene un logger con el nombre indicado
     * @param nombre Nombre del logger qeu obtener
     * @return Un nuevo objeto SystemLogger_utils.
     */
    public static SystemLogger_utils getLogger(String nombre) throws Exception {
        SystemLogger_utils systemLogger_utils = new SystemLogger_utils();
        systemLogger_utils.logger = Logger.getLogger(nombre);
        return systemLogger_utils;
    }
    /**
     * Obtiene un logger con el nombre indicado
     * @param nombre Nombre del logger qeu obtener
     * @param resourceBundleName Nombre del archivo de propiedades con los recursos de traducción
     * @return Un nuevo objeto SystemLogger_utils.
     */
    public static SystemLogger_utils getLogger(String nombre, String resourceBundleName) throws Exception {
        SystemLogger_utils systemLogger_utils = new SystemLogger_utils();
        systemLogger_utils.logger = Logger.getLogger(nombre, resourceBundleName);
        return systemLogger_utils;
    }

    public String getName() {
        return logger.getName();
    }

    public boolean isLoggable(Level level) {
        return logger.isLoggable(level);
    }

    public void log(Level level, ResourceBundle bundle, String msg, Throwable thrown) {
        logger.logrb(level, "--", "--", bundle, msg, thrown);
    }

    public void log(Level level, ResourceBundle bundle, String format, Object... params) {
        logger.logrb(level, "--", "--", bundle, format, params);
    }

    public void log(Level level, String msg) {
        logger.log(level, msg);
    }

    public void log(Level level, Supplier<String> msgSupplier) {
        logger.log(level, msgSupplier);
    }

    public void log(Level level, Object obj) {
        logger.log(level, "", obj);
    }

    public void log(Level level, String msg, Throwable thrown) {
        logger.log(level, msg, thrown);
    }

    public void log(Level level, Supplier<String> msgSupplier, Throwable thrown) {
        logger.log(level, thrown, msgSupplier);
    }

    public void log(Level level, String format, Object... params) {
        logger.log(level, format, params);
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

}
