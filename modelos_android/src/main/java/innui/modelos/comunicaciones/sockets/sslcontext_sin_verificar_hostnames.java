package innui.modelos.comunicaciones.sockets;

import innui.modelos.errores.oks;
import innui.bases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 *
 * @author emilio
 */
public class sslcontext_sin_verificar_hostnames extends bases implements Serializable {
    public SSLSocketFactory inicial_SSLSocketFactory = null;
    public SSLSocketFactory actual_SSLSocketFactory = null;
    public HostnameVerifier inicial_hostnameVerifier = null;
    public HostnameVerifier actual_hostnameVerifier = null;
    public SSLContext actual_SSLContext = null;
    public List <String> hostnames_a_evitar_list = new ArrayList ();
    
    public sslcontext_sin_verificar_hostnames() 
    {
        hostnames_a_evitar_list.add("localhost");
    }

    public boolean poner_SSLContext(SSLContext sSLContext, oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return ok.es; }
        actual_SSLContext = sSLContext;
        return ok.es;
    }

    public SSLContext leer_SSLContext(oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return null; }
        return actual_SSLContext;
    }

    public boolean poner_HostnameVerifier(HostnameVerifier hostnameVerifier, oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return ok.es; }
        _guardar_HostnameVerifier(ok);
        if (ok.es == false) { return ok.es; }
        actual_hostnameVerifier = hostnameVerifier;
        HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
        return ok.es;
    }
    
    public boolean poner_SSLSocketFactory(SSLSocketFactory sSLSocketFactory, oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return ok.es; }
        _guardar_SSLSocketFactory(ok);
        if (ok.es == false) { return ok.es; }
        actual_SSLSocketFactory = sSLSocketFactory;
        HttpsURLConnection.setDefaultSSLSocketFactory(sSLSocketFactory);
        return ok.es;
    }
    
    public boolean _guardar_SSLSocketFactory(oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return ok.es; }
        inicial_SSLSocketFactory = HttpsURLConnection.getDefaultSSLSocketFactory();
        return ok.es;
    }

    public boolean restaurar_SSLSocketFactory(oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return ok.es; }
        if (inicial_SSLSocketFactory != null) {
            HttpsURLConnection.setDefaultSSLSocketFactory(inicial_SSLSocketFactory);
        }
        actual_SSLSocketFactory = null;
        actual_SSLContext = null;
        return ok.es;
    }

    public boolean _guardar_HostnameVerifier(oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return ok.es; }
        inicial_hostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
        return ok.es;
    }

    public boolean restaurar_HostnameVerifier(oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return ok.es; }
        if (inicial_hostnameVerifier != null) {
            HttpsURLConnection.setDefaultHostnameVerifier(inicial_hostnameVerifier);
        }
        actual_hostnameVerifier = null;
        return ok.es;
    }
    
    public boolean presentar_contenido_tls_sin_verificar_hostnames(oks ok, Object ... extras_array) throws Exception {
        if (ok.es == false) { return ok.es; }
        // Crear un trust manager que lo valida todo
        TrustManager[] trustAllCerts = new TrustManager[] { 
            new X509TrustManager() {     
                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() { 
                    return null;
                } 
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                } 
                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                }
            } 
        }; 
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS"); 
            sSLContext.init(null, trustAllCerts, new java.security.SecureRandom()); 
            poner_SSLContext(sSLContext, ok);
            if (ok.es == false) { return ok.es; }
            poner_SSLSocketFactory(actual_SSLContext.getSocketFactory(), ok);
            if (ok.es == false) { return ok.es; }
            HostnameVerifier hostnameVerifier = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname
                , javax.net.ssl.SSLSession sslSession) {
                    if (hostnames_a_evitar_list.contains(hostname)) {
                        return true;
                    } else {
                        return false;
                    }
                }
            };
            poner_HostnameVerifier(hostnameVerifier, ok);
            if (ok.es == false) { return ok.es; }
        } catch (Exception e) {
            ok.setTxt(e);
        }   
        return ok.es;
    }
}
