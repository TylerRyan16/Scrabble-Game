
import javax.swing.*;
import Board.Board;
import Board.Square;
import java.awt.*;

public class ScrabbleBoardPanel extends JPanel{

    private static final  int CELL_SIZE = 50;
    private static final  int BOARD_SIZE = 15;

    private Board board;

    public ScrabbleBoardPanel(){};
    public ScrabbleBoardPanel(Board board){
        this.board = board;
    }
    
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        for (int i = 0; i < BOARD_SIZE; i++){
            g.drawLine(i * CELL_SIZE, 0, i * CELL_SIZE, BOARD_SIZE * CELL_SIZE - 1);
            g.drawLine(0, i * CELL_SIZE, BOARD_SIZE * CELL_SIZE - 1, i * CELL_SIZE);
        }

        for (int row = 0; row < BOARD_SIZE; row++){
            for (int col = 0; col < BOARD_SIZE; col++){
                drawSquare(g, row, col);
            }
        }
    }

    private void drawSquare(Graphics g, int row, int col){
        Square square = board.getSquare(row, col);
        String squareValue = square.getLetter();
        Color squareColor = getColorBasedOnValue(squareValue);

        int x = col * CELL_SIZE;
        int y = row * CELL_SIZE;

        g.setColor(squareColor);
        g.fillRect(x, y, CELL_SIZE, CELL_SIZE);

        g.setColor(Color.BLACK);
        g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
    }

    private Color getColorBasedOnValue(String squareValue){
        switch (squareValue){
            case "o":
                return Color.WHITE;
            case "b":
                return Color.BLUE.darker();
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

    public int getCellSize(){
        return CELL_SIZE;
    }

    public int getBoardSize(){
        return BOARD_SIZE;
    }
}