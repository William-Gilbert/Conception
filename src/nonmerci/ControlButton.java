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
            String[] nbJoueursPossible = {"3", "4", "5"};
            String nbJoueurs=null;

            //Nombre de joueurs

            while(nbJoueurs==null){
                nbJoueurs = (String) JOptionPane.showInputDialog(
                        null,
                        "Choisissez le nombre de joueurs",
                        "Nouvelle partie",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        nbJoueursPossible,
                        nbJoueursPossible[0]);

            }
            partie.setNbJoueurs(Integer.parseInt(nbJoueurs));
            fen.widgetCreationJoueur();
        }

        if(e.getSource()==fen.confirmerJoueurs){
            int nbJoueurs=0;
            String nom,jetonsInit;

            Joueur j;
            for(JTextField champs:fen.champsJoueurs){
                nbJoueurs+=1;
                nom = champs.getText();
                jetonsInit = fen.nbjet.getText();
                if(nom==null || jetonsInit==null){
                    //Joueur non null pour Thomas
                }
                else {
                    j = new Joueur(champs.getText(), Integer.parseInt(jetonsInit));
                    partie.addJoueur(j);
                    partie.setNbJoueurs(nbJoueurs);
                    fen.initialisation.dispose();
                    fen.nouvelleManche();
                }
            }

        }
    }


}
