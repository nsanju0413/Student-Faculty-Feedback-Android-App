package com.professor.feedbackform;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {
    EditText userEdiit,passEdit;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_admin_login);
        userEdiit= (EditText) findViewById(R.id.adminusername);
        passEdit= (EditText) findViewById(R.id.adminpassword);
        loginButton= (Button) findViewById(R.id.adminlogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userEdiit.getText().toString().length() > 0 && passEdit.getText().toString().length() > 0) {
                    if (userEdiit.getText().toString().equals("mgit") && passEdit.getText().toString().equals("mgit")) {
                        Intent in = new Intent(AdminLogin.this, AdminHome.class);
                        startActivity(in);
                        userEdiit.setText("");
                        passEdit.setText("");
                    } else {
                        Toast.makeText(AdminLogin.this, "Enter  correct fields", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(AdminLogin.this, "Enter correct fields", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        userEdiit.setText("");
        passEdit.setText("");
    }
}
