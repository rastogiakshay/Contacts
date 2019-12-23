package com.facebook.contacts.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.facebook.contacts.data.ContactContract.contactEntry;

public class DB_Helper extends SQLiteOpenHelper {
    public static final String LOG_TAG = DB_Helper.class.getSimpleName();

    private static final  String SQL_CREATE_CONTACT_TABLE = "CREATE TABLE IF NOT EXISTS " + contactEntry.TABLE_NAME + " ("
            + contactEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + contactEntry.COLUMN_FIRST_NAME + " TEXT, "
            + contactEntry.COLUMN_lAST_NAME + " TEXT NOT NULL, "
            + contactEntry.COLUMN_EMAIL + " TEXT, "
            + contactEntry.COLUMN_MOB_NUM + " TEXT NOT NULL DEFAULT 0);";

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "contacts";

    public DB_Helper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        Log.i("SQL Error", SQL_CREATE_CONTACT_TABLE);
        db.execSQL(SQL_CREATE_CONTACT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
