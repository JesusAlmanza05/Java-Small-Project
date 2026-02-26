import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;


public class HangMan {


    //word bank txt file
    File wordBank = new File("fruits.txt");

    //Array list to hold words from word bank
    ArrayList<String> words = new ArrayList<>();

    //Array that holds guesses
    ArrayList<Character> guesses = new ArrayList<>();


    //Initialize variables
    String selectedWord=" ";
    boolean winner = false;
    boolean gameOver = false;
    boolean alreadyGuessed=false;
    boolean found = false;
    int lives=6;
    char displayWord[];


    //Constructor calls methods to get word and display game
    public HangMan() throws Exception{
        getWord();
    }


    //method reads file and adds them to word list, then selects random word from list
    public void getWord() throws Exception
    {
        words.clear();
        //reads file and adds words to array list
        Scanner filScanner = new Scanner(wordBank);
        while(filScanner.hasNextLine()){
            words.add(filScanner.nextLine());
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
    public void checkWin()
    {
        if(String.valueOf(displayWord).equals(selectedWord))
        {
            winner = true;
        }
    }


    //method reduces lives by 1 and checks if lives have reached 0
    public void checkLoss()
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
    
