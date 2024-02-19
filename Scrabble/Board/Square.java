package Board;
public class Square {
    private String letter;
    private boolean isFilled;


    public Square(){
        this.letter = "";
        this.isFilled = false;
    }
    public Square(String letter){
        this.letter = letter;
    }

    public String getLetter(){
        return letter;
    }

    public void setLetter(String letter){
        Character c = letter.charAt(0);
        if (Character.isLetter(c)){
            this.letter = letter;
            this.isFilled = true;
        } else {
            System.out.println("Error: Character entered is not a valid letter.");
        }
        
    }

    public boolean isFilled(){
        return isFilled;
    }
    
}
