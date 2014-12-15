package nonmerci;

import java.util.ArrayList;
import java.util.Random;

import static java.util.Collections.sort;

/**
 * Created by William on 20/11/2014.
 */
public class Partie {
    private ArrayList<Joueur> joueurs;
    private int nbJoueurs;


    public Partie(){
        //Une partie est pour l'instant composé d'une liste de joueurs
        joueurs = new ArrayList<Joueur>();
    }

    public void videJoueur() {
        joueurs.clear();
    }

    public void setNbJoueurs(int nbJoueurs_) {
        nbJoueurs = nbJoueurs_;
    }

    public Joueur getJoueurs(int index) {
        return joueurs.get(index);
    }

    public ArrayList<Joueur> getAllJoueurs(){return joueurs;}

    public int getNbJoueurs() {
        return nbJoueurs;
    }

    public void addJoueur(Joueur j) {
        joueurs.add(j);
    }

    public ArrayList<Joueur> endGame(){
        ArrayList<Joueur> ordreFin = new ArrayList<Joueur>();
        ordreFin.addAll(joueurs);
        sort(ordreFin);
        return ordreFin;
    }

    public void reset(int jetonsMemoire){
        for(Joueur j:joueurs){
            j.reset(jetonsMemoire);
        }
    }
}
