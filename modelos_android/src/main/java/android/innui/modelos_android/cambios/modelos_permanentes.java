package android.innui.modelos_android.cambios;

import androidx.lifecycle.ViewModel;

import java.io.Serializable;

public class modelos_permanentes extends ViewModel {
    public interface i_modelos_permanentes extends Serializable {
        void onCleared();
    }
    public i_modelos_permanentes i_modelo_permanente = null;

    @Override
    public void onCleared() {
        if (i_modelo_permanente != null) {
            i_modelo_permanente.onCleared();
        } else {
            System.exit(0);
        }
    }
}
