package it.unibo.savinistudio.gianni.lab02es3;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by gianni on 11/04/17.
 */

public class ShowFragment extends Fragment {
    /**
     * Per gestire il flusso dell'applicazione dal fragment all'activity viene definita un'interfaccia all'interno del fragment stesso.
     * Viene poi utilizzata una variabile del tipo dell'interfaccia per poter mantenere il collegamento con l'activity che andrà ad implementarla.
     * In questo modo sarà possibile utilizzare questa variabile per segnalare all'activity "ascoltatrice" quando vengono intercettati degli eventi,
     * e passare anche alla stessa dei dati come parametri dei metodi richiamabili.
     * Il collegamento tra l'activity e la variabile chiamata listener avviene nel metodo "onAttach".
     */
    public interface OnShowInteraction {
        void onBackClick();
    }

    private OnShowInteraction listener;

    /**
     * "Costruttore" statico del fragment; l'utilizzo di questo metodo, che ritorna un oggetto della classe corrente, rappresenta la modalità standard per
     * istanziare un oggetto di una classe Fragment.
     * Per il passaggio di paramentri si utilizza la classe Bundle. In un oggetto della suddetta classe, è possibile "inserire" dei dati
     * utilizzando il meccanismo chiave-valore.
     * Una volta terminati i dati da inserire, il bundle stesso viene passato al fragment tramite il metodo "setArguments" .
     * Questi dati andranno poi recuperati nel metodo "onCreate".
     *
     * @param name
     * @param surname
     * @param bachelor
     * @param newsletter
     * @return oggetto di classe ShowFragment
     */
    public static ShowFragment newInstance(String name, String surname, boolean bachelor, boolean newsletter) {
        ShowFragment fragment = new ShowFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("surname", surname);
        bundle.putBoolean("bachelor", bachelor);
        bundle.putBoolean("newsletter", newsletter);
        fragment.setArguments(bundle);
        return fragment;
    }

    private TextView txvName;
    private TextView txvSurname;
    private TextView txvDegree;
    private TextView txvNews;
    private Button btnReturn;

    private String name;
    private String surname;
    private boolean bachelor;
    private boolean newsletter;

    //Costruttore vuoto richiesto dal sistema per poter funzionare correttamente in tutte le situazioni
    public ShowFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_fragment, container, false);
        System.out.println(newsletter);
        // Creazione dei riferimento con gli elementi della view tramite l'id univoco assegnatoli
        txvName = (TextView) view.findViewById(R.id.txv_name);
        txvSurname = (TextView) view.findViewById(R.id.txv_surname);
        txvDegree = (TextView) view.findViewById(R.id.txv_txv_degree);
        txvNews = (TextView) view.findViewById(R.id.txv_news);
        btnReturn = (Button) view.findViewById(R.id.btn_torna);

        //I valori passati dall'activity vengono utilizzati per mostrare all'utente i risultati
        txvName.setText(name);
        txvSurname.setText(surname);

        //Imposto i testi delle text view con operazioni ternarie...(domanda ? cond vera : cond falsa)
        txvDegree.setText("Iscritto al corso di lauera: " + (bachelor ? "Triennale" : "Magistrale"));
        txvNews.setText((newsletter ? "E'" :"Non") + " iscritto alla news letter");

        // Creazione di un listener per intercettare il click sul bottone da parte dell'utente...
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Se premuto ritorno alla pagina precedente
                if(listener != null) {
                    listener.onBackClick();
                }
            }
        });

        return view;
    }

    /**
     * Nel metodo "onCreate", dopo aver verificato che sia presente un oggetto di classe Bundle (ottentuo tramite il metodo "getArguments",
     * vengono recuperati i valori passati nel metodo statico. Vanno utilizzate le stesse chiavi usate in fase di inserimento dei dati
     * nel bundle.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null) {
            name = bundle.getString("name");
            surname = bundle.getString("surname");
            bachelor = bundle.getBoolean("bachelor");
            newsletter = bundle.getBoolean("newsletter");
        }
    }

    /**
     * Metodo del ciclo di vita del fragment che viene richiamato quando lo stesso viene "collegato" ad un'activity.
     * Il parametro context è appunto il riferimento all'activity appena citata. Tramite la parola chiave "instanceof" si verifica che
     * l'activity implementi l'interfaccia definita dal fragment; se risulta essere così, viene salvato il riferimento all'activity in modo
     * da poter richiamare i metodi dell'interfaccia che l'activity ha implementato.
     *
     * @param context Riferimento alla activity chiamante
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnShowInteraction) {
            listener = (OnShowInteraction) context;
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
