package nonmerci;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by bicou on 03/12/14.
 */
public class SuiteUnitTest {
    @Test
    public void ajouterCarteTest(){
        Suite suite = new Suite();

        Carte c1 = new Carte(3);
        suite.ajouterCarte(c1);

        //Il doit y avoir une suite
        Assert.assertEquals(1, suite.suite.size());
        //Vérification de l'insertion de la carte
        Assert.assertEquals(3,suite.suite.get(0).get(0).getValue());

        Carte c2 = new Carte(4);
        Carte c3 = new Carte(6);
        suite.ajouterCarte(c2);
        suite.ajouterCarte(c3);

        //Il doit y avoir 2 suites (3-4 et 6)
        Assert.assertEquals(2, suite.suite.size());
        //Vérification de l'insertion des cartes
        Assert.assertEquals(4,suite.suite.get(0).get(1).getValue());
        Assert.assertEquals(6,suite.suite.get(1).get(0).getValue());

        Carte c4 = new Carte(5);
        suite.ajouterCarte(c4);
        //Vu que la carte insérée génère une nouvelle suite, il doit y en avoir une seule
        Assert.assertEquals(1, suite.suite.size());
        //La 3e carte doit être 5
        Assert.assertEquals(5,suite.suite.get(0).get(2).getValue());

        Suite suite2 = new Suite();

        suite2.ajouterCarte(c2);
        suite2.ajouterCarte(c1);
        //Pour vérifier que la suite se crée aussi dans l'autre sens
        Assert.assertEquals(1, suite2.suite.size());
    }

    @Test
    public void nbPointsTest(){
        Suite suite = new Suite();

        Carte c1 = new Carte(3);
        Carte c2 = new Carte(4);
        Carte c3 = new Carte(6);

        suite.ajouterCarte(c1);
        suite.ajouterCarte(c2);
        suite.ajouterCarte(c3);
        //Les suite doivent retourner 9 (3+6)
        Assert.assertEquals(-9,suite.nbPoints());

        //Puis 3 avec la nouvelle suite formée par le 5
        Carte c4 = new Carte(5);
        suite.ajouterCarte(c4);
        Assert.assertEquals(-3,suite.nbPoints());
    }

    @Test
    public void nbCartesTest(){
        Suite suite = new Suite();

        Carte c1 = new Carte(3);
        Carte c2 = new Carte(4);
        Carte c3 = new Carte(6);

        suite.ajouterCarte(c1);
        suite.ajouterCarte(c2);
        suite.ajouterCarte(c3);

        Assert.assertEquals(3,suite.nbCartes());

        Carte c4 = new Carte(5);
        suite.ajouterCarte(c4);
        Assert.assertEquals(4,suite.nbCartes());
    }
    @Test
    public void clearTest(){
        Suite suite = new Suite();

        Carte c1 = new Carte(3);
        Carte c2 = new Carte(4);
        Carte c3 = new Carte(6);

        suite.ajouterCarte(c1);
        suite.ajouterCarte(c2);
        suite.ajouterCarte(c3);

        suite.clear();
        Assert.assertEquals(0,suite.nbCartes());
    }
}
