package nonmerci;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by bicou on 12/11/14.
 */
public class JoueurUnitTest {



    // Test la décrémentation des jetons en cas de refus d'une carte
    @Test
    public void refuseTest(){
        Joueur j = new Joueur("totto", 4);
        Carte c = new Carte(4);
        Assert.assertEquals(4, j.getJeton());
        Assert.assertEquals(0, c.getJeton());
        j.refuse(c);
        j.refuse(c);
        Assert.assertEquals(2, j.getJeton());
        Assert.assertEquals(2, c.getJeton());
        j.refuse(c);
        Assert.assertEquals(1, j.getJeton());
        j.refuse(c);
        Assert.assertEquals(0, j.getJeton());
        Assert.assertEquals(4, c.getJeton());
        j.refuse(c);
        Assert.assertEquals(c.getJeton(), j.getJeton());
    }

    // Test l'acceptation de jetons en cas de non refus d'une carte
    @Test
    public void accepteCarteTest(){
        Joueur j1 = new Joueur("totto", 4);
        Carte c = new Carte(4);
        int nombreCartesJ = j1.nbCartes();
        j1.accepteCarte(c);
        Assert.assertEquals(4+c.getJeton(), j1.getJeton()); //joueur a 12 jetons
        Assert.assertEquals(j1.nbCartes(), nombreCartesJ+1);//nombre de cartes de joueur augmente de1
    }





    @Test
    public void nbpointTest(){
        Joueur j = new Joueur("totto", 11);
        Carte c1 = new Carte(1);
        Carte c2 = new Carte(2);
        Carte c3 = new Carte(7);
        Carte c4 = new Carte(4);
        Carte c5 = new Carte(5);
        j.accepteCarte(c1);
        j.accepteCarte(c2);
        j.accepteCarte(c3);
        j.accepteCarte(c4);
        j.accepteCarte(c5);
        Assert.assertEquals(-1, j.nbPoints());

    }

}
