package com.facebook.contacts.data;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DB_connect {
    public static void create() {
        SQLiteDatabase DB_Create = SQLiteDatabase.openOrCreateDatabase("Contacts", null);
        DB_Create.execSQL("CREATE TABLE IF NOT EXISTS contacts(Name VARCHAR,Num NUMBER);");
        DB_Create.execSQL("INSERT INTO contacts VALUES('Akshay Rastogi','8004022257');");
    }
    public static void fetch(){
        SQLiteDatabase DB_Create = SQLiteDatabase.openOrCreateDatabase("Contacts", null);
        Cursor resultSet = DB_Create.rawQuery("Select * from contacts", null);
        resultSet.moveToFirst();
        String Name = resultSet.getString(0);
        String Num = resultSet.getString(1);
    }
}
