package android.ingui.navegador_web_android;

import static android.ingui.navegador_web_android.Webview_simpleFragment.k_no_procesar_event_llamada_a_url;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.ingui.utiles.alertas;
import android.innui.modelos_android.configuraciones.iniciales;
import android.innui.modelos_android.modelos_android;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.net.URI;
import java.net.URL;

import innui.modelos.errores.oks;

public class Navegador_web_android_activity extends AppCompatActivity {
    public static String k_in_ruta;
    static {
        String paquete_tex = Navegador_web_android_activity.class.getPackage().getName();
        paquete_tex = paquete_tex.replace(".", File.separator);
        k_in_ruta = "assets/in/" + paquete_tex + "/in";
    }
    public String url_tex;
    public boolean es_inicio = true;
    public Button retroceso_boton;
    public Button avance_boton;
    public Button inicio_boton;
    public Webview_simpleFragment_implementaciones webview_simpleFragment_implementacion;
    public Webview_simpleFragment.I_Webview_simpleController_capturas i_webview_simpleController_captura;
    public Webview_simpleFragment webview_simpleFragment;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        oks ok = new oks();
        try {
            setContentView(R.layout.activity_navegador_web_android);
            url_tex = getString(R.string.ulr_inicial);
            crear_componentes_visuales(ok);
        } catch (Exception e) {
            ok.setTxt(e);
        }
        if (ok.es == false) {
            try {
                alertas.poner_alerta(this, ok.getTxt(), ok.iniciar());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    /**
     * Método que se llama cuando los elementos visuales ya están listos.
     */
    @Override
    public void onResume() {
        super.onResume();
        oks ok = new oks();
        if (es_inicio) {
            es_inicio = false;
            try {
                inicial.run(ok);
            } catch (Exception e) {
                ok.setTxt(e);
            }
        }
        if (ok.es == false) {
            if (ok.es == false) {
                try {
                    alertas.poner_alerta(this, ok.getTxt(), ok.iniciar());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

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
    public boolean crear_componentes_visuales(oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            if (retroceso_boton == null) { // Componentes visuales anulados
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
                                alertas.poner_alerta(Navegador_web_android_activity.this, ok.getTxt(), ok.iniciar());
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });
            }
            if (avance_boton == null) { // Componentes visuales anulados
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
                                alertas.poner_alerta(Navegador_web_android_activity.this, ok.getTxt(), ok.iniciar());
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });
            }
            if (inicio_boton == null) { // Componentes visuales anulados
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
                                alertas.poner_alerta(Navegador_web_android_activity.this, ok.getTxt(), ok.iniciar());
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });
            }
            if (webview_simpleFragment == null) { // Componentes visuales anulados
                poner_webview_simpleFragment(R.id.fragmento_1, ok);
            }
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
            if (webview_simpleFragment.webview.canGoBack()) {
                webview_simpleFragment.webview.goBack();
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
            if (webview_simpleFragment.webview.canGoForward()) {
                webview_simpleFragment.webview.goForward();
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
     * Pone en fragmento_1 un objeto Leer_procesosFragment
     * @param r_id_fragmento Identificador del fragmento.
     * @param ok
     * @param extras_array
     * @return true si va bien, false si hay error.
     */
    public boolean poner_webview_simpleFragment(int r_id_fragmento, oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            FragmentManager fragmentManager;
            fragmentManager = getSupportFragmentManager();
            Fragment fragment = fragmentManager.findFragmentById(r_id_fragmento);
            if (fragment != null
                    && fragment instanceof Webview_simpleFragment) {
                webview_simpleFragment = (Webview_simpleFragment) fragment;
            } else {
                webview_simpleFragment = new Webview_simpleFragment();
            }
            crear_atributos_webview_simple_fragment(ok);
            if (ok.es == false) { return false; }
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(r_id_fragmento, webview_simpleFragment);
            // Commit no implica una realización inmediata. No causa la excepción que causa: commitNow()
            fragmentTransaction.commit();
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return ok.es;
    }
    /**
     *
     * @param ok
     * @param extras_array
     * @return true si tiene éxito, false si hay error.
     */
    public boolean crear_atributos_webview_simple_fragment(oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            if (i_webview_simpleController_captura == null) {
                i_webview_simpleController_captura = new Webview_simpleFragment.I_Webview_simpleController_capturas() {
                    @Override
                    public boolean poner_error(String mensaje, oks ok, Object... extras_array) {
                        return Navegador_web_android_activity.this.poner_error(mensaje, ok);
                    }
                };
            }
            if (webview_simpleFragment_implementacion == null) {
                webview_simpleFragment_implementacion = new Webview_simpleFragment_implementaciones() {
                    @Override
                    public boolean presentar_contenido(oks ok, Object... extras_array) throws Exception {
                        try {
                            if (ok.es == false) {
                                return ok.es;
                            }
                            if (o != null) {
                                if (o instanceof Webview_simpleFragment_implementaciones) {
                                    return ((Webview_simpleFragment_implementaciones) o).presentar_contenido(ok, extras_array);
                                }
                            }
                            return webview_simpleFragment.presentar_contenido(ok);
                        } catch (Exception e) {
                            throw e;
                        }
                    }

                    @Override
                    public boolean presentar_contenido(URI uri, oks ok, Object... extras_array) throws Exception {
                        try {
                            if (ok.es == false) {
                                return ok.es;
                            }
                            if (o != null) {
                                if (o instanceof Webview_simpleFragment_implementaciones) {
                                    return ((Webview_simpleFragment_implementaciones) o).presentar_contenido(uri, ok, extras_array);
                                }
                            }
                            return webview_simpleFragment.presentar_contenido(uri, ok);
                        } catch (Exception e) {
                            throw e;
                        }
                    }

                    @Override
                    public Boolean procesar_evento_llamada_a_url(URL url, oks ok, Object... extras_array) throws Exception {
                        try {
                            if (ok.es == false) {
                                return ok.es;
                            }
                            Navegador_web_android_activity.this.procesar_evento_llamada_a_url(url, ok, extras_array);
                            return ok.es;
                        } catch (Exception e) {
                            throw e;
                        }
                    }
                };
            }
            if (ok.es) {
                webview_simpleFragment.agregar_objeto_de_captura(i_webview_simpleController_captura, ok);
            }
            if (ok.es) {
                webview_simpleFragment.agregar_objeto_de_extension(webview_simpleFragment_implementacion, ok);
            }
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return ok.es;
    }

    /**
     * Poner un mensaje de error
     * @param mensaje
     * @param ok
     * @param extras_array
     * @return
     */
    public boolean poner_error(String mensaje, oks ok, Object ... extras_array)  {
        boolean ret = true;
        try {
            alertas.poner_alerta(this, mensaje, ok);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
            ok.id = k_no_procesar_event_llamada_a_url;
            ok.setTxt(k_no_procesar_event_llamada_a_url);
            return ok.es;
        } catch (Exception e) {
            throw e;
        }
    }

}