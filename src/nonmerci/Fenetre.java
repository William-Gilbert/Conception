package nonmerci;

import javax.swing.*; //Pour les composants graphiques
import javax.swing.border.Border;
import java.awt.*; //Pour la Jframe
import java.util.ArrayList;

public class Fenetre extends JFrame{
    //Attributs du menu
    public JMenuItem nouveauItem;
    public JMenuItem quitterItem;
    public JMenuItem apropos;
    //Controlleurs
    public ControlMenu controlMenu;
    public ControlButton controlButton;
    //Boutons
    public JButton confirmerJoueurs;
    public JButton boutonLancer;
    public JButton boutonQuitter;
    //Données
    public Partie partie;
    public ArrayList<JTextField> champsJoueurs;
    public ArrayList<JLabel> labelJoueurs;
    public ArrayList<String> nomsInit;
    public JTextField nbjet;
    //Panels et JFrame
    public fondPanel imgManche;
    public JPanel global;
    public JFrame initialisation;
    //Timer
    JLabel labelTime;


    public Fenetre(){
        init();
        creerMenu();
        creerMenuPrincipal();

        setTitle("Non Merci!");
        setSize(212,300);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void creerMenuPrincipal() {
        fondPanel fondMenuPrincipal= new fondPanel(new ImageIcon("fond.jpg").getImage());
        global.setLayout(new BorderLayout());   //Efface le Layout

        fondMenuPrincipal.setLayout(new BoxLayout(fondMenuPrincipal, BoxLayout.Y_AXIS));//aligner verticalement
        boutonLancer.setAlignmentX(CENTER_ALIGNMENT);//centrer les boutons
        boutonQuitter.setAlignmentX(CENTER_ALIGNMENT);
        fondMenuPrincipal.add(Box.createRigidArea(new Dimension(0,20)));//ajouter de l'espace entre les boutons
        fondMenuPrincipal.add(boutonLancer);
        fondMenuPrincipal.add(Box.createRigidArea(new Dimension(0,5)));
        fondMenuPrincipal.add(boutonQuitter);

        global.add(fondMenuPrincipal);
        setContentPane(global);
        pack();
    }

    public void init() {
        global = new JPanel();
        partie = new Partie();
        nomsInit = new ArrayList<String>();

        controlMenu = new ControlMenu(this, partie);
        controlButton = new ControlButton(this,partie);

        confirmerJoueurs = new JButton("Confirmer");
        boutonLancer = new JButton("Démarrer le jeu");
        boutonQuitter = new JButton("Quitter");
        boutonLancer.addActionListener(controlButton);
        boutonQuitter.addActionListener(controlButton);



        quitterItem = new JMenuItem("Quitter");
        apropos = new JMenuItem("À propos");
        nouveauItem = new JMenuItem("Nouvelle partie");
        nouveauItem.addActionListener(controlMenu);
        apropos.addActionListener(controlMenu);
        quitterItem.addActionListener(controlMenu);




    }

    public void recommencer(){
        //Fonction identique au constructeurs permettant de recharger la fenêtre s'il
        //y a problème pendant l'initialisation
        init();
        creerMenu();
        creerMenuPrincipal();
        setTitle("Non Merci!");
        setSize(212,300);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void creerMenu(){
        JMenuBar barMenu;
        JMenu jeuMenu;
        JMenu plusMenu;
        barMenu = new JMenuBar();
        jeuMenu = new JMenu("Partie");
        plusMenu = new JMenu("?");

        jeuMenu.add(nouveauItem);
        jeuMenu.add(quitterItem);
        plusMenu.add(apropos);

        barMenu.add(jeuMenu);
        barMenu.add(plusMenu);
        setJMenuBar(barMenu);
    }



    public void nouvelleManche() {
        GridBagConstraints c;
        imgManche=new fondPanel(new ImageIcon("table.jpg").getImage());
        Manche m = new Manche();
        JLabel piocheLabel = new JLabel(new ImageIcon("pioche.jpg"));

        JLabel carte1 = new JLabel(new ImageIcon("3.png"));//Carte courante

        global = new JPanel();
        global.setLayout(new BorderLayout()); //Efface le pane actuel

        imgManche.setLayout(new GridBagLayout());


        //Pioche
        c = new GridBagConstraints();

        labelJoueurs = new ArrayList<JLabel>();
        for(int i =0 ; i <partie.getNbJoueurs() ; i++){
            labelJoueurs.add(new JLabel(partie.getJoueurs(i).getNom()));
        }//Définition des labels joueurs

        //Squelette de placement pour le jeu selon le nombre de joueurs
        switch(partie.getNbJoueurs()){
            case 4:


                //J haut gauche
                c.gridx=0;
                c.gridy=0;
                c.gridwidth=2;

                labelJoueurs.get(1).setForeground(Color.red);
                imgManche.add(labelJoueurs.get(1),c);
                //Jhaut droite
                c.gridx=2;
                c.gridy=0;
                c.gridwidth=2;

                labelJoueurs.get(2).setForeground(Color.red);
                imgManche.add(labelJoueurs.get(2),c);
                //Zone JhGauche
                c.gridx=0;
                c.gridy=1;
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridwidth=2;
                imgManche.add(new JButton("Zone jeu j1"),c);
                //Zone Jhdroite
                c.gridx=2;
                c.gridy=1;
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridwidth=2;
                imgManche.add(new JButton("Zone jeu j2"),c);

                //Carte Courante
                c.gridx=0;
                c.gridy=2;
                c.gridwidth=2;
                imgManche.add(carte1,c);
                //pioche
                c.gridx=2;
                c.gridy=2;
                c.gridwidth=2;
                imgManche.add(piocheLabel, c);

                //Zone Jeu humain bas gauche
                c.gridx=0;
                c.gridy=3;
                c.gridwidth=2;
                c.gridwidth = GridBagConstraints.HORIZONTAL;
                imgManche.add(new JButton("Zone jeu humain"),c);

                //Zone jeu joueurs 2 bas droite
                c.gridx=2;
                c.gridy=3;
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridwidth=2;
                imgManche.add(new JButton("Zone jeu j3"),c);

                //hud humain
                c.gridx=0;
                c.gridy=4;
                c.gridwidth=2;
                labelJoueurs.get(0).setForeground(Color.red);
                labelJoueurs.get(0).setText(labelJoueurs.get(0).getText()+" : "+partie.getJoueurs(0).getJeton());
                imgManche.add(labelJoueurs.get(0),c);
                //hud j2
                c.gridx=2;
                c.gridy=4;
                c.gridwidth=2;
                labelJoueurs.get(3).setForeground(Color.red);
                imgManche.add(labelJoueurs.get(3),c);
                break;
            case 5:

                //J haut gauche
                c.gridx=0;
                c.gridy=0;
                labelJoueurs.get(1).setForeground(Color.red);
                imgManche.add(labelJoueurs.get(1),c);
                //JhautCentre
                c.gridx=1;
                c.gridy=0;

                labelJoueurs.get(2).setForeground(Color.red);
                imgManche.add(labelJoueurs.get(2),c);
                //Jhaut droite
                c.gridx=2;
                c.gridy=0;

                labelJoueurs.get(3).setForeground(Color.red);
                imgManche.add(labelJoueurs.get(3),c);

                //Zone JhGauche
                c.gridx=0;
                c.gridy=1;
                c.fill = GridBagConstraints.HORIZONTAL;
                imgManche.add(new JButton("Zone jeu j1"),c);
                //Zone JhCentre
                c.gridx=1;
                c.gridy=1;
                c.fill = GridBagConstraints.HORIZONTAL;
                imgManche.add(new JButton("Zone jeu j2"),c);
                //Zone Jhdroite
                c.gridx=2;
                c.gridy=1;
                c.fill = GridBagConstraints.HORIZONTAL;
                imgManche.add(new JButton("Zone jeu j3"),c);

                //Carte Courante
                c.gridx=0;
                c.gridy=2;
                c.gridwidth=2;
                imgManche.add(carte1,c);
                //pioche
                c.gridx=2;
                c.gridy=2;
                c.gridwidth=2;
                imgManche.add(piocheLabel, c);

                //Zone Jeu humain bas gauche
                c.gridx=0;
                c.gridy=3;

                imgManche.add(new JButton("Zone jeu humain"),c);

                //Zone jeu joueurs 2 bas droite
                c.gridx=2;
                c.gridy=3;

                imgManche.add(new JButton("Zone jeu j4"),c);

                //hud humain
                c.gridx=0;
                c.gridy=4;
                c.gridwidth=2;
                labelJoueurs.get(0).setForeground(Color.red);
                labelJoueurs.get(0).setText(labelJoueurs.get(0).getText()+" : "+partie.getJoueurs(0).getJeton());
                imgManche.add(labelJoueurs.get(0),c);
                //hud j2
                c.gridx=2;
                c.gridy=4;
                c.gridwidth=2;
                labelJoueurs.get(4).setForeground(Color.red);
                imgManche.add(labelJoueurs.get(4),c);
                break;
            default:



                //J haut gauche
                c.gridx=0;
                c.gridy=0;
                c.gridwidth=4;
                c.anchor=GridBagConstraints.LINE_START;
                labelJoueurs.get(1).setForeground(Color.red);
                imgManche.add(labelJoueurs.get(1),c);
                //Zone JhGauche
                c.gridx=0;
                c.gridy=1;
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridwidth=4;
                imgManche.add(new JButton("Zone jeu j1"),c);

                //Carte Courante
                c.gridx=0;
                c.gridy=2;
                c.gridwidth=2;
                imgManche.add(carte1,c);
                //pioche
                c.gridx=2;
                c.gridy=2;
                c.gridwidth=2;
                imgManche.add(piocheLabel, c);

                //Zone Jeu humain bas gauche
                c.gridx=0;
                c.gridy=3;
                c.gridwidth=2;
                c.gridwidth = GridBagConstraints.HORIZONTAL;
                imgManche.add(new JButton("Zone jeu humain"),c);

                //Zone jeu joueurs 2 bas droite
                c.gridx=2;
                c.gridy=3;
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridwidth=2;
                imgManche.add(new JButton("Zone jeu j2"),c);

                //hud humain
                c.gridx=0;
                c.gridy=4;
                c.gridwidth=2;
                labelJoueurs.get(0).setForeground(Color.red);
                labelJoueurs.get(0).setText(labelJoueurs.get(0).getText()+" : "+partie.getJoueurs(0).getJeton());
                imgManche.add(labelJoueurs.get(0),c);
                //hud j2
                c.gridx=2;
                c.gridy=4;
                c.gridwidth=2;
                labelJoueurs.get(2).setForeground(Color.red);
                imgManche.add(labelJoueurs.get(2),c);
                break;
        }



        global.add(imgManche, BorderLayout.CENTER);


        setContentPane(global);

        setSize(1280,770);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void afficherImageTest(){
        //Fonction test pour afficher une manche
        JLabel carte1 = new JLabel(new ImageIcon("3.png"));
        imgManche.add(carte1);
    }


    public void widgetCreationJoueur() {
        JLabel jeton;
        JPanel pan = new JPanel();
        //list des textfields
        champsJoueurs = new ArrayList<JTextField>();
        labelJoueurs = new ArrayList<JLabel>();
        nbjet = new JTextField();


        initialisation = new JFrame();
        initialisation.setTitle("Initialisation");
        initialisation.setVisible(true);
        initialisation.setSize(200, 300);
        initialisation.setLocationRelativeTo(null);

        //Création des champs par rapports au nombre de joueurs
        for(int i = 1; i<partie.getNbJoueurs()+1; i++) {
            champsJoueurs.add(new JTextField());
            labelJoueurs.add(new JLabel());
        }
        System.out.println(champsJoueurs.size());

        //placement des widgets de la fenêtre
        for(int i = 0 ; i<champsJoueurs.size() ; i++){
            labelJoueurs.get(i).setText("Joueur"+(i+1));
            champsJoueurs.get(i).setColumns(15);
            pan.add(labelJoueurs.get(i));
            pan.add(champsJoueurs.get(i));
        }

        jeton = new JLabel("Nombre de jetons par joueur");
        nbjet.setColumns(15);

        pan.add(jeton);
        pan.add(nbjet);
        confirmerJoueurs = new JButton("Confirmer");
        confirmerJoueurs.addActionListener(controlButton);

        pan.add(confirmerJoueurs);

        initialisation.setContentPane(pan);
    }

}