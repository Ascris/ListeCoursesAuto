package garnier.antoine.listecoursesauto;

import java.util.ArrayList;

/**
 * Created by AsCris64 on 21/07/2016.
 */
public class Rayon {
    private String nom;
    private ArrayList<Aliment> aliments; //liste des aliments appartenant a ce rayon

    //Constructeurs
    public Rayon(){
        nom= "";
        aliments= new ArrayList<Aliment>();
    }
    public Rayon(String name) {
        System.out.println("Construction d'un rayon");
        nom= name;
        aliments= new ArrayList<Aliment>();
    }

    //Getter & setter
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }

    public ArrayList<Aliment> getAliments(){
        return aliments;
    }

    //Ajoute un aliment aux aliments acceptes s'il n'y est pas deja
    public void ajoutAliment(Aliment a){
        if(aliments.contains(a)){
            System.out.println("Aliment deja dans le rayon");
        } else {
            System.out.println("Ajout de l'aliment au rayon");
            aliments.add(a);
        }
    }
    //Supprime un aliment de la liste des aliments acceptes
    public void suppressionAliment(Aliment a){
        if(aliments.contains(a)){
            System.out.println("Suppression de l'aliment");
            aliments.remove(a);
        } else {
            System.out.println("Suppression impossible - aliment non present dans le rayon");
        }
    }

}
