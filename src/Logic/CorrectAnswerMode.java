package Logic;
/**
 * Αυτή η κλάση αναπαριστά  τη λειτουργία του γύρου 'Σωστή Απάντηση'
 * και κληρονομεί από το interface GameMode
 */
public class CorrectAnswerMode implements GameMode {
    private int points;

    /**
     * Κατασκευαστής/Constructor
     * Αρχικοποιεί τη μεταβλητή points με 1000
     */
    public CorrectAnswerMode()
    {
        points = 1000;
    }

    /**
     * Μέθοδος η οποία επιστρέφει το όνομα του γύρου 'Σωστή Απάντηση'
     * @return το όνομα του τρέχοντα γύρου
     */
    @Override
    public String getModeName() {
        return "Correct Answer Mode";
    }

    /**
     * Μέθοδος η οποία επιστρέφει τους πόντους του παίχτη στο ατομικό παιχνίδι.
     * Αν η απάντηση είναι σωστή κερδίζει 1000 πόντους.
     * Διαφορετικά παίρνει 0 πόντους.
     * @param isCorrect αν η απάντηση του παίχτη είναι σωστή ή όχι
     * @param y δεν χρησιμοποίειται στη συγκεκριμένη μέθοδο
     * @return τους πόντους που κέρδισε ή 0
     */
    @Override
    public int getPoints(boolean isCorrect, double y) {
        if(isCorrect) {
            return points;
        }
        else
            return 0;
    }

    /**
     * Μέθοδος η οποία επιστρέφει τους πόντους του παίχτη1 στο ανταγωνιστικό παιχνίδι.
     * Αν η απάντηση είναι σωστή κερδίζει 1000 πόντους.
     * Διαφορετικά παίρνει 0 πόντους.
     * @param isCorrect αν η απάντηση του παίχτη1 είναι σωστή ή όχι
     * @param y δεν χρησιμοποίειται στη συγκεκριμένη μέθοδο
     * @param z δεν χρησιμοποίειται στη συγκεκριμένη μέθοδο
     * @param k δεν χρησιμοποίειται στη συγκεκριμένη μέθοδο
     * @return τους πόντους που κέρδισε ή 0
     */
    @Override
    public int getPoints1(boolean isCorrect, double y, boolean z, boolean k) {
        if(isCorrect) {
            return points;
        }
        else
            return 0;
    }
    /**
     * Μέθοδος η οποία επιστρέφει τους πόντους του παίχτη2 στο ανταγωνιστικό παιχνίδι.
     * Αν η απάντηση είναι σωστή κερδίζει 1000 πόντους.
     * Διαφορετικά παίρνει 0 πόντους.
     * @param isCorrect αν η απάντηση του παίχτη2 είναι σωστή ή όχι
     * @param y δεν χρησιμοποίειται στη συγκεκριμένη μέθοδο
     * @param z δεν χρησιμοποίειται στη συγκεκριμένη μέθοδο
     * @param k δεν χρησιμοποίειται στη συγκεκριμένη μέθοδο
     * @return τους πόντους που κέρδισε ή 0
     */
    @Override
    public int getPoints2(boolean isCorrect, double y, boolean z, boolean k) {
        if(isCorrect) {
            return points;
        }
        else
            return 0;
    }
}
