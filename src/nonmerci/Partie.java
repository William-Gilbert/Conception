package nonmerci;

import java.util.ArrayList;

/**
 * Created by William on 20/11/2014.
 */
public class Partie {
    private int nbJoueur;
    private ArrayList<Joueur> joueurs;
    private int nbJoueurs;

    public Partie(){
        //Une partie est pour l'instant composé d'une liste de joueurs
        joueurs = new ArrayList<Joueur>();
    }


    public void setNbJoueurs(int nbJoueurs_) {
        nbJoueurs = nbJoueurs_;
    }

    public Joueur getJoueurs(int index) {
        return joueurs.get(index);
    }

    public int getNbJoueurs() {
        return nbJoueurs;
    }

    public void addJoueur(Joueur j) {
        joueurs.add(j);
    }
}
