package jumblehelper.jumble.com.jumblehelper.util;

/**
 * Created by kon1532 on 9/16/2015.
 */
public class AlphabetToPrime {

    private static final int aDecimalValue = 97;


    private static final int[] map = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73,
            79, 83, 89, 97, 101};


    private static int get(char c) {
        int val = (int) c;
        return map[val - aDecimalValue];    // offset
    }

    //TODO - create unique multiplication of word
    
    public static long calcPrimeProduct(String word) {
        char[] wordChars = word.toCharArray();
        long primeProd = 1;
        for (char c : wordChars) {
            primeProd *= AlphabetToPrime.get(c);
        }
        return primeProd;
    }
}