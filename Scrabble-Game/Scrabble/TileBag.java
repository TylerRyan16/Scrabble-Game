import java.util.*;

public class TileBag {
    private Map<Character, Integer> distribution;
    private List<Character> tiles;

    public TileBag(){
        distribution = new HashMap<>();
        distribution.put('A', 9);
        distribution.put('B', 2);
        distribution.put('C', 2);
        distribution.put('D', 4);
        distribution.put('E', 12);
        distribution.put('F', 2);
        distribution.put('G', 3);
        distribution.put('H', 2);
        distribution.put('I', 9);
        distribution.put('J', 1);
        distribution.put('K', 1);
        distribution.put('L', 4);
        distribution.put('M', 2);
        distribution.put('N', 6);
        distribution.put('O', 8);
        distribution.put('P', 2);
        distribution.put('Q', 1);
        distribution.put('R', 6);
        distribution.put('S', 4);
        distribution.put('T', 6);
        distribution.put('U', 4);
        distribution.put('V', 2);
        distribution.put('W', 2);
        distribution.put('X', 1);
        distribution.put('Y', 2);
        distribution.put('Z', 1);


        tiles = new ArrayList<>();
        refillBag();
    }

    public void displayTilesInBag(){
        System.out.println("Tiles in the bag:");
        for (char tile : tiles){
            System.out.print(tile + " ");
        }
        System.out.println();
    }

    private void refillBag(){
        tiles.clear();
        for (Map.Entry<Character, Integer> entry : distribution.entrySet()){
            char tile = entry.getKey();
            int quantity = entry.getValue();
            for (int i = 0; i < quantity; i++){
                tiles.add(tile);
            }
        }
        Collections.shuffle(tiles);
    }

    public List<Character> drawTiles(int numTiles){
        List<Character> drawnTiles = new ArrayList<>();
        for (int i = 0; i < numTiles; i++){
            if (!tiles.isEmpty()){
                char tile = tiles.remove(0);
                drawnTiles.add(tile);
            } else {
                System.out.println("The tile bag is empty.");
                break;
            }
        }
        return drawnTiles;
    }
}
