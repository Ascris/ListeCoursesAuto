package garnier.antoine.listecoursesauto;

import java.util.ArrayList;

/**
 * Created by AsCris64 on 21/07/2016.
 */
public class Magasin {
    private String nom;
    private ArrayList<Rayon> rayons; //liste des rayons du magasin

    //Constructeurs
    public Magasin(){
        nom= "";
        rayons= new ArrayList<Rayon>();
    }
    public Magasin(String name){
        nom= name;
        rayons= new ArrayList<Rayon>();
    }

    //Getter & setter
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Rayon> getRayons() {
        return rayons;
    }

    //Ajoute un rayon s'il n'existe pas deja
    public void ajoutRayon(Rayon r){
        if(rayons.contains(r)){
            System.out.println("Rayon deja existant");
        } else {
            System.out.println("Ajout du rayon");
            rayons.add(r);
        }
    }
    //Supprime un rayon
    public void suppressionRayon(Rayon r){
        if(rayons.contains(r)){
            System.out.println("Suppression du rayon");
            rayons.remove(r);
        } else {
            System.out.println("Suppression impossible - rayon non existant");
        }
    }

    //Retourne la liste de tous les aliments du magasin
    public ArrayList<Aliment> getAllAliments(){
        ArrayList<Aliment> res= new ArrayList<Aliment>();
        for(Rayon r : rayons){
            for(Aliment a : r.getAlimentsAcceptes()){
                res.add(a);
            }
        }
        return res;
    }
}
