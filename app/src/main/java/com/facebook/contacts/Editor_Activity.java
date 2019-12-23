package com.facebook.contacts;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.contacts.data.ContactContract;
import com.facebook.contacts.data.ContactContract.contactEntry;
import com.facebook.contacts.data.DB_Helper;

public class Editor_Activity extends AppCompatActivity {

    private DB_Helper db_helper;

    private EditText mFirstName;

    private EditText mLastName;

    private EditText mEMail;

    private EditText mMobNo;

    private Button mSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        mFirstName = findViewById(R.id.FirstName);
        mLastName = findViewById(R.id.LastName);
        mEMail = findViewById(R.id.EMail);
        mMobNo = findViewById(R.id.MobNo);
        mSubmit = findViewById(R.id.Submit);
        mSubmit.setOnClickListener(listener);



    }

    public void insertContacts(){
        String firstName = mFirstName.getText().toString().trim();
        String lastName = mLastName.getText().toString().trim();
        String eMail = mEMail.getText().toString().trim();
        String mobNo = mMobNo.getText().toString().trim();


        //Log.i("No is", mobNo);

        db_helper = new DB_Helper(this);

        SQLiteDatabase db = db_helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(contactEntry.COLUMN_FIRST_NAME , firstName);
        values.put(contactEntry.COLUMN_lAST_NAME,lastName);
        values.put(contactEntry.COLUMN_EMAIL,eMail);
        values.put(contactEntry.COLUMN_MOB_NUM,mobNo);

        long RowID = db.insert(contactEntry.TABLE_NAME,null,values);

        if(RowID == -1){
            Toast.makeText(this,"Something is wrong",Toast.LENGTH_SHORT).show();
//            if(db.isOpen()) {
//                Toast.makeText(this,"DATABASE is OPEN",Toast.LENGTH_LONG).show();
//            }
        }
        else{
            Toast.makeText(this, contactEntry._ID,Toast.LENGTH_LONG).show();
        }

    }

     private   View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               insertContacts();
            }
        };


}
