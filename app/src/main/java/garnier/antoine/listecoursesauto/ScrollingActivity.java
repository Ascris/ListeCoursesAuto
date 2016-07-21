package garnier.antoine.listecoursesauto;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Premier magasin test
        Magasin m1= new Magasin("Carrefour");
            Rayon r1= new Rayon("Fruits et légumes");
                Aliment a1= new Aliment("Pomme");
                Aliment a2= new Aliment("Concombre");
            r1.ajoutAliment(a1);
            r1.ajoutAliment(a2);
            Rayon r2= new Rayon("Boulangerie");
                Aliment a3= new Aliment("Pain");
                Aliment a4= new Aliment("Croissant");
            r2.ajoutAliment(a3);
            r2.ajoutAliment(a4);
        m1.ajoutRayon(r1);
        m1.ajoutRayon(r2);

        String stock= new String();
        for(Aliment a : m1.getAllAliments()){
            stock= stock + a.getNom().toString();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final String finalStock = stock;
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Snackbar.make(view, finalStock, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
