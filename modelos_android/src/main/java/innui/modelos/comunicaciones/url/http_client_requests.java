package innui.modelos.comunicaciones.url;

import static innui.modelos.comunicaciones.url.http_header_fields.k_host;

import innui.bases;
import android.innui.modelos_android.configuraciones.ResourceBundles;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;

import java.net.URI;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

/**
 *
 * @author emilio
 */
public class http_client_requests extends bases {
    public static String k_in_ruta = "assets/in/innui/modelos/comunicaciones/url/in";
    public static String k_connection = "Connection";
    public static String k_cache_control = "Cache-Control";
    public static String k_upgrade_insecure_requests = "Upgrade-Insecure-Requests";
    public static String k_user_agent = "User-Agent";
    public static String k_accept = "Accept";
    public static String k_sec_fetch_site = "Sec-Fetch-Site";
    public static String k_sec_fetch_mode = "Sec-Fetch-Mode";
    public static String k_sec_fetch_user = "Sec-Fetch-User";
    public static String k_sec_fetch_dest = "Sec-Fetch-Dest";
    public static String k_accept_encoding = "Accept-Encoding";
    public static String k_accept_language = "Accept-Language";
    public static String k_connection_valor_por_defecto = "keep-alive";
    public static String k_cache_control_valor_por_defecto = "Cache-Control";
    public static String k_upgrade_insecure_requests_valor_por_defecto = "Upgrade-Insecure-Requests";
    public static String k_user_agent_valor_por_defecto = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36";
    public static String k_accept_valor_por_defecto = "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9";
    public static String k_sec_fetch_site_valor_por_defecto = "none";
    public static String k_sec_fetch_mode_valor_por_defecto = "navigate";
    public static String k_sec_fetch_user_valor_por_defecto = "?1";
    public static String k_sec_fetch_dest_valor_por_defecto = "document";
    public static String k_accept_encoding_valor_por_defecto = "gzip, deflate, br";
    public static String k_accept_language_valor_por_defecto = "es-ES,es;q=0.9";
    public ResourceBundle in = null;
    public Map<String, String> http_request_map = new LinkedHashMap<>();

    public http_client_requests() throws Exception {
        in = ResourceBundles.getBundle(k_in_ruta);
        http_request_map.put(k_connection,k_connection_valor_por_defecto);
        http_request_map.put(k_cache_control,k_cache_control_valor_por_defecto);
        http_request_map.put(k_upgrade_insecure_requests,k_upgrade_insecure_requests_valor_por_defecto);
        http_request_map.put(k_user_agent,k_user_agent_valor_por_defecto);
        http_request_map.put(k_accept,k_accept_valor_por_defecto);
        http_request_map.put(k_sec_fetch_site,k_sec_fetch_site_valor_por_defecto);
        http_request_map.put(k_sec_fetch_mode,k_sec_fetch_mode_valor_por_defecto);
        http_request_map.put(k_sec_fetch_user,k_sec_fetch_user_valor_por_defecto);
        http_request_map.put(k_sec_fetch_dest,k_sec_fetch_dest_valor_por_defecto);
        http_request_map.put(k_accept_encoding,k_accept_encoding_valor_por_defecto);
        http_request_map.put(k_accept_language,k_accept_language_valor_por_defecto);
    }
    public String generar_get(URI uri, oks ok, Object... extra_array) throws Exception {
        return generar_peticion_http("GET", uri, ok);
    }

    public String generar_post(URI uri, Map <String, String> datos_mapa, oks ok, Object... extra_array) throws Exception {
        String retorno = null;
        boolean es_primero = true;
        try {
            retorno = generar_peticion_http("POST", uri, ok);
            for (Entry<String, String> entry: datos_mapa.entrySet()) {
                if (es_primero) {
                    es_primero = true;
                } else {
                    retorno = retorno + "&";
                }
                retorno = retorno + URLEncoder.encode(entry.getKey(), "ISO-8859-1")
                + "=" + URLEncoder.encode(entry.getValue(), "ISO-8859-1");
            }
        } catch (Exception e) {
            ok.setTxt(tr.in( in, "ERROR AL GENERAR POST. ")
                , e);
        }
        return retorno;
    }

    public String generar_delete(URI uri, oks ok, Object... extra_array) throws Exception {
        return generar_peticion_http("DELETE", uri, ok);
    }

    public String generar_put(URI uri, oks ok, Object... extra_array) throws Exception {
        return generar_peticion_http("PUT", uri, ok);
    }

    public String generar_connect(URI uri, oks ok, Object... extra_array) throws Exception {
        return generar_peticion_http("CONNECT", uri, ok);
    }

    public String generar_options(URI uri, oks ok, Object... extra_array) throws Exception {
        return generar_peticion_http("OPTIONS", uri, ok);
    }

    public String generar_trace(URI uri, oks ok, Object... extra_array) throws Exception {
        return generar_peticion_http("TRACE", uri, ok);
    }
    
    public String generar_head(URI uri, oks ok, Object... extra_array) throws Exception {
        return generar_peticion_http("HEAD", uri, ok);
    }
    
    public String generar_peticion_http(String metodo, URI uri, oks ok, Object... extra_array) throws Exception {
        String retorno = null;
        String request_line;
        String host;
        String parte_uri;
        parte_uri = uri.getPath();
        if (uri.getQuery() != null) {
            parte_uri = parte_uri + "?" + uri.getQuery();
        }
        request_line = metodo + " " + parte_uri + " HTTP/1.1" + "\n";
        host = k_host + ": " + uri.getHost();
        if (uri.getPort() >= 0) {
            host = host + ": " + uri.getPort() + "\n";
        }
        retorno = request_line
        + host;
        for (Entry<String, String> entry: http_request_map.entrySet()) {
            retorno = retorno + entry.getKey() + ": " + entry.getValue() + "\n";
        }
        retorno = retorno + "\n";
        return retorno;
    }    

}
