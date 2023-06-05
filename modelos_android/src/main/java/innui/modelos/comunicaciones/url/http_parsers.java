package innui.modelos.comunicaciones.url;

import innui.modelos.errores.oks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class http_parsers implements i_peticiones {
    public static final int STATUS_CONTINUE = 100;
    public static final int STATUS_SWITCHING_PROTOCOLS = 101;
    public static final int STATUS_OK = 200;
    public static final int STATUS_CREATED = 201;
    public static final int STATUS_ACCEPTED = 202;
    public static final int STATUS_NON_AUTHORITATIVE_INFORMATION = 203;
    public static final int STATUS_NO_CONTENT = 204;
    public static final int STATUS_RESET_CONTENT = 205;
    public static final int STATUS_PARTIAL_CONTENT = 206;
    public static final int STATUS_MULTIPLE_CHOICES = 300;
    public static final int STATUS_MOVED_PERMANENTLY = 301;
    public static final int STATUS_FOUND = 302;
    public static final int STATUS_SEE_OTHER = 303;
    public static final int STATUS_NOT_MODIFIED = 304;
    public static final int STATUS_USE_PROXY = 305;
    public static final int STATUS_UNUSED = 306;
    public static final int STATUS_TEMPORARY_REDIRECT = 307;
    public static final int STATUS_BAD_REQUEST = 400;
    public static final int STATUS_UNAUTHORIZED = 401;
    public static final int STATUS_PAYMENT_REQUIRED = 402;
    public static final int STATUS_FORBIDDEN = 403;
    public static final int STATUS_NOT_FOUND = 404;
    public static final int STATUS_METHOD_NOT_ALLOWED = 405;
    public static final int STATUS_NOT_ACCEPTABLE = 406;
    public static final int STATUS_PROXY_AUTHENTICATION_REQUIRED = 407;
    public static final int STATUS_REQUEST_TIMEOUT = 408;
    public static final int STATUS_CONFLICT = 409;
    public static final int STATUS_GONE = 410;
    public static final int STATUS_LENGTH_REQUIRED = 411;
    public static final int STATUS_PRECONDITION_FAILED = 412;
    public static final int STATUS_REQUEST_ENTITY_TOO_LARGE = 413;
    public static final int STATUS_REQUEST_URI_TOO_LONG = 414;
    public static final int STATUS_UNSUPPORTED_MEDIA_TYPE = 415;
    public static final int STATUS_REQUESTED_RANGE_NOT_SATISFIABLE = 416;
    public static final int STATUS_EXPECTATION_FAILED = 417;
    public static final int STATUS_INTERNAL_SERVER_ERROR = 500;
    public static final int STATUS_NOT_IMPLEMENTED = 501;
    public static final int STATUS_BAD_GATEWAY = 502;
    public static final int STATUS_SERVICE_UNAVAILABLE = 503;
    public static final int STATUS_GATEWAY_TIMEOUT = 504;
    public static final int STATUS_HTTP_VERSION_NOT_SUPPORTED = 505;
    
    public static final String[][] HttpReplies = {
    {"100", "Continue"},
    {"101", "Switching Protocols"},
    {"200", "OK"},
    {"201", "Created"},
    {"202", "Accepted"},
    {"203", "Non-Authoritative Information"},
    {"204", "No Content"},
    {"205", "Reset Content"},
    {"206", "Partial Content"},
    {"300", "Multiple Choices"},
    {"301", "Moved Permanently"},
    {"302", "Found"},
    {"303", "See Other"},
    {"304", "Not Modified"},
    {"305", "Use Proxy"},
    {"306", "(Unused)"},
    {"307", "Temporary Redirect"},
    {"400", "Bad Request"},
    {"401", "Unauthorized"},
    {"402", "Payment Required"},
    {"403", "Forbidden"},
    {"404", "Not Found"},
    {"405", "Method Not Allowed"},
    {"406", "Not Acceptable"},
    {"407", "Proxy Authentication Required"},
    {"408", "Request Timeout"},
    {"409", "Conflict"},
    {"410", "Gone"},
    {"411", "Length Required"},
    {"412", "Precondition Failed"},
    {"413", "Request Entity Too Large"},
    {"414", "Request-URI Too Long"},
    {"415", "Unsupported Media Type"},
    {"416", "Requested Range Not Satisfiable"},
    {"417", "Expectation Failed"},
    {"500", "Internal Server Error"},
    {"501", "Not Implemented"},
    {"502", "Bad Gateway"},
    {"503", "Service Unavailable"},
    {"504", "Gateway Timeout"},
    {"505", "HTTP Version Not Supported"}};

    public static Map<String, String> k_extensiones_tipos_mime_mapa;
    public BufferedReader reader;
    public String method, path;
    public Map<String, String> cabeceras_mapa, datos_peticion_mapa;
    public Map<String, Object> datos_adicionales_mapa;
    public Integer[] ver;

    public http_parsers(InputStream inputStream) {
        reader = new BufferedReader(new InputStreamReader(inputStream));
        method = "";
        path = "";
        ver = new Integer[2];
        cabeceras_mapa = new LinkedHashMap<>();
        datos_peticion_mapa = new LinkedHashMap<>();
        datos_adicionales_mapa = new LinkedHashMap<>();
        // Algunos tipos MIME según la extensión del archivo
        k_extensiones_tipos_mime_mapa = new LinkedHashMap<>();
        k_extensiones_tipos_mime_mapa.put(".aac", "audio/aac"); // Archivo de audio AAC
        k_extensiones_tipos_mime_mapa.put(".abw", "application/x-abiword"); // Documento AbiWord
        k_extensiones_tipos_mime_mapa.put(".arc", "application/octet-stream"); // Documento de Archivo (múltiples archivos incrustados)
        k_extensiones_tipos_mime_mapa.put(".avi", "video/x-msvideo"); // AVI: Audio Video Intercalado
        k_extensiones_tipos_mime_mapa.put(".azw", "application/vnd.amazon.ebook"); // Formato  eBook Amazon Kindle 
        k_extensiones_tipos_mime_mapa.put(".bin", "application/octet-stream"); // Cualquier tipo de datos binarios
        k_extensiones_tipos_mime_mapa.put(".bz", "application/x-bzip"); // Archivo BZip
        k_extensiones_tipos_mime_mapa.put(".bz2", "application/x-bzip2"); // Archivo BZip2
        k_extensiones_tipos_mime_mapa.put(".csh", "application/x-csh"); // Script C-Shell
        k_extensiones_tipos_mime_mapa.put(".css", "text/css"); // Hojas de estilo (CSS)
        k_extensiones_tipos_mime_mapa.put(".csv", "text/csv"); // Valores separados por coma (CSV)
        k_extensiones_tipos_mime_mapa.put(".doc", "application/msword"); // Microsoft Word
        k_extensiones_tipos_mime_mapa.put(".epub", "application/epub+zip"); // Publicación Electrónica (EPUB)
        k_extensiones_tipos_mime_mapa.put(".gif", "image/gif"); // Graphics Interchange Format (GIF)
        k_extensiones_tipos_mime_mapa.put(".htm", "text/html"); // Hipertexto (HTML)
        k_extensiones_tipos_mime_mapa.put(".html", "text/html"); // Hipertexto (HTML)
        k_extensiones_tipos_mime_mapa.put(".ico", "image/x-icon"); // Formato Icon
        k_extensiones_tipos_mime_mapa.put(".ics", "text/calendar"); // Formato iCalendar
        k_extensiones_tipos_mime_mapa.put(".jar", "application/java-archive"); // Archivo Java (JAR)
        k_extensiones_tipos_mime_mapa.put(".jpeg", "image/jpeg"); // Imágenes JPEG
        k_extensiones_tipos_mime_mapa.put(".jpg", "image/jpeg"); // Imágenes JPEG
        k_extensiones_tipos_mime_mapa.put(".js", "application/javascript"); // JavaScript (ECMAScript)
        k_extensiones_tipos_mime_mapa.put(".json", "application/json"); // Formato JSON
        k_extensiones_tipos_mime_mapa.put(".mid", "audio/midi"); // Interfaz Digital de Instrumentos Musicales (MIDI)
        k_extensiones_tipos_mime_mapa.put(".midi", "audio/midi"); // Interfaz Digital de Instrumentos Musicales (MIDI)
        k_extensiones_tipos_mime_mapa.put(".mpeg", "video/mpeg"); // Video MPEG
        k_extensiones_tipos_mime_mapa.put(".mpkg", "application/vnd.apple.installer+xml"); // Paquete de instalación de Apple
        k_extensiones_tipos_mime_mapa.put(".odp", "application/vnd.oasis.opendocument.presentation"); // Documento de presentación de OpenDocument
        k_extensiones_tipos_mime_mapa.put(".ods", "application/vnd.oasis.opendocument.spreadsheet"); // Hoja de Cálculo OpenDocument
        k_extensiones_tipos_mime_mapa.put(".odt", "application/vnd.oasis.opendocument.text"); // Documento de texto OpenDocument
        k_extensiones_tipos_mime_mapa.put(".oga", "audio/ogg"); // Audio OGG
        k_extensiones_tipos_mime_mapa.put(".ogv", "video/ogg"); // Video OGG
        k_extensiones_tipos_mime_mapa.put(".ogx", "application/ogg"); // OGG
        k_extensiones_tipos_mime_mapa.put(".pdf", "application/pdf"); // Adobe Portable Document Format (PDF)
        k_extensiones_tipos_mime_mapa.put(".ppt", "application/vnd.ms-powerpoint"); // Microsoft PowerPoint
        k_extensiones_tipos_mime_mapa.put(".rar", "application/x-rar-compressed"); // Archivo RAR
        k_extensiones_tipos_mime_mapa.put(".rtf", "application/rtf"); // Formato de Texto Enriquecido (RTF)
        k_extensiones_tipos_mime_mapa.put(".sh", "application/x-sh"); // Script Bourne shell
        k_extensiones_tipos_mime_mapa.put(".svg", "image/svg+xml"); // Gráficos Vectoriales (SVG)
        k_extensiones_tipos_mime_mapa.put(".swf", "application/x-shockwave-flash"); // Small web format (SWF) o Documento Adobe Flash
        k_extensiones_tipos_mime_mapa.put(".tar", "application/x-tar"); // Aerchivo Tape (TAR)
        k_extensiones_tipos_mime_mapa.put(".tif", "image/tiff"); // Formato de archivo de imagen etiquetado (TIFF)
        k_extensiones_tipos_mime_mapa.put(".tiff", "image/tiff"); // Formato de archivo de imagen etiquetado (TIFF)
        k_extensiones_tipos_mime_mapa.put(".ttf", "font/ttf"); // Fuente TrueType
        k_extensiones_tipos_mime_mapa.put(".txt", "text/plain"); 
        k_extensiones_tipos_mime_mapa.put(".vsd", "application/vnd.visio"); // Microsft Visio
        k_extensiones_tipos_mime_mapa.put(".wav", "audio/x-wav"); // Formato de audio de forma de onda (WAV)
        k_extensiones_tipos_mime_mapa.put(".weba", "audio/webm"); // Audio WEBM
        k_extensiones_tipos_mime_mapa.put(".webm", "video/webm"); // Video WEBM
        k_extensiones_tipos_mime_mapa.put(".webp", "image/webp"); // Imágenes WEBP
        k_extensiones_tipos_mime_mapa.put(".woff", "font/woff"); // Formato de fuente abierta web (WOFF)
        k_extensiones_tipos_mime_mapa.put(".woff2", "font/woff2"); // Formato de fuente abierta web (WOFF)
        k_extensiones_tipos_mime_mapa.put(".xhtml", "application/xhtml+xml"); // XHTML
        k_extensiones_tipos_mime_mapa.put(".xls", "application/vnd.ms-excel"); // Microsoft Excel
        k_extensiones_tipos_mime_mapa.put(".xml", "application/xml"); // XML
        k_extensiones_tipos_mime_mapa.put(".xul", "application/vnd.mozilla.xul+xml"); // XUL
        k_extensiones_tipos_mime_mapa.put(".zip", "application/zip"); // Archivo ZIP
        k_extensiones_tipos_mime_mapa.put(".3gp", "video/3gpp"); // Contenedor de audio/video 3GPP
        k_extensiones_tipos_mime_mapa.put(".3g2", "video/3gpp2"); // Contenedor de audio/video 3GPP2
        k_extensiones_tipos_mime_mapa.put(".7z", "application/x-7z-compressed"); // Archivo 7-zip
    }

    public int  parseRequest() throws IOException {
        String texto_inicial, prms[], lineas_array[], temporal_array[];
        int  ret, idx, i;

        ret = STATUS_OK; // default is OK now
        texto_inicial = reader.readLine();
        if (texto_inicial == null || texto_inicial.length() == 0) {
            return STATUS_NO_CONTENT;
        }
        if (Character.isWhitespace(texto_inicial.charAt(0))) {
            // starting whitespace, return bad request
            return STATUS_BAD_REQUEST;
        }

        lineas_array = texto_inicial.split("\\s");
        if (lineas_array.length != 3) {
            return STATUS_BAD_REQUEST;
        }

        if (lineas_array[2].indexOf("HTTP/") == 0 && lineas_array[2].indexOf('.') > 5) {
            temporal_array = lineas_array[2].substring(5).split("\\.");
            try {
                ver[0] = Integer.parseInt(temporal_array[0]);
                datos_adicionales_mapa.put("version_entero", ver[0]);
                ver[1] = Integer.parseInt(temporal_array[1]);
                datos_adicionales_mapa.put("version_decimal", ver[1]);
            } catch (NumberFormatException nfe) {
                ret = STATUS_BAD_REQUEST;
            }
        } else {
            ret = STATUS_BAD_REQUEST;
        }

        if (lineas_array[0].equals("GET") || lineas_array[0].equals("HEAD")) {
            method = lineas_array[0];
            datos_adicionales_mapa.put("method", method);
            idx = lineas_array[1].indexOf('?');
            if (idx < 0) {
                path = lineas_array[1];
            } else {
                path = URLDecoder.decode(lineas_array[1].substring(0, idx), "ISO-8859-1");
                prms = lineas_array[1].substring(idx + 1).split("&");
                for (i = 0; i < prms.length; i++) {
                    temporal_array = prms[i].split("=");
                    if (temporal_array.length == 2) {
                        // we use ISO-8859-1 as temporary charset and then
                        // String.getBytes("ISO-8859-1") to get the data
                        datos_peticion_mapa.put(URLDecoder.decode(temporal_array[0], "ISO-8859-1"),
                                URLDecoder.decode(temporal_array[1], "ISO-8859-1"));
                    } else if (temporal_array.length == 1 && prms[i].indexOf('=') == prms[i].length() - 1) {
                        // handle empty string separatedly
                        datos_peticion_mapa.put(URLDecoder.decode(temporal_array[0], "ISO-8859-1"), "");
                    }
                }
            }
            datos_adicionales_mapa.put("path", path);
            parseHeaders();
            if (cabeceras_mapa == null) {
                ret = STATUS_BAD_REQUEST;
            }
        } else if (lineas_array[0].equals("POST")) {
            ret = STATUS_NOT_IMPLEMENTED; // not implemented
        } else if (ver[0] == 1 && ver[1] >= 1) {
            if (lineas_array[0].equals("OPTIONS")
                    || lineas_array[0].equals("PUT")
                    || lineas_array[0].equals("DELETE")
                    || lineas_array[0].equals("TRACE")
                    || lineas_array[0].equals("CONNECT")) {
                ret = STATUS_NOT_IMPLEMENTED; // not implemented
            }
        } else {
            ret = STATUS_BAD_REQUEST;
        }

        if (ver[0] == 1 && ver[1] >= 1 && getHeader("Host") == null) {
            ret = STATUS_BAD_REQUEST;
        }

        return ret;
    }

    private void parseHeaders() throws IOException {
        String linea;
        int  i;

        // that fscking rfc822 allows multiple lines, we don't care now
        linea = reader.readLine();
        while (!linea.equals("")) {
            i = linea.indexOf(':');
            if (i < 0) {
                cabeceras_mapa = null;
                break;
            } else {
                cabeceras_mapa.put(linea.substring(0, i).toLowerCase(), linea.substring(i + 1).trim());
            }
            linea = reader.readLine();
        }
    }

    public String getMethod() {
        return method;
    }

    public String getHeader(String key) {
        if (cabeceras_mapa != null) {
            return (String) cabeceras_mapa.get(key.toLowerCase());
        } else {
            return null;
        }
    }

    public Map<String, String> getHeaders() {
        return cabeceras_mapa;
    }

    public String getRequestPath() {
        return path;
    }

    public String getParam(String key) {
        return (String) datos_peticion_mapa.get(key);
    }

    public Map getParams() {
        return datos_peticion_mapa;
    }

    public String getVersion() {
        return ver[0] + "." + ver[1];
    }

    public int  compareVersion(int  mayor, int  menor) {
        if (mayor < ver[0]) {
            return -1;
        } else if (mayor > ver[0]) {
            return 1;
        } else if (menor < ver[1]) {
            return -1;
        } else if (menor > ver[1]) {
            return 1;
        } else {
            return 0;
        }
    }

    public static String getHttpReply(int codigo) {
        String key, ret;
        int  i;

        ret = null;
        key = "" + codigo;
        for (i = 0; i < HttpReplies.length; i++) {
            if (HttpReplies[i][0].equals(key)) {
                ret = codigo + " " + HttpReplies[i][1];
                break;
            }
        }

        return ret;
    }

    @Override
    public Map<String, String> leer_cabeceras(oks ok, Object... extra_array) throws Exception {
        return cabeceras_mapa;
    }

    @Override
    public Map<String, String> leer_datos_peticion(oks ok, Object... extra_array) throws Exception {
        return datos_peticion_mapa;
    }

    @Override
    public Map<String, Object> leer_datos_adicionales(oks ok, Object... extra_array) throws Exception {
        return datos_adicionales_mapa;
    }
}
