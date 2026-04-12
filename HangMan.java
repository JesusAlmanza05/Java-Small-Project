import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;


public class HangMan {


    //word bank txt file
    private File wordBank;  

    //Array list to hold words from word bank
    private ArrayList<String> words = new ArrayList<>();

    //Array that holds guesses
    private ArrayList<Character> guesses = new ArrayList<>();


    //Initialize variables
    private String selectedWord=" ";
    private String translation="";
    private boolean winner = false;
    private boolean gameOver = false;
    private boolean alreadyGuessed=false;
    private boolean found = false;
    private int lives=6 ;
    private char displayWord[];


    //Constructor calls methods to get word and display game
    public HangMan(String language, String difficulty,String userLanguage) throws Exception{
        getWord(language,difficulty);
        doTranslation(userLanguage);
    }


    //method reads file and adds them to word list, then selects random word from list
    private void getWord(String language,String difficulty) throws Exception
    {
        if(language.equals("English"))
            wordBank = new File("englishWords.txt");
        else
            wordBank = new File("spanishWords.txt");
        words.clear();
        //reads file and adds words to array list
        Scanner filScanner = new Scanner(wordBank);
        
        while(filScanner.hasNextLine()) {

            String word = filScanner.nextLine().toLowerCase();

            int len = word.length();

            if(difficulty.equals("Easy") && len <= 5)
                words.add(word);

            else if(difficulty.equals("Medium") && len >=6 && len <=7)
                words.add(word);

            else if(difficulty.equals("Hard") && len >=8)
                words.add(word);
        }


        //randomly selects word from list
        Random rand = new Random();
        int i = rand.nextInt(words.size());
        selectedWord = words.get(i).toLowerCase();

        //initialize display word with underscores
        displayWord = new char[selectedWord.length()];

        for(int j=0 ; j < selectedWord.length(); j++){
            displayWord[j]='_';
        }
        filScanner.close();
    }

    private void doTranslation(String userLanguage) throws Exception
        {
            String selectedWord = this.selectedWord;
            File spanishBank = new File("spanishWords.txt");
            File englishBank = new File("englishWords.txt");

            if(userLanguage.equals("English"))
            {
                int i=0;
                Scanner filScanner = new Scanner(spanishBank);
                while(filScanner.hasNextLine())
                 {
                    String placeHolder = filScanner.nextLine().toLowerCase();
                    if(placeHolder.equals(selectedWord))
                    {
                        Scanner translationScanner = new Scanner(englishBank);
                        for(int j=0; j<=i; j++)
                        {
                            translation =translationScanner.nextLine();
                        }
                        translationScanner.close();
                        break;
                    }
                    else{
                        i++;
                    }
                 }
                filScanner.close();

            }


            if(userLanguage.equals("Spanish"))
            {
                int i=0;
                Scanner filScanner = new Scanner(englishBank);
                while(filScanner.hasNextLine())
                 {
                    String placeHolder = filScanner.nextLine().toLowerCase();
                    if(placeHolder.equals(selectedWord))
                    {
                        Scanner translationScanner = new Scanner(spanishBank);
                        for(int j=0; j<=i; j++)
                        {
                            translation =translationScanner.nextLine();
                        }
                        translationScanner.close();
                        break;
                    }
                    else{
                        i++;
                    }
                 }
                filScanner.close();

            }
                
        }


    //method checks if guessed letter is in selected word
    //updates display word and lives accordingly
    //if wrong letter is guessed, calls checkLoss method
    public void wordGuess(char guess)
    {
        if(guesses.contains(guess))
        {
            alreadyGuessed=true;
        }
        else
        {
            found=false;
            alreadyGuessed=false;
            for(int i = 0; i < selectedWord.length(); i++)
            {
                if(selectedWord.charAt(i) == guess)
                {
                    displayWord[i] = guess;
                    found = true;
                }
            }
                if(found == false)
                {
                    checkLoss();
                }

                guesses.add(guess);
            }
            checkWin();
        }
    

    //method checks if player has guessed the entire word
    private void checkWin()
    {
        if(String.valueOf(displayWord).equals(selectedWord))
        {
            winner = true;
        }
    }


    //method reduces lives by 1 and checks if lives have reached 0
    private void checkLoss()
    {
        lives = lives - 1;
        if(lives==0)
        {
            gameOver = true;
        }
    }
    
    public String getSelectedWord(){
        return selectedWord;
    }
    public String getTranslationWord(){
        return translation;
    }
    public String getDisplayWord() {
        return String.valueOf(displayWord);
    }
    public int getSizeOfWord() {
        return selectedWord.length();
    }

    public String getWordsGuessed() {
        return String.valueOf(guesses);
    }

    public boolean isWordGuessed() {
        return alreadyGuessed;
    }


    public boolean isWordFound() {
        return found;
    }


    public int getLives() {
        return lives;
    }


    public boolean isGameOver() {
        return gameOver;
    }


    public boolean isWinner() {
        return winner;
    }

    
}
    
