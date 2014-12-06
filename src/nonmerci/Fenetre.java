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
    public Manche m;
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

    public void affichageDebutManche(){
        //Label de placement
        JLabel piocheLabel = new JLabel(new ImageIcon("carte/pioche.png"));

        Carte maCarteCourante = m.piocher(); //retourne la carte piocher et la supprime de la pioche

        JLabel carteCourante = new JLabel(new ImageIcon("carte/"+maCarteCourante.getValue()+".png"));//Carte courante


        global = new JPanel();
        global.setLayout(null); //important pour placer manuellement
        imgManche.setLayout(null);

        //Réutilisation de notre liste de JLabel de l'initialisation pour pouvoir afficher le nom des joueurs
        labelJoueurs = new ArrayList<JLabel>();
        for(int i =0 ; i <partie.getNbJoueurs() ; i++){
            JLabel swap;
            if(i==0){
                //Différenciation du joueur humain
                swap =new JLabel(partie.getJoueurs(i).getNom()+" : "+partie.getJoueurs(i).getJeton());
                swap.setForeground(Color.red);
            }else{
                swap =new JLabel(partie.getJoueurs(i).getNom());
                swap.setForeground(Color.green);
            }
            labelJoueurs.add(swap);
        }

        //Placement des éléments qui ne bouge pas selon le nombre de joueurs
        //Humain
        labelJoueurs.get(0).setBounds(15,695,100,31-5);
        imgManche.add(labelJoueurs.get(0));
        //courante
        carteCourante.setBounds(625-51-5,310,51,84);
        imgManche.add(carteCourante);
        //pioche
        piocheLabel.setBounds(625+51+5,310,51,84);
        imgManche.add(piocheLabel);
        //joueurs 2
        labelJoueurs.get(1).setBounds(15,0,100,31-5);
        imgManche.add(labelJoueurs.get(1));
        //joueurs 3
        labelJoueurs.get(2).setBounds(625+15,695,100,31-5);
        imgManche.add(labelJoueurs.get(2));



        //Squelette de placement pour le jeu selon le nombre de joueurs
        switch(partie.getNbJoueurs()){
            case 4:
                //joueurs 4
                labelJoueurs.get(3).setBounds(625+15,0,100,31-5);
                imgManche.add(labelJoueurs.get(3));
                break;
            case 5:
                //joueurs 4
                labelJoueurs.get(3).setBounds(416+15,0,100,31-5);
                imgManche.add(labelJoueurs.get(3));
                //joueurs 5
                labelJoueurs.get(4).setBounds(832+15,0,100,31-5);
                imgManche.add(labelJoueurs.get(4));
                break;

        }
        imgManche.setBounds(0,0,1280,770);
        global.add(imgManche);
        setContentPane(global);
        setSize(1280,770);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void nouvelleManche() {

        //Pour les placements manuel : largeur 1250, hauteur 695, basé vous la dessus ça marche
        //Chargement fond
        imgManche=new fondPanel(new ImageIcon("table.jpg").getImage());

        //Lancement d'une nouvelle manche
        m = new Manche();
        affichageDebutManche();


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
            if(i==0) labelJoueurs.get(i).setText("Votre Nom");
            else labelJoueurs.get(i).setText("Joueur"+(i+1));
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