package Logic;

/**
 * Αυτή η κλάση αναπαριστά  τη λειτουργία του γύρου 'Σταμάτησε το χρονόμετρο'
 * και κληρονομεί από το interface GameMode
 */
public class ClockMode implements GameMode {


    double points;

    /**
     * Μέθοδος η οποία επιστρέφει το όνομα του γύρου 'Σταμάτησε το χρονόμετρο'
     * @return το όνομα του τρέχοντα γύρου
     */
    @Override
    public String getModeName() {
        return "Clock Mode";
    }

    /**
     * Μέθοδος η οποία επιστρέφει τους πόντους του παίχτη στο ατομικό παιχνίδι.
     * Αν απαντήσει σωστά κερδίζει πόντους ίσους με τα δευτερόλεπτα που
     * του απέμειναν από τα αρχικά 5 επί το 0,2.
     * Διαφορετικά παίρνει 0 πόντους
     * @param isCorrect αν η απάντηση του παίχτη είναι σωστή ή όχι
     * @param sec τα δευτερόλεπτα που του απέμειναν
     * @return τους πόντους που κέρδισε ή 0
     */
    @Override
    public int getPoints(boolean isCorrect,double sec) {
        if(isCorrect) {
            points = sec*1000 * 0.2;
            return (int) (points);
        }
        else
            return 0;
    }

    /**
     * Μέθοδος η οποία επιστρέφει τους πόντους του παίχτη1 στο ανταγωνιστικό παιχνίδι,
     * Αν απαντήσει σωστά κερδίζει πόντους ίσους με τα δευτερόλεπτα που
     * του απέμειναν από τα αρχικά 5 επί το 0,2.
     * Διαφορετικά παίρνει 0 πόντους
     * @param isCorrect αν η απάντηση του παίχτη είναι σωστή ή όχι
     * @param sec τα δευτερόλεπτα που του απέμειναν
     * @param z δεν χρησιμοποίειται στη συγκεκριμένη μέθοδο
     * @param k δεν χρησιμοποίειται στη συγκεκριμένη μέθοδο
     * @return τους πόντους που κέρδισε ή 0
     */
    @Override
    public int getPoints1(boolean isCorrect, double sec, boolean z, boolean k) {
        if(isCorrect) {
            points = sec*1000 * 0.2;
            return (int) (points);
        }
        else
            return 0;
    }
    /**
     * Μέθοδος η οποία επιστρέφει τους πόντους του παίχτη2 στο ανταγωνιστικό παιχνίδι,
     * Αν απαντήσει σωστά κερδίζει πόντους ίσους με τα δευτερόλεπτα που
     * του απέμειναν από τα αρχικά 5 επί το 0,2.
     * Διαφορετικά παίρνει 0 πόντους
     * @param isCorrect αν η απάντηση του παίχτη είναι σωστή ή όχι
     * @param sec τα δευτερόλεπτα που του απέμειναν
     * @param z δεν χρησιμοποίειται στη συγκεκριμένη μέθοδο
     * @param k δεν χρησιμοποίειται στη συγκεκριμένη μέθοδο
     * @return τους πόντους που κέρδισε ή 0
     */
    @Override
    public int getPoints2(boolean isCorrect, double sec, boolean z, boolean k) {
        if(isCorrect) {
            points = sec*1000 * 0.2;
            return (int) (points);
        }
        else
            return 0;
    }


}
