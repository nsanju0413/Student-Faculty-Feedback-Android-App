package com.professor.feedbackform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class StudentSignupActivity extends AppCompatActivity {

    EditText nameEdit, rollnoEdit, mobileEdit, mailEdit, departEdit, usernameEdit, passwordEdit, cPasswordEdit;
    String nameString, rollnoString, mobileString, mailString, departString, usernameString, passwordString, cPasswordString;
    DBAdapterStudent dbAdapterStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signup);
        Initialization();
    }

    public void Initialization() {
        dbAdapterStudent = new DBAdapterStudent(StudentSignupActivity.this);
        nameEdit = findViewById(R.id.name);
        rollnoEdit = findViewById(R.id.roolno);
        mobileEdit = findViewById(R.id.mobile);
        mailEdit = findViewById(R.id.mail);
        departEdit = findViewById(R.id.depart);
        usernameEdit = findViewById(R.id.username);
        passwordEdit = findViewById(R.id.password);
        cPasswordEdit = findViewById(R.id.cPassword);
    }

    public void registerImplementation(View view) {
        nameString = nameEdit.getText().toString();
        rollnoString = rollnoEdit.getText().toString();
        mobileString = mobileEdit.getText().toString();
        mailString = mailEdit.getText().toString();
        departString = departEdit.getText().toString();
        usernameString = usernameEdit.getText().toString();
        passwordString = passwordEdit.getText().toString();
        cPasswordString = cPasswordEdit.getText().toString();

        if (nameString.isEmpty() || rollnoString.isEmpty() || mobileString.isEmpty() || mailString.isEmpty() || departString.isEmpty() || usernameString.isEmpty() || passwordString.isEmpty() || cPasswordString.isEmpty()) {
            Toast.makeText(StudentSignupActivity.this, "Please verify all the fields", Toast.LENGTH_LONG).show();
        } else {
            if (passwordString.equalsIgnoreCase(cPasswordString)) {

                dbAdapterStudent.open();
                long id = dbAdapterStudent.insertStudent(nameString, rollnoString, mobileString, mailString, departString, usernameString, passwordString);
                if (id > 0) {
                    Toast.makeText(StudentSignupActivity.this, "Student register successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(StudentSignupActivity.this, StudentLoginActivity.class));
                }
                dbAdapterStudent.close();

            } else {
                Toast.makeText(StudentSignupActivity.this, "Password mismatch", Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        nameEdit.setText("");
        rollnoEdit.setText("");
        mobileEdit.setText("");
        mailEdit.setText("");
        departEdit.setText("");
        usernameEdit.setText("");
        passwordEdit.setText("");
        cPasswordEdit.setText("");
    }
}
