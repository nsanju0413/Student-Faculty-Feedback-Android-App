package com.professor.feedbackform;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FeedbackHome extends AppCompatActivity {

    ListView flist;

    DBadapterFaculty dbf;
    Cursor c;

    ArrayList<String> idList = new ArrayList<>();
    ArrayList<String> nameList = new ArrayList<>();
    ArrayList<String> mobileList = new ArrayList<>();
    ArrayList<String> mailList = new ArrayList<>();
    ArrayList<String> departList = new ArrayList<>();
    ArrayList<String> usernameList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_feedback_home);

        dbf=new DBadapterFaculty(FeedbackHome.this);

        flist = (ListView) findViewById(R.id.flistview);

        flist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in=new Intent(FeedbackHome.this,UploadFeedback.class);
                in.putExtra("fusername",usernameList.get(position).toString());
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

            ArrayAdapter<String> fAdapter=new ArrayAdapter<String>(FeedbackHome.this,android.R.layout.simple_list_item_1,nameList);
            flist.setAdapter(fAdapter);

        }
        else
        {
            Toast.makeText(FeedbackHome.this,"No faculty found",Toast.LENGTH_LONG).show();
        }

        dbf.close();
    }
}
