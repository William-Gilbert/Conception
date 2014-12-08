package nonmerci;

/**
 * Created by bicou on 12/11/14.
 */
public class Carte implements Comparable{

    private int jetons;
    private int point;

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

    @Override
    public int compareTo(Object o) {
        Carte c = (Carte)o;
        if(point<c.point)return -1;
        return 1;
    }
}
