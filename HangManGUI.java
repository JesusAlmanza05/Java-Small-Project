import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HangManGUI extends JFrame {

    HangManDrawing drawingPanel;
    HangMan game;

    JLabel wordLabel;
    JLabel livesLabel;
    JLabel messageLabel;
    JLabel wordsUsed;

    JTextField inputField;
    JButton guessButton;
    JButton retryButton;

    public HangManGUI() throws Exception {

        game = new HangMan();
        drawingPanel = new HangManDrawing(game);
        add(drawingPanel, BorderLayout.CENTER);

        setTitle("Hangman Game");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Top panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3,1));


        wordLabel = new JLabel(game.getSizeOfWord()+" Letters:"+game.getDisplayWord(), SwingConstants.CENTER);
        wordLabel.setFont(new Font("Consolas", Font.BOLD, 50));

        livesLabel = new JLabel("Lives: " + game.getLives(), SwingConstants.CENTER);
        livesLabel.setFont(new Font("Arial", Font.BOLD, 50));

        wordsUsed = new JLabel("Guesses: "+game.getWordsGuessed(), SwingConstants.CENTER);
        wordsUsed.setFont(new Font("Arial", Font.PLAIN, 35));
        
        topPanel.add(livesLabel);
        topPanel.add(wordLabel);
        topPanel.add(wordsUsed);
        add(topPanel, BorderLayout.NORTH);


        // bottom panel
        JPanel bottomPanel = new JPanel();

        JPanel subBottom = new JPanel();
        subBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        inputField = new JTextField(5);
        inputField.setFont(new Font("Arial", Font.BOLD, 50));
        inputField.setHorizontalAlignment(JTextField.CENTER);
        guessButton = new JButton("Guess");
        guessButton.setFont(new Font("Arial", Font.BOLD, 30));

        retryButton = new JButton("Retry");
        retryButton.setFont(new Font("Arial", Font.BOLD, 30));
        retryButton.setVisible(false); // hidden at start

        subBottom.add(inputField);
        subBottom.add(guessButton);
        subBottom.add(retryButton);

        
        messageLabel = new JLabel("Enter a letter.");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 45));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        bottomPanel.setLayout(new GridLayout(2, 1, 0, 10));
        bottomPanel.add(subBottom); 
        bottomPanel.add(messageLabel);

        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 0));
        add(bottomPanel, BorderLayout.SOUTH);


        // Button listener
        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });
        
        retryButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                try {
                    game = new HangMan();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }  // new game
                drawingPanel.updateGame(game);
                wordLabel.setText(game.getSizeOfWord()+" Letters:"+game.getDisplayWord());
                livesLabel.setText("Lives: " + game.getLives());
                wordsUsed.setText("Guesses: "+game.getWordsGuessed());

                messageLabel.setText("Enter a letter.");
                guessButton.setEnabled(true);
                retryButton.setVisible(false);
            }
            
        });


        setVisible(true);
    }

    private void handleGuess() {

        String input = inputField.getText().toLowerCase();

        if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
            messageLabel.setText("Enter ONE LETTER only!");
            return;
        }

        char guess = input.charAt(0);

        game.wordGuess(guess);

        wordLabel.setText(game.getSizeOfWord()+" Letters:"+game.getDisplayWord());
        livesLabel.setText("Lives: " + game.getLives());
        wordsUsed.setText("Guesses: "+game.getWordsGuessed());

        if (game.isWinner()) {
            messageLabel.setText("You WIN! The Word Was: "+game.getSelectedWord());
            guessButton.setEnabled(false);
            retryButton.setVisible(true);
        }
        else if (game.isGameOver()) {
            messageLabel.setText("Game Over! The Word Was: "+game.getSelectedWord());
            guessButton.setEnabled(false);
            retryButton.setVisible(true);

        }
        else if(game.isWordGuessed())
        {
            messageLabel.setText("Letter Already Used! Try Again!");
        }
        else if(game.isWordFound())
        {
            messageLabel.setText("Correct! Keep Guessing!");
        }
        else{
            messageLabel.setText("Nope Incorret! Keep Guessing!");
        }

        inputField.setText("");

        drawingPanel.updateGame(game);
    }
}
