package com.professor.feedbackform;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class AdminHome extends AppCompatActivity implements View.OnClickListener{

    Button facultyButton,feedbackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_admin_home);

        facultyButton= (Button) findViewById(R.id.faculty);
        feedbackButton= (Button) findViewById(R.id.feedback);

        facultyButton.setOnClickListener(this);
        feedbackButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int id=v.getId();
        if (id==R.id.faculty)
        {
            Intent in=new Intent(AdminHome.this,FacultyList.class);
            startActivity(in);
        }
        else if (id==R.id.feedback)
        {
            Intent in=new Intent(AdminHome.this,FacultyScreen.class);
            startActivity(in);
        }
    }
}
