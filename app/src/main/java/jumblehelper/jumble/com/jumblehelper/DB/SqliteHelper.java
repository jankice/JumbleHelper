package jumblehelper.jumble.com.jumblehelper.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by KON1532 on 9/20/2015.
 */
public class SqliteHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "jumblehelper.db3";
    public static final String TABLE_NAME = "DICTIONARY";
    public static final String COLUMN_PRODUCT = "PRODUCT";
    public static final String COLUMN_WORDS = "WORDS";

    public static final String GET_WORD_STRING_BY_PRODUCT = "SELECT WORDS FROM DICTIONARY WHERE PRODUCT = ?";

    // Gets the data repository in write mode
    private SQLiteDatabase db = null;

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + SqliteHelper.TABLE_NAME;

    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public long insertValues(long product, String words)
    {
        ContentValues values = new ContentValues();
        values.put(SqliteHelper.COLUMN_PRODUCT, product);
        values.put(SqliteHelper.COLUMN_WORDS, words);
        Log.d(SqliteHelper.class.getName(), "Inserting values");
        Log.d("product ", String.valueOf(product));
        Log.d("Words ", words);
        long newRowId;
        newRowId = db.insert(
                SqliteHelper.TABLE_NAME, null, values);

        return newRowId;
    }

    public String getWordsByProduct(long product)
    {
        String words = null;

        try
        {
            Cursor cursor = db.rawQuery(GET_WORD_STRING_BY_PRODUCT, new String[]{String.valueOf(product)});
            cursor.moveToFirst();
            words =  cursor.getString(0);
        }catch(Exception ex)
        {
            Log.e("Exception: ", ex.toString());
        }

        return  words;
    }
}