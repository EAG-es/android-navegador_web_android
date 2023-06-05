package innui.modelos;

import innui.modelos.errores.oks;
import innui.modelos.errores.patrones;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.util.Date;

/**
 *
 * @author emilio
 */
public class tipos_valores {
    public static String k_tipos_valores_formato_date = "%1$td/%1$tm/%1$ty %1$tT";
    public static String k_tipos_valores_formato_time = "%1$tT";
    public static String k_tipos_valores_formato_numero_con_agrupador = "%1$,d";
    public static String k_tipos_valores_formato_decimal_con_agrupador_2_decimales = "%1$,.2f";
    public static String k_tipos_valores_formato_decimal_con_agrupador_3_decimales = "%1$,.3f";
    public static String k_tipos_valores_formato_numero_sin_agrupador = "%1$d";
    public static String k_tipos_valores_formato_decimal_sin_agrupador_2_decimales = "%1$.2f";
    public static String k_tipos_valores_formato_decimal_sin_agrupador_3_decimales = "%1$.3f";
    public static String k_tipos_valores_formato_numero_con_agrupador_15 = "%1$,15d";
    public static String k_tipos_valores_formato_decimal_con_agrupador_15_2_decimales = "%1$,15.2f";
    public static String k_tipos_valores_formato_decimal_con_agrupador_15_3_decimales = "%1$,15.3f";
    public static String k_tipos_valores_formato_numero_sin_agrupador_15 = "%1$15d";
    public static String k_tipos_valores_formato_decimal_sin_agrupador_15_2_decimales = "%1$15.2f";
    public static String k_tipos_valores_formato_decimal_sin_agrupador_15_3_decimales = "%1$15.3f";
    public static String k_tipos_valores_formato_numero_con_agrupador_por_defecto = k_tipos_valores_formato_numero_con_agrupador_15;
    public static String k_tipos_valores_formato_decimal_con_agrupador_por_defecto = k_tipos_valores_formato_decimal_con_agrupador_15_2_decimales;
    public static String k_tipos_valores_formato_numero_sin_agrupador_por_defecto = k_tipos_valores_formato_numero_sin_agrupador_15;
    public static String k_tipos_valores_formato_decimal_sin_agrupador_por_defecto = k_tipos_valores_formato_decimal_sin_agrupador_15_2_decimales;
    public static String k_tipos_valores_modo_con_agrupador = "modo_con_agrupador";
    public static String k_tipos_valores_modo_sin_agrupador = "modo_sin_agrupador";
    /**
     * El nombre simple (getSimpleName) de una clase.
     */
    public String tipo;
    /**
     * Un objeto instanciado de una clase que tiene ese nombre.
     */
    public Object valor;

    public tipos_valores() {
        
    }
    
    public tipos_valores(String tipo, Object valor) {
        this.tipo = tipo;
        this.valor = valor;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public String toString() {
        String texto;
        if (tipo == null) {
            texto = "";
        } else {
            texto = tipo.toString();
        }
        texto = texto + ",";
        if (valor == null) {
            texto = texto + "";
        } else {
            texto = texto + valor.toString();
        }
        return texto;
    }
    /**
     * Pasa a texto el dato.
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public String pasar_a_texto_segun_tipo(oks ok, Object... extra_array) throws Exception {
        return pasar_a_texto_segun_tipo(k_tipos_valores_modo_con_agrupador, ok, extra_array);
    }
    /**
     * Pasa a texto el dato.
     * @param modo k_tipos_valores_modo_con_agrupador o k_tipos_valores_modo_sin_agrupador
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception 
     */
    public String pasar_a_texto_segun_tipo(String modo, oks ok, Object... extra_array) throws Exception {
        if (ok.es == false) { return null; }
        String retorno = null;
        try {
            String tipo_tex = getTipo();
            Object valor_tex = getValor();
            Date date;
            Time time;
            String formato_numero;
            String formato_decimal;
            if (modo.equals(k_tipos_valores_modo_con_agrupador)) {
                formato_numero = k_tipos_valores_formato_numero_con_agrupador_por_defecto;
                formato_decimal = k_tipos_valores_formato_decimal_con_agrupador_por_defecto;
            } else {
                formato_numero = k_tipos_valores_formato_numero_sin_agrupador_por_defecto;
                formato_decimal = k_tipos_valores_formato_decimal_sin_agrupador_por_defecto;
            }
            if (valor_tex == null) {
                retorno = "--";
            } else if (valor_tex instanceof String) {
                if (tipo_tex.toLowerCase().contains("date")
                 || tipo_tex.toLowerCase().contains("timestamp")) {
                    date = _convertir_fecha_y_hora(valor_tex.toString(), ok);
                    retorno = String.format(k_tipos_valores_formato_date, date);
                } else if (tipo_tex.toLowerCase().contains("time")) {
                    time = _convertir_hora(valor_tex.toString(), ok);
                    retorno = String.format(k_tipos_valores_formato_time, time);
                } else {
                    retorno = valor_tex.toString();
                }
            } else if (valor_tex instanceof Date) {
                retorno = String.format(k_tipos_valores_formato_date, (Date) valor);
            } else if (valor_tex instanceof Time) {
                retorno = String.format(k_tipos_valores_formato_time, (Time) valor);
            } else if (valor_tex instanceof Integer) {
                retorno = String.format(formato_numero, (Integer) valor);
            } else if (valor_tex instanceof Long) {
                retorno = String.format(formato_numero, (Long) valor);
            } else if (valor_tex instanceof Double) {
                retorno = String.format(formato_decimal, (double) valor);
            } else if (valor_tex instanceof Float) {
                retorno = String.format(formato_decimal, (Float) valor);
            } else if (valor_tex instanceof BigDecimal) {
                if (((BigDecimal)valor).remainder(BigDecimal.ONE).multiply(BigDecimal.valueOf(1000)).compareTo(BigDecimal.ZERO) > 0) {
                    retorno = String.format(formato_decimal, (BigDecimal) valor);
                } else {
                    retorno = String.format(formato_numero, ((BigDecimal) valor).toBigIntegerExact());
                }
            } else if (valor_tex instanceof BigInteger) {
                retorno = String.format(formato_numero, (BigInteger) valor);
            } else {
                retorno = valor_tex.toString();
            }
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return retorno;
    }
    /**
     * Convierte una hora según los formatos de k_patrones_formato_fecha y k_patrones_formato_hora (separados por |)
     * El texto se separa por el espacio en blanco entre la fecha y la hora. Y se convierten por separado
     * @param texto
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public Date _convertir_fecha_y_hora(String texto, oks ok, Object ... extras_array) throws Exception {
        Date date;
        date = patrones.convertir_fecha_y_hora(texto, ok, extras_array);
        if (ok.es == false) {
            date = patrones.convertir_fecha(texto, ok, extras_array);
        }
        return date;
    }    
    /**
     * Convierte una hora según los formatos de k_patrones_formato_hora (separados por |)
     * @param texto
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception 
     */
    public Time _convertir_hora(String texto, oks ok, Object ... extras_array) throws Exception {
        Time time;
        Date date;
        date = patrones.convertir_hora(texto, ok, extras_array);
        time = new Time(date.getTime());
        return time;
    }
}
