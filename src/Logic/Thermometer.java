package Logic;

/**
 * Αυτή η κλάση αναπαριστά την λειτουργία του γύρου 'Θερμόμετρο'
 * και κληρονομεί από το interface GameMode
 */
public class Thermometer implements GameMode{
    private boolean f1,f2;

    /**
     * Μέθοδος η οποία επιστρέφει το όνομα του γύρου 'Θερμόμετρο'
     * @return το όνομα του τρέχοντα γύρου
     */
    @Override
    public String getModeName() {
        return "Thermometer Mode";
    }

    @Override
    public int getPoints(boolean x, double y) {
        return 0;
    }

    /**
     * Μέθοδος η οποία επιστρέφει τους πόντους του παίχτη1 στο ανταγωνιστικό παιχνίδι.
     * Αν ο παίχτης1 απαντήσει πρώτος σωστά σε 5 ερωτήσεις κερδίζει 5000 πόντους.
     * Σε κάθε άλλη περίπτωση παίρνει 0 πόντους.
     * @param x δεν χρησιμοποίειται στη συγκεκριμένη μέθοδο
     * @param y δεν χρησιμοποίειται στη συγκεκριμένη μέθοδο
     * @param z δεν χρησιμοποίειται στη συγκεκριμένη μέθοδο
     * @param first5 αν απάντησε πρώτος 5 ερωτήσεις σωστά
     * @return τους πόντους που κέρδισε ή 0
     */
    @Override
    public int getPoints1(boolean x, double y, boolean z, boolean first5) {
        f1=first5;
        if(first5 && !f2)
            return 5000;
        else
            return 0;
    }
    /**
     * Μέθοδος η οποία επιστρέφει τους πόντους του παίχτη2 στο ανταγωνιστικό παιχνίδι.
     * Αν ο παίχτης2 απαντήσει πρώτος σωστά σε 5 ερωτήσεις κερδίζει 5000 πόντους.
     * Σε κάθε άλλη περίπτωση παίρνει 0 πόντους.
     * @param x δεν χρησιμοποίειται στη συγκεκριμένη μέθοδο
     * @param y δεν χρησιμοποίειται στη συγκεκριμένη μέθοδο
     * @param z δεν χρησιμοποίειται στη συγκεκριμένη μέθοδο
     * @param first5 αν απάντησε πρώτος 5 ερωτήσεις σωστά
     * @return τους πόντους που κέρδισε ή 0
     */
    @Override
    public int getPoints2(boolean x, double y, boolean z, boolean first5) {
        f2=first5;
        if(first5 && !f1)
            return 5000;
        else
            return 0;
    }
}
