package com.facebook.contacts;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.contacts.data.ContactContract.contactEntry;

import com.facebook.contacts.data.DB_Helper;

public class MainActivity extends AppCompatActivity {

    private DB_Helper db_helper;
    private Button Demo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Editor_Activity.class);
                startActivity(intent);
            }
        });

//        Demo = findViewById(R.id.Demo);
//        Demo.setOnClickListener(listener);
        db_helper = new DB_Helper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayEntries();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void insertContacts(){
                //Log.i("No is", mobNo);



        SQLiteDatabase db = db_helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(contactEntry.COLUMN_FIRST_NAME,"Akshay");
        values.put(contactEntry.COLUMN_lAST_NAME,"Rastogi");
        values.put(contactEntry.COLUMN_EMAIL,"rastogiakshay@live.com");
        values.put(contactEntry.COLUMN_MOB_NUM,"8004022257");

        long RowID = db.insert(contactEntry.TABLE_NAME,null,values);

        if(RowID == -1){
            Toast.makeText(this,"Something is wrong",Toast.LENGTH_SHORT).show();
            if(db.isOpen()) {
                //Toast.makeText(this,"DATABASE is OPEN",Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(this,"Entry was Successful",Toast.LENGTH_LONG).show();
        }

    }
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            insertContacts();
        }
    };

    public void displayEntries(){
        SQLiteDatabase db = db_helper.getReadableDatabase();

        String[] projection = {
                contactEntry._ID,
                contactEntry.COLUMN_FIRST_NAME,
                contactEntry.COLUMN_lAST_NAME,
                contactEntry.COLUMN_EMAIL,
                contactEntry.COLUMN_MOB_NUM };

        Cursor cursor = db.query(contactEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        TextView display = (TextView) findViewById(R.id.text_view_contacts);

        try {
            display.setText("This table contains" + cursor.getCount() + " Table.\n\n");
            display.append(contactEntry._ID + "-" +
                    contactEntry.COLUMN_FIRST_NAME + "-" +
                    contactEntry.COLUMN_lAST_NAME + "-" +
                    contactEntry.COLUMN_EMAIL + "-" +
                    contactEntry.COLUMN_MOB_NUM + "\n");

            int idIndex = cursor.getColumnIndex(contactEntry._ID);
            int fNameIndex = cursor.getColumnIndex(contactEntry.COLUMN_FIRST_NAME);
            int lNameIndex = cursor.getColumnIndex(contactEntry.COLUMN_lAST_NAME);
            int eMailIndex = cursor.getColumnIndex(contactEntry.COLUMN_EMAIL);
            int mobNumIndex = cursor.getColumnIndex(contactEntry.COLUMN_MOB_NUM);


            String log = Integer.toString(idIndex);
            Log.e("ERROR IS HERE",contactEntry.COLUMN_FIRST_NAME);
            while(cursor.moveToNext()){

                int currentID = cursor.getInt(idIndex);
                String currentFName = cursor.getString(fNameIndex);
                String currentLName = cursor.getString(lNameIndex);
                String currentEMail = cursor.getString(eMailIndex);
                String currentMobNum = cursor.getString(mobNumIndex);

                display.append("\n" + currentID + "-"
                        + currentFName + " - "
                        + currentLName + "-"
                        + currentEMail + "-"
                        + currentMobNum);
            }
        }finally {
            db.close();
        }
    }
}
