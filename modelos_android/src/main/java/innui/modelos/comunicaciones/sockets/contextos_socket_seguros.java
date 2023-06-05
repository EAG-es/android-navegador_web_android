package innui.modelos.comunicaciones.sockets;

import android.innui.modelos_android.configuraciones.ResourceBundles;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;
import innui.bases;
import android.innui.modelos_android.configuraciones.rutas;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/**
 *
 * @author profesor
 */
public class contextos_socket_seguros extends bases {
    public static String k_in_ruta = "assets/in/innui/modelos/comunicaciones/sockets/in";
    public static String k_almacen_de_claves = "almacen_de_claves";
    public static String k_almacen_de_claves_clave_de_acceso = "almacen_de_claves_clave_de_acceso";
    public static String k_almacen_de_confianzas = "almacen_de_confianzas";
    public static String k_almacen_de_confianzas_clave_de_acceso = "almacen_de_confianzas_clave_de_acceso";
    public static int k_modo_cargando_almacenes = 0;
    public static int k_modo_propiedades_sistema = 1;
    public static int k_modo_linea_de_comando = 2;    
    public static int k_protocolo_con_autenticacion_en_servidor = 0;
    public static int k_protocolo_con_autenticacion_en_servidor_y_cliente = 1;
    public ResourceBundle in = null;
    public SSLContext sslContext;
    public int modo = 0;
    public int protocolo_de_autenticacion = 0;
    public SecureRandom numero_seguro_aleatorio = null;
    
    public contextos_socket_seguros() throws Exception {
        in = ResourceBundles.getBundle(k_in_ruta);
    }
    /**
     * Preconfigura la manera de preparar el contexto de los sockets seguros
     * @param modo null, no se cambia. Las opciones de modo, comienzan por: k_modo
     * @param protocolo_de_autenticacion null, no se cambia. Las opciones de protocolo de autenticacion, comienzan por: k_protocolo_con_autenticacion
     * @param numero_seguro_aleatorio null, no se cambia. Número seguro aleatorio.
     * @param ok
     * @param extra_array
     * @return true si todo es correcto, false si hay error.
     * @throws Exception
     */
    public boolean preconfigurar(Integer modo, Integer protocolo_de_autenticacion, SecureRandom numero_seguro_aleatorio
            , oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return ok.es; }
        if (modo != null) {
            this.modo = modo;
        }
        if (protocolo_de_autenticacion != null) {
            this.protocolo_de_autenticacion = protocolo_de_autenticacion;
        }
        if (numero_seguro_aleatorio != null) {
            this.numero_seguro_aleatorio = numero_seguro_aleatorio;
        }
        return ok.es;
    }
    /**
     * 
     * @param ruta_relativa_properties Ruta desde la raiz de las clases, o del archivo jar
     * @param ok
     * @param extra_array
     * @return true si todo es correcto, false si hay error.
     * @throws Exception
     */
    public boolean configurar_servidor(String ruta_relativa_properties, oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return ok.es; }
        String ruta;
        InputStream inputstream;
        Properties propertie;
        String almacen_de_claves = null;
        String almacen_de_claves_clave_de_acceso = null;
        String almacen_de_confianzas = null;
        String almacen_de_confianzas_clave_de_acceso = null;
        KeyStore keystore = null;
        KeyManagerFactory keyManagerFactory = null;
        char[] truststorepass_array = null;
        KeyStore truststore = null;
        char[] keystorepass_array = null;
        TrustManagerFactory trustManagerFactory = null;
        try {
            ruta = rutas.crear_ruta_desde_clase(getClass(), ruta_relativa_properties, ok);
            inputstream = new FileInputStream(ruta);
            propertie = new Properties();
            propertie.load(inputstream);
            almacen_de_claves = (String) propertie.get(k_almacen_de_claves);
            ok.es = (almacen_de_claves != null);
            if (ok.es) {
                almacen_de_claves = rutas.crear_ruta_desde_clase(getClass(), almacen_de_claves, ok);
                if (ok.es) {
                    ok.es = (almacen_de_claves != null);
                }
            }
            if (ok.es) {
                almacen_de_claves_clave_de_acceso = (String) propertie.get(k_almacen_de_claves_clave_de_acceso);
                if (ok.es) {
                    ok.es = (almacen_de_claves_clave_de_acceso != null);
                }
            }
            if (protocolo_de_autenticacion == k_protocolo_con_autenticacion_en_servidor_y_cliente) {
                if (ok.es) {
                    almacen_de_confianzas = (String) propertie.get(k_almacen_de_confianzas);
                    ok.es = (almacen_de_confianzas != null);
                }
                if (ok.es) {
                    almacen_de_confianzas = rutas.crear_ruta_desde_clase(getClass(), almacen_de_confianzas, ok);
                    ok.es = (almacen_de_confianzas != null);
                }
                if (ok.es) {
                    almacen_de_confianzas_clave_de_acceso = (String) propertie.get(k_almacen_de_confianzas_clave_de_acceso);
                    ok.es = (almacen_de_confianzas_clave_de_acceso != null);
                }
            }
            if (ok.es) {
                if (modo == k_modo_linea_de_comando) {
                    sslContext = SSLContext.getDefault();
                } else if (modo == k_modo_propiedades_sistema) {
                    // Metodo 1                
                    System.setProperty("javax.net.ssl.keyStore", almacen_de_claves);
                    System.setProperty("javax.net.ssl.keyStorePassword", almacen_de_claves_clave_de_acceso);
                    if (protocolo_de_autenticacion == k_protocolo_con_autenticacion_en_servidor_y_cliente) {
                        System.setProperty("javax.net.ssl.trustStore", almacen_de_confianzas);
                        System.setProperty("javax.net.ssl.trustStorePassword", almacen_de_confianzas_clave_de_acceso);   
                    }
                    sslContext = SSLContext.getDefault();
                } else if (modo == k_modo_cargando_almacenes) {
                    // Metodo 2
                    keystorepass_array = almacen_de_claves_clave_de_acceso.toCharArray();
                    keystore = obtener_almacen(almacen_de_claves, keystorepass_array, ok);
                    if (ok.es) {
                        ok.es = (keystore != null);
                    }
                    if (ok.es) {
                        keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm()); // "SunX509");
                        keyManagerFactory.init(keystore, keystorepass_array);
                        if (protocolo_de_autenticacion == k_protocolo_con_autenticacion_en_servidor_y_cliente) {
                            truststorepass_array = almacen_de_confianzas_clave_de_acceso.toCharArray();
                            truststore = obtener_almacen(almacen_de_confianzas, truststorepass_array, ok);
                            if (ok.es) {
                                ok.es = (truststore != null);
                            }
                            if (ok.es) {
                                trustManagerFactory = TrustManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm()); //"SunX509");
                                trustManagerFactory.init(truststore);
                            }
                        }
                    }
                    if (ok.es) {
                        sslContext = SSLContext.getInstance("TLS");
                        if (protocolo_de_autenticacion == k_protocolo_con_autenticacion_en_servidor_y_cliente) {
                            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), numero_seguro_aleatorio);
                        } else {
                            sslContext.init(keyManagerFactory.getKeyManagers(), null, numero_seguro_aleatorio);
                        }
                    }
                }
            }
        } catch (Exception e) {
            ok.setTxt(tr.in(in, "ERROR DE EXCEPCIÓN AL CONFIGURAR EL CONTEXTO SERVIDOR")
                , e);
        }
        return ok.es;
    }
    /**
     * 
     * @param ruta_relativa_properties Ruta desde la raiz de las clases, o del archivo jar
     * @param ok
     * @param extra_array
     * @return true si todo es correcto, false si hay error.
     * @throws Exception
     */
    public boolean configurar_cliente(String ruta_relativa_properties, oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return ok.es; }
        String ruta;
        InputStream inputstream;
        Properties propertie;
        String almacen_de_claves = null;
        String almacen_de_claves_clave_de_acceso = null;
        String almacen_de_confianzas = null;
        String almacen_de_confianzas_clave_de_acceso = null;
        KeyStore keystore = null;
        KeyManagerFactory keyManagerFactory = null;
        char[] truststorepass_array = null;
        KeyStore truststore = null;
        char[] keystorepass_array = null;
        TrustManagerFactory trustManagerFactory = null;
        try {
            ruta = rutas.crear_ruta_desde_clase(getClass(), ruta_relativa_properties, ok);
            inputstream = new FileInputStream(ruta);
            propertie = new Properties();
            propertie.load(inputstream);
            if (protocolo_de_autenticacion == k_protocolo_con_autenticacion_en_servidor_y_cliente) {
                almacen_de_claves = (String) propertie.get(k_almacen_de_claves);
                ok.es = (almacen_de_claves != null);
                if (ok.es) {
                    almacen_de_claves = rutas.crear_ruta_desde_clase(getClass(), almacen_de_claves, ok);
                    if (ok.es) {
                        ok.es = (almacen_de_claves != null);
                    }
                }
                if (ok.es) {
                    almacen_de_claves_clave_de_acceso = (String) propertie.get(k_almacen_de_claves_clave_de_acceso);
                    if (ok.es) {
                        ok.es = (almacen_de_claves_clave_de_acceso != null);
                    }
                }
            }
            if (ok.es) {
                almacen_de_confianzas = (String) propertie.get(k_almacen_de_confianzas);
                if (ok.es) {
                    ok.es = (almacen_de_confianzas != null);
                }
            }
            if (ok.es) {
                almacen_de_confianzas = rutas.crear_ruta_desde_clase(getClass(), almacen_de_confianzas, ok);
                if (ok.es) {
                    ok.es = (almacen_de_confianzas != null);
                }
            }
            if (ok.es) {
                almacen_de_confianzas_clave_de_acceso = (String) propertie.get(k_almacen_de_confianzas_clave_de_acceso);
                if (ok.es) {
                    ok.es = (almacen_de_confianzas_clave_de_acceso != null);
                }
            }
            if (ok.es) {
                if (modo == k_modo_linea_de_comando) {
                    sslContext = SSLContext.getDefault();
                } else if (modo == k_modo_propiedades_sistema) {
                    // Metodo 1                
                    if (protocolo_de_autenticacion == k_protocolo_con_autenticacion_en_servidor_y_cliente) {
                        System.setProperty("javax.net.ssl.keyStore", almacen_de_claves);
                        System.setProperty("javax.net.ssl.keyStorePassword", almacen_de_claves_clave_de_acceso);
                    }
                    System.setProperty("javax.net.ssl.trustStore", almacen_de_confianzas);
                    System.setProperty("javax.net.ssl.trustStorePassword", almacen_de_confianzas_clave_de_acceso);   
                    sslContext = SSLContext.getDefault();
                } else if (modo == k_modo_cargando_almacenes) {
                    // Metodo 2
                    if (protocolo_de_autenticacion == k_protocolo_con_autenticacion_en_servidor_y_cliente) {
                        keystorepass_array = almacen_de_claves_clave_de_acceso.toCharArray();
                        keystore = obtener_almacen(almacen_de_claves, keystorepass_array, ok);
                        if (ok.es) {
                            ok.es = (keystore != null);
                        }
                        if (ok.es) {
                            keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm()); // "SunX509");
                            keyManagerFactory.init(keystore, keystorepass_array);
                        }
                    }
                    truststorepass_array = almacen_de_confianzas_clave_de_acceso.toCharArray();
                    truststore = obtener_almacen(almacen_de_confianzas, truststorepass_array, ok);
                    if (ok.es) {
                        ok.es = (truststore != null);
                    }
                    if (ok.es) {
                        trustManagerFactory = TrustManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm()); // "SunX509");
                        trustManagerFactory.init(truststore);
                        sslContext = SSLContext.getInstance("TLS");
                        if (protocolo_de_autenticacion == k_protocolo_con_autenticacion_en_servidor_y_cliente) {
                            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), numero_seguro_aleatorio);
                        } else {
                            sslContext.init(null, trustManagerFactory.getTrustManagers(), numero_seguro_aleatorio);                            
                        }
                    }
                }
            }
        } catch (Exception e) {
            ok.setTxt(tr.in(in, "ERROR DE EXCEPCIÓN AL CONFIGURAR EL CONTEXTO CLIENTE")
                , e);
        }
        return ok.es;
    }
    /**
     * 
     * @param ruta_almacen
     * @param pass_array
     * @param ok
     * @param extra_array
     * @return true si todo es correcto, false si hay error.
     * @throws Exception
     */
    public KeyStore obtener_almacen(String ruta_almacen, char [] pass_array, oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return null; }
        KeyStore keystore = null;
        try (
            InputStream inputstream = new FileInputStream(ruta_almacen);
                ) {
            if (ruta_almacen.toUpperCase().endsWith("PKCS")
                    || ruta_almacen.toUpperCase().endsWith("P12")
                    || ruta_almacen.toUpperCase().endsWith("PFX")) {
                keystore = KeyStore.getInstance("PKCS12");
                keystore.load(inputstream, pass_array);
            } else if (ruta_almacen.toUpperCase().endsWith("JKS")) {
                keystore = KeyStore.getInstance("JKS");
                keystore.load(inputstream, pass_array);
            } else {
                File file = new File(ruta_almacen);
                String extension = file.getName();
                int pos = extension.lastIndexOf('.');
                if (pos >= 0) {
                    extension = extension.substring(pos + 1);
                }
                keystore = KeyStore.getInstance(extension, new String(pass_array));
            }
        } catch (Exception e) {
            ok.setTxt(tr.in(in, "ERROR DE EXCEPCIÓN AL OBTENER ALMACEN")
                , e);
            keystore = null;
        }
        return keystore;
    }
}
