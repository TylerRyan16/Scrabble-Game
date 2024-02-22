import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import Board.Board;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    private final int WINDOW_WIDTH = 1000; // Width
    private final int WINDOW_HEIGHT = 1000; // Height

    private JPanel mainPanel;
    private StartPanel startPanel;
    private GamePanel gamePanel;
    private GameFinishPanel gameFinishPanel;

    private ScrabbleGame scrabbleGame;


    public MainWindow() {
        setTitle("Scrabble Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializePanels();
        
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        centerFrame();
        setVisible(true);
    }

    
    private void initializePanels() {
        mainPanel = new JPanel(new CardLayout());

    startPanel = new StartPanel(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            startGame(startPanel.getSelectedNumberOfPlayers());
        }
    });

    mainPanel.add(startPanel, "startPanel");

    scrabbleGame = new ScrabbleGame(this);
    gamePanel = new GamePanel(new Board(), this);
    mainPanel.add(gamePanel, "gamePanel");


    gameFinishPanel = new GameFinishPanel(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            restartGame();
        }
    });
    mainPanel.add(gameFinishPanel, "gameFinishPanel");

    add(mainPanel, BorderLayout.CENTER);

    }
    

    private void switchToPanel(String panelName){
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, panelName);
    }

    private void startGame(int numPlayers){
        scrabbleGame.startGame(numPlayers);
        gamePanel.updatePlayerTiles(scrabbleGame.getPlayerTiles());
        switchToPanel("gamePanel");
    }

    protected void restartGame(){
        switchToPanel("startPanel");
    }
    
    private void centerFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);
    }

}