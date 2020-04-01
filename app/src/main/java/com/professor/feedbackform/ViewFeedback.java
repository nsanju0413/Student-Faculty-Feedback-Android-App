package com.professor.feedbackform;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewFeedback extends AppCompatActivity {

    String getusername;
    DBadapterComment dbc;
    Cursor c;

    ArrayList<String> commentArray = new ArrayList<>();
    ArrayList<String> getRatingArray = new ArrayList<>();

    ListView clist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_view_feedback);

        dbc = new DBadapterComment(ViewFeedback.this);

        getusername = getIntent().getStringExtra("username");

        clist = (ListView) findViewById(R.id.commentlist);

    }

    @Override
    protected void onResume() {
        super.onResume();

        getRatingArray.clear();
        commentArray.clear();

        dbc.open();

        try {
            c = dbc.getAllAccounts();

            if (c.getCount() > 0) {
                c.moveToFirst();

                do {
                    String name = c.getString(c.getColumnIndex("name"));
                    String status = c.getString(c.getColumnIndex("status"));
                    String comment = c.getString(c.getColumnIndex("comment"));
                    String subjectName = c.getString(c.getColumnIndex("subject"));
                    String rating = c.getString(c.getColumnIndex("rating"));

                    //                nameArray.add(name);
                    //                statusArray.add(status);
                    //                commentArray.add(comment);

                    if (getusername.equals(name)) {

                        String[] ratingArray = rating.toString().split(",");


                        for (int i = 0; i < ratingArray.length; i++) {
                            getRatingArray.add("\n" + ratingArray[i].toString());
                        }

                        commentArray.add("Faculty Name: " + getusername + "\n\nSubject Name: " + subjectName + "\n\nComment: " + comment + "\n\nRating: " + getRatingArray.toString().replace("[", "").replace("]", ""));
                    }

                } while (c.moveToNext());

                ArrayAdapter<String> cAdapter = new ArrayAdapter<String>(ViewFeedback.this, android.R.layout.simple_list_item_1, commentArray);
                clist.setAdapter(cAdapter);
            }

            dbc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
