package Board;
import java.util.Arrays;

public class Board{
    
    private Square[][] squares;

    public Board(){
        this.squares = new Square[15][15];
        initializeBoard();
    }
    
    private void initializeBoard(){
        String[][] boardValues = {
            {"r", "o", "o", "l", "o", "o", "o", "r", "o", "o", "o", "l", "o", "o", "r"},
            {"o", "p", "o", "o", "o", "b", "o", "o", "o", "b", "o", "o", "o", "p", "o"},
            {"o", "o", "p", "o", "o", "o", "l", "o", "l", "o", "o", "o", "p", "o", "o"},
            {"l", "o", "o", "p", "o", "o", "o", "l", "o", "o", "o", "p", "o", "o", "l"},
            {"o", "o", "o", "o", "p", "o", "o", "o", "o", "o", "p", "o", "o", "o", "o"},
            {"o", "b", "o", "o", "o", "b", "o", "o", "o", "b", "o", "o", "o", "b", "o"},
            {"o", "o", "l", "o", "o", "o", "l", "o", "l", "o", "o", "o", "l", "o", "o"},
            {"r", "o", "o", "l", "o", "o", "o", "M", "o", "o", "o", "l", "o", "o", "r"},
            {"o", "o", "l", "o", "o", "o", "l", "o", "l", "o", "o", "o", "l", "o", "o"},
            {"o", "b", "o", "o", "o", "b", "o", "o", "o", "b", "o", "o", "o", "b", "o"},
            {"o", "o", "o", "o", "p", "o", "o", "o", "o", "o", "p", "o", "o", "o", "o"},
            {"l", "o", "o", "p", "o", "o", "o", "l", "o", "o", "o", "p", "o", "o", "l"},
            {"o", "o", "p", "o", "o", "o", "l", "o", "l", "o", "o", "o", "p", "o", "o"},
            {"o", "p", "o", "o", "o", "b", "o", "o", "o", "b", "o", "o", "o", "p", "o"},
            {"r", "o", "o", "l", "o", "o", "o", "r", "o", "o", "o", "l", "o", "o", "r"},

        };

        for (int row = 0; row < 15; row++){
            for (int col = 0; col < 15; col++){
                squares[row][col] = new Square(boardValues[row][col]);
            }
        }
    }

    public Square getSquare(int row, int col){
        return squares[row][col];
    }

    public void setSquare(int row, int col, Square square){
        squares[row][col] = square;
    }

    public void printBoard(){
        for (int row = 0; row < 15; row++){
            for (int col = 0; col < 15; col++){
                System.out.print(squares[row][col].getLetter() + "  ");
            }
            System.out.println();
        }
    }

}