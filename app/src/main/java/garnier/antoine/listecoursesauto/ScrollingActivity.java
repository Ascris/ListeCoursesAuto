package garnier.antoine.listecoursesauto;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.*;
import android.widget.*;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class ScrollingActivity extends AppCompatActivity {

    MyCustomAdapter dataAdapter= null;
    HashMap<String, String> rayon_aliment;
    String FILENAME= "rayon-aliment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Liste des aliments actuelle
        //Premier magasin test
        final Magasin m1= new Magasin("Carrefour");
        Rayon r1= new Rayon("FruitsEtLegumes");
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

        final ArrayList<String> ordreRayons= new ArrayList<String>();
        ordreRayons.add("Boulangerie");
        ordreRayons.add("FruitsEtLegumes");
        afficherAlimentsOrdonnes(m1, ordreRayons);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TextInputEditText nouvel_aliment= (TextInputEditText) findViewById(R.id.nouvel_aliment);
                String aliment_a_ajouter= nouvel_aliment.getText().toString();

                Snackbar.make(view, aliment_a_ajouter, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                m1.ajoutAliment(aliment_a_ajouter);
                afficherAlimentsOrdonnes(m1, ordreRayons);
            }
        });
    }

    private void afficherListeAliments(Magasin m){

        ArrayList<Aliment> allAliments= m.getAllAliments();

        //ArrayAdapter pour les aliments du magasin
        dataAdapter= new MyCustomAdapter(this,
                R.layout.content_scrolling, allAliments);
        ListView liste_aliments= (ListView) findViewById(R.id.liste_aliments);

        liste_aliments.setAdapter(dataAdapter);

        liste_aliments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                //Affiche l'aliment quand on clique dessus
                Aliment al= (Aliment) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Nom aliment : "+al.getNom(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void afficherAlimentsOrdonnes(Magasin m, ArrayList<String> ordreRayons){
        final ArrayList<Integer> indicesRayons= m.getIndicesNomsRayons(ordreRayons);

        ArrayList<Aliment> allAliments= new ArrayList<Aliment>();
        ArrayList<Rayon> rayons= m.getRayons();
        for(Integer i : indicesRayons){
            Rayon r= rayons.get(i);
            allAliments.addAll(r.getAliments());
        }

        //ArrayAdapter pour les aliments du magasin
        dataAdapter= new MyCustomAdapter(this,
                R.layout.content_scrolling, allAliments);
        ListView liste_aliments= (ListView) findViewById(R.id.liste_aliments);

        liste_aliments.setAdapter(dataAdapter);

        liste_aliments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
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


//    private void checkButtonClick() {
//
//        Button myButton = (Button) findViewById(R.id.findSelected);
//        myButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                StringBuffer responseText = new StringBuffer();
//                responseText.append("The following were selected...\n");
//
//                ArrayList<Aliment> allAliments= dataAdapter.allAliments;
//                for(int i= 0 ; i < allAliments.size(); ++i){
//                    Aliment al= allAliments.get(i);
//                    if(al.isSelected()){
//                        responseText.append("\n" + al.getNom());
//                    }
//                }
//
//                Toast.makeText(getApplicationContext(),
//                        responseText, Toast.LENGTH_LONG).show();
//
//            }
//        });
//
//    }

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

        switch(id) {
            //noinspection SimplifiableIfStatement
            case R.id.action_settings:
                return true;

            case R.id.ajouter_aliment_au_rayon:

                // custom dialog
                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.layout_ajout_aliment_au_rayon);
                dialog.setTitle("Mettre en rayon");
                dialog.show();
                return true;

            case R.id.check_aliments:
                ArrayList<Aliment> allAliments= dataAdapter.allAliments; //recuperation de tous les aliments
                //recuperation des aliments non coches
                ArrayList<Integer> alimentsRestants= new ArrayList<Integer>();
                for(int i= 0 ; i < allAliments.size(); ++i){
                    Aliment al= allAliments.get(i);
                    if(!al.isSelected()){
                        alimentsRestants.add(i);
                    }
                }
                StringBuffer responseText = new StringBuffer();
                int nb_aliments_manquants= alimentsRestants.size();
                if(nb_aliments_manquants == 0){
                    responseText.append("Votre liste est complete, a la caisse !");
                } else if(nb_aliments_manquants == 1) {
                    responseText.append("Il vous manque cet aliment : " + allAliments.get(alimentsRestants.get(0)).getNom());
                } else {
                    responseText.append("Il vous manque ces " + alimentsRestants.size() + " aliments :\n");
                    for(int i= 0 ; i < alimentsRestants.size(); ++i){
                        Integer indice_aliment_restant= alimentsRestants.get(i);
                        Aliment al= allAliments.get(indice_aliment_restant);
                        responseText.append("\n" + al.getNom());
                    }
                }

                Toast.makeText(getApplicationContext(),
                        responseText, Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.layout_ajout_aliment_au_rayon, null));
        builder.setPositiveButton(R.id.ajouter_aliment_au_rayon, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int clicked_button){
                EditText e1= (EditText)findViewById(R.id.rayon);
                String nom_rayon= e1.getText().toString();
                EditText e2= (EditText)findViewById(R.id.nouvel_aliment_dans_rayon);
                String nouvel_aliment= e2.getText().toString();


            }

        });

        return builder.create();
    }

}
