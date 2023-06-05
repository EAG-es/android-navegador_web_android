package android.ingui.navegador_web_android;

import static android.ingui.navegador_web_android.Webview_simpleFragment.k_no_procesar_event_llamada_a_url;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.innui.modelos_android.cambios.modelos_permanentes;
import android.innui.modelos_android.modelos_android;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;

import android.innui.modelos_android.configuraciones.iniciales;
import innui.modelos.errores.oks;

public class Navegador_web_android_activity extends AppCompatActivity {
    public static String k_webview_simpleFragment_implementacion = "webview_simpleFragment_implementacion";
    public static String k_es_inicio = "es_inicio";
    public static String k_i_webview_simpleController_captura = "i_webview_simpleController_captura";
    public static String k_i_modelo_permanente = "i_modelo_permanente";

    public static String k_url_tex = "url_tex";
    public static String k_inicial = "inicial";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.navegador_web_activity);
//    }
    public Webview_simpleFragment_implementaciones webview_simpleFragment_implementacion;
    public Webview_simpleFragment.I_Webview_simpleController_capturas _i_webview_simpleController_captura;
    public Webview_simpleFragment _webview_simpleFragment;
    public TextView textView;
    public Button retroceso_boton;
    public Button avance_boton;
    public Button inicio_boton;
    public modelos_permanentes _modelo_permanente;
    public modelos_permanentes.i_modelos_permanentes i_modelo_permanente;
    public String url_tex;
    public boolean es_inicio = true;
    public Navegador_web_android_activity() throws Exception {
        i_modelo_permanente = new modelos_permanentes.i_modelos_permanentes() {
            @Override
            public void onCleared() {
                if (webview_simpleFragment_implementacion != null) {
                    System.exit(0);
                }
            }
        };
    }
    /**
     *
     * @param ok
     * @param extras_array
     * @return true si tiene éxito, false si hay error.
     */
    public boolean iniciar_atributos(oks ok, Object ... extras_array) throws Exception {
        _i_webview_simpleController_captura = new Webview_simpleFragment.I_Webview_simpleController_capturas() {
            @Override
            public boolean poner_error(String mensaje, oks ok, Object ... extras_array) {
                return Navegador_web_android_activity.this.poner_error(mensaje, ok);
            }
        };
        webview_simpleFragment_implementacion = new Webview_simpleFragment_implementaciones() {
            @Override
            public boolean presentar_contenido(oks ok, Object ... extras_array) throws Exception {
                try {
                    if (ok.es == false) { return ok.es; }
                    if (o != null) {
                        if (o instanceof Webview_simpleFragment_implementaciones) {
                            return ((Webview_simpleFragment_implementaciones) o).presentar_contenido(ok, extras_array);
                        }
                    }
                    return _webview_simpleFragment.presentar_contenido(ok);
                } catch (Exception e) {
                    throw e;
                }
            }
            @Override
            public boolean presentar_contenido(URI uri, oks ok, Object ... extras_array) throws Exception {
                try {
                    if (ok.es == false) { return ok.es; }
                    if (o != null) {
                        if (o instanceof Webview_simpleFragment_implementaciones) {
                            return ((Webview_simpleFragment_implementaciones) o).presentar_contenido(uri, ok, extras_array);
                        }
                    }
                    return _webview_simpleFragment.presentar_contenido(uri, ok);
                } catch (Exception e) {
                    throw e;
                }
            }
            @Override
            public Boolean procesar_evento_llamada_a_url(URL url, oks ok, Object ... extras_array) throws Exception {
                try {
                    if (ok.es == false) { return ok.es; }
                    Navegador_web_android_activity.this.procesar_evento_llamada_a_url(url, ok, extras_array);
                    return ok.es;
                } catch (Exception e) {
                    throw e;
                }
            }
        };
        return ok.es;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        oks ok = new oks();
        try {
            ViewModelProvider.AndroidViewModelFactory androidViewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication());
            ViewModelProvider viewModelProvider = new ViewModelProvider(this
              , (ViewModelProvider.Factory) androidViewModelFactory);
            _modelo_permanente = viewModelProvider.get(modelos_permanentes.class);
            _modelo_permanente.i_modelo_permanente = i_modelo_permanente;
            setContentView(R.layout.navegador_web_activity);
            textView = (TextView) findViewById(R.id.mensaje_textView);
            url_tex = getString(R.string.ulr_inicial);
            retroceso_boton = (Button) findViewById(R.id.retroceso_button);
            retroceso_boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        procesar_boton_retroceso(ok);
                    } catch (Exception e) {
                        ok.setTxt(e);
                    }
                    if (ok.es == false) {
                        try {
                            poner_alerta(ok.getTxt(), ok.iniciar());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });
            avance_boton = (Button) findViewById(R.id.avance_button);
            avance_boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        procesar_boton_avance(ok);
                    } catch (Exception e) {
                        ok.setTxt(e);
                    }
                    if (ok.es == false) {
                        try {
                            poner_alerta(ok.getTxt(), ok.iniciar());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });
            inicio_boton = (Button) findViewById(R.id.inicio_button);
            inicio_boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        procesar_boton_inicio(ok);
                    } catch (Exception e) {
                        ok.setTxt(e);
                    }
                    if (ok.es == false) {
                        try {
                            poner_alerta(ok.getTxt(), ok.iniciar());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });
            poner_webview_simpleFragment(R.id.fragmento_1, ok);
            if (ok.es) {
                if (savedInstanceState != null) {
                    restaurar_atributos(savedInstanceState, ok);

                }
            }
            if (ok.es) {
                _webview_simpleFragment.agregar_objeto_de_captura(_i_webview_simpleController_captura, ok);
            }
            if (ok.es) {
                _webview_simpleFragment.agregar_objeto_de_extension(webview_simpleFragment_implementacion, ok);
            }
            // Prueba de onSaveInstanceState
//            Bundle bundle = new Bundle();
//            salvar_atributos(bundle, ok);
//            if (ok.es) {
//                restaurar_atributos(bundle, ok);
//            }
        } catch (Exception e) {
            ok.setTxt(e);
        }
        if (ok.es == false) {
            try {
                poner_alerta(ok.getTxt(), ok.iniciar());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Retroceso de página web
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception
     */
    public boolean procesar_boton_retroceso(oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            if (_webview_simpleFragment.webview.canGoBack()) {
                _webview_simpleFragment.webview.goBack();
            }
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return ok.es;
    }

    /**
     * Avance de página web
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception
     */
    public boolean procesar_boton_avance(oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            if (_webview_simpleFragment.webview.canGoForward()) {
                _webview_simpleFragment.webview.goForward();
            }
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return ok.es;
    }
    /**
     * Avance de página web
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception
     */
    public boolean procesar_boton_inicio(oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            navegar(ok);
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return ok.es;
    }
    /**
     * Crea una ventana de diálogo con un mensaje y lanza una excepción RuntimeException
     * @param mensaje
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception
     */
    public boolean poner_alerta(String mensaje, oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            final String error_final = mensaje;
            AlertDialog.Builder alertDialog_Builder = new AlertDialog.Builder(this);
            alertDialog_Builder.setMessage(mensaje);
            alertDialog_Builder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    throw new RuntimeException(error_final);
                }
            });
            alertDialog_Builder.create();
            alertDialog_Builder.show();
        } catch (Exception e) {
            String texto = "";
            ok.setTxt(e);
            try {
                texto = ok.getTxt();
            } catch (Exception ex) {}
            throw new RuntimeException(texto);
        }
        return ok.es;
    }
    /**
     * Pone en fragmento_1 un objeto Leer_procesosFragment
     * @param r_id_fragmento Identificador del fragmento.
     * @param ok
     * @param extras_array
     * @return true si va bien, false si hay error.
     */
    public boolean poner_webview_simpleFragment(int r_id_fragmento, oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return false; }
        FragmentManager fragmentManager;
        fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(r_id_fragmento);
        if (fragment != null
                && fragment instanceof Webview_simpleFragment) {
            _webview_simpleFragment = (Webview_simpleFragment) fragment;
            iniciar_atributos(ok);
            if (ok.es == false) { return false; }
        } else {
            _webview_simpleFragment = new Webview_simpleFragment();
            iniciar_atributos(ok);
            if (ok.es == false) { return false; }
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(r_id_fragmento, _webview_simpleFragment);
        // Commit no implica una realización inmediata. No causa la excepción que causa: commitNow()
        fragmentTransaction.commit();
        return ok.es;
    }
    /**
     * Método que se llama cuando los elementos visuales ya están listos.
     */
    @Override
    public void onResume() {
        super.onResume();
        if (es_inicio) {
            es_inicio = false;
            oks ok = new oks();
            try {
                inicial.run(ok);
            } catch (Exception e) {
                ok.setTxt(e);
            }
            if (ok.es == false) {
                if (ok.es == false) {
                    try {
                        poner_alerta(ok.getTxt(), ok.iniciar());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
    /**
     * Método para restaurar los atributos del objeto, con gestión de errores.
     * @param bundle Objeto donde guardar los objetos de la clase de ese fragmento. Para reponrelos cuando se cree de nuevo.
     * @return true si va bien, false si hay error.
     */
    public boolean restaurar_atributos(Bundle bundle, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            es_inicio = bundle.getBoolean(k_es_inicio);
            inicial = (iniciales) bundle.getSerializable(k_inicial);
            _i_webview_simpleController_captura = (Webview_simpleFragment.I_Webview_simpleController_capturas) bundle.getSerializable(k_i_webview_simpleController_captura);
            i_modelo_permanente = (modelos_permanentes.i_modelos_permanentes) bundle.getSerializable(k_i_modelo_permanente);
            _webview_simpleFragment.restaurar_atributos(bundle, ok, extras_array);
            if (ok.es == false) { return false; }
            HashMap<String, Object> datos_mapa;
            datos_mapa = (HashMap<String, Object>) bundle.getSerializable(k_webview_simpleFragment_implementacion);
            webview_simpleFragment_implementacion.restaurar_datos_serializables(datos_mapa, ok);
            _webview_simpleFragment.restaurar_atributos(bundle, ok, extras_array);
            url_tex = (String) datos_mapa.get(k_url_tex);
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return ok.es;
    }
    /**
     * Método que se llama cuando una actividad cambia su modo de visualización
     * @param bundle Objeto donde guardar los objetos de la clase de esa actividad. Para reponrelos cuando se cree de nuevo.
     */
    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        oks ok = new oks();
        // Las vistas se restauran solas. Lo que no se restaura es el valor de los campos de la clase
        // Nota: el contenido de un TextView no es conservado automáticamente. Para que sea conservado, debemos asignarle la propiedad android:freezesText="true"
        try {
            super.onSaveInstanceState(bundle);
            salvar_atributos(bundle, ok);
        } catch (Exception e) {
            ok.setTxt(e);
        }
        if (ok.es == false) {
            try {
                final String error_final = ok.getTxt();
                AlertDialog.Builder alertDialog_Builder = new AlertDialog.Builder(this);
                alertDialog_Builder.setMessage(ok.getTxt());
                alertDialog_Builder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        throw new RuntimeException(error_final);
                    }
                });
                alertDialog_Builder.create();
                alertDialog_Builder.show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    /**
     * Método para guardar los atributos del objeto, con gestión de errores.
     * @param bundle Objeto donde guardar los objetos de la clase de ese fragmento. Para reponrelos cuando se cree de nuevo.
     * @param ok
     * @param extras_array
     * @return true si va bien, false si hay error.
     */
    public boolean salvar_atributos(Bundle bundle, oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return false; }
        try {
            bundle.putSerializable(k_inicial, inicial);
            bundle.putBoolean(k_es_inicio, es_inicio);
            bundle.putSerializable(k_i_webview_simpleController_captura, _i_webview_simpleController_captura);
            bundle.putSerializable(k_i_modelo_permanente, i_modelo_permanente);
            _webview_simpleFragment.salvar_atributos(bundle, ok, extras_array);
            if (ok.es == false) { return false; }
            HashMap<String, Object> datos_mapa = new HashMap();
            datos_mapa.put(k_url_tex, url_tex);
            webview_simpleFragment_implementacion.salvar_datos_serializables(datos_mapa, ok);
            if (ok.es == false) { return false; }
            bundle.putSerializable(k_webview_simpleFragment_implementacion, datos_mapa);
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return ok.es;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    /**
     * Junto con el atributo de: activity (no de application):
     * android:configChanges="orientation|screenSize"
     * El cual evita que se destruya la actividad.
     * De manera que el cambio de orientación mantiene el aspecto anterior, si no se hace nada.
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
    public boolean poner_error(String mensaje, oks ok, Object ... extras_array)  {
        boolean ret = true;
        if (textView != null) {
            textView.setText(mensaje);
        }
        Log.d(this.getClass().getName(), ok.txt);
        return ret;
    }
    /**
     * Captura las URLs que son llamadas (clic) en el navegador
     * @param url
     * @param ok
     * @param extras_array
     * @return true si todo es correcto, false/null si hay error.
     * @throws Exception
     */
    public Boolean procesar_evento_llamada_a_url(URL url, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return ok.es; }
            textView.setText("");
            ok.id = k_no_procesar_event_llamada_a_url;
            ok.setTxt(k_no_procesar_event_llamada_a_url);
            return ok.es;
        } catch (Exception e) {
            throw e;
        }
    }
    public iniciales inicial = new iniciales () {
        @Override
        public boolean run(oks ok, Object... extra_array) throws Exception {
            try {
                if (ok.es == false) { return ok.es; }
                if (o != null) {
                    if (o instanceof iniciales) {
                        return ((iniciales) o).run(ok, extra_array);
                    }
                }
                iniciar(ok);
                if (ok.es) {
                    while (true) {
                        navegar(ok);
                        if (ok.es == false) { break; }
                        break;
                    }
                    oks ok_fin = new oks();
                    terminar(ok_fin);
                    if (ok_fin.es == false) {
                        ok.setTxt(ok.getTxt(), ok_fin.getTxt());
                    }
                }
                return ok.es;
            } catch (Exception e) {
                throw e;
            }
        }

        @Override
        public boolean iniciar(oks ok, Object... extra_array) throws Exception {
            if (o != null) {
                if (o instanceof iniciales) {
                    return ((iniciales) o).iniciar(ok, extra_array);
                }
            }
            if (ok.es == false) { return ok.es; }
            _poner_aplicacion(this.getClass(), ok);
            if (ok.es == false) { return ok.es; }
            _iniciar_desde_clase(Navegador_web_android_activity.this, modelos_android.class, ok);
            if (ok.es == false) { return ok.es; }
            _iniciar_desde_clase(Navegador_web_android_activity.this, Navegador_web_android_activity.class, ok);
            if (ok.es == false) { return ok.es; }
            return ok.es;
        }

        @Override
        public boolean terminar(oks ok, Object... extra_array) throws Exception {
            if (o != null) {
                if (o instanceof iniciales) {
                    return ((iniciales) o).terminar(ok, extra_array);
                }
            }
            if (ok.es == false) { return ok.es; }
            _terminar_desde_clase(modelos_android.class, ok);
            if (ok.es == false) { return ok.es; }
            _terminar_desde_clase(this.getClass(), ok);
            if (ok.es == false) { return ok.es; }
            return ok.es;
        }
    };
    /**
     * Realiza la operativa de navegar para presentar una página web
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception
     */
    public boolean navegar(oks ok, Object... extra_array) throws Exception {
        int index;
        URI uri = new URI(url_tex);
        webview_simpleFragment_implementacion.presentar_contenido(uri, ok);
        return ok.es;
    }

}