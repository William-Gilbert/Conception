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
        if(e.getSource()==fen.boutonQuitter){
            System.exit(0);
        }
        if(e.getSource()==fen.boutonLancer) {
            String[] nbJoueursPossible = {"3", "4", "5"};
            String nbJoueurs;

            //Panel pour choisir le nombre de joueurs
            nbJoueurs = (String) JOptionPane.showInputDialog(
                null,"Choisissez le nombre de joueurs","Nouvelle partie",JOptionPane.QUESTION_MESSAGE,null,
                nbJoueursPossible,
                nbJoueursPossible[0]);

            //Cas ou on appuie sur "annuler" ou sur la croix pour fermer la fenêtre
            if(nbJoueurs==null){
                fen.recommencer();
            }else {
                partie.setNbJoueurs(Integer.parseInt(nbJoueurs));
                fen.widgetCreationJoueur();
            }
        }

        if(e.getSource()==fen.confirmerJoueurs){
            int nbJoueurs=0;
            String nom,jetonsInit;
            boolean passeralasuite = true;//faux si un label est vide
            Joueur j;

            for(int i=0 ; i< fen.champsJoueurs.size(); i++){
                nbJoueurs+=1;
                nom = fen.champsJoueurs.get(i).getText();

                if(nom.equals("")){
                    passeralasuite=false;
                    //Joueur non null pour Thomas
                    // remplace le texte pour avertir qu'il ne doit pas être vide
                    //Pour tester plus vite
                    fen.champsJoueurs.get(i).setText("bob");
                    fen.labelJoueurs.get(i).setText("Joueurs "+(i+1)+"(champ vide)");
                    fen.champsJoueurs.get(i).repaint();
                }
            }

            jetonsInit = fen.nbjet.getText();
            if(jetonsInit.equals("")){
                passeralasuite=false;
                fen.nbjet.setText("11");
                fen.nbjet.repaint();
            }

            if(passeralasuite) {
                for(JTextField champs:fen.champsJoueurs){
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
