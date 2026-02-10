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
    int lives=6;
    char displayWord[];
    char userGuess;


    //Constructor calls methods to get word and display game
    public HangMan() throws Exception{
        getWord();
        displayGame();
    }

    //method reads file and adds them to word list, then selects random word from list
    public void getWord() throws Exception
    {
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

    //method displays game status and takes player input
    public void displayGame()
    {
        System.out.println("Learning Hangman Game Started!");
        System.out.println("Catgeory: Fruits");
        System.out.println("Word Length: " + selectedWord.length());
        Scanner input = new Scanner(System.in);

        //game continues until player wins or runs out of lives
        while(winner ==false && gameOver ==false)
        {
            System.out.println("Lives: " + lives);
            System.out.println("Guess the word: " + String.valueOf(displayWord));
            System.out.print("Enter a letter: ");
            //takes only first character(even if player enters multiple characters)
            // of input and converts to lowercase
            while(true)
            {
                 String userInput = input.next().toLowerCase();

                if(userInput.length()!= 1)
                {
                    System.out.println("Error only enter ONE character!");
                    continue;
                }
                userGuess = userInput.charAt(0);

                if(!Character.isLetter(userGuess))
                {
                    System.out.println("Error please enter a single LETTER");
                    continue;
                }

                break;
                    
            }

            System.out.println();
            wordGuess(userGuess);
            checkWin();
        }
        input.close();
    }

    //method checks if guessed letter is in selected word
    //updates display word and lives accordingly
    //if wrong letter is guessed, calls checkLoss method
    public void wordGuess(char guess)
    {
        boolean found = false;
        if(guesses.contains(guess))
        {
            System.out.println("You already guessed that letter! Try again!");
        }
        else
        {
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
                    if(!gameOver)
                    {
                       System.out.println("Wrong guess! Try again!");
                    }
                }
                else
                {
                    System.out.println("Correct guess! Keep going!");
                }
                guesses.add(guess);
            }
            System.out.println("Guesses: " + guesses);
        }
    
    //method checks if player has guessed the entire word
    public void checkWin()
    {
        if(String.valueOf(displayWord).equals(selectedWord))
        {
            winner = true;
            System.out.println("Congratulations! You guessed the word: " + selectedWord);
        }
    }

    //method reduces lives by 1 and checks if lives have reached 0
    public void checkLoss()
    {
        lives = lives - 1;
        if(lives==0)
        {
            gameOver = true;
            System.out.println("Game Over! The word was: " + selectedWord);
        }
    }
    
}
    
