package jumblehelper.jumble.com.jumblehelper.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import jumblehelper.jumble.com.jumblehelper.DB.SqliteHelper;
import jumblehelper.jumble.com.jumblehelper.util.ResourceUtil;

/**
 * Created by kon1532 on 9/16/2015.
 */
public class DBLoader extends AsyncTask<String, Void, String> {

    private Context mContext;
    private ProgressDialog dialog;
    private SqliteHelper db;
    private  HashMap<Long, HashSet<String>> dictionary;

    public DBLoader(Context context, HashMap<Long, HashSet<String>> dictionary)
    {
        mContext = context;
        dialog = new ProgressDialog(context);
        dialog.setCanceledOnTouchOutside(false);
        this.dictionary = dictionary;
        db = new SqliteHelper(mContext);
    }

    @Override
    protected String doInBackground(String... urls) {

        for (Map.Entry<Long, HashSet<String>> entry : dictionary.entrySet()) {
            long productKey = entry.getKey();
            HashSet value = entry.getValue();

            db.insertValues(productKey, value.toString());
        }
        return "";
    }

    @Override
    protected void onPostExecute(String result) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    protected void onPreExecute() {
        dialog.setMessage("Inserting data, please wait...");
        dialog.show();
    }
}

