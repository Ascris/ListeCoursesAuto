package garnier.antoine.listecoursesauto;

/**
 * Created by AsCris64 on 21/07/2016.
 */
public class Aliment {
    private String nom;
    private boolean selected= false;

    //Constructeur
    public Aliment(String name) {
        nom= name;
        System.out.println("Construction d'un aliment");
    }

    //Getter & setter
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isSelected(){
        return selected;
    }

    public void setSelected(boolean select){
        selected= select;
    }
}
