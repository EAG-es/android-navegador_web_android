/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package innui.modelos.jdbc;

import innui.modelos.errores.oks;
import innui.modelos.tipos_valores;
import innui.bases;
import android.innui.modelos_android.configuraciones.ResourceBundles;
import innui.modelos.internacionalizacion.tr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;

/**
 *
 * @author informatica
 */
public class clabs extends bases {
    public static String k_in_ruta = "assets/in/innui/modelos/jdbc/in";
    public static String k_clabs_id_error_crear = "clabs_id_error_crear";
    public static String k_clabs_id_error_leer = "clabs_id_error_leer";
    public static String k_clabs_id_error_actualizar = "clabs_id_error_actualizar";
    public static String k_clabs_id_error_borrar = "clabs_id_error_borrar";
    public Connection connection = null;
    
    /**
     * Devuelve una lista con las filas de la tabla productos 
     * @param sql_comando
     * @param ok
     * @param extra_array
     * @return null si hay error, una lista de productos si no hay error
     * @throws Exception
     */
    public boolean leer(sql_comandos sql_comando, oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return false; }
        ResourceBundle in;
        String comando;
        comando = sql_comando.getLeer_sql();
        LinkedHashMap<String, tipos_valores> columnas_mapa;
        Object object;
        tipos_valores tipo_valor;
        try (PreparedStatement preparedStatement = connection.prepareStatement(comando)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                sql_comando.leer_crear_lecturas_lista(ok, extra_array);
                if (ok.es == false) { return false; }
                while (resultSet.next() ) {
                    int i = 1;
                    columnas_mapa = sql_comando.crear_columnas_mapa(ok);
                    if (ok.es == false) { break; }
                    if (sql_comando.lectura_columnas_lista == null) {
                        in = ResourceBundles.getBundle(k_in_ruta);
                        ok.setTxt(tr.in(in, "Error, no se han definido las columnas que leer. "));
                        break;
                    }
                    for(String nombre: sql_comando.lectura_columnas_lista) {
                        object = resultSet.getObject(i);
                        if (resultSet.wasNull()) {
                            object = null;
                            tipo_valor = new tipos_valores("", object);
                        } else {
                            tipo_valor = new tipos_valores(object.getClass().getSimpleName(), object);
                        }
                        columnas_mapa.put(nombre, tipo_valor);
                        i = i + 1;
                    }
                    sql_comando.lecturas_lista.add(columnas_mapa);
                }
            } catch (Exception e) {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.setTxt(tr.in(in, "Error al leer: ") + comando, e);
            }
        } catch (Exception e) {
            in = ResourceBundles.getBundle(k_in_ruta);
            ok.setTxt(tr.in(in, "Error al leer: ") + comando, e);
        }
        return ok.es;
    }
    /**
     * Borra un registro, si es posible (no hay restricciones de integridad)
     * @param sql_comando
     * @param ok
     * @param extra_array
     * @return 
     * @throws Exception
     */
    public boolean borrar(sql_comandos sql_comando, oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return false; }
        ResourceBundle in;
        String comando;
        comando = sql_comando.borrado_sql;
        try (PreparedStatement preparedStatement = connection.prepareStatement(comando)) {
            int row = preparedStatement.executeUpdate();
            if (row <= 0) {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.setTxt(tr.in(in, "Error al borrar: ") + comando);
            }
        } catch (Exception e) {
            in = ResourceBundles.getBundle(k_in_ruta);
            ok.setTxt(tr.in(in, "Error al borrar: ") + comando, e);
        }
        return ok.es;
    }
    /**
     * Crear (inserta) una nueva fila
     * @param sql_comando
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public boolean crear(sql_comandos sql_comando, oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return false; }
        ResourceBundle in;
        String comando;
        comando = sql_comando.creacion_sql;
        try (PreparedStatement preparedStatement = connection.prepareStatement(comando)) {
            int row = preparedStatement.executeUpdate();
            if (row != 1) {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.setTxt(tr.in(in, "Error al crear: ") + comando);
            }
        } catch (Exception e) {
            in = ResourceBundles.getBundle(k_in_ruta);
            ok.setTxt(tr.in(in, "Error al crear: ") + comando, e);
        }
        return ok.es;
    }

    /**
     * Crear (inserta) una nueva fila y obtiene las nuevas claves autogeneradas
     * @param sql_comando
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public boolean crear_auto(sql_comandos sql_comando, oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return false; }
        ResourceBundle in;
        String comando;
        Object object;
        tipos_valores tipo_valor;
        comando = sql_comando.creacion_sql;
        try (PreparedStatement preparedStatement = connection.prepareStatement(comando, Statement.RETURN_GENERATED_KEYS)) {
            int row = preparedStatement.executeUpdate();
            if (row != 1) {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.setTxt(tr.in(in, "Error al crear: ") + comando);
            }
            int i = 1;
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                while (resultSet.next() ) {
                    i = 1;
                    sql_comando.clave_nueva_mapa = sql_comando.crear_columnas_mapa(ok);
                    if (ok.es == false) { break; }
                    if (sql_comando.clave_nueva_columnas_lista == null) {
                        in = ResourceBundles.getBundle(k_in_ruta);
                        ok.setTxt(tr.in(in, "Error, no se han definido las columnas de la clave primaria auto. "));
                        break;
                    }
                    for(String nombre: sql_comando.clave_nueva_columnas_lista) {
                        object = resultSet.getObject(i);
                        if (resultSet.wasNull()) {
                            object = null;
                        }
                        if (resultSet.wasNull()) {
                            object = null;
                            tipo_valor = new tipos_valores("", object);
                        } else {
                            tipo_valor = new tipos_valores(object.getClass().getSimpleName(), object);
                        }
                        sql_comando.clave_nueva_mapa.put(nombre, tipo_valor);
                        i = i + 1;
                    }
                }
            } catch (Exception e) {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.setTxt(tr.in(in, "Error al recuperar la clave creada: ") + comando, e);
            }
        } catch (Exception e) {
            in = ResourceBundles.getBundle(k_in_ruta);
            ok.setTxt(tr.in(in, "Error al crear: ") + comando, e);
        }
        return ok.es;
    }

    public boolean actualizar(sql_comandos sql_comando, oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return false; }
        ResourceBundle in;
        String comando;
        comando = sql_comando.actualizacion_sql;
        try (PreparedStatement preparedStatement = connection.prepareStatement(comando)) {
            int row = preparedStatement.executeUpdate();
            if (row <= 0) {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.setTxt(tr.in(in, "Error al actualizar: ") + comando);
            }
        } catch (Exception e) {
            in = ResourceBundles.getBundle(k_in_ruta);
            ok.setTxt(tr.in(in, "Error al actualizar: ") + comando, e);
        }
        return ok.es;
    }
}
