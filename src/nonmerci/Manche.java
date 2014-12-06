package nonmerci;

import javax.print.attribute.AttributeSet;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by William on 03/12/2014.
 */
public class Manche {
    private JeuDeCarte maPioche;


    public Manche(){
        maPioche = new JeuDeCarte(35);
    }

    public int sizePioche(){
        return maPioche.getSize();
    }

    public Carte piocher(){
        Carte c = maPioche.getCartePourPioche();//supprimer la carte piocher de la pioche
        return c;
    }

}
