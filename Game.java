package guessTheWord;

import java.util.Scanner;

public class Game {
    /**
     * Class that represents a "Guess The Word" Game:
	 * The goal is to try to figure out the word by guessing one letter at a time. If a letter is indeed in the
     * word the computer will reveal its correct position in the word, if not, the player loses a point. If the player
     * loses 10 points, the game is over.
     */

    private String wordToGuess;
    private int pointsLost;
    private String wrongLetters;
    private String rightLetters;
    private boolean gameWon;

    /**
     * Class constructor that initializes a {@link wordList} object from a file containing the words' titles, and
     * all other attributes of the game:
     * pointsLost = points lost so far;
     * rightLetters = letters guessed that are actually in the word title (in upper and lower case);
     * wrongLetters = letters guessed that are not in the word title;
     * gameWon = whether the player has already won the game.
     *
     * @param pathname          Path to a file containing the words' titles.
     */
    public Game(String pathname) {
        Words wordsList = new Words (pathname);
        wordToGuess = wordsList.getRandomWord().trim();
        pointsLost = 0;
        rightLetters = "";
        wrongLetters = "";
        gameWon = false;
    }

    /**
     * Method that returns the title of the word to be guessed.
     *
     * @return title of the word to be guessed.
     */
    public String getWordTitle() {
        return wordToGuess;
    }

    /**
     * Method that replaces all the letters in the word title with underscores, if no letters have been correctly
     * guessed yet, and all the letters except the ones guessed, if any letter was already correctly guessed.
     *
     * @return {@link String} with the word title with the letters not guessed hidden.
     */
    public String getHiddenWordTitle() {
        if(rightLetters.equals("")){
            return wordToGuess.replaceAll("[a-zA-Z]", "_");
        }
        else{
            return wordToGuess.replaceAll("[a-zA-Z&&[^" + rightLetters +"]]", "_");
        }
    }

    /**
     * Method that returns letters guessed that are not in the word title.
     *
     * @return {@link String} with letters guessed that are not in the word title separated by blank spaces.
     */
    public String getWrongLetters() {
        return wrongLetters;
    }

    /**
     * Method that returns true if the game was won and false otherwise.
     *
     * @return true if the game was won and false otherwise.
     */
    public boolean WonGame() {
        return gameWon;
    }

    /**
     * Method that returns that the game has ended and the player did not win if number of points lost is at least 10,
     * and returns that the game has ended and the player won if the previous is not true and there are no underscores
     * left in the hidden version of the word title.
     *
     * @return true if the game has ended and false otherwise.
     */
    public boolean gameEnded() {
        if (pointsLost >= 10) {
            return true;
        }

        if(!getHiddenWordTitle().contains("_")) {
            gameWon = true;
            return true;
        }
        return false;
    }

    /**
     * Method that (1) asks the player to input a letter; (2) converts it to lower case; (3) asks him to input another
     * letter (implemented recursively) if (a) the {@link String} inputed is not a letter or (b) if the letter was
     * already guessed and so is included in the {@link String} objects containing the letters guessed wrongly and
     * correctly, respectively; (4) if the {@link String} inputed is a letter not guessed yet, the method returns the
     * letter.
     *
     * @return Letter not guessed yet.
     */
    private String inputLetter(){

        System.out.println("Guess a letter:");
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        String letter = scanner.nextLine().toLowerCase();

        if(!letter.matches("[a-z]")){
            System.out.println("That is not a letter.");
            return inputLetter();
        }
        else if(wrongLetters.contains(letter) || rightLetters.contains(letter)){
            System.out.println("You already guessed that letter.");
            return inputLetter();
        }
        else{
            return letter;
        }
    }

    /**
     * Method that (1) asks the player for a letter not guessed and (a) if the guess is correct, adds it to the
     * {@link String} that contains the letters guessed correctly in upper and lower case, (b) otherwise increases
     * the number of points lost by 1 and adds the letter to the {@link String} that contains the letters guessed
     * wrongly.
     */
    public void guessLetter() {

        String guessedLetter = inputLetter();

        if (wordToGuess.toLowerCase().contains(guessedLetter)) {
            rightLetters += guessedLetter + guessedLetter.toUpperCase();
        }
        else {
            pointsLost++;
            wrongLetters += " " + guessedLetter;
        }
    }
}