package jumblehelper.jumble.com.jumblehelper.Task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import jumblehelper.jumble.com.jumblehelper.DB.SqliteHelper;

/**
 * Created by kon1532 on 9/16/2015.
 */
public class DataDictionaryTask extends AsyncTask<Long, Void, String[]> {

    private Context mContext;
    private ProgressDialog dialog;
    private SqliteHelper db;
    private ListView listView;

    public DataDictionaryTask(Context context, ListView lstView)
    {
        mContext = context;
        dialog = new ProgressDialog(context);
        dialog.setCanceledOnTouchOutside(false);
        this.listView = lstView;
        db = new SqliteHelper(mContext);
    }

    @Override
    protected String[] doInBackground(Long... urls) {

        String[] result = null;
        String words = db.getWordsByProduct(urls[0]);

        if(words != null)
        {
            words = words.replaceAll("\\[", "").replaceAll("\\]","");

            if(words.contains(","))
            {
                result = words.split(",");
            }else
            {
                result = new String[]{words};
            }
        }

        return result;
    }

    @Override
    protected void onPostExecute(String[] result) {

        if(result != null)
        {
            ((View)(listView.getParent())).setVisibility(View.VISIBLE);
            listView.setAdapter(new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, result));
        }else
        {
            ((View)(listView.getParent())).setVisibility(View.GONE);
            Toast.makeText(mContext, "no word found", Toast.LENGTH_LONG).show();
        }

        if (dialog.isShowing()) {
            dialog.dismiss();
        }

    }

    @Override
    protected void onPreExecute() {
        dialog.setMessage("Fetching data, please wait...");
        dialog.show();
    }
}

