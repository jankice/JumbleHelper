package jumblehelper.jumble.com.jumblehelper.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import jumblehelper.jumble.com.jumblehelper.util.ParseFile;

/**
 * Created by roshni on 9/20/2015.
 */
public class AssetLoader extends AsyncTask<String, Void, String> {

    private Context mContext;
    private ParseFile parseFile;
    private ProgressDialog dialog;

    public AssetLoader(Context context, ParseFile parseFile)
    {
        mContext = context;
        this.parseFile = parseFile;
        dialog = new ProgressDialog(context);
    }


    @Override
    protected String doInBackground(String... urls) {
        parseFile.readAssetFile();
        return "";
    }

    @Override
    protected void onPostExecute(String result) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

   
}
