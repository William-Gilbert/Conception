package nonmerci;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by bicou on 12/11/14.
 */
public class Joueur {

    private int jetons;
    public Suite main;
    private String nom;

    public Joueur(String nom_, int jeton_){
        jetons = jeton_;
        main = new Suite();
        nom = nom_;
    }

    public int getJeton() {//utilisé pour refuseTest
        return jetons;
    }

    public boolean refuse(Carte enCours) {
        if(jetons>=1){
            enCours.addJeton();
            jetons-=1;
            return true;
        }
        else{
            accepteCarte(enCours);
            return false;
        }
    }

    public void accepteCarte(Carte c) {
        main.ajouterCarte(c);
        jetons+=c.getJeton();
    }

    public int nbCartes(){
        return main.nbCartes();
    }

    public void reset(){  //utilisé pour le test de nbpoints
        main.clear();
        jetons = 0;
    }

    public int nbPoints() {
        return main.nbPoints();
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public Carte getCartes(int index) {
        return main.get(index);
    }
}
