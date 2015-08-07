package jumblehelper.jumble.com.jumblehelper;

import java.util.Comparator;

/**
 * Created by roshni on 7/27/2015.
 */
public class CompareWord implements Comparator {
    @Override
    public int compare(Object mul, Object hashval) {
        Word mulword = (Word) mul;
        Word hashword = (Word) hashval;

        return mulword.getHash().compareTo(hashword.getHash());


    }
}
