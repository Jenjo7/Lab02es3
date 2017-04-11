package it.unibo.savinistudio.gianni.lab02es3;

import android.support.v4.app.Fragment;

/**
 * Created by gianni on 11/04/17.
 */

public class InputFragment extends Fragment {

    /**
     * Per gestire il flusso dell'applicazione dal fragment all'activity viene definita un'interfaccia all'interno del fragment stesso.
     * Viene poi utilizzata una variabile del tipo dell'interfaccia per poter mantenere il collegamento con l'activity che andrà ad implementarla.
     * In questo modo sarà possibile utilizzare questa variabile per segnalare all'activity "ascoltatrice" quando vengono intercettati degli eventi,
     * e passare anche alla stessa dei dati come parametri dei metodi richiamabili.
     * Il collegamento tra l'activity e la variabile chiamata listener avviene nel metodo "onAttach".
     */
    public interface OnInputInteraction {
        void onButtonClick(String name, String surname, boolean bachelor, boolean newsletter);
    }

    private OnInputInteraction listener;

    /**
     * "Costruttore" statico del fragment; l'utilizzo di questo metodo, che ritorna un oggetto della classe corrente, rappresenta la modalità standard per
     * istanziare un oggetto di una classe Fragment.
     *
     * @return oggetto di classe InputFragment
     */
    public static InputFragment newInstance() {
        return new InputFragment();
    }

    /**
     * Costruttore di default/vuoto
     */
    public InputFragment() {

    }




}
