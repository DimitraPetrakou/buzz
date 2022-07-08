package Logic;

/**
 * Interface από το οποίο κληρονομούν οι κλάσεις που αφορούν
 * τους τύπους γύρων του παιχνιδιού
 */
public interface GameMode {


    public String getModeName();

    public int getPoints(boolean isCorrect, double sec);
    public int getPoints1(boolean isCorrect, double sec,boolean isFirst, boolean first5);
    public int getPoints2(boolean isCorrect, double sec,boolean isFirst, boolean first5);




}
