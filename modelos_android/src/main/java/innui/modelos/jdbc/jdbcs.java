/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package innui.modelos.jdbc;

import innui.bases;
import android.innui.modelos_android.configuraciones.ResourceBundles;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

/**
 *
 * @author informatica
 */
public class jdbcs extends bases {
    public static String k_in_ruta = "assets/in/innui/modelos/jdbc/in";
    public Connection connection = null;
    public static String jdbc_uri_tex = "jdbc_uri";
    public static String jdbc_usuario_tex = "jdbc_usuario";
    public static String jdbc_clave_tex = "jdbc_clave";
    
    /**
     * Conecta con una base de datos
     * @param clase_driver_jdbc
     * @param clase_driver_jdbc_uri_conexion
     * @param usuario
     * @param clave
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public boolean conectar(String clase_driver_jdbc, String clase_driver_jdbc_uri_conexion, String usuario, String clave
            , oks ok, Object... extra_array) throws Exception {
        ResourceBundle in;
        if (ok.es == false) { return false; }
        try {
            if (connection == null) {
                // Instalar la librería con los drivers:
                // /usr/share/java/mysql-connector-java-8.0.26.jar
                // "com.mysql.jdbc.Driver"
                Class.forName(clase_driver_jdbc);
                connection = DriverManager.getConnection(clase_driver_jdbc_uri_conexion, usuario, clave);
            }
        } catch (Exception e) {
            in = ResourceBundles.getBundle(k_in_ruta);
            ok.setTxt(tr.in(in, "Error al conectar con el Driver JDBC. "), e);
        }
        return ok.es;
    }
    /**
     * Anula la conexión existente
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public boolean desconectar(oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return false; }
        try {
            connection = null;
        } catch (Exception e) {
            throw e;
        }
        return ok.es;
    }
}
