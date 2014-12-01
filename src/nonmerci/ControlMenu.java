package nonmerci;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by bicou on 18/11/14.
 */
public class ControlMenu implements ActionListener {
    private Fenetre fen;
    private Partie partie;

    public ArrayList<String>noms;

    public ControlMenu(Fenetre f, Partie p) {
        fen=f;
        partie=p;
    }


    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==fen.nouveauItem) {
            //commenter dans controlButton
            String[] nbJoueursPossible = {"3", "4", "5"};
            String nbJoueurs;

            //Nombre de joueurs

            nbJoueurs = (String) JOptionPane.showInputDialog(
                null,
                "Choisissez le nombre de joueurs",
                "Nouvelle partie",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nbJoueursPossible,
                nbJoueursPossible[0]);


            if(nbJoueurs==null){
                fen.recommencer();
            }else {
                partie.setNbJoueurs(Integer.parseInt(nbJoueurs));
                fen.widgetCreationJoueur();
            }
        }



    }
}
