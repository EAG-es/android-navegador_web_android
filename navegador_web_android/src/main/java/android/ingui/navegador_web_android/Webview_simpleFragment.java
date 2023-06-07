package android.ingui.navegador_web_android;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.provider.DocumentsContract.EXTRA_INITIAL_URI;

import android.content.Intent;
import android.ingui.utiles.alertas;
import android.innui.modelos_android.configuraciones.ResourceBundles;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.Serializable;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;

public class Webview_simpleFragment extends Fragment {
    public static String k_in_ruta;
    static {
        String paquete_tex = Navegador_web_android_activity.class.getPackage().getName();
        paquete_tex = paquete_tex.replace(".", File.separator);
        k_in_ruta = "assets/in/" + paquete_tex + "/in";
    }
    public static String k_no_procesar_event_llamada_a_url = "no_procesar_event_llamada_a_url";
    public static interface I_Webview_simpleController_extensiones extends Serializable {
        /**
         * Procesa una URL (reemplazable en Webview_simpleController_hijo)
         * @param url URL que procesar
         * @param ok
         * @param extras_array
         * @return null si hay error o no se debe procesar la petición; o el texto resultante de procesar la petición
         * @throws Exception
         */
        public Boolean procesar_evento_llamada_a_url(URL url, oks ok, Object ... extras_array) throws Exception;
        /**
         * Escribe una copia del texto del Webview
         * @param texto
         * @param ok
         * @param extras_array
         * @return true si tiene éxito, o false si hay error
         * @throws Exception
         */
        public boolean escribir_texto(String texto, oks ok, Object ... extras_array) throws Exception;
        /**
         * Lee una copia del texto del Webview
         * @param ok
         * @param extras_array
         * @return true si tiene éxito, o false si hay error
         * @throws Exception
         */
        public String leer_texto(oks ok, Object ... extras_array) throws Exception;
    }
    public static interface I_Webview_simpleController_capturas extends Serializable {
        public boolean poner_error(String mensaje, oks ok, Object ... extras_array);
        public default boolean procesar_estado(String estado, String url_texto, oks ok, Object ... extras_array) throws Exception {
            return true;
        }
        public default boolean procesar_avance(Number avance, oks ok, Object ... extras_array) throws Exception {
            return true;
        }
    }
    public String webview_texto;
    public WebView webview;
    public I_Webview_simpleController_extensiones i_Webview_simpleController_extension;
    public I_Webview_simpleController_capturas i_Webview_simpleController_captura;
//    public boolean es_intentar_sin_certificado_valido = false;
//    public sslcontext_sin_verificar_hostnames sslcontext_sin_verificar_hostname = new sslcontext_sin_verificar_hostnames();
    public URI uri = null;
//    public ResourceBundle in = null;
    public ValueCallback<Uri[]> file_chooser_ruta_callback;
    public final static int k_filechooser_resultcode = 1;
    ActivityResultLauncher<Intent> escuchador_file_chooser_activityResultLauncher;
    /**
     * Método que se llama para crear una vista, aunqeu los elementos visuales no se hayan terminado de hacer visibles.
     * @param inflater Objeto para construir layouts
     * @param container Grupo de vistas
     * @param savedInstanceState Si no es la primera vez que se crea. Se le pasan los datos de la creación anterior.
     * @return Retorna una vista que debe ser visible posteriormente.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = null;
        oks ok = new oks();
        try {
            while (true) {
                super.onCreateView(inflater, container, savedInstanceState);
                view = inflater.inflate(R.layout.webview_simple_fragment, container, false);
//                if (savedInstanceState != null) {
//                    restaurar_datos_serializables(savedInstanceState, ok);
//                    if (ok.es == false) { break; }
//                }
                webview = view.findViewById(R.id.webview);
                WebSettings websetting = webview.getSettings();
                websetting.setSupportZoom(true);
                websetting.setDisplayZoomControls(false);
                // Enable Javascript
                websetting.setJavaScriptEnabled(true);
                // Enable Zoom
                websetting.setBuiltInZoomControls(true);
                // Cargar imágenes automáticamente
                websetting.setLoadsImagesAutomatically(true);
                // Permitir acceso a archivos locales.
                websetting.setAllowFileAccess(true);
                websetting.setAllowContentAccess(true);
                //            websetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    websetting.setAllowFileAccessFromFileURLs(true);
                    websetting.setAllowUniversalAccessFromFileURLs(true);
                }
                // Capturar las peticiones de páginas
                webview.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest webResourceRequest) {
                        boolean retorno = false;
                        oks ok = new oks();
                        try {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                Uri uri = webResourceRequest.getUrl();
                                URL url = new URL(uri.toString());
                                procesar_evento_llamada_a_url(url, ok);
                                if (ok.id.equals(k_no_procesar_event_llamada_a_url)) {
                                    ok.iniciar();
                                    retorno = false;
                                } else {
                                    retorno = true;
                                }
                            }
                        } catch (Exception e) {
                            ok.setTxt(e);
                        }
                        if (ok.es == false) {
                            try {
                                retorno = true;
                                escribir_texto(ok.getTxt(), ok.iniciar());
                                if (ok.es == false) {
                                    throw new RuntimeException(ok.getTxt());
                                }
                                presentar_contenido(ok);
                                if (ok.es == false) {
                                    throw new RuntimeException(ok.getTxt());
                                }
                            } catch (Exception e) {
                                String texto = "";
                                ok.setTxt(e);
                                try {
                                    texto = ok.getTxt();
                                } catch (Exception ex) {
                                }
                                throw new RuntimeException(texto);
                            }
                        }
                        return retorno;
                    }
        /*            @Override
                    public WebResourceResponse shouldInterceptRequest (final WebView view, WebResourceRequest webResourceRequest)
                    {
                    }*/

                });
                registrar_escuchador_file_chooser(ok);
                if (ok.es == false) { break; }
                Intent seleccion_archivo_intent = crear_seleccion_archivo(ok);
                if (ok.es == false) { break; }
                poner_webChromeClient(seleccion_archivo_intent, ok);
                if (ok.es == false) { break; }
                break;
            }
        } catch (Exception e) {
            ok.setTxt(e);
        }
        if (ok.es == false) {
            try {
                alertas.poner_alerta(this.getContext(), ok.getTxt(), ok.iniciar());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return view;
    }
    public boolean registrar_escuchador_file_chooser(oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) {
                return false;
            }
            escuchador_file_chooser_activityResultLauncher = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult()
                    , new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            oks ok = new oks();
                            try {
                                Webview_simpleFragment.this.atender_file_chooser(result, k_filechooser_resultcode, ok);
                            } catch (Exception e) {
                                ok.setTxt(e);
                            }
                            if (ok.es == false) {
                                try {
                                    alertas.poner_alerta(Webview_simpleFragment.this.getContext(), ok.getTxt(), ok.iniciar());
                                } catch (Exception e) {
                                }
                            }
                        }
                    }
            );
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return ok.es;
    }
    /**
     * Permite el uso de ChooseFile en la página web
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public boolean poner_webChromeClient(Intent seleccion_archivo_intent, oks ok, Object ... extras_array) throws Exception {
        try {
            webview.setWebChromeClient(new WebChromeClient() {
                // For Android 3.0+
                @Override
                public boolean onShowFileChooser(WebView webView
                        , ValueCallback<Uri[]> filePathCallback
                        , WebChromeClient.FileChooserParams fileChooserParams) {
                    ResourceBundle in;
                    try {
                        super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
                        in = ResourceBundles.getBundle(k_in_ruta);
                        file_chooser_ruta_callback = filePathCallback;
                        escuchador_file_chooser_activityResultLauncher.launch(seleccion_archivo_intent);
                    } catch (Exception e) {
                        try {
                            alertas.poner_alerta(Webview_simpleFragment.this.getContext(), ok.getTxt(), ok.iniciar());
                        } catch (Exception ex) {
                        }
                    }
                    return ok.es;
                }
            });
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return ok.es;
    }
    /**
     * Crea el intent para la seleccion de archivos
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public Intent crear_seleccion_archivo(oks ok, Object ... extras_array) throws Exception {
        Intent seleccion_archivo_intent = null;
        try {
            if (ok.es == false) { return null; }
            ResourceBundle in = null;
            in = ResourceBundles.getBundle(k_in_ruta);
            seleccion_archivo_intent = new Intent(Intent.ACTION_OPEN_DOCUMENT); // ACTION_OPEN_DOCUMENT ACTION_GET_CONTENT);
            seleccion_archivo_intent.addCategory(Intent.CATEGORY_OPENABLE);
            File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            Uri uri = Uri.fromFile(file);
            seleccion_archivo_intent.setData(uri);
            seleccion_archivo_intent.setType("*/*");
            seleccion_archivo_intent.putExtra(EXTRA_INITIAL_URI, uri);
            seleccion_archivo_intent.putExtra("android.provider.extra.SHOW_ADVANCED", true);
            seleccion_archivo_intent = Intent.createChooser(seleccion_archivo_intent, tr.in(in, "Seleccionar archivo "));
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return seleccion_archivo_intent;
    }
    /**
     * Escuchador de seleccion_archivo_intent
     * @param activityResult
     * @param id_codigo
     * @param ok
     * @return
     */
    public boolean atender_file_chooser(ActivityResult activityResult, int id_codigo, oks ok) {
        ResourceBundle in;
        try {
            if (id_codigo == k_filechooser_resultcode) {
                in = ResourceBundles.getBundle(k_in_ruta);
                boolean es_crear_seleccion_archivo;
                while (true) {
                    es_crear_seleccion_archivo = false;
                    if (file_chooser_ruta_callback == null) {
                        ok.setTxt(tr.in(in, "Error en atender_file_chooser, no se puede retornar la URI. "));
                        if (ok.es == false) {
                            break;
                        }
                    }
                    if (activityResult == null) {
                        file_chooser_ruta_callback = null;
                        ok.setTxt(tr.in(in, "Error en atender_file_chooser. "));
                        if (ok.es == false) {
                            break;
                        }
                    } else if (activityResult.getResultCode() == RESULT_OK) {
                        Uri result = activityResult.getData().getData();
                        Uri[] result_array = {result};
                        file_chooser_ruta_callback.onReceiveValue(result_array);
                    } else if (activityResult.getResultCode() != RESULT_CANCELED) {
                        file_chooser_ruta_callback = null;
                        ok.setTxt(tr.in(in, "Error en atender_file_chooser. "));
                        if (ok.es == false) {
                            break;
                        }
                    } else {
                        file_chooser_ruta_callback = null;
                        es_crear_seleccion_archivo = true;
                    }
                    break;
                }
//                if (ok.es == false || es_crear_seleccion_archivo) {
//                    oks ok_1 = new oks();
//                    crear_seleccion_archivo(ok_1);
//                    if (ok_1.es == false) {
//                        ok.setTxt(ok_1.getTxt(), ok.getTxt());
//                        return false;
//                    }
//                }
            }
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return ok.es;
    }
    /**
     * Procesa una URL (reemplazable en Webview_simpleController_hijo)
     * @param url URL que procesar
     * @param ok
     * @param extras_array
     * @return true si todo es correcto, false si hay error.
     * @throws Exception
     */
    public boolean procesar_evento_llamada_a_url(URL url, oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return ok.es; }
        Boolean ret_Boolean = true;
        if (i_Webview_simpleController_extension != null) {
            ok.iniciar();
            ret_Boolean = i_Webview_simpleController_extension.procesar_evento_llamada_a_url(url, ok);
            if (ret_Boolean == null) {
                ok.es = true;
            } else {
                ok.es = ret_Boolean;
            }
        } else {
            ok.id = k_no_procesar_event_llamada_a_url;
            ok.setTxt(k_no_procesar_event_llamada_a_url);
        }
        return ok.es;
    }
    /**
     * Escribe una copia del texto del Webview
     * @param texto
     * @param ok
     * @param extras_array
     * @return true si tiene éxito, o false si hay error
     * @throws Exception
     */
    public boolean escribir_texto(String texto, oks ok, Object ... extras_array) throws Exception {
        if (i_Webview_simpleController_extension != null) {
            i_Webview_simpleController_extension.escribir_texto(texto, ok);
        } else {
            webview_texto = texto;
        }
        return ok.es;
    }
    /**
     * Lee una copia del texto del Webview
     * @param ok
     * @param extras_array
     * @return true si tiene éxito, o false si hay error
     * @throws Exception
     */
    public String leer_texto(oks ok, Object ... extras_array) throws Exception {
        String retorno = null;
        if (i_Webview_simpleController_extension != null) {
            retorno = i_Webview_simpleController_extension.leer_texto(ok);
        } else {
            retorno = webview_texto;
        }
        return retorno;
    }

    public boolean agregar_objeto_de_extension(I_Webview_simpleController_extensiones i_webview_simpleController_extension
            , oks ok, Object ... extras_array) {
        if (ok.es == false) { return ok.es; }
        this.i_Webview_simpleController_extension = i_webview_simpleController_extension;
        return ok.es;
    }

    public boolean agregar_objeto_de_captura(I_Webview_simpleController_capturas i_webview_simpleController_captura
            , oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return ok.es; }
        this.i_Webview_simpleController_captura = i_webview_simpleController_captura;
        return ok.es;
    }
    /**
     * Carga el contenido en un hilo
     * @param ok
     * @param extras_array
     * @return true si tiene éxito, o false si hay error
     * @throws Exception
     */
    public boolean presentar_contenido(oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return ok.es; }
        String contenido;
        final String contenido_final;
        uri = null;
        contenido = leer_texto(ok);
        if (contenido == null || ok.es == false) {
            contenido = ok.txt;
        }
        contenido_final = contenido;
        if (webview != null) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        webview.loadDataWithBaseURL("", contenido_final, "text/html", "UTF-8", "");
                    } catch (Exception e) {
                        Log.d(this.getClass().getName(), e.getLocalizedMessage());
                    }
                }
            });
        }
        return ok.es;
    }
    /**
     * Carga el contenido en un hilo
     * @param uri URI de donde obtener el contenido
     * @param ok
     * @param extras_array
     * @return true si todo es correcto, false si hay error.
     * @throws Exception
     */
    public boolean presentar_contenido(URI uri, oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return ok.es; }
        String texto = uri.toString();
        if (webview != null) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        webview.loadUrl(texto);
                    } catch (Exception e) {
                        Log.d(this.getClass().getName(), e.getLocalizedMessage());
                    }
                }
            });
        }
        return ok.es;
    }
    /**
     * Presenta el contenido empleando sockets seguros
     * @param uri URI de donde obtener el contenido
     * @param sSLSocketFactory Factoría de sockets seguros; null si no se debe utilizar ninguna.
     * @param es_intentar_sin_certificado_valido Si es verdad, y la primera llamada falla, se hace un segundo intento; ignorando la validación de certificado.
     * @param ok
     * @param extras_array
     * @return true si todo es correcto, false si hay error.
     * @throws Exception
     */
    public boolean presentar_contenido(URI uri, SSLSocketFactory sSLSocketFactory, boolean es_intentar_sin_certificado_valido
            , oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return ok.es; }
//        this.es_intentar_sin_certificado_valido = es_intentar_sin_certificado_valido;
//        sslcontext_sin_verificar_hostname.actual_SSLSocketFactory = sSLSocketFactory;
        presentar_contenido(uri, ok);
        return ok.es;
    }

    public boolean presentar_contenido_con_factoria_de_socket(URI uri, SSLSocketFactory sSLSocketFactory
            , oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return ok.es; }
        String texto = uri.toString();
        if (sSLSocketFactory != null) {
            HttpsURLConnection.setDefaultSSLSocketFactory(sSLSocketFactory);
        }
        if (webview != null) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        webview.loadUrl(texto);
                    } catch (Exception e) {
                        Log.d(this.getClass().getName(), e.getLocalizedMessage());
                    }
                }
            });
        }
        return ok.es;
    }

    public boolean presentar_contenido_tls_sin_validar_certificado(URI uri, oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return ok.es; }
        String texto = uri.toString();
        // Crear un trust manager que lo valida todo
        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    @Override
                    public void checkClientTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                    @Override
                    public void checkServerTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sSLContext.getSocketFactory());
//            sslcontext_sin_verificar_hostname.inicial_hostnameVerifier
//                    = HttpsURLConnection.getDefaultHostnameVerifier();
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
            if (webview != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            webview.loadUrl(texto);
                        } catch (Exception e) {
                            Log.d(this.getClass().getName(), e.getLocalizedMessage());
                        }
                    }
                });
            }
        } catch (Exception e) {
            ok.setTxt(e);
        }
        // webEngine.load(texto);
        return ok.es;
    }

}