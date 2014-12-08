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
            if(jetonsInit.equals("") || Integer.parseInt(jetonsInit)>11){
                passeralasuite=false;
                fen.nbjet.setText("11");
                fen.nbjet.repaint();
            }

            if(passeralasuite) {
                partie.videJoueur();
                for(JTextField champs:fen.champsJoueurs){
                    j = new Joueur(champs.getText(), Integer.parseInt(jetonsInit));
                    partie.addJoueur(j);

                }
                partie.setNbJoueurs(nbJoueurs);
                fen.initialisation.dispose();


                fen.nouvelleManche();

            }
        }
        if(e.getSource()==fen.accepteCarte) {
            if(fen.m.sizePioche()>0 || fen.uneCarteCourante) {
                fen.partie.getJoueurs(0).accepteCarte(fen.maCarteCourante);

                if(fen.m.sizePioche()>0) {
                    fen.maCarteCourante = fen.m.piocher();
                }
                else{
                    fen.uneCarteCourante=false;

                }
                    fen.affichageCartesJoueurs();
                    fen.IA();

            }
        }

        if(e.getSource()==fen.refuseCarte) {
            if(fen.m.sizePioche()>0 || fen.uneCarteCourante) {
                fen.partie.getJoueurs(0).refuse(fen.maCarteCourante);
                fen.affichageCartesJoueurs();
                fen.IA();
            }
        }

        if(e.getSource()==fen.boutonAPropos){
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

        if(e.getSource()==fen.boutonhowtoplay){
            JFrame howtoplay = new JFrame();
            JPanel panContenu = new JPanel();
            JLabel contenu = new JLabel(new ImageIcon("image/menu/aproposmenu.png"));
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
