package garnier.antoine.listecoursesauto;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by AsCris64 on 21/07/2016.
 */
public class Magasin {
    private String nom;
    private ArrayList<Rayon> rayons; //liste des rayons du magasin
    private HashMap<String, String> aliment_rayon;

    //Constructeurs
    public Magasin(){
        nom= "";
        rayons= new ArrayList<Rayon>();
        rayons.add(new Rayon("Inconnu")); //rayon present de base
        aliment_rayon= new HashMap<String, String>();
    }
    public Magasin(String name){
        nom= name;
        rayons= new ArrayList<Rayon>();
        rayons.add(new Rayon("Inconnu")); //rayon present de base
        aliment_rayon= new HashMap<String, String>();
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
    public ArrayList<String> getNomsRayons(){
        ArrayList<String> res= new ArrayList<String>();
        for(Rayon r : rayons){
            res.add(r.getNom());
        }
        return res;
    }

    /**
     * Operations sur les couples "rayon - aliment"
     *
     * */
    public HashMap<String, String> getAlimentRayon(){
        return aliment_rayon;
    }

    public void repertorierAlimentRayon(String nom_aliment, String nom_rayon){
        aliment_rayon.put(nom_aliment, nom_rayon);
    }

    public void repertorierAliment(String nom_aliment){
        repertorierAlimentRayon(nom_aliment, "Inconnu");
    }

    public boolean estRepertorie(String nom_aliment){
        return(aliment_rayon.containsKey(nom_aliment));
    }

    public String getNomRayonFromNomAliment(String nom_aliment){
        return aliment_rayon.get(nom_aliment);
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
            String nom_rayon= r.getNom();
            for(Aliment a : r.getAliments()){
                System.out.println("Aliment " + a.toString() + " ajouté au rayon " + nom_rayon);
                res.add(a);
            }
        }
        return res;
    }

    public Integer getIndiceRayon(String nom_rayon){
        for(int i= 0; i < rayons.size(); ++i){
            if(rayons.get(i).getNom() == nom_rayon) return i;
        }
        return 0;
    }

    public void ajoutAlimentARayon(String nom_rayon, Aliment a){
        int indice_rayon= getIndiceRayon(nom_rayon);
        rayons.get(indice_rayon).ajoutAliment(a);
        repertorierAlimentRayon(a.getNom(), nom_rayon);
    }

    public ArrayList<Aliment> getAllAlimentsRayon(String nom_rayon){
        int indice_rayon= getIndiceRayon(nom_rayon);
        ArrayList<Aliment> allAliments= rayons.get(indice_rayon).getAliments();
        ArrayList<Aliment> res= new ArrayList<Aliment>();
        for(int i= 0; i < allAliments.size(); ++i){
            Aliment a= allAliments.get(i);
            res.add(a);
        }
        return res;
    }

    /**
     * Retourne les indices des noms de rayon passes en parametre
     * @param noms_rayons : liste des noms des rayons du magasin
     * @return liste des indices des rayons (liste d'entiers)
     */
    public ArrayList<Integer> getIndicesNomsRayons(ArrayList<String> noms_rayons){
        ArrayList<Integer> res= new ArrayList<Integer>();
        for(String nom_rayon : noms_rayons){
            res.add(getIndiceRayon(nom_rayon));
        }
        return res;
    }

    public void ajoutAlimentAListe(String nom_aliment){
        //si l'aliment n'est pas repertorie, il le devient
        if(!estRepertorie(nom_aliment)){
            repertorierAliment(nom_aliment);
        }
        String nom_rayon= getNomRayonFromNomAliment(nom_aliment);
        Rayon rayon_courant= rayons.get(getIndiceRayon(nom_rayon));
        rayon_courant.ajoutAliment(new Aliment(nom_aliment));
    }

}
