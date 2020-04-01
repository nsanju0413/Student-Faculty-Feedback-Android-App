package com.professor.feedbackform;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddFaculty extends AppCompatActivity {

    EditText nameEdit, mobileEdit, mailEdit, departEdit, userEdit, passEdit;
    Button addButton;

    DBadapterFaculty dbf;

    String nameString, mobileString, mailString, departString, userString, passString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_add_faculty);

        dbf = new DBadapterFaculty(AddFaculty.this);

        nameEdit = (EditText) findViewById(R.id.name);
        mobileEdit = (EditText) findViewById(R.id.mobile);
        mailEdit = (EditText) findViewById(R.id.mail);
        departEdit = (EditText) findViewById(R.id.depart);
        userEdit = (EditText) findViewById(R.id.username);
        passEdit = (EditText) findViewById(R.id.password);
        addButton = (Button) findViewById(R.id.add);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nameString = nameEdit.getText().toString();
                mobileString = mobileEdit.getText().toString();
                mailString = mailEdit.getText().toString();
                departString = departEdit.getText().toString();
                userString = userEdit.getText().toString();
                passString = passEdit.getText().toString();

                if (nameString.length() > 0 && mobileString.length() > 0 && mailString.length() > 0 && departString.length() > 0 && userString.length() > 0 && passString.length() > 0) {

                    dbf.open();
                    long id = dbf.insertfaculty(nameString, mobileString, mailString, departString, userString, passString);
                    dbf.close();

                    if (id > 0) {
                        Intent in = new Intent(AddFaculty.this, FacultyList.class);
                        startActivity(in);
                        finish();
                    }

                } else {
                    Toast.makeText(AddFaculty.this, "Please verify all the fields", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        nameEdit.setText("");
        mobileEdit.setText("");
        mailEdit.setText("");
        departEdit.setText("");
        userEdit.setText("");
        passEdit.setText("");
    }
}
