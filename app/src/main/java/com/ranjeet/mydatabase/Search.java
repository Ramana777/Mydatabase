package com.ranjeet.mydatabase;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by admin on 2/26/2018.
 */

public class Search extends Activity {
    DatabaseHelper mydb;
    EditText editText1;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        editText1 = (EditText) findViewById(R.id.name);
        b1 = (Button) findViewById(R.id.search);
        mydb = new DatabaseHelper(this);
  search();
    }
    public void search() {
        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Cursor cursor = mydb.searchquery(editText1.getText().toString());
                        if (cursor.getCount() == 0) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (cursor.moveToNext()) {
                            buffer.append("Firstname :" + cursor.getString(0) + "\n");
                            buffer.append("Lastname :" + cursor.getString(1) + "\n");
                            buffer.append("qualification :" + cursor.getString(2) + "\n");
                            buffer.append("Email :" + cursor.getString(3) + "\n\n");
                            buffer.append("mobileno :" + cursor.getString(4) + "\n\n");
                            buffer.append("domain :" + cursor.getString(5) + "\n\n");
                            buffer.append("salary :" + cursor.getString(6) + "\n\n");
                            buffer.append("companyname :" + cursor.getString(7) + "\n\n");
                            buffer.append("address :" + cursor.getString(8) + "\n\n");
                        }


                        showMessage("Data", buffer.toString());

                    }
                }

        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    }

