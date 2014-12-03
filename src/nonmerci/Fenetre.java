package nonmerci;

import javax.swing.*; //Pour les composants graphiques
import javax.swing.border.Border;
import java.awt.*; //Pour la Jframe
import java.util.ArrayList;

public class Fenetre extends JFrame{
    //COUCOUSYLVAIN TEST COMMENTAIRE
    //COUCOUSYLVAIN TEST COMMENTAIRE
    //COUCOUSYLVAIN TEST COMMENTAIRE
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
        //Creer un panel avec une image de fond avec une table
        imgManche=new fondPanel(new ImageIcon("table.png").getImage());

        init();
        creerMenu();
        global.setLayout(new BorderLayout()); //Efface le pane actuel

        afficherImageTest();

        global.add(imgManche);
        setContentPane(global);

        setTitle("Non Merci!");
        setSize(1280,720);
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