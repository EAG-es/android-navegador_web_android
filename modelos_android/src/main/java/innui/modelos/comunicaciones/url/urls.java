package innui.modelos.comunicaciones.url;

import android.innui.modelos_android.configuraciones.ResourceBundles;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;
import innui.bases;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

/**
 * Clase para manejar URLs
 */
public class urls extends bases {
    public static String k_in_ruta = "assets/in/innui/modelos/comunicaciones/url/in";
    /**
     * Protocolo por defecto 
     */
    public static String k_protocolo_por_defecto = "https"; //NOI18N
    public static ResourceBundle in = null;
    
    public urls() throws Exception {
        in = ResourceBundles.getBundle(k_in_ruta);
    }
    /**
     * Obtiene los parámetros de la consulta de una url
     * @param url URL de donde extraerlos.
     * @param objects_mapa Mapa donde almacenar las claves y los valores extraidos.
     * @param ok
     * @param extras_array
     * @return true si tiene éxito, false si hay algún error
     * @throws Exception
     */
    public static boolean extraer_parametros_query(URL url, Map<String, String> objects_mapa, oks ok, Object ... extras_array) throws Exception {
        String mensaje;
        mensaje = url.getQuery();
        extraer_parametros_texto(mensaje, objects_mapa, ok);
        return ok.es;
    }
    /**
     * Obtiene los parámetros de la consulta de una url
     * @param texto Texto de donde extraerlos.
     * @param objects_mapa Mapa donde almacenar las claves y los valores extraidos.
     * @param ok
     * @param extras_array
     * @return true si tiene éxito, false si hay algún error
     * @throws Exception
     */
    public static boolean extraer_parametros_texto(String texto, Map<String, String> objects_mapa, oks ok, Object ... extras_array) throws Exception {
        boolean ret = true;
        String [] partes_array = { "" }; //NOI18N
        String clave;
        Map<String, List<String>> listas_mapa = new LinkedHashMap<>();
        List <String> lista;
        try {
            if (texto != null && texto.isEmpty() == false) {
                String [] parametros_array = texto.split("&"); //NOI18N
                for (String cadena : parametros_array) {
                    partes_array = cadena.split("="); //NOI18N
                    if (partes_array.length == 2) {
                        partes_array[0] = URLDecoder.decode(partes_array[0], "UTF-8"); //NOI18N
                        partes_array[1] = URLDecoder.decode(partes_array[1], "UTF-8"); //NOI18N
                    } else if (partes_array.length == 1) {
                        clave = partes_array[0];
                        partes_array = new String [2];
                        partes_array[0] = URLDecoder.decode(clave, "UTF-8"); //NOI18N
                        partes_array[1] = ""; //NOI18N
                    } else {
                        ret = false;
                        ok.setTxt(tr.in(in, "NO SE HAN RECONOCIDO LOS PARÁMETROS. "));
                        break;
                    }
                    if (ret) {
                        lista = listas_mapa.get(partes_array[0]);
                        if (lista == null) {
                            lista = new ArrayList<>();
                            lista.add(partes_array[1]);
                            listas_mapa.put(partes_array[0], lista);
                        } else {
                            lista.add(partes_array[1]);
                        }
                    }
                }
                if (ret) {
                    for (Entry<String, List<String>> entrada: listas_mapa.entrySet()) {
                        if (entrada.getValue().size() != 1) {
                            int i = 0;
                            for (String entrada_lista: entrada.getValue()) {
                                objects_mapa.put(entrada.getKey()+"["+i+"]", entrada_lista); //NOI18N
                                i = i + 1;
                            }
                        } else {
                            objects_mapa.put(entrada.getKey(), entrada.getValue().get(0));
                        }
                    }
                }
            }
        } catch (Exception e) {
            ok.setTxt(java.text.MessageFormat.format(tr.in(in, "ERROR AL EXTRAER LOS PARAMETROS DE LA QUERY. {0}"), new Object[] {""})
            , e);
            ret = false;
        }
        return ret;
    }
    /**
     * Extrae la parte de la ruta de la url que sigue al macador indicado.
     * @param url_texto Texto con la URlL de la que extraer la ruta
     * @param marcador Marcdor que buscar en la ruta, a partir del que extraer.
     * @param ok
     * @param extras_array
     * @return El path dentro de la URL; o null si no encuentra el marcador
     * @throws Exception
     */
    public static String extraer_path(String url_texto, String marcador, oks ok, Object ... extras_array) throws Exception {
        String resto = null; 
        int pos_fin;
        int pos_inicio = url_texto.indexOf(marcador);
        if (pos_inicio >= 0) {
            pos_inicio = pos_inicio + marcador.length();
            pos_fin = url_texto.indexOf("?"); //NOI18N
            if (pos_fin >= 0) {
                resto = url_texto.substring(pos_inicio, pos_fin);
            } else {
                resto = url_texto.substring(pos_inicio);
            }
            pos_fin = url_texto.indexOf("#"); //NOI18N
            if (pos_fin >= 0) {
                resto = resto.substring(pos_inicio, pos_fin);
            }
            pos_inicio = resto.indexOf("://"); //NOI18N
            if (pos_inicio >= 0) {
                resto = resto.substring(pos_inicio + "://".length()); //NOI18N
            }           
        }
        return resto;
    }
    /**
     * Extrae el protocolo de un texto con una URL
     * @param url_texto Texto con la URlL de la que extraer el protocolo.
     * @param ok
     * @param extras_array
     * @return El protocolo si tiene éxito, null si hay algún error
     * @throws Exception
     */
    public static String extraer_protocolo(String url_texto, oks ok, Object ... extras_array) throws Exception {
        String retorno = null; 
        int pos_inicio;
        pos_inicio = url_texto.indexOf("://"); //NOI18N
        if (pos_inicio >= 0) {
            retorno = url_texto.substring(0, pos_inicio);
        }            
        return retorno;
    }
    /**
     * Extrae las subcarpetas de una ruta de url.
     * @param ruta Ruta de url de la que extraer
     * @param url_fragmentos_path_lista Lista conteniendo las subcarpetas.
     * @param ok
     * @param extras_array
     * @return true si tiene éxito, false si hay algún error
     * @throws Exception
     */
    public static boolean extraer_fragmentos_path(String ruta, List <String> url_fragmentos_path_lista, oks ok, Object ... extras_array) throws Exception {
        String [] resto_partes_array = ruta.split("/"); //NOI18N
        int i = 0;
        for (String parte: resto_partes_array) {
            if (parte.isEmpty() == false) {
                url_fragmentos_path_lista.add(parte);
                i = i + 1;
            }
        }
        return ok.es;
    }
    /**
     * Añade el protocolo a un texto con una url, si no lo tiene.
     * @param url_texto Texto con la URL
     * @param protocolo_si_falta Protocolo que poner, si falta.
     * @param ok
     * @param extras_array
     * @return la URL coimpletada con el protocolo, null si hay error.
     * @throws Exception
     */
    public static URL completar_URL(String url_texto, String protocolo_si_falta, oks ok, Object ... extras_array) throws Exception {
        URL retorno = null;
        String texto;
        String url_path = ""; //NOI18N
        try {
            url_path = extraer_path(url_texto, "", ok); //NOI18N
            if (url_path != null) {
                if (url_path.contains("://") == false) { //NOI18N
                    if (protocolo_si_falta.isEmpty()) {
                        texto = k_protocolo_por_defecto + "://" + url_texto; //NOI18N
                    } else {
                        texto = protocolo_si_falta + "://" + url_texto; //NOI18N
                    }
                    retorno = new URL(texto);
                } else {
                    retorno = new URL(url_texto);
                }
            }
        } catch (Exception e) {
            ok.setTxt(java.text.MessageFormat.format(tr.in(in, "ERROR EN COMPLETAR_URL. {0}"), new Object[] {""})
                , e);
            retorno = null;
        }
        return retorno;
    }
    /**
     * 
     * @param uri_texto
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */        
    public static URI generar_uri(String uri_texto, oks ok, Object ... extras_array) throws Exception {
        URI retorno = null;
        URL url = null;
        int pos;
        String query;
        String fragmento = "";
        String [] query_array;
        String [] param_array;
        try {
            url = new URL(uri_texto);
            retorno = url.toURI();
        } catch (Exception ex) {
            try {
                String nueva_url = "";
                String protocol = url.getProtocol();
                if (protocol == null) {
                    protocol = "http";
                }
                nueva_url = protocol + "://";
                String authority = url.getAuthority();
                authority = URLEncoder.encode(authority, "UTF-8"); 
                if (authority == null) {
                    authority = "";
                } else {
                    authority = URLEncoder.encode(authority, "UTF-8"); 
                }
                nueva_url = nueva_url + authority;
                int port = url.getPort();
                if (port != -1) {
                    nueva_url = nueva_url + port;
                }
                String path = url.getFile();
                if (path != null) {
                    File file = new File(path);
                    URI file_uri = file.toURI();
                    path = file_uri.getRawPath(); 
                    if (path != null) {
                        nueva_url = nueva_url + path;
                    }
                    query = file_uri.getRawQuery();
                    if (query != null) {
                        nueva_url = nueva_url + "?" + query;
                    }
                }
                String ref = url.getRef();
                if (ref != null) {
                    ref = URLEncoder.encode(ref, "UTF-8"); 
                    nueva_url = nueva_url + "#" + ref;
                }
                url = new URL(nueva_url);
                retorno = url.toURI();
            } catch (Exception exc) {
                try {
                    pos = uri_texto.indexOf("#");
                    if (pos >= 0) {
                        fragmento = uri_texto.substring(pos+1);
                        uri_texto = uri_texto.substring(0, pos);
                    }
                    pos = uri_texto.indexOf("?");
                    if (pos >= 0) {
                        query = uri_texto.substring(pos+1);
                        uri_texto = uri_texto.substring(0, pos);
                        query_array = query.split("&");
                        query = "";
                        for (String parte: query_array) {
                            if (query.isEmpty() == false) {
                                query = query + "&";
                            }
                            param_array = parte.split("=");
                            query = query + URLEncoder.encode(param_array[0], "ISO-8859-1") + "=";
                            if (param_array.length > 1) {
                                query = query + URLEncoder.encode(param_array[1], "ISO-8859-1");
                            }
                        }
                        uri_texto = uri_texto + query;
                    }
                    if (fragmento.isEmpty() == false) {
                        uri_texto = uri_texto + "#" + fragmento;
                    }
                    url = new URL(uri_texto);
                    retorno = url.toURI();
                } catch (Exception e) {
                    ok.setTxt(java.text.MessageFormat.format(tr.in(in, "NO SE HA PODIDO ENTENDER LA DIRECCIÓN WEB INDICADA. {0}"), new Object[] {""})
                        , e);
                    retorno = null;
                }
            }
        }
        return retorno;
    }
}
