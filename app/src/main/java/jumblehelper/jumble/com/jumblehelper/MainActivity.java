package jumblehelper.jumble.com.jumblehelper;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


public class MainActivity extends ActionBarActivity {

    Button btn_get;
    EditText edt_char;
    TextView view_mul;
    TextView view_word;
    BigInteger mul = BigInteger.ONE;
    HashMap<Character, Integer> m;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Log.d("start", "hashmp");

        m = new HashMap<Character, Integer>();

        m.put('a', 2);
        m.put('b', 3);
        m.put('c', 5);
        m.put('d', 7);
        m.put('e', 11);
        m.put('f', 13);
        m.put('g', 17);
        m.put('h', 19);
        m.put('i', 23);
        m.put('j', 29);
        m.put('k', 31);
        m.put('l', 37);
        m.put('m', 43);
        m.put('n', 47);
        m.put('o', 53);
        m.put('p', 59);
        m.put('q', 61);
        m.put('r', 67);
        m.put('s', 71);
        m.put('t', 73);
        m.put('u', 79);
        m.put('v', 83);
        m.put('w', 89);
        m.put('x', 97);
        m.put('y', 101);
        m.put('z', 103);

        Set set = m.entrySet();
        Iterator i = set.iterator();


        btn_get = (Button) findViewById(R.id.btnGet);
        edt_char = (EditText) findViewById(R.id.editText);
        view_mul = (TextView) findViewById(R.id.viewMul);
        view_word = (TextView) findViewById(R.id.yourWord);

        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("debug", "click");
                String word = edt_char.getText().toString();
                BigInteger computation = compute(word);
                view_mul.setText(computation.toString());


                AssetManager assetManager = getAssets();
                //  InputStream is = null;
                try {
                    InputStream is = assetManager.open("W3.TXT");
                    parsefile(is, new char[0]);


                } catch (IOException e) {

                    e.printStackTrace();
                }

            }
        });
    }


    public BigInteger compute(String s) {
        BigInteger mul = BigInteger.ONE;
        for (int i = 0; i < s.length(); i++) {
            mul = mul.multiply(BigInteger.valueOf(m.get(s.charAt(i))));
        }

        return mul;
    }

    public void parsefile(InputStream strm, char[] index) {
        ArrayList<String> fileContents = new ArrayList<String>();
        BufferedReader bfr = new BufferedReader(new InputStreamReader(strm));

        try {
            String str;
            do {
                str = bfr.readLine();
                if (str != null) {
                    fileContents.add(str);
                }

            } while (str != null);
            Log.d("debug", String.valueOf(fileContents.size()));
        } catch (IOException ex) {

        }

        Word[] wordArray = new Word[fileContents.size()];

        for (int i = 0; i < fileContents.size(); i++) {
            wordArray[i] = new Word(fileContents.get(i));

        }

        Arrays.sort(wordArray, new CompareWord());
        Log.d("array", "sorted");


        int position = Arrays.binarySearch(wordArray, new Word(edt_char.getText().toString() + "," + String.valueOf(compute(edt_char.getText().toString()))), new CompareWord());
        if (position >= 0) {
            Log.d("debug", "Fetching data from " + position);
            Word result = wordArray[position];


            view_word.setText(result.getWord());
        } else {
            view_word.setText("word not found");
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
