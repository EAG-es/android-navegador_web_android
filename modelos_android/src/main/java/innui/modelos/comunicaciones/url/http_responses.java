package innui.modelos.comunicaciones.url;

import static innui.modelos.comunicaciones.url.http_header_fields.k_connection;
import static innui.modelos.comunicaciones.url.http_header_fields.k_content_type;
import static innui.modelos.comunicaciones.url.http_header_fields.k_date;

import android.innui.modelos_android.configuraciones.ResourceBundles;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.TimeZone;

/**
 *
 * @author emilio
 */
public class http_responses implements i_respuestas {
    public static final String k_in_ruta = "assets/in/innui/modelos/comunicaciones/url/in";
    public ResourceBundle in = null;
    public http_parsers httpParser;
    OutputStream outputstream;
    public Map<String, String> header_fields_mapa = new LinkedHashMap<> ();
    public boolean es_respuesta_iniciada = false;
    
    public http_responses() throws Exception {
        in = ResourceBundles.getBundle(k_in_ruta);
    }
    public boolean poner_httpParser(http_parsers httpParser, oks ok, Object... extra_array) throws Exception {
        this.httpParser = httpParser;
        return ok.es;
    }
    public boolean poner_serie_de_salida(OutputStream outputstream, oks ok, Object... extra_array) throws Exception {
        this.outputstream = outputstream;
        return ok.es;
    }

    @Override
    public boolean iniciar_respuesta(int status, String content_type, oks ok, Object... extra_array) throws Exception {
        String protocolo;
        String status_text;
        String mensaje = "";
        try {
            if (es_respuesta_iniciada == false) {
                es_respuesta_iniciada = true;
            } else {
                ok.setTxt(tr.in(in, "HAY UN INICIO DE RESPUESTA PREVIO. "));
            }
            if (ok.es) {
                protocolo = "HTTP/1.1";
                status_text = http_parsers.getHttpReply(status);
                mensaje = protocolo + " " + status_text + "\n";
                mensaje = mensaje + leer_Date(ok) + "\n";
            }
            if (ok.es) {
                if (content_type != null) {
                    mensaje = mensaje + k_content_type + ": " + content_type + "\n";
                }
                for (Entry<String, String> entry :header_fields_mapa.entrySet()) {
                    mensaje = mensaje + entry.getKey() + ": " + entry.getValue() + "\n";
                }
                mensaje = mensaje + k_connection + ": " + "Keep-Alive" + "\n";
                mensaje = mensaje + "\n";
                outputstream.write(mensaje.getBytes("ISO-8859-1"));
            }
        } catch (Exception e) {
            ok.setTxt(java.text.MessageFormat.format(tr.in(in, "ERROR AL INICIAR RESPUESTA. {0}"), new Object[] {""})
                , e);
        }
        return ok.es;
    }
    
    @Override
    public boolean escribir_respuesta(byte [] mensaje, int inicio, int tam, oks ok, Object... extra_array) throws Exception {
        try {
            if (es_respuesta_iniciada) {
                outputstream.write(mensaje, inicio, tam);
            } else {
                ok.setTxt(tr.in(in, "NO HA HABIDO UN INICIO DE RESPUESTA PREVIO. "));
            }
        } catch (Exception e) {
            ok.setTxt(java.text.MessageFormat.format(tr.in(in, "ERROR AL ESCRIBIR RESPUESTA. {0}"), new Object[] {""})
                , e);
        }
        return ok.es;
    }

    @Override
    public boolean terminar_respuesta(boolean es_cerrar, oks ok, Object... extra_array) throws Exception {
        try {            
            if (es_cerrar) {
                outputstream.close();
            }
        } catch (Exception e) {
            ok.setTxt(java.text.MessageFormat.format(tr.in(in, "ERROR AL TERMINAR RESPUESTA. {0}"), new Object[] {""})
                , e);
        }
        return ok.es;
    }

    @Override
    public boolean completar_cabeceras(Map<String, String> datos_mapa, oks ok, Object... extra_array) throws Exception {
        if (es_respuesta_iniciada == false) {
            header_fields_mapa.putAll(datos_mapa);
        } else {
            ok.setTxt(tr.in(in,"HA HABIDO UN INICIO DE RESPUESTA. "));
        }
        return ok.es;
    }

    public static String leer_Date(oks ok, Object... extra_array) throws Exception {
        SimpleDateFormat simpleDateFormat;
        String retorno;
        simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        retorno = k_date + ": " + simpleDateFormat.format(new Date()) + " GMT";
        return retorno;
    }
    
}
