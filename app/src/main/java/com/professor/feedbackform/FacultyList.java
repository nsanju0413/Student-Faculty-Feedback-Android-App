package com.professor.feedbackform;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FacultyList extends AppCompatActivity {

    Button addfacultyButton;
    ListView flistView;

    DBadapterFaculty dbf;
    Cursor c;

    ArrayList<String> idList=new ArrayList<>();
    ArrayList<String> nameList=new ArrayList<>();
    ArrayList<String> mobileList=new ArrayList<>();
    ArrayList<String> mailList=new ArrayList<>();
    ArrayList<String> departList=new ArrayList<>();
    ArrayList<String> usernameList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_faculty_list);

        dbf=new DBadapterFaculty(FacultyList.this);

        addfacultyButton= (Button) findViewById(R.id.addfaculty);
        flistView= (ListView) findViewById(R.id.facultylist);

        addfacultyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(FacultyList.this,AddFaculty.class);
                startActivity(in);

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        idList.clear();
        nameList.clear();
        mobileList.clear();
        mailList.clear();
        departList.clear();
        usernameList.clear();

        dbf.open();
        c=dbf.getAllAccounts();

        if (c.getCount()>0)
        {
            c.moveToFirst();

            do {

                String id=c.getString(c.getColumnIndex("id"));
                String name=c.getString(c.getColumnIndex("name"));
                String mobile=c.getString(c.getColumnIndex("mobile"));
                String mail=c.getString(c.getColumnIndex("mail"));
                String depart=c.getString(c.getColumnIndex("depart"));
                String username=c.getString(c.getColumnIndex("username"));

                idList.add(id);
                nameList.add(name);
                mobileList.add(mobile);
                mailList.add(mail);
                departList.add(depart);
                usernameList.add(username);

            }while (c.moveToNext());

            ArrayAdapter<String> fAdapter=new ArrayAdapter<String>(FacultyList.this,android.R.layout.simple_list_item_1,nameList);
            flistView.setAdapter(fAdapter);

        }
        else
        {
            Toast.makeText(FacultyList.this,"No faculty found",Toast.LENGTH_LONG).show();
        }

        dbf.close();
    }
}
