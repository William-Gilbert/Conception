package nonmerci;

import java.util.*;

public class JeuDeCarte {
    public List<Carte> jeu;


    public JeuDeCarte(int taille) {
        jeu = new ArrayList<Carte>();
        Carte c;
        for(int i=3 ; i<taille ; i++){
            c = new Carte(i);
            jeu.add(c);
        }
        Random loto = new Random();
        while(jeu.size()!=24){
            jeu.remove(loto.nextInt(jeu.size()-1));//choisi 24 cartes parmis les 35, de façon aléatoire
        }
    }


}
