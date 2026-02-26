import javax.swing.*;
import java.awt.*;

public class HangManDrawing extends JPanel {

    HangMan game;
    Image[] hangImages;

    public HangManDrawing(HangMan game) {
        this.game = game;
        setBackground(Color.WHITE);
        
        hangImages = new Image[7];
        hangImages[0] = new ImageIcon(getClass().getResource("/hangStand.png")).getImage();
        hangImages[1] = new ImageIcon(getClass().getResource("/hangSix.png")).getImage();
        hangImages[2] = new ImageIcon(getClass().getResource("/hangFive.png")).getImage();
        hangImages[3] = new ImageIcon(getClass().getResource("/hangFour.png")).getImage();
        hangImages[4] = new ImageIcon(getClass().getResource("/hangThree.png")).getImage();
        hangImages[5] = new ImageIcon(getClass().getResource("/hangTwo.png")).getImage();
        hangImages[6] = new ImageIcon(getClass().getResource("/hangOne.png")).getImage();

    }

    public void updateGame(HangMan game) {
        this.game = game;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int lives = game.getLives();
        int index = 6 - lives;

        if (index < hangImages.length) {
            g.drawImage(hangImages[index], 0, 0, getWidth(), getHeight(), this);
        }
    }
}