package GUI;

import Logic.*;

import javax.swing.ImageIcon;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.GenericArrayType;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import java.lang.*;
import java.util.*;


/**
 * Αυτή η κλάση αναπαριστά την τα γραφικά του παιχνιδιού
 */
public class GUI implements ActionListener {

    private JFrame frame;
    private JTextArea questionText, finalScore;
    private JTextField nameField1, nameField2,nameField;
    private JTextField title;
    private JTextField betText;
    private JTextField scoreField, score1Field, score2Field, yourBet, modeText;
    private JTextField Q, W, E, R;
    private JTextField U, I, O, P;
    private JButton player1, player2, button1, button2, button3, button4, next, letsgo, playAgain;
    private JButton geoB, sportsB, historyB, celeB, brandsB;
    private JButton bet250, bet500, bet750, bet1000;
    private JLabel seconds_left, nameLabel, wellDoneLabel1, wellDoneLabel2 ,qLabel1,qLabel2,qLabel3,qLabel4;
    private JLabel therm1Label, therm2Label,introImgLabel ;
    private JLabel highscoreLabel;

    private Timer pause, pause2;

    private int countQ = 0, countR = 1, thermometer1=0, thermometer2=0;
    private int score = 0, score1 = 0, score2 = 0;
    private int bet = 0, bet1 = 0 ,bet2 = 0;
    private int i = 1;
    private int catNum;
    private int highscore = -10000, highscore2 = -10000;
    private double seconds = 5.0;
    private boolean are2players = false;
    private boolean firstReady = false;
    private boolean secondReady = false;
    private boolean keyPressed1 = false;
    private boolean keyPressed2 = false;
    private boolean enableKeyboard =  false;
    private boolean iAmFirst=false;

    /**
     * Το αντικείμενο timer μετράει αντίστροφα 5 δευτερόλεπτα με ακρίβεια 100 ms
     * Χρησιμοποιείται για την λειτουργία του Clock mode
     * @return το όνομα του τρέχοντα γύρου
     */
    Timer timer = new Timer(100, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            seconds-=0.1;
            String str = String.format("%.1f", seconds);
            seconds_left.setText(str);
            if(seconds <= 0.1){
                bothReady();
                timer.stop();
                displayAnswer(answer);
                seconds = 5.0;
                title.setText("Time is up !!!");
                seconds_left.setText(":(");
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);
            }
        }
    });

    Categories Geography, Sports, History, Celebrities, BrandsAndBusiness;
    String answer, name1, name2, name;
    Categories cat;
    GameMode mode;
    ArrayList<GameMode> modes = new ArrayList<>();
    String highscoreName, highscoreName2;
    JLabel imgLabel;
    ImageIcon image;
    File file, file2;
    String parts[], parts2[];
    /**
     * Κατασκευαστής/Constructor
     * Αρχικοποιούμε όλα τα στοιχεία που θα χρησιμοποιηθούν
     */
    public GUI() {

        /**
         * Δημιουργούμε τον φάκελο highscore αν δεν υπάρχει
         * Αν υπάρχει σημαίνει οτι έχει καταγραφεί προηγούμενο highscore,
         * επομένως ενημερώνουμε ανάλογα και την μεταβλητή highscore
         */
        file = new File("highscore.txt");
        try {
            if(file.exists()==false)
                file.createNewFile();

            Scanner scan = new Scanner(file);
            if(scan.hasNextLine()) {
                parts = scan.nextLine().split(":");
                highscoreName = parts[0];
                highscore = Integer.parseInt(parts[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        /**
         * Το ίδιο κανουμε για την περίπτωση που παίζουν 2 παίκτες
         * αποθηκεύουμε διαφορετικό highscore στην μεταβλητή highscore2
         */
        file2 = new File("highscore2.txt");
        try {
            if(file2.exists()==false)
                file2.createNewFile();

            Scanner scan2 = new Scanner(file2);
            if(scan2.hasNextLine()) {
                parts2 = scan2.nextLine().split(":");
                highscoreName2 = parts2[0];
                highscore2 = Integer.parseInt(parts2[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        imgLabel = new JLabel("",image,JLabel.CENTER);
        imgLabel.setBounds(300,150,400,250);
        imgLabel.setVisible(false);

        introImgLabel = new JLabel();
        introImgLabel.setBounds(100,100,900,700);
        introImgLabel.setVisible(false);
        image = new ImageIcon("Buzz_logo.jpg");
        introImgLabel.setIcon(image);

        InitCat();
        cat = new Categories() ;
        GameMode correctAnswerMode = new CorrectAnswerMode();
        BetMode betMode = new BetMode();
        GameMode clockMode = new ClockMode();
        GameMode quickAnswerMode = new QuickAnswer();
        GameMode thermometerMode = new Thermometer();
        modes.add(correctAnswerMode);
        modes.add(betMode);
        modes.add(clockMode);
        modes.add(quickAnswerMode);
        modes.add(thermometerMode);

        frame = new JFrame();
        frame.setTitle("Buzz Quiz");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        title = new JTextField("Welcome to BUZZ Quiz");
        title.setBounds(0, 0, 1000, 100);
        title.setBackground(Color.BLACK);
        title.setForeground(Color.RED);
        title.setFont(new Font("TimesRoman", Font.BOLD, 35));
        title.setEditable(false);
        title.setFocusable(false);
        title.setHorizontalAlignment(JTextField.CENTER);

        scoreField = new JTextField("Score: " + score);
        scoreField.setBounds(800,700,200,50);
        scoreField.setFont(new Font("MV Boli",Font.BOLD,20));
        scoreField.setBackground(Color.BLUE);
        scoreField.setForeground(Color.WHITE);
        scoreField.setEditable(false);
        scoreField.setVisible(false);
        scoreField.setFocusable(false);

        score1Field = new JTextField("Score1: "+score1);
        score1Field.setBounds(600,700,200,50);
        score1Field.setFont(new Font("MV Boli",Font.BOLD,20));
        score1Field.setBackground(Color.BLUE);
        score1Field.setForeground(Color.WHITE);
        score1Field.setEditable(false);
        score1Field.setVisible(false);
        score1Field.setFocusable(false);

        score2Field = new JTextField("Score2: "+score2);
        score2Field.setBounds(800,700,200,50);
        score2Field.setFont(new Font("MV Boli",Font.BOLD,20));
        score2Field.setBackground(Color.BLUE);
        score2Field.setForeground(Color.WHITE);
        score2Field.setEditable(false);
        score2Field.setVisible(false);
        score2Field.setFocusable(false);

        questionText = new JTextArea();
        questionText.setBounds(2, 100, 1000, 200);
        questionText.setBackground(Color.BLACK);
        questionText.setForeground(Color.WHITE);
        questionText.setFont(new Font("TimesRoman", Font.BOLD, 25));
        questionText.setEditable(false);
        questionText.setLineWrap(true);
        questionText.setWrapStyleWord(true);
        questionText.setVisible(false);
        questionText.setFocusable(false);

        modeText = new JTextField();
        modeText.setBounds(0,100, 1000,200);
        modeText.setFont(new Font("Ink Free", Font.BOLD, 50));
        modeText.setForeground(Color.WHITE);
        modeText.setBackground(Color.BLACK);
        modeText.setEditable(false);
        modeText.setHorizontalAlignment(JTextField.CENTER);
        modeText.setVisible(false);
        modeText.setFocusable(false);

        betText = new JTextField("Choose your bet: ");
        betText.setBounds(0,300,1000,100);
        betText.setFont(new Font("TimesRoman",Font.BOLD,30));
        betText.setHorizontalAlignment(JTextField.CENTER);
        betText.setForeground(Color.yellow);
        betText.setBackground(Color.darkGray);
        betText.setEditable(false);
        betText.setVisible(false);
        betText.setFocusable(false);

        /**
         * @param nameField το όνομα του χρήστη στην μέθοδο 1 player
         */
        nameField = new JTextField(20);
        nameField.setBounds(20,200,300,50);
        nameField.setFont(new Font("TimesRoman",Font.PLAIN,25));
        nameField.setVisible(false);
        nameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name= nameField.getText();
                ChooseCat();
                nameField.setVisible(false);
                nameLabel.setVisible(false);
            }
        });

        /**
         * @param nameField1 το όνομα του πρώτου χρήστη στην μέθοδο 2 players
         */
        nameField1 = new JTextField(20);
        nameField1.setBounds(20,200,300,50);
        nameField1.setFont(new Font("TimesRoman",Font.PLAIN,25));
        nameField1.setVisible(false);
        nameField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name1 = nameField1.getText();
                nameField1.setVisible(false);
                nameLabel.setText("Player 2");
                nameField2.setVisible(true);
            }
        });

        /**
         * @param nameField2 το όνομα του δεύτερου χρήστη στην μέθοδο 2 players
         */
        nameField2 = new JTextField(20);
        nameField2.setBounds(20,200,300,50);
        nameField2.setFont(new Font("TimesRoman",Font.PLAIN,25));
        nameField2.setVisible(false);
        nameField2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name2 = nameField2.getText();
                nameField2.setVisible(false);
                nameLabel.setVisible(false);
                score1Field.setVisible(true);
                score2Field.setVisible(true);
                ChooseCat();
            }
        });

        yourBet = new JTextField();
        yourBet.setBounds(0,700,500,50);
        yourBet.setFont(new Font("MV Boli",Font.BOLD,20));
        yourBet.setBackground(Color.RED);
        yourBet.setForeground(Color.black);
        yourBet.setEditable(false);
        yourBet.setFocusable(false);
        yourBet.setVisible(false);

        Q = new JTextField("Q");
        Q.setBounds(10,400,70,70);
        Q.setHorizontalAlignment(JTextField.CENTER);
        Q.setFont(new Font("MV Boli",Font.BOLD,20));
        Q.setEditable(false);
        Q.setForeground(Color.WHITE);
        Q.setBackground(Color.GRAY);
        Q.setVisible(false);
        Q.setFocusable(false);

        W = new JTextField("W");
        W.setBounds(10,470,70,70);
        W.setHorizontalAlignment(JTextField.CENTER);
        W.setFont(new Font("MV Boli",Font.BOLD,20));
        W.setEditable(false);
        W.setForeground(Color.WHITE);
        W.setBackground(Color.GRAY);
        W.setVisible(false);
        W.setFocusable(false);


        E = new JTextField("E");
        E.setBounds(10,540,70,70);
        E.setHorizontalAlignment(JTextField.CENTER);
        E.setFont(new Font("MV Boli",Font.BOLD,20));
        E.setEditable(false);
        E.setForeground(Color.WHITE);
        E.setBackground(Color.GRAY);
        E.setVisible(false);
        E.setFocusable(false);

        R = new JTextField("R");
        R.setBounds(10,610,70,70);
        R.setHorizontalAlignment(JTextField.CENTER);
        R.setFont(new Font("MV Boli",Font.BOLD,20));
        R.setEditable(false);
        R.setForeground(Color.WHITE);
        R.setBackground(Color.GRAY);
        R.setVisible(false);
        R.setFocusable(false);

        U = new JTextField("U");
        U.setBounds(900,400,70,70);
        U.setHorizontalAlignment(JTextField.CENTER);
        U.setFont(new Font("MV Boli",Font.BOLD,20));
        U.setEditable(false);
        U.setForeground(Color.WHITE);
        U.setBackground(Color.GRAY);
        U.setVisible(false);
        U.setFocusable(false);

        I = new JTextField("I");
        I.setBounds(900,470,70,70);
        I.setHorizontalAlignment(JTextField.CENTER);
        I.setFont(new Font("MV Boli",Font.BOLD,20));
        I.setEditable(false);
        I.setForeground(Color.WHITE);
        I.setBackground(Color.GRAY);
        I.setVisible(false);
        I.setFocusable(false);

        O = new JTextField("O");
        O.setBounds(900,540,70,70);
        O.setHorizontalAlignment(JTextField.CENTER);
        O.setFont(new Font("MV Boli",Font.BOLD,20));
        O.setEditable(false);
        O.setForeground(Color.WHITE);
        O.setBackground(Color.GRAY);
        O.setVisible(false);
        O.setFocusable(false);

        P = new JTextField("P");
        P.setBounds(900,610,70,70);
        P.setHorizontalAlignment(JTextField.CENTER);
        P.setFont(new Font("MV Boli",Font.BOLD,20));
        P.setEditable(false);
        P.setForeground(Color.WHITE);
        P.setBackground(Color.GRAY);
        P.setVisible(false);
        P.setFocusable(false);

        highscoreLabel = new JLabel("The HighScore is: "+highscore);
        highscoreLabel.setBounds(200,500,1000,100);
        highscoreLabel.setForeground(Color.RED);
        highscoreLabel.setFont(new Font("Ink Free",Font.BOLD,30));
        highscoreLabel.setVisible(false);
        highscoreLabel.setFocusable(false);

        therm1Label = new JLabel(thermometer1+"/5");
        therm1Label.setBounds(20,20,80,50);
        therm1Label.setForeground(Color.yellow);
        therm1Label.setFont(new Font("Ink Free",Font.BOLD,30));
        therm1Label.setVisible(false);
        therm1Label.setFocusable(false);

        therm2Label = new JLabel(thermometer2+"/5");
        therm2Label.setBounds(850,20,80,50);
        therm2Label.setForeground(Color.yellow);
        therm2Label.setFont(new Font("Ink Free",Font.BOLD,30));
        therm2Label.setVisible(false);
        therm2Label.setFocusable(false);

        qLabel1 = new JLabel();
        qLabel1.setBounds(0,400,1000,70);
        qLabel1.setFont(new Font("TimesRoman",Font.BOLD,35));
        qLabel1.setHorizontalAlignment(JLabel.CENTER);
        qLabel1.setForeground(Color.white);
        qLabel1.setVisible(false);
        qLabel1.setFocusable(false);

        qLabel2 = new JLabel();
        qLabel2.setBounds(0,470,1000,70);
        qLabel2.setFont(new Font("TimesRoman",Font.BOLD,35));
        qLabel2.setHorizontalAlignment(JLabel.CENTER);
        qLabel2.setForeground(Color.white);
        qLabel2.setVisible(false);
        qLabel2.setFocusable(false);

        qLabel3 = new JLabel();
        qLabel3.setBounds(0,540,1000,70);
        qLabel3.setFont(new Font("TimesRoman",Font.BOLD,35));
        qLabel3.setHorizontalAlignment(JLabel.CENTER);
        qLabel3.setForeground(Color.white);
        qLabel3.setVisible(false);
        qLabel3.setFocusable(false);

        qLabel4= new JLabel();
        qLabel4.setBounds(0,610,1000,70);
        qLabel4.setFont(new Font("TimesRoman",Font.BOLD,35));
        qLabel4.setHorizontalAlignment(JLabel.CENTER);
        qLabel4.setForeground(Color.white);
        qLabel4.setVisible(false);
        qLabel4.setFocusable(false);

        nameLabel = new JLabel("Player 1");
        nameLabel.setFont(new Font("MV Boli",Font.BOLD,30));
        nameLabel.setForeground(Color.white);
        nameLabel.setBounds(20,150,300,35);
        nameLabel.setVisible(false);
        nameLabel.setFocusable(false);

        seconds_left = new JLabel("");
        seconds_left.setBounds(450,700,100,50);
        seconds_left.setBackground(Color.RED);
        seconds_left.setForeground(Color.white);
        seconds_left.setFont(new Font("TimesRoman",Font.BOLD,30));
        seconds_left.setOpaque(true);
        seconds_left.setHorizontalAlignment(JTextField.CENTER);
        seconds_left.setVisible(false);
        seconds_left.setFocusable(false);

        wellDoneLabel1 = new JLabel("");
        wellDoneLabel1.setBounds(0,350,250,50);
        wellDoneLabel1.setForeground(Color.GREEN);
        wellDoneLabel1.setFont(new Font("Ink Free",Font.BOLD,25));
        wellDoneLabel1.setVisible(false);
        wellDoneLabel1.setFocusable(false);

        wellDoneLabel2 = new JLabel("");
        wellDoneLabel2.setBounds(780,350,250,50);
        wellDoneLabel2.setForeground(Color.GREEN);
        wellDoneLabel2.setFont(new Font("Ink Free",Font.BOLD,25));
        wellDoneLabel2.setVisible(false);
        wellDoneLabel2.setFocusable(false);

        finalScore = new JTextArea();
        finalScore.setBounds(0,200,650,200);
        finalScore.setWrapStyleWord(true);
        finalScore.setLineWrap(true);
        finalScore.setForeground(Color.GREEN);
        finalScore.setBackground(Color.BLACK);
        finalScore.setFont(new Font("MV Boli",Font.BOLD, 35));
        finalScore.setVisible(false);
        finalScore.setFocusable(false);

        /**
         * Τα κουμπιά με τα οποία επιλέγει ο χρήστης την σωστή απάντηση στην μέθοδο 1 player
         */
        button1 = new JButton("A");
        button1.setActionCommand("1");
        button1.setFont(new Font("MV Boli",Font.BOLD,20));
        button1.setBounds(0,400,70,70);
        button1.setVisible(false);
        button1.setFocusable(false);
        button1.addActionListener(this);

        button2 = new JButton("B");
        button2.setFont(new Font("MV Boli",Font.BOLD,20));
        button2.setActionCommand("2");
        button2.setFocusable(false);
        button2.setBounds(0,470,70,70);
        button2.setVisible(false);
        button2.addActionListener(this);

        button3 = new JButton("C");
        button3.setFont(new Font("MV Boli",Font.BOLD,20));
        button3.setFocusable(false);
        button3.setActionCommand("3");
        button3.setBounds(0,540,70,70);
        button3.setVisible(false);
        button3.addActionListener(this);

        button4 = new JButton("D");
        button4.setFont(new Font("MV Boli",Font.BOLD,20));
        button4.setActionCommand("4");
        button4.setBounds(0,610,70,70);
        button4.setVisible(false);
        button4.setFocusable(false);
        button4.addActionListener(this);

        /**
         * Το κουμπί με το οποίο ενεργοποιείται η μέθοδος 1 player
         */
        player1 = new JButton("1 PLAYER");
        player1.setFocusable(false);
        player1.setBounds(300,200,200,100);
        player1.setVisible(false);
        player1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                score = 0;
                nameLabel.setVisible(true);
                nameLabel.setText("What's your name?");
                nameField.setVisible(true);
                scoreField.setText("Score: " + score);
                player1.setVisible(false);
                player2.setVisible(false);
                scoreField.setVisible(true);
                qLabel1.setBounds(80,400,1000,70);
                qLabel2.setBounds(80,470,1000,70);
                qLabel3.setBounds(80,540,1000,70);
                qLabel4.setBounds(80,610,1000,70);
                qLabel1.setHorizontalAlignment(JTextField.LEFT);
                qLabel2.setHorizontalAlignment(JTextField.LEFT);
                qLabel3.setHorizontalAlignment(JTextField.LEFT);
                qLabel4.setHorizontalAlignment(JTextField.LEFT);
            }
        });

        /**
         * Το κουμπί με το οποίο ενεργοποιείται η μέθοδος 1 player
         */
        player2 = new JButton("2 PLAYERS");
        player2.setBounds(500,200,200,100);
        player2.setFocusable(false);
        player2.setVisible(false);
        player2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameLabel.setText("Player 1");
                score1 = 0;
                score1Field.setText("Score: "+score);
                score2 = 0;
                score2Field.setText("Score: "+score);
                player1.setVisible(false);
                player2.setVisible(false);
                nameLabel.setVisible(true);
                nameField1.setVisible(true);
                are2players = true;
                button1.setVisible(false);
                button2.setVisible(false);
                button3.setVisible(false);
                button4.setVisible(false);
                qLabel1.setHorizontalAlignment(JTextField.CENTER);
                qLabel2.setHorizontalAlignment(JTextField.CENTER);
                qLabel3.setHorizontalAlignment(JTextField.CENTER);
                qLabel4.setHorizontalAlignment(JTextField.CENTER);


            }
        });

        /**
         * Το κουμπί εκίνησης του παιχνιδιού
         */
        letsgo = new JButton("Let's Go");
        letsgo.setFocusable(false);
        letsgo.setBounds(400,400,200,100);
        letsgo.setVisible(false);
        letsgo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                letsgo.setVisible(false);
                modeText.setVisible(false);
                nextQuestion();
            }
        });

        /**
         * Το κουμπί εμφανίζεται όταν ολοκληρωθούν 5 ερωτήσεις και ελέγχει αν
         * θα συνεχίσει ο χρήστης σε επόμενο γύρο ή θα σταματήσει το παιχνίδι
         */
        next = new JButton("Next");
        next.setFocusable(false);
        next.setBounds(750,200,150,100);
        next.setFont(new Font("TimesRoman",Font.BOLD,30));
        next.setVisible(false);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                title.setVisible(true);
                therm1Label.setVisible(false);
                therm2Label.setVisible(false);
                thermometer1=0;
                thermometer2=0;
                therm1Label.setText(thermometer1+"/5");
                therm2Label.setText(thermometer2+"/5");
                imgLabel.setVisible(false);
                countR++;
                qLabel1.setForeground(Color.WHITE);
                qLabel2.setForeground(Color.WHITE);
                qLabel3.setForeground(Color.WHITE);
                qLabel4.setForeground(Color.WHITE);

                wellDoneLabel1.setVisible(false);
                wellDoneLabel2.setVisible(false);
                if(countR == 5)
                    endGame();
                else {
                    countQ = 0;
                    seconds_left.setVisible(false);
                    title.setForeground(Color.pink);
                    ChooseCat();
                    yourBet.setVisible(false);
                    next.setVisible(false);
                }
            }
        });

        /**
         * Το κουμπί με το οποίο ξεκινάει το παιχνίδι απο την αρχή
         */
        playAgain = new JButton("Play Again");
        playAgain.setFocusable(false);
        playAgain.setBounds(400, 400, 200, 100);
        playAgain.setForeground(Color.black);
        playAgain.setFont(new Font("MV Boli",Font.BOLD,30));
        playAgain.setVisible(false);
        playAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                highscoreLabel.setVisible(false);
                playAgain.setVisible(false);
                title.setText("Hello let's play Buzz");
                finalScore.setVisible(false);
                countQ = 0;
                countR = 1;
                score = 0;

                are2players = false;

                Intro();
            }
        });


        /**
         * Τα κουμπιά με τα οποία ο χρήστης επιλέγει την κατηγορία που θα ακολουθήσει
         * @param cat αποθηκεύει την κατηγορία που επέλεξε
         */
        geoB = new JButton("Geography");
        geoB.setFocusable(false);
        geoB.setBounds(200,150,600,80);
        geoB.setForeground(Color.blue);
        geoB.setFont(new Font("MV Boli", Font.BOLD, 45));
        geoB.setVisible(false);
        geoB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideCat();
                Mode();
                cat = Geography;
                catNum = 0;
            }
        });

        sportsB = new JButton("Sports");
        sportsB.setBounds(200,230,600,80);
        sportsB.setFont(new Font("MV Boli", Font.BOLD, 45));
        sportsB.setFocusable(false);
        sportsB.setForeground(Color.blue);
        sportsB.setVisible(false);
        sportsB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideCat();
                Mode();
                cat = Sports;
                catNum = 1;
            }
        });

        historyB = new JButton("History");
        historyB.setFocusable(false);
        historyB.setBounds(200,310,600,80);
        historyB.setFont(new Font("MV Boli", Font.BOLD, 45));
        historyB.setForeground(Color.blue);
        historyB.setVisible(false);
        historyB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideCat();
                Mode();
                cat = History;
                catNum = 2;
            }
        });

        celeB = new JButton("Celebrities");
        celeB.setBounds(200,390,600,80);
        celeB.setFont(new Font("MV Boli", Font.BOLD, 45));
        celeB.setForeground(Color.blue);
        celeB.setFocusable(false);
        celeB.setVisible(false);
        celeB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideCat();
                Mode();
                cat = Celebrities;
                catNum = 3;
            }
        });

        brandsB = new JButton("Brands and business");
        brandsB.setBounds(200,470,600,80);
        brandsB.setFont(new Font("MV Boli", Font.BOLD, 45));
        brandsB.setForeground(Color.blue);
        brandsB.setFocusable(false);
        brandsB.setVisible(false);
        brandsB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideCat();
                Mode();
                cat = BrandsAndBusiness;
                catNum = 4;
            }
        });

        /**
         * Τα κουμπιά με τα οποία ο χρήστης επιλέγει το ποντάρισμα του
         */
        bet250 = new JButton("250");
        bet250.setFocusable(false);
        bet250.setBounds(50,450,200,80);
        bet250.setFont(new Font("MV Boli",Font.BOLD,30));
        bet250.setVisible(false);
        bet250.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!are2players) {
                    bet = 250;
                    betMode.setBet(bet);
                    hideBet();
                    letsgo.setVisible(true);
                }else{
                    if(i%2 == 1) {
                        bet1 = 250;
                        betMode.setBet1(bet1);
                        betText.setText(name2 +" choose your bet: ");
                    }else if(i%2 == 0) {
                        bet2 = 250;
                        betMode.setBet2(bet2);
                        hideBet();
                        betText.setText(name1 +" choose your bet: ");
                        letsgo.setVisible(true);
                    }
                    i++;
                }
            }
        });
        bet500 = new JButton("500");
        bet500.setFocusable(false);
        bet500.setBounds(250,450,200,80);
        bet500.setFont(new Font("MV Boli",Font.BOLD,30));
        bet500.setVisible(false);
        bet500.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!are2players) {
                    bet = 500;
                    betMode.setBet(bet);
                    hideBet();
                    modeText.setText("Your bet is " + bet);
                    letsgo.setVisible(true);
                }else{
                    if(i%2 == 1) {
                        bet1 = 500;
                        betMode.setBet1(bet1);
                        betText.setText(name2 +" choose your bet: ");
                    }else if(i%2 == 0) {
                        bet2 = 500;
                        betMode.setBet2(bet2);
                        hideBet();
                        betText.setText(name1 +" choose your bet: ");
                        letsgo.setVisible(true);
                    }
                    i++;
                }
            }
        });
        bet750 = new JButton("750");
        bet750.setBounds(450,450,200,80);
        bet750.setFont(new Font("MV Boli",Font.BOLD,30));
        bet750.setFocusable(false);
        bet750.setVisible(false);
        bet750.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!are2players) {
                    bet = 750;
                    betMode.setBet(bet);
                    hideBet();
                    modeText.setText("Your bet is " + bet);
                    letsgo.setVisible(true);
                }else{
                    if(i%2 == 1) {
                        bet1 = 750;
                        betMode.setBet1(bet1);
                        betText.setText(name2 +" choose your bet: ");
                    }else if(i%2 == 0) {
                        bet2 = 750;
                        betMode.setBet2(bet2);
                        hideBet();
                        betText.setText(name1 +" choose your bet: ");
                        letsgo.setVisible(true);
                    }
                    i++;
                }
            }
        });
        bet1000 = new JButton("1000");
        bet1000.setBounds(650,450,200,80);
        bet1000.setFont(new Font("MV Boli",Font.BOLD,30));
        bet1000.setFocusable(false);
        bet1000.setVisible(false);
        bet1000.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!are2players) {
                    bet = 1000;
                    betMode.setBet(bet);
                    hideBet();
                    modeText.setText("Your bet is " + bet);
                    letsgo.setVisible(true);
                }else{
                    if(i%2 == 1) {
                        bet1 = 1000;
                        betMode.setBet1(bet1);
                        betText.setText(name2 +" choose your bet: ");
                    }else if(i%2 == 0) {
                        bet2 = 1000;
                        betMode.setBet2(bet2);
                        hideBet();
                        betText.setText(name1 +" choose your bet: ");
                        letsgo.setVisible(true);
                    }
                    i++;
                }
            }
        });

        /**
         * το pause ενεργοποιείται μόλις απαντάει ο χρήστης στην ερώτηση (για την μέθοδο 1 player)
         * και αντίστοιχα μόλις απαντήσουν και οι δύο χρήστες στην ερώτηση (για την μέθοδο 2 player)
         * Περιμένει 2 δευτερόλεπτα και πηγαίνει στην επόμενη ερώτηση
         */
        pause = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                title.setForeground(Color.pink);
                qLabel1.setForeground(Color.WHITE);
                qLabel2.setForeground(Color.WHITE);
                qLabel3.setForeground(Color.WHITE);
                qLabel4.setForeground(Color.WHITE);
                wellDoneLabel1.setVisible(false);
                wellDoneLabel2.setVisible(false);
                if(mode.getModeName().equals("Bet Mode")) {
                    hideQuest();
                    modeText.setText(mode.getModeName());
                    showBet();
                }
                else {
                    nextQuestion();
                }


            }
        });
        pause.setRepeats(false);


        frame.add(highscoreLabel);
        frame.add(therm1Label);
        frame.add(therm2Label);
        frame.add(imgLabel);
        frame.add(nameField);
        frame.add(next);
        frame.add(Q);
        frame.add(W);
        frame.add(E);
        frame.add(R);
        frame.add(U);
        frame.add(I);
        frame.add(O);
        frame.add(P);
        frame.add(qLabel1);
        frame.add(qLabel2);
        frame.add(qLabel3);
        frame.add(qLabel4);
        frame.add(playAgain);
        frame.add(finalScore);
        frame.add(wellDoneLabel2);
        frame.add(wellDoneLabel1);
        frame.add(seconds_left);
        frame.add(yourBet);
        frame.add(betText);
        frame.add(bet250);
        frame.add(bet500);
        frame.add(bet750);
        frame.add(bet1000);
        frame.add(letsgo);
        frame.add(modeText);
        frame.add(score2Field);
        frame.add(score1Field);
        frame.add(scoreField);
        frame.add(brandsB);
        frame.add(celeB);
        frame.add(historyB);
        frame.add(sportsB);
        frame.add(geoB);
        frame.add(questionText);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(nameLabel);
        frame.add(nameField2);
        frame.add(nameField1);
        frame.add(player2);
        frame.add(player1);
        frame.add(introImgLabel);
        frame.add(title);
        frame.setVisible(true);
        frame.setFocusable(true);
        /**
         * @param enableKeyboard γίνεται true όταν επιλεχτεί η μέθοδος 2 players
         * Eπιτρέπει στους χρήστες να απαντάνε στις ερωτήσεις με την βοήθεια του πληκτρολογίου
         * Q,W,E,R για τον player1 και U,I,O,P για τον player2
         */
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(enableKeyboard) {
                    super.keyTyped(e);

                    if ((e.getKeyChar() == 'q' || e.getKeyChar() == 'w' || e.getKeyChar() == 'e' || e.getKeyChar() == 'r')&&!keyPressed1) {
                        keyPressed1 = true;
                        firstReady = true;
                        if (secondReady)
                            iAmFirst=false;
                        else
                            iAmFirst=true;

                            switch (e.getKeyChar()) {
                                case 'q':
                                    if (answer.equals("1"))
                                        answerIsCorrect1();
                                    else
                                        answerIsWrong1();

                                    if (firstReady && secondReady)
                                        bothReady();

                                    break;
                                case 'w':
                                    if (answer.equals("2"))
                                        answerIsCorrect1();
                                    else
                                        answerIsWrong1();

                                    if (firstReady && secondReady)
                                        bothReady();

                                    break;
                                case 'e':
                                    if (answer.equals("3"))
                                        answerIsCorrect1();
                                    else
                                        answerIsWrong1();

                                    if (firstReady && secondReady)
                                        bothReady();

                                    break;
                                case 'r':
                                    if (answer.equals("4"))
                                        answerIsCorrect1();
                                    else
                                        answerIsWrong1();

                                    if (firstReady && secondReady)
                                        bothReady();
                                    break;
                            }
                    }
                    if ( (e.getKeyChar() == 'u' || e.getKeyChar() == 'i' || e.getKeyChar() == 'o' || e.getKeyChar() == 'p') && !keyPressed2){
                        keyPressed2 = true;
                        secondReady = true;
                        if (firstReady)
                            iAmFirst=false;
                        else
                            iAmFirst=true;
                        switch (e.getKeyChar()) {
                            case 'u':
                                if (answer.equals("1"))
                                    answerIsCorrect2();
                                 else
                                    answerIsWrong2();

                                if (firstReady && secondReady)
                                    bothReady();

                                break;
                            case 'i':
                                if (answer.equals("2"))
                                    answerIsCorrect2();
                                 else
                                   answerIsWrong2();

                                if (firstReady && secondReady)
                                    bothReady();

                                break;
                            case 'o':
                                if (answer.equals("3"))
                                    answerIsCorrect2();
                                 else
                                    answerIsWrong2();

                                if (firstReady && secondReady)
                                    bothReady();

                                break;
                            case 'p':
                                if (answer.equals("4"))
                                  answerIsCorrect2();
                                 else
                                   answerIsWrong2();

                                if (firstReady && secondReady)
                                    bothReady();

                                break;
                        }
                    }
                }
            }
        });


    }

    /**
     * Εκτελείται μόλις πατηθεί ένα απο τα κουμπιά button1, button2, button3, button4
     * Ελέγχει αν ήταν σωστή η απάντηση και ενημερώνει τον χρήστη ανάλογα
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.stop();
        displayAnswer(answer);

        String action = e.getActionCommand();

        if(action.equals(answer)) {
            title.setForeground(Color.GREEN);
            title.setText("Well Done !!!");
            score += mode.getPoints(true, seconds);
            scoreField.setText("Score: "+score);

        }
        else {
            title.setForeground(Color.RED);
            score += mode.getPoints(false, seconds);
            title.setText("Wrong Answer !!!");
            scoreField.setText("Score: "+score);
        }
        seconds = 5;

        if(countQ == 5)
            next.setVisible(true);
        else
            pause.start();
    }

    /**
     * Η εισαγωγή του παιχνιδιού από όπου επιλέγει ο χρήστης την μέθοδο 1 player ή 2players
     */
    public void Intro(){
        player1.setVisible(true);
        player2.setVisible(true);
        introImgLabel.setVisible(true);
    }

    /**
     * Δημιουργεί το περιβάλλον για την επιλογή κατηγορίας
     */
    public void ChooseCat(){
        title.setText("Choose Category");
        title.setForeground(Color.pink);
        hideQuest();
        showCat();
    }

    /**
     * Περνάει στην επόμενη ερώτηση
     */
    public void nextQuestion() {

        title.setVisible(true);
        imgLabel.setVisible(false);
        if (!are2players) {
            Mode(mode);
            countQ++;
            title.setText("Question " + countQ);
            showQuest();

        } else {
            enableKeyboard = true;
            Mode2(mode);
            keyPressed1 = false;
            keyPressed2 = false;
            if (!mode.getModeName().equals("Thermometer Mode"))
                countQ++;

            title.setText("Question " + countQ);
            showQuest();

        }

    }

    /**
     * Δημιουργεί το περιβάλλον για την τυχαία επιλογή του mode
     */
    public void Mode() {
        int randMode;
        hideQuest();
        Random random = new Random();
        if(are2players)
            randMode=random.nextInt(5);
        else
             randMode = random.nextInt(3);
        title.setText("Your game mode is.....");
        modeText.setVisible(true);
        mode = modes.get(randMode);
        modeText.setText(mode.getModeName());
        if(mode.getModeName().equals("Bet Mode")) {
            showBet();
        }else
            letsgo.setVisible(true);
    }

    /**
     * Προσθέτει τα έξτρα χαρακτηριστικά που χρειάζεται κάθε mode (μόνο για τα 3 πρώτα modes)
     * Καλείται μόνο στην μέθοδο 1 player
     */
    public void Mode(GameMode mode){
        if(mode.getModeName().equals("Correct Answer Mode")) {

        }else if(mode.getModeName().equals("Bet Mode")){
            yourBet.setText("Your bet is: "+bet);
            yourBet.setVisible(true);
        }else if(mode.getModeName().equals("Clock Mode")){

            seconds = 5.0;
            String str1 = String.format("%.1f", seconds);
            seconds_left.setText(String.valueOf(str1));
            seconds_left.setVisible(true);
            timer.start();
        }
    }

    /**
     * Προσθέτει τα έξτρα χαρακτηριστικά που χρειάζεται κάθε mode (και για τα 5 modes)
     * Καλείται μόνο στην μέθοδο 2 players
     */
    public void Mode2(GameMode mode){
        if(mode.getModeName().equals("Correct Answer Mode")) {

        }else if(mode.getModeName().equals("Bet Mode")){
            yourBet.setText(name1 + "'s bet: " + bet1 + " | " + name2 + "'s bet: " + bet2);
            yourBet.setVisible(true);
        }else if(mode.getModeName().equals("Clock Mode")){
            seconds = 5.0;
            String str1 = String.format("%.1f", seconds);
            seconds_left.setText(String.valueOf(str1));
            seconds_left.setVisible(true);
            timer.start();
        }else if(mode.getModeName().equals("Quick Answer Mode")){

        }else if(mode.getModeName().equals("Thermometer Mode")){
            therm1Label.setVisible(true);
            therm2Label.setVisible(true);
            title.setVisible(false);

        }
    }

    /**
     * Δημιουργεί το περιβάλλον του τερματισμού όπου εμφανίζει τα score, highscore και την επιλογή
     * να ξαναπαίξει ο χρήστης
     * Εκτελείται όταν ολοκληρωθούν 4 γύροι
     */
    public void endGame(){
        title.setForeground(Color.RED);
        title.setText("The End");
        title.setFont(new Font("TimesRoman",Font.BOLD,40));
        next.setVisible(false);
        scoreField.setVisible(false);
        score1Field.setVisible(false);
        score2Field.setVisible(false);
        seconds_left.setVisible(false);
        yourBet.setVisible(false);
        highscoreLabel.setVisible(true);
        hideQuest();
        if(!are2players) {
            highscoreLabel.setText("The Highscore is "+highscore+" from "+highscoreName);
            try{
                if(file.exists()==false)
                    file.createNewFile();


                if(score > highscore){
                    PrintWriter out = new PrintWriter(file);
                    out.println(name+":"+score+"\n");
                    //out.append(name+":"+score+"\n");
                    highscore = score;
                    highscoreName = name;
                    highscoreLabel.setText(name+" you set a new highscore: "+highscore);
                    out.close();
                }

            }catch(IOException e){
                System.out.println("COULD NOT LOG!!");
            }

            finalScore.setText(name + " your final score is: " + score);
        }
        else {
            highscoreLabel.setText("The Highscore is "+highscore2+" from "+highscoreName2);
            try{
                if(file2.exists()==false)
                    file2.createNewFile();

                if(score1 > highscore2){
                    PrintWriter out = new PrintWriter(file2);
                    out.println(name1+":"+score1+"\n");
                    highscore2 = score1;
                    highscoreName2 = name1;
                    highscoreLabel.setText(name1+" you set a new highscore: "+highscore2);
                    out.close();
                }

                if(score2 > highscore2){
                    PrintWriter out = new PrintWriter(file2);
                    out.println(name2+":"+score2+"\n");
                    highscore2 = score2;
                    highscoreName2 = name2;
                    highscoreLabel.setText(name2+" you set a new highscore: "+highscore2);
                    out.close();
                }


            }catch(IOException e){
                System.out.println("COULD NOT LOG!!");
            }

            finalScore.setText(name1 + "'s score is:" + score1 + "\n" + name2 + "'s score is:" + score2);
        }

        finalScore.setVisible(true);
        playAgain.setVisible(true);
    }

    /**
     * Αρχικοποιεί όλες τις κατηγορίες και γεμίζει τα αντίστοιχα ArrayLists με τις ερωτήσεις,
     * τις 4 επιλογές κάθε ερώτησης και την σωστή απάντηση
     */
    public void InitCat() {
        Geography = new Categories();
        File file = new File("cat1.txt");
        Geography.Init(file);

        Sports = new Categories();
        file = new File("cat2.txt");
        Sports.Init(file);

        History = new Categories();
        file = new File("cat3.txt");
        History.Init(file);

        Celebrities = new Categories();
        file = new File("cat4.txt");
        Celebrities.Init(file);

        BrandsAndBusiness = new Categories();
        file = new File("cat5.txt");
        BrandsAndBusiness.Init(file);
    }

    /**
     * Κρύβει τα κουμπιά για το ποντάρισμα
     */
    public void hideBet() {
        betText.setVisible(false);
        bet250.setVisible(false);
        bet500.setVisible(false);
        bet750.setVisible(false);
        bet1000.setVisible(false);
    }

    /**
     * Εμφανίζει τα κουμπιά για το ποντάρισμα
     */
    public void showBet() {
        if(are2players)
            betText.setText(name1 +" choose your bet: ");
        imgLabel.setVisible(false);
        betText.setVisible(true);
        bet250.setVisible(true);
        bet500.setVisible(true);
        bet750.setVisible(true);
        bet1000.setVisible(true);
        title.setVisible(false);
    }

    /**
     * Κρύβει τις ερωτήσεις και τα αντίστοιχα κουμπιά
     */
    public void hideQuest(){
        qLabel1.setVisible(false);
        qLabel2.setVisible(false);
        qLabel3.setVisible(false);
        qLabel4.setVisible(false);
        questionText.setVisible(false);
        if(are2players) {
            Q.setVisible(false);
            W.setVisible(false);
            E.setVisible(false);
            R.setVisible(false);
            U.setVisible(false);
            I.setVisible(false);
            O.setVisible(false);
            P.setVisible(false);
        }else{
            button1.setVisible(false);
            button2.setVisible(false);
            button3.setVisible(false);
            button4.setVisible(false);
        }

    }

    /**
     * Εμφανίζει τις ερωτήσεις και τα αντίστοιχα κουμπιά
     */
    public void showQuest(){
        int rand = cat.random();
        questionText.setVisible(true);
        qLabel1.setVisible(true);
        qLabel2.setVisible(true);
        qLabel3.setVisible(true);
        qLabel4.setVisible(true);
        if(are2players) {
            Q.setVisible(true);
            W.setVisible(true);
            E.setVisible(true);
            R.setVisible(true);
            U.setVisible(true);
            I.setVisible(true);
            O.setVisible(true);
            P.setVisible(true);
        } else{
            button1.setVisible(true);
            button2.setVisible(true);
            button3.setVisible(true);
            button4.setVisible(true);
            button1.setEnabled(true);
            button2.setEnabled(true);
            button3.setEnabled(true);
            button4.setEnabled(true);
        }
       String question = cat.getQuestion(rand);
        while(question.equals("")){
            rand= cat.random();
            question = cat.getQuestion(rand);
        }
        questionText.setText(question);
        qLabel1.setText(cat.getOption(0,rand));
        qLabel2.setText(cat.getOption(1,rand));
        qLabel3.setText(cat.getOption(2,rand));
        qLabel4.setText(cat.getOption(3,rand));
        answer = cat.getAnswer(rand);

        if (rand >= 0 && rand <= 4) {
            displayPhoto(rand + 5 * catNum);
        }
    }

    /**
     * Κρύβει τα κουμπιά για την επιλογή κατηγορίας
     */
    public void hideCat(){
        geoB.setVisible(false);
        sportsB.setVisible(false);
        historyB.setVisible(false);
        celeB.setVisible(false);
        brandsB.setVisible(false);
        introImgLabel.setVisible(false);
    }

    /**
     * Εμφανίζει τα κουμπιά για την επιλογή κατηγορίας
     */
    public void showCat(){
        geoB.setVisible(true);
        sportsB.setVisible(true);
        historyB.setVisible(true);
        celeB.setVisible(true);
        brandsB.setVisible(true);
    }

    /**
     * Καλείται όταν απαντήσει ο χρήστης και τον ενημερώνει με πράσινο χρώμα για την σωστή επιλογή
     * και με κόκκινο χρώμα για τις υπόλοιπες 3 λάθος επιλογές
     */
    public void displayAnswer(String answer){
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
        qLabel1.setForeground(Color.red);
        qLabel2.setForeground(Color.red);
        qLabel3.setForeground(Color.red);
        qLabel4.setForeground(Color.red);
         if(answer.equals("1")){
             qLabel1.setForeground(Color.green);
         }
         else if (answer.equals("2")){
             qLabel2.setForeground(Color.green);
         }
         else if(answer.equals("3")){
             qLabel3.setForeground(Color.green);
         }
         else {
             qLabel4.setForeground(Color.green);
         }
    }

    /**
     * Καλείται όταν απαντήσουν και οι δύο παίκτες (στην μέθοδο 2 players)
     */
    public void bothReady(){
        seconds = 5;
        displayAnswer(answer);
        timer.stop();
        firstReady = false;
        secondReady = false;
        if (countQ == 5)
            next.setVisible(true);
        else
            pause.start();
    }

    /**
     * @param x ο αριθμός την φωτογραφίας 0-24
     * Εμφανίζει την αντίστοιχη φωτογραφία όταν το απαιτεί η ερώτηση
     */
    public void displayPhoto(int x){

        image = new ImageIcon(x+".jpg");
        imgLabel.setIcon(image);
        imgLabel.setVisible(true);

    }

    /**
     * Καλείται όταν ο χρήστης player1 απαντήσει σωστά στην ερώτηση
     * Ενημερώνει τον χρήστη με μήνυμα "Well Done"
     * Ενημερώνει αντίστοιχα τα σκορ
     */
    public void answerIsCorrect1(){
        wellDoneLabel1.setForeground(Color.GREEN);
        wellDoneLabel1.setText("Well Done !!!");
        wellDoneLabel1.setVisible(true);
        thermometer1++;
        score1 += mode.getPoints1(true, seconds,iAmFirst,thermometer1==5);
        score1Field.setText("Score1: " + score1);
        therm1Label.setText(thermometer1+"/5");
          if(thermometer1==5){
            countQ=5;
         }
    }

    /**
     * Καλείται όταν ο χρήστης player1 απαντήσει λάθος στην ερώτηση
     * Ενημερώνει τον χρήστη με μήνυμα "Wrong Answer"
     * Ενημερώνει αντίστοιχα τα σκορ
     */
    public void answerIsWrong1(){
        wellDoneLabel1.setForeground(Color.RED);
        wellDoneLabel1.setText("Wrong Answer !!!");
        wellDoneLabel1.setVisible(true);
        score1 += mode.getPoints1(false, seconds,iAmFirst,thermometer1==5);
        score1Field.setText("Score1: " + score1);
    }

    /**
     * Καλείται όταν ο χρήστης player2 απαντήσει σωστά στην ερώτηση
     * Ενημερώνει τον χρήστη με μήνυμα "Well Done"
     * Ενημερώνει αντίστοιχα τα σκορ
     */
    public void answerIsCorrect2(){
        wellDoneLabel2.setForeground(Color.GREEN);
        wellDoneLabel2.setText("Well Done !!!");
        wellDoneLabel2.setVisible(true);
        thermometer2++;
        score2 += mode.getPoints2(true, seconds,iAmFirst,thermometer2==5);
        score2Field.setText("Score2: " + score2);
        therm2Label.setText(thermometer2+"/5");
        if(thermometer2==5){
            countQ=5;
        }

    }

    /**
     * Καλείται όταν ο χρήστης player2 απαντήσει λάθος στην ερώτηση
     * Ενημερώνει τον χρήστη με μήνυμα "Wrong Answer"
     * Ενημερώνει αντίστοιχα τα σκορ
     */
    public void answerIsWrong2(){
        wellDoneLabel2.setForeground(Color.RED);
        wellDoneLabel2.setText("Wrong Answer !!!");
        wellDoneLabel2.setVisible(true);
        score2 += mode.getPoints2(false, seconds,iAmFirst,thermometer2==5);
        score2Field.setText("Score2: " + score2);

    }
}
