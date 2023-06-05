package android.innui.modelos_android;

import static java.lang.System.exit;

import android.innui.modelos_android.configuraciones.iniciales;
import innui.modelos.errores.oks;

import java.util.ResourceBundle;

/**
 *
 * @author emilio
 
 Librería de uso genérico en Java
 Utilizar esta librería permite crear aplicaciones con:
 - Modelo de inicio de librerías y aplicaciones
 - Modelo de internacionalización de textos extendido
 - Modelo de gestión de configuración inicial de propiedades en recursos
 - Modelo de concurrencia extendido
 - Modelo de notificación de errores adicional
 - Utilidades de uso de interfaz de línea de comando, uso de bigdecimals y conversiones y uso de textos
 
 El modelo de inicio de librerías y aplicaciones 
 1) La clase principal de una librería o aplicación se encuentra en la carpeta con el nombre de la misma. La cual se sitúa dentro de un paquete que identifica sus propiedades fundamentales:
 - Incluye la internacionalización de textos, dentro del paquete de recursos: in
 - Indica el tipo de interfaz que requiere
 - innui : No usa interfaz (puede usar stdin, stdout y stderr como corrientes de datos (no interactivamente))
 - inclui : Incerfaz de línea de comando. Usa stdin, stdout, stderr interactivamente
 - ingui : Interfaz gráfico. Se debe indicar, en un subpaquete, el marco de tecnología gráfica de la que depende (Swing, JavaFX,...)
 - inser : Interfaz de servicio ofrecido como servidor del mismo. Se debe indicar, en un subpaquete, el modelo de servicio del que depende (Rest, SOAP, TCP Sockets, RMI,...)
 - incli : Ciente de un interfaz de servicio. Se debe indicar, en un subpaquete, el modelo de servicio del que depende (Rest, SOAP, TCP Sockets, RMI,...)
 - inweb : Interfaz mediante cliente ligero genérico HTML (navegador web). Se debe indicar, en un subpaquete, el marco de tecnología web de la que depende (Spring, JSF, Strout,... )
 2) La clase principal hereda de la clase: iniciales (que deriva de: bases). O puede heredar de la clase: modelos_android (para aprovechar código)
 - Debe implementar el método: run, el cual contiene el código de ejecución desde un objeto de clase. (se debe llamar desde: main)
 - Debe implementar el método: iniciar, el cual llama a todos los métodos de inicio de las librerías adicionales de las que depende la librería que se está iniciando 
 - Alternativamente, si hereda de modelos_android, solo necesitar implementar un método: iniciar con las llamadas a los inicios de las librerías adicionales de las que depende la librería que se está iniciando.
 - Por defecto la implementación de: _iniciar_desde_clase extrae los recursos contenidos en las carpetas de recursos del .jar de la librería o la aplicación: in y re. Y los copia en un arbol de directorios igual pero fuera del archivo .jar. 
 - Todos los .jar de los que hay dependencia deberían estar en la misma carpeta que el jar principal para la correcta extracción.
 - No deben repetirse los nombres de carpetas o archivos de recursos en los .jar, pues serían sobreescritos en la extracción.
 - Para acceder a los recursos extraidos, en lugar de los recursos del .jar, se debe hacer uso de la clase: Resources, en caso contrario se accede a los recursos del .jar y los cambios no tiene efecto.
 3) Las clases que se crean en nuevas librerías o aplicaciones que hacen uso de la librería modelos_android:
 - Pueden heredar la clase: bases, la cual:
 - Incorpora mecanismos de uso de log. Por defecto: logger.log en la salida estandar y en memoria. 
 - Utiliza el modelo de errores de propia librería: modelos_android, las clases Loggers y SystemLogger_utils
 - Ofrece métodos para escribir en el log, para escribir errores (stderr) y mensajes (stdout)
 - Ofrece métodos para formar texto (String.format), números (NumberFormat) y fechas (DateFormat)
 - Incluye un mapa de Objetos (mapa) para extender atributos en tiempo de ejecución
 - Incluye un objeto (o) para reemplazar los comportamientos por defecto, en tiempo de ejecución.
 4) Presenta una clase sencilla para la notificación de errores: oks
 - Con un campo de texto (txt) para dejar y obtener el mensaje de error (mensaje humano).
 - Con un campo de texto (id) para identificar el error de manera única (para su procesamiento por el código)
 - Con un campo booleano (es) para indicar si hubo o no error
 - Con un campo (gravedad) que califica la misma entre 0 y 10.
 - Tiene métodos para extraer mensajes de error de las excepciones, detectar errores por tener un valor nulo un objeto, y para poner y concatenar textos (txt)
 5) Para la programación con hilos concurrentes, presenta la clase Threads
 - Tiene un mapa de Objetos sincronizado para los accesos concurrentes
 - Tiene una referencia atómica a un objeto de notificación de errores (ref_ok)
 - Tiene una referencia atómica a un objeto de la clase Loggers (ref_logger)
 - Ofrece métodos sleep con notificación de errores con un parámetro de la clase oks
 6) Permite gestionar los mensajes de texto traducibles de manera simplificada mediante la clase: tr
 - Traduce un texto utilizando un objeto ResourceBundle (que puede obtenerse con la clase ResourceBundles de la propia librería para manejar los recursos extraidos del .jar en lugar de los recursos internos)
 - Si no puede traducirlo, escribe un mensaje en la stdout (no lanza excepción) y usa el texto original.
 
 Esta librería está en evolución constante, y no se entrega con ninguna garantía de funcionamiento.
 La filosofía de construcción de clases y métodos que propone y sigue es la siguiente:
 - Los nombres compuestos se separan con guión bajo (notación baja) y todos están en minúsculas (No se sigue la notación de camello)
 - Las clases tienen nombre en plural, y los paquetes.
 - Los métodos son todos públicos (los que se pensaron para ser privados comienzan con guión bajo: _)
 - Los nombres de los métodos comienzan por verbo infinitivo, y se escriben con palabras completas 
 - Los atributos, parámetros y variables se escriben con palabras completas, y están en singular. Pueden tener sufijos para indicar su tipo o especifidad (_num, _tex, _array, _lista, _mapa)
 - Las normas pueden tener excepciones si estas son muy beneficiosas
 - No hay constantes. Todo es susceptible de ser corregido. Los datos que se pensaron para ser constantes comienzan con el prefijo k_ (pero son modificables)
 
 El lenguaje de programación Java es de los años 90 en el siglo XX. Su filosofía se basa en la creación de una herramienta básica de bajo nivel, como sustituto mejorado del lenguaje de programación ensamblador (sustituto mejorado del código máquina)
 Las librerías de Java tienen la filosofía de ofrecer la funcionalidad más básica posible (primitivas mínimas, de capa 0) de modo que se atomizaron todos los comportamientos para ser lo más sencillos posibles.
 Los desarrollos actuales, en el siglo XXI, requieren unas rutinas que sean de un nivel superior al de las primitivas básicas de las librerías de Java.
 Por ese motivo, se han codificado, con el tiempo, infinidad de frameworks de nivel superior (capa 1). Lo que ha supuesto una multiplicidad de trabajos que competían entre sí. Y que han producido códigos muy diferentes, difíciles de entender y mantener, y que se iban quedando anticuados en la medida en la que nuevos framework conseguían la simpatía o adhesión de nuevos grupos de desarrolladores. También por incorporar nuevas tecnologías.
 El resultado ha sido una guerra de frameworks que ha perjudicado a todos los desarrollos que eligieron la solución que no tenía más apoyo y seguimiento. O que acababa por perderlo, por aparecer un reemplazo nuevo y más atractivo.
 La consecuencia de todo esto ha sido una elevada variedad y complejidad dentro de las posibles soluciones a disposición de los desarrolladores. Lo que ha causado tal descontento, que muchos preferían cambiar a otros lenguajes de programación. Más sencillo. Con menos código de capa 0 y más funcionalidades estandarizadas de capa 1.
 Si los programadores no aprendemos de los errores del pasado, nunca saldremos del bucle de reemplazos tecnológicos que hacen lo mismo siempre, pero con ligeras diferencias, y con incompatibilidades de lo que se hizo en el pasado (que podría haberse reutilizado con muyo mayor éxito, y quedó obsoleto por no estás a la moda del momento)
 * 
 */
public class modelos_android extends iniciales {
    /**
     * Inicio de la aplicación
     * @param args 
     */
    public static void main(String[] args) {
        oks ok = new oks();
        try {
            ResourceBundle in = null;
            modelos_android modelo_android = null;
            try {
                modelo_android = new modelos_android();
                modelo_android.run(ok);
            } catch (Exception e) {
                ok.setTxt(e);
            }
        } catch (Exception e) {
            ok.setTxt(e);
        }
        if (ok.es == false) {
            System.err.println(ok.txt);
            exit(1);
        } else {
            exit(0);
        }
    }    
    /**
     * Inicio de la aplicación desde un objeto instanciado
     * @param ok Comunicar resultados
     * @param extras_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public boolean run(oks ok, Object ... extras_array) throws Exception {
        try {
            if (ok.es == false) { return ok.es; }
            iniciar(ok);
            if (ok.es) {
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
        // Iniciar clase principal de la librería
        if (ok.es == false) { return ok.es; }
        _poner_aplicacion(this.getClass(), ok);
        if (ok.es == false) { return ok.es; }
        _iniciar_desde_clase(null, this.getClass(), ok);
        return ok.es;
    }

    @Override
    public boolean terminar(oks ok, Object... extra_array) throws Exception {
        // Terminar clase principal de la librería
        _terminar_desde_clase(this.getClass(), ok);
        return ok.es;
    }
    
}
