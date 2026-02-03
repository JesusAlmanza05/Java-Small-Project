import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;


public class HangMan {


    //word bank txt file
    File wordBank = new File("src/fruits.txt");

    //Array list to hold words from word bank
    ArrayList<String> words = new ArrayList<>();

    //Initialize variables
    String selectedWord=" ";
    boolean winner = false;

    public HangMan() throws Exception{
        getWord();
        displayGame();
    }

    //method reads file and adds them to word list, then selects random word from list
    public void getWord() throws Exception{

        Scanner filScanner = new Scanner(wordBank);
        while(filScanner.hasNextLine()){
            words.add(filScanner.nextLine());
        }
        Random rand = new Random();
        int i = rand.nextInt(words.size());
        selectedWord = words.get(i).toLowerCase();
        filScanner.close();
    }

    public void displayGame(){
        System.out.println("Hangman Game Started!");
        System.out.println("Guess the word: " + "_ ".repeat(selectedWord.length()));

        Scanner input = new Scanner(System.in);
        while(winner ==false)
        {
            System.out.print("Enter your guess: ");
            String guess = input.nextLine().toLowerCase();

            if(selectedWord.equals(guess)){
                System.out.println("Congratulations! You guessed the word: " + selectedWord);
                winner = true;
            } else {
            System.out.println("incorrect guess. Try again!");
            }
        }
        input.close();

    }
}