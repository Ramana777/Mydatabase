package com.ranjeet.mydatabase;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddUser extends Activity {
DatabaseHelper mydb;
    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9;
    Button b1,b2,b3,b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user);
        mydb = new DatabaseHelper(this);

        e1 = (EditText) findViewById(R.id.fstname);
        e2 = (EditText) findViewById(R.id.lstname);
        e3 = (EditText) findViewById(R.id.qualification);
        e4 = (EditText) findViewById(R.id.email);
        e5 = (EditText) findViewById(R.id.mnbr);
        e6 = (EditText) findViewById(R.id.domain);
        e7 = (EditText) findViewById(R.id.sal);
        e8 = (EditText) findViewById(R.id.company);
        e9 = (EditText) findViewById(R.id.address);
        b1 = (Button) findViewById(R.id.submit);
        b2 = (Button) findViewById(R.id.view);
        b3 = (Button) findViewById(R.id.update);
        b4 = (Button) findViewById(R.id.delete);


        //Store data in database

        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = mydb.insertData(e1.getText().toString(),
                                e2.getText().toString(),
                                e3.getText().toString(), e4.getText().toString(),
                                e5.getText().toString(), e6.getText().toString(), e7.getText().toString(), e8.getText().toString(), e9.getText().toString());
                        if (isInserted == true)
                            Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getApplicationContext(), "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );

        //this query is used to update data from database

        b3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = mydb.updateData(
                                e1.getText().toString(),
                                e2.getText().toString(),
                                e3.getText().toString(), e4.getText().toString(),
                                e5.getText().toString(), e6.getText().toString(), e7.getText().toString(), e8.getText().toString(), e9.getText().toString());


                        if (isUpdate == true)
                            Toast.makeText(AddUser.this, "Data Updated in datbase", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AddUser.this, "Data not Updated in datbase", Toast.LENGTH_LONG).show();
                    }
                }
        );

        //this query is used to delete data from database

        b4.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = mydb.deletedData(e1.getText().toString());
                        if (deletedRows == null)
                            Toast.makeText(AddUser.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AddUser.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                    }
                }
        );

        //view data from database

        b2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = mydb.getAllData();
                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Firstname :" + res.getString(0) + "\n");
                            buffer.append("Lastname :" + res.getString(1) + "\n");
                            buffer.append("qualification :" + res.getString(2) + "\n");
                            buffer.append("Email :" + res.getString(3) + "\n\n");
                            buffer.append("mobileno :" + res.getString(4) + "\n\n");
                            buffer.append("domain :" + res.getString(5) + "\n\n");
                            buffer.append("salary :" + res.getString(6) + "\n\n");
                            buffer.append("companyname :" + res.getString(7) + "\n\n");
                            buffer.append("address :" + res.getString(8) + "\n\n");
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





