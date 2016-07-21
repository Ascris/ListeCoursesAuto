package garnier.antoine.listecoursesauto;

import java.util.ArrayList;

/**
 * Created by AsCris64 on 21/07/2016.
 */
public class Rayon {
    private String nom;
    private ArrayList<Aliment> alimentsAcceptes; //liste des aliments appartenant a ce rayon

    //Constructeurs
    public Rayon(){
        nom= "";
        alimentsAcceptes= new ArrayList<Aliment>();
    }
    public Rayon(String name) {
        System.out.println("Construction d'un rayon");
        nom= name;
        alimentsAcceptes= new ArrayList<Aliment>();
    }

    //Getter & setter
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }

    public ArrayList<Aliment> getAlimentsAcceptes(){
        return alimentsAcceptes;
    }

    //Ajoute un aliment aux aliments acceptes s'il n'y est pas deja
    public void ajoutAliment(Aliment a){
        if(alimentsAcceptes.contains(a)){
            System.out.println("Aliment deja dans le rayon");
        } else {
            System.out.println("Ajout de l'aliment au rayon");
            alimentsAcceptes.add(a);
        }
    }
    //Supprime un aliment de la liste des aliments acceptes
    public void suppressionAliment(Aliment a){
        if(alimentsAcceptes.contains(a)){
            System.out.println("Suppression de l'aliment");
            alimentsAcceptes.remove(a);
        } else {
            System.out.println("Suppression impossible - aliment non present dans le rayon");
        }
    }

}
