package android.ingui.utiles;

import android.content.Context;
import android.content.DialogInterface;
import android.innui.modelos_android.configuraciones.ResourceBundles;

import androidx.appcompat.app.AlertDialog;

import java.io.File;
import java.util.ResourceBundle;

import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;

public class alertas {
    public static String k_in_ruta;
    static {
        String paquete_tex = alertas.class.getPackage().getName();
        paquete_tex = paquete_tex.replace(".", File.separator);
        k_in_ruta = "assets/in/" + paquete_tex + "/in";
    }
    public static boolean poner_alerta(Context contexto, String mensaje, oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            ResourceBundle in = null;
            in = ResourceBundles.getBundle(k_in_ruta);
            final String error_final = mensaje;
            AlertDialog.Builder alertDialog_Builder = new AlertDialog.Builder(contexto);
            alertDialog_Builder.setMessage(mensaje);
            alertDialog_Builder.setPositiveButton(tr.in(in,"Aceptar"), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    throw new RuntimeException(error_final);
                }
            });
            alertDialog_Builder.create();
            alertDialog_Builder.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ok.es;
    }
}
