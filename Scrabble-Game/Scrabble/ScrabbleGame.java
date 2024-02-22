import java.util.*;

import javax.swing.SwingUtilities;

import Board.Board;

public class ScrabbleGame{
    
    private TileBag tileBag;
    private Map<Character, Integer> playerTiles;
    private List<Map<Character, Integer>> playerTilesList;
    private static final Map<Character, Integer> letterValues = new HashMap<>();

    private MainWindow mainWindow;
    static {
        // Assign point values to letters
        letterValues.put('A', 1);
        letterValues.put('E', 1);
        letterValues.put('I', 1);
        letterValues.put('O', 1);
        letterValues.put('U', 1);
        letterValues.put('L', 1);
        letterValues.put('N', 1);
        letterValues.put('S', 1);
        letterValues.put('T', 1);
        letterValues.put('R', 1);

        letterValues.put('D', 2);
        letterValues.put('G', 2);

        letterValues.put('B', 3);
        letterValues.put('C', 3);
        letterValues.put('M', 3);
        letterValues.put('P', 3);

        letterValues.put('F', 4);
        letterValues.put('H', 4);
        letterValues.put('V', 4);
        letterValues.put('W', 4);
        letterValues.put('Y', 4);

        letterValues.put('K', 5);

        letterValues.put('J', 8);
        letterValues.put('X', 8);

        letterValues.put('Q', 10);
        letterValues.put('Z', 10);
    }



public ScrabbleGame(MainWindow mainWindow){
    this.mainWindow = mainWindow;
    tileBag = new TileBag();
    playerTiles = new HashMap<>();
    playerTilesList = new ArrayList<>();
}

public void startGame(int numPlayers){
   // mainWindow.switchToGamePanel(new Board());
    initializePlayerTiles(numPlayers);
    drawInitialTiles(numPlayers);
    tileBag.displayTilesInBag();
}

private void initializePlayerTiles(int numPlayers){
    for (int i = 0; i < numPlayers; i++){
        playerTilesList.add(new HashMap<>());
    }
}

private void drawInitialTiles(int numPlayers){
    for (int i = 0; i < numPlayers; i++){
        Map<Character, Integer> currentPlayerTiles = playerTilesList.get(i);
        drawTileForPlayer(currentPlayerTiles);
    }
}

public void drawTileForPlayer(Map<Character, Integer> playerTiles){
    for (int i = 0; i < 7; i++){
        List<Character> drawnTiles = tileBag.drawTiles(1);
        char tile = drawnTiles.get(0);
        playerTiles.put(tile, playerTiles.getOrDefault(tile, 0) + 1);
    }
}

public boolean placeTiles(List<Character> tiles){
    for (char tile : tiles){
        if (!playerTiles.containsKey(tile) || playerTiles.get(tile) == 0){
            return false;
        }
    }
    for (char tile : tiles){
        playerTiles.put(tile, playerTiles.get(tile) - 1);
    }
    return true;
}

public static int getLetterValue(char letter){
    return letterValues.getOrDefault(Character.toUpperCase(letter), 0);
}

public Map<Character, Integer> getPlayerTiles(){
    return playerTiles;
}

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
        MainWindow mainWindow = new MainWindow();
        ScrabbleGame game = new ScrabbleGame(mainWindow);
        game.startGame(2);
    });
    }
}