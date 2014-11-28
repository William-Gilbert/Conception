package nonmerci;

import java.util.*;

public class JeuDeCarte {
    private List<Carte> jeu;


    public JeuDeCarte(int taille) {
        jeu = new ArrayList<Carte>();
        Carte c;
        for(int i=3 ; i<taille+3 ; i++){
            c = new Carte(i);
            jeu.add(c);
        }
    }


}
