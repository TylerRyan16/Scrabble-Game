import Board.Board;
import Board.Square;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class GamePanel extends JPanel{

    // Constants
    private static final int CELL_SIZE = 50;
    private static final int BOARD_SIZE = 15;
    private static final int PLAYER_TILES_HEIGHT = 50;

    // Panels, Buttons
    private JButton mainMenuButton;
    private MainWindow mainWindow;
    private JPanel topRightPanel;
    private JPanel topLeftPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JLabel timerLabel;
    private JLabel turnLabel;
    private JPanel yesNoPanel;
    private JButton yesButton;
    private JButton noButton;
    private JLabel messageLabel;

    int seconds = 0;
    int minutes = 0;

    private Timer timer;
    private Board board;
    private Map<Character, Integer> playerTiles;

    // Game Panel Constructor
    public GamePanel(MainWindow mainWindow){
        this.mainWindow = mainWindow;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(CELL_SIZE * BOARD_SIZE, CELL_SIZE * BOARD_SIZE));

        // Main Menu Button
        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setPreferredSize(new Dimension(200, 50));
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Create a custom dialog
                JDialog dialog = new JDialog(mainWindow, "Confirmation", true);

                // Create panel for Yes/No button customization
                yesNoPanel = new JPanel();
                yesButton  = new JButton("Yes");
                noButton = new JButton("No");

                // Set preferred button size
                Dimension yesNoButtonSize = new Dimension(80, 40);
                yesButton.setPreferredSize(yesNoButtonSize);
                noButton.setPreferredSize(yesNoButtonSize);

                // Add action listener for Yes button
                yesButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        timer.stop();
                        timerLabel.setText("00:00");
                        seconds = 0;
                        minutes = 0;
                        mainWindow.restartGame();
                        dialog.dispose();
                    }
                });

                // Add action listener for no button
                noButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        dialog.dispose();
                    }
                });

                // create confirmation message label
                messageLabel = new JLabel("Are you sure you want to return to the main menu? All your progress will be lost.");
                messageLabel.setHorizontalAlignment(JLabel.CENTER);

                // set Layout
                yesNoPanel.setLayout(new FlowLayout());

                // add components to yesNoPanel
                yesNoPanel.add(messageLabel, BorderLayout.CENTER);
                yesNoPanel.add(yesButton, BorderLayout.WEST);
                yesNoPanel.add(noButton, BorderLayout.EAST);

                // add and adjust dialog
                dialog.add(yesNoPanel);
                dialog.setSize(600, 150);
                dialog.setLocationRelativeTo(mainWindow);
                dialog.setVisible(true);
                }
            });
        

            // Indicating what player's turn it is
            turnLabel = new JLabel("Player 1's Turn");
            turnLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));

            // Timer
            timerLabel = new JLabel("00:00");
            timerLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
            timer = new Timer(1000, new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    seconds++;
                    if (seconds == 60){
                        seconds = 0;
                        minutes++;
                    }
                    timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
                }
            });
       

            // Top Right Panel
            topRightPanel = new JPanel(new BorderLayout());
            topRightPanel.add(turnLabel, BorderLayout.NORTH);
            topRightPanel.add(timerLabel, BorderLayout.CENTER);

            // Top Left Panel
            topLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            topLeftPanel.add(mainMenuButton, BorderLayout.NORTH);

            // Top Panel
            topPanel = new JPanel(new BorderLayout());
            topPanel.add(topLeftPanel, BorderLayout.WEST);
            topPanel.add(topRightPanel, BorderLayout.EAST);
            add(topPanel, BorderLayout.NORTH);

            // Bottom Panel
            bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            add(bottomPanel, BorderLayout.SOUTH);
        }

    public GamePanel(Board board, MainWindow mainWindow){
        this(mainWindow);
        this.board = board;
    }

    public void updatePlayerTiles(Map<Character, Integer> playerTiles){
        this.playerTiles = playerTiles;
        repaint();
    }
    
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        int xMargin = (getWidth() - BOARD_SIZE * CELL_SIZE) / 2;
        int yMargin = (getHeight() - BOARD_SIZE * CELL_SIZE) / 2;

        // draw borders between squares
        g.setColor(Color.BLACK);
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                int x = xMargin + j * CELL_SIZE;
                int y = yMargin + i * CELL_SIZE;

                g.drawLine(x, y + CELL_SIZE, x + CELL_SIZE, y + CELL_SIZE);
                g.drawLine(x + CELL_SIZE, y + CELL_SIZE, x + CELL_SIZE, y + CELL_SIZE);

            }
        }

        // Draw Squares and Premium Tiles
        for (int row = 0; row < BOARD_SIZE; row++){
            for (int col = 0; col < BOARD_SIZE; col++){
                int x = xMargin + col * CELL_SIZE;
                int y = yMargin + row * CELL_SIZE;
                drawSquare(g, x, y, row, col);
            }
         }

         // Draw Player Tiles at bottom
         drawPlayerTiles(g);
         timer.start();
    }

    private void drawPlayerTiles(Graphics g){
        int x = 0;
        int y = getHeight() - PLAYER_TILES_HEIGHT;

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x, y, getWidth(), PLAYER_TILES_HEIGHT);

        g.setColor(Color.BLACK);
        g.drawRect(x, y, getWidth(), PLAYER_TILES_HEIGHT);

        x += 10;
        y += 30;
        g.setFont(new Font("Times New Roman", Font.BOLD, 25));
        g.setColor(Color.BLACK);

        for (Map.Entry<Character, Integer> entry : playerTiles.entrySet()){
            char tile = entry.getKey();
            int count = entry.getValue();

            for (int i = 0; i < count; i++){
                g.drawString(String.valueOf(tile), x, y);
                x += CELL_SIZE;
            }
        }
    }
    

    private void drawSquare(Graphics g, int x, int y, int row, int col){
        Square square = board.getSquare(row, col);
        String squareValue = square.getLetter();
        Color squareColor = getColorBasedOnValue(squareValue);

        g.setColor(squareColor);
        g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, CELL_SIZE, CELL_SIZE);


        if (board.isPremiumTile(row, col)){
            String premiumType = getPremiumType(row, col);
            drawPremiumType(g, x, y, premiumType);
        } else {
            g.setFont(new Font("Times New Roman", Font.BOLD, 20));
            g.setColor(Color.WHITE);

            int centerX = x + CELL_SIZE / 2;
            int centerY = y + CELL_SIZE / 2;

            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(squareValue);
            int textHeight = fm.getHeight();

            int textX = centerX - textWidth / 2;
            int textY = centerY + textHeight / 2;

            g.drawString(squareValue, textX, textY);
        }
    }

    private void drawPremiumType(Graphics g, int x, int y, String premiumType) {
        g.setColor(Color.WHITE);
        Font font = new Font("Times New Roman", Font.BOLD, 15);
        g.setFont(font);
        g.drawString(premiumType, x + CELL_SIZE / 2 - 8, y + CELL_SIZE / 2 + 5);

    }

    private String getPremiumType(int row, int col){
        String squareValue = board.getSquare(row, col).getLetter();
        switch (squareValue){
            case "r":
                return "3W";
            case "p":
                return "2W";
            case "b":
                return "3L";
            case "l":
                return "2L";
            default:
                return "";
        }
    }

    private Color getColorBasedOnValue(String squareValue){
        switch (squareValue){
            case "o":
                return Color.WHITE;
            case "b":
                return Color.BLUE;
            case "l":
                return new Color(173, 216, 230);
            case "p":
                return Color.PINK;
            case "r":
                return Color.RED.darker();
            default:
                return Color.WHITE;
        }
    }

    public void startTimer(){
        timer.start();
    }
}
