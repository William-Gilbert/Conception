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
    public JButton boutonAPropos;
    public JButton boutonQuitter;
    public JButton accepteCarte;
    public JButton refuseCarte;
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

    public Carte maCarteCourante;
    public boolean uneCarteCourante; //boolean utlise pour prendre la derniere carte alors que le paquet est vide


    public Fenetre(){
        init();
        creerMenu();
        creerMenuPrincipal();
        setTitle("Non Merci!");
        setSize(1280,770);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void creerMenuPrincipal() {
        fondPanel fondMenuPrincipal= new fondPanel(new ImageIcon("image/menu/fond.jpg").getImage());
        global.setLayout(new BorderLayout());   //Efface le Layout
        fondMenuPrincipal.setLayout(null);//aligner verticalement
        boutonLancer.setBounds(165,280,107,31);//centrer les boutons
        boutonAPropos.setBounds(169,320,97,27);
        boutonQuitter.setBounds(169,390,97,31);
        fondMenuPrincipal.add(boutonLancer);
        fondMenuPrincipal.add(boutonAPropos);
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
        boutonLancer = new JButton(new ImageIcon("image/menu/jouer.png"));
        boutonQuitter = new JButton(new ImageIcon("image/menu/quitter.png"));
        boutonAPropos = new JButton(new ImageIcon("image/menu/apropos.png"));
        boutonLancer.addActionListener(controlButton);
        boutonAPropos.addActionListener(controlButton);
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
        setSize(1280,770);
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

    public void affichageStatiqueManche(){
        //Element graphique de la manche, indépendant du joueur
        //Pour les placements manuel : largeur 1250, hauteur 695, basé vous la dessus ça marche
        //Chargement fond
        imgManche=new fondPanel(new ImageIcon("table.jpg").getImage());
        //Label de placement
        JLabel piocheLabel = new JLabel(new ImageIcon("image/carte/pioche.png"));

        JLabel carteCourante = new JLabel(new ImageIcon("image/carte/"+maCarteCourante.getValue()+".png"));//Carte courante


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
        if(m.sizePioche()>0 || uneCarteCourante) {
            carteCourante.setBounds(625 - 51 - 5, 310, 51, 84);
            imgManche.add(carteCourante);
        }
        //pioche
        if(m.sizePioche()>0) {
            piocheLabel.setBounds(625 + 51 + 5, 310, 51, 84);
            imgManche.add(piocheLabel);
        }
        //bouton accepte
        accepteCarte =new JButton("Prendre carte");
        accepteCarte.setBounds(470,400,150,30);
        accepteCarte.addActionListener(controlButton);
        imgManche.add(accepteCarte);
        //bouton refuse
        refuseCarte =new JButton("Passer");
        refuseCarte.setBounds(670,400,150,30);
        imgManche.add(refuseCarte);
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
    }

    public void actualiser(){
        imgManche.setBounds(0,0,1280,770);
        global.add(imgManche);
        setContentPane(global);
        setVisible(true);
    }

    public void affichageCartesJoueurs(){

        affichageStatiqueManche();
        JLabel carteJoueur;

        for(int z=0;z<partie.getNbJoueurs();z++) {
            int x=0;
            int y=0;
            Joueur jActuelle = partie.getJoueurs(z);
            if (z == 0) {
                x = 30;
                y = 400;
            } else if (z == 1) {
                x = 30;
                y = 100;
            }
            else if (z == 2) {
                x = 600;
                y = 400;
            }
            if (jActuelle.nbCartes() < 9) y += 100;
            if (jActuelle.nbCartes() < 17) y += 100;
            for (int i = 0; i < jActuelle.nbCartes(); i++) {
                Carte afficheCarte = jActuelle.getCartes(i);
                carteJoueur = new JLabel(new ImageIcon("image/carte/" + afficheCarte.getValue() + ".png"));
                carteJoueur.setBounds(x, y, 51, 84);
                x += 55;
                imgManche.add(carteJoueur);
                if (i % 8 == 7) {
                    y += 100;
                    if (z == 0) {
                        x = 30;
                    } else if (z == 1) {
                        x = 30;
                    }
                    else if (z == 2) {
                        x = 600;
                    }
                }
            }

            if (jActuelle.getJeton() == 0) {
                accepteCarte.setEnabled(false);
            }
        }

        if(!uneCarteCourante){
            System.out.println("Partie terminé");
        }

        actualiser();

    }

    public void IA() {
        if(partie.getNbJoueurs()==3){
            //Joueur 2 joue

            if(m.sizePioche()>0 || uneCarteCourante) {
                partie.getJoueurs(2).accepteCarte(maCarteCourante);
                if (m.sizePioche() > 0) {
                    maCarteCourante =m.piocher();
                } else {
                    uneCarteCourante = false;
                }
                affichageCartesJoueurs();
            }
        }
    }


    public void nouvelleManche() {

        //Lancement d'une nouvelle manche
        m = new Manche();
        maCarteCourante = m.piocher(); //retourne la carte piocher et la supprime de la pioche
        uneCarteCourante=true;
        affichageStatiqueManche();
        imgManche.setBounds(0,0,1280,770);
        global.add(imgManche);
        setContentPane(global);
        setVisible(true);
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