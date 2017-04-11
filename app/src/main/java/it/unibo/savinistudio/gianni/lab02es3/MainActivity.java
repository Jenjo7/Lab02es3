package it.unibo.savinistudio.gianni.lab02es3;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements InputFragment.OnInputInteraction, ShowFragment.OnShowInteraction {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Procedura per popolare un layout con un'istanza di un Fragment, utilizzando le classi ausiliari FragmentManager e FragmentTransaction
         * e un oggetto della classe del fragment stesso.
         * L'oggetto di classe FragmentTransaction presenta vari metodi per l'interazione con i fragment; in particolare è stato utilizzato il metodo
         * "add" che prende in input l'id del layout in cui "creare" il fragment, e l'oggetto che si vuole istanziare.
         * Viene poi richiamato il metodo "commit" per rendere definitive le operazioni volte.
         *
         * (Nel file activity_main.xml è presente commentato il tag fragment per istanziare un fragment da xml).
         */
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        InputFragment fragment = InputFragment.newInstance();
        transaction.add(R.id.activity_main, fragment);
        transaction.commit();
    }

    /**
     * Metodo dell'interfaccia OnInputInteraction implementata dall'activity
     *
     * @param name
     * @param surname
     * @param bachelor
     * @param newsletter
     */
    @Override
    public void onButtonClick(String name, String surname, boolean bachelor, boolean newsletter) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        //Viene istanziato un oggetto della classe ShowFragment, a cui vengono passati i valori richiesti dal metodo statico per crearlo
        ShowFragment fragment = ShowFragment.newInstance(name, surname, bachelor, newsletter);
        /**
         * in questo caso della classe FragmentTransaction è stato utilizzato il metodo "replace", in quanto un fragment è già presente
         * nel layout.Questo metodo come il precedente prende in input l'id del layout in cui "creare" il fragment, e l'oggetto che si vuole istanziare.
         * Se si vuole interagire con la rimozione del fragment, è necessario aggiungere lo stesso allo stack tramite il metodo "addToBackStack".
         * Viene poi richiamato il metodo "commit" per rendere definitive le operazioni volte.
         */
        transaction.replace(R.id.activity_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    /**
     * Metodo dell'interfaccia OnShowInteraction implementata dall'activity
     */
    @Override
    public void onBackClick() {
        /**
         * Tramite il FragmentManager è possibile verificare quanti elementi sono presenti sullo stack dei fragment.
         * Se questo numero è maggiore di zero, viene rimosso l'elemento in cima allo stack.
         */
        FragmentManager manager = getSupportFragmentManager();
        if(manager.getBackStackEntryCount() > 0) {
            manager.popBackStack();
        }
    }
}
