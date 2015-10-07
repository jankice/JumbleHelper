package jumblehelper.jumble.com.jumblehelper.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;

import jumblehelper.jumble.com.jumblehelper.DB.SqliteHelper;

/**
 * Created by kon1532 on 9/16/2015.
 */
public class ResourceUtil {

    public static void copyDBFile(Context mContext, String fileName)
    {
        try
        {
            // Open your local db as the input stream
            InputStream myInput = mContext.getAssets().open(fileName);
            // Path to the just created empty db
            String outFileName = mContext.getDatabasePath(fileName).getAbsolutePath();
            // Open the empty db as the output stream

            File databaseFile = new File(outFileName);

            if(!databaseFile.getParentFile().exists())
            {
                databaseFile.getParentFile().mkdir();
            }

            OutputStream myOutput = new FileOutputStream(outFileName);
            // transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            // Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }catch(Exception ex)
        {
            Log.e("Exception in copy File ", ex.toString());
        }
    }

    public static boolean supportedWordFormat(String word) {
        return word.matches("[a-zA-Z]+");
    }
}