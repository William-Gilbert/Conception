package nonmerci;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by bicou on 12/11/14.
 */
public class Joueur {

    private int jetons;
    private List<Carte> main;
    private String nom;

    public Joueur(String nom_, int jeton_){
        jetons = jeton_;
        main = new ArrayList<Carte>();
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
        main.add(c);
        jetons+=c.getJeton();
    }

    public int nbCartes(){
            return main.size();
    }

    public void reset(){  //utilisé pour le test de nbpoints
        main.clear();
        jetons = 0;
    }

    public int nbPoints() {
        List<Integer> pointTri = new ArrayList<Integer>();
        int points = jetons;
        int i = 0;
        for (Carte aMain : main) {
            pointTri.add(aMain.point);
        }

        Collections.sort(pointTri, Collections.reverseOrder());

        while(!pointTri.isEmpty()){
            if(!(pointTri.contains(pointTri.get(i)-1))){
                points=-pointTri.get(i)+points;
            }
            pointTri.remove(i);
        }
        return points;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
}
