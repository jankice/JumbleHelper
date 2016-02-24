package jumblehelper.jumble.com.jumblehelper.task;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import jumblehelper.jumble.com.jumblehelper.R;

/**
 * Created by roshni on 10/7/2015.
 */

public class WebViewDictionary extends Activity {
    private WebView dic_view;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        dic_view = (WebView)findViewById(R.id.webDic);

        dic_view.getSettings().setJavaScriptEnabled(true);
        Bundle extras = getIntent().getExtras();
        String url = extras.getString("");
       // dic_view.loadUrl(url);
        dic_view.loadUrl("http://dictionary.reference.com");
    }

}
