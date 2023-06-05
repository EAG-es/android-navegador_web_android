package innui.modelos.comunicaciones.url;

import android.innui.modelos_android.configuraciones.ResourceBundles;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;
import innui.bases;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;

/**
 *
 * @author emilio
 */
public class urls_remotas extends bases {
    public static String k_in_ruta = "assets/in/innui/modelos/comunicaciones/url/in";
    public static String k_http = "http";
    public static String k_https = "https";
    /**
     * 
     * @param ok
     * @param extras_array
     * @return true si todo es correcto, false si hay error.
     * @throws Exception
     */
    public String descargar_y_procesar_usando_URL(String url_tex, oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return null; }
        String texto = null;
        ResourceBundle in;
        URI uri;
        uri = urls.generar_uri(url_tex, ok);
        if (ok.es) {
            ok.es = (uri != null);
        }
        if (ok.es) {
            if (uri.getScheme().equals(k_http)
             || uri.getScheme().equals(k_https)) {
                // Inserte aquí el código necesario para filtrar las peticiones realizadas en la URL                    
                texto = descargar_usando_URL(uri, ok); // Opción 1
//                texto = _procesar_con_httpclient(uri, ok); // Opción 2
                if (ok.es) {
                    ok.es = (texto != null);
                }
                if (ok.es) {
                    if (uri.getScheme().equals("http")) {
                        texto = corregir_html(uri, texto, ok);
                        if (ok.es) {
                            ok.es = (texto != null);
                        }
                    }
                }
                if (ok.es) {
                    if (uri.getScheme().equals("http")) {
                        texto = modificar_html(texto, ok);
                        if (ok.es) {
                            ok.es = (texto != null);
                        }
                    }
                }
            } else {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.setTxt(java.text.MessageFormat.format(tr.in(in, "PROTOCOLO NO SOPORTADO: {0} "), new Object[] {uri.getScheme()}));
                texto = null;
            }
        }
        return texto;
    }
    /**
     * 
     * @param uri
     * @param ok
     * @param extras_array
     * @return el texto leido, null si hay error
     * @throws Exception
     */
    public String descargar_usando_URL(URI uri, oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return null; }
        String retorno = null;
        String content_type;
        String charset;
        URL url = null;
        InputStream inputStream;
        URLConnection uRLConnection = null;
        HttpURLConnection httpURLConnection;
        int response_code;
        int tam;
        int pos;
        byte [] byte_array;
        String [] texto_array;
        ResourceBundle in;
        in = ResourceBundles.getBundle(k_in_ruta);
        try {
            url = uri.toURL();
            uRLConnection = url.openConnection();
            if (uRLConnection instanceof HttpURLConnection) {
                httpURLConnection = (HttpURLConnection) uRLConnection;
                response_code = httpURLConnection.getResponseCode();
                if (response_code >= 300 && response_code < 400) {
                    String location = httpURLConnection.getHeaderField("Location");
                    url = new URL(location);
                    retorno = descargar_usando_URL(url.toURI(), ok);
                } else {
                    if (response_code != 200) {
                        ok.setTxt(tr.in(in, "Error en la respuesta. " 
                                + response_code
                                + " " + httpURLConnection.getResponseMessage()));
                    }
                    if (ok.es) {
                        content_type = httpURLConnection.getContentType();
                        if (content_type == null) {
                            content_type = "text/html";
                        }
                        if (content_type.toLowerCase().contains("text/html")) {
                            charset = httpURLConnection.getContentEncoding();
                            if (charset == null) {
                                charset = "UTF-8";
                                texto_array = content_type.split(";");
                                for (String texto: texto_array) {
                                    pos = texto.toLowerCase().indexOf("charset=");
                                    if (pos >= 0) {
                                        charset = texto.substring(pos + "charset=".length());
                                        break;
                                    }
                                }
                            }
                            inputStream = httpURLConnection.getInputStream();
                            byte_array = new byte[1024];
                            retorno = "";
                            while (true) {
                                tam = inputStream.read(byte_array);
                                if (tam == -1) {
                                    break;
                                }
                                retorno = retorno + new String(byte_array, 0, tam, charset);
                            }
                        }
                    }
                }
            } else {
                inputStream = uRLConnection.getInputStream();
                byte_array = new byte[1024];
                retorno = "";
                while (true) {
                    tam = inputStream.read(byte_array);
                    if (tam == -1) {
                        break;
                    }
                    retorno = retorno + new String(byte_array, 0, tam, "UTF-8");
                }
            }
        } catch (Exception e) {
            ok.setTxt(tr.in(in, "Error al procesar la petición HTTP. "), e);
            retorno = null;
        }
        return retorno;
    }
    /**
     * Pone un mensaje de aviso
     * @param contenido_html
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public String modificar_html(String contenido_html, oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return null; }
        String retorno;
        String mensaje_aviso;
        ResourceBundle in;
        in = ResourceBundles.getBundle(k_in_ruta);
        mensaje_aviso = tr.in(in, "Solicitud de página empleando HTTP. No se recomienda este protocolo, debería emplear HTTPS. ");
        int tam = contenido_html.length();
        retorno = contenido_html.replaceFirst("(?i)<body.*>", "<body\\1><h1 style='color:red;'>"
        + mensaje_aviso
        + "</h1><div style='position: absolute;top: 0; left: 0; z-index:10;background:#ffc; padding:5px; border:1px solid #CCCCCC'><h1 style='color:red;'>"
        + mensaje_aviso
        + "</h1></div>");
        if (tam == retorno.length()) {
            // No es texto HTML
            retorno = "<html><body><pre>"
                    + retorno
                    + "<pre></body></html>";
        }
        return retorno;
    }
    /**
     * Hace cambios en el texto descargado, para completar las URL relativas que contenga.
     * @param uri
     * @param contenido_html
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public String corregir_html(URI uri, String contenido_html, oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return null; }
        String retorno = contenido_html;
        String auth_host;
        auth_host = uri.getRawAuthority();
        retorno = retorno.replaceAll("(?i)=\\s*\"//", "=\"https://");
        retorno = retorno.replaceAll("(?i)=\\s*'//", "='https://");
        retorno = retorno.replaceAll("(?i)=\\s*\"/", "=\"https://" + auth_host + "/");
        retorno = retorno.replaceAll("(?i)=\\s*'/", "='https://" + auth_host + "/");
        return retorno;
    }
    /**
     * Presenta un texto
     * @param texto_original
     * @param error_tex
     * @param ok
     * @param extras_array
     * @return true si todo es correcto, false si hay error.
     * @throws Exception
     */
    public String poner_error(String texto_original, String error_tex, oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return null; }
        String mensaje = texto_original;
        String nuevo_mensaje = "";
        ok.es = (mensaje != null);
        if (ok.es == false) {
            nuevo_mensaje = error_tex;
        } else if (mensaje.contains("<body>")) {
            nuevo_mensaje = mensaje.replace("<body>", "<body><h3 style='color:red'>" + error_tex + "</h3>");
        } else {
            nuevo_mensaje = error_tex;
        }
        return nuevo_mensaje;
    }
    
}
