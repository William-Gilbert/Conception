package nonmerci;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by bicou on 12/11/14.
 */
public class MancheUnitTest {
    @Test
    public void maPiocheSizeTest(){
        Manche m = new Manche();
        Assert.assertEquals(m.sizePioche(),24);
    }

    @Test
    public void piocherTest(){}
}
