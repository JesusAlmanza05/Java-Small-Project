import javax.swing.*;
import java.awt.*;

public class StartMenuGUI extends JFrame {

    JComboBox<String> languageBox;
    JComboBox<String> difficultyBox;
    JButton startButton;

    public StartMenuGUI() {

        setTitle("Hangman - Start Menu");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(20,20,20));

        JLabel title = new JLabel("HANGMAN Education Edition", SwingConstants.CENTER);
        title.setFont(new Font("Consolas", Font.BOLD, 70));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(40,0,40,0));

        mainPanel.add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4,1,20,20));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50,250,50,250));
        centerPanel.setBackground(new Color(40,40,40));

        JLabel langLabel = new JLabel("Select Language", SwingConstants.CENTER);
        langLabel.setForeground(Color.WHITE);
        langLabel.setFont(new Font("Arial", Font.BOLD, 40));

        languageBox = new JComboBox<>(new String[]{"English","Spanish"});
        languageBox.setFont(new Font("Arial", Font.BOLD, 25));

        JLabel diffLabel = new JLabel("Select Difficulty", SwingConstants.CENTER);
        diffLabel.setForeground(Color.WHITE);
        diffLabel.setFont(new Font("Arial", Font.BOLD, 40));

        difficultyBox = new JComboBox<>(new String[]{"Easy","Medium","Hard"});
        difficultyBox.setFont(new Font("Arial", Font.BOLD, 25));

        centerPanel.add(langLabel);
        centerPanel.add(languageBox);
        centerPanel.add(diffLabel);
        centerPanel.add(difficultyBox);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        startButton = new JButton("START GAME");
        startButton.setFont(new Font("Arial", Font.BOLD, 35));
        startButton.setBackground(new Color(70,130,180));
        startButton.setForeground(Color.WHITE);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(40,40,40));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20,0,40,0));
        bottomPanel.add(startButton);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);

        startButton.addActionListener(e -> startGame());

        setVisible(true);
    }

    private void startGame() {

        String language = (String) languageBox.getSelectedItem();
        String difficulty = (String) difficultyBox.getSelectedItem();

        try {
            new GameGUI(language, difficulty);
        } catch (Exception e) {
            e.printStackTrace();
        }

        dispose();
    }
}