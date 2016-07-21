package garnier.antoine.listecoursesauto;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;

public class ScrollingActivity extends AppCompatActivity {

    MyCustomAdapter dataAdapter= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Liste des aliments actuelle
        afficherListeAliments();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Snackbar.make(view, "Waiting for something new", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void afficherListeAliments(){
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

        ArrayList<Aliment> allAliments= m1.getAllAliments();

        //ArrayAdapter pour les aliments du magasin
        dataAdapter= new MyCustomAdapter(this,
                R.layout.ligne_aliment, allAliments);
        ListView liste_aliments= (ListView) findViewById(R.id.liste_aliments);

        liste_aliments.setAdapter(dataAdapter);

        liste_aliments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Affiche l'aliment quand on clique dessus
                Aliment al= (Aliment) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Nom aliment : "+al.getNom(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private class MyCustomAdapter extends ArrayAdapter<Aliment> {
        private ArrayList<Aliment> allAliments;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<Aliment> allAliments){
            super(context, textViewResourceId, allAliments);
            this.allAliments= new ArrayList<Aliment>();
            this.allAliments.addAll(allAliments);
        }

        private class ViewHolder {
            TextView nomAliment;
            CheckBox alimentCliquable;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            ViewHolder holder= null;
            Log.v("ConvertView", String.valueOf(position));

            if(convertView == null){
                LayoutInflater vi= (LayoutInflater) getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView= vi.inflate(R.layout.ligne_aliment, null);

                holder= new ViewHolder();
                holder.nomAliment= (TextView) convertView.findViewById(R.id.nomAliment);
                holder.alimentCliquable= (CheckBox) convertView.findViewById(R.id.alimentCliquable);
                convertView.setTag(holder);

                    holder.alimentCliquable.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CheckBox cb= (CheckBox) v;
                            Aliment al= (Aliment) cb.getTag();
                            Toast.makeText(getApplicationContext(),
                                    "Clic sur liste : " + cb.getText() + " is " + cb.isChecked(),
                                    Toast.LENGTH_LONG).show();
                            al.setSelected(cb.isChecked());
                        }
                    });
            } else {
                holder= (ViewHolder) convertView.getTag();
            }
            Aliment al= allAliments.get(position);
            holder.nomAliment.setText(" ("+al.getNom()+")");
            holder.alimentCliquable.setText(al.getNom());
            holder.alimentCliquable.setChecked(al.isSelected());
            holder.alimentCliquable.setTag(al);

            return convertView;
        }

    }

    private void checkButtonClick() {


        Button myButton = (Button) findViewById(R.id.findSelected);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                StringBuffer responseText = new StringBuffer();
                responseText.append("The following were selected...\n");

                ArrayList<Aliment> allAliments= dataAdapter.allAliments;
                for(int i= 0 ; i < allAliments.size(); ++i){
                    Aliment al= allAliments.get(i);
                    if(al.isSelected()){
                        responseText.append("\n" + al.getNom());
                    }
                }

                Toast.makeText(getApplicationContext(),
                        responseText, Toast.LENGTH_LONG).show();

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
