package nonmerci;

import java.util.*;

public class JeuDeCarte {
    public Map<Integer, Carte> jeu;
    public ArrayDeque<Carte> deck;


    public JeuDeCarte(int taille) {

        //Création des valeurs des cartes
        List<Integer> liste = new ArrayList<Integer>();
        for(int i = 3 ; i <taille ; i++){
            liste.add(i);
        }
        Random loto = new Random();
        while(liste.size()!=24){
            liste.remove(loto.nextInt(liste.size()-1));//choisi 24 cartes parmis les 35, de façon aléatoire
        }
        Collections.shuffle(liste);

        jeu = new HashMap<Integer, Carte>();
        deck = new ArrayDeque<Carte>();
        for(Integer i : liste){
            Carte carte = new Carte(i);
            jeu.put(i, carte);
            deck.add(carte);
        }
    }

    public int getSize() {
        return jeu.size();
    }

    public Carte getCartePourPioche() {
        Carte cartePioche = deck.poll();
        jeu.remove(cartePioche.getValue());
        return cartePioche;
    }

    public Carte getCartePourPiocheSansRemove() {
        return deck.getFirst();
    }
}
