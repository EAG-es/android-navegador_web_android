package android.ingui.navegador_web_android;

import static android.ingui.navegador_web_android.Webview_simpleFragment.k_no_procesar_event_llamada_a_url;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Map;

import innui.bases;
import innui.modelos.errores.oks;

public abstract class Webview_simpleFragment_implementaciones
        extends bases
        implements Webview_simpleFragment.I_Webview_simpleController_extensiones {
    public static String k_in_ruta;
    static {
        String paquete_tex = Navegador_web_android_activity.class.getPackage().getName();
        paquete_tex = paquete_tex.replace(".", File.separator);
        k_in_ruta = "assets/in/" + paquete_tex + "/in";
    }
    public static String k_contenido_web = "contenido_web";
    public StringBuilder contenido_web = new StringBuilder();

    /**
     * Constructor
     */
    public Webview_simpleFragment_implementaciones() {
        oks ok = new oks();
        try {
            iniciar(ok);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Método para iniciar los datos necesarios pra repetir la partida
     * @param ok
     * @param extras_array
     * @return true si todo es correcto, false si hay error.
     */
    public boolean iniciar(oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return false; }
        return ok.es;
    }
    /**
     * @param datos_mapa mapa con los datos que salvar
     * @param ok
     * @param extras_array
     * @return true si todo es correcto, false si hay error.
     */
    public boolean salvar_datos_serializables(Map<String, Object> datos_mapa, oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return false; }
        try {
            datos_mapa.put(k_contenido_web, contenido_web);
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return ok.es;
    }
    /**
     *
     * @param datos_mapa mapa con los datos que restaurar
     * @param ok
     * @param extras_array
     * @return true si todo es correcto, false si hay error.
     */
    public boolean restaurar_datos_serializables(Map<String, Object> datos_mapa, oks ok, Object ... extras_array) throws Exception {
        contenido_web = (StringBuilder) datos_mapa.get(contenido_web);
        return ok.es;
    }
    /**
     * Procesa las peticiones URL que recibe el Webview_simpleController
     * @param url
     * @param ok
     * @param extras_array
     * @return true si todo es correcto, false si hay error.
     */
    @Override
    public Boolean procesar_evento_llamada_a_url(URL url, oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return false; }
        try {
            ok.id = k_no_procesar_event_llamada_a_url;
            ok.setTxt(k_no_procesar_event_llamada_a_url);
//            Map<String, String> datos_mapa = new LinkedHashMap<>();
//            urls.extraer_parametros_query(url, datos_mapa, ok, extras_array);
//            if (ok.es == false) {
//                poner_error(ok.getTxt(), ok, extras_array);
//            }
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return ok.es;
    }
    /**
     * Presenta un texto
     * @param texto
     * @param ok
     * @param extras_array
     * @return true si todo es correcto, false si hay error.
     */
    public boolean poner_texto(String texto, oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return false; }
        escribir_texto(texto, ok, extras_array);
        if (ok.es == false) { return false; }
        presentar_contenido(ok, extras_array);
        if (ok.es == false) { return false; }
        return ok.es;
    }
    /**
     * Presenta un texto
     * @param texto
     * @param ok
     * @param extras_array
     * @return true si todo es correcto, false si hay error.
     */
    public boolean poner_error(String texto, oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return false; }
        String mensaje = leer_texto(ok);
        String nuevo_mensaje = "";
        if (mensaje != null) {
            mensaje = texto;
        }
        if (mensaje.contains("<body>")) {
            nuevo_mensaje = mensaje.replace("<body>", "<body><h3 style='color:red'>" + texto + "</h3>");
            escribir_texto(nuevo_mensaje, ok, extras_array);
            if (ok.es == false) { return false; }
            presentar_contenido(ok, extras_array);
            if (ok.es == false) { return false; }
            escribir_texto(mensaje, ok, extras_array);
        } else {
            escribir_texto(texto, ok, extras_array);
            if (ok.es == false) { return false; }
            presentar_contenido(ok, extras_array);
        }
        return ok.es;
    }
    /**
     * Escribe texto en la zona de presentación de texto
     * @param texto
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    @Override
    public boolean escribir_texto(String texto, oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return false; }
        contenido_web.replace(0, contenido_web.length(), texto);
        return ok.es;
    }
    /**
     * Lee texto de la zona e presentación de texto
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    @Override
    public String leer_texto(oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return null; }
        return contenido_web.toString();
    }
    /**
     * Carga el contenido en un hilo
     * @param ok
     * @param extras_array
     * @return true si tiene éxito, o false si hay error
     */
    /**
     * Carga el contenido en un hilo
     * @param ok
     * @param extras_array
     * @return true si tiene éxito, o false si hay error
     * @throws Exception
     */
    public abstract boolean presentar_contenido(oks ok, Object ... extras_array) throws Exception;
    /**
     * Carga el contenido en un hilo
     * @param uri
     * @param ok
     * @param extras_array
     * @return true si tiene éxito, o false si hay error
     * @throws Exception
     */
    public abstract boolean presentar_contenido(URI uri, oks ok, Object ... extras_array) throws Exception;

}
