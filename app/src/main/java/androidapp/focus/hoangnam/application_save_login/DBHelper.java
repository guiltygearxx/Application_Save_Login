package androidapp.focus.hoangnam.application_save_login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by huy.nquoc on 12/24/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyAppDB";

    public static final Integer DATABASE_VERSION = 1;
    public static final String TABLE_STORED_DATA = "stored_data";

    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private void createTable(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_STORED_DATA + " (name text primary key,value text)");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        this.createTable(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORED_DATA);

        this.createTable(db);
    }

    public void saveLoginData(String username, String token) {

        SQLiteDatabase db = this.getWritableDatabase();

        storedData("username", username, db);
        storedData("token", token, db);
    }

    private void storedData(String name, String value, SQLiteDatabase db) {

        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("value", value);

        db.insert(TABLE_STORED_DATA, null, values);
    }

    public String getStoredData(String name) {

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "select value from " + TABLE_STORED_DATA + " where name = ?";

        Cursor res = db.rawQuery(sql, new String[]{name});

        res.moveToFirst();

        if (res.isAfterLast()) {

            return null;
        } else {

            return res.getString(0);
        }
    }

    public void clearStoredData(String name) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_STORED_DATA, "name = ?", new String[]{name});
    }
}
