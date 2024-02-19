
import javax.swing.*;
import Board.Board;
import Board.Square;
import java.awt.*;

public class MainWindow extends JFrame{


    private final int WINDOW_WIDTH = 800; // Width
    private final int WINDOW_HEIGHT = 800; // Height
    
    private JLabel timerLabel;
    private JPanel playerPanel;
    
    public MainWindow(){
        setTitle("Scrabble Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set up layout for entire frame
        setLayout(new BorderLayout());

        // create and add the game board panel
        ScrabbleBoardPanel boardPanel = new ScrabbleBoardPanel(new Board());
        boardPanel.setPreferredSize(new Dimension(boardPanel.getCellSize() * boardPanel.getBoardSize(), boardPanel.getCellSize() * boardPanel.getBoardSize()));
        add(boardPanel, BorderLayout.CENTER);

        // create and add the player panel
        playerPanel = new JPanel();
        playerPanel.setLayout(new FlowLayout());
        add(playerPanel, BorderLayout.SOUTH);
       

        // create and add timer label
        timerLabel = new JLabel("00:00", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(timerLabel, BorderLayout.NORTH);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        pack();


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);
        setVisible(true);
    


    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            new MainWindow();
        });
    }
}
