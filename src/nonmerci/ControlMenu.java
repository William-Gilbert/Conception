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

        if(e.getSource()==fen.quitterItem){
            System.exit(0);
        }

        if(e.getSource()==fen.apropos) {
            JFrame apropos = new JFrame();
            JPanel panContenu = new JPanel();
            JLabel contenu = new JLabel(new ImageIcon("image/menu/aproposContenu.jpg"));

            apropos.setTitle("A propos");
            apropos.setVisible(true);
            apropos.setSize(200, 300);
            apropos.setLocationRelativeTo(null);
            panContenu.add(contenu);
            apropos.setContentPane(panContenu);
        }

        if(e.getSource()==fen.howtoplay){
            JFrame howtoplay = new JFrame();
            JPanel panContenu = new JPanel();
            JLabel contenu = new JLabel(new ImageIcon("image/menu/jouer.png"));
            howtoplay.setLayout(null);
            System.out.println("Test howtoplay reussi");
          /*  JLabel contenu = new JLabel("<html> Le but : terminer avec le moins de points possible,<br>" +
                    " les points correspondant à la valeur des cartes accumulées.<br><br>" +
                    "\n" +
                    "A votre tour de jeu, une carte du paquet est retournée.<br>" +
                    " A tour de rôle les joueurs ont le choix de prendre la carte ou de passer son tour en <br>" +
                    " \"payant\" un jeton (chacun débutant avec un stock de jetons). <br><br>" +
                    "Le tour de table lorsqu'un joueur décide de prendre la carte et il récupère ainsi tous les jetons éventuellement accumulés.<br><br>" +
                    " On retourne alors une nouvelle carte.\n" +
                    "\n" +
                    "Chaque carte acceptée fait perdre des points.<br>" +
                    " Mais si vous reconstituez des suites, vous ne perdez que la valeur de la plus petite carte de la suite. <br><br>" +
                    "Gérez au mieux vos jetons, car, de toute façon, il vous faudra accepter des cartes !</html>");*/
            howtoplay.setTitle("Comment jouer ?");
            howtoplay.setVisible(true);
            howtoplay.setSize(800,250);
            howtoplay.setLocationRelativeTo(null);
            panContenu.add(contenu);
            howtoplay.setContentPane(panContenu);

        }


    }


}
