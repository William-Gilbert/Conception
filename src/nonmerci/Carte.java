package nonmerci;

/**
 * Created by bicou on 12/11/14.
 */
public class Carte {

    private int jetons;
    public int point;

    public Carte(int point_){
        point = point_;
        jetons =0;
    }

    public void addJeton() {
        jetons++;
    }

    public int getJeton() {
        return jetons;
    }

    public int getValue() {
        return point;
    }
}
