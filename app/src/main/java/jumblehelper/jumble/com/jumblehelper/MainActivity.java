package jumblehelper.jumble.com.jumblehelper;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Locale;

import jumblehelper.jumble.com.jumblehelper.Task.AssetLoader;
import jumblehelper.jumble.com.jumblehelper.Task.DataDictionaryTask;
import jumblehelper.jumble.com.jumblehelper.util.AlphabetToPrime;
import jumblehelper.jumble.com.jumblehelper.util.ResourceUtil;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    private EditText edt_char;
    private TextToSpeech tts;
    private ListView wordListView;
    private SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        edt_char = (EditText) findViewById(R.id.editText);
        wordListView = (ListView) findViewById(R.id.word_listview);
        wordListView.setOnItemClickListener(this);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.ENGLISH);
                }

            }
        });

        copyDBFileOnlyOnce();
    }

    public void viewOnClick(View button) {

        switch (button.getId()) {

            case R.id.btnGet:
                String word = edt_char.getText().toString();
                if (!"".equalsIgnoreCase(word) && ResourceUtil.supportedWordFormat(word)) {
                   new DataDictionaryTask(this, wordListView).execute(AlphabetToPrime.calcPrimeProduct(word.toLowerCase()));
                } else {
                    ((View)(wordListView.getParent())).setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Please enter a-z characters only", Toast.LENGTH_LONG).show();
                }
                hideSoftKeyboard(this);
                break;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String spk = (String)parent.getItemAtPosition(position);
        Log.d("Selected text:", spk);
        tts.speak(spk, TextToSpeech.QUEUE_FLUSH, null);
    }

    private void copyDBFileOnlyOnce()
    {
        prefs = getSharedPreferences("jumblehelper_pref", MODE_PRIVATE);

        if (prefs.getBoolean("firstrun", true)) {
            new AssetLoader(this).execute("jumblehelper.db3");
            prefs.edit().putBoolean("firstrun", false).commit();
        }
    }



    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }


}