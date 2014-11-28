package nonmerci;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by William on 20/11/2014.
 */
public class PartieUnitTest {
    @Test
    public void testsetNbJoueur(){
        Partie p= new Partie();
        p.setNbJoueurs(3);
        Assert.assertEquals(p.getNbJoueurs(), 3);

    }

    @Test
    public void testAddJoueur(){
        Joueur jAjouter;
        Partie p = new Partie();
        Joueur j = new Joueur("Alphonse",11);
        p.addJoueur(j);
        jAjouter=p.getJoueurs(0);
        Assert.assertEquals(jAjouter,j);
    }
}
