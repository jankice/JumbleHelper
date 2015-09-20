package jumblehelper.jumble.com.jumblehelper.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by roshni on 9/20/2015.
 */


    public class ParseFile {

        private Context mContext;
        private HashMap<Long, HashSet<String>> dictionary;

        public ParseFile(Context context) {
            mContext = context;
        }

        public void readAssetFile() {

            AssetManager assetManager = mContext.getAssets();
            try {
                InputStream is = assetManager.open("words.txt");
                parseFile(is);

            } catch (IOException e) {

                e.printStackTrace();
            }
        }


        private void parseFile(InputStream strm) {
            dictionary = new HashMap<Long, HashSet<String>>();;
            BufferedReader bfr = new BufferedReader(new InputStreamReader(strm));

            try {
                String word;
                HashSet<String> wordSet;
                do {
                    word = bfr.readLine();

                    if (word != null && supportedWordFormat(word) && word.length() > 1) {
                        word = word.trim().toLowerCase();
                        long key = AlphabetToPrime.calcPrimeProduct(word);
                        if (dictionary.containsKey(key))
                        {
                            wordSet = dictionary.get(key);
                        }
                        else
                        {
                            dictionary.put(key, wordSet = new HashSet<String>());
                        }

                        wordSet.add(word);
                    }
                } while (word != null);
                Log.d("Size of dictionary ", String.valueOf(dictionary.size()));
            } catch (IOException ex) {
                Log.e("Exception in parseFile ", ex.toString());
            }
        }

        public HashSet<String> getAllMatchingJumbleWords(String word) {
            HashSet<String> matchWords = null;
            long key = AlphabetToPrime.calcPrimeProduct(word);
            if (dictionary.containsKey(key))
                matchWords = dictionary.get(key);
            return matchWords;
        }


        public boolean supportedWordFormat(String word) {
            return word.matches("[a-zA-Z]+");
        }
    }

