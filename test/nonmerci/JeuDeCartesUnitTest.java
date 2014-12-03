package nonmerci;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by William on 13/11/2014.
 */
public class JeuDeCartesUnitTest {
    @Test
    public void JeuDeCarteTest(){
        JeuDeCarte jdc= new JeuDeCarte(35);
        Assert.assertEquals(jdc.jeu.size(),24);
    }

}
