package guessTheWord;

public class Main {

    public static void main(String[] args) {
        Game game = new Game("C:\\Users\\97252\\eclipse-workspace\\guessTheWord\\src\\guessTheWord\\words.txt");

        System.out.println("Welcome to Guess the word!");
        System.out.println("The rules are simple, the computer randomly picks a word, and shows you how " +
                "many " + "letters it's made up of.");
        System.out.println("Your goal is to try to figure out the word by guessing one letter at a time.");
        System.out.println("If a letter is indeed in the word the computer will reveal its correct position in" +
                " the " + "word, if not, you lose a point.");
        System.out.println("If you lose 10 points, game over!");
        System.out.println("Let's start!");
        System.out.println("The word has " + game.getWordTitle().length() + " characters (including spaces "
                + "and punctuation).");

        while(!game.gameEnded()){
            System.out.println("You are guessing:" + game.getHiddenWordTitle());
            System.out.println("You have guessed (" + game.getWrongLetters().length()/2 + ") wrong letters:"
                    + game.getWrongLetters());
            game.guessLetter();
        }
        if(game.WonGame()){
            System.out.println("You win!");
            System.out.println("You have guessed " + game.getWordTitle() + " correctly.");
        }
        else{
            System.out.println("You have guessed (" + game.getWrongLetters().length()/2 + ") wrong letters:" +
                    game.getWrongLetters());
            System.out.println("You lost!");
            System.out.println("The word title was " + game.getWordTitle());
            System.out.println("Better luck next time.");
        }
    }
}