package jumblehelper.jumble.com.jumblehelper;

import android.content.res.Configuration;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Locale;

import jumblehelper.jumble.com.jumblehelper.task.AssetLoader;
import jumblehelper.jumble.com.jumblehelper.util.ParseFile;

public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private EditText edt_char;
    private TextToSpeech tts;
    private ParseFile parseFile;
    private ListView wordListView;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        edt_char = (EditText) findViewById(R.id.editText);
        wordListView = (ListView) findViewById(R.id.word_listview);
        wordListView.setOnItemClickListener(this);
        linearLayout = (LinearLayout) findViewById(R.id.linear_layout_child_2);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.ENGLISH);
                }

            }
        });

        parseFile = new ParseFile(this);

        new AssetLoader(this, parseFile).execute();
    }

    public void viewOnClick(View button) {

        switch (button.getId()) {

            case R.id.btnGet:

                String word = edt_char.getText().toString();
                if (!"".equalsIgnoreCase(word) && parseFile.supportedWordFormat(word)) {
                    displayJumbleWord(parseFile.getAllMatchingJumbleWords(word.toLowerCase()));
                } else {
                    hideView();
                    Toast.makeText(getApplicationContext(), "invalid word", Toast.LENGTH_LONG).show();
                }
                break;

        }

    }

    private void displayJumbleWord(HashSet<String> jumbleWordsSet) {
        if (jumbleWordsSet != null && !jumbleWordsSet.isEmpty()) {
            wordListView.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.VISIBLE);
            String[] words = jumbleWordsSet.toArray(new String[jumbleWordsSet.size()]);
            wordListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words));
        } else {
            hideView();
        }
    }

    private void hideView()
    {
        wordListView.setVisibility(View.GONE);
        linearLayout.setVisibility(View.GONE);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String spk = (String)parent.getItemAtPosition(position);
        Log.d("Selected text:", spk);
        tts.speak(spk, TextToSpeech.QUEUE_FLUSH, null);
    }

}
