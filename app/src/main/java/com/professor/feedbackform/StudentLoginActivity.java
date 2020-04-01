package com.professor.feedbackform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class StudentLoginActivity extends AppCompatActivity {

    EditText usernameEdit, passwordEdit;
    String usernameString, passwordString;
    DBAdapterStudent dbAdapterStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        Initialization();
    }

    public void Initialization() {
        dbAdapterStudent = new DBAdapterStudent(StudentLoginActivity.this);
        usernameEdit = findViewById(R.id.username);
        passwordEdit = findViewById(R.id.password);
    }

    public void loginImplementation(View view) {
        usernameString = usernameEdit.getText().toString();
        passwordString = passwordEdit.getText().toString();

        if (usernameString.isEmpty() || passwordString.isEmpty()) {
            Toast.makeText(StudentLoginActivity.this, "Please verify all the fields", Toast.LENGTH_LONG).show();
        } else {
            try {
                dbAdapterStudent.open();
                String getPassword = dbAdapterStudent.getpassword(usernameString);
                if (getPassword.equalsIgnoreCase(passwordString)) {
                    Intent in = new Intent(StudentLoginActivity.this, FeedbackHome.class);
                    startActivity(in);
                    usernameEdit.setText("");
                    passwordEdit.setText("");
                } else {
                    Toast.makeText(StudentLoginActivity.this, "Invalid User name or Password", Toast.LENGTH_LONG).show();
                }
                dbAdapterStudent.close();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(StudentLoginActivity.this, "Invalid User name or Password", Toast.LENGTH_LONG).show();
            }


        }
    }

    public void signupImplementation(View view) {
        Intent in = new Intent(StudentLoginActivity.this, StudentSignupActivity.class);
        startActivity(in);
    }

}