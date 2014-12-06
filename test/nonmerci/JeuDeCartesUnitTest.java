package nonmerci;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by William on 13/11/2014.
 */
public class JeuDeCartesUnitTest {
    private Carte cartePourPiocheSansRemove;

    @Test
    public void JeuDeCarteTest(){
        JeuDeCarte jdc= new JeuDeCarte(35);
        Assert.assertEquals(jdc.jeu.size(),24);
    }

    @Test
    public void getCartePourPiocheTest() {
        JeuDeCarte jdc = new JeuDeCarte(35);
        int taille = jdc.getSize();
        Carte c2 = jdc.getCartePourPiocheSansRemove();
        Carte c = jdc.getCartePourPioche();
        Assert.assertEquals(jdc.getSize(), taille - 1);
        Assert.assertEquals(c,c2);
    }


}
