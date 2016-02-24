package jumblehelper.jumble.com.jumblehelper.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import jumblehelper.jumble.com.jumblehelper.util.ResourceUtil;

/**
 * Created by kon1532 on 9/16/2015.
 */
public class AssetLoader extends AsyncTask<String, Void, String> {

    private Context mContext;
    private ProgressDialog dialog;

    public AssetLoader(Context context)
    {
        mContext = context;
        dialog = new ProgressDialog(context);
        dialog.setCanceledOnTouchOutside(false);
    }

    @Override
    protected String doInBackground(String... urls) {
        ResourceUtil.copyDBFile(mContext, urls[0]);
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
        dialog.setMessage("copying dictionary, please wait...");
        dialog.show();
    }
}

