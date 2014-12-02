package nonmerci;

import javax.swing.*; //Pour les composants graphiques
import java.awt.*; //Pour la Jframe
import java.util.ArrayList;

public class Fenetre extends JFrame{
    public JMenuBar barMenu;
    public JMenu jeuMenu;
    public  JMenuItem nouveauItem;
    public ControlMenu controlMenu;
    public ControlButton controlButton;

    public JButton confirmerJoueurs;
    public Partie partie;
    public JPanel global;
    public JButton boutonLancer;
    private JPanel lancementPanel;
    private fondPanel imgPan;

    public ArrayList<JTextField> champsJoueurs;
    public ArrayList<JLabel> labelJoueurs;


    private fondPanel imgManche;

    public ArrayList<String> nomsInit;
    public JTextField nbjet;
    public JFrame initialisation;


    public Fenetre(){

        init();
        creerMenu();
        creerMenuPrincipal();
        setTitle("Non Merci!");
        setSize(400,400);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void creerMenuPrincipal() {
        imgPan=new fondPanel(new ImageIcon("fond.jpg").getImage());
        global.setLayout(new BorderLayout());
        imgPan.add(boutonLancer, BorderLayout.CENTER);
        global.add(imgPan);
        setContentPane(global);
        pack();
    }

    public void init() {
        partie = new Partie();
        lancementPanel = new JPanel();
        global = new JPanel();
        barMenu = new JMenuBar();
        jeuMenu = new JMenu("Partie");
        controlMenu = new ControlMenu(this, partie);
        controlButton = new ControlButton(this,partie);
        nomsInit = new ArrayList<String>();

        confirmerJoueurs = new JButton("Confirmer");
        boutonLancer = new JButton("Démarrer le jeu");
        boutonLancer.addActionListener(controlButton);

        //Listener & menu
        nouveauItem = new JMenuItem("Nouvelle partie");
        nouveauItem.addActionListener(controlMenu);




    }

    public void recommencer(){
        //Fonction identique au constructeurs permettant de recharger la fenêtre s'il
        //y a problème pendant l'initialisation
        init();
        creerMenu();
        creerMenuPrincipal();
        setTitle("Non Merci!");
        setSize(400,400);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void creerMenu(){
        jeuMenu.add(nouveauItem);
        barMenu.add(jeuMenu);
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