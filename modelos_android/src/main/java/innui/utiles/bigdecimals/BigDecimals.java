/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package innui.utiles.bigdecimals;

import android.innui.modelos_android.configuraciones.ResourceBundles;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;
import innui.bases;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ResourceBundle;

/**
 *
 * @author emilio
 */
public class BigDecimals extends bases {
    public static String k_in_ruta = "assets/in/innui/utiles/bigdecimals/in";
    /**
     * Quita decimales a un número (precision por máxima defecto 10 decimales y HALF_UP)
     * @param bigDecimal Número al que quitar decimales
     * @param decimales_a_quitar Cuantos decimales a quitar (desde atrás)
     * @param ok Comunicar resultados
     * @param extra_array Posición 0 = MathContext nueva precisión máxima por defecto. Resto opción de añadir parámetros en el futuro.
     * @return El número con decimales quitados.
     * @throws Exception Opción de notificar errores de excepción
     */
    public static BigDecimal quitar_decimales(BigDecimal bigDecimal, int decimales_a_quitar, oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return null; }
            BigDecimal retorno;
            MathContext mathContext = null;
            ResourceBundle in;
            in = ResourceBundles.getBundle(k_in_ruta);
            if (extra_array != null
             && extra_array.length > 0) {
                if (extra_array[0] instanceof MathContext) {
                    mathContext = (MathContext) extra_array[0];
                } else {
                    ok.setTxt(tr.in(in, "El primer elemento opcional debe ser de la clase MathContext. "));
                }
            } else {
                mathContext = new MathContext(10, RoundingMode.HALF_UP);
            }
            BigDecimal [] bigDecimal_array = bigDecimal.divideAndRemainder(BigDecimal.ONE);
            bigDecimal_array[1] = redondear(bigDecimal_array[1], mathContext, ok);
            if (ok.es == false) { return null; }
            bigDecimal_array[1] = bigDecimal_array[1].stripTrailingZeros();
            String decimales_txt = bigDecimal_array[1].toPlainString();
            int decimales_num = decimales_txt.length() - 2; // "0."
            decimales_num = decimales_num - decimales_a_quitar;
            if (decimales_num >= 0) {
                BigDecimal nuevo_divisor = BigDecimal.valueOf(1, decimales_num);
                bigDecimal_array = bigDecimal.divideAndRemainder(nuevo_divisor);                
                retorno = bigDecimal.subtract(bigDecimal_array[1]);
                retorno =  retorno.stripTrailingZeros();
            } else { // if (decimales_num == 0) {
                retorno = new BigDecimal(bigDecimal.toBigInteger());
                retorno = retorno.setScale(0);
//            } else {
//                retorno = new BigDecimal(bigDecimal_array[1].toBigInteger());
            }
            return retorno;
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * Quita decimales a un número redondeando (precision máxima por defecto 10 decimales y HALF_UP)
     * @param bigDecimal Número al que quitar decimales
     * @param decimales_a_quitar Cuantos decimales a quitar (desde atrás)
     * @param roundingMode Modo de redondeo
     * @param ok Comunicar resultados
     * @param extra_array Posición 0 = MathContext nueva precisión máxima por defecto. Resto opción de añadir parámetros en el futuro.
     * @return El número con decimales quitados.
     * @throws Exception Opción de notificar errores de excepción
     */
    public static BigDecimal quitar_decimales_redondeando(BigDecimal bigDecimal
            , int decimales_a_quitar
            , RoundingMode roundingMode
            , oks ok
            , Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return null; }
            BigDecimal retorno = null;
            BigDecimal resto = null;
            BigDecimal resto_redondeado = null;
            MathContext mathContext = null;
            ResourceBundle in;
            in = ResourceBundles.getBundle(k_in_ruta);
            if (extra_array != null
             && extra_array.length > 0) {
                if (extra_array[0] instanceof MathContext) {
                    mathContext = (MathContext) extra_array[0];
                } else {
                    ok.setTxt(tr.in(in, "El primer elemento opcional debe ser de la clase MathContext. "));
                }
            } else {
                mathContext = new MathContext(10, RoundingMode.HALF_UP);
            }
            BigDecimal [] bigDecimal_array = bigDecimal.divideAndRemainder(BigDecimal.ONE);
            bigDecimal_array[1] = redondear(bigDecimal_array[1], mathContext, ok);
            if (ok.es == false) { return null; }
            bigDecimal_array[1] = bigDecimal_array[1].stripTrailingZeros();
            String decimales_txt = bigDecimal_array[1].toPlainString();
            int decimales_num = decimales_txt.length() - 2; // "0."
            decimales_num = decimales_num - decimales_a_quitar;
            if (decimales_num >= 0) {
                BigDecimal nuevo_divisor = BigDecimal.valueOf(1, decimales_num);
                bigDecimal_array = bigDecimal.divideAndRemainder(nuevo_divisor);
                resto = bigDecimal_array[1];
                retorno = bigDecimal.subtract(resto);
                mathContext = new MathContext(decimales_num + 1, roundingMode);
                resto_redondeado = redondear(resto, mathContext, ok);
                if (ok.es == false) { return null; }
                retorno = retorno.add(resto_redondeado);
                retorno = retorno.stripTrailingZeros();
            } else { // if (decimales_num == 0) {
                retorno = new BigDecimal(bigDecimal.toBigInteger());
                retorno = retorno.setScale(0);
//            } else {
//                retorno = redondear(bigDecimal_array[0], mathContext, ok);
            }
            return retorno;
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * Redondea de una manera simplificada
     * @param bigDecimal Número que redondear 
     * @param mathContext Información para el redondeo
     * @param ok Comunicar resultados
     * @param extra_array Hasta 2 parámetros, según la división de BigDecimal que se desee hacer.
     * @return El resultado de la división o un valor cercano a Double.POSITIVE_INFINITY o Double.NEGATIVE_INFINITY
     * @throws Exception Opción de notificar errores de excepción
     */
    public static BigDecimal redondear(BigDecimal bigDecimal, MathContext mathContext, oks ok, Object ... extra_array) throws Exception {
        BigDecimal retorno = null;
        try {
            if (ok.es == false) { return null; }
//            BigDecimal entero;
            int precision = mathContext.getPrecision();
            if (precision > 0) {
//                precision = precision - 1;
                BigDecimal multiplicador = BigDecimal.valueOf(1, -precision);
                retorno = bigDecimal.multiply(multiplicador, mathContext);
//                try {
//                    entero = new BigDecimal(retorno.toBigInteger());
//                } catch (Exception e) {
//                    entero = BigDecimal.valueOf(retorno.longValue());
//                }
//                BigDecimal parte_decimal = retorno.subtract(entero);
//                if (parte_decimal.compareTo(BigDecimal.valueOf(0.1)) >= 0
//                 || parte_decimal.compareTo(BigDecimal.valueOf(-0.1)) <= 0) {
                    // no es 0 el primer decimal
                    try {
                        retorno = new BigDecimal(retorno.toBigInteger());
                    } catch (Exception e) {
                        retorno = BigDecimal.valueOf(retorno.longValue());
                    }
                    retorno = divide_0(retorno, BigDecimal.valueOf(10.0), ok, mathContext);
                    if (ok.es == false) { return null; }
                    retorno = retorno.setScale(0, mathContext.getRoundingMode());
                    retorno = retorno.multiply(BigDecimal.valueOf(10.0), mathContext);
//                } else {
//                    retorno = entero;
//                }
                retorno = divide_0(retorno, multiplicador, ok, mathContext);
                if (ok.es == false) { return null; }
            } else {
                retorno = bigDecimal;
            }
        
        } catch (Exception e) {
            throw e;
        }
        return retorno;
    }
    /**
     * Divide comprobando la división entre 0 y retornando un valor cercano a Double.MAX_VALUE o -Double.MAX_VALUE según corresponda, si hay división por 0 (convertido a BigInteger)
     * Si no se indica nada, opera con 8 decimales y HALF_UP
     * @param dividendo 
     * @param divisor
     * @param ok Comunicar resultados
     * @param extra_array Hasta 2 parámetros, según la división de BigDecimal que se desee hacer.
     * @return El resultado de la división o un valor cercano a Double.POSITIVE_INFINITY o Double.NEGATIVE_INFINITY
     * @throws Exception Opción de notificar errores de excepción
     */
    @SuppressWarnings( "deprecation" )
    public static BigDecimal divide_0(BigDecimal dividendo, BigDecimal divisor, oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return null; }
            ResourceBundle in;
            in = ResourceBundles.getBundle(k_in_ruta);
            BigDecimal retorno = null;
            if (divisor.compareTo(BigDecimal.ZERO) == 0) {
                ok.setTxt(tr.in(in, "División entre 0. "));
                if (dividendo.compareTo(BigDecimal.ZERO) >= 0) {
                    if (divisor.compareTo(BigDecimal.ZERO) >= 0) {
                        retorno = BigDecimal.valueOf(1.0*Double.MAX_VALUE);
                    } else {
                        retorno = BigDecimal.valueOf(-1.0*Double.MAX_VALUE);
                    }
                } else {
                    if (divisor.compareTo(BigDecimal.ZERO) >= 0) {
                        retorno = BigDecimal.valueOf(-1.0*Double.MAX_VALUE);
                    } else {
                        retorno = BigDecimal.valueOf(1.0*Double.MAX_VALUE);
                    }
                }
            } else {
                if (extra_array == null || extra_array.length == 0) {
                    MathContext mathContext;
                    mathContext = new MathContext(8, RoundingMode.HALF_UP);
                    return dividendo.divide(divisor, mathContext);
                } else if (extra_array.length == 1) {
                    if (extra_array[0] instanceof MathContext) {
                        retorno = dividendo.divide(divisor, (MathContext) extra_array[0]);
                    } else if (extra_array[0] instanceof RoundingMode) {
                        retorno = dividendo.divide(divisor, (RoundingMode) extra_array[0]);
                    } else {
                        retorno = dividendo.divide(divisor, (Integer) extra_array[0]);
                    }
                } else if (extra_array.length == 2) {
                    if (extra_array[1] instanceof RoundingMode) {
                        retorno = dividendo.divide(divisor, (Integer) extra_array[0], (RoundingMode) extra_array[1]);
                    } else {
                        retorno = dividendo.divide(divisor, (Integer) extra_array[0], (Integer) extra_array[1]);
                    }
                } else {
                    ok.setTxt(tr.in(in, "Parámetros opcionales no válidos. "));
                }
            }
            return retorno;
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
     * Divide comprobando la división entre 0 y retornando un valor cercano a Double.POSITIVE_INFINITY o Double.NEGATIVE_INFINITY según corresponda, si hay división por 0 (convertido a BigInteger)
     * @param dividendo 
     * @param divisor
     * @param ok Comunicar resultados
     * @param extra_array Hasta 1 parámetro, según la división de BigDecimal que se desee hacer.
     * @return El resultado de la división o o un valor cercano a Double.POSITIVE_INFINITY o Double.NEGATIVE_INFINITY
     * @throws Exception Opción de notificar errores de excepción
     */
    public static BigDecimal divideToIntegralValue_0(BigDecimal dividendo, BigDecimal divisor, oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return null; }
            ResourceBundle in;
            in = ResourceBundles.getBundle(k_in_ruta);
            BigDecimal retorno = null;
            if (divisor.toBigInteger().compareTo(BigInteger.ZERO) == 0) {
                ok.setTxt(tr.in(in, "División entre 0. "));
                if (dividendo.compareTo(BigDecimal.ZERO) >= 0) {
                    if (divisor.compareTo(BigDecimal.ZERO) >= 0) {
                        retorno = BigDecimal.valueOf(1.0*Double.MAX_VALUE);
                    } else {
                        retorno = BigDecimal.valueOf(-1.0*Double.MAX_VALUE);
                    }
                } else {
                    if (divisor.compareTo(BigDecimal.ZERO) >= 0) {
                        retorno = BigDecimal.valueOf(-1.0*Double.MAX_VALUE);
                    } else {
                        retorno = BigDecimal.valueOf(1.0*Double.MAX_VALUE);
                    }
                }
            } else {
                if (extra_array == null || extra_array.length == 0) {
                    return dividendo.divide(divisor);
                } else if (extra_array.length == 1) {
                    if (extra_array[0] instanceof MathContext) {
                        retorno = dividendo.divideToIntegralValue(divisor, (MathContext) extra_array[0]);
                    }
                } else {
                    ok.setTxt(tr.in(in, "Parámetros opcionales no válidos. "));
                }
            }
            return retorno;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static BigDecimal nulo_es_0(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return BigDecimal.ZERO;
        } else {
            return bigDecimal;
        }
    }
}
