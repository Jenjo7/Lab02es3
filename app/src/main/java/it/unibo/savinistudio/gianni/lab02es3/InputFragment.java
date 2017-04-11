package it.unibo.savinistudio.gianni.lab02es3;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

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

    private EditText edtName;
    private EditText edtSurname;
    private RadioButton rdbTri;
    private RadioButton rdbMas;
    private CheckBox chkNews;
    private Button btnNews;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.input_fragment, container, false);
        edtName = (EditText) view.findViewById(R.id.edt_name);
        edtSurname = (EditText) view.findViewById(R.id.edt_surname);
        rdbTri = (RadioButton) view.findViewById(R.id.rdb_tri);
        rdbMas = (RadioButton) view.findViewById(R.id.rdb_mas);
        chkNews = (CheckBox) view.findViewById(R.id.chb_newsletter);
        btnNews = (Button) view.findViewById(R.id.btn_insert);
        // Creazione di un listener per intercettare il click sul bottone da parte dell'utente
        btnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null) {
                    //Passo gli argomenti da intercettare, al metodo OnButtonClick dell'interfaccia OnInputInteraction
                    listener.onButtonClick(edtName.getText().toString(), edtSurname.getText().toString(), rdbTri.isChecked(), chkNews.isChecked());
                }
            }
        });

        return view;
    }
    /**
     * Metodo del ciclo di vita del fragment che viene richiamato quando lo stesso viene "collegato" ad un'activity.
     * Il parametro context è appunto il riferimento all'activity appena citata. Tramite la parola chiave "instanceof" si verifica che
     * l'activity implementi l'interfaccia definita dal fragment; se risulta essere così, viene salvato il riferimento all'activity in modo
     * da poter richiamare i metodi dell'interfaccia che l'activity ha implementato.
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnInputInteraction) {
            listener = (OnInputInteraction) context;
        }
    }
    /**
     * Quando il fragment viene distrutto, viene eliminato il collegamento con l'activity.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        listener = null;
    }

}
