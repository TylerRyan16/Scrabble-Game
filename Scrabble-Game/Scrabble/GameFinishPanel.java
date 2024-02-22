import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFinishPanel extends JPanel{
    private JButton restartButton;

    public GameFinishPanel(ActionListener restartButtonListener){
        setLayout(new GridLayout(2,1));

        JLabel gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(gameOverLabel);

        restartButton = new JButton("Restart Game");
        restartButton.addActionListener(restartButtonListener);
        add(restartButton);
    }
}
