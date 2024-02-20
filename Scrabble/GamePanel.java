import Board.Board;
import Board.Square;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{
    private static final int CELL_SIZE = 50;
    private static final int BOARD_SIZE = 15;
    private JButton mainMenuButton;

    private Board board;

    public GamePanel(){
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(CELL_SIZE * BOARD_SIZE, CELL_SIZE * BOARD_SIZE));

        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setPreferredSize(new Dimension(100, 30));
        add(mainMenuButton, BorderLayout.SOUTH);
    }

    public GamePanel(Board board){
        this();
        this.board = board;
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
}
