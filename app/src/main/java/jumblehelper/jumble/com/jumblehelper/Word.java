package jumblehelper.jumble.com.jumblehelper;

import android.util.Log;

import java.math.BigInteger;

/**
 * Created by roshni on 7/26/2015.
 */
public class Word {

    Word(String value) {

        String[] str = value.split(",");
        try {
            this.word = str[0];


            this.hash = new BigInteger(str[1]);
        } catch (ArrayIndexOutOfBoundsException ex) {
            Log.d("debug", "Array index out of bounds=>" + value);
        }
        // String index = String.valueOf(String.valueOf(hash).indexOf(word));


    }

    private String word;
    private BigInteger hash;


    public String getWord() {
        return word;
    }

    public BigInteger getHash() {
        return hash;
    }


}
