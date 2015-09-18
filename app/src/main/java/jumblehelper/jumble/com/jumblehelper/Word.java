package jumblehelper.jumble.com.jumblehelper;

import android.util.Log;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by roshni on 7/26/2015.
 */
public class Word {


    ArrayList<String> wordlist;
    Word(String value) {

        wordlist = new ArrayList<String>();


        String[] str = value.split(",");
        try {
            this.word = str[0];
            this.hash = new BigInteger(str[1]);
            wordlist.add(word);
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
    public ArrayList<String> getallword(){
        return wordlist;
    }

public String getallwordstring()
{
    String allWords = "";
    for(int i=0;i<wordlist.size();i++)
    {
        allWords +=", "+ wordlist.get(i);

    }
    return allWords;
}
    public BigInteger getHash() {
        return hash;
    }


}
