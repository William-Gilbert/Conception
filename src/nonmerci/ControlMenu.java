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
    private ControlButton ctrlBut;
    public JLabel jeton;
    public JTextField nbjet;

    public ArrayList<String>noms;

    public ControlMenu(Fenetre f, Partie p) {
        fen=f;
        partie=p;
        ctrlBut = new ControlButton(f,p);

    }


    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==fen.nouveauItem) {
            Integer[] nbJoueursPossible = {3, 4, 5};
            int nbJoueurs;

            //Nombre de joueurs
            nbJoueurs = (Integer) JOptionPane.showInputDialog(
                    null,
                    "Choisissez le nombre de joueurs",
                    "Nouvelle partie",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    nbJoueursPossible,
                    nbJoueursPossible[0]);
            partie.setNbJoueurs(nbJoueurs);

            fen.widgetCreationJoueur();
        }



    }
}
