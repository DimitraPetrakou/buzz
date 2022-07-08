package Logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Αυτή η κλάση αναπαριστά τις κατηγορίες των ερωτήσεων και αρχικοποιεί
 * κάθε ArrayList με τις κατάλληλες ερωτήσεις/επιλογές/απαντήσεις
 */

public class Categories {


    private ArrayList Questions = new ArrayList<String>();
    private ArrayList Options = new ArrayList<String>();
    private ArrayList Answers = new ArrayList<Integer>();

    /**
     * Κατασκευαστής
     */
    public Categories()
    {
    }

    /**
     * Μέθοδος η οποία επιστρέφει εναν τυχαίο ακέραιο αριθμό (0-19)
     * @return τυχαίος ακέραιος
     */
    public int random(){
        int rand;
        Random random = new Random();
        rand = random.nextInt(20);
        return rand;
    }

    /**
     * Μέθοδος η οποία επιστρέφει την ερώτηση που βρίσκεται στην εκάστοτε θέση του ArrayList που όρισε η random()
     * και έπειτα την αντικαθιστά με κενό ώστε να μην ξαναεμφανιστεί
     * @param rand η θέση που βρίσκεται η επιθυμητή ερώτηση
     * @return την ερώτηση στη θέση rand
     */

    public String getQuestion(int rand)
    {
        String question;
        question = Questions.get(rand).toString();
        Questions.set(rand,"") ;
         return question;
    }

    /**
     * Μέθοδος η οποία επιστρέφει τη σωστή απάντηση της εκάστοτε ερώτησης
     * @param rand η θέση που βρίσκεται η απάντηση στο ArrayList
     * @return τη σωστή απάντηση
     */

    public String getAnswer(int rand)
    {
        return Answers.get(rand).toString();
    }

    /**
     * Μέθοδος η οποία επιστρέφει τις πιθανές απαντήσεις της εκάστοτε ερώτησης
     * @param i μεταβλητή που ορίζει τη σειρά των επιλογών
     * της κάθε ερώτησης (1η επιλογή, 2η επιλογή κτλ)
     * @param rand η θέση των επιλογών στο ArrayList
     * @return την πιθανή απάντηση
     */
    public String getOption(int i,int rand)
    {
        return Options.get(rand*4+i).toString();
    }

    /**
     * Μέθοδος η οποία αρχικοποιέι κάθε ArrayList κάθε κατηγορίας παίρνωντας τα στοιχεία από διάφορα αρχεία
     * @param file το αρχείο με τις πληροφορίες για την κάθε κατηγορία
     */
    public void Init(File file){
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while( scan.hasNextLine() )
        {
            Questions.add(scan.nextLine());
            Options.add(scan.nextLine());
            Options.add(scan.nextLine());
            Options.add(scan.nextLine());
            Options.add(scan.nextLine());
            Answers.add(scan.nextLine());
        }
    }
}
