/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package innui.modelos.concurrencias;

import innui.modelos.errores.Loggers;
import innui.modelos.errores.oks;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author emilio
 */
public class Threads extends Thread {
    public static String k_parar = "parar";
    public static String k_pausar = "pausar";
    public static String k_despausar = "despausar";
    public static String k_interrumpir = "interrumpir";
    public static String k_interrumpido = "interrumpido";
    /**
     * Mapa preperado para el paso de datos en concurrencia.
     */
    public Map<String, Object> mapa = Collections.synchronizedMap(new HashMap<>());
    /**
     * Objeto oks que compartir concurrentemente 
     */
    public AtomicReference<oks> ref_ok = new AtomicReference<>(new oks());
    /**
     * Objeto Loggers que compartir concurrentemente (null por defecto)
     */
    public AtomicReference<Loggers> ref_logger = new AtomicReference<>();
    
    public AtomicReference<oks> getOk() {
        return ref_ok;
    }

    public void setOk(AtomicReference<oks> ok) {
        this.ref_ok = ok;
    }

    public AtomicReference<Loggers> getRef_logger() {
        return ref_logger;
    }

    public void setRef_logger(AtomicReference<Loggers> logger) {
        this.ref_logger = logger;
    }   
    
    public Loggers getLogger() {
        return ref_logger.get();
    }

    public void setLogger(Loggers logger) {
        this.ref_logger.set(logger);
    }   
    /**
     * Sleep que no lanza excepción
     * @param milisegundos
     * @param ok
     * @return true si tiene éxito
     */
    public static boolean sleep(long milisegundos, oks ok) {
        try {
            if (ok.es == false) { return ok.es; }
            Thread.sleep(milisegundos);
            return ok.es;
        } catch (InterruptedException e) {
            ok.id = k_interrumpido;
            ok.setTxt(e);
            return ok.es;
        } catch (Exception e) {
            ok.setTxt(e);
            return ok.es;
        }
    }   
    /**
     * Sleep que no lanza excepción
     * @param milisegundos
     * @param nanosegundos
     * @param ok
     * @return true si tiene éxito
     */
    public static boolean sleep(long milisegundos, int nanosegundos, oks ok) {
        try {
            Thread.sleep(milisegundos, nanosegundos);
            return ok.es;
        } catch (InterruptedException e) {
            ok.id = k_interrumpido;
            ok.setTxt(e);
            return ok.es;
        } catch (Exception e) {
            ok.setTxt(e);
            return ok.es;
        }
    }   
    /**
     * interrupt que no lanza excepción
     * @param ok
     * @return true si tiene éxito
     */
    public boolean interrupt(oks ok) {
        try {
            if (ok.es == false) { return ok.es; }
            interrupt();
            return ok.es;
        } catch (Exception e) {
            ok.setTxt(e);
            return ok.es;
        }
    }   
    
}
