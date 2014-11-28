package nonmerci;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by William on 20/11/2014.
 */
public class ControlButton implements ActionListener {
   private Fenetre fen;
    private Partie partie;


    public ControlButton(Fenetre f, Partie p) {
        fen=f;
        partie=p;


    }



    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==fen.boutonLancer) {
        //Création des joueurs
            Joueur j;
            Integer[] nbJoueursPossible =  {3, 4, 5};
            Integer[] nbJetonsPossible =  {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
            String nom;
            int nbJoueurs, nbJetons;

            //Nombre de joueurs
            nbJoueurs = (Integer)JOptionPane.showInputDialog(
                    null,
                    "Choisissez le nombre de joueurs",
                    "Nouvelle partie",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    nbJoueursPossible,
                    nbJoueursPossible[0]);
            partie.setNbJoueurs(nbJoueurs);

            //Nombres de jetons par joueurs
            nbJetons = (Integer) JOptionPane.showInputDialog(
                    null,
                    "Nombre de jetons par joueur ",
                    "Nouveau joueur",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    nbJetonsPossible,
                    nbJetonsPossible[10]);


            //création des n joueurs
            for(int i=0 ; i < partie.getNbJoueurs() ; i++) {
                nom = JOptionPane.showInputDialog(null, "Nom du joueur " + i + " :",
                        "Nouveau joueur", JOptionPane.QUESTION_MESSAGE);
                j = new Joueur(nom, nbJetons);
                partie.addJoueur(j);
            }


        //Création de la manche
            fen.nouvelleManche();
        }
    }


}
