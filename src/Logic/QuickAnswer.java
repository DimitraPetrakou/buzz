package Logic;

/**
 * Αυτή η κλάση αναπαριστά την λειτουργία του γύρου 'Γρήγορη Απάντηση'
 * και κληρονομεί από το interface GameMode
 */
public class QuickAnswer implements GameMode{
    private boolean c1,c2;

    /**
     * Μέθοδος η οποία επιστρέφει το όνομα του γύρου 'Γρήγορη Απάντηση'
     * @return το όνομα του τρέχοντα γύρου
     */
    @Override
    public String getModeName() {
        return "Quick Answer Mode";
    }

    @Override
    public int getPoints(boolean x, double y) {
        return 0;
    }

    /**
     * Μέθοδος η οποία επιστρέφει τους πόντους του παίχτη1 στο ανταγωνιστικό παιχνίδι.
     * Αν απαντήσει σωστά και πρώτος κερδίζει 1000 πόντους.
     * Αν απαντήσει σωστά αλλά δεύτερος κερδίζει 500 πόντους.
     * Διαφορετικά παίρνει 0 πόντους.
     * @param isCorrect1 αν η απάντηση του παίχτη1 είναι σωστή ή όχι
     * @param y δεν χρησιμοποίειται στη συγκεκριμένη μέθοδο
     * @param isFirst αν ο παίχτης1 απάντησε πρώτος
     * @param k δεν χρησιμοποίειται στη συγκεκριμένη μέθοδο
     * @return τους πόντους που κέρδισε ή 0
     */
    @Override
    public int getPoints1(boolean isCorrect1, double y, boolean isFirst, boolean k) {
        c1=isCorrect1;
        if(isCorrect1){
            if(isFirst)
                return 1000;
            else if(!c2)
                return 1000;
            else
                return 500;

        }
        else
        return 0;

    }
    /**
     * Μέθοδος η οποία επιστρέφει τους πόντους του παίχτη2 στο ανταγωνιστικό παιχνίδι.
     * Αν απαντήσει σωστά και πρώτος παίρνει 1000 πόντους.
     * Αν απαντήσει σωστά αλλά δεύτερος παίρνει 500 πόντους.
     * Διαφορετικά παίρνει 0 πόντους.
     * @param isCorrect2 αν η απάντηση του παίχτη2 είναι σωστή ή όχι
     * @param y δεν χρησιμοποίειται στη συγκεκριμένη μέθοδο
     * @param isFirst αν ο παίχτης1 απάντησε πρώτος
     * @param k δεν χρησιμοποίειται στη συγκεκριμένη μέθοδο
     * @return τους πόντους που κέρδισε ή 0
     */
    @Override
    public int getPoints2(boolean isCorrect2, double y, boolean isFirst, boolean k) {
        c2=isCorrect2;
        if(isCorrect2){
            if(isFirst)
                return 1000;
            else if(!c1)
                return 1000;
            else
                return 500;
        }
        else
            return 0;
    }
}
