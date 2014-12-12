package nonmerci;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.sort;

public class Suite {
    //Les suites sont une liste de liste de carte
    public List<ArrayList<Carte>> suite;

    public Suite() {
        suite = new ArrayList<ArrayList<Carte>>();
    }

    public void ajouterCarte(Carte c) {
        //Si la liste de suite est vide on ajoute la première suite qui contient la premiere carte
        if (suite.isEmpty()) {
            suite.add(new ArrayList<Carte>());
            suite.get(0).add(c);
            return;
        }
        //On vérifie que la carte à insérer est dans une suite ou nom(si oui pas besoin de créer une nouvelle suite)
        for (ArrayList<Carte> aSuite : suite) {
            if (aSuite.get(0).getValue() - 1 == c.getValue()  || aSuite.get(aSuite.size() - 1).getValue() + 1 == c.getValue() ) {
                aSuite.add(c);
                //Tri de la liste pour connaitre rapidement la première et la dernière valeur de celle-ci
                sort(aSuite);

                //Si une nouvelle suite est générée par la carte
                for (int i = 0; i < suite.size(); i++) {
                    ArrayList<Carte> aSuite2 = suite.get(i);
                    if ((aSuite.get(0).getValue() - 1 == aSuite2.get(aSuite2.size()-1).getValue() || aSuite.get(aSuite.size() - 1).getValue()+ 1 == aSuite2.get(0).getValue() ) && !aSuite2.equals(aSuite)) {
                        aSuite.addAll(aSuite2);
                        sort(aSuite);
                        suite.remove(i);
                    }
                }
                return;
            }
        }
        //Création d'une nouvelle suite si la carte ne vas pas dans les autres
        suite.add(new ArrayList<Carte>());
        suite.get(suite.size() - 1).add(c);
    }

    //C'est ici que l'on compte les points vu que les suites sont stockées ici
    public int nbPoints() {
        int res = 0;
        for (ArrayList<Carte> aSuite : suite) {
            res -= aSuite.get(0).getValue();
        }
        return res;
    }

    public int nbCartes() {
        int res = 0;
        if (suite.isEmpty()) return res;
        for (ArrayList<Carte> aSuite : suite) {
            res += aSuite.size();
        }
        return res;
    }

    public void clear() {
        //Réallocation de suite pour la vider
        suite = new ArrayList<ArrayList<Carte>>();
    }

    public Carte get(int index) {
        ArrayList<Carte> listeDesCartes = new ArrayList<Carte>();
        for (ArrayList<Carte> aSuite : suite) {
            for (Carte carte : aSuite) {
                listeDesCartes.add(carte);
            }
        }
        return listeDesCartes.get(index);

    }

    public ArrayList<Carte> getJeuJoueur(){
        ArrayList<Carte> lc = new ArrayList<Carte>();
        for(ArrayList<Carte> alc : suite){
            for(Carte c : alc){
                lc.add(c);
            }
        }
        return lc;
    }


}