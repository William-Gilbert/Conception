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


    private fondPanel imgManche;

    public ArrayList<String> nomsInit;
    public int nbJetonsInit;
    private JTextField nbjet;


    public Fenetre(){

        init();
        creerMenu();
        imgPan=new fondPanel(new ImageIcon("fond.jpg").getImage());
        creerWidget(imgPan);

        setTitle("Non Merci!");
        setSize(400,400);
        setResizable(false);



        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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


//--------------------------- NE FONCTIONNE PAS AVEC UN FOND
        //listener & boutons NE FONCTIONNE PAS AVEC UN FOND
        confirmerJoueurs = new JButton("Confirmer");
        //L'action listener de ce bouton ce trouve dans controlMenu

        boutonLancer = new JButton("Démarrer le jeu");
        boutonLancer.addActionListener(controlButton);
//--------------------------------------------------------------
        //Listener & menu
        nouveauItem = new JMenuItem("Nouvelle partie");
        nouveauItem.addActionListener(controlMenu);




    }

    public void creerMenu(){


        jeuMenu.add(nouveauItem);
        barMenu.add(jeuMenu);
        setJMenuBar(barMenu);
    }

    public void creerWidget(fondPanel imgfond){


        global.setLayout(new BorderLayout());
        imgPan.add(boutonLancer, BorderLayout.CENTER);
        global.add(imgfond);
        setContentPane(global);
        pack();
    }


    public void nouvelleManche() {
        //Creer un panel avec une image de fond avec une table
        imgManche=new fondPanel(new ImageIcon("table.jpg").getImage());
        init();
        creerMenu();
        creerWidget(imgManche);

        setTitle("Non Merci!");
        setSize(400,400);
        setResizable(false);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void widgetCreationJoueur() {
        JLabel jeton;
        JPanel pan = new JPanel();
        nbjet = new JTextField();
        champsJoueurs = new ArrayList<JTextField>();
        nbjet = new JTextField();
        JFrame initialisation = new JFrame();

        initialisation.setTitle("Initialisation");
        initialisation.setVisible(true);
        initialisation.setSize(200, 300);
        initialisation.setLocationRelativeTo(null);

        for(int i = 1; i<partie.getNbJoueurs()+1; i++) {
            champsJoueurs.add(new JTextField());
        }
        System.out.println(champsJoueurs.size());

        for(int i = 0 ; i<champsJoueurs.size() ; i++){
            JLabel labelJoueur = new JLabel("Joueur"+(i+1));
            champsJoueurs.get(i).setColumns(15);
            pan.add(labelJoueur);
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