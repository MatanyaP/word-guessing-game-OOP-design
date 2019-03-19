package guessTheWord;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Words {
    /**
     * Class representing a list of words.
     */

    private ArrayList <String> words;

    /**
     * Class constructor that stores the word contained a file in an {@link ArrayList}, until it scans all lines
     * in the file, if a valid path to the file exists or warns the user otherwise.
     *
     * @param pathname          Path to a file containing the words.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Words(String pathname) {
        words = new ArrayList();
        File file = new File(pathname);
        try {
            @SuppressWarnings("resource")
			Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                words.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist!");
        }
    }

    /**
     * Method that generates a random {@link Integer} from 0 to the number of words in the list minus 1, and
     * returns the word in the words {@link ArrayList} with that index.
     *
     * @return random word title from the list.
     */
    public String getRandomWord() {
        //Returns a string with a random word from the list.
        int wordIndex = (int) (Math.random() * words.size());
        return words.get(wordIndex);
    }
}
