package nonmerci;

import javax.swing.*; //Pour les composants graphiques
import java.awt.*; //Pour la Jframe
import java.util.ArrayList;


public class Fenetre extends JFrame{
    //Attributs du menu
    public JMenuItem nouveauItem;
    public JMenuItem quitterItem;
    public JMenuItem apropos;
    public JMenuItem howtoplay;
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
    public JButton boutonhowtoplay;
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

    public Carte maCarteCourante;
    public boolean uneCarteCourante; //boolean utlise pour prendre la derniere carte alors que le paquet est vide
    public int jetonsMemoire;

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
        boutonLancer.setBounds(165, 280, 96, 29);//centrer les boutons
        boutonLancer.setBorderPainted(false);
        boutonAPropos.setBounds(165, 320, 96, 28);
        boutonAPropos.setBorderPainted(false);
        boutonhowtoplay.setBounds(150, 359, 125, 28);
        boutonhowtoplay.setBorderPainted(false);

        boutonQuitter.setBounds(165, 401, 95, 29);
        boutonQuitter.setBorderPainted(false);
        fondMenuPrincipal.add(boutonLancer);
        fondMenuPrincipal.add(boutonAPropos);
        fondMenuPrincipal.add(boutonhowtoplay);
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
        boutonLancer = new JButton(new ImageIcon("image/menu/jouer.jpg"));
        boutonQuitter = new JButton(new ImageIcon("image/menu/quitter.jpg"));
        boutonAPropos = new JButton(new ImageIcon("image/menu/apropos.jpg"));
        boutonhowtoplay = new JButton(new ImageIcon("image/menu/commentJouer.jpg"));
        boutonLancer.addActionListener(controlButton);
        boutonAPropos.addActionListener(controlButton);
        boutonhowtoplay.addActionListener(controlButton);
        boutonQuitter.addActionListener(controlButton);


        quitterItem = new JMenuItem("Quitter");
        apropos = new JMenuItem("À propos");
        howtoplay = new JMenuItem("Comment jouer ?");
        nouveauItem = new JMenuItem("Nouvelle partie");
        nouveauItem.addActionListener(controlMenu);
        apropos.addActionListener(controlMenu);
        quitterItem.addActionListener(controlMenu);
        howtoplay.addActionListener(controlMenu);

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
        plusMenu.add(howtoplay);

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

        JLabel jeton = new JLabel(new ImageIcon("image/carte/jeton.png"));

        JLabel nbJet = new JLabel(String.valueOf(maCarteCourante.getJeton()));
        Font font = new Font("Arial",Font.BOLD,25);
        nbJet.setFont(font);
        nbJet.setForeground(Color.red);

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
            if(maCarteCourante.getJeton()>0){
                jeton.setBounds(480, 320, 78, 75);
                imgManche.add(jeton);
                nbJet.setBounds(512, 334, 200, 50);
                imgManche.add(nbJet);
            }
        }
        //pioche
        if(m.sizePioche()>0) {
            piocheLabel.setBounds(625 + 51 + 5, 310, 51, 84);
            imgManche.add(piocheLabel);
        }
        //bouton accepte
        accepteCarte =new JButton(new ImageIcon("image/menu/prendreCarte.png"));
        accepteCarte.setBounds(470,400,150,30);
        accepteCarte.setBorderPainted(false);
        accepteCarte.addActionListener(controlButton);
        imgManche.add(accepteCarte);
        //bouton refuse
        refuseCarte =new JButton(new ImageIcon("image/menu/passer.png"));
        refuseCarte.setBounds(670,400,150,30);
        refuseCarte.setBorderPainted(false);
        refuseCarte.addActionListener(controlButton);
        if(partie.getJoueurs(0).getJeton()<=0) {refuseCarte.setEnabled(false);}
        imgManche.add(refuseCarte);
        //joueurs 2
        labelJoueurs.get(1).setBounds(15,0,100,31-5);
        imgManche.add(labelJoueurs.get(1));
        //joueurs 3
        labelJoueurs.get(2).setBounds(625+15,695,100,31-5);
        imgManche.add(labelJoueurs.get(2));
        //jeton


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

            if(partie.getNbJoueurs()==5){
                if (z == 0) {
                    x = 210;
                    y = 400;
                } else if (z == 1) {
                    x = 50;
                    y = 250;
                } else if (z == 2) {
                    x = 800;
                    y = 400;
                } else if (z == 3) {
                    x = 470;
                    y = 250;
                }else if( z==4){
                    x = 847 ;
                    y = 250;
                }
            }else {
                if (z == 0) {
                    x = 210;
                    y = 400;
                } else if (z == 1) {
                    x = 210;
                    y = 250;
                } else if (z == 2) {
                    x = 800;
                    y = 400;
                } else if (z == 3) {
                    x = 800;
                    y = 250;
                }
            }

            if(z==0||z==2) {
                if (jActuelle.nbCartes() < 7) y += 100;
                if (jActuelle.nbCartes() < 13) y += 100;
            }else{
                if (jActuelle.nbCartes() < 7) y -= 100;
                if (jActuelle.nbCartes() < 13) y -= 100;
            }
            int nbCarteligne=0;
            for (int i = 0; i < jActuelle.nbCartes(); i++) {
                boolean suiteEnCours=false;
                Carte afficheCarte = jActuelle.getCartes(i);
                carteJoueur = new JLabel(new ImageIcon("image/carte/" + afficheCarte.getValue() + ".png"));
                carteJoueur.setBounds(x, y, 51, 84);

                if (i < jActuelle.nbCartes()-1) {
                    if ((jActuelle.getCartes(i).getValue() + 1) == jActuelle.getCartes(i + 1).getValue()) {
                        x += 20;
                        suiteEnCours=true;
                    }
                    else x += 55;
                }else x += 55;

                imgManche.add(carteJoueur);
                if (nbCarteligne>=5&&!suiteEnCours) {
                    nbCarteligne = 0;
                    if (z == 0) {
                        y += 100;
                        x = 210;
                    } else if (z == 1 && partie.getNbJoueurs() == 5) {
                        y -= 100;
                        x = 50;
                    } else if (z == 1 && (partie.getNbJoueurs() == 4 || partie.getNbJoueurs() == 3)) {
                        y -= 100;
                        x = 210;
                    } else if (z == 2) {
                        y += 100;
                        x = 800;
                    } else if (z == 3 && partie.getNbJoueurs() == 5) {
                        y -= 100;
                        x = 470;
                    } else if (z == 3 && partie.getNbJoueurs() == 4) {
                        y -= 100;
                        x = 800;
                    } else if (z == 4) {
                        y -= 100;
                        x = 847;
                    }
                }else{
                    nbCarteligne++;
                }
            }

        }
        actualiser();

        if(!uneCarteCourante){
            String messageFin;
            partie.endGame();

            messageFin="Le gagnant est... "+partie.getJoueurs(0).getNom()+" avec "+partie.getJoueurs(0).nbPoints()+" points!";
            for(int i=1; i<partie.getNbJoueurs();i++){
                messageFin+="\n"+(i+1)+"-"+partie.getJoueurs(i).getNom()+" : "+partie.getJoueurs(i).nbPoints()+"points.";
            }
            messageFin+="\n\nRejouer?";

            String[] choix = {"Avec les mêmes paramètres", "Changer les paramètres", "Quitter"};
            int fin = JOptionPane.showOptionDialog(
                    null,
                    messageFin,
                    "Partie terminée!",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    choix,
                    choix[0]
            );

            if(fin==0){
                partie.reset(jetonsMemoire);
                nouvelleManche();
            }
            if(fin==1){
                boutonLancer.doClick();
            }
            if(fin==2){
                quitterItem.doClick();
            }
        }

    }

    public void IA() {



        ArrayList<Integer> ordreJeu = new ArrayList<Integer>();
        if (partie.getNbJoueurs() == 3) {
            ordreJeu.add(2);
            ordreJeu.add(1);
        } else if (partie.getNbJoueurs() == 4) {
            ordreJeu.add(2);
            ordreJeu.add(3);
            ordreJeu.add(1);
        } else if (partie.getNbJoueurs() == 5) {
            ordreJeu.add(2);
            ordreJeu.add(4);
            ordreJeu.add(3);
            ordreJeu.add(1);
        }

        boolean choix=false;

        for (int i = 1; i < partie.getNbJoueurs(); i++) {
            if (m.sizePioche() > 0 || uneCarteCourante) {
                ArrayList<Carte> lc = partie.getJoueurs(i).getCarte().getJeuJoueur();
                for(Carte c : lc){
                    if(c.getValue()-1 == maCarteCourante.getValue() || c.getValue()+1==maCarteCourante.getValue() ){
                        choix = true;//SI il y a une carte directement supérieur ou directement inférieur dans le jeu du joueur
                        break;
                    }else{
                        choix = false;
                    }
                }
                if(!choix && maCarteCourante.getValue()<9) choix=true;//Si une carte est les IA l'accepte
                if(lc.size()==0 && maCarteCourante.getValue()<20) choix = true;//Si il n'y a aucune carte dansle jeu, les IA accepte que les cartes <20
                if(!choix && maCarteCourante.getJeton()>=10) choix = true;//si grand nombre de jeton



                if (!choix) {//si l'ia refuse
                    choix = partie.getJoueurs(ordreJeu.get(i - 1)).refuse(maCarteCourante);
                } else {//si'lia accepte
                    partie.getJoueurs(ordreJeu.get(i - 1)).accepteCarte(maCarteCourante);
                    if (m.sizePioche() > 0) maCarteCourante = m.piocher();
                    else uneCarteCourante = false;
                }
                if (!choix ) {//on ne change pas la pioche si on passe
                    if (m.sizePioche() > 0) {
                        maCarteCourante = m.piocher();
                    } else {
                        uneCarteCourante = false;
                    }
                }
            /*    ActionListener actionTimer = new ActionListener() {
                    // Methode appelee a chaque tic du timer
                    public void actionPerformed(ActionEvent event) {*/
                affichageCartesJoueurs();
                       /* timer.stop();
                    }

                };*/

                //timer = new Timer(1000, actionTimer);
              //  timer.start();
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
        initialisation.setLocationRelativeTo(null);
        if(partie.getNbJoueurs()==3){
            initialisation.setSize(200,280);
        }else if(partie.getNbJoueurs()<=4){
            initialisation.setSize(200,310);
        }else if(partie.getNbJoueurs()<=5){
            initialisation.setSize(200,360);
        }

        //Création des champs par rapports au nombre de joueurs
        for(int i = 1; i<partie.getNbJoueurs()+1; i++) {

            champsJoueurs.add(new JTextField());
            labelJoueurs.add(new JLabel());
        }


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